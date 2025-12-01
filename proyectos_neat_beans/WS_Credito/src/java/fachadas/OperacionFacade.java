/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Operacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rafael
 */
@Stateless
public class OperacionFacade extends AbstractFacade<Operacion> {

    @PersistenceContext(unitName = "Parcial_202503_02PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperacionFacade() {
        super(Operacion.class);
    }
    
    public int siguienteFolio() {
        try {
            // Sintaxis Derby
            return (Integer) em.createNativeQuery("VALUES (NEXT VALUE FOR SEC_FOLIO_OPER)").getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // (opcional) alternativa sin sequence:
    public int siguienteFolioFallback() {
        Number n = (Number) getEntityManager()
            .createQuery("SELECT COALESCE(MAX(o.folio), 0) + 1 FROM Operacion o")
            .getSingleResult();
        return n.intValue();
    }
    
}
