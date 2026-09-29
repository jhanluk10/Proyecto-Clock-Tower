package cliente;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Tema visual unificado - Clock Tower Restaurant
 * Colores inspirados en el logo: negro profundo + dorado + blanco.
 */
public final class TemaClockTower {

    // ===== PALETA DE COLORES =====
    public static final Color FONDO_PRINCIPAL   = new Color(12, 12, 14);      // Negro casi puro
    public static final Color FONDO_PANEL       = new Color(22, 22, 26);      // Gris muy oscuro
    public static final Color FONDO_TARJETA     = new Color(30, 30, 36);      // Tarjetas
    public static final Color DORADO           = new Color(245, 197, 24);     // #F5C518 (logo)
    public static final Color DORADO_CLARO     = new Color(255, 220, 80);
    public static final Color DORADO_OSCURO    = new Color(180, 140, 10);
    public static final Color TEXTO_BLANCO     = new Color(245, 245, 245);
    public static final Color TEXTO_SECUNDARIO = new Color(180, 180, 185);
    public static final Color BORDE            = new Color(60, 60, 70);
    public static final Color EXITO            = new Color(46, 204, 113);
    public static final Color PELIGRO          = new Color(231, 76, 60);
    public static final Color ADVERTENCIA      = new Color(241, 196, 15);

    // ===== FUENTES =====
    public static final Font FUENTE_TITULO     = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FUENTE_SUBTITULO  = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_BOTON      = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FUENTE_NORMAL     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_PEQUENA    = new Font("Segoe UI", Font.PLAIN, 12);

    private TemaClockTower() {}

    // ===== MÉTODOS DE ESTILO =====

    /** Aplica el fondo principal a un JFrame */
    public static void aplicarFondo(JFrame frame) {
        frame.getContentPane().setBackground(FONDO_PRINCIPAL);
        frame.setBackground(FONDO_PRINCIPAL);
    }

    /** Estiliza un botón principal (dorado) */
    public static void estiloBotonPrimario(JButton btn) {
        btn.setFont(FUENTE_BOTON);
        btn.setBackground(DORADO);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 22, 10, 22));

        btn.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (b.getModel().isPressed()) {
                    g2.setColor(DORADO_OSCURO);
                } else if (b.getModel().isRollover()) {
                    g2.setColor(DORADO_CLARO);
                } else {
                    g2.setColor(DORADO);
                }
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 12, 12);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    /** Estiliza un botón secundario (borde dorado) */
    public static void estiloBotonSecundario(JButton btn) {
        btn.setFont(FUENTE_BOTON);
        btn.setBackground(FONDO_TARJETA);
        btn.setForeground(DORADO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DORADO, 2, true),
                new EmptyBorder(8, 18, 8, 18)
        ));
    }

    /** Estiliza un botón de peligro (rojo) */
    public static void estiloBotonPeligro(JButton btn) {
        btn.setFont(FUENTE_BOTON);
        btn.setBackground(PELIGRO);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 22, 10, 22));
    }

    /** Estiliza un label de título */
    public static void estiloTitulo(JLabel lbl) {
        lbl.setFont(FUENTE_TITULO);
        lbl.setForeground(DORADO);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /** Estiliza un label normal */
    public static void estiloLabel(JLabel lbl) {
        lbl.setFont(FUENTE_NORMAL);
        lbl.setForeground(TEXTO_BLANCO);
    }

    /** Estiliza un label secundario */
    public static void estiloLabelSecundario(JLabel lbl) {
        lbl.setFont(FUENTE_SUBTITULO);
        lbl.setForeground(TEXTO_SECUNDARIO);
    }

    /** Estiliza un campo de texto */
    public static void estiloCampo(JTextField campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setBackground(FONDO_TARJETA);
        campo.setForeground(TEXTO_BLANCO);
        campo.setCaretColor(DORADO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDE, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setSelectionColor(DORADO);
        campo.setSelectedTextColor(Color.BLACK);
    }

    /** Estiliza un campo de contraseña */
    public static void estiloPassword(JPasswordField campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setBackground(FONDO_TARJETA);
        campo.setForeground(TEXTO_BLANCO);
        campo.setCaretColor(DORADO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDE, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setSelectionColor(DORADO);
        campo.setSelectedTextColor(Color.BLACK);
    }

    /** Estiliza una tabla */
    public static void estiloTabla(JTable tabla) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.setBackground(FONDO_TARJETA);
        tabla.setForeground(TEXTO_BLANCO);
        tabla.setGridColor(BORDE);
        tabla.setSelectionBackground(DORADO);
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setRowHeight(32);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));

        JTableHeader header = tabla.getTableHeader();
        header.setFont(FUENTE_BOTON);
        header.setBackground(FONDO_PANEL);
        header.setForeground(DORADO);
        header.setReorderingAllowed(false);
        header.setBorder(new LineBorder(BORDE));

        // Renderer para celdas
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? FONDO_TARJETA : new Color(26, 26, 32));
                    c.setForeground(TEXTO_BLANCO);
                }
                setBorder(new EmptyBorder(0, 10, 0, 10));
                return c;
            }
        };
        tabla.setDefaultRenderer(Object.class, renderer);
    }

    /** Carga y escala el logo de Clock Tower */
    public static ImageIcon cargarLogo(int ancho, int alto) {
        try {
            // Buscar el logo en varias ubicaciones posibles
            String[] rutas = {
                "src/cliente/logo_clock_tower.jpg",
                "cliente/logo_clock_tower.jpg",
                "logo_clock_tower.jpg",
                System.getProperty("user.dir") + "/src/cliente/logo_clock_tower.jpg",
                System.getProperty("user.dir") + "/logo_clock_tower.jpg"
            };

            BufferedImage img = null;
            for (String ruta : rutas) {
                File f = new File(ruta);
                if (f.exists()) {
                    img = ImageIO.read(f);
                    break;
                }
            }

            // Intentar desde el classpath (útil cuando se empaqueta en JAR)
            if (img == null) {
                java.net.URL url = TemaClockTower.class.getResource("/cliente/logo_clock_tower.jpg");
                if (url != null) {
                    img = ImageIO.read(url);
                }
            }

            if (img != null) {
                Image scaled = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar el logo: " + e.getMessage());
        }
        return null;
    }

    /** Crea un panel con borde dorado redondeado (simulado) */
    public static JPanel crearPanelTarjeta() {
        JPanel panel = new JPanel();
        panel.setBackground(FONDO_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DORADO, 2, true),
                new EmptyBorder(20, 25, 20, 25)
        ));
        return panel;
    }

    /** Aplica Look & Feel Nimbus y ajusta colores globales */
    public static void aplicarLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Ajustes globales de Nimbus
            UIManager.put("control", FONDO_PRINCIPAL);
            UIManager.put("nimbusBase", new Color(30, 30, 36));
            UIManager.put("nimbusBlueGrey", new Color(40, 40, 48));
            UIManager.put("nimbusFocus", DORADO);
            UIManager.put("nimbusSelectionBackground", DORADO);
            UIManager.put("text", TEXTO_BLANCO);
            UIManager.put("nimbusLightBackground", FONDO_TARJETA);
        } catch (Exception e) {
            // Si falla, se usa el L&F por defecto
        }
    }
}
