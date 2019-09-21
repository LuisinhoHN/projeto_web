/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.control;

import MinhasFinancas.Dao.MoedaDao;
import MinhasFinancas.Dao.ReceitaDao;
import MinhasFinancas.Dao.TipoReceitaDao;
import MinhasFinancas.Dao.UsuarioDao;
import MinhasFinancas.DaoUtil.DaoFactory;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.Receita;
import MinhasFinancas.model.TipoReceita;
import MinhasFinancas.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdicionarReceitaServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sessao = request.getSession();
        
        DaoFactory daoInstance = DaoFactory.getInstance();
        
        MoedaDao finderMoeda = daoInstance.criarMoedaDao();
        TipoReceitaDao finderTipoReceita = daoInstance.criarTipoReceitaDao();
        ReceitaDao novaReceita = daoInstance.criarReceitasDao();
        UsuarioDao finderUsuario = daoInstance.criarUsuarioDao();

        Receita receitaADD = new Receita();
        //Date dataEfetuacao = new Date(0, 0, 0);
        Date dataEfetuacao = new Date(0);
        
        //String dataPagamento = request.getParameter("dataPagamento");
        //Converte valor String para date.
        //dataEfetuacao = Date.valueOf(dataPagamento);
        dataEfetuacao = Date.valueOf(request.getParameter("dataReceita"));
        
        //Encontra o objeto Moeda especificado.
        //Neste caso apenas a moeda real esta implementada logo o valor de seu
        //id é 1.
        Usuario usuarioId = new Usuario();
        usuarioId.setNome((String) sessao.getAttribute("nomeDoCara"));
        usuarioId = finderUsuario.BuscaPeloNome(usuarioId);
        
        Moeda moedaId = new Moeda();
        moedaId.setNome(request.getParameter("moedaReceita"));
        moedaId = finderMoeda.BuscarPeloNome(moedaId);

        TipoReceita tipoReceitaId = new TipoReceita();
        tipoReceitaId.setNome(request.getParameter("tipo"));
        tipoReceitaId = finderTipoReceita.BuscaPeloNome(tipoReceitaId);

        receitaADD.setDataReceita(dataEfetuacao);
        receitaADD.setDescricao(request.getParameter("descricaoReceita"));
        receitaADD.setMoedaId(moedaId);
        receitaADD.setValor(BigInteger.valueOf(Long.parseLong(request.getParameter("preco"))));
        receitaADD.setTipoReceitaId(tipoReceitaId);
        receitaADD.setIntervalo(request.getParameter("intervalo"));

        receitaADD.setUsuarioId(usuarioId);
        novaReceita.create(receitaADD);
        response.sendRedirect(request.getContextPath() + "/pages/visualizar_receita.jsp");

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
        } catch (ParseException ex) {
            Logger.getLogger(AdicionarReceitaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(AdicionarReceitaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
