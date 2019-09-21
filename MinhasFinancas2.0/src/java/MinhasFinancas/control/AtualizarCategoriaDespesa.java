/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.control;

import MinhasFinancas.Dao.CategoriaDespesaDao;
import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.model.CategoriaDespesa;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizarCategoriaDespesa extends HttpServlet {

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
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        
        String nomeCategoriaDespesa = request.getParameter("nomeCategoriaDespesa");
        String descricaoCategoriaDespesa = request.getParameter("descricaoCategoriaDespesa");
        
        int idCategoriaDespesa = (int) session.getAttribute("idCategoriaDespesa");
        
        CategoriaDespesaDao categoriaDespesaDao = DaoFactory.getInstance().criarCategoriaDespesaDao();
        
        CategoriaDespesa categoriaDespesa = categoriaDespesaDao.find(idCategoriaDespesa);
        categoriaDespesa.setDescricao(descricaoCategoriaDespesa);
        categoriaDespesa.setNome(nomeCategoriaDespesa);
        
        try {
            categoriaDespesaDao.edit(categoriaDespesa);
        } catch (Exception ex) {
            Logger.getLogger(AtualizarReceitaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath()  + "/pages/visualizar_categoria_despesa.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
