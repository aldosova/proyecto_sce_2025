/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_solicita_autorizacion;

import fachadas.ClienteFacade;
import fachadas.OperacionFacade;
import fachadas.ProveedorFacade;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.Entity;
import javax.persistence.LockModeType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author rafael
 * Bruno Daniel Pérez Vargas 204430
 */
@WebService(serviceName = "WS_Solicita_Autorizacion")
public class WS_Solicita_Autorizacion {

    @EJB
    private ClienteFacade ejbClte;
    
    @EJB
    private ProveedorFacade ejbProv;
    
    @EJB
    private OperacionFacade ejbOper;
    
    /**
     * This is a sample web service operation
     */
        
        @XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name="ClienteWS", propOrder={"id","nombre","email","tel","status"})
	public  class ClienteWS {
		
		public Integer id; public String nombre; public String email; public String tel; public String status;
		public ClienteWS() {}
		public ClienteWS(entidades.Cliente c){
        this.id = c.getId();
        this.nombre = c.getNombre();
        this.email = c.getEmail();
        this.tel = c.getTel();
        this.status = c.getStatus();
		}
	}
        
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name="ProveedorWS", propOrder={"id","nombre","email","tel","status"})
	public class ProveedorWS {
		
		public Integer id; public String nombre; public String email; public String tel; public String status;
		public ProveedorWS() {}
		public ProveedorWS(entidades.Proveedor p){
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.status = p.getStatus();
		}
	}
        
    @WebMethod(operationName = "catalogoClientes")
    public java.util.List<entidades.Cliente> catalogoClientes() {
        return ejbClte.findAll();
    }

    @WebMethod(operationName = "catalogoProveedores")
    public java.util.List<entidades.Proveedor> catalogoProveedores() {
        return ejbProv.findAll();
    }
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "solicitaAutorizacion")
    public int solicitaAutorizacion(@WebParam(name = "idClte") int idClte, @WebParam(name = "idProv") int idProv, @WebParam(name = "dblMonto") double dblMonto) {
        
        int autorizacion    = 0;
        entidades.Cliente   clte;
        entidades.Proveedor prov;
        entidades.Operacion oper;
        
        //clte= ejbClte.find(new Integer(idClte));
        clte = ejbClte.bloquea(idClte);
        
        if(clte == null)
            return 0;
        
        String st = clte.getStatus();
        if (st == null || st.isEmpty() || Character.toUpperCase(st.charAt(0)) != 'A') {
            
            return 0;
        }
        
        // clte.setFechaStatus(new java.util.Date());
        // prov.setFechaStatus(new java.util.Date());
        
        prov = ejbProv.find(idProv);
        
        if(prov == null)
            return autorizacion;
        
        if(dblMonto <= 0.0)
            return autorizacion;
        
        BigDecimal monto_solicitado = new BigDecimal(dblMonto);
        
        if( clte.getMontoMax().compareTo(monto_solicitado) == -1)
            return autorizacion;
        
        BigDecimal monto_disponible = clte.getMontoDisponible(); 
        
        if( monto_disponible.multiply(new BigDecimal(1.05)).compareTo(monto_solicitado) == -1 )
            return autorizacion;
        
        BigDecimal monto_utilizado = clte.getMontoUtilizado();
         
        clte.setMontoDisponible(monto_disponible.subtract(monto_solicitado));
        clte.setMontoUtilizado(monto_utilizado.add(monto_solicitado));
        
        ejbClte.edit(clte);
        
        autorizacion = ejbOper.siguienteFolio();
        
        oper = new entidades.Operacion();
        
        java.util.Date d = new java.util.Date();
        
        oper.setIdCliente(clte);
        oper.setIdProveedor(prov);
        oper.setMonto(monto_solicitado);
        oper.setStatusOp("A");
        oper.setFechaOp(d);
        oper.setAutorizacion(autorizacion);
        oper.setFolio(autorizacion);
        
        ejbOper.create(oper);
        
        return autorizacion;
    }
    
    @WebMethod(operationName = "solicitaAutorizacionEx")
    public int solicitaAutorizacionEx(
            @WebParam(name = "idClte") int idClte,
            @WebParam(name = "idProv") int idProv,
            @WebParam(name = "dblMonto") double dblMonto
    ) throws NegocioException {

    entidades.Cliente clte = ejbClte.bloquea(idClte);
    if (clte == null) {
        throw NegocioException.of("Cliente no encontrado: id=" + idClte);
    }

    String st = clte.getStatus();
    if (st == null || st.isEmpty() || Character.toUpperCase(st.charAt(0)) != 'A') {
        throw NegocioException.of("Cliente inactivo: id=" + idClte);
    }

    entidades.Proveedor prov = ejbProv.find(idProv);
    if (prov == null) {
        throw NegocioException.of("Proveedor no encontrado: id=" + idProv);
    }

    String stp = prov.getStatus();
    if (stp == null || stp.isEmpty() || Character.toUpperCase(stp.charAt(0)) != 'A') {
        throw NegocioException.of("Proveedor inactivo: id=" + idProv);
    }

    if (dblMonto <= 0.0) {
        throw NegocioException.of("Monto inválido: " + dblMonto);
    }

    java.math.BigDecimal monto = java.math.BigDecimal.valueOf(dblMonto);

    if (clte.getMontoMax().compareTo(monto) < 0) {
        throw NegocioException.of("Monto excede MontoMax del cliente");
    }

    java.math.BigDecimal disponible = clte.getMontoDisponible();
    if (disponible.multiply(new java.math.BigDecimal("1.05")).compareTo(monto) < 0) {
        throw NegocioException.of("Crédito insuficiente (incl. 5%)");
    }

    java.math.BigDecimal utilizado = clte.getMontoUtilizado();
    clte.setMontoDisponible(disponible.subtract(monto));
    clte.setMontoUtilizado(utilizado.add(monto));
    ejbClte.edit(clte);

    int autorizacion = ejbOper.siguienteFolio();

    entidades.Operacion oper = new entidades.Operacion();
    oper.setIdCliente(clte);
    oper.setIdProveedor(prov);
    oper.setMonto(monto);
    oper.setStatusOp("A");
    oper.setFechaOp(new java.util.Date());
    oper.setAutorizacion(autorizacion);
    oper.setFolio(autorizacion);
    ejbOper.create(oper);

    return autorizacion;
}
    
}
