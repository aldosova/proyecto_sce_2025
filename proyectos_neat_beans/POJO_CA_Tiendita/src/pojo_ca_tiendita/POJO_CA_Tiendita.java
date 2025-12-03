/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_ca_tiendita;

/**
 *
 * @author bruno
 */

public class POJO_CA_Tiendita {

    public static void main(String[] args) {
        System.out.println("=== EVIDENCIA 6: CARGA MASIVA (20 PEDIDOS) ===");
        
        // Simulamos 20 peticiones seguidas
        for (int i = 1; i <= 20; i++) {
            try {
                String idCliente = String.valueOf(i);
                
                System.out.print("Enviando Pedido #" + i + " (Cliente " + idCliente + ")... ");
                
              
                String respuesta = procesarCompra(idCliente);
                
                System.out.println("Respuesta: " + respuesta);
                
              
                Thread.sleep(100); 
                
            } catch (Exception e) {
                System.out.println("ERROR en Pedido #" + i + ": " + e.getMessage());
            }
        }
        System.out.println("=== PROCESO TERMINADO ===");
    }


    private static String procesarCompra(java.lang.String datosEntrada) {
        cliente_ca.TienditaInterfaceService service = new cliente_ca.TienditaInterfaceService();
        cliente_ca.TienditaInterfacePortType port = service.getTienditaInterfacePort();
      
        javax.xml.ws.Holder<String> parametro = new javax.xml.ws.Holder<>(datosEntrada);
      
        port.procesarCompra(parametro);
        
        return parametro.value;
    }
}
