/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.control;

import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception/*, ParseException*/ {
        response.setContentType("text/html;charset=UTF-8");
        
        Usuario user = new Usuario();
                
        DaoFactory daoInstance = DaoFactory.getInstance();
        Moeda real = daoInstance.criarMoedaDao().find(1);
        
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date Nascimento = new Date();
        Nascimento.setDate(8);
        Nascimento.setMonth(4);
        Nascimento.setYear(1997);
        //Nascimento = format.parse(request.getParameter("Data_nascimento"));
        
        user.setNome(request.getParameter("nome"));
        user.setEmail(request.getParameter("email"));
        user.setSenha(request.getParameter("senha"));
        user.setDataNascimento(Nascimento);
        //user.setSexo(request.getParameter("sexo"));
        user.setPais(request.getParameter("pais"));
        user.setEstado(request.getParameter("estado"));
        user.setCidade(request.getParameter("cidade"));
        
        user.setMoedaId(real);
        user.setIdUsuario(1);


        daoInstance.criarUsuarioDao().create(user);
        
        response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
