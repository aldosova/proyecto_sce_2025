
package cliente_credito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para NegocioFault complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NegocioFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="causa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NegocioFault", propOrder = {
    "causa"
})
public class NegocioFault {

    protected String causa;

    /**
     * Obtiene el valor de la propiedad causa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausa() {
        return causa;
    }

    /**
     * Define el valor de la propiedad causa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausa(String value) {
        this.causa = value;
    }

}
