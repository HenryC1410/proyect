// Nodo para cola
class Pedido {
    Pedido pedido;
    Pedido siguiente;

    Pedido(Pedido pedido) {
        this.pedido = pedido;
        this.siguiente = null;
    }
}

// Cola para pedidos
class ColaPedidos {
    private Pedido frente, finalCola;

    public void enqueue(Pedido pedido) {
        Pedido nuevoNodo = new Pedido(pedido);
        if (finalCola == null) {
            frente = finalCola = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
    }

    public Pedido dequeue() {
        if (frente == null) {
            return null;
        }
        Pedido pedido = frente.pedido;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        return pedido;
    }

    public boolean isEmpty() {
        return frente == null;
    }
}