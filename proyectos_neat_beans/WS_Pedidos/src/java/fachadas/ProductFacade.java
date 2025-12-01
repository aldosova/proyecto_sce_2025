/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "WS_PedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    // REGLA DE NEGOCIO: Se surte lo que hay.
    public int actualizaExistencia(int id_prod, int cant_solicitada) {
        int cant_posible = 0;
        int cant_existente;

        // Bloqueo pesimista para evitar errores si dos usuarios compran a la vez
        entidades.Product prod = em.find(entidades.Product.class, id_prod, javax.persistence.LockModeType.PESSIMISTIC_WRITE);

        if (prod == null) {
            cant_posible = 0;
        } else {
            cant_existente = prod.getExistencia(); // Asegúrate de que Product tenga este getter
            // Lógica: Surtir el mínimo entre lo que piden y lo que hay
            cant_posible = (cant_solicitada < cant_existente) ? cant_solicitada : cant_existente;
            
            cant_existente -= cant_posible;
            prod.setExistencia(cant_existente);
            this.edit(prod);
        }
        prod = null;
        return cant_posible;
    }
    
}
