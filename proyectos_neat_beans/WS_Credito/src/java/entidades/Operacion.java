/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad Operacion Limpia y Corregida
 */
@Entity
@Table(name = "OPERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacion.findAll", query = "SELECT o FROM Operacion o")
    , @NamedQuery(name = "Operacion.findById", query = "SELECT o FROM Operacion o WHERE o.id = :id")
    , @NamedQuery(name = "Operacion.findByMonto", query = "SELECT o FROM Operacion o WHERE o.monto = :monto")
    , @NamedQuery(name = "Operacion.findByStatusOp", query = "SELECT o FROM Operacion o WHERE o.statusOp = :statusOp")
    , @NamedQuery(name = "Operacion.findByFechaOp", query = "SELECT o FROM Operacion o WHERE o.fechaOp = :fechaOp")
    , @NamedQuery(name = "Operacion.findByAutorizacion", query = "SELECT o FROM Operacion o WHERE o.autorizacion = :autorizacion")
    , @NamedQuery(name = "Operacion.findByFolio", query = "SELECT o FROM Operacion o WHERE o.folio = :folio")})
public class Operacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPERACION")
    private Integer id;
    
    @Column(name = "MONTO")
    private BigDecimal monto;
    
    @Column(name = "STATUS_OP")
    private String statusOp;
    
    @Column(name = "FECHA_OP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOp;
    
    @Column(name = "AUTORIZACION")
    private Integer autorizacion;
    
    @Column(name = "FOLIO")
    private Integer folio;
    
    // --- AQUÍ ESTABA EL ERROR: CORREGIMOS LAS REFERENCIAS ---
    
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne
    private Cliente idCliente;
    
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;

    public Operacion() {
    }

    public Operacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getStatusOp() {
        return statusOp;
    }

    public void setStatusOp(String statusOp) {
        this.statusOp = statusOp;
    }

    public Date getFechaOp() {
        return fechaOp;
    }

    public void setFechaOp(Date fechaOp) {
        this.fechaOp = fechaOp;
    }

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Integer autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Operacion[ id=" + id + " ]";
    }
    
}