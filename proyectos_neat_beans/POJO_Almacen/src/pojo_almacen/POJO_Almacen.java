package pojo_almacen;

import java.util.ArrayList;
import java.util.List;
import cliente_pedidos.ClsItem; 

public class POJO_Almacen {

    public static void main(String[] args) {
        System.out.println("=== EVIDENCIA 2.1: POJO ALMACÉN ===");

        // --- CASO A: 3 Pedidos OK (Stock baja) ---
        System.out.println("\n--- [A] 3 PEDIDOS EXITOSOS ---");
        hacerPedido(1, 1, 10, false); // Cliente 1, Prod 1 (Laptop), 10 u.
        hacerPedido(1, 2, 5, false);  // Cliente 1, Prod 2 (Mouse), 5 u.
        hacerPedido(1, 3, 2, false);  // Cliente 1, Prod 3 (Teclado), 2 u.

        // --- CASO B: 3 Pedidos Cancelados (Prueba Restitución) ---
        // Aquí simulamos lo que hará el BPEL: crea el pedido y luego lo cancela por falta de fondos.
        System.out.println("\n--- [B] 3 PEDIDOS CANCELADOS (Simulación Restitución) ---");
        hacerPedido(1, 1, 20, true); 
        hacerPedido(1, 2, 20, true);
        hacerPedido(1, 3, 20, true);

        // --- CASO C: 1 Pedido Excesivo (Stock insuficiente) ---
        System.out.println("\n--- [C] STOCK INSUFICIENTE ---");
        hacerPedido(1, 1, 5000, false); // Pide 5000, hay ~1000
    }

    private static void hacerPedido(int idCliente, int idProd, int cantidad, boolean simularCancelacion) {
        try {
            List<ClsItem> lista = new ArrayList<>();
            ClsItem item = new ClsItem();
            item.setIdProd(idProd);
            item.setCantidad(cantidad);
            lista.add(item);

            System.out.println("Solicitando -> Prod: " + idProd + " Cant: " + cantidad);
            int idPedido = altaPedido(idCliente, lista);

            if (idPedido > 0) {
                System.out.println("Pedido Creado ID: " + idPedido);
                if (simularCancelacion) {
                    System.out.println("Simulando fallo Crédito -> Cancelando...");
                    boolean res = cancelarPedido(idPedido);
                    System.out.println("Restitución: " + (res ? "EXITOSA (Stock devuelto)" : "FALLIDA"));
                }
            } else {
                System.out.println("Pedido NO registrado (Stock insuficiente).");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // MÉTODOS WS CLIENT (Generados)
    private static int altaPedido(int idClte, java.util.List<cliente_pedidos.ClsItem> listaIt) {
        cliente_pedidos.WSPedido_Service service = new cliente_pedidos.WSPedido_Service();
        return service.getWSPedidoPort().altaPedido(idClte, listaIt);
    }

    private static boolean cancelarPedido(int idPedido) {
        cliente_pedidos.WSPedido_Service service = new cliente_pedidos.WSPedido_Service();
        return service.getWSPedidoPort().cancelarPedido(idPedido);
    }
}