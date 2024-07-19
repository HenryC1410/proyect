// Clase Nodo para Árbol B+
class NodoBPlus {
    int[] claves;
    NodoBPlus[] hijos;
    int numClaves;
    boolean esHoja;

    public NodoBPlus(int grado, boolean esHoja) {
        this.claves = new int[2 * grado - 1];
        this.hijos = new NodoBPlus[2 * grado];
        this.numClaves = 0;
        this.esHoja = esHoja;
    }
}

// Clase Árbol B+
class ArbolBPlus {
    private NodoBPlus raiz;
    private int grado;

    public ArbolBPlus(int grado) {
        this.raiz = null;
        this.grado = grado;
    }

    // Método para buscar productos por categoría y niveles de stock
    public void buscarProductos(String categoria, int stockMin, int stockMax, ListaProductos listaProductos) {
        buscarProductosRec(raiz, categoria, stockMin, stockMax, listaProductos);
    }

    private void buscarProductosRec(NodoBPlus nodo, String categoria, int stockMin, int stockMax, ListaProductos listaProductos) {
        if (nodo == null) {
            return;
        }
        for (int i = 0; i < nodo.numClaves; i++) {
            Producto producto = listaProductos.buscarProductoPorId(String.valueOf(nodo.claves[i]));
            if (producto != null && producto.getCategoria().equals(categoria) && producto.getCantidad() >= stockMin && producto.getCantidad() <= stockMax) {
                // Mostrar producto (puedes implementar una función que recoja estos productos y los muestre)
                System.out.println(producto);
            }
        }
        if (!nodo.esHoja) {
            for (int i = 0; i <= nodo.numClaves; i++) {
                buscarProductosRec(nodo.hijos[i], categoria, stockMin, stockMax, listaProductos);
            }
        }
    }
}