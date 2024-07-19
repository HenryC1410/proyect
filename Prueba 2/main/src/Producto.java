class Producto {
    int id;
    String nombre;
    int stock;
    double precio;

    Producto(int id, String nombre, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Stock: " + stock + ", Precio: " + precio;
    }
}
