import gui.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal del Reto 3.
 * Lector del nivel de la calidad del agua en el Atlántico.
 */
public class Main {

    public static void main(String[] args) {
        // Intentar usar el look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, se usa el look and feel por defecto
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
