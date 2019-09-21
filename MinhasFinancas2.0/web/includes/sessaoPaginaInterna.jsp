<% 
     /*Verificando se a sessão está ativa (Manda pra fora ou não?) ------------------------------------
        Se estiver ativa, permanece na página atual. Senão, redireciona para página principal */
    
    HttpSession sessao = request.getSession();
    String estaAtivo = (String) session.getAttribute("isActive");
    String nomeDoCara = (String) session.getAttribute("nomeDoCara"); // Apenas para mostrar no topo da página

    if (estaAtivo == "verdadeiro"){ // Se a sessão estiver ativa
        // Não faz nada :)
    }else{ // Se estiver desativada
        // Manda para a página inicial
        response.sendRedirect(request.getContextPath()+ "/pages/index.jsp");
    } 

%>
