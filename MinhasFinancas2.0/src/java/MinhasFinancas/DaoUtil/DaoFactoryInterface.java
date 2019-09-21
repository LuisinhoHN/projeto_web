/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.DaoUtil;

import MinhasFinancas.Dao.CategoriaDespesaDao;
import MinhasFinancas.Dao.DespesaDao;
import MinhasFinancas.Dao.FormaPagamentoDao;
import MinhasFinancas.Dao.MetaDao;
import MinhasFinancas.Dao.MoedaDao;
import MinhasFinancas.Dao.ReceitaDao;
import MinhasFinancas.Dao.TipoDespesaDao;
import MinhasFinancas.Dao.TipoReceitaDao;
import MinhasFinancas.Dao.UsuarioDao;

public interface DaoFactoryInterface {
    
    public UsuarioDao criarUsuarioDao();
    
    public CategoriaDespesaDao criarCategoriaDespesaDao();
    
    public FormaPagamentoDao criarFormaPagamentoDao();
    
    public MetaDao criarMetaDao();
    
    public MoedaDao criarMoedaDao();
    
    public ReceitaDao criarReceitasDao();
    
    public TipoDespesaDao criarTipoDespesaDao();
    
    public TipoReceitaDao criarTipoReceitaDao();
    
    public DespesaDao criarDespesasDao();
    
}
