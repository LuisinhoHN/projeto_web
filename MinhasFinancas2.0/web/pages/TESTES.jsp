<%-- 
    Document   : TESTES
    Created on : 22/12/2016, 10:17:02
    Author     : Usuario
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DaoFactory daoFactory = DaoFactory.getInstance();
    
    int idDoCara = session.getAttribute("idDoCara");
    
    List<Receita> receitas = daoFactory.criarReceitasDao().findReceitas(idDoCara);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
            <thead>
                <tr>
                    <th>Data</th>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th>Moeda</th>
                    <th>Tipo</th>
                    <th>Intervalo</th>
                </tr>
            </thead>
            <tbody>
                <tr class="odd gradeX">
                    <c:forEach items="${receitas}" var="receitas">
                        <td> <c:out value="${receitas.getDataReceita()}"/></td>
                        <td> <c:out value="${receitas.getDescricao()}"/></td>
                        <td> <c:out value="${receitas.getValor()}"/></td>
                        <td> <c:out value="${receitas.getMoedaId()}"/></td>
                        <td> <c:out value="${receitas.getTipoReceitaId()}"/></td>
                        <td> <c:out value="${receitas.getIntervalo()}"/></td>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
    </body>
</html>
