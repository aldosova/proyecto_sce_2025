/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_solicita_autorizacion;

import javax.xml.ws.WebFault;

/**
 * Excepción de negocio para el servicio.
 * Incluye una cadena 'causa' dentro de un fault bean.
 */
@WebFault(name = "NegocioFault")
public class NegocioException extends Exception {
    private static final long serialVersionUID = 1L;

    private final NegocioFault faultInfo;

    public NegocioException(String message, NegocioFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public NegocioException(String message, NegocioFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public NegocioFault getFaultInfo() { return faultInfo; }

    public static NegocioException of(String causa) {
        return new NegocioException(causa, new NegocioFault(causa));
    }
}