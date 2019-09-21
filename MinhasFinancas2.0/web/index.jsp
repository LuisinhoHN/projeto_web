<%-- 
    Document   : index
    Created on : 20/10/2016, 00:49:06
    Author     : Allan Santos
--%>
<%-- 
    Nota: Nessa página não é necessária fazer verificação de sessão, pois ela redireciona para 
    pages/index.jsp, que por sua vez, faz essa verificação (Allan, 28-10)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="0;url=pages/index.jsp">
        <title>Minhas Finanças</title>
        <script language="javascript">
            window.location.href = "pages/index.jsp";
        </script>
    </head>
    <body>
        Go to <a href="pages/index.jsp">/pages/index.jsp</a>
    </body>
</html>
