/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_solicita_autorizacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NegocioFault", propOrder = { "causa" })
public class NegocioFault {
    private String causa;

    public NegocioFault() {}
    public NegocioFault(String causa) { this.causa = causa; }

    public String getCausa() { return causa; }
    public void setCausa(String causa) { this.causa = causa; }
}