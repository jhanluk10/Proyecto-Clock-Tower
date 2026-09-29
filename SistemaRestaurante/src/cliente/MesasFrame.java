package cliente;

import javax.swing.*;
import java.awt.*;

public class MesasFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MesasFrame.class.getName());
    private String rol;

    public MesasFrame(String rol) {
        this.rol = rol;
        TemaClockTower.aplicarLookAndFeel();
        initComponents();
        aplicarEstilos();
        setLocationRelativeTo(null);
        // Cargar mesas al abrir
        btnActualizar.doClick();
    }

    private void aplicarEstilos() {
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);

        TemaClockTower.estiloTitulo(lblTitulo);
        lblTitulo.setText("GESTIÓN DE MESAS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        TemaClockTower.estiloBotonPrimario(btnSeleccionarMesa);
        btnSeleccionarMesa.setText("SELECCIONAR MESA");

        TemaClockTower.estiloBotonSecundario(btnActualizar);
        btnActualizar.setText("ACTUALIZAR");

        TemaClockTower.estiloBotonSecundario(btnVolver);
        btnVolver.setText("VOLVER");

        TemaClockTower.estiloTabla(tblMesas);
        aplicarColoresEstado();

        jScrollPane1.getViewport().setBackground(TemaClockTower.FONDO_TARJETA);
        jScrollPane1.setBorder(BorderFactory.createLineBorder(TemaClockTower.BORDE));

        setSize(620, 480);
        setResizable(false);
        setTitle("Clock Tower Restaurant - Mesas");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        btnSeleccionarMesa = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("GESTIÓN DE MESAS");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Mesa", "Estado"}
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jScrollPane1.setViewportView(tblMesas);

        btnSeleccionarMesa.setText("Seleccionar mesa");
        btnSeleccionarMesa.addActionListener(this::btnSeleccionarMesaActionPerformed);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(e -> dispose());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSeleccionarMesa, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeleccionarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String respuesta = cliente.enviarMensaje("MESAS|listar");
        cliente.cerrarConexion();
        if (respuesta != null && respuesta.startsWith("MESAS_OK|")) {
            String datosMesas = respuesta.substring("MESAS_OK|".length());
            javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblMesas.getModel();
            modelo.setRowCount(0);
            String[] mesas = datosMesas.split(";");
            for (String mesa : mesas) {
                if (!mesa.trim().isEmpty()) {
                    String[] datos = mesa.split("\\|");
                    if (datos.length == 2) {
                        modelo.addRow(new Object[]{datos[0], datos[1]});
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, respuesta, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSeleccionarMesaActionPerformed(java.awt.event.ActionEvent evt) {
        int filaSeleccionada = tblMesas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una mesa.", "Mesa no seleccionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String estado = tblMesas.getValueAt(filaSeleccionada, 1).toString();
        if (!estado.equalsIgnoreCase("Libre")) {
            JOptionPane.showMessageDialog(this, "La mesa seleccionada está ocupada.", "Mesa no disponible", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numeroMesa = Integer.parseInt(tblMesas.getValueAt(filaSeleccionada, 0).toString());
        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String mensaje = "PEDIDO|crear|" + numeroMesa;
        String respuesta = cliente.enviarMensaje(mensaje);
        cliente.cerrarConexion();
        if (respuesta != null && respuesta.startsWith("PEDIDO_OK|")) {
            int idPedido = Integer.parseInt(respuesta.substring("PEDIDO_OK|".length()));
            PedidoFrame pedido = new PedidoFrame(rol, numeroMesa, idPedido);
            pedido.setLocationRelativeTo(null);
            pedido.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, respuesta, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Colorea la columna Estado: Libre = verde, Ocupada = rojo */
    private void aplicarColoresEstado() {
        tblMesas.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));

                if (isSelected) {
                    c.setBackground(TemaClockTower.DORADO);
                    c.setForeground(java.awt.Color.BLACK);
                } else {
                    String estado = value == null ? "" : value.toString().trim();
                    if (estado.equalsIgnoreCase("Libre")) {
                        c.setForeground(new java.awt.Color(46, 204, 113)); // verde
                        c.setBackground(row % 2 == 0
                                ? TemaClockTower.FONDO_TARJETA
                                : new java.awt.Color(26, 26, 32));
                    } else if (estado.equalsIgnoreCase("Ocupada") || estado.equalsIgnoreCase("Ocupado")) {
                        c.setForeground(new java.awt.Color(231, 76, 60)); // rojo
                        c.setBackground(row % 2 == 0
                                ? TemaClockTower.FONDO_TARJETA
                                : new java.awt.Color(26, 26, 32));
                    } else {
                        c.setForeground(TemaClockTower.TEXTO_BLANCO);
                        c.setBackground(row % 2 == 0
                                ? TemaClockTower.FONDO_TARJETA
                                : new java.awt.Color(26, 26, 32));
                    }
                }
                setBorder(new javax.swing.border.EmptyBorder(0, 10, 0, 10));
                return c;
            }
        });
    }

    public static void main(String args[]) {
        TemaClockTower.aplicarLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> new MesasFrame("admin").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnSeleccionarMesa;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblMesas;
    // End of variables declaration//GEN-END:variables
}
