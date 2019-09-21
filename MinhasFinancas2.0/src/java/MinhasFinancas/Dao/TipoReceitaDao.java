/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Dao;

import MinhasFinancas.DaoUtil.DAO;
import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import MinhasFinancas.Util.EntityManagerSingleton;
import MinhasFinancas.model.Receita;
import MinhasFinancas.model.TipoReceita;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TipoReceitaDao implements Serializable, DAO<TipoReceita> {

    public TipoReceitaDao() {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(TipoReceita tipoReceita) {
        if (tipoReceita.getReceitaList() == null) {
            tipoReceita.setReceitaList(new ArrayList<Receita>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Receita> attachedReceitaList = new ArrayList<Receita>();
            for (Receita receitaListReceitaToAttach : tipoReceita.getReceitaList()) {
                receitaListReceitaToAttach = em.getReference(receitaListReceitaToAttach.getClass(), receitaListReceitaToAttach.getIdReceita());
                attachedReceitaList.add(receitaListReceitaToAttach);
            }
            tipoReceita.setReceitaList(attachedReceitaList);
            em.persist(tipoReceita);
            for (Receita receitaListReceita : tipoReceita.getReceitaList()) {
                TipoReceita oldTipoReceitaIdOfReceitaListReceita = receitaListReceita.getTipoReceitaId();
                receitaListReceita.setTipoReceitaId(tipoReceita);
                receitaListReceita = em.merge(receitaListReceita);
                if (oldTipoReceitaIdOfReceitaListReceita != null) {
                    oldTipoReceitaIdOfReceitaListReceita.getReceitaList().remove(receitaListReceita);
                    oldTipoReceitaIdOfReceitaListReceita = em.merge(oldTipoReceitaIdOfReceitaListReceita);
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
    public void edit(TipoReceita tipoReceita) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoReceita persistentTipoReceita = em.find(TipoReceita.class, tipoReceita.getIdTipoReceita());
            List<Receita> receitaListOld = persistentTipoReceita.getReceitaList();
            List<Receita> receitaListNew = tipoReceita.getReceitaList();
            List<Receita> attachedReceitaListNew = new ArrayList<Receita>();
            for (Receita receitaListNewReceitaToAttach : receitaListNew) {
                receitaListNewReceitaToAttach = em.getReference(receitaListNewReceitaToAttach.getClass(), receitaListNewReceitaToAttach.getIdReceita());
                attachedReceitaListNew.add(receitaListNewReceitaToAttach);
            }
            receitaListNew = attachedReceitaListNew;
            tipoReceita.setReceitaList(receitaListNew);
            tipoReceita = em.merge(tipoReceita);
            for (Receita receitaListOldReceita : receitaListOld) {
                if (!receitaListNew.contains(receitaListOldReceita)) {
                    receitaListOldReceita.setTipoReceitaId(null);
                    receitaListOldReceita = em.merge(receitaListOldReceita);
                }
            }
            for (Receita receitaListNewReceita : receitaListNew) {
                if (!receitaListOld.contains(receitaListNewReceita)) {
                    TipoReceita oldTipoReceitaIdOfReceitaListNewReceita = receitaListNewReceita.getTipoReceitaId();
                    receitaListNewReceita.setTipoReceitaId(tipoReceita);
                    receitaListNewReceita = em.merge(receitaListNewReceita);
                    if (oldTipoReceitaIdOfReceitaListNewReceita != null && !oldTipoReceitaIdOfReceitaListNewReceita.equals(tipoReceita)) {
                        oldTipoReceitaIdOfReceitaListNewReceita.getReceitaList().remove(receitaListNewReceita);
                        oldTipoReceitaIdOfReceitaListNewReceita = em.merge(oldTipoReceitaIdOfReceitaListNewReceita);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoReceita.getIdTipoReceita();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The tipoReceita with id " + id + " no longer exists.");
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
            TipoReceita tipoReceita;
            try {
                tipoReceita = em.getReference(TipoReceita.class, id);
                tipoReceita.getIdTipoReceita();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoReceita with id " + id + " no longer exists.", enfe);
            }
            List<Receita> receitaList = tipoReceita.getReceitaList();
            for (Receita receitaListReceita : receitaList) {
                receitaListReceita.setTipoReceitaId(null);
                receitaListReceita = em.merge(receitaListReceita);
            }
            em.remove(tipoReceita);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<TipoReceita> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<TipoReceita> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<TipoReceita> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoReceita.class));
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
    public TipoReceita find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoReceita.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoReceita> rt = cq.from(TipoReceita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    public TipoReceita BuscaPeloNome(TipoReceita tipoReceita){
        
        TipoReceita tipoReceitaEncontrado;
        EntityManager em = getEntityManager();
        
        String hql = "from TipoReceita u where u.nome = '" + tipoReceita.getNome()+"' ";
        Query query = em.createQuery(hql);
        
        tipoReceitaEncontrado = (TipoReceita) query.getSingleResult();
        
        if(tipoReceitaEncontrado != null){
            return tipoReceitaEncontrado;
        } 
        
        return tipoReceitaEncontrado;
    }
    
}

