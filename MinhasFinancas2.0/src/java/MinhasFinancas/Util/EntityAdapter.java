/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityAdapter extends Persistence implements AdapterEntityManagerFactory{

    /**
     *
     * @param unidadePersistencia
     * @return 
     */
    @Override
    public EntityManagerFactory criaFabricaDeGerentes(String unidadePersistencia) {
       
        return Persistence.createEntityManagerFactory(unidadePersistencia);  
    
    }

}
