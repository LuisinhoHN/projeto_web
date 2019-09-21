/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.DaoUtil;

import MinhasFinancas.JPA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

public interface DAO <E> {
    
    public EntityManager getEntityManager();

    void create(E entity);

    void edit(E entity) throws NonexistentEntityException, Exception;

    void destroy(Integer id) throws NonexistentEntityException;

    public List<E> find();

    public List<E> find(int maxResults, int firstResult);

    public List<E> find(boolean all, int maxResults, int firstResult);

    public E find(Integer id);

    public int getCount();
    
}
