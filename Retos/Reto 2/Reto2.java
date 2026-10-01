package reto2_JhanCueva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Reto2 extends JFrame {

    
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtMunicipio;
    private JTextField txtTipoCuerpo;
    private JTextField txtTipoAgua;
    private JTextField txtIrca;

    
    private JTextArea areaDatosIngresados;
    private JTextArea areaSalidas;

    
    private ArrayList<CuerpoDeAgua> listaCuerpos;

    public Reto2() {
        listaCuerpos = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Reto 2 - Lector del nivel de la calidad del agua en el Atlántico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 620);
        setLocationRelativeTo(null);
        setResizable(false);

       
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(240, 248, 255));

        
        JLabel lblTitulo = new JLabel("Lector de Calidad del Agua - Departamento del Atlántico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(0, 70, 120));
        lblTitulo.setBorder(new EmptyBorder(5, 5, 15, 5));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        
        JPanel panelEntradas = new JPanel(new GridBagLayout());
        panelEntradas.setBackground(new Color(240, 248, 255));
        panelEntradas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 160), 1),
                "Datos del Cuerpo de Agua",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(0, 70, 120)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelEntradas.add(crearLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = crearCampo();
        panelEntradas.add(txtNombre, gbc);

        
        gbc.gridx = 2; gbc.weightx = 0;
        panelEntradas.add(crearLabel("Id:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        txtId = crearCampo();
        panelEntradas.add(txtId, gbc);

        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelEntradas.add(crearLabel("Municipio:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtMunicipio = crearCampo();
        panelEntradas.add(txtMunicipio, gbc);

        
        gbc.gridx = 2; gbc.weightx = 0;
        panelEntradas.add(crearLabel("Tipo de cuerpo de agua:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        txtTipoCuerpo = crearCampo();
        panelEntradas.add(txtTipoCuerpo, gbc);

       
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelEntradas.add(crearLabel("Tipo de agua:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtTipoAgua = crearCampo();
        panelEntradas.add(txtTipoAgua, gbc);

        
        gbc.gridx = 2; gbc.weightx = 0;
        panelEntradas.add(crearLabel("IRCA:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1;
        txtIrca = crearCampo();
        panelEntradas.add(txtIrca, gbc);

        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 8));
        panelBotones.setBackground(new Color(240, 248, 255));

        JButton btnIngresar = crearBoton("Ingresar", new Color(0, 120, 180));
        JButton btnProcesar = crearBoton("Procesar", new Color(0, 140, 70));
        JButton btnLimpiar = crearBoton("Limpiar", new Color(180, 80, 0));

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarDatos();
            }
        });

        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarDatos();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTodo();
            }
        });

        panelBotones.add(btnIngresar);
        panelBotones.add(btnProcesar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        gbc.weightx = 1;
        panelEntradas.add(panelBotones, gbc);

        
        JPanel panelAreas = new JPanel(new GridLayout(1, 2, 12, 0));
        panelAreas.setBackground(new Color(240, 248, 255));

        
        JPanel panelDatos = new JPanel(new BorderLayout());
        panelDatos.setBackground(new Color(240, 248, 255));
        panelDatos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 160), 1),
                "Datos ingresados",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(0, 70, 120)));

        areaDatosIngresados = new JTextArea();
        areaDatosIngresados.setEditable(false);
        areaDatosIngresados.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaDatosIngresados.setLineWrap(true);
        areaDatosIngresados.setWrapStyleWord(true);
        JScrollPane scrollDatos = new JScrollPane(areaDatosIngresados);
        scrollDatos.setPreferredSize(new Dimension(340, 220));
        panelDatos.add(scrollDatos, BorderLayout.CENTER);

        
        JPanel panelSalidas = new JPanel(new BorderLayout());
        panelSalidas.setBackground(new Color(240, 248, 255));
        panelSalidas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 160), 1),
                "Salidas",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                new Color(0, 70, 120)));

        areaSalidas = new JTextArea();
        areaSalidas.setEditable(false);
        areaSalidas.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaSalidas.setLineWrap(true);
        areaSalidas.setWrapStyleWord(true);
        JScrollPane scrollSalidas = new JScrollPane(areaSalidas);
        scrollSalidas.setPreferredSize(new Dimension(340, 220));
        panelSalidas.add(scrollSalidas, BorderLayout.CENTER);

        panelAreas.add(panelDatos);
        panelAreas.add(panelSalidas);

        
        JPanel centro = new JPanel(new BorderLayout(10, 12));
        centro.setBackground(new Color(240, 248, 255));
        centro.add(panelEntradas, BorderLayout.NORTH);
        centro.add(panelAreas, BorderLayout.CENTER);

        panelPrincipal.add(centro, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField(12);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return campo;
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(120, 32));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    
    private void ingresarDatos() {
        try {
            String nombre = txtNombre.getText().trim();
            String idStr = txtId.getText().trim();
            String municipio = txtMunicipio.getText().trim();
            String tipoCuerpo = txtTipoCuerpo.getText().trim();
            String tipoAgua = txtTipoAgua.getText().trim();
            String ircaStr = txtIrca.getText().trim();

            if (nombre.isEmpty() || idStr.isEmpty() || municipio.isEmpty()
                    || tipoCuerpo.isEmpty() || tipoAgua.isEmpty() || ircaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor complete todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idStr);
            double irca = Double.parseDouble(ircaStr);

            if (irca < 0 || irca > 100) {
                JOptionPane.showMessageDialog(this,
                        "El valor de IRCA debe estar entre 0 y 100.",
                        "IRCA inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            CuerpoDeAgua cuerpo = new CuerpoDeAgua(nombre, id, municipio, tipoCuerpo, tipoAgua, irca);
            listaCuerpos.add(cuerpo);

            
            areaDatosIngresados.append(cuerpo.toString() + "\n");

           
            txtNombre.setText("");
            txtId.setText("");
            txtMunicipio.setText("");
            txtTipoCuerpo.setText("");
            txtTipoAgua.setText("");
            txtIrca.setText("");
            txtNombre.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Id debe ser un número entero e IRCA un número decimal válido.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void procesarDatos() {
        if (listaCuerpos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay cuerpos de agua ingresados para procesar.",
                    "Lista vacía",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder salida = new StringBuilder();

       
        for (int i = 0; i < listaCuerpos.size(); i++) {
            CuerpoDeAgua c = listaCuerpos.get(i);
            salida.append("Cuerpo de agua ").append(i + 1).append(":\n");
            salida.append("Nivel de riesgo: ").append(c.nivel()).append("\n\n");
        }

       
        int contadorMedioOInferior = 0;
        for (CuerpoDeAgua c : listaCuerpos) {
            String nivel = c.nivel();
            if (nivel.equals("MEDIO") || nivel.equals("BAJO") || nivel.equals("SIN RIESGO")) {
                contadorMedioOInferior++;
            }
        }
        salida.append("Número de cuerpos de agua con nivel de riesgo MEDIO o inferior: ")
              .append(contadorMedioOInferior).append("\n\n");

        
        StringBuilder nombresMedio = new StringBuilder();
        boolean hayMedio = false;
        for (CuerpoDeAgua c : listaCuerpos) {
            if (c.nivel().equals("MEDIO")) {
                if (hayMedio) {
                    nombresMedio.append(" ");
                }
                nombresMedio.append(c.getNombre());
                hayMedio = true;
            }
        }
        if (!hayMedio) {
            nombresMedio.append("NA");
        }
        salida.append("Nombres de los cuerpos de agua que tienen un nivel de riesgo MEDIO: ")
              .append(nombresMedio).append("\n\n");

        
        CuerpoDeAgua menorIrca = listaCuerpos.get(0);
        for (CuerpoDeAgua c : listaCuerpos) {
            if (c.getIrca() < menorIrca.getIrca()) {
                menorIrca = c;
            }
        }
        salida.append("Nombres del cuerpo de agua con la clasificación IRCA más baja encontrada y su número identificador: ")
              .append(menorIrca.getNombre()).append(" ").append(menorIrca.getIdCuerpoDeAgua());

        areaSalidas.setText(salida.toString());
    }

    
    private void limpiarTodo() {
        listaCuerpos.clear();
        areaDatosIngresados.setText("");
        areaSalidas.setText("");
        txtNombre.setText("");
        txtId.setText("");
        txtMunicipio.setText("");
        txtTipoCuerpo.setText("");
        txtTipoAgua.setText("");
        txtIrca.setText("");
        txtNombre.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usar look and feel por defecto
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reto2().setVisible(true);
            }
        });
    }
}

