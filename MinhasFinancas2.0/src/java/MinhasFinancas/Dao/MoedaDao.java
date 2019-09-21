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

public class MoedaDao implements Serializable, DAO<Moeda> {

    public MoedaDao() {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(Moeda moeda) {
        if (moeda.getReceitaList() == null) {
            moeda.setReceitaList(new ArrayList<Receita>());
        }
        if (moeda.getDespesaList() == null) {
            moeda.setDespesaList(new ArrayList<Despesa>());
        }
        if (moeda.getUsuarioList() == null) {
            moeda.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Receita> attachedReceitaList = new ArrayList<Receita>();
            for (Receita receitaListReceitaToAttach : moeda.getReceitaList()) {
                receitaListReceitaToAttach = em.getReference(receitaListReceitaToAttach.getClass(), receitaListReceitaToAttach.getIdReceita());
                attachedReceitaList.add(receitaListReceitaToAttach);
            }
            moeda.setReceitaList(attachedReceitaList);
            List<Despesa> attachedDespesaList = new ArrayList<Despesa>();
            for (Despesa despesaListDespesaToAttach : moeda.getDespesaList()) {
                despesaListDespesaToAttach = em.getReference(despesaListDespesaToAttach.getClass(), despesaListDespesaToAttach.getIdDespesa());
                attachedDespesaList.add(despesaListDespesaToAttach);
            }
            moeda.setDespesaList(attachedDespesaList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : moeda.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            moeda.setUsuarioList(attachedUsuarioList);
            em.persist(moeda);
            for (Receita receitaListReceita : moeda.getReceitaList()) {
                Moeda oldMoedaIdOfReceitaListReceita = receitaListReceita.getMoedaId();
                receitaListReceita.setMoedaId(moeda);
                receitaListReceita = em.merge(receitaListReceita);
                if (oldMoedaIdOfReceitaListReceita != null) {
                    oldMoedaIdOfReceitaListReceita.getReceitaList().remove(receitaListReceita);
                    oldMoedaIdOfReceitaListReceita = em.merge(oldMoedaIdOfReceitaListReceita);
                }
            }
            for (Despesa despesaListDespesa : moeda.getDespesaList()) {
                Moeda oldMoedaIdOfDespesaListDespesa = despesaListDespesa.getMoedaId();
                despesaListDespesa.setMoedaId(moeda);
                despesaListDespesa = em.merge(despesaListDespesa);
                if (oldMoedaIdOfDespesaListDespesa != null) {
                    oldMoedaIdOfDespesaListDespesa.getDespesaList().remove(despesaListDespesa);
                    oldMoedaIdOfDespesaListDespesa = em.merge(oldMoedaIdOfDespesaListDespesa);
                }
            }
            for (Usuario usuarioListUsuario : moeda.getUsuarioList()) {
                Moeda oldMoedaIdOfUsuarioListUsuario = usuarioListUsuario.getMoedaId();
                usuarioListUsuario.setMoedaId(moeda);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldMoedaIdOfUsuarioListUsuario != null) {
                    oldMoedaIdOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldMoedaIdOfUsuarioListUsuario = em.merge(oldMoedaIdOfUsuarioListUsuario);
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
    public void edit(Moeda moeda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moeda persistentMoeda = em.find(Moeda.class, moeda.getIdMoeda());
            List<Receita> receitaListOld = persistentMoeda.getReceitaList();
            List<Receita> receitaListNew = moeda.getReceitaList();
            List<Despesa> despesaListOld = persistentMoeda.getDespesaList();
            List<Despesa> despesaListNew = moeda.getDespesaList();
            List<Usuario> usuarioListOld = persistentMoeda.getUsuarioList();
            List<Usuario> usuarioListNew = moeda.getUsuarioList();
            List<Receita> attachedReceitaListNew = new ArrayList<Receita>();
            for (Receita receitaListNewReceitaToAttach : receitaListNew) {
                receitaListNewReceitaToAttach = em.getReference(receitaListNewReceitaToAttach.getClass(), receitaListNewReceitaToAttach.getIdReceita());
                attachedReceitaListNew.add(receitaListNewReceitaToAttach);
            }
            receitaListNew = attachedReceitaListNew;
            moeda.setReceitaList(receitaListNew);
            List<Despesa> attachedDespesaListNew = new ArrayList<Despesa>();
            for (Despesa despesaListNewDespesaToAttach : despesaListNew) {
                despesaListNewDespesaToAttach = em.getReference(despesaListNewDespesaToAttach.getClass(), despesaListNewDespesaToAttach.getIdDespesa());
                attachedDespesaListNew.add(despesaListNewDespesaToAttach);
            }
            despesaListNew = attachedDespesaListNew;
            moeda.setDespesaList(despesaListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            moeda.setUsuarioList(usuarioListNew);
            moeda = em.merge(moeda);
            for (Receita receitaListOldReceita : receitaListOld) {
                if (!receitaListNew.contains(receitaListOldReceita)) {
                    receitaListOldReceita.setMoedaId(null);
                    receitaListOldReceita = em.merge(receitaListOldReceita);
                }
            }
            for (Receita receitaListNewReceita : receitaListNew) {
                if (!receitaListOld.contains(receitaListNewReceita)) {
                    Moeda oldMoedaIdOfReceitaListNewReceita = receitaListNewReceita.getMoedaId();
                    receitaListNewReceita.setMoedaId(moeda);
                    receitaListNewReceita = em.merge(receitaListNewReceita);
                    if (oldMoedaIdOfReceitaListNewReceita != null && !oldMoedaIdOfReceitaListNewReceita.equals(moeda)) {
                        oldMoedaIdOfReceitaListNewReceita.getReceitaList().remove(receitaListNewReceita);
                        oldMoedaIdOfReceitaListNewReceita = em.merge(oldMoedaIdOfReceitaListNewReceita);
                    }
                }
            }
            for (Despesa despesaListOldDespesa : despesaListOld) {
                if (!despesaListNew.contains(despesaListOldDespesa)) {
                    despesaListOldDespesa.setMoedaId(null);
                    despesaListOldDespesa = em.merge(despesaListOldDespesa);
                }
            }
            for (Despesa despesaListNewDespesa : despesaListNew) {
                if (!despesaListOld.contains(despesaListNewDespesa)) {
                    Moeda oldMoedaIdOfDespesaListNewDespesa = despesaListNewDespesa.getMoedaId();
                    despesaListNewDespesa.setMoedaId(moeda);
                    despesaListNewDespesa = em.merge(despesaListNewDespesa);
                    if (oldMoedaIdOfDespesaListNewDespesa != null && !oldMoedaIdOfDespesaListNewDespesa.equals(moeda)) {
                        oldMoedaIdOfDespesaListNewDespesa.getDespesaList().remove(despesaListNewDespesa);
                        oldMoedaIdOfDespesaListNewDespesa = em.merge(oldMoedaIdOfDespesaListNewDespesa);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setMoedaId(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Moeda oldMoedaIdOfUsuarioListNewUsuario = usuarioListNewUsuario.getMoedaId();
                    usuarioListNewUsuario.setMoedaId(moeda);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldMoedaIdOfUsuarioListNewUsuario != null && !oldMoedaIdOfUsuarioListNewUsuario.equals(moeda)) {
                        oldMoedaIdOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldMoedaIdOfUsuarioListNewUsuario = em.merge(oldMoedaIdOfUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = moeda.getIdMoeda();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The moeda with id " + id + " no longer exists.");
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
            Moeda moeda;
            try {
                moeda = em.getReference(Moeda.class, id);
                moeda.getIdMoeda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The moeda with id " + id + " no longer exists.", enfe);
            }
            List<Receita> receitaList = moeda.getReceitaList();
            for (Receita receitaListReceita : receitaList) {
                receitaListReceita.setMoedaId(null);
                receitaListReceita = em.merge(receitaListReceita);
            }
            List<Despesa> despesaList = moeda.getDespesaList();
            for (Despesa despesaListDespesa : despesaList) {
                despesaListDespesa.setMoedaId(null);
                despesaListDespesa = em.merge(despesaListDespesa);
            }
            List<Usuario> usuarioList = moeda.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setMoedaId(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(moeda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<Moeda> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<Moeda> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<Moeda> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moeda.class));
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
    /** 
     * Essa função procura uma moeda com base no id, caso encontre retorna um 
     * objeto moeda.
    */
    @Override
    public Moeda find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moeda.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Moeda> rt = cq.from(Moeda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    
    public Moeda BuscarPeloNome(Moeda moeda){
    
        EntityManager em = getEntityManager();
        
        Moeda moedaEncontrada = new Moeda();
        
        String hql = "from Moeda u where u.nome = '"+ moeda.getNome() +"' ";
        Query query = em.createQuery(hql);
        
        moedaEncontrada = (Moeda) query.getSingleResult();
        
        if(moedaEncontrada != null){
            return moedaEncontrada;
        } 
        
        return moedaEncontrada;
    }   
}
