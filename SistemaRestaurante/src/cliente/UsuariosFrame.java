
package cliente;

import javax.swing.JOptionPane;
public class UsuariosFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UsuariosFrame.class.getName());

    
    public UsuariosFrame() {
     initComponents();
        // ===== ESTILO CLOCK TOWER =====
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);
        setTitle("Clock Tower Restaurant - Usuarios");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof javax.swing.JLabel) TemaClockTower.estiloLabel((javax.swing.JLabel) c);
            if (c instanceof javax.swing.JButton) TemaClockTower.estiloBotonSecundario((javax.swing.JButton) c);
            if (c instanceof javax.swing.JTable) TemaClockTower.estiloTabla((javax.swing.JTable) c);
            if (c instanceof javax.swing.JTextField) TemaClockTower.estiloCampo((javax.swing.JTextField) c);
        }

     cargarUsuarios();
 }
private void cargarUsuarios() {

    Cliente cliente = new Cliente();

    if (!cliente.conectar()) {

        JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar con el servidor.",
                "Error de conexión",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }
    String respuesta = cliente.enviarMensaje("USUARIO|listar");
    cliente.cerrarConexion();
    if (respuesta == null
            || !respuesta.startsWith("USUARIOS_OK|")) {
        JOptionPane.showMessageDialog(
                this,
                respuesta,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }
    javax.swing.table.DefaultTableModel modelo =
        (javax.swing.table.DefaultTableModel) jTable2.getModel();
    modelo.setRowCount(0);
    String datos = respuesta.substring("USUARIOS_OK|".length());
    if (datos.isEmpty()) {
        return;
    }
    String[] usuarios = datos.split(";");
    for (String usuario : usuarios) {
        if (usuario.isEmpty()) {
            continue;
        }
        String[] campos = usuario.split("\\|");
        if (campos.length == 4) {
            String id = campos[0];
            String nombre = campos[1];
            String rol = campos[2];
            String estado =
                    campos[3].equals("1")
                    ? "Activo"
                    : "Inactivo";
            modelo.addRow(new Object[]{
                id,
                nombre,
                rol,
                estado
            });
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GESTIÓN DE USUARIOS");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jButton1.setText("Agregar usuario");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Volver");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Desactivar usuario");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "mesero" }));

        jLabel4.setText("Rol:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Rol", "Estado"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPasswordField1)
                                    .addComponent(jTextField1))))
                        .addContainerGap(464, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         String usuario = jTextField1.getText().trim();
    String contrasena = new String(jPasswordField1.getPassword());
    String rol = jComboBox1.getSelectedItem().toString();

    if (usuario.isEmpty() || contrasena.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Debe completar todos los campos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    Cliente cliente = new Cliente();

    if (!cliente.conectar()) {

        JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar con el servidor.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    String mensaje = "USUARIO|crear|"
            + usuario + "|"
            + contrasena + "|"
            + rol;

    String respuesta = cliente.enviarMensaje(mensaje);

    cliente.cerrarConexion();

    if (respuesta != null && respuesta.startsWith("USUARIO_OK|")) {

        JOptionPane.showMessageDialog(
                this,
                "Usuario creado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        jTextField1.setText("");
        jPasswordField1.setText("");

    } else {

        JOptionPane.showMessageDialog(
                this,
                respuesta,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.dispose();    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String usuario = jTextField1.getText().trim();

        if (usuario.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el usuario que desea desactivar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Cliente cliente = new Cliente();

        if (!cliente.conectar()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con el servidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String mensaje = "USUARIO|desactivar|" + usuario;

        String respuesta = cliente.enviarMensaje(mensaje);

        cliente.cerrarConexion();

        if (respuesta != null && respuesta.startsWith("USUARIO_OK|")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario desactivado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            jTextField1.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    respuesta,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new UsuariosFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
