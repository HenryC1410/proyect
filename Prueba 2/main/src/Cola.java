class Cola {
    private Nodo frente, fin;

    Cola() {
        this.frente = this.fin = null;
    }

    void encolar(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    Producto desencolar() {
        if (frente == null) {
            return null;
        } else {
            Producto producto = frente.producto;
            frente = frente.siguiente;
            if (frente == null) {
                fin = null;
            }
            return producto;
        }
    }

    void mostrar() {
        Nodo temp = frente;
        while (temp != null) {
            System.out.println(temp.producto);
            temp = temp.siguiente;
        }
    }
}
