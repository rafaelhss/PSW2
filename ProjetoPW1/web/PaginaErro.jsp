<%-- 
    Document   : PaginaErro
    Created on : 26/05/2016, 19:43:06
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Web I</title>
        <link rel="stylesheet" type="text/css" href="Utilitario/estilo1.css">
        <script src="Utilitario/biblioteca.js"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="grp0">
            <h1 class="titulo">Portal do Astral</h1>
                
            <div class="grp1">
                <img alt="" src="imagens/cabecalho.jpg">
            </div>
            
            <fieldset class="grp4">
                <p class="subtitulo">Desculpe! Isso realmente é constrangedor.</p>
                        
                <p>ERRO: ${requestScope.erro} </p> <br>

                <a href="NovoPortalAstral.html" target="_self">
                    <img src="imagens/voltar3.jpg">
                </a>
            </fieldset>
        </div>
    </body>
</html> 