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

public class DaoFactory implements DaoFactoryInterface {
    
    private static final DaoFactory instancia = new DaoFactory();
    
    public DaoFactory(){
        
    }
    
    public static DaoFactory getInstance(){
        return instancia;
    }
    
    @Override
    public UsuarioDao criarUsuarioDao(){
        return new UsuarioDao();
    }
    
    @Override
    public CategoriaDespesaDao criarCategoriaDespesaDao(){
        return new CategoriaDespesaDao();
    }
    
    @Override
    public FormaPagamentoDao criarFormaPagamentoDao(){
        return new FormaPagamentoDao();
    }
    
    @Override
    public MetaDao criarMetaDao(){
        return new MetaDao();
    }
    
    @Override
    public MoedaDao criarMoedaDao(){
        return new MoedaDao();
    }
    
    @Override
    public ReceitaDao criarReceitasDao(){
        return new ReceitaDao();
    }
    
    @Override
    public TipoDespesaDao criarTipoDespesaDao(){
        return new TipoDespesaDao();
    }
    
    @Override
    public TipoReceitaDao criarTipoReceitaDao(){
        return new TipoReceitaDao();
    }
    
    @Override
    public DespesaDao criarDespesasDao(){
        return new DespesaDao();
    }
}
