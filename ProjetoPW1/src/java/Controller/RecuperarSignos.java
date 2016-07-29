package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.DadosSignos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class RecuperarSignos extends HttpServlet {

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
            //Carrega o driver do MySQL para memória
            Class.forName("com.mysql.jdbc.Driver");
            
            // Conecta ao banco jdbc:mysql://localhost:3306/dbastral
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbastral","root", "291220");
            
            // Cria a transação
            java.sql.Statement transacao = conn.createStatement();  
            
            // Executar comando SQL
            String query = "Select codigoSigno, nomeSigno from dbastral.signo;";
            ResultSet res = transacao.executeQuery(query); 
            
            // Criação de ArrayList do BEAN referente aos dados do SIGNO
            ArrayList<DadosSignos> lista = new ArrayList<DadosSignos>();
            
            // Carga do ArrayList
            while(res.next()) {
                // Criação de objeto listaSignos do tipo DadosSignos
                DadosSignos listaSignos = new DadosSignos();
                // Recuperação dos atributos do ResultSet, SetBEAN e AddArrayList
                listaSignos.setCodigoSigno(res.getString("codigoSigno"));
                listaSignos.setNomeSigno(res.getString("nomeSigno"));
                lista.add(listaSignos);
            }

            // Fecha a transação + conexão
            transacao.close();
            conn.close();
            
            // Adiciona atributos
            String mens = "Preencha o formulário a seguir:";
           
            HttpSession sessao = request.getSession();
            
            request.setAttribute("mensagem",mens);            
            sessao.setAttribute("lista", lista);
          //  request.setAttribute("lista", lista);
            
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
