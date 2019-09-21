/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.control;

import MinhasFinancas.Dao.MoedaDao;
import MinhasFinancas.Dao.ReceitaDao;
import MinhasFinancas.Dao.TipoReceitaDao;
import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.Receita;
import MinhasFinancas.model.TipoReceita;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizarReceitaServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        String dataCompraForm = request.getParameter("dataCompra");
        String descricaoReceita = request.getParameter("descricaoReceita");
        String intervalo = request.getParameter("intervalo");
        BigInteger preco = BigInteger.valueOf(Long.parseLong(request.getParameter("preco")));
        String moedaReceita = request.getParameter("moedaReceita");
        String tipo = request.getParameter("tipo");
        int idReceita = (int) session.getAttribute("idReceita");
        
        ReceitaDao receitaDao = DaoFactory.getInstance().criarReceitasDao();
        MoedaDao moedaDao = DaoFactory.getInstance().criarMoedaDao();
        TipoReceitaDao tipoReceitaDao = DaoFactory.getInstance().criarTipoReceitaDao();
        
        Moeda moeda = new Moeda();
        moeda.setNome(moedaReceita);
        moeda = moedaDao.BuscarPeloNome(moeda);
        
        TipoReceita tipoReceita = new TipoReceita();
        tipoReceita.setNome(tipo);
        tipoReceita = tipoReceitaDao.BuscaPeloNome(tipoReceita);
        
        Date dataCompra = new Date(0);
        dataCompra = Date.valueOf(dataCompraForm);
        
        Receita receita = receitaDao.find(idReceita);
        receita.setDataReceita(dataCompra);
        receita.setDescricao(descricaoReceita);
        receita.setIntervalo(intervalo);
        receita.setMoedaId(moeda);
        receita.setTipoReceitaId(tipoReceita);
        receita.setValor(preco);
        
        try {
            receitaDao.edit(receita);
        } catch (Exception ex) {
            Logger.getLogger(AtualizarReceitaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath()  + "/pages/visualizar_receita.jsp");
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
