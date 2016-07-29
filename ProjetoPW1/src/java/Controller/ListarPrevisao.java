package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DesingnPatterns.ConSGBD;
import bean.DadosPrevisao;
import java.io.IOException;
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
 * @author Fábio Tranzillo Nogueira
 */
public class ListarPrevisao extends HttpServlet {

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

            // Define formato para YYYY/MM/DD
            SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
            sdfYYYYMMDD.setLenient(false);

            // Recebe campos do FORMULÁRIO e formata DATAS
            String sDtPrevMySQL = "", sDtDisplay = "";
            if (!request.getParameter("sDtPrevForm").equals("")) {
                Date sDtPrev = sdf.parse(request.getParameter("sDtPrevForm"));
                // Converte a data para o formato do MySQL
                sDtPrevMySQL = sdfYYYYMMDD.format(sDtPrev);
                // Converte a data para o formato do Display
                sDtDisplay = sdf.format(sDtPrev);
            }   
            String sSigno = request.getParameter("signoForm");
            
            //Carrega o driver do MySQL para memória + banco + Transação (sem SINGLETON)
            //  Class.forName("com.mysql.jdbc.Driver");
            //  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbastral","root", "291220");
            
            // Conexão utilizando SINGLETON 
            Connection conn = ConSGBD.getConnection();
            
            // Cria a transação
            java.sql.Statement transacao = conn.createStatement(); 
            
            // Montagem do comando SQL
            String query = "select idPrevisaoAstral, date_format('dataPrevisao','%d/%m/%Y'), conteudoPrevisao, nomeSigno" +
                           " from dbastral.previsaoastral, dbastral.signo where codigoSigno = Signo_codigoSigno";
            String criterio = " (Sem critério informado)";
            
            if (!sDtPrevMySQL.equals("") && (!sSigno.equals(""))) {
                query = query + " and dataPrevisao = '" + sDtPrevMySQL + "' and Signo_codigoSigno = '" + sSigno + "'" ;
                criterio = " Data Previsão(" + sDtPrevMySQL + ")" + " Signo(" + sSigno + ")";
            }
            
            if (!sDtPrevMySQL.equals("") && (sSigno.equals("")))  {
                query = query + " and dataPrevisao = '" + sDtPrevMySQL + "'";
                criterio = " Data Previsão(" + sDtDisplay + ")";
            }
                                        
            if (sDtPrevMySQL.equals("") && (!sSigno.equals(""))) {
                query = query + " and Signo_codigoSigno = '" + sSigno + "'" ;
                criterio = " Signo(" + sSigno + ")";
            }
            
            query = query + " order by nomeSigno";
            
            ResultSet res = transacao.executeQuery(query);         
            
            // Criação de ArrayList com PREVISÕES
            ArrayList<DadosPrevisao> lista = new ArrayList<DadosPrevisao>();
            
            // Montar tabela resultado da consulta
            while(res.next()) {
                // Criação de objeto listaPrevisoes do tipo DadosPrevisao
                DadosPrevisao listaPrevisoes = new DadosPrevisao();
                // Recuperação dos atributos do ResultSet, SetBEAN e AddArrayList
                listaPrevisoes.setIdPrevisao(res.getInt("idPrevisaoAstral"));
                listaPrevisoes.setCodigoSigno(res.getString("nomeSigno"));
                listaPrevisoes.setConteudoPrevisao(res.getString("conteudoPrevisao"));
                lista.add(listaPrevisoes);
            }            
            
            // Fecha a transação + conexão
            transacao.close();
            
            // Caso não seja mais utilizada a conexão com o SINGLETON, retirar o comentário abaixo
            // conn.close();
            
            // Adiciona atributos
            String mens = "Consulta realizada com sucesso !" + criterio;
            request.setAttribute("mensagem",mens);
            request.setAttribute("lista",lista);
            
            // Aciona pagina JSP - Formulário de Edição
            request.getRequestDispatcher("ListarPrevisao.jsp").forward(request, response);            
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
