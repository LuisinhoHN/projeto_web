/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.control;

import MinhasFinancas.Dao.CategoriaDespesaDao;
import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import MinhasFinancas.model.CategoriaDespesa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExcluirCategoriaDespesa extends HttpServlet {

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
            throws ServletException, IOException, NonexistentEntityException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sessao = request.getSession();
        
        DaoFactory daoInstance = DaoFactory.getInstance();
        
        CategoriaDespesaDao categoriaDespesaFinder = daoInstance.criarCategoriaDespesaDao();
        CategoriaDespesaDao categoriaDespesaREMOVER = daoInstance.criarCategoriaDespesaDao();
        
        CategoriaDespesa categoriaDespExcluir = new CategoriaDespesa();
        
        //Exclusão buscando pelo nome (Exclusao indireta)
//        categoriaDespExcluir.setNome(request.getParameter("nomeCategoriaDespesa"));
//        categoriaDespExcluir = categoriaDespesaFinder.BuscaPeloNome(categoriaDespExcluir);
        
        
        //Exclusao pelo id. (Exclusao direta)
        categoriaDespExcluir.setIdCategoriaDespesa(Integer.parseInt(request.getParameter("idCategoriaDespesa")));
        
        
        categoriaDespesaREMOVER.destroy(categoriaDespExcluir.getIdCategoriaDespesa());     
        
        response.sendRedirect(request.getContextPath() + "/pages/visualizar_categoria_despesa.jsp");        
        
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ExcluirCategoriaDespesa.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ExcluirCategoriaDespesa.class.getName()).log(Level.SEVERE, null, ex);
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
