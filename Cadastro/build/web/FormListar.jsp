<%-- 
    Document   : FormListar
    Created on : 03/11/2016, 22:26:45
    Author     : Rafael.Soares
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pesquisar vendas</h1>
        <form action="Listar">
            Filtro Produto (opcional): <input type="text" name="produto" value="${requestScope.ultimoFiltro}"> <br/>
        <input type="submit">
        </form>
            
    </body>
</html>
