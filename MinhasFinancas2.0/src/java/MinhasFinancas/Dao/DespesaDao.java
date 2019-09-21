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
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.TipoDespesa;
import MinhasFinancas.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DespesaDao implements Serializable, DAO<Despesa> {

    public DespesaDao() {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(Despesa despesa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaDespesa categoriaDespesaId = despesa.getCategoriaDespesaId();
            if (categoriaDespesaId != null) {
                categoriaDespesaId = em.getReference(categoriaDespesaId.getClass(), categoriaDespesaId.getIdCategoriaDespesa());
                despesa.setCategoriaDespesaId(categoriaDespesaId);
            }
            FormaPagamento formaPagamentoId = despesa.getFormaPagamentoId();
            if (formaPagamentoId != null) {
                formaPagamentoId = em.getReference(formaPagamentoId.getClass(), formaPagamentoId.getIdFormaPagamento());
                despesa.setFormaPagamentoId(formaPagamentoId);
            }
            Moeda moedaId = despesa.getMoedaId();
            if (moedaId != null) {
                moedaId = em.getReference(moedaId.getClass(), moedaId.getIdMoeda());
                despesa.setMoedaId(moedaId);
            }
            TipoDespesa tipoDespesaId = despesa.getTipoDespesaId();
            if (tipoDespesaId != null) {
                tipoDespesaId = em.getReference(tipoDespesaId.getClass(), tipoDespesaId.getIdTipoDespesa());
                despesa.setTipoDespesaId(tipoDespesaId);
            }
            Usuario usuarioId = despesa.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                despesa.setUsuarioId(usuarioId);
            }
            em.persist(despesa);
            if (categoriaDespesaId != null) {
                categoriaDespesaId.getDespesaList().add(despesa);
                categoriaDespesaId = em.merge(categoriaDespesaId);
            }
            if (formaPagamentoId != null) {
                formaPagamentoId.getDespesaList().add(despesa);
                formaPagamentoId = em.merge(formaPagamentoId);
            }
            if (moedaId != null) {
                moedaId.getDespesaList().add(despesa);
                moedaId = em.merge(moedaId);
            }
            if (tipoDespesaId != null) {
                tipoDespesaId.getDespesaList().add(despesa);
                tipoDespesaId = em.merge(tipoDespesaId);
            }
            if (usuarioId != null) {
                usuarioId.getDespesaList().add(despesa);
                usuarioId = em.merge(usuarioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public void edit(Despesa despesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Despesa persistentDespesa = em.find(Despesa.class, despesa.getIdDespesa());
            CategoriaDespesa categoriaDespesaIdOld = persistentDespesa.getCategoriaDespesaId();
            CategoriaDespesa categoriaDespesaIdNew = despesa.getCategoriaDespesaId();
            FormaPagamento formaPagamentoIdOld = persistentDespesa.getFormaPagamentoId();
            FormaPagamento formaPagamentoIdNew = despesa.getFormaPagamentoId();
            Moeda moedaIdOld = persistentDespesa.getMoedaId();
            Moeda moedaIdNew = despesa.getMoedaId();
            TipoDespesa tipoDespesaIdOld = persistentDespesa.getTipoDespesaId();
            TipoDespesa tipoDespesaIdNew = despesa.getTipoDespesaId();
            Usuario usuarioIdOld = persistentDespesa.getUsuarioId();
            Usuario usuarioIdNew = despesa.getUsuarioId();
            if (categoriaDespesaIdNew != null) {
                categoriaDespesaIdNew = em.getReference(categoriaDespesaIdNew.getClass(), categoriaDespesaIdNew.getIdCategoriaDespesa());
                despesa.setCategoriaDespesaId(categoriaDespesaIdNew);
            }
            if (formaPagamentoIdNew != null) {
                formaPagamentoIdNew = em.getReference(formaPagamentoIdNew.getClass(), formaPagamentoIdNew.getIdFormaPagamento());
                despesa.setFormaPagamentoId(formaPagamentoIdNew);
            }
            if (moedaIdNew != null) {
                moedaIdNew = em.getReference(moedaIdNew.getClass(), moedaIdNew.getIdMoeda());
                despesa.setMoedaId(moedaIdNew);
            }
            if (tipoDespesaIdNew != null) {
                tipoDespesaIdNew = em.getReference(tipoDespesaIdNew.getClass(), tipoDespesaIdNew.getIdTipoDespesa());
                despesa.setTipoDespesaId(tipoDespesaIdNew);
            }
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                despesa.setUsuarioId(usuarioIdNew);
            }
            despesa = em.merge(despesa);
            if (categoriaDespesaIdOld != null && !categoriaDespesaIdOld.equals(categoriaDespesaIdNew)) {
                categoriaDespesaIdOld.getDespesaList().remove(despesa);
                categoriaDespesaIdOld = em.merge(categoriaDespesaIdOld);
            }
            if (categoriaDespesaIdNew != null && !categoriaDespesaIdNew.equals(categoriaDespesaIdOld)) {
                categoriaDespesaIdNew.getDespesaList().add(despesa);
                categoriaDespesaIdNew = em.merge(categoriaDespesaIdNew);
            }
            if (formaPagamentoIdOld != null && !formaPagamentoIdOld.equals(formaPagamentoIdNew)) {
                formaPagamentoIdOld.getDespesaList().remove(despesa);
                formaPagamentoIdOld = em.merge(formaPagamentoIdOld);
            }
            if (formaPagamentoIdNew != null && !formaPagamentoIdNew.equals(formaPagamentoIdOld)) {
                formaPagamentoIdNew.getDespesaList().add(despesa);
                formaPagamentoIdNew = em.merge(formaPagamentoIdNew);
            }
            if (moedaIdOld != null && !moedaIdOld.equals(moedaIdNew)) {
                moedaIdOld.getDespesaList().remove(despesa);
                moedaIdOld = em.merge(moedaIdOld);
            }
            if (moedaIdNew != null && !moedaIdNew.equals(moedaIdOld)) {
                moedaIdNew.getDespesaList().add(despesa);
                moedaIdNew = em.merge(moedaIdNew);
            }
            if (tipoDespesaIdOld != null && !tipoDespesaIdOld.equals(tipoDespesaIdNew)) {
                tipoDespesaIdOld.getDespesaList().remove(despesa);
                tipoDespesaIdOld = em.merge(tipoDespesaIdOld);
            }
            if (tipoDespesaIdNew != null && !tipoDespesaIdNew.equals(tipoDespesaIdOld)) {
                tipoDespesaIdNew.getDespesaList().add(despesa);
                tipoDespesaIdNew = em.merge(tipoDespesaIdNew);
            }
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getDespesaList().remove(despesa);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getDespesaList().add(despesa);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = despesa.getIdDespesa();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The despesa with id " + id + " no longer exists.");
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
            Despesa despesa;
            try {
                despesa = em.getReference(Despesa.class, id);
                despesa.getIdDespesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The despesa with id " + id + " no longer exists.", enfe);
            }
            CategoriaDespesa categoriaDespesaId = despesa.getCategoriaDespesaId();
            if (categoriaDespesaId != null) {
                categoriaDespesaId.getDespesaList().remove(despesa);
                categoriaDespesaId = em.merge(categoriaDespesaId);
            }
            FormaPagamento formaPagamentoId = despesa.getFormaPagamentoId();
            if (formaPagamentoId != null) {
                formaPagamentoId.getDespesaList().remove(despesa);
                formaPagamentoId = em.merge(formaPagamentoId);
            }
            Moeda moedaId = despesa.getMoedaId();
            if (moedaId != null) {
                moedaId.getDespesaList().remove(despesa);
                moedaId = em.merge(moedaId);
            }
            TipoDespesa tipoDespesaId = despesa.getTipoDespesaId();
            if (tipoDespesaId != null) {
                tipoDespesaId.getDespesaList().remove(despesa);
                tipoDespesaId = em.merge(tipoDespesaId);
            }
            Usuario usuarioId = despesa.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getDespesaList().remove(despesa);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(despesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<Despesa> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<Despesa> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<Despesa> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Despesa.class));
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
    public Despesa find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Despesa.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Despesa> rt = cq.from(Despesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    
    public List<Despesa> findDespesas(Usuario usuario) {
        EntityManager em = getEntityManager();
        
        Query query = null; 
        
        try {
            String hql = "from Despesa d where d.usuarioId.idUsuario = " + usuario.getIdUsuario();        
            query = em.createQuery(hql);

        } catch (Exception ex) {
            
            throw new RuntimeException(ex);
        }

        if(query != null){
            return (List<Despesa>) query.getResultList();
        }
            
        return null;

    }
    
}
