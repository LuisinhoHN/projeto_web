/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Dao;

import MinhasFinancas.DaoUtil.DAO;
import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import MinhasFinancas.Util.EntityManagerSingleton;
import MinhasFinancas.model.Despesa;
import MinhasFinancas.model.TipoDespesa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TipoDespesaDao implements Serializable, DAO<TipoDespesa> {

    public TipoDespesaDao() {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(TipoDespesa tipoDespesa) {
        if (tipoDespesa.getDespesaList() == null) {
            tipoDespesa.setDespesaList(new ArrayList<Despesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Despesa> attachedDespesaList = new ArrayList<Despesa>();
            for (Despesa despesaListDespesaToAttach : tipoDespesa.getDespesaList()) {
                despesaListDespesaToAttach = em.getReference(despesaListDespesaToAttach.getClass(), despesaListDespesaToAttach.getIdDespesa());
                attachedDespesaList.add(despesaListDespesaToAttach);
            }
            tipoDespesa.setDespesaList(attachedDespesaList);
            em.persist(tipoDespesa);
            for (Despesa despesaListDespesa : tipoDespesa.getDespesaList()) {
                TipoDespesa oldTipoDespesaIdOfDespesaListDespesa = despesaListDespesa.getTipoDespesaId();
                despesaListDespesa.setTipoDespesaId(tipoDespesa);
                despesaListDespesa = em.merge(despesaListDespesa);
                if (oldTipoDespesaIdOfDespesaListDespesa != null) {
                    oldTipoDespesaIdOfDespesaListDespesa.getDespesaList().remove(despesaListDespesa);
                    oldTipoDespesaIdOfDespesaListDespesa = em.merge(oldTipoDespesaIdOfDespesaListDespesa);
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
    public void edit(TipoDespesa tipoDespesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDespesa persistentTipoDespesa = em.find(TipoDespesa.class, tipoDespesa.getIdTipoDespesa());
            List<Despesa> despesaListOld = persistentTipoDespesa.getDespesaList();
            List<Despesa> despesaListNew = tipoDespesa.getDespesaList();
            List<Despesa> attachedDespesaListNew = new ArrayList<Despesa>();
            for (Despesa despesaListNewDespesaToAttach : despesaListNew) {
                despesaListNewDespesaToAttach = em.getReference(despesaListNewDespesaToAttach.getClass(), despesaListNewDespesaToAttach.getIdDespesa());
                attachedDespesaListNew.add(despesaListNewDespesaToAttach);
            }
            despesaListNew = attachedDespesaListNew;
            tipoDespesa.setDespesaList(despesaListNew);
            tipoDespesa = em.merge(tipoDespesa);
            for (Despesa despesaListOldDespesa : despesaListOld) {
                if (!despesaListNew.contains(despesaListOldDespesa)) {
                    despesaListOldDespesa.setTipoDespesaId(null);
                    despesaListOldDespesa = em.merge(despesaListOldDespesa);
                }
            }
            for (Despesa despesaListNewDespesa : despesaListNew) {
                if (!despesaListOld.contains(despesaListNewDespesa)) {
                    TipoDespesa oldTipoDespesaIdOfDespesaListNewDespesa = despesaListNewDespesa.getTipoDespesaId();
                    despesaListNewDespesa.setTipoDespesaId(tipoDespesa);
                    despesaListNewDespesa = em.merge(despesaListNewDespesa);
                    if (oldTipoDespesaIdOfDespesaListNewDespesa != null && !oldTipoDespesaIdOfDespesaListNewDespesa.equals(tipoDespesa)) {
                        oldTipoDespesaIdOfDespesaListNewDespesa.getDespesaList().remove(despesaListNewDespesa);
                        oldTipoDespesaIdOfDespesaListNewDespesa = em.merge(oldTipoDespesaIdOfDespesaListNewDespesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDespesa.getIdTipoDespesa();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The tipoDespesa with id " + id + " no longer exists.");
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
            TipoDespesa tipoDespesa;
            try {
                tipoDespesa = em.getReference(TipoDespesa.class, id);
                tipoDespesa.getIdTipoDespesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDespesa with id " + id + " no longer exists.", enfe);
            }
            List<Despesa> despesaList = tipoDespesa.getDespesaList();
            for (Despesa despesaListDespesa : despesaList) {
                despesaListDespesa.setTipoDespesaId(null);
                despesaListDespesa = em.merge(despesaListDespesa);
            }
            em.remove(tipoDespesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<TipoDespesa> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<TipoDespesa> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<TipoDespesa> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDespesa.class));
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
    public TipoDespesa find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDespesa.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDespesa> rt = cq.from(TipoDespesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    
    public TipoDespesa BuscaPeloNome(TipoDespesa tipoDespesa){
        
        TipoDespesa tipoDespesaEncontrado;
        EntityManager em = getEntityManager();
        
        String hql = "from TipoDespesa u where u.nome = '" + tipoDespesa.getNome()+"' ";
        Query query = em.createQuery(hql);
        
        tipoDespesaEncontrado = (TipoDespesa) query.getSingleResult();
        
        if(tipoDespesaEncontrado != null){
            return tipoDespesaEncontrado;
        } 
        
        return tipoDespesaEncontrado;
    }
}
