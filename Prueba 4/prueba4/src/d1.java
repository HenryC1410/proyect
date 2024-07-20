class NodoProducto {
    int id;
    String nombre;
    String categoria;
    int cantidad;
    double precio;
    NodoProducto siguiente;

    public NodoProducto(int id, String nombre, String categoria, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
        this.siguiente = null;
    }
}

class ListaInventario {
    NodoProducto cabeza;
    public Object primero;

    public ListaInventario() {
        cabeza = null;
    }

    public void agregarProducto(int id, String nombre, String categoria, int cantidad, double precio) {
        NodoProducto nuevo = new NodoProducto(id, nombre, categoria, cantidad, precio);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public NodoProducto buscarProducto(int id) {
        NodoProducto temp = cabeza;
        while (temp != null && temp.id != id) {
            temp = temp.siguiente;
        }
        return temp;
    }

    public void eliminarProducto(int id) {
        if (cabeza == null) return;
        if (cabeza.id == id) {
            cabeza = cabeza.siguiente;
            return;
        }
        NodoProducto temp = cabeza;
        while (temp.siguiente != null && temp.siguiente.id != id) {
            temp = temp.siguiente;
        }
        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
        }
    }
}
