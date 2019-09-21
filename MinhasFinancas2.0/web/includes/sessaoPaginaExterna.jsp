<%
    /* Verificando se a sess�o est� ativa (Manda pra dentro ou n�o?)--------------
        Se estiver ativa joga pra a p�gina principal. Sen�o fica onde est�.
    Nota: O usu�rio logado n�o pode ficar na p�gina de cadastro, por exemplo. 
    Pra cadastrar um usu�rio � necess�rio estar deslogado, como ocorre com o Facebook, 
    por exemplo. */
    
     
    HttpSession sessao = request.getSession();
    String estaAtivo = (String) session.getAttribute("isActive");

    if (estaAtivo == "verdadeiro"){ // Se o usu�rio estiver logado
        // Redireciona para a p�gina painel.jsp (p�gina principal)
        response.sendRedirect(request.getContextPath()+ "/pages/painel.jsp");
    }// Sen�o...n�o faz nada  
      
%>
