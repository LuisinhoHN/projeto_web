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
import MinhasFinancas.Dao.UsuarioDao;
import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.model.CategoriaDespesa;
import MinhasFinancas.model.Despesa;
import MinhasFinancas.model.FormaPagamento;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.TipoDespesa;
import MinhasFinancas.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdicionarDespesaServlet extends HttpServlet {

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

        HttpSession sessao = request.getSession();
        
        DaoFactory daoInstance = DaoFactory.getInstance();
        
        FormaPagamentoDao finderFormaPagamento = daoInstance.criarFormaPagamentoDao();
        UsuarioDao finderUsuario = daoInstance.criarUsuarioDao();
        TipoDespesaDao finderTipoDespesa = daoInstance.criarTipoDespesaDao();
        CategoriaDespesaDao finderCategoriaDespesa = daoInstance.criarCategoriaDespesaDao();
        MoedaDao finderMoeda = daoInstance.criarMoedaDao();
        DespesaDao novaDespesa = daoInstance.criarDespesasDao();
        Despesa despesaADD = new Despesa();

        Moeda moedaId = new Moeda();
        moedaId.setNome(request.getParameter("moedaDespesa"));
        moedaId = finderMoeda.BuscarPeloNome(moedaId);

        TipoDespesa tipoDespesaId = new TipoDespesa();
        tipoDespesaId.setNome(request.getParameter("tipo"));
        tipoDespesaId = finderTipoDespesa.BuscaPeloNome(tipoDespesaId);

        Usuario usuarioId = new Usuario();
        usuarioId.setNome((String) sessao.getAttribute("nomeDoCara"));
        usuarioId = finderUsuario.BuscaPeloNome(usuarioId);

        CategoriaDespesa categoriaDespesaId = new CategoriaDespesa();
        categoriaDespesaId.setNome(request.getParameter("categoriaDespesa"));
        //categoriaDespesaId.setUsuarioId(usuarioId);
        categoriaDespesaId = finderCategoriaDespesa.BuscaPeloNome(categoriaDespesaId);

        FormaPagamento formaPagamentoId = new FormaPagamento();
        formaPagamentoId.setNome(request.getParameter("formaPagamentoDespesa"));
        formaPagamentoId = finderFormaPagamento.BuscaPeloNome(formaPagamentoId);

        Date dataEfetuacao = new Date(0);
        String descricao = request.getParameter("descricaoDespesa");
        //Converte valor String para date.
        dataEfetuacao = Date.valueOf(request.getParameter("dataCompra"));
        //Data para teste

        despesaADD.setUsuarioId(usuarioId);
        despesaADD.setDataDespesa(dataEfetuacao);
        despesaADD.setDescricao(request.getParameter("descricaoDespesa"));
        despesaADD.setValor(BigInteger.valueOf(Long.parseLong(request.getParameter("precoDespesa"))));
        despesaADD.setMoedaId(moedaId);
        despesaADD.setCategoriaDespesaId(categoriaDespesaId);
        despesaADD.setTipoDespesaId(tipoDespesaId);
        despesaADD.setFormaPagamentoId(formaPagamentoId);

        if (tipoDespesaId.getNome() == "Temporária") {
            despesaADD.setIntervalo(request.getParameter("intervaloDespesa"));
        }

        novaDespesa.create(despesaADD);

        response.sendRedirect(request.getContextPath() + "/pages/visualizar_despesa.jsp");

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
