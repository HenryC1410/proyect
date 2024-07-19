class Producto {
    Producto producto;
    Producto siguiente;

    Producto(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    public Producto(String id, String nombre, String categoria, int cantidad, double precio) {
        //TODO Auto-generated constructor stub
    }

    public Object getCategoria() {
  
        throw new UnsupportedOperationException("Unimplemented method 'getCategoria'");
    }

    public int getCantidad() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getCantidad'");
    }

    public Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    public int getPrecio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrecio'");
    }

    public void setCantidad(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCantidad'");
    }

    public Object getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }
}

// Lista para productos
class ListaProductos {
    private Producto cabeza;

    public void agregarProducto(Producto producto) {
        Producto nuevoNodo = new Producto(producto);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Producto actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public Producto buscarProductoPorId(String id) {
        Producto actual = cabeza;
        while (actual != null) {
            if (actual.producto.getId().equals(id)) {
                return actual.producto;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public String mostrarInventario() {
        if (cabeza == null) {
            return "El inventario está vacío.";
        }
        StringBuilder sb = new StringBuilder();
        Producto actual = cabeza;
        while (actual != null) {
            sb.append(actual.producto).append("\n\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    public boolean existeProductoConId(String id) {
        Producto actual = cabeza;
        while (actual != null) {
            if (actual.producto.getId().equals(id)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}