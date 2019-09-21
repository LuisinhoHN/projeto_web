<% 
     /*Verificando se a sess�o est� ativa (Manda pra fora ou n�o?) ------------------------------------
        Se estiver ativa, permanece na p�gina atual. Sen�o, redireciona para p�gina principal */
    
    HttpSession sessao = request.getSession();
    String estaAtivo = (String) session.getAttribute("isActive");
    String nomeDoCara = (String) session.getAttribute("nomeDoCara"); // Apenas para mostrar no topo da p�gina

    if (estaAtivo == "verdadeiro"){ // Se a sess�o estiver ativa
        // N�o faz nada :)
    }else{ // Se estiver desativada
        // Manda para a p�gina inicial
        response.sendRedirect(request.getContextPath()+ "/pages/index.jsp");
    } 

%>
