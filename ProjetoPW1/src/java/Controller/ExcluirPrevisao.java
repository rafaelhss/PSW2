package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class ExcluirPrevisao extends HttpServlet {

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
            // Recebe campos do FORMULÁRIO
            String [] itens = request.getParameterValues("itensPrevisao");            
           
            //Carrega o driver do MySQL para memória + banco + Transação
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbastral","root", "291220");
            java.sql.Statement transacao = conn.createStatement(); 
            
            // SQL referente ao DELETE
            if (itens != null && itens.length > 0) {
                String idsDelete = "( ";
                
                for (int i=0; i< itens.length; i++) {
                    idsDelete = idsDelete + itens[i];
                    if (itens.length > (i+1)) idsDelete = idsDelete + " , ";
                }    
                
                idsDelete = idsDelete + " )";
            
                String query = "delete from dbastral.previsaoastral where idPrevisaoAstral in " + idsDelete;
            
                //executa um DELETE
                int linhas = transacao.executeUpdate(query);            
            }
            
            // Fecha a transação + conexão
            transacao.close();
            conn.close();
            
            // Adiciona atributos
            String mens = "Previsões excluidas com sucesso !";
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
