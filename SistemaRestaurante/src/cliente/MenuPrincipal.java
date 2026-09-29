package cliente;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());
    private String rol;
    private String usuario;

    public MenuPrincipal(String rol) {
        this(rol, "admin");
    }

    public MenuPrincipal(String rol, String usuario) {
        this.rol = rol;
        this.usuario = usuario != null ? usuario : "";
        TemaClockTower.aplicarLookAndFeel();
        initComponents();
        aplicarEstilos();
        setLocationRelativeTo(null);

        if (rol.equalsIgnoreCase("mesero")) {
            btnUsuarios.setVisible(false);
            btnProductos.setVisible(false);
            btnCambiarPin.setVisible(false);
            btnBitacora.setVisible(false);
            btnReportes.setVisible(false);
            btnBackup.setVisible(false);
        } else if (!rol.equalsIgnoreCase("admin")) {
            btnCambiarPin.setVisible(false);
            btnBitacora.setVisible(false);
            btnReportes.setVisible(false);
            btnBackup.setVisible(false);
        }
    }

    private void aplicarEstilos() {
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);

        // Logo pequeño
        ImageIcon logo = TemaClockTower.cargarLogo(90, 90);
        if (logo != null) {
            lblLogo.setIcon(logo);
            lblLogo.setText("");
        } else {
            lblLogo.setText("⏱");
            lblLogo.setFont(new Font("Segoe UI", Font.PLAIN, 36));
            lblLogo.setForeground(TemaClockTower.DORADO);
        }
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        // Títulos
        TemaClockTower.estiloTitulo(lblTitulo);
        lblTitulo.setText("CLOCK TOWER RESTAURANT");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        TemaClockTower.estiloLabelSecundario(lblBienvenida);
        lblBienvenida.setText("Bienvenido al sistema  •  Rol: " + rol.toUpperCase());
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        // Botones
        TemaClockTower.estiloBotonPrimario(btnMesas);
        btnMesas.setText("  CONSULTAR MESAS");
        btnMesas.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonSecundario(btnProductos);
        btnProductos.setText("  GESTIONAR PRODUCTOS");
        btnProductos.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonSecundario(btnUsuarios);
        btnUsuarios.setText("  GESTIONAR USUARIOS");
        btnUsuarios.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonSecundario(btnCambiarPin);
        btnCambiarPin.setText("  CAMBIAR PIN");
        btnCambiarPin.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonSecundario(btnBitacora);
        btnBitacora.setText("  BITÁCORA DE ACCESOS");
        btnBitacora.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonSecundario(btnReportes);
        btnReportes.setText("  REPORTES");
        btnReportes.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonSecundario(btnBackup);
        btnBackup.setText("  BACKUP BD");
        btnBackup.setPreferredSize(new Dimension(260, 50));

        TemaClockTower.estiloBotonPeligro(btnSalir);
        btnSalir.setText("CERRAR SESIÓN");
        btnSalir.setPreferredSize(new Dimension(160, 40));

        setSize(500, 720);
        setResizable(false);
        setTitle("Clock Tower Restaurant - Menú Principal");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblBienvenida = new javax.swing.JLabel();
        btnMesas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnCambiarPin = new javax.swing.JButton();
        btnBitacora = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnBackup = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelBotones = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CLOCK TOWER RESTAURANT");

        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida.setText("Bienvenido al sistema");

        btnMesas.setText("Consultar Mesas");
        btnMesas.addActionListener(this::btnMesasActionPerformed);

        btnProductos.setText("Gestionar productos");
        btnProductos.addActionListener(this::btnProductosActionPerformed);

        btnUsuarios.setText("Gestionar usuarios");
        btnUsuarios.addActionListener(this::btnUsuariosActionPerformed);

        btnCambiarPin.setText("Cambiar PIN");
        btnCambiarPin.addActionListener(this::btnCambiarPinActionPerformed);

        btnBitacora.setText("Bitácora de accesos");
        btnBitacora.addActionListener(this::btnBitacoraActionPerformed);

        btnReportes.setText("Reportes");
        btnReportes.addActionListener(this::btnReportesActionPerformed);

        btnBackup.setText("Backup BD");
        btnBackup.addActionListener(this::btnBackupActionPerformed);

        btnSalir.setText("Cerrar sesión");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        panelBotones.setOpaque(false);
        panelBotones.setLayout(new java.awt.GridLayout(7, 1, 0, 10));
        panelBotones.add(btnMesas);
        panelBotones.add(btnProductos);
        panelBotones.add(btnUsuarios);
        panelBotones.add(btnCambiarPin);
        panelBotones.add(btnBitacora);
        panelBotones.add(btnReportes);
        panelBotones.add(btnBackup);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBienvenida)
                .addGap(25, 25, 25)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {
        UsuariosFrame usuarios = new UsuariosFrame();
        usuarios.setLocationRelativeTo(null);
        usuarios.setVisible(true);
    }

    private void btnMesasActionPerformed(java.awt.event.ActionEvent evt) {
        MesasFrame mesas = new MesasFrame(rol);
        mesas.setLocationRelativeTo(null);
        mesas.setVisible(true);
    }

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {
        ProductosAdminFrame productos = new ProductosAdminFrame();
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        int conf = JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar sesión y volver al inicio de sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            LoginFrame login = new LoginFrame();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            this.dispose();
        }
    }

    private void btnCambiarPinActionPerformed(java.awt.event.ActionEvent evt) {
        JPasswordField txtActual = new JPasswordField(10);
        JPasswordField txtNuevo = new JPasswordField(10);
        JPasswordField txtConfirmar = new JPasswordField(10);

        JPanel panel = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("PIN actual:"));
        panel.add(txtActual);
        panel.add(new JLabel("Nuevo PIN (solo números, 4 a 6 dígitos):"));
        panel.add(txtNuevo);
        panel.add(new JLabel("Confirmar nuevo PIN:"));
        panel.add(txtConfirmar);

        int opcion = JOptionPane.showConfirmDialog(
                this, panel, "Cambiar PIN de Administrador",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion != JOptionPane.OK_OPTION) return;

        String actual = new String(txtActual.getPassword()).trim();
        String nuevo = new String(txtNuevo.getPassword()).trim();
        String confirmar = new String(txtConfirmar.getPassword()).trim();

        if (actual.isEmpty() || nuevo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nuevo.length() < 4 || nuevo.length() > 6 || !nuevo.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El nuevo PIN debe tener entre 4 y 6 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!nuevo.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "El nuevo PIN y la confirmación no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String respuesta = cliente.enviarMensaje("CHANGE_PIN|" + usuario + "|" + actual + "|" + nuevo);
        cliente.cerrarConexion();

        if (respuesta != null && respuesta.startsWith("PIN_OK|")) {
            JOptionPane.showMessageDialog(this,
                    "PIN actualizado correctamente.\nSe solicitará el nuevo PIN en el próximo inicio de sesión.",
                    "PIN actualizado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String msg = respuesta != null ? respuesta.replace("PIN_ERROR|", "") : "Error desconocido";
            JOptionPane.showMessageDialog(this, msg, "Error al cambiar PIN", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBitacoraActionPerformed(java.awt.event.ActionEvent evt) {
        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String respuesta = cliente.enviarMensaje("BITACORA|50");
        cliente.cerrarConexion();

        if (respuesta == null || !respuesta.startsWith("BITACORA_OK|")) {
            String msg = respuesta != null ? respuesta.replace("BITACORA_ERROR|", "") : "Error al consultar bitácora";
            JOptionPane.showMessageDialog(this, msg, "Bitácora", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String data = respuesta.substring("BITACORA_OK|".length());
        String[] filas = data.isEmpty() ? new String[0] : data.split(";;");

        String[] columnas = {"ID", "Usuario", "Evento", "Detalle", "Fecha"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        for (String fila : filas) {
            if (fila == null || fila.trim().isEmpty()) continue;
            String[] partes = fila.split("\\|", -1);
            if (partes.length >= 5) {
                modelo.addRow(new Object[]{partes[0], partes[1], partes[2], partes[3], partes[4]});
            }
        }

        JTable tabla = new JTable(modelo);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(700, 350));

        JOptionPane.showMessageDialog(this, scroll,
                "Bitácora de accesos (últimos " + modelo.getRowCount() + " eventos)",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {
        String[] opciones = {"Ventas del día", "Productos más vendidos", "Cancelar"};
        int sel = JOptionPane.showOptionDialog(
                this,
                "Seleccione el reporte a consultar:",
                "Reportes",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        if (sel < 0 || sel == 2) return;

        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sel == 0) {
            // Ventas del día
            String respuesta = cliente.enviarMensaje("REPORTES|ventas_dia");
            cliente.cerrarConexion();
            if (respuesta == null || !respuesta.startsWith("REPORTES_OK|VENTAS_DIA|")) {
                String msg = respuesta != null ? respuesta.replace("REPORTES_ERROR|", "") : "Error al consultar reporte";
                JOptionPane.showMessageDialog(this, msg, "Reportes", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String resto = respuesta.substring("REPORTES_OK|VENTAS_DIA|".length());
            String[] partes = resto.split(";;", 2);
            String[] resumen = partes[0].split("\\|", -1);
            String totalPedidos = resumen.length > 0 ? resumen[0] : "0";
            String totalItems = resumen.length > 1 ? resumen[1] : "0";
            String montoTotal = resumen.length > 2 ? resumen[2] : "0.00";

            String[] columnas = {"ID Pedido", "Mesa", "Fecha", "Estado", "Subtotal"};
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int r, int c) { return false; }
            };
            if (partes.length > 1 && !partes[1].isEmpty()) {
                for (String fila : partes[1].split(";;")) {
                    if (fila == null || fila.trim().isEmpty()) continue;
                    String[] p = fila.split("\\|", -1);
                    if (p.length >= 5) {
                        modelo.addRow(new Object[]{p[0], p[1], p[2], p[3], "$ " + p[4]});
                    }
                }
            }
            JTable tabla = new JTable(modelo);
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setPreferredSize(new Dimension(650, 280));

            JPanel panel = new JPanel(new BorderLayout(8, 8));
            JLabel lbl = new JLabel("<html><b>Ventas del día</b><br/>"
                    + "Pedidos: " + totalPedidos
                    + " &nbsp;|&nbsp; Ítems: " + totalItems
                    + " &nbsp;|&nbsp; Total: <span style='color:#B8860B'>$ " + montoTotal + "</span></html>");
            panel.add(lbl, BorderLayout.NORTH);
            panel.add(scroll, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(this, panel, "Reporte - Ventas del día", JOptionPane.INFORMATION_MESSAGE);

        } else {
            // Top productos
            String respuesta = cliente.enviarMensaje("REPORTES|top_productos|10");
            cliente.cerrarConexion();
            if (respuesta == null || !respuesta.startsWith("REPORTES_OK|TOP_PRODUCTOS")) {
                String msg = respuesta != null ? respuesta.replace("REPORTES_ERROR|", "") : "Error al consultar reporte";
                JOptionPane.showMessageDialog(this, msg, "Reportes", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String data = respuesta.substring("REPORTES_OK|TOP_PRODUCTOS".length());
            String[] columnas = {"ID", "Producto", "Cant. vendida", "Monto"};
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int r, int c) { return false; }
            };
            if (data.startsWith(";;")) data = data.substring(2);
            if (!data.isEmpty()) {
                for (String fila : data.split(";;")) {
                    if (fila == null || fila.trim().isEmpty()) continue;
                    String[] p = fila.split("\\|", -1);
                    if (p.length >= 4) {
                        modelo.addRow(new Object[]{p[0], p[1], p[2], "$ " + p[3]});
                    }
                }
            }
            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay ventas registradas aún.", "Productos más vendidos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JTable tabla = new JTable(modelo);
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setPreferredSize(new Dimension(550, 300));
            JOptionPane.showMessageDialog(this, scroll, "Productos más vendidos (Top 10)", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {
        int conf = JOptionPane.showConfirmDialog(this,
                "¿Crear una copia de seguridad de la base de datos?",
                "Backup BD",
                JOptionPane.YES_NO_OPTION);
        if (conf != JOptionPane.YES_OPTION) return;

        Cliente cliente = new Cliente();
        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String respuesta = cliente.enviarMensaje("BACKUP|crear");
        cliente.cerrarConexion();

        if (respuesta != null && respuesta.startsWith("BACKUP_OK|")) {
            String ruta = respuesta.substring("BACKUP_OK|".length());
            JOptionPane.showMessageDialog(this,
                    "Backup creado correctamente.\n\nUbicación:\n" + ruta,
                    "Backup exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            String msg = respuesta != null ? respuesta.replace("BACKUP_ERROR|", "") : "Error desconocido";
            JOptionPane.showMessageDialog(this, msg, "Error de backup", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        TemaClockTower.aplicarLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> new MenuPrincipal("admin", "admin").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnBitacora;
    private javax.swing.JButton btnCambiarPin;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
