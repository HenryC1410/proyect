import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
    private ListaProductos inventario;
    private PilaVentas historialVentas;
    private ColaPedidos pedidos;
    private ArbolBPlus arbolBPlus;

    public Main() {
        inventario = new ListaProductos();
        historialVentas = new PilaVentas();
        pedidos = new ColaPedidos();
        arbolBPlus = new ArbolBPlus(3); // Grado del árbol B+
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.mostrarMenu();
    }

    private void mostrarMenu() {
        String[] opciones = {
            "Agregar Producto al Inventario",
            "Registrar Venta",
            "Ver Inventario",
            "Ver Historial de Ventas",
            "Ver Pedidos",
            "Buscar Productos por Categoría y Stock",
            "Salir"
        };

        while (true) {
            String seleccion = (String) JOptionPane.showInputDialog(
                null, "Seleccione una opción", "Menú Principal",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]
            );

            if (seleccion == null) {
                break;
            }

            switch (seleccion) {
                case "Agregar Producto al Inventario":
                    agregarProducto();
                    break;
                case "Registrar Venta":
                    registrarVenta();
                    break;
                case "Ver Inventario":
                    verInventario();
                    break;
                case "Ver Historial de Ventas":
                    verHistorialVentas();
                    break;
                case "Ver Pedidos":
                    verPedidos();
                    break;
                case "Buscar Productos por Categoría y Stock":
                    buscarProductosPorCategoriaYStock();
                    break;
                case "Salir":
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }

    private void agregarProducto() {
        String id = JOptionPane.showInputDialog("Ingrese el ID del producto:");
        if (inventario.existeProductoConId(id)) {
            JOptionPane.showMessageDialog(null, "Ya existe un producto con este ID.");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        String categoria = JOptionPane.showInputDialog("Ingrese la categoría del producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad en inventario:"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));

        Producto nuevoProducto = new Producto(id, nombre, categoria, cantidad, precio);
        inventario.agregarProducto(nuevoProducto);
        JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
    }

    private void registrarVenta() {
        String idProducto = JOptionPane.showInputDialog("Ingrese el ID del producto vendido:");
        Producto producto = inventario.buscarProductoPorId(idProducto);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return;
        }

        int cantidadVendida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad vendida:"));
        if (cantidadVendida > producto.getCantidad()) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock para esta venta.");
            return;
        }

        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String apellidoCliente = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
        String cedulaCliente = JOptionPane.showInputDialog("Ingrese la cédula del cliente:");

        Cliente cliente = new Cliente(nombreCliente, apellidoCliente, cedulaCliente);
        double totalVenta = cantidadVendida * producto.getPrecio();
        Venta venta = new Venta(producto, cantidadVendida, totalVenta, cliente);

        historialVentas.push(venta);
        producto.setCantidad(producto.getCantidad() - cantidadVendida);
        JOptionPane.showMessageDialog(null, "Venta registrada exitosamente.");
    }

    private void verInventario() {
        JTextArea textArea = new JTextArea(inventario.mostrarInventario());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Inventario", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verHistorialVentas() {
        JTextArea textArea = new JTextArea(historialVentas.mostrarHistorialVentas());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Historial de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verPedidos() {
        // Implementar funcionalidad para ver pedidos en cola
    }

    private void buscarProductosPorCategoriaYStock() {
        String categoria = JOptionPane.showInputDialog("Ingrese la categoría del producto:");
        int stockMin = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock mínimo:"));
        int stockMax = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock máximo:"));

        arbolBPlus.buscarProductos(categoria, stockMin, stockMax, inventario);
    }
}
