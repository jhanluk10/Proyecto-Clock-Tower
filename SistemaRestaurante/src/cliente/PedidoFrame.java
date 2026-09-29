
package cliente;

public class PedidoFrame extends javax.swing.JFrame {

    private String rol;
    private int numeroMesa;
    private int idPedido;
    private java.util.List<Object[]> productosSeleccionados =
            new java.util.ArrayList<>();

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(PedidoFrame.class.getName());

    public PedidoFrame(String rol, int numeroMesa, int idPedido) {
        this.rol = rol;
        this.numeroMesa = numeroMesa;
        this.idPedido = idPedido;

        initComponents();

        lblMesa.setText("Mesa: " + numeroMesa);
        lblPedido.setText("Pedido: #" + idPedido);

        // ===== ESTILO CLOCK TOWER =====
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);
        setTitle("Clock Tower Restaurant - Pedido");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof javax.swing.JLabel) {
                TemaClockTower.estiloLabel((javax.swing.JLabel) c);
            }
        }
        if (lblMesa != null) TemaClockTower.estiloTitulo(lblMesa);
        if (lblPedido != null) TemaClockTower.estiloTitulo(lblPedido);
        if (tblProductos != null) TemaClockTower.estiloTabla(tblProductos);

        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof javax.swing.JButton) {
                TemaClockTower.estiloBotonSecundario((javax.swing.JButton) c);
            }
        }
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
    }

    public void actualizarProductos(java.util.List<Object[]> productos) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblProductos.getModel();

        productosSeleccionados = productos;
        modelo.setRowCount(0);

        double total = 0;
        for (Object[] producto : productos) {
            String nombre = producto[1].toString();
            int cantidad = (int) producto[2];
            double precio = (double) producto[3];
            double subtotal = (double) producto[4];

            modelo.addRow(new Object[]{nombre, cantidad, precio, subtotal});
            total += subtotal;
        }
        lblTotal.setText("Total: $" + total);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblMesa = new javax.swing.JLabel();
        lblPedido = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        btnConfirmarPedido = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("PEDIDO");

        lblMesa.setText("Mesa:");

        lblPedido.setText("Pedido:");

        btnAgregarProducto.setText("Agregar producto");
        btnAgregarProducto.addActionListener(this::btnAgregarProductoActionPerformed);

        btnConfirmarPedido.setText("Confirmar pedido");
        btnConfirmarPedido.addActionListener(this::btnConfirmarPedidoActionPerformed);

        btnCancelar.setText("Cancelar");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        lblTotal.setText("Total: $0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblMesa)
                        .addGap(531, 531, 531))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTotal)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(180, 180, 180))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPedido)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnAgregarProducto)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmarPedido)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMesa)
                        .addGap(40, 40, 40)
                        .addComponent(lblPedido))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAgregarProducto)
                    .addComponent(btnConfirmarPedido))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
ProductosFrame productos = new ProductosFrame(
                rol,
                numeroMesa,
                idPedido,
                this
        );
        productos.setLocationRelativeTo(this);
        productos.setVisible(true);
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnConfirmarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPedidoActionPerformed
 if (productosSeleccionados.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Debe agregar al menos un producto al pedido.",
                    "Pedido",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con el servidor.",
                    "Error de conexión",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        boolean guardadoCorrectamente = true;

        for (Object[] producto : productosSeleccionados) {
            int idProducto = (int) producto[0];
            int cantidad = (int) producto[2];
            double precioUnitario = (double) producto[3];

            String mensaje =
                    "DETALLE|crear|"
                    + idPedido + "|"
                    + idProducto + "|"
                    + cantidad + "|"
                    + precioUnitario;

            String respuesta = cliente.enviarMensaje(mensaje);

            if (respuesta == null || !respuesta.startsWith("DETALLE_OK|")) {
                guardadoCorrectamente = false;
                break;
            }
        }

        if (!guardadoCorrectamente) {
            cliente.cerrarConexion();
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudieron guardar todos los productos.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String mensajeCerrar = "PEDIDO|cerrar|" + idPedido;
        String respuestaCerrar = cliente.enviarMensaje(mensajeCerrar);
        cliente.cerrarConexion();

        if (respuestaCerrar != null
                && respuestaCerrar.startsWith("PEDIDO_CERRADO_OK|")) {

            double total = 0;
            for (Object[] p : productosSeleccionados) {
                total += (double) p[4];
            }

            FacturaFrame factura = new FacturaFrame(
                    numeroMesa,
                    idPedido,
                    productosSeleccionados,
                    total
            );
            factura.setLocationRelativeTo(null);
            factura.setVisible(true);

            dispose();
    
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Los productos se guardaron, "
                    + "pero no se pudo cerrar el pedido.\n\n"
                    + "Respuesta del servidor: "
                    + respuestaCerrar,
                    "Error al cerrar pedido",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int conf = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Cancelar el pedido y liberar la mesa?",
                "Cancelar pedido",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (conf != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con el servidor.",
                    "Error de conexión",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String respuesta = cliente.enviarMensaje("PEDIDO|cancelar|" + idPedido);
        cliente.cerrarConexion();

        if (respuesta != null && respuesta.startsWith("PEDIDO_CANCELADO_OK|")) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Pedido cancelado.\nLa mesa " + numeroMesa + " quedó libre.",
                    "Cancelado",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo cancelar el pedido.\n" + respuesta,
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnConfirmarPedidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
    new PedidoFrame("admin", 1, 3).setVisible(true)
);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmarPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblPedido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
