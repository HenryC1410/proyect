class Nodo {
    Producto producto;
    Nodo siguiente;

    Nodo(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }
}

class Lista {
    Nodo cabeza;

    Lista() {
        this.cabeza = null;
    }

    void agregar(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    void mostrar() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.producto);
            temp = temp.siguiente;
        }
    }
}
