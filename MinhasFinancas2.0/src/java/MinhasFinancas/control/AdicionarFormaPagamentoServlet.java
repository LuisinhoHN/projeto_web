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

public class AdicionarFormaPagamentoServlet extends HttpServlet {

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

        FormaPagamentoDao formaPagamentoADD = daoInstance.criarFormaPagamentoDao();
        //CategoriaDespesaDao categoriaDespesaADD = daoInstance.criarCategoriaDespesaDao();

        UsuarioDao finderUsuario = daoInstance.criarUsuarioDao();

        FormaPagamento formaPagamento = new FormaPagamento(); 
        //CategoriaDespesa categoriaDesp = new CategoriaDespesa();
        Usuario usuarioId = new Usuario();

        usuarioId.setNome((String) sessao.getAttribute("nomeDoCara"));
        usuarioId = finderUsuario.BuscaPeloNome(usuarioId);

        formaPagamento.setNome( request.getParameter("nomeFormaPagamento"));
        formaPagamento.setDescricao( request.getParameter("descricaoFormaPagamento"));
        formaPagamento.setUsuarioId(usuarioId);
        
        //categoriaDesp.setDescricao(request.getParameter("descricaoCategoriaDespesa"));
        //categoriaDesp.setNome("nomeCategoriaDespesa");
        //categoriaDesp.setUsuarioId(usuarioId);

        formaPagamentoADD.create(formaPagamento);
        //categoriaDespesaADD.create(categoriaDesp);

        response.sendRedirect(request.getContextPath() + "/pages/visualizar_forma_pagamento.jsp");
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
