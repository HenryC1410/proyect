class NodoPedido {
    int idProducto;
    int cantidad;
    NodoPedido siguiente;
    public Object nombreProducto;
    public Object categoria;

    public NodoPedido(int idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.siguiente = null;
    }
}

class ColaPedidos {
    NodoPedido frente;
    NodoPedido fondo;

    public ColaPedidos() {
        frente = null;
        fondo = null;
    }

    public void agregarPedido(int idProducto, int cantidad) {
        NodoPedido nuevoPedido = new NodoPedido(idProducto, cantidad);
        if (fondo == null) {
            frente = nuevoPedido;
            fondo = nuevoPedido;
        } else {
            fondo.siguiente = nuevoPedido;
            fondo = nuevoPedido;
        }
    }

    public void mostrarHistorialPedidos() {
        if (frente == null) {
            System.out.println("No hay ningún pedido en el historial.");
            return;
        }
        NodoPedido temp = frente;
        while (temp != null) {
            System.out.println("ID Producto: " + temp.idProducto);
            System.out.println("Cantidad: " + temp.cantidad);
            System.out.println("---------------------------");
            temp = temp.siguiente;
        }
    }

    public void agregarPedido(int id, int cantidad, String nombre, String categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPedido'");
    }
}
