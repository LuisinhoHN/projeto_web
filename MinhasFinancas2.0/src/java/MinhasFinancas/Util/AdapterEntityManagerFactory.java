/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinhasFinancas.Util;

import javax.persistence.EntityManagerFactory;

public interface AdapterEntityManagerFactory {

  public EntityManagerFactory criaFabricaDeGerentes(String unidadePersistencia);
  
}
