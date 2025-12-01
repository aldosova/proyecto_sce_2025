
package cliente_credito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitaAutorizacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitaAutorizacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idClte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idProv" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dblMonto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitaAutorizacion", propOrder = {
    "idClte",
    "idProv",
    "dblMonto"
})
public class SolicitaAutorizacion {

    protected int idClte;
    protected int idProv;
    protected double dblMonto;

    /**
     * Obtiene el valor de la propiedad idClte.
     * 
     */
    public int getIdClte() {
        return idClte;
    }

    /**
     * Define el valor de la propiedad idClte.
     * 
     */
    public void setIdClte(int value) {
        this.idClte = value;
    }

    /**
     * Obtiene el valor de la propiedad idProv.
     * 
     */
    public int getIdProv() {
        return idProv;
    }

    /**
     * Define el valor de la propiedad idProv.
     * 
     */
    public void setIdProv(int value) {
        this.idProv = value;
    }

    /**
     * Obtiene el valor de la propiedad dblMonto.
     * 
     */
    public double getDblMonto() {
        return dblMonto;
    }

    /**
     * Define el valor de la propiedad dblMonto.
     * 
     */
    public void setDblMonto(double value) {
        this.dblMonto = value;
    }

}
