package cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FacturaFrame extends JFrame {

    private final int numeroMesa;
    private final int idPedido;
    private final List<Object[]> productos;
    private final double total;

    public FacturaFrame(int numeroMesa, int idPedido, List<Object[]> productos, double total) {
        this.numeroMesa = numeroMesa;
        this.idPedido = idPedido;
        this.productos = productos;
        this.total = total;

        setTitle("Clock Tower Restaurant - Factura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 560);
        setResizable(false);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(TemaClockTower.FONDO_PRINCIPAL);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Título
        JLabel lblTitulo = new JLabel("CLOCK TOWER RESTAURANT");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(TemaClockTower.DORADO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblFactura = new JLabel("FACTURA / TICKET");
        lblFactura.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblFactura.setForeground(Color.WHITE);
        lblFactura.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Datos
        String fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm"));

        JLabel lblInfo = new JLabel(
                "<html><center>"
                + "Pedido: #" + idPedido + " &nbsp;&nbsp; Mesa: " + numeroMesa + "<br>"
                + "Fecha: " + fecha
                + "</center></html>"
        );
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblInfo.setForeground(Color.LIGHT_GRAY);
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tabla de productos
        String[] columnas = {"Producto", "Cant.", "Precio", "Subtotal"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object[] p : productos) {
            modelo.addRow(new Object[]{
                    p[1],           // nombre
                    p[2],           // cantidad
                    p[3],           // precio
                    p[4]            // subtotal
            });
        }

        JTable tabla = new JTable(modelo);
        TemaClockTower.estiloTabla(tabla);
        tabla.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(400, 220));
        scroll.getViewport().setBackground(TemaClockTower.FONDO_TARJETA);
        scroll.setBorder(BorderFactory.createLineBorder(TemaClockTower.BORDE));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Total
        JLabel lblTotal = new JLabel("TOTAL: $" + String.format("%.0f", total));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTotal.setForeground(TemaClockTower.DORADO);
        lblTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblGracias = new JLabel("¡Gracias por su visita!");
        lblGracias.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblGracias.setForeground(Color.LIGHT_GRAY);
        lblGracias.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón cerrar
        JButton btnCerrar = new JButton("CERRAR");
        TemaClockTower.estiloBotonPrimario(btnCerrar);
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.setMaximumSize(new Dimension(160, 40));
        btnCerrar.addActionListener(e -> dispose());

        // Armar panel
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(lblFactura);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(15));
        panel.add(scroll);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblTotal);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblGracias);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnCerrar);

        setContentPane(panel);
    }
}