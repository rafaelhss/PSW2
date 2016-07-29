package Controller;

import bean.DadosAstral;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ResultadoPortaAstral"})
public class ResultNovoPortalAstral extends HttpServlet {

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
        
        // Carrega BEAN DadosAstral com dados do formulário HTML
        DadosAstral result = CarregarDadosAstral(request);
        
        // Acionamento do formulário JSP enviando o objeto result
        request.setAttribute("dados", result);
        request.getRequestDispatcher("NovoPortalAstral.jsp").forward(request, response);
    }

    public DadosAstral CarregarDadosAstral(HttpServletRequest request) {
        // Criação de objeto result do tipo DadosAstral
        DadosAstral result = new DadosAstral();
        
        // Recebe o nome do formulário
        result.setNome(request.getParameter("sNomeForm"));
        result.setData(request.getParameter("sDtNascForm"));
        
        // Calcula o valor referente as letras que compõe o nome
        result.setNumeroSorte(somaValorLetras(result.getNome()));
        if (result.getNumeroSorte() == 0) result.setNome("Nome não informado");
        
        // Verifica o SIGNO
        result.setSigno(verificaSigno(result.getData()));
        
        // Sorteio de carta
        result.setEnderecoImagem(sorteioCarta());
        
        // Criptografa Nome
        result.setNomeCriptografado(criptografaTexto(result.getNome().toUpperCase()));
        
        // Recupera previsões de arquivo texto
        ArrayList<String> al = new ArrayList<String>();
        al = soteioPrevisao(result.getNumeroSorte());
        result.setListaPrevisoes(al);
        
        // Recupera previsões do MySQL
        ArrayList<String> al1 = new ArrayList<String>();
        al1 = recuperaPrevisao(result.getSigno());
        result.setListaPrevisoesUsuario(al1);
        
        return result;
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

    @SuppressWarnings("empty-statement")
    
    private int somaValorLetras(String texto) {
        char[] letras = texto.toCharArray();
        
        int num = 0, soma = 0;
        
        for (int i = 0; i < letras.length; i++) {
            soma = soma + Character.getNumericValue(letras[i]);
        }
        
        if (letras.length > 0) {
           num = verificaDigitos(soma); 
        } 
        return num;
    }
    
    //Verifica quantidade de dígitos de um número e soma até que sobrar 1 dígito
    private int verificaDigitos(int numero){
        boolean fim = false;

        do {
            // Converte resultado em VETOR de caracteres
            char [] digitos;
            digitos = Integer.toString(numero).toCharArray();
            
            // Testa a quantidade de dígitos resultado da soma
            if (digitos.length > 1) {
                int soma = 0;
                
                // Soma valores correspondente aos dígitos resultado
                for (int i = 0; i < digitos.length; i++) {
                    soma = soma + Character.getNumericValue(digitos[i]);       
                }
                numero = soma;
                
            } else {
                fim = true;
            }
        } while (!fim);           
        
        return numero;
    }

    //Verifica SIGNO
    private String verificaSigno(String data){
        String signo = null;
        
        // Define formato para validação da data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        
        try {
            // Validaação da Data de Nascimento
            Date dData = sdf.parse(data);
            
            // Seleciona o dia da Data de Nascimento
            String sDia = data.substring(0, 2);
            int dia = Integer.parseInt(sDia);
            
            // Seleciona o mês da Data de Nascimento
            String mes = data.substring(3, 5);

            if (signo == null) {
                if ( (mes.equals("01") && dia >= 21) ||
                     (mes.equals("02") && dia <= 19) ) return "Aquário";
                if ( (mes.equals("02") && dia >= 20) ||
                     (mes.equals("03") && dia <= 20) ) return "Peixes";        
                if ( (mes.equals("03") && dia >= 21) ||
                     (mes.equals("04") && dia <= 20) ) return "Áries"; 
                if ( (mes.equals("04") && dia >= 21) ||
                     (mes.equals("05") && dia <= 20) ) return "Touro";         
                if ( (mes.equals("05") && dia >= 21) ||
                     (mes.equals("06") && dia <= 20) ) return "Gémeos";   
                if ( (mes.equals("06") && dia >= 21) ||
                     (mes.equals("07") && dia <= 21) ) return "Câncer";
                if ( (mes.equals("07") && dia >= 22) ||
                     (mes.equals("08") && dia <= 22) ) return "Leão";  
                if ( (mes.equals("08") && dia >= 23) ||
                     (mes.equals("09") && dia <= 22) ) return "Virgem";  
                if ( (mes.equals("09") && dia >= 23) ||
                     (mes.equals("10") && dia <= 22) ) return "Libra";  
                if ( (mes.equals("10") && dia >= 23) ||
                     (mes.equals("11") && dia <= 21) ) return "Escopião";  
                if ( (mes.equals("11") && dia >= 22) ||
                     (mes.equals("12") && dia <= 21) ) return "Sagitário";  
                if ( (mes.equals("12") && dia >= 22) ||
                     (mes.equals("01") && dia <= 20) ) return "Capricórnio";  
            }            
        } catch(ParseException e) {
            // Acionamento do formulário JSP enviando o objeto result         
            signo = "Não foi possível avaliar porque a Data de Nascimento informada é Inválida";
        }
     
        return signo;
    } 

    //Sorteio de Carta 
    private String sorteioCarta(){
        int num;
        String desc = null;
        
        Random gerador = new Random();
        num = gerador.nextInt(5);
        
        switch (gerador.nextInt(5)) {
            case 0:
                desc = "http://rafaelhenrique.net/tarot/tarot1.jpg"; 
                break;
            case 1:
                desc = "http://rafaelhenrique.net/tarot/tarot2.jpg"; 
                break;
            case 2:                
                desc = "http://rafaelhenrique.net/tarot/tarot3.jpg";
                break;
            case 3:
                desc = "http://rafaelhenrique.net/tarot/tarot4.jpg"; 
                break;
            case 4:
                desc = "http://rafaelhenrique.net/tarot/tarot5.jpg"; 
                break;
            default:
        }
        return desc;
    }

    //Criptografia ZENIT-POLAR
    private String criptografaTexto(String texto){
        char[] letras = texto.toCharArray();
        
        char iTextoConvertido [] = new char [letras.length];

        for (int i = 0; i < letras.length; i++) {
            switch (letras[i]) {
                case 'P' :
                    iTextoConvertido[i] = 'Z'; break;
                case 'O' :
                    iTextoConvertido[i] = 'E'; break;
                case 'L' :
                    iTextoConvertido[i] = 'N'; break;
                case 'A' :
                    iTextoConvertido[i] = 'I'; break;
                case 'R' :
                    iTextoConvertido[i] = 'T'; break;
                case 'Z' :
                    iTextoConvertido[i] = 'P'; break;
                case 'E' :
                    iTextoConvertido[i] = 'O'; break;
                case 'N' :
                    iTextoConvertido[i] = 'L'; break;
                case 'I' :
                    iTextoConvertido[i] = 'A'; break;
                case 'T' :
                    iTextoConvertido[i] = 'R'; break;                    
                default:
                    iTextoConvertido[i] = letras[i]; 
            }
        }           
        String nome = new String(iTextoConvertido);
        
        return nome;
    }
    
    //Realiza o sorteio das previsões astral
    private ArrayList soteioPrevisao(int i){
        
        // Gera randomicamente a quantidade de previsões para ser apresentado 
        Random gerador = new Random();
        int f = gerador.nextInt(11);
        
        int qtd = 0, qtdFim = 0;
        ArrayList<String> al = new ArrayList<String>();
        
        try {
            String diretorio = getServletContext().getRealPath("WebContent/WEB-INF/");
            
            // Realiza abertura do arquivo
            FileReader arq = new FileReader("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjetoAula\\web\\outros\\previsoes.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            
            // Realiza a leitura da primeira linha do arquivo
            String linha = lerArq.readLine();

            // a variável "linha" = "null" quando o processo de repetição atingir o final do arquivo
            while (linha != null && qtdFim < f ) {
                if( qtd > i) {
                    al.add(linha);
                    qtdFim += 1;
                }
                qtd += 1;
                linha = lerArq.readLine();
            }
            
            arq.close();
        } catch (IOException e) {
            al.add("Desculpe, isso é muito constragedor. Ocorreu um ERRO na identificações das previsões !!!");
            e.printStackTrace();
        }
        return al;
    }    

    //Recupera previsões cadastradas pelo USUÁRIO
    private ArrayList recuperaPrevisao(String signo){
        
        ArrayList<String> al = new ArrayList<String>();
        
        try {
            //Carrega o driver do MySQL para memória + banco + Transação
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbastral","root", "291220");
            java.sql.Statement transacao = conn.createStatement(); 
            
            // Montagem do comando SQL
            String query = "select conteudoPrevisao from dbastral.previsaoastral where signo_codigoSigno = '" 
                    + recuperaCodigoSigno(signo) + "'";
            
            ResultSet res = transacao.executeQuery(query);         
            
            // Montar tabela resultado da consulta
            while(res.next()) {
                // Recuperação dos atributos do ResultSet, SetBEAN e AddArrayList
                al.add(res.getString("conteudoPrevisao"));
            }

            if (res == null) al.add("Não existem previsões do usuário para o signo!");
            
            // Fecha a transação + conexão
            transacao.close();
            conn.close();

        } catch (Exception e) {
            al.add("Desculpe, isso é muito constragedor. Ocorreu um ERRO na recuperação das previsões do usuário !!!");
            e.printStackTrace();
        }
        return al;
    }    

    //Converte SIGNO em CÓDIGO
    private String recuperaCodigoSigno(String signo) {
        String r = "";
        
        switch (signo) {
            case "Aquário" :
                r = "AQR"; break;
            case "Peisxes" :
                r = "PXS"; break;
            case "Áries" :
                r = "ARS"; break;
            case "Touro" :
                r = "TRO"; break;
            case "Gémeos" :
                r = "GMS"; break;
            case "Câncer" :
                r = "CNC"; break;
            case "Leão" :
                r = "LEA"; break;
            case "Virgem" :
                r = "VRG"; break;
            case "Libra" :
                r = "LBR"; break;
            case "Escopião" :
                r = "ECP"; break;
            case "Sagitário" :
                r = "SGT"; break;
            case "Capricórnio" :
                r = "CPR"; break;
            default: 
        }
        
        return r;
    }

}