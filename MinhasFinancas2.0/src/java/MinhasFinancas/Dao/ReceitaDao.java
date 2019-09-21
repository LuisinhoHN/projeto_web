/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Dao;

import MinhasFinancas.DaoUtil.DAO;
import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import MinhasFinancas.Util.EntityManagerSingleton;
import MinhasFinancas.model.Moeda;
import MinhasFinancas.model.Receita;
import MinhasFinancas.model.TipoReceita;
import MinhasFinancas.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ReceitaDao implements Serializable, DAO<Receita> {

    public ReceitaDao() {

    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(Receita receita) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moeda moedaId = receita.getMoedaId();
            if (moedaId != null) {
                moedaId = em.getReference(moedaId.getClass(), moedaId.getIdMoeda());
                receita.setMoedaId(moedaId);
            }
            TipoReceita tipoReceitaId = receita.getTipoReceitaId();
            if (tipoReceitaId != null) {
                tipoReceitaId = em.getReference(tipoReceitaId.getClass(), tipoReceitaId.getIdTipoReceita());
                receita.setTipoReceitaId(tipoReceitaId);
            }
            Usuario usuarioId = receita.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                receita.setUsuarioId(usuarioId);
            }
            em.persist(receita);
            if (moedaId != null) {
                moedaId.getReceitaList().add(receita);
                moedaId = em.merge(moedaId);
            }
            if (tipoReceitaId != null) {
                tipoReceitaId.getReceitaList().add(receita);
                tipoReceitaId = em.merge(tipoReceitaId);
            }
            if (usuarioId != null) {
                usuarioId.getReceitaList().add(receita);
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
    public void edit(Receita receita) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receita persistentReceita = em.find(Receita.class, receita.getIdReceita());
            Moeda moedaIdOld = persistentReceita.getMoedaId();
            Moeda moedaIdNew = receita.getMoedaId();
            TipoReceita tipoReceitaIdOld = persistentReceita.getTipoReceitaId();
            TipoReceita tipoReceitaIdNew = receita.getTipoReceitaId();
            Usuario usuarioIdOld = persistentReceita.getUsuarioId();
            Usuario usuarioIdNew = receita.getUsuarioId();
            if (moedaIdNew != null) {
                moedaIdNew = em.getReference(moedaIdNew.getClass(), moedaIdNew.getIdMoeda());
                receita.setMoedaId(moedaIdNew);
            }
            if (tipoReceitaIdNew != null) {
                tipoReceitaIdNew = em.getReference(tipoReceitaIdNew.getClass(), tipoReceitaIdNew.getIdTipoReceita());
                receita.setTipoReceitaId(tipoReceitaIdNew);
            }
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                receita.setUsuarioId(usuarioIdNew);
            }
            receita = em.merge(receita);
            if (moedaIdOld != null && !moedaIdOld.equals(moedaIdNew)) {
                moedaIdOld.getReceitaList().remove(receita);
                moedaIdOld = em.merge(moedaIdOld);
            }
            if (moedaIdNew != null && !moedaIdNew.equals(moedaIdOld)) {
                moedaIdNew.getReceitaList().add(receita);
                moedaIdNew = em.merge(moedaIdNew);
            }
            if (tipoReceitaIdOld != null && !tipoReceitaIdOld.equals(tipoReceitaIdNew)) {
                tipoReceitaIdOld.getReceitaList().remove(receita);
                tipoReceitaIdOld = em.merge(tipoReceitaIdOld);
            }
            if (tipoReceitaIdNew != null && !tipoReceitaIdNew.equals(tipoReceitaIdOld)) {
                tipoReceitaIdNew.getReceitaList().add(receita);
                tipoReceitaIdNew = em.merge(tipoReceitaIdNew);
            }
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getReceitaList().remove(receita);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getReceitaList().add(receita);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = receita.getIdReceita();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The receita with id " + id + " no longer exists.");
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
            Receita receita;
            try {
                receita = em.getReference(Receita.class, id);
                receita.getIdReceita();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receita with id " + id + " no longer exists.", enfe);
            }
            Moeda moedaId = receita.getMoedaId();
            if (moedaId != null) {
                moedaId.getReceitaList().remove(receita);
                moedaId = em.merge(moedaId);
            }
            TipoReceita tipoReceitaId = receita.getTipoReceitaId();
            if (tipoReceitaId != null) {
                tipoReceitaId.getReceitaList().remove(receita);
                tipoReceitaId = em.merge(tipoReceitaId);
            }
            Usuario usuarioId = receita.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getReceitaList().remove(receita);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(receita);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<Receita> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<Receita> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<Receita> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receita.class));
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
    public Receita find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receita.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receita> rt = cq.from(Receita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }

    public List<Receita> findReceitas(Usuario usuario) {
        EntityManager em = getEntityManager();
        
        Query query = null; 
        
        try {
            String hql = "from Receita r where r.usuarioId.idUsuario = " + usuario.getIdUsuario();        
            query = em.createQuery(hql);

        } catch (Exception ex) {
            
            throw new RuntimeException(ex);
        }

        if(query != null){
            return (List<Receita>) query.getResultList();
        }
            
        return null;

    }
}
