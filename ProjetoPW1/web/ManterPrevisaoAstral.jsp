<%-- 
    Document   : ManterPrevisaoAstral
    Created on : 25/05/2016, 10:16:58
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Utilitario/estilo1.css">
        <script src="Utilitario/biblioteca.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web I</title>
    </head>
    <body>
        <form method="post" accept-charset="UTF-8">
            <div class="grp0">
                <h1 class="titulo">Portal do Astral</h1>
                
                <div class="grp1">
                    <img src="imagens/cabecalho.jpg">
                </div>            
        
                <fieldset class="grp4">
                    <p class="subtitulo">${requestScope.mensagem}</p>
                    
                    <label class="label1">Data da Previsão: </label> 
                    <input type="text" name="sDtPrevForm" onkeypress="formatar('##/##/####', this)" size="40" maxlength="10" placeholder="DD/MM/AAAA">
                    
                    <p><label class="label1">Signo: </label> 
                        <select size="1" name="signoForm">
                            <option selected value=""></option> 
                            
                            <c:forEach items="${sessionScope.lista}" var="item">
                                <option value="${item.codigoSigno}">${item.nomeSigno}</option>
                            </c:forEach>    
                        </select>
                    </p>                                      

                    <p><label class="label1">Previsão: </label> 
                        <textarea rows="5" cols="100" name="sPrevisaoForm" placeholder="Texto da Previsão Astral"></textarea>
                    </p>                   
                    
                    <p>
                       <input class="bts2" type="button" value="Incluir" onClick="this.form.action='IncluirPrevisao'; this.form.submit()">
                       <input class="bts2" type="button" value="Listar" onClick="this.form.action='ListarPrevisao'; this.form.submit()">
                       <button class="bts2" type="reset">Limpar</button>
                    </p>
                    
                    <br>
                    <a href="NovoPortalAstral.html" target="_self">
                        <img src="imagens/voltar3.jpg">
                    </a>
                </fieldset>
            </div>
        </form>
    </body>
</html>
