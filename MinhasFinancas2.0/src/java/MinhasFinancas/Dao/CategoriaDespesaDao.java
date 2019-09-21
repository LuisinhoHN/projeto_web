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
import MinhasFinancas.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CategoriaDespesaDao implements Serializable, DAO<CategoriaDespesa> {

    public CategoriaDespesaDao( ) {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(CategoriaDespesa categoriaDespesa) {
        if (categoriaDespesa.getDespesaList() == null) {
            categoriaDespesa.setDespesaList(new ArrayList<Despesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioId = categoriaDespesa.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                categoriaDespesa.setUsuarioId(usuarioId);
            }
            List<Despesa> attachedDespesaList = new ArrayList<Despesa>();
            for (Despesa despesaListDespesaToAttach : categoriaDespesa.getDespesaList()) {
                despesaListDespesaToAttach = em.getReference(despesaListDespesaToAttach.getClass(), despesaListDespesaToAttach.getIdDespesa());
                attachedDespesaList.add(despesaListDespesaToAttach);
            }
            categoriaDespesa.setDespesaList(attachedDespesaList);
            em.persist(categoriaDespesa);
            if (usuarioId != null) {
                usuarioId.getCategoriaDespesaList().add(categoriaDespesa);
                usuarioId = em.merge(usuarioId);
            }
            for (Despesa despesaListDespesa : categoriaDespesa.getDespesaList()) {
                CategoriaDespesa oldCategoriaDespesaIdOfDespesaListDespesa = despesaListDespesa.getCategoriaDespesaId();
                despesaListDespesa.setCategoriaDespesaId(categoriaDespesa);
                despesaListDespesa = em.merge(despesaListDespesa);
                if (oldCategoriaDespesaIdOfDespesaListDespesa != null) {
                    oldCategoriaDespesaIdOfDespesaListDespesa.getDespesaList().remove(despesaListDespesa);
                    oldCategoriaDespesaIdOfDespesaListDespesa = em.merge(oldCategoriaDespesaIdOfDespesaListDespesa);
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
    public void edit(CategoriaDespesa categoriaDespesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaDespesa persistentCategoriaDespesa = em.find(CategoriaDespesa.class, categoriaDespesa.getIdCategoriaDespesa());
            Usuario usuarioIdOld = persistentCategoriaDespesa.getUsuarioId();
            Usuario usuarioIdNew = categoriaDespesa.getUsuarioId();
            List<Despesa> despesaListOld = persistentCategoriaDespesa.getDespesaList();
            List<Despesa> despesaListNew = categoriaDespesa.getDespesaList();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                categoriaDespesa.setUsuarioId(usuarioIdNew);
            }
            List<Despesa> attachedDespesaListNew = new ArrayList<Despesa>();
            for (Despesa despesaListNewDespesaToAttach : despesaListNew) {
                despesaListNewDespesaToAttach = em.getReference(despesaListNewDespesaToAttach.getClass(), despesaListNewDespesaToAttach.getIdDespesa());
                attachedDespesaListNew.add(despesaListNewDespesaToAttach);
            }
            despesaListNew = attachedDespesaListNew;
            categoriaDespesa.setDespesaList(despesaListNew);
            categoriaDespesa = em.merge(categoriaDespesa);
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getCategoriaDespesaList().remove(categoriaDespesa);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getCategoriaDespesaList().add(categoriaDespesa);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            for (Despesa despesaListOldDespesa : despesaListOld) {
                if (!despesaListNew.contains(despesaListOldDespesa)) {
                    despesaListOldDespesa.setCategoriaDespesaId(null);
                    despesaListOldDespesa = em.merge(despesaListOldDespesa);
                }
            }
            for (Despesa despesaListNewDespesa : despesaListNew) {
                if (!despesaListOld.contains(despesaListNewDespesa)) {
                    CategoriaDespesa oldCategoriaDespesaIdOfDespesaListNewDespesa = despesaListNewDespesa.getCategoriaDespesaId();
                    despesaListNewDespesa.setCategoriaDespesaId(categoriaDespesa);
                    despesaListNewDespesa = em.merge(despesaListNewDespesa);
                    if (oldCategoriaDespesaIdOfDespesaListNewDespesa != null && !oldCategoriaDespesaIdOfDespesaListNewDespesa.equals(categoriaDespesa)) {
                        oldCategoriaDespesaIdOfDespesaListNewDespesa.getDespesaList().remove(despesaListNewDespesa);
                        oldCategoriaDespesaIdOfDespesaListNewDespesa = em.merge(oldCategoriaDespesaIdOfDespesaListNewDespesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriaDespesa.getIdCategoriaDespesa();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The categoriaDespesa with id " + id + " no longer exists.");
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
            CategoriaDespesa categoriaDespesa;
            try {
                categoriaDespesa = em.getReference(CategoriaDespesa.class, id);
                categoriaDespesa.getIdCategoriaDespesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaDespesa with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioId = categoriaDespesa.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getCategoriaDespesaList().remove(categoriaDespesa);
                usuarioId = em.merge(usuarioId);
            }
            List<Despesa> despesaList = categoriaDespesa.getDespesaList();
            for (Despesa despesaListDespesa : despesaList) {
                despesaListDespesa.setCategoriaDespesaId(null);
                despesaListDespesa = em.merge(despesaListDespesa);
            }
            em.remove(categoriaDespesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<CategoriaDespesa> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<CategoriaDespesa> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<CategoriaDespesa> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaDespesa.class));
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
    public CategoriaDespesa find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaDespesa.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaDespesa> rt = cq.from(CategoriaDespesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    public CategoriaDespesa BuscaPeloNome(CategoriaDespesa categoriaDespesa){
        
        CategoriaDespesa categoriaDespesaEncontrada;
        EntityManager em = getEntityManager();
        
        String hql = "from CategoriaDespesa u where u.nome = '" + categoriaDespesa.getNome()+"' ";
        Query query = em.createQuery(hql);
        
        categoriaDespesaEncontrada = (CategoriaDespesa) query.getSingleResult();
        
        if(categoriaDespesaEncontrada != null){
            return categoriaDespesaEncontrada;
        } 
        
        return categoriaDespesaEncontrada;
    }
    
    public List<CategoriaDespesa> findCategoriasDespesa(Usuario usuario) {
        EntityManager em = getEntityManager();
        
        Query query = null; 
        
        try {
            String hql = "from CategoriaDespesa c where c.usuarioId.idUsuario = " + usuario.getIdUsuario();        
            query = em.createQuery(hql);

        } catch (Exception ex) {
            
            throw new RuntimeException(ex);
        }

        if(query != null){
            return (List<CategoriaDespesa>) query.getResultList();
        }
            
        return null;

    }
    
}
