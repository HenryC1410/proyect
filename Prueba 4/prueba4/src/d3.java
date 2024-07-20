class NodoVenta {
    int idProducto;
    String nombreProducto;
    String categoria;
    int cantidadVendida;
    double precioTotal;
    String cedulaComprador;
    String nombreComprador;
    String apellidoComprador;
    NodoVenta siguiente;

    public NodoVenta(int idProducto, String nombreProducto, String categoria, int cantidadVendida, double precioTotal,
                     String cedulaComprador, String nombreComprador, String apellidoComprador) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.cantidadVendida = cantidadVendida;
        this.precioTotal = precioTotal;
        this.cedulaComprador = cedulaComprador;
        this.nombreComprador = nombreComprador;
        this.apellidoComprador = apellidoComprador;
        this.siguiente = null;
    }
}

class PilaVentas {
    NodoVenta cima;

    public PilaVentas() {
        cima = null;
    }

    public void registrarVenta(int idProducto, String nombreProducto, String categoria, int cantidadVendida, double precioTotal,
                               String cedulaComprador, String nombreComprador, String apellidoComprador) {
        NodoVenta nuevaVenta = new NodoVenta(idProducto, nombreProducto, categoria, cantidadVendida, precioTotal,
                                             cedulaComprador, nombreComprador, apellidoComprador);
        if (cima == null) {
            cima = nuevaVenta;
        } else {
            nuevaVenta.siguiente = cima;
            cima = nuevaVenta;
        }
    }

    public void mostrarHistorial() {
        if (cima == null) {
            System.out.println("No hay registro de venta.");
            return;
        }
        NodoVenta temp = cima;
        while (temp != null) {
            System.out.println("ID Producto: " + temp.idProducto);
            System.out.println("Nombre Producto: " + temp.nombreProducto);
            System.out.println("Categoría: " + temp.categoria);
            System.out.println("Cantidad Vendida: " + temp.cantidadVendida);
            System.out.println("Precio Total: " + temp.precioTotal);
            System.out.println("Comprador: " + temp.nombreComprador + " " + temp.apellidoComprador + " - Cédula: " + temp.cedulaComprador);
            System.out.println("---------------------------");
            temp = temp.siguiente;
        }
    }
}
