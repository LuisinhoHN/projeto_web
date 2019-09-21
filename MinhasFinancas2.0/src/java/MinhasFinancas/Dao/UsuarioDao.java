/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Dao;

import MinhasFinancas.DaoUtil.DAO;
import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import MinhasFinancas.Util.EntityManagerSingleton;
import MinhasFinancas.model.CategoriaDespesa;
import MinhasFinancas.model.Despesa;
import MinhasFinancas.model.FormaPagamento;
import MinhasFinancas.model.Meta;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.Receita;
import MinhasFinancas.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsuarioDao implements Serializable, DAO<Usuario> {

    public UsuarioDao() {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(Usuario usuario) {
        if (usuario.getFormaPagamentoList() == null) {
            usuario.setFormaPagamentoList(new ArrayList<FormaPagamento>());
        }
        if (usuario.getReceitaList() == null) {
            usuario.setReceitaList(new ArrayList<Receita>());
        }
        if (usuario.getDespesaList() == null) {
            usuario.setDespesaList(new ArrayList<Despesa>());
        }
        if (usuario.getMetaList() == null) {
            usuario.setMetaList(new ArrayList<Meta>());
        }
        if (usuario.getCategoriaDespesaList() == null) {
            usuario.setCategoriaDespesaList(new ArrayList<CategoriaDespesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moeda moedaId = usuario.getMoedaId();
            if (moedaId != null) {
                moedaId = em.getReference(moedaId.getClass(), moedaId.getIdMoeda());
                usuario.setMoedaId(moedaId);
            }
            List<FormaPagamento> attachedFormaPagamentoList = new ArrayList<FormaPagamento>();
            for (FormaPagamento formaPagamentoListFormaPagamentoToAttach : usuario.getFormaPagamentoList()) {
                formaPagamentoListFormaPagamentoToAttach = em.getReference(formaPagamentoListFormaPagamentoToAttach.getClass(), formaPagamentoListFormaPagamentoToAttach.getIdFormaPagamento());
                attachedFormaPagamentoList.add(formaPagamentoListFormaPagamentoToAttach);
            }
            usuario.setFormaPagamentoList(attachedFormaPagamentoList);
            List<Receita> attachedReceitaList = new ArrayList<Receita>();
            for (Receita receitaListReceitaToAttach : usuario.getReceitaList()) {
                receitaListReceitaToAttach = em.getReference(receitaListReceitaToAttach.getClass(), receitaListReceitaToAttach.getIdReceita());
                attachedReceitaList.add(receitaListReceitaToAttach);
            }
            usuario.setReceitaList(attachedReceitaList);
            List<Despesa> attachedDespesaList = new ArrayList<Despesa>();
            for (Despesa despesaListDespesaToAttach : usuario.getDespesaList()) {
                despesaListDespesaToAttach = em.getReference(despesaListDespesaToAttach.getClass(), despesaListDespesaToAttach.getIdDespesa());
                attachedDespesaList.add(despesaListDespesaToAttach);
            }
            usuario.setDespesaList(attachedDespesaList);
            List<Meta> attachedMetaList = new ArrayList<Meta>();
            for (Meta metaListMetaToAttach : usuario.getMetaList()) {
                metaListMetaToAttach = em.getReference(metaListMetaToAttach.getClass(), metaListMetaToAttach.getIdMeta());
                attachedMetaList.add(metaListMetaToAttach);
            }
            usuario.setMetaList(attachedMetaList);
            List<CategoriaDespesa> attachedCategoriaDespesaList = new ArrayList<CategoriaDespesa>();
            for (CategoriaDespesa categoriaDespesaListCategoriaDespesaToAttach : usuario.getCategoriaDespesaList()) {
                categoriaDespesaListCategoriaDespesaToAttach = em.getReference(categoriaDespesaListCategoriaDespesaToAttach.getClass(), categoriaDespesaListCategoriaDespesaToAttach.getIdCategoriaDespesa());
                attachedCategoriaDespesaList.add(categoriaDespesaListCategoriaDespesaToAttach);
            }
            usuario.setCategoriaDespesaList(attachedCategoriaDespesaList);
            em.persist(usuario);
            if (moedaId != null) {
                moedaId.getUsuarioList().add(usuario);
                moedaId = em.merge(moedaId);
            }
            for (FormaPagamento formaPagamentoListFormaPagamento : usuario.getFormaPagamentoList()) {
                Usuario oldUsuarioIdOfFormaPagamentoListFormaPagamento = formaPagamentoListFormaPagamento.getUsuarioId();
                formaPagamentoListFormaPagamento.setUsuarioId(usuario);
                formaPagamentoListFormaPagamento = em.merge(formaPagamentoListFormaPagamento);
                if (oldUsuarioIdOfFormaPagamentoListFormaPagamento != null) {
                    oldUsuarioIdOfFormaPagamentoListFormaPagamento.getFormaPagamentoList().remove(formaPagamentoListFormaPagamento);
                    oldUsuarioIdOfFormaPagamentoListFormaPagamento = em.merge(oldUsuarioIdOfFormaPagamentoListFormaPagamento);
                }
            }
            for (Receita receitaListReceita : usuario.getReceitaList()) {
                Usuario oldUsuarioIdOfReceitaListReceita = receitaListReceita.getUsuarioId();
                receitaListReceita.setUsuarioId(usuario);
                receitaListReceita = em.merge(receitaListReceita);
                if (oldUsuarioIdOfReceitaListReceita != null) {
                    oldUsuarioIdOfReceitaListReceita.getReceitaList().remove(receitaListReceita);
                    oldUsuarioIdOfReceitaListReceita = em.merge(oldUsuarioIdOfReceitaListReceita);
                }
            }
            for (Despesa despesaListDespesa : usuario.getDespesaList()) {
                Usuario oldUsuarioIdOfDespesaListDespesa = despesaListDespesa.getUsuarioId();
                despesaListDespesa.setUsuarioId(usuario);
                despesaListDespesa = em.merge(despesaListDespesa);
                if (oldUsuarioIdOfDespesaListDespesa != null) {
                    oldUsuarioIdOfDespesaListDespesa.getDespesaList().remove(despesaListDespesa);
                    oldUsuarioIdOfDespesaListDespesa = em.merge(oldUsuarioIdOfDespesaListDespesa);
                }
            }
            for (Meta metaListMeta : usuario.getMetaList()) {
                Usuario oldUsuarioIdOfMetaListMeta = metaListMeta.getUsuarioId();
                metaListMeta.setUsuarioId(usuario);
                metaListMeta = em.merge(metaListMeta);
                if (oldUsuarioIdOfMetaListMeta != null) {
                    oldUsuarioIdOfMetaListMeta.getMetaList().remove(metaListMeta);
                    oldUsuarioIdOfMetaListMeta = em.merge(oldUsuarioIdOfMetaListMeta);
                }
            }
            for (CategoriaDespesa categoriaDespesaListCategoriaDespesa : usuario.getCategoriaDespesaList()) {
                Usuario oldUsuarioIdOfCategoriaDespesaListCategoriaDespesa = categoriaDespesaListCategoriaDespesa.getUsuarioId();
                categoriaDespesaListCategoriaDespesa.setUsuarioId(usuario);
                categoriaDespesaListCategoriaDespesa = em.merge(categoriaDespesaListCategoriaDespesa);
                if (oldUsuarioIdOfCategoriaDespesaListCategoriaDespesa != null) {
                    oldUsuarioIdOfCategoriaDespesaListCategoriaDespesa.getCategoriaDespesaList().remove(categoriaDespesaListCategoriaDespesa);
                    oldUsuarioIdOfCategoriaDespesaListCategoriaDespesa = em.merge(oldUsuarioIdOfCategoriaDespesaListCategoriaDespesa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Moeda moedaIdOld = persistentUsuario.getMoedaId();
            Moeda moedaIdNew = usuario.getMoedaId();
            List<FormaPagamento> formaPagamentoListOld = persistentUsuario.getFormaPagamentoList();
            List<FormaPagamento> formaPagamentoListNew = usuario.getFormaPagamentoList();
            List<Receita> receitaListOld = persistentUsuario.getReceitaList();
            List<Receita> receitaListNew = usuario.getReceitaList();
            List<Despesa> despesaListOld = persistentUsuario.getDespesaList();
            List<Despesa> despesaListNew = usuario.getDespesaList();
            List<Meta> metaListOld = persistentUsuario.getMetaList();
            List<Meta> metaListNew = usuario.getMetaList();
            List<CategoriaDespesa> categoriaDespesaListOld = persistentUsuario.getCategoriaDespesaList();
            List<CategoriaDespesa> categoriaDespesaListNew = usuario.getCategoriaDespesaList();
            if (moedaIdNew != null) {
                moedaIdNew = em.getReference(moedaIdNew.getClass(), moedaIdNew.getIdMoeda());
                usuario.setMoedaId(moedaIdNew);
            }
            List<FormaPagamento> attachedFormaPagamentoListNew = new ArrayList<FormaPagamento>();
            for (FormaPagamento formaPagamentoListNewFormaPagamentoToAttach : formaPagamentoListNew) {
                formaPagamentoListNewFormaPagamentoToAttach = em.getReference(formaPagamentoListNewFormaPagamentoToAttach.getClass(), formaPagamentoListNewFormaPagamentoToAttach.getIdFormaPagamento());
                attachedFormaPagamentoListNew.add(formaPagamentoListNewFormaPagamentoToAttach);
            }
            formaPagamentoListNew = attachedFormaPagamentoListNew;
            usuario.setFormaPagamentoList(formaPagamentoListNew);
            List<Receita> attachedReceitaListNew = new ArrayList<Receita>();
            for (Receita receitaListNewReceitaToAttach : receitaListNew) {
                receitaListNewReceitaToAttach = em.getReference(receitaListNewReceitaToAttach.getClass(), receitaListNewReceitaToAttach.getIdReceita());
                attachedReceitaListNew.add(receitaListNewReceitaToAttach);
            }
            receitaListNew = attachedReceitaListNew;
            usuario.setReceitaList(receitaListNew);
            List<Despesa> attachedDespesaListNew = new ArrayList<Despesa>();
            for (Despesa despesaListNewDespesaToAttach : despesaListNew) {
                despesaListNewDespesaToAttach = em.getReference(despesaListNewDespesaToAttach.getClass(), despesaListNewDespesaToAttach.getIdDespesa());
                attachedDespesaListNew.add(despesaListNewDespesaToAttach);
            }
            despesaListNew = attachedDespesaListNew;
            usuario.setDespesaList(despesaListNew);
            List<Meta> attachedMetaListNew = new ArrayList<Meta>();
            for (Meta metaListNewMetaToAttach : metaListNew) {
                metaListNewMetaToAttach = em.getReference(metaListNewMetaToAttach.getClass(), metaListNewMetaToAttach.getIdMeta());
                attachedMetaListNew.add(metaListNewMetaToAttach);
            }
            metaListNew = attachedMetaListNew;
            usuario.setMetaList(metaListNew);
            List<CategoriaDespesa> attachedCategoriaDespesaListNew = new ArrayList<CategoriaDespesa>();
            for (CategoriaDespesa categoriaDespesaListNewCategoriaDespesaToAttach : categoriaDespesaListNew) {
                categoriaDespesaListNewCategoriaDespesaToAttach = em.getReference(categoriaDespesaListNewCategoriaDespesaToAttach.getClass(), categoriaDespesaListNewCategoriaDespesaToAttach.getIdCategoriaDespesa());
                attachedCategoriaDespesaListNew.add(categoriaDespesaListNewCategoriaDespesaToAttach);
            }
            categoriaDespesaListNew = attachedCategoriaDespesaListNew;
            usuario.setCategoriaDespesaList(categoriaDespesaListNew);
            usuario = em.merge(usuario);
            if (moedaIdOld != null && !moedaIdOld.equals(moedaIdNew)) {
                moedaIdOld.getUsuarioList().remove(usuario);
                moedaIdOld = em.merge(moedaIdOld);
            }
            if (moedaIdNew != null && !moedaIdNew.equals(moedaIdOld)) {
                moedaIdNew.getUsuarioList().add(usuario);
                moedaIdNew = em.merge(moedaIdNew);
            }
            for (FormaPagamento formaPagamentoListOldFormaPagamento : formaPagamentoListOld) {
                if (!formaPagamentoListNew.contains(formaPagamentoListOldFormaPagamento)) {
                    formaPagamentoListOldFormaPagamento.setUsuarioId(null);
                    formaPagamentoListOldFormaPagamento = em.merge(formaPagamentoListOldFormaPagamento);
                }
            }
            for (FormaPagamento formaPagamentoListNewFormaPagamento : formaPagamentoListNew) {
                if (!formaPagamentoListOld.contains(formaPagamentoListNewFormaPagamento)) {
                    Usuario oldUsuarioIdOfFormaPagamentoListNewFormaPagamento = formaPagamentoListNewFormaPagamento.getUsuarioId();
                    formaPagamentoListNewFormaPagamento.setUsuarioId(usuario);
                    formaPagamentoListNewFormaPagamento = em.merge(formaPagamentoListNewFormaPagamento);
                    if (oldUsuarioIdOfFormaPagamentoListNewFormaPagamento != null && !oldUsuarioIdOfFormaPagamentoListNewFormaPagamento.equals(usuario)) {
                        oldUsuarioIdOfFormaPagamentoListNewFormaPagamento.getFormaPagamentoList().remove(formaPagamentoListNewFormaPagamento);
                        oldUsuarioIdOfFormaPagamentoListNewFormaPagamento = em.merge(oldUsuarioIdOfFormaPagamentoListNewFormaPagamento);
                    }
                }
            }
            for (Receita receitaListOldReceita : receitaListOld) {
                if (!receitaListNew.contains(receitaListOldReceita)) {
                    receitaListOldReceita.setUsuarioId(null);
                    receitaListOldReceita = em.merge(receitaListOldReceita);
                }
            }
            for (Receita receitaListNewReceita : receitaListNew) {
                if (!receitaListOld.contains(receitaListNewReceita)) {
                    Usuario oldUsuarioIdOfReceitaListNewReceita = receitaListNewReceita.getUsuarioId();
                    receitaListNewReceita.setUsuarioId(usuario);
                    receitaListNewReceita = em.merge(receitaListNewReceita);
                    if (oldUsuarioIdOfReceitaListNewReceita != null && !oldUsuarioIdOfReceitaListNewReceita.equals(usuario)) {
                        oldUsuarioIdOfReceitaListNewReceita.getReceitaList().remove(receitaListNewReceita);
                        oldUsuarioIdOfReceitaListNewReceita = em.merge(oldUsuarioIdOfReceitaListNewReceita);
                    }
                }
            }
            for (Despesa despesaListOldDespesa : despesaListOld) {
                if (!despesaListNew.contains(despesaListOldDespesa)) {
                    despesaListOldDespesa.setUsuarioId(null);
                    despesaListOldDespesa = em.merge(despesaListOldDespesa);
                }
            }
            for (Despesa despesaListNewDespesa : despesaListNew) {
                if (!despesaListOld.contains(despesaListNewDespesa)) {
                    Usuario oldUsuarioIdOfDespesaListNewDespesa = despesaListNewDespesa.getUsuarioId();
                    despesaListNewDespesa.setUsuarioId(usuario);
                    despesaListNewDespesa = em.merge(despesaListNewDespesa);
                    if (oldUsuarioIdOfDespesaListNewDespesa != null && !oldUsuarioIdOfDespesaListNewDespesa.equals(usuario)) {
                        oldUsuarioIdOfDespesaListNewDespesa.getDespesaList().remove(despesaListNewDespesa);
                        oldUsuarioIdOfDespesaListNewDespesa = em.merge(oldUsuarioIdOfDespesaListNewDespesa);
                    }
                }
            }
            for (Meta metaListOldMeta : metaListOld) {
                if (!metaListNew.contains(metaListOldMeta)) {
                    metaListOldMeta.setUsuarioId(null);
                    metaListOldMeta = em.merge(metaListOldMeta);
                }
            }
            for (Meta metaListNewMeta : metaListNew) {
                if (!metaListOld.contains(metaListNewMeta)) {
                    Usuario oldUsuarioIdOfMetaListNewMeta = metaListNewMeta.getUsuarioId();
                    metaListNewMeta.setUsuarioId(usuario);
                    metaListNewMeta = em.merge(metaListNewMeta);
                    if (oldUsuarioIdOfMetaListNewMeta != null && !oldUsuarioIdOfMetaListNewMeta.equals(usuario)) {
                        oldUsuarioIdOfMetaListNewMeta.getMetaList().remove(metaListNewMeta);
                        oldUsuarioIdOfMetaListNewMeta = em.merge(oldUsuarioIdOfMetaListNewMeta);
                    }
                }
            }
            for (CategoriaDespesa categoriaDespesaListOldCategoriaDespesa : categoriaDespesaListOld) {
                if (!categoriaDespesaListNew.contains(categoriaDespesaListOldCategoriaDespesa)) {
                    categoriaDespesaListOldCategoriaDespesa.setUsuarioId(null);
                    categoriaDespesaListOldCategoriaDespesa = em.merge(categoriaDespesaListOldCategoriaDespesa);
                }
            }
            for (CategoriaDespesa categoriaDespesaListNewCategoriaDespesa : categoriaDespesaListNew) {
                if (!categoriaDespesaListOld.contains(categoriaDespesaListNewCategoriaDespesa)) {
                    Usuario oldUsuarioIdOfCategoriaDespesaListNewCategoriaDespesa = categoriaDespesaListNewCategoriaDespesa.getUsuarioId();
                    categoriaDespesaListNewCategoriaDespesa.setUsuarioId(usuario);
                    categoriaDespesaListNewCategoriaDespesa = em.merge(categoriaDespesaListNewCategoriaDespesa);
                    if (oldUsuarioIdOfCategoriaDespesaListNewCategoriaDespesa != null && !oldUsuarioIdOfCategoriaDespesaListNewCategoriaDespesa.equals(usuario)) {
                        oldUsuarioIdOfCategoriaDespesaListNewCategoriaDespesa.getCategoriaDespesaList().remove(categoriaDespesaListNewCategoriaDespesa);
                        oldUsuarioIdOfCategoriaDespesaListNewCategoriaDespesa = em.merge(oldUsuarioIdOfCategoriaDespesaListNewCategoriaDespesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Moeda moedaId = usuario.getMoedaId();
            if (moedaId != null) {
                moedaId.getUsuarioList().remove(usuario);
                moedaId = em.merge(moedaId);
            }
            List<FormaPagamento> formaPagamentoList = usuario.getFormaPagamentoList();
            for (FormaPagamento formaPagamentoListFormaPagamento : formaPagamentoList) {
                formaPagamentoListFormaPagamento.setUsuarioId(null);
                formaPagamentoListFormaPagamento = em.merge(formaPagamentoListFormaPagamento);
            }
            List<Receita> receitaList = usuario.getReceitaList();
            for (Receita receitaListReceita : receitaList) {
                receitaListReceita.setUsuarioId(null);
                receitaListReceita = em.merge(receitaListReceita);
            }
            List<Despesa> despesaList = usuario.getDespesaList();
            for (Despesa despesaListDespesa : despesaList) {
                despesaListDespesa.setUsuarioId(null);
                despesaListDespesa = em.merge(despesaListDespesa);
            }
            List<Meta> metaList = usuario.getMetaList();
            for (Meta metaListMeta : metaList) {
                metaListMeta.setUsuarioId(null);
                metaListMeta = em.merge(metaListMeta);
            }
            List<CategoriaDespesa> categoriaDespesaList = usuario.getCategoriaDespesaList();
            for (CategoriaDespesa categoriaDespesaListCategoriaDespesa : categoriaDespesaList) {
                categoriaDespesaListCategoriaDespesa.setUsuarioId(null);
                categoriaDespesaListCategoriaDespesa = em.merge(categoriaDespesaListCategoriaDespesa);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<Usuario> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<Usuario> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<Usuario> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            //em.close();
        }
    }

    @Override
    public Usuario find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    
    public Usuario verificaLogin(Usuario usuario){
        Usuario usuarioEncontrado;
        EntityManager em = getEntityManager();
        
        String hql = "from Usuario u where u.nome = '" + usuario.getNome() + "' and u.senha = '" + usuario.getSenha() + "' ";
        Query query = em.createQuery(hql);
        
        usuarioEncontrado = (Usuario) query.getSingleResult();
        
        if(usuarioEncontrado != null){
            return usuarioEncontrado;
        } 
        
        return usuarioEncontrado;
    }
    
    public Usuario BuscaPeloNome(Usuario usuario){
        
        Usuario usuarioEncontrado;
        EntityManager em = getEntityManager();
        
        String hql = "from Usuario u where u.nome = '" + usuario.getNome()+"' ";
        Query query = em.createQuery(hql);
        
        usuarioEncontrado = (Usuario) query.getSingleResult();
        
        if(usuarioEncontrado != null){
            return usuarioEncontrado;
        } 
        
        return usuarioEncontrado;
    }   
    
}
