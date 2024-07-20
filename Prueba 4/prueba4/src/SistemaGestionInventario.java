import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SistemaGestionInventario {
    public static void main(String[] args) {
        ListaInventario inventario = new ListaInventario();
        PilaVentas historialVentas = new PilaVentas();
        ColaPedidos historialPedidos = new ColaPedidos();
        ArbolBPlus arbolProductos = new ArbolBPlus(3); // Grado mínimo del árbol B+

        while (true) {
            String menu = "1. Agregar Producto\n"
                        + "2. Realizar Venta\n"
                        + "3. Buscar Producto\n"
                        + "4. Modificar Producto\n"
                        + "5. Agregar Pedido\n"
                        + "6. Historial de Ventas\n"
                        + "7. Historial de Pedidos\n"
                        + "8. Mostrar Inventario\n"
                        + "9. Salir";
            String opcion = JOptionPane.showInputDialog(menu);
            if (opcion == null) break;
            switch (Integer.parseInt(opcion)) {
                case 1:
                    agregarProducto(inventario, arbolProductos);
                    break;
                case 2:
                    realizarVenta(inventario, historialVentas);
                    break;
                case 3:
                    buscarProducto(inventario);
                    break;
                case 4:
                    modificarProducto(inventario, arbolProductos);
                    break;
                case 5:
                    agregarPedido(historialPedidos);
                    break;
                case 6:
                    mostrarHistorialVentas(historialVentas);
                    break;
                case 7:
                    mostrarHistorialPedidos(historialPedidos);
                    break;
                case 8:
                    mostrarInventario(inventario);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }

    private static void agregarProducto(ListaInventario inventario, ArbolBPlus arbolProductos) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Producto:"));
        String nombre = JOptionPane.showInputDialog("Nombre del Producto:");
        String categoria = JOptionPane.showInputDialog("Categoría del Producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad en Stock:"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
        inventario.agregarProducto(id, nombre, categoria, cantidad, precio);
        arbolProductos.insertar(id, nombre, categoria, cantidad, precio);
    }


    private static void realizarVenta(ListaInventario inventario, PilaVentas historialVentas) {
        String cedula = JOptionPane.showInputDialog("Cédula del Comprador:");
        String nombreComprador = JOptionPane.showInputDialog("Nombre del Comprador:");
        String apellidoComprador = JOptionPane.showInputDialog("Apellido del Comprador:");
        
        boolean agregarMas = true;
        while (agregarMas) {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Producto a Vender:"));
            NodoProducto producto = inventario.buscarProducto(id);
            if (producto == null || producto.cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado o sin stock");
                return;
            }
            int cantidadVendida = Integer.parseInt(JOptionPane.showInputDialog("Cantidad Vendida:"));
            if (cantidadVendida > producto.cantidad) {
                JOptionPane.showMessageDialog(null, "Cantidad insuficiente en stock");
                return;
            }
            producto.cantidad -= cantidadVendida;
            double precioTotal = producto.precio * cantidadVendida;
            historialVentas.registrarVenta(id, producto.nombre, producto.categoria, cantidadVendida, precioTotal,
                                           cedula, nombreComprador, apellidoComprador);
            int agregarOtro = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto a la venta?", "Agregar Más", JOptionPane.YES_NO_OPTION);
            agregarMas = (agregarOtro == JOptionPane.YES_OPTION);
        }
    }
    private static void buscarProducto(ListaInventario inventario) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Producto a Buscar:"));
        NodoProducto producto = inventario.buscarProducto(id);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        } else {
            JOptionPane.showMessageDialog(null, "Cantidad en Stock: " + producto.cantidad);
        }
    }

    private static void modificarProducto(ListaInventario inventario, ArbolBPlus arbolProductos) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Producto a Modificar:"));
        NodoProducto producto = inventario.buscarProducto(id);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
            return;
        }
        String[] opciones = {"Modificar", "Eliminar"};
        int opcion = JOptionPane.showOptionDialog(null, "¿Desea modificar o eliminar el producto?",
                "Modificar/Eliminar Producto", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if (opcion == 0) {
            String nombre = JOptionPane.showInputDialog("Nuevo Nombre del Producto:", producto.nombre);
            String categoria = JOptionPane.showInputDialog("Nueva Categoría del Producto:", producto.categoria);
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Nueva Cantidad en Stock:", producto.cantidad));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo Precio:", producto.precio));
            producto.nombre = nombre;
            producto.categoria = categoria;
            producto.cantidad = cantidad;
            producto.precio = precio;
        } else if (opcion == 1) {
            inventario.eliminarProducto(id);
        }
    }

    private static void agregarPedido(ColaPedidos historialPedidos) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Producto a Pedir:"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a Pedir:"));
        historialPedidos.agregarPedido(id, cantidad);
    }

    private static void mostrarHistorialVentas(PilaVentas historialVentas) {
        if (historialVentas.cima == null) {
            JOptionPane.showMessageDialog(null, "No hay registro de venta.");
            return;
        }
        StringBuilder historial = new StringBuilder();
        NodoVenta temp = historialVentas.cima;
        while (temp != null) {
            historial.append("ID Producto: ").append(temp.idProducto).append("\n");
            historial.append("Nombre Producto: ").append(temp.nombreProducto).append("\n");
            historial.append("Categoría: ").append(temp.categoria).append("\n");
            historial.append("Cantidad Vendida: ").append(temp.cantidadVendida).append("\n");
            historial.append("Precio Total: ").append(temp.precioTotal).append("\n");
            historial.append("Comprador: ").append(temp.nombreComprador).append(" ").append(temp.apellidoComprador).append(" - Cédula: ").append(temp.cedulaComprador).append("\n");
            historial.append("---------------------------\n");
            temp = temp.siguiente;
        }
        JTextArea textArea = new JTextArea(historial.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Historial de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarHistorialPedidos(ColaPedidos historialPedidos) {
        if (historialPedidos.frente == null) {
            JOptionPane.showMessageDialog(null, "No hay ningún pedido en el historial.");
            return;
        }
        StringBuilder historial = new StringBuilder();
        NodoPedido temp = historialPedidos.frente;
        while (temp != null) {
            historial.append("ID Producto: ").append(temp.idProducto).append("\n");
            historial.append("Cantidad: ").append(temp.cantidad).append("\n");
            historial.append("---------------------------\n");
            temp = temp.siguiente;
        }
        JTextArea textArea = new JTextArea(historial.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Historial de Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarInventario(ListaInventario inventario) {
        if (inventario.cabeza == null) {
            JOptionPane.showMessageDialog(null, "No hay ningún producto en el inventario.");
            return;
        }
        StringBuilder inventarioStr = new StringBuilder();
        NodoProducto temp = inventario.cabeza;
        while (temp != null) {
            inventarioStr.append("ID: ").append(temp.id).append("\n");
            inventarioStr.append("Nombre: ").append(temp.nombre).append("\n");
            inventarioStr.append("Categoría: ").append(temp.categoria).append("\n");
            inventarioStr.append("Cantidad en Stock: ").append(temp.cantidad).append("\n");
            inventarioStr.append("Precio: ").append(temp.precio).append("\n");
            inventarioStr.append("---------------------------\n");
            temp = temp.siguiente;
        }
        JTextArea textArea = new JTextArea(inventarioStr.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Inventario", JOptionPane.INFORMATION_MESSAGE);
    }
}
