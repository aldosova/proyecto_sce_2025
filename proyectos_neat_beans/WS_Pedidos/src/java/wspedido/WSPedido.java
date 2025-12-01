package wspedido;

import fachadas.CustomerFacade;
import fachadas.CustomerOrderFacade;
import fachadas.OrderedProductFacade;
import fachadas.ProductFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "WSPedido")
public class WSPedido {

    @EJB
    private OrderedProductFacade orderedProductFacade;
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerFacade customerFacade;

    /**
     * Operación principal que procesa el pedido con validación de inventario.
     */
    @WebMethod(operationName = "altaPedido")
    public int altaPedido(@WebParam(name = "id_clte") int id_clte, 
                          @WebParam(name = "lista_it") List<ClsItem> lista_it) {
        
        List<entidades.OrderedProduct> lista_orderedProducts = new ArrayList<>();
        entidades.OrderedProduct ordered_product;
        entidades.Product prod;
        double fltMontoPedido = 0.0;
        boolean hayItemsParaSurtir = false;

        // -------------------------------------------------------------
        // FASE 1: Verificar inventario y apartar productos
        // -------------------------------------------------------------
        for (ClsItem it : lista_it) {
            // LLAMADA CLAVE: Descontamos del inventario real usando la lógica "Se surte lo que hay"
            // Retorna la cantidad que REALMENTE se pudo tomar (puede ser menor a la solicitada)
            int cantidadReal = productFacade.actualizaExistencia(it.getId_prod(), it.getCantidad());
            
            if (cantidadReal > 0) {
                hayItemsParaSurtir = true;
                prod = productFacade.find(it.getId_prod());
                
                // Preparamos el item para guardarlo luego
                ordered_product = new entidades.OrderedProduct();
                ordered_product.setProduct(prod);
                ordered_product.setQuantity((short) cantidadReal); 
                
                lista_orderedProducts.add(ordered_product);
                
                // Sumamos al monto total
                fltMontoPedido += prod.getPrice().doubleValue() * cantidadReal;
                
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Surtido -> Prod ID: " + prod.getId() + " | Solicitado: " + it.getCantidad() + " | Surtido: " + cantidadReal);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, 
                        "Stock Insuficiente -> Prod ID: " + it.getId_prod() + ". No se incluye en el pedido.");
            }
        }

        // REGLA DE NEGOCIO: Si no se pudo surtir NADA, el pedido no se registra.
        if (!hayItemsParaSurtir) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Pedido Cancelado: No hay stock para ningún item.");
            return 0;
        }

        // -------------------------------------------------------------
        // FASE 2: Crear el Encabezado del Pedido (CustomerOrder)
        // -------------------------------------------------------------
        entidades.Customer clte = customerFacade.find(id_clte);
        if (clte == null) {
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Cliente no existe: " + id_clte);
             return 0; 
        }

        entidades.CustomerOrder customer_order = new entidades.CustomerOrder();
        customer_order.setCustomerId(clte);
        customer_order.setDateCreated(new java.util.Date());
        customer_order.setAmount(new BigDecimal(fltMontoPedido));
        
        // Usamos la secuencia para el número de confirmación
        int confNum = customerOrderFacade.next_conf_number();
        customer_order.setConfirmationNumber(confNum);
        
        customerOrderFacade.create(customer_order);
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Pedido creado. ID: " + customer_order.getId() + " Conf#: " + confNum);

        // -------------------------------------------------------------
        // FASE 3: Guardar los detalles (OrderedProduct)
        // -------------------------------------------------------------
        entidades.OrderedProductPK oppk;
        for (entidades.OrderedProduct op : lista_orderedProducts) {
            oppk = new entidades.OrderedProductPK();
            oppk.setCustomerOrderId(customer_order.getId());
            oppk.setProductId(op.getProduct().getId());
            
            op.setOrderedProductPK(oppk);
            op.setCustomerOrder(customer_order); // Relación bidireccional
            
            orderedProductFacade.create(op);
        }

        return customer_order.getId(); // Retornamos el ID generado
    }

    // Métodos auxiliares para probar catálogos (Opcionales pero útiles)
    @WebMethod(operationName = "catalogoCltes")
    public List<entidades.Customer> catalogoCltes() {
        return customerFacade.findAll();
    }

    @WebMethod(operationName = "catalogoProds")
    public List<entidades.Product> catalogoProds() {
        return productFacade.findAll();
    }
    
    /**
     * Método para cancelar pedido y restituir stock (REGLA DE NEGOCIO C y Evidencia 3)
     * Se usa cuando el crédito es rechazado.
     */
    @WebMethod(operationName = "cancelarPedido")
    public boolean cancelarPedido(@WebParam(name = "idPedido") int idPedido) {
        try {
            // 1. Buscar el pedido
            entidades.CustomerOrder order = customerOrderFacade.find(idPedido);
            
            if (order == null) return false;

            // 2. Marcar status "NO_PROCEDE" 
            order.setStatus("NO_PROCEDE");
            customerOrderFacade.edit(order);
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, 
                    "Iniciando restitución para Pedido ID: " + idPedido);

            // 3. Restituir productos (Regresar cantidades a disponibles) [cite: 51]
            // Obtenemos la lista de productos ordenados asociados a este pedido
            // NOTA: Asegúrate que tu Entity CustomerOrder tenga la relación getOrderedProductCollection()
            // Si no la tienes accesible fácil, podemos buscar por query. 
            // Usaremos una lógica segura buscando en la tabla de relación:
            
            List<entidades.OrderedProduct> todosLosItems = orderedProductFacade.findAll();
            
            for (entidades.OrderedProduct item : todosLosItems) {
                // Buscamos los items que pertenezcan a este pedido
                if (item.getCustomerOrder().getId() == idPedido) {
                    entidades.Product prod = item.getProduct();
                    int cantidadADevolver = item.getQuantity();
                    
                    // Sumamos al stock actual
                    int stockActual = prod.getExistencia();
                    prod.setExistencia(stockActual + cantidadADevolver);
                    
                    productFacade.edit(prod);
                    
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, 
                            "Restituido Prod: " + prod.getName() + "Cant: " + cantidadADevolver);
                }
            }
            return true;
            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al cancelar", e);
            return false;
        }
    }
}