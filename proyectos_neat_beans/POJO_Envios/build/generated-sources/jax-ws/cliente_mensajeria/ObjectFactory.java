
package cliente_mensajeria;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cliente_mensajeria package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistrarEnvioResponse_QNAME = new QName("http://ws_mensajeria/", "registrarEnvioResponse");
    private final static QName _Envios_QNAME = new QName("http://ws_mensajeria/", "envios");
    private final static QName _RegistrarEnvio_QNAME = new QName("http://ws_mensajeria/", "registrarEnvio");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cliente_mensajeria
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarEnvio }
     * 
     */
    public RegistrarEnvio createRegistrarEnvio() {
        return new RegistrarEnvio();
    }

    /**
     * Create an instance of {@link RegistrarEnvioResponse }
     * 
     */
    public RegistrarEnvioResponse createRegistrarEnvioResponse() {
        return new RegistrarEnvioResponse();
    }

    /**
     * Create an instance of {@link Envios }
     * 
     */
    public Envios createEnvios() {
        return new Envios();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarEnvioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_mensajeria/", name = "registrarEnvioResponse")
    public JAXBElement<RegistrarEnvioResponse> createRegistrarEnvioResponse(RegistrarEnvioResponse value) {
        return new JAXBElement<RegistrarEnvioResponse>(_RegistrarEnvioResponse_QNAME, RegistrarEnvioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Envios }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_mensajeria/", name = "envios")
    public JAXBElement<Envios> createEnvios(Envios value) {
        return new JAXBElement<Envios>(_Envios_QNAME, Envios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarEnvio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws_mensajeria/", name = "registrarEnvio")
    public JAXBElement<RegistrarEnvio> createRegistrarEnvio(RegistrarEnvio value) {
        return new JAXBElement<RegistrarEnvio>(_RegistrarEnvio_QNAME, RegistrarEnvio.class, null, value);
    }

}
