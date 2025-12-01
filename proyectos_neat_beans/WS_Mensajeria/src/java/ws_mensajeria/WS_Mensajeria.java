package ws_mensajeria;

import entidades.Envios;
import fachadas.EnviosFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Servicio de Mensajería.
 * Requisito: Guardar pedido, cliente, fechas y sumar días aleatorios (1-7).
 */
@WebService(serviceName = "WS_Mensajeria")
public class WS_Mensajeria {

    @EJB
    private EnviosFacade ejbRef; // Referencia al Facade generado por NetBeans

    @WebMethod(operationName = "registrarEnvio")
    public Envios registrarEnvio(@WebParam(name = "idPedido") int idPedido, 
                                 @WebParam(name = "idCliente") int idCliente) {
        
        // 1. Obtener fecha actual
        Date fechaRegistro = new Date(); 

        // 2. Calcular fecha entrega (Random 1-7 días)
        Date fechaEntrega = calcularFechaEntrega(fechaRegistro);

        // 3. Crear y llenar la entidad
        Envios envio = new Envios();
        envio.setIdPedido(idPedido);
        envio.setIdCliente(idCliente);
        envio.setFechaRegistro(fechaRegistro);
        envio.setFechaEntrega(fechaEntrega);

        // 4. Guardar en Base de Datos
        ejbRef.create(envio);

        // 5. Retornar el objeto guardado (útil para ver la fecha generada)
        return envio; 
    }

    /**
     * Método privado auxiliar para sumar días laborables/aleatorios
     */
    private Date calcularFechaEntrega(Date fechaInicio) {
        Random rand = new Random();
        // REGLA EXAMEN: Entre 5 y 10 días 
        // nextInt(6) genera 0..5. Al sumarle 5, el rango final es 5..10.
        int diasAleatorios = rand.nextInt(6) + 5; 
        
        Calendar c = Calendar.getInstance();
        c.setTime(fechaInicio);
        c.add(Calendar.DATE, diasAleatorios);
        
        return c.getTime();
    }
}