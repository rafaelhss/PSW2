<%-- 
    Document   : vendas
    Created on : 03/11/2016, 22:19:45
    Author     : Rafael.Soares
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Vendas:</h1>
            <table>
                <tr>
                    <td>Produto</td>
                    <td>Quantidade</td>
                    <td>Preco</td>
                </tr>
                <c:forEach items="${requestScope.vendas}" var="venda">
                    <tr>
                        <td>${venda.produto}</td>
                        <td>${venda.quantidade}</td>
                        <td>${venda.preco}</td>
                    </tr>
                </c:forEach>
            </table>
          <a href="index.html">Voltar</a>
    </body>
</html>
