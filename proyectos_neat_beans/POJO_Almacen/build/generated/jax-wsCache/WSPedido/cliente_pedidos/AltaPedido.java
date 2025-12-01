
package cliente_pedidos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para altaPedido complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="altaPedido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_clte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lista_it" type="{http://wspedido/}clsItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "altaPedido", propOrder = {
    "idClte",
    "listaIt"
})
public class AltaPedido {

    @XmlElement(name = "id_clte")
    protected int idClte;
    @XmlElement(name = "lista_it")
    protected List<ClsItem> listaIt;

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
     * Gets the value of the listaIt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaIt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaIt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClsItem }
     * 
     * 
     */
    public List<ClsItem> getListaIt() {
        if (listaIt == null) {
            listaIt = new ArrayList<ClsItem>();
        }
        return this.listaIt;
    }

}
