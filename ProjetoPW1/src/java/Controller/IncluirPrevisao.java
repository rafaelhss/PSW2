package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class IncluirPrevisao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Define formato para validação da data
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);

            // Define formato para INSERT no MySQL
            SimpleDateFormat sdfMySQL = new SimpleDateFormat("yyyy/MM/dd");
            sdfMySQL.setLenient(false);
            
            // Recebe campos do FORMULÁRIO
            Date sDtPrev = sdf.parse(request.getParameter("sDtPrevForm"));
            String sSigno = request.getParameter("signoForm");
            String sPrevisao = request.getParameter("sPrevisaoForm");
            
            // Converte a data para o formato do MySQL
            String sDtPrevMySQL = sdfMySQL.format(sDtPrev);
            
            //Carrega o driver do MySQL para memória + banco + Transação
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbastral","root", "291220");
            java.sql.Statement transacao = conn.createStatement(); 
            
            // SQL referente ao INSERT
            String query = "insert into dbastral.previsaoastral " +
                           "(dataPrevisao , conteudoPrevisao , Signo_codigoSigno) " +
                           " values( '" + sDtPrevMySQL + "' , '" + sPrevisao + "', '" + sSigno + "')";
            
            //executa um update
            int linhas = transacao.executeUpdate(query);            
            
            // Fecha a transação + conexão
            transacao.close();
            conn.close();
            
            // Adiciona atributos
            String mens = "Previsão incluída com sucesso !";
            request.setAttribute("mensagem",mens);
            
            // Aciona pagina JSP - Formulário de Edição
            request.getRequestDispatcher("ManterPrevisaoAstral.jsp").forward(request, response);            
        }
        catch (Exception e){            
            // Adiciona objeto
            request.setAttribute("erro", e);
            
            // Aciona pagina JSP de ERRO
            request.getRequestDispatcher("PaginaErro.jsp").forward(request, response);
        }          
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
