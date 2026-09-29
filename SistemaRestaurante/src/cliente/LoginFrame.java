package cliente;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFrame.class.getName());

    public LoginFrame() {
        TemaClockTower.aplicarLookAndFeel();
        initComponents();
        aplicarEstilos();
        setLocationRelativeTo(null);
    }

    private void aplicarEstilos() {
        // Fondo principal
        TemaClockTower.aplicarFondo(this);
        getContentPane().setBackground(TemaClockTower.FONDO_PRINCIPAL);

        // Título
        TemaClockTower.estiloTitulo(lblTitulo);
        lblTitulo.setText("CLOCK TOWER RESTAURANT");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Subtítulo
        lblSubtitulo.setText("Sistema de Gestión");
        TemaClockTower.estiloLabelSecundario(lblSubtitulo);
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Labels
        TemaClockTower.estiloLabel(lblUsuario);
        TemaClockTower.estiloLabel(lblContrasena);

        // Campos
        TemaClockTower.estiloCampo(txtUsuario);
        TemaClockTower.estiloPassword(txtContrasena);

        // Botón
        TemaClockTower.estiloBotonPrimario(btnIngresar);
        btnIngresar.setText("INGRESAR");

        // Logo
        ImageIcon logo = TemaClockTower.cargarLogo(160, 160);
        if (logo != null) {
            lblLogo.setIcon(logo);
            lblLogo.setText("");
        } else {
            lblLogo.setText("⏱");
            lblLogo.setFont(new Font("Segoe UI", Font.PLAIN, 48));
            lblLogo.setForeground(TemaClockTower.DORADO);
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Tamaño y centrado
        setSize(420, 580);
        setResizable(false);
        setTitle("Clock Tower Restaurant - Inicio de Sesión");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        panelCard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CLOCK TOWER RESTAURANT");

        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Sistema de Gestión");

        lblUsuario.setText("Usuario");

        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        lblContrasena.setText("Contraseña");

        txtContrasena.addActionListener(this::txtContrasenaActionPerformed);

        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);

        panelCard.setOpaque(false);

        javax.swing.GroupLayout panelCardLayout = new javax.swing.GroupLayout(panelCard);
        panelCard.setLayout(panelCardLayout);
        panelCardLayout.setHorizontalGroup(
            panelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(lblContrasena)
                    .addComponent(txtContrasena)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelCardLayout.setVerticalGroup(
            panelCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(panelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo)
                .addGap(25, 25, 25)
                .addComponent(panelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {
        txtContrasena.requestFocus();
    }

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el usuario.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtUsuario.requestFocus();
            return;
        }

        if (contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la contraseña.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtContrasena.requestFocus();
            return;
        }

        Cliente cliente = new Cliente();

        if (!cliente.conectar()) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String mensaje = "LOGIN|" + usuario + "|" + contrasena;
        String respuesta = cliente.enviarMensaje(mensaje);

        if (respuesta != null && respuesta.startsWith("LOGIN_OK|")) {
            // Formato: LOGIN_OK|rol|HAS_PIN  o  LOGIN_OK|rol|NO_PIN
            String[] partes = respuesta.split("\\|");
            String rol = partes.length > 1 ? partes[1] : "";
            String estadoPin = partes.length > 2 ? partes[2] : "NO_PIN";

            // Solo el administrador requiere PIN
            if (rol.equalsIgnoreCase("admin")) {
                boolean pinOk = false;

                if ("NO_PIN".equals(estadoPin)) {
                    // Primera vez: configurar PIN
                    pinOk = configurarPinAdmin(cliente, usuario);
                } else {
                    // PIN ya configurado: pedir verificación
                    pinOk = verificarPinAdmin(cliente, usuario);
                }

                if (!pinOk) {
                    cliente.cerrarConexion();
                    return; // No abrir el menú si falla el PIN
                }
            }

            cliente.cerrarConexion();
            MenuPrincipal menu = new MenuPrincipal(rol, usuario);
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            this.dispose();
        } else {
            String msg = respuesta != null ? respuesta : "Error desconocido";
            // Quitar prefijos técnicos para mostrar mensaje limpio al usuario
            if (msg.startsWith("LOGIN_ERROR|")) {
                msg = msg.substring("LOGIN_ERROR|".length());
            }
            // Mensaje de bloqueo más visible
            if (msg.contains("BLOQUEADA") || msg.toLowerCase().contains("bloqueado")) {
                JOptionPane.showMessageDialog(this, msg, "⚠ Sesión bloqueada", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, msg, "Inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
            cliente.cerrarConexion();
        }
    }

    /**
     * Diálogo para que el administrador configure su PIN por primera vez.
     * Retorna true si se configuró correctamente.
     */
    private boolean configurarPinAdmin(Cliente cliente, String usuario) {
        JPasswordField txtPin = new JPasswordField(10);
        JPasswordField txtConfirmar = new JPasswordField(10);

        JPanel panel = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Como administrador debe configurar un PIN de seguridad."));
        panel.add(new JLabel("El PIN se solicitará cada vez que inicie sesión."));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Ingrese un PIN (solo números, 4 a 6 dígitos):"));
        panel.add(txtPin);
        panel.add(new JLabel("Confirme el PIN:"));
        panel.add(txtConfirmar);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Configurar PIN de Administrador",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Debe configurar un PIN para continuar como administrador.",
                    "PIN requerido",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String pin = new String(txtPin.getPassword()).trim();
        String confirmar = new String(txtConfirmar.getPassword()).trim();

        if (pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El PIN no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (pin.length() < 4 || pin.length() > 6) {
            JOptionPane.showMessageDialog(this, "El PIN debe tener entre 4 y 6 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!pin.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El PIN debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!pin.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Los PIN no coinciden. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String respuesta = cliente.enviarMensaje("SET_PIN|" + usuario + "|" + pin);
        if (respuesta != null && respuesta.startsWith("PIN_OK|")) {
            JOptionPane.showMessageDialog(this,
                    "PIN configurado correctamente.\nSe le pedirá en los próximos inicios de sesión.",
                    "PIN configurado",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(this,
                    respuesta != null ? respuesta : "Error al guardar el PIN",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Diálogo para verificar el PIN del administrador.
     * Retorna true si el PIN es correcto.
     */
    private boolean verificarPinAdmin(Cliente cliente, String usuario) {
        JPasswordField txtPin = new JPasswordField(10);

        JPanel panel = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Ingrese su PIN de administrador:"));
        panel.add(txtPin);

        int intentos = 0;
        final int MAX_INTENTOS = 3;

        while (intentos < MAX_INTENTOS) {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Verificación de PIN",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar el PIN para continuar.",
                        "PIN requerido",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }

            String pin = new String(txtPin.getPassword()).trim();
            if (pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                intentos++;
                txtPin.setText("");
                continue;
            }

            String respuesta = cliente.enviarMensaje("VERIFY_PIN|" + usuario + "|" + pin);
            if (respuesta != null && respuesta.startsWith("PIN_OK|")) {
                return true;
            }

            // Si el servidor reporta bloqueo temporal, salir de inmediato
            if (respuesta != null && respuesta.contains("bloqueado")) {
                JOptionPane.showMessageDialog(this,
                        respuesta.replace("PIN_ERROR|", ""),
                        "Usuario bloqueado",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            intentos++;
            String msg = (respuesta != null) ? respuesta.replace("PIN_ERROR|", "") : "PIN incorrecto";
            int restantes = MAX_INTENTOS - intentos;
            if (restantes > 0) {
                JOptionPane.showMessageDialog(this,
                        msg,
                        "PIN incorrecto",
                        JOptionPane.ERROR_MESSAGE);
                txtPin.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Demasiados intentos fallidos. Acceso denegado.",
                        "Acceso denegado",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {
        btnIngresar.doClick();
    }

    public static void main(String args[]) {
        TemaClockTower.aplicarLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
