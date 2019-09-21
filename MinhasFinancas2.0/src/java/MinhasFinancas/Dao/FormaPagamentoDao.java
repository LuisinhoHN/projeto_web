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
import MinhasFinancas.model.FormaPagamento;
import MinhasFinancas.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FormaPagamentoDao implements Serializable, DAO<FormaPagamento> {

    public FormaPagamentoDao() {
    }

    @Override
    public EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance();
    }

    @Override
    public void create(FormaPagamento formaPagamento) {
        if (formaPagamento.getDespesaList() == null) {
            formaPagamento.setDespesaList(new ArrayList<Despesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioId = formaPagamento.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getIdUsuario());
                formaPagamento.setUsuarioId(usuarioId);
            }
            List<Despesa> attachedDespesaList = new ArrayList<Despesa>();
            for (Despesa despesaListDespesaToAttach : formaPagamento.getDespesaList()) {
                despesaListDespesaToAttach = em.getReference(despesaListDespesaToAttach.getClass(), despesaListDespesaToAttach.getIdDespesa());
                attachedDespesaList.add(despesaListDespesaToAttach);
            }
            formaPagamento.setDespesaList(attachedDespesaList);
            em.persist(formaPagamento);
            if (usuarioId != null) {
                usuarioId.getFormaPagamentoList().add(formaPagamento);
                usuarioId = em.merge(usuarioId);
            }
            for (Despesa despesaListDespesa : formaPagamento.getDespesaList()) {
                FormaPagamento oldFormaPagamentoIdOfDespesaListDespesa = despesaListDespesa.getFormaPagamentoId();
                despesaListDespesa.setFormaPagamentoId(formaPagamento);
                despesaListDespesa = em.merge(despesaListDespesa);
                if (oldFormaPagamentoIdOfDespesaListDespesa != null) {
                    oldFormaPagamentoIdOfDespesaListDespesa.getDespesaList().remove(despesaListDespesa);
                    oldFormaPagamentoIdOfDespesaListDespesa = em.merge(oldFormaPagamentoIdOfDespesaListDespesa);
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
    public void edit(FormaPagamento formaPagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormaPagamento persistentFormaPagamento = em.find(FormaPagamento.class, formaPagamento.getIdFormaPagamento());
            Usuario usuarioIdOld = persistentFormaPagamento.getUsuarioId();
            Usuario usuarioIdNew = formaPagamento.getUsuarioId();
            List<Despesa> despesaListOld = persistentFormaPagamento.getDespesaList();
            List<Despesa> despesaListNew = formaPagamento.getDespesaList();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getIdUsuario());
                formaPagamento.setUsuarioId(usuarioIdNew);
            }
            List<Despesa> attachedDespesaListNew = new ArrayList<Despesa>();
            for (Despesa despesaListNewDespesaToAttach : despesaListNew) {
                despesaListNewDespesaToAttach = em.getReference(despesaListNewDespesaToAttach.getClass(), despesaListNewDespesaToAttach.getIdDespesa());
                attachedDespesaListNew.add(despesaListNewDespesaToAttach);
            }
            despesaListNew = attachedDespesaListNew;
            formaPagamento.setDespesaList(despesaListNew);
            formaPagamento = em.merge(formaPagamento);
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getFormaPagamentoList().remove(formaPagamento);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getFormaPagamentoList().add(formaPagamento);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            for (Despesa despesaListOldDespesa : despesaListOld) {
                if (!despesaListNew.contains(despesaListOldDespesa)) {
                    despesaListOldDespesa.setFormaPagamentoId(null);
                    despesaListOldDespesa = em.merge(despesaListOldDespesa);
                }
            }
            for (Despesa despesaListNewDespesa : despesaListNew) {
                if (!despesaListOld.contains(despesaListNewDespesa)) {
                    FormaPagamento oldFormaPagamentoIdOfDespesaListNewDespesa = despesaListNewDespesa.getFormaPagamentoId();
                    despesaListNewDespesa.setFormaPagamentoId(formaPagamento);
                    despesaListNewDespesa = em.merge(despesaListNewDespesa);
                    if (oldFormaPagamentoIdOfDespesaListNewDespesa != null && !oldFormaPagamentoIdOfDespesaListNewDespesa.equals(formaPagamento)) {
                        oldFormaPagamentoIdOfDespesaListNewDespesa.getDespesaList().remove(despesaListNewDespesa);
                        oldFormaPagamentoIdOfDespesaListNewDespesa = em.merge(oldFormaPagamentoIdOfDespesaListNewDespesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formaPagamento.getIdFormaPagamento();
                if (find(id) == null) {
                    throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.");
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
            FormaPagamento formaPagamento;
            try {
                formaPagamento = em.getReference(FormaPagamento.class, id);
                formaPagamento.getIdFormaPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioId = formaPagamento.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getFormaPagamentoList().remove(formaPagamento);
                usuarioId = em.merge(usuarioId);
            }
            List<Despesa> despesaList = formaPagamento.getDespesaList();
            for (Despesa despesaListDespesa : despesaList) {
                despesaListDespesa.setFormaPagamentoId(null);
                despesaListDespesa = em.merge(despesaListDespesa);
            }
            em.remove(formaPagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    @Override
    public List<FormaPagamento> find() {
        return find(true, -1, -1);
    }

    @Override
    public List<FormaPagamento> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    @Override
    public List<FormaPagamento> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormaPagamento.class));
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
    public FormaPagamento find(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormaPagamento.class, id);
        } finally {
            //em.close();
        }
    }

    @Override
    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormaPagamento> rt = cq.from(FormaPagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }

    public FormaPagamento BuscaPeloNome(FormaPagamento formaPagamento) {

        FormaPagamento formaPagamentoEncontrado;
        EntityManager em = getEntityManager();

        String hql = "from FormaPagamento u where u.nome = '" + formaPagamento.getNome() + "' ";
        Query query = em.createQuery(hql);

        formaPagamentoEncontrado = (FormaPagamento) query.getSingleResult();

        if (formaPagamentoEncontrado != null) {
            return formaPagamentoEncontrado;
        }

        return formaPagamentoEncontrado;
    }

    public List<FormaPagamento> findFormasPagamento(Usuario usuario) {
        EntityManager em = getEntityManager();

        Query query = null;

        try {
            String hql = "from FormaPagamento f where f.usuarioId.idUsuario = " + usuario.getIdUsuario();
            query = em.createQuery(hql);

        } catch (Exception ex) {

            throw new RuntimeException(ex);
        }

        if (query != null) {
            return (List<FormaPagamento>) query.getResultList();
        }

        return null;

    }
}
