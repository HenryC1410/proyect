public class Producto1 {
    private String id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;

    public Producto1(String id, String nombre, String categoria, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nNombre: %s\nCategoría: %s\nCantidad: %d\nPrecio: %.2f", id, nombre, categoria, cantidad, precio);
    }
}


