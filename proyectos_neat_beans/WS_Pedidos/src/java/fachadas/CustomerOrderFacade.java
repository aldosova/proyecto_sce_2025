/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.CustomerOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrder> {

    @PersistenceContext(unitName = "WS_PedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderFacade() {
        super(CustomerOrder.class);
    }
    
    public int next_conf_number() {
        // --- COMENTAMOS ESTO TEMPORALMENTE ---
        // try {
        //    int intRes = (Integer) em.createNativeQuery("VALUES (NEXT VALUE FOR NUM_CONF)").getSingleResult();
        //    return intRes;
        // } catch (Exception e) {
        //    e.printStackTrace();
        //    return 0;
        // }
        
        // --- USAMOS ESTO EN SU LUGAR PARA PROBAR ---
        return (int) (System.currentTimeMillis() % 10000); // Genera un numero tonto basado en el tiempo
    }
    
}
