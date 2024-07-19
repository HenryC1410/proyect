class Pila {
    private Nodo cima;

    Pila() {
        this.cima = null;
    }

    void push(Producto producto) {
        Nodo nuevo = new Nodo(producto);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    Producto pop() {
        if (cima == null) {
            return null;
        } else {
            Producto producto = cima.producto;
            cima = cima.siguiente;
            return producto;
        }
    }

    void mostrar() {
        Nodo temp = cima;
        while (temp != null) {
            System.out.println(temp.producto);
            temp = temp.siguiente;
        }
    }
}
