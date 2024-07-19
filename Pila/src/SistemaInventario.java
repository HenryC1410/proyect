import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Clase Producto
class Producto {
    String nombre;
    String categoria;
    int stock;
    double precio;

    Producto(String nombre, String categoria, int stock, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", Categoría: " + categoria + ", Stock: " + stock + ", Precio: $" + precio;
    }
}

// Nodo para Lista
class NodoLista {
    Producto producto;
    NodoLista siguiente;

    NodoLista(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }
}

// Lista para el Inventario
class Lista {
    NodoLista cabeza;

    void agregar(Producto producto) {
        NodoLista nuevoNodo = new NodoLista(producto);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    void mostrar() {
        NodoLista actual = cabeza;
        while (actual != null) {
            System.out.println(actual.producto);
            actual = actual.siguiente;
        }
    }

    Producto buscar(String nombre) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.producto.nombre.equals(nombre)) {
                return actual.producto;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    boolean eliminar(String nombre) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.producto.nombre.equals(nombre)) {
            cabeza = cabeza.siguiente;
            return true;
        }
        NodoLista actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.producto.nombre.equals(nombre)) {
            actual = actual.siguiente;
        }
        if (actual.siguiente == null) {
            return false;
        }
        actual.siguiente = actual.siguiente.siguiente;
        return true;
    }
}

// Clase Inventario
class Inventario {
    Lista productos = new Lista();
    Stack<Producto> historialVentas = new Stack<>();
    Queue<Producto> pedidos = new LinkedList<>();

    void agregarProducto(String nombre, String categoria, int stock, double precio) {
        productos.agregar(new Producto(nombre, categoria, stock, precio));
    }

    void mostrarInventario() {
        productos.mostrar();
    }

    void realizarVenta(String nombre) {
        Producto producto = productos.buscar(nombre);
        if (producto != null && producto.stock > 0) {
            producto.stock--;
            historialVentas.push(producto);
            System.out.println("Venta realizada: " + producto.nombre);
        } else {
            System.out.println("Producto no disponible o fuera de stock.");
        }
    }

    void mostrarHistorialVentas() {
        for (Producto producto : historialVentas) {
            System.out.println(producto);
        }
    }

    void agregarPedido(String nombre) {
        Producto producto = productos.buscar(nombre);
        if (producto != null) {
            pedidos.add(producto);
            System.out.println("Pedido agregado: " + producto.nombre);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    void mostrarPedidos() {
        for (Producto producto : pedidos) {
            System.out.println(producto);
        }
    }
}

// Clase Principal
public class SistemaInventario {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú del Sistema de Inventario:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Inventario");
            System.out.println("3. Realizar Venta");
            System.out.println("4. Mostrar Historial de Ventas");
            System.out.println("5. Agregar Pedido");
            System.out.println("6. Mostrar Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del Producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Categoría: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    inventario.agregarProducto(nombre, categoria, stock, precio);
                    break;
                case 2:
                    inventario.mostrarInventario();
                    break;
                case 3:
                    System.out.print("Nombre del Producto a vender: ");
                    nombre = scanner.nextLine();
                    inventario.realizarVenta(nombre);
                    break;
                case 4:
                    inventario.mostrarHistorialVentas();
                    break;
                case 5:
                    System.out.print("Nombre del Producto para pedido: ");
                    nombre = scanner.nextLine();
                    inventario.agregarPedido(nombre);
                    break;
                case 6:
                    inventario.mostrarPedidos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
