/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.control;

import MinhasFinancas.Dao.CategoriaDespesaDao;
import MinhasFinancas.Dao.DespesaDao;
import MinhasFinancas.Dao.FormaPagamentoDao;
import MinhasFinancas.Dao.MoedaDao;
import MinhasFinancas.Dao.TipoDespesaDao;
import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.model.CategoriaDespesa;
import MinhasFinancas.model.Despesa;
import MinhasFinancas.model.FormaPagamento;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.TipoDespesa;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizarDespesa extends HttpServlet {

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
        String descricaoFormaPagamento = request.getParameter("descricaoDespesa");
        BigInteger precoDespesa = BigInteger.valueOf(Long.parseLong(request.getParameter("precoDespesa")));
        String moedaDespesa = request.getParameter("moedaDespesa");
        String categoriaDespesaForm = request.getParameter("categoriaDespesa");
        String formaPagamentoDespesa = request.getParameter("formaPagamentoDespesa");
        String tipo = request.getParameter("tipo");
        String intervaloDespesa = request.getParameter("intervaloDespesa");
        
        int idDespesa = (int) session.getAttribute("idDespesa");
        
        DespesaDao despesaDao = DaoFactory.getInstance().criarDespesasDao();
        MoedaDao moedaDao = DaoFactory.getInstance().criarMoedaDao();
        CategoriaDespesaDao categoriaDespesaDao = DaoFactory.getInstance().criarCategoriaDespesaDao();
        FormaPagamentoDao formaPagamentoDao = DaoFactory.getInstance().criarFormaPagamentoDao();
        TipoDespesaDao tipoDespesaDao = DaoFactory.getInstance().criarTipoDespesaDao();
        
        Moeda moeda = new Moeda();
        moeda.setNome(moedaDespesa);
        moeda = moedaDao.BuscarPeloNome(moeda);
        
        CategoriaDespesa categoriaDespesa = new CategoriaDespesa();
        categoriaDespesa.setNome(categoriaDespesaForm);
        categoriaDespesa = categoriaDespesaDao.BuscaPeloNome(categoriaDespesa);
        
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setNome(formaPagamentoDespesa);
        formaPagamento = formaPagamentoDao.BuscaPeloNome(formaPagamento);
        
        TipoDespesa tipoDespesa = new TipoDespesa();
        tipoDespesa.setNome(tipo);
        tipoDespesa = tipoDespesaDao.BuscaPeloNome(tipoDespesa);
        
        Date dataCompra = new Date(0);
        dataCompra = Date.valueOf(dataCompraForm);
        
        Despesa despesa = despesaDao.find(idDespesa);
        despesa.setDataDespesa(dataCompra);
        despesa.setDescricao(descricaoFormaPagamento);
        despesa.setValor(precoDespesa);
        despesa.setMoedaId(moeda);
        despesa.setCategoriaDespesaId(categoriaDespesa);
        despesa.setFormaPagamentoId(formaPagamento);
        despesa.setTipoDespesaId(tipoDespesa);
        despesa.setIntervalo(intervaloDespesa);
        
        try {
            despesaDao.edit(despesa);
        } catch (Exception ex) {
            Logger.getLogger(AtualizarReceitaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath()  + "/pages/visualizar_despesa.jsp");
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
