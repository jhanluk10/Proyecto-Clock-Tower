
package cliente;


public class ProductosAdminFrame extends javax.swing.JFrame {
    
   private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductosAdminFrame.class.getName());
   private int idProductoSeleccionado = -1;
   public ProductosAdminFrame() {
    initComponents();
        // ===== ESTILO CLOCK TOWER =====
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);
        setTitle("Clock Tower Restaurant - Productos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof javax.swing.JLabel) TemaClockTower.estiloLabel((javax.swing.JLabel) c);
            if (c instanceof javax.swing.JButton) TemaClockTower.estiloBotonSecundario((javax.swing.JButton) c);
            if (c instanceof javax.swing.JTable) TemaClockTower.estiloTabla((javax.swing.JTable) c);
            if (c instanceof javax.swing.JTextField) TemaClockTower.estiloCampo((javax.swing.JTextField) c);
        }

    cargarProductos();
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

    String respuesta =
        cliente.enviarMensaje("PRODUCTOS|admin");

    cliente.cerrarConexion();

    if (respuesta != null
            && respuesta.startsWith("PRODUCTOS_ADMIN_OK|")) {

        String datosProductos =
                respuesta.substring("PRODUCTOS_ADMIN_OK|".length());

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel)
                        tblProductos.getModel();

        modelo.setRowCount(0);

        String[] productos =
                datosProductos.split(";");

        for (String producto : productos) {

    if (!producto.trim().isEmpty()) {

        String[] datos =
                producto.split("\\|", -1);

        if (datos.length == 6) {

            String estado =
                    datos[5].equals("1")
                            ? "Disponible"
                            : "No disponible";

            modelo.addRow(new Object[]{
                datos[0],
                datos[1],
                datos[2],
                datos[3],
                datos[4],
                estado
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbDisponibilidad = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDisponibilidad = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GESTIÓN DE PRODUCTOS");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripción:");

        jLabel4.setText("Precio:");

        jLabel5.setText("Categoría:");

        jLabel6.setText("Disponibilidad:");

        cmbDisponibilidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "No disponible" }));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", " Nombre ", " Descripción", "Precio", "Categoría ", "Disponible"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDisponibilidad.setText("Cambiar disponibilidad");
        btnDisponibilidad.addActionListener(this::btnDisponibilidadActionPerformed);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnNuevo)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGuardar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(btnDisponibilidad)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                                .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCategoria, javax.swing.GroupLayout.Alignment.LEADING))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(btnEditar)))))))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cmbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDisponibilidad)
                    .addComponent(btnVolver))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
    this.dispose();        
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCategoria.setText("");

        cmbDisponibilidad.setSelectedIndex(0);

        txtNombre.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      String nombre = txtNombre.getText().trim();
String descripcion = txtDescripcion.getText().trim();
String precioTexto = txtPrecio.getText().trim();
String categoria = txtCategoria.getText().trim();

if (nombre.isEmpty()
        || precioTexto.isEmpty()
        || categoria.isEmpty()) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Nombre, precio y categoría son obligatorios.",
            "Datos incompletos",
            javax.swing.JOptionPane.WARNING_MESSAGE
    );

    return;
}

double precio;

try {

    precio = Double.parseDouble(precioTexto);

} catch (NumberFormatException e) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "El precio debe ser un número válido.",
            "Precio",
            javax.swing.JOptionPane.WARNING_MESSAGE
    );

    return;
}

if (precio <= 0) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "El precio debe ser mayor que cero.",
            "Precio",
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

String mensaje;

if (idProductoSeleccionado == -1) {

    // CREAR PRODUCTO
    mensaje =
            "PRODUCTO|crear|"
            + nombre + "|"
            + descripcion + "|"
            + precio + "|"
            + categoria;

} else {

    // EDITAR PRODUCTO
    mensaje =
            "PRODUCTO|editar|"
            + idProductoSeleccionado + "|"
            + nombre + "|"
            + descripcion + "|"
            + precio + "|"
            + categoria;
}

String respuesta = cliente.enviarMensaje(mensaje);

cliente.cerrarConexion();

if (respuesta != null
        && respuesta.startsWith("PRODUCTO_OK|")) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            idProductoSeleccionado == -1
                    ? "Producto creado correctamente."
                    : "Producto actualizado correctamente.",
            "Producto",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
    );

    txtNombre.setText("");
    txtDescripcion.setText("");
    txtPrecio.setText("");
    txtCategoria.setText("");

    cmbDisponibilidad.setSelectedIndex(0);

    idProductoSeleccionado = -1;

    cargarProductos();

} else {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            respuesta,
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
    );
}
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       int filaSeleccionada = tblProductos.getSelectedRow();

if (filaSeleccionada == -1) {

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Debe seleccionar un producto.",
            "Editar producto",
            javax.swing.JOptionPane.WARNING_MESSAGE
    );

    return;
}

idProductoSeleccionado = Integer.parseInt(
        tblProductos.getValueAt(filaSeleccionada, 0).toString()
);

txtNombre.setText(
        tblProductos.getValueAt(filaSeleccionada, 1).toString()
);

txtDescripcion.setText(
        tblProductos.getValueAt(filaSeleccionada, 2).toString()
);

txtPrecio.setText(
        tblProductos.getValueAt(filaSeleccionada, 3).toString()
);

txtCategoria.setText(
        tblProductos.getValueAt(filaSeleccionada, 4).toString()
);

String disponibilidad =
        tblProductos.getValueAt(filaSeleccionada, 5).toString();

if (disponibilidad.equals("Disponible")) {
    cmbDisponibilidad.setSelectedIndex(0);
} else {
    cmbDisponibilidad.setSelectedIndex(1);
}

javax.swing.JOptionPane.showMessageDialog(
        this,
        "Producto cargado. Ahora puedes modificar sus datos.",
        "Editar producto",
        javax.swing.JOptionPane.INFORMATION_MESSAGE
);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
                                                       

    int fila = tblProductos.getSelectedRow();

    if (fila == -1) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecciona un producto de la tabla.",
                "Cambiar disponibilidad",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int idProducto = Integer.parseInt(
            tblProductos.getValueAt(fila, 0).toString()
    );

    String seleccion =
            cmbDisponibilidad.getSelectedItem().toString();

    int disponible;

    if (seleccion.equals("Disponible")) {
        disponible = 1;
    } else {
        disponible = 0;
    }

    int confirmacion =
            javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Cambiar la disponibilidad de este producto?",
                    "Confirmar cambio",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

    if (confirmacion != javax.swing.JOptionPane.YES_OPTION) {
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

    String respuesta =
            cliente.enviarMensaje(
                    "PRODUCTO|disponibilidad|"
                    + idProducto + "|"
                    + disponible
            );

    cliente.cerrarConexion();

    if (respuesta != null
            && respuesta.startsWith("PRODUCTO_OK|")) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Disponibilidad actualizada correctamente.",
                "Producto",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );

        cargarProductos();

    } else {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                respuesta,
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new ProductosAdminFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbDisponibilidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
