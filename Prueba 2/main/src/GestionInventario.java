import javax.swing.*;
import java.util.Scanner;

public class GestionInventario {
    Lista inventario = new Lista();
    Pila historialVentas = new Pila();
    Cola pedidos = new Cola();
    ArbolBPlus arbolCategorias = new ArbolBPlus(3);
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GestionInventario app = new GestionInventario();
        app.menu();
    }

    void menu() {
        while (true) {
            String opcion = JOptionPane.showInputDialog(null, "1. Agregar Producto\n2. Realizar Venta\n3. Mostrar Inventario\n4. Mostrar Historial de Ventas\n5. Mostrar Pedidos\n6. Salir\nSeleccione una opción:");
            switch (opcion) {
                case "1":
                    agregarProducto();
                    break;
                case "2":
                    realizarVenta();
                    break;
                case "3":
                    mostrarInventario();
                    break;
                case "4":
                    mostrarHistorialVentas();
                    break;
                case "5":
                    mostrarPedidos();
                    break;
                case "6":
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
            }
        }
    }

    void agregarProducto() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto:"));
        String nombre = JOptionPane.showInputDialog("Ingrese nombre del producto:");
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese stock del producto:"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio del producto:"));
        Producto producto = new Producto(id, nombre, stock, precio);
        inventario.agregar(producto);
        arbolCategorias.insertar(id);
        JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
    }

    void realizarVenta() {
        String cedula = JOptionPane.showInputDialog("Ingrese cédula del comprador:");
        String nombre = JOptionPane.showInputDialog("Ingrese nombre del comprador:");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido del comprador:");
        String idCompra = cedula + System.currentTimeMillis();
        while (true) {
            int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del producto a vender:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad a vender:"));
            Nodo temp = inventario.cabeza;
            while (temp != null) {
                if (temp.producto.id == idProducto) {
                    if (temp.producto.stock >= cantidad) {
                        temp.producto.stock -= cantidad;
                        historialVentas.push(temp.producto);
                        JOptionPane.showMessageDialog(null, "Venta realizada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Stock insuficiente.");
                    }
                    break;
                }
                temp = temp.siguiente;
            }
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto para este comprador?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) {
                break;
            }
        }
    }

    void mostrarInventario() {
        JTextArea textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        Nodo temp = inventario.cabeza;
        while (temp != null) {
            textArea.append(temp.producto.toString() + "\n");
            temp = temp.siguiente;
        }
        JOptionPane.showMessageDialog(null, scrollPane, "Inventario", JOptionPane.INFORMATION_MESSAGE);
    }

    void mostrarHistorialVentas() {
        JTextArea textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        Nodo temp = historialVentas.cima;
        while (temp != null) {
            textArea.append(temp.producto.toString() + "\n");
            temp = temp.siguiente;
        }
        JOptionPane.showMessageDialog(null, scrollPane, "Historial de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }

    void mostrarPedidos() {
        JTextArea textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        Nodo temp = pedidos.frente;
        while (temp != null) {
            textArea.append(temp.producto.toString() + "\n");
            temp = temp.siguiente;
        }
        JOptionPane.showMessageDialog(null, scrollPane, "Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }
}
