
package cliente;


public class ProductosFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductosFrame.class.getName());
    private String rol;
    private int numeroMesa;
    private int idPedido;
    private PedidoFrame pedidoFrame;
    private java.util.List<Object[]> productosSeleccionados = new java.util.ArrayList<>();
    
    public ProductosFrame(String rol, int numeroMesa, int idPedido, PedidoFrame pedidoFrame) {

    this.rol = rol;
    this.numeroMesa = numeroMesa;
    this.idPedido = idPedido;
    this.pedidoFrame = pedidoFrame;

    initComponents();
        // ===== ESTILO CLOCK TOWER =====
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    cargarProductos();
    
}
    public java.util.List<Object[]> getProductosSeleccionados() {
    return productosSeleccionados;
}
    private void cargarProductos() {

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

    String respuesta = cliente.enviarMensaje("PRODUCTOS|listar");

    cliente.cerrarConexion();

    if (respuesta != null && respuesta.startsWith("PRODUCTOS_OK|")) {

        String datosProductos =
                respuesta.substring("PRODUCTOS_OK|".length());

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblProductos.getModel();

        modelo.setRowCount(0);

        String[] productos = datosProductos.split(";");

        for (String producto : productos) {

            if (!producto.trim().isEmpty()) {

                String[] datos = producto.split("\\|");

                if (datos.length == 4) {

                    modelo.addRow(new Object[]{
                        datos[0],
                        datos[1],
                        datos[2],
                        datos[3]
                    });
                }
            }
        }

    } else {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                respuesta,
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        spnCantidad = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("PRODUCTOS");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnAgregar.setText("Agregar al pedido");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnCancelar.setText("Volver al pedido");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar)
                            .addComponent(btnCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(lblTitulo)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       
        int filaSeleccionada = tblProductos.getSelectedRow();

if (filaSeleccionada == -1) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Debe seleccionar un producto.",
            "Producto",
            javax.swing.JOptionPane.WARNING_MESSAGE
    );

    return;
}

int cantidad = (int) spnCantidad.getValue();

if (cantidad <= 0) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "La cantidad debe ser mayor que cero.",
            "Cantidad",
            javax.swing.JOptionPane.WARNING_MESSAGE
    );

    return;
}

int idProducto = Integer.parseInt(
        tblProductos.getValueAt(filaSeleccionada, 0).toString()
);

String nombreProducto =
        tblProductos.getValueAt(filaSeleccionada, 1).toString();

double precio =
        Double.parseDouble(
                tblProductos.getValueAt(filaSeleccionada, 2).toString()
        );

double subtotal = precio * cantidad;

// Guardar producto en la lista temporal
productosSeleccionados.add(new Object[]{
    idProducto,
    nombreProducto,
    cantidad,
    precio,
    subtotal
});

javax.swing.JOptionPane.showMessageDialog(
        this,
        nombreProducto + " agregado al pedido.",
        "Producto agregado",
        javax.swing.JOptionPane.INFORMATION_MESSAGE
);

// Reiniciar cantidad
spnCantidad.setValue(1);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        pedidoFrame.actualizarProductos(productosSeleccionados);

this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() ->
    new ProductosFrame("admin", 1, 3, null).setVisible(true)
);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
