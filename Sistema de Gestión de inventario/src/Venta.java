// Nodo para pila
class Venta {
    Venta venta;
    Venta siguiente;

    Venta(Venta venta) {
        this.venta = venta;
        this.siguiente = null;
    }

    public Venta(Producto producto, int cantidadVendida, double totalVenta, Cliente cliente) {
        //TODO Auto-generated constructor stub
    }

    public Producto getProducto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProducto'");
    }

    public Object getCantidad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCantidad'");
    }

    public Object getTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotal'");
    }
}

// Pila para historial de ventas
class PilaVentas {
    private Venta cima;

    public void push(Venta venta) {
        Venta nuevoNodo = new Venta(venta);
        if (cima == null) {
            cima = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cima;
            cima = nuevoNodo;
        }
    }

    public Venta pop() {
        if (cima == null) {
            return null;
        }
        Venta venta = cima.venta;
        cima = cima.siguiente;
        return venta;
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public String mostrarHistorialVentas() {
        if (cima == null) {
            return "No hay ventas registradas.";
        }
        StringBuilder sb = new StringBuilder();
        Venta actual = cima;
        while (actual != null) {
            sb.append("ID del Producto: ").append(actual.venta.getProducto().getId()).append("\n")
              .append("Nombre del Producto: ").append(actual.venta.getProducto().getNombre()).append("\n")
              .append("Categoría: ").append(actual.venta.getProducto().getCategoria()).append("\n")
              .append("Cantidad Vendida: ").append(actual.venta.getCantidad()).append("\n")
              .append("Valor Total: ").append(actual.venta.getTotal()).append("\n")
              .append("------------------------------------------------------\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }
}
