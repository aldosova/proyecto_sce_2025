package wspedido;

/**
 * Clase auxiliar para transportar datos del item solicitado.
 */
public class ClsItem {
    int id_prod;
    int cantidad;    

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}