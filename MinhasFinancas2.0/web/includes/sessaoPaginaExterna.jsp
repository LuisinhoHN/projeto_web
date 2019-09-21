<%
    /* Verificando se a sessão está ativa (Manda pra dentro ou não?)--------------
        Se estiver ativa joga pra a página principal. Senão fica onde está.
    Nota: O usuário logado não pode ficar na página de cadastro, por exemplo. 
    Pra cadastrar um usuário é necessário estar deslogado, como ocorre com o Facebook, 
    por exemplo. */
    
     
    HttpSession sessao = request.getSession();
    String estaAtivo = (String) session.getAttribute("isActive");

    if (estaAtivo == "verdadeiro"){ // Se o usuário estiver logado
        // Redireciona para a página painel.jsp (página principal)
        response.sendRedirect(request.getContextPath()+ "/pages/painel.jsp");
    }// Senão...não faz nada  
      
%>
