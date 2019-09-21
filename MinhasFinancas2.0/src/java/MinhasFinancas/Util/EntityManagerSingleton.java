/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerSingleton {
    
    private static EntityManager em;
    private static EntityManagerFactory emf;

    private EntityManagerSingleton() {

    }   

    public static synchronized EntityManager getInstance() {
        if (em == null) {
            EntityAdapter entityAdapter = new EntityAdapter();
            
            emf = entityAdapter.criaFabricaDeGerentes("MinhasFinancasPU");
            em = emf.createEntityManager();
        }
        return em;
    }

}