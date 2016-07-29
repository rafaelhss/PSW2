<%-- 
    Document   : ListarPrevisao
    Created on : 28/05/2016, 21:22:26
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
                    
                    <table bordercolor="grey" border=1 style="table-layout: fixed; border-collapse: collapse; width: 920px">
                        <tr bgcolor="orange"><td width="5%" align="center">#</td>
                            <td width="10%" align="center">Signo</td>
                            <td width="85%" align="center">Texto da Previsão</td>
                        </tr>
                        <c:forEach items="${requestScope.lista}" var="item">
                            <tr><td width="5%" align="center"><input type="checkbox" name="itensPrevisao" value="${item.idPrevisao}"/></td>
                                <td width="10%">${item.codigoSigno}</td>
                                <td width="85%">${item.conteudoPrevisao}</td>
                            </tr>
                        </c:forEach> 
                    </table>

                    </p>                                      
                 
                    <p><input class="bts2" type="button" value="Excluir"onClick="javascript:this.form.action='ExcluirPrevisao'; this.form.submit()">
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

