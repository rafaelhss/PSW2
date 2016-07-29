<%-- 
    Document   : NovoPortalAstral
    Created on : 24/04/2016, 09:02:36
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
        <div class="grp0">
            <h1 class="titulo">Portal do Astral</h1>
                
            <div class="grp1">
                <img src="imagens/cabecalho.jpg">
            </div>            
        
            <fieldset class="grp4">
                <p class="subtitulo">Resultado apresentado após avaliação dos dados informados</p>
                <p>1. O nome informado foi: ${requestScope.dados.nome} </p>
                <p>2. A sua data de nascimento informada foi: ${requestScope.dados.data} </p>
                <p>3. O seu signo é: ${requestScope.dados.signo} </p>
                <p>4. O seu número da sorte é: ${requestScope.dados.numeroSorte} </p>
                <p>5. O seu nome criptografado é: ${requestScope.dados.nomeCriptografado} </p>
                
                <p class="subtitulo1">Imagem Sorteada</p>
                <p class="grp5"><img type="image" src="${requestScope.dados.enderecoImagem}"></p>
                
                <p class="subtitulo1">Dicas do SISTEMA para o dia !!!</p>
                <c:forEach items="${requestScope.dados.listaPrevisoes}" var="lista">
                    <p> <img alt="" src="imagens/icn1.png"> ${lista} </p>
                </c:forEach>
                    
                <p class="subtitulo1">Dicas do USUÁRIO para o signo !!!</p>
                <c:forEach items="${requestScope.dados.listaPrevisoesUsuario}" var="lista">
                    <p> <img alt="" src="imagens/icn1.png"> ${lista} </p>
                </c:forEach>                    
                
                <br>
                <a href="NovoPortalAstral.html" target="_self">
                    <img src="imagens/voltar3.jpg"> 
                </a>
            </fieldset>
        </div>
    </body>
</html>
