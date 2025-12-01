package pojo_credito;

import cliente_credito.NegocioException; // Importante

public class POJO_Credito {

    public static void main(String[] args) {
        System.out.println("=== EVIDENCIA 2.2: POJO CRÉDITO ===");

        // --- 5 CASOS OK ---
        System.out.println("\n--- [A] 5 SOLICITUDES APROBADAS ---");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Intento OK #" + i + ": ");
            // Cliente 1, Proveedor 1, Monto bajo
            testSolicitud(1, 1, 50.00); 
        }

        // --- 5 CASOS FAIL ---
        System.out.println("\n--- [B] 5 SOLICITUDES DENEGADAS ---");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Intento Fail #" + i + ": ");
            // Monto Millonario para forzar error
            testSolicitud(1, 1, 9000000.00); 
        }
    }

    private static void testSolicitud(int idClte, int idProv, double monto) {
        try {
            int folio = solicitaAutorizacionEx(idClte, idProv, monto);
            System.out.println("Autorizado. Folio: " + folio);
        } catch (NegocioException ex) {
            String causa = "Error desconocido";
            if(ex.getFaultInfo() != null) {
                causa = ex.getFaultInfo().getCausa();
            }
            System.out.println("Excepción Negocio: " + causa);
        } catch (Exception e) {
            System.out.println("Error Técnico: " + e.getMessage());
        }
    }

    // --- CONEXIÓN WS ---
    private static int solicitaAutorizacionEx(int idClte, int idProv, double dblMonto) throws NegocioException {
        cliente_credito.WSSolicitaAutorizacion_Service service = new cliente_credito.WSSolicitaAutorizacion_Service();
        cliente_credito.WSSolicitaAutorizacion port = service.getWSSolicitaAutorizacionPort();
        return port.solicitaAutorizacionEx(idClte, idProv, dblMonto);
    }
}