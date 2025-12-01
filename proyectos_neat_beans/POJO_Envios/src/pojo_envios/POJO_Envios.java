package pojo_envios;

import cliente_mensajeria.Envios;

public class POJO_Envios {

    public static void main(String[] args) {
        System.out.println("=== EVIDENCIA 2.3: POJO ENVÍOS (10 Casos) ===");
        
        int idPedidoBase = 5000;
        int idCliente = 1;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Envío #" + i + ": ");
            registrarEnvio(idPedidoBase + i, idCliente);
        }
    }

    private static void registrarEnvio(int idPedido, int idCliente) {
        try {
            Envios e = registrarEnvioWS(idPedido, idCliente);
            // Mostrar fechas
            System.out.println("Pedido " + idPedido + 
                    " | Reg: " + e.getFechaRegistro() + 
                    " | Entrega: " + e.getFechaEntrega());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // --- CONEXIÓN WS ---
    private static Envios registrarEnvioWS(int idPedido, int idCliente) {
        cliente_mensajeria.WSMensajeria_Service service = new cliente_mensajeria.WSMensajeria_Service();
        cliente_mensajeria.WSMensajeria port = service.getWSMensajeriaPort();
        return port.registrarEnvio(idPedido, idCliente);
    }
}