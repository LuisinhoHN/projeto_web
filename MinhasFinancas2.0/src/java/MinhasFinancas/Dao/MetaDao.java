/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Dao;

import MinhasFinancas.DaoUtil.DAO;
import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import MinhasFinancas.Util.EntityManagerSingleton;
import MinhasFinancas.model.Meta;
import MinhasFinancas.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MetaDao implements Serializable, DAO<Meta> {

    public MetaDao() {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(Meta meta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioId = meta.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                meta.setUsuarioId(usuarioId);
            }
            em.persist(meta);
            if (usuarioId != null) {
                usuarioId.getMetaList().add(meta);
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
    public void edit(Meta meta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meta persistentMeta = em.find(Meta.class, meta.getIdMeta());
            Usuario usuarioIdOld = persistentMeta.getUsuarioId();
            Usuario usuarioIdNew = meta.getUsuarioId();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                meta.setUsuarioId(usuarioIdNew);
            }
            meta = em.merge(meta);
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getMetaList().remove(meta);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getMetaList().add(meta);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = meta.getIdMeta();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The meta with id " + id + " no longer exists.");
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
            Meta meta;
            try {
                meta = em.getReference(Meta.class, id);
                meta.getIdMeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The meta with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioId = meta.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getMetaList().remove(meta);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(meta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<Meta> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<Meta> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<Meta> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Meta.class));
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
    public Meta find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Meta.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Meta> rt = cq.from(Meta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    
    public Meta BuscaPeloNome(Meta meta){
        
        Meta metaEncontrado;
        EntityManager em = getEntityManager();
        
        String hql = "from Meta u where u.nome = '" + meta.getNome()+"' ";
        Query query = em.createQuery(hql);
        
        metaEncontrado = (Meta) query.getSingleResult();
        
        if(metaEncontrado != null){
            return metaEncontrado;
        } 
        
        return metaEncontrado;
    }
    
    
    // Fiz esse aqui que tava faltanto
    public List<Meta> findMetas(Usuario usuario) {
        EntityManager em = getEntityManager();
        
        Query query = null; 
        
        try {
            String hql = "from Meta c where c.usuarioId.idUsuario = " + usuario.getIdUsuario();        
            query = em.createQuery(hql);

        } catch (Exception ex) {
            
            throw new RuntimeException(ex);
        }

        if(query != null){
            return (List<Meta>) query.getResultList();
        }
            
        return null;

    }
    
    
}
