package gui;

import db.Database;
import modelo.CuerpoDeAgua;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz gráfica principal del Reto 3.
 * Contiene 3 pestañas: Ingresar, Procesar datos y Editar/Eliminar.
 */
public class MainFrame extends JFrame {

    private final Database db;

    // ===== Pestaña Ingresar =====
    private JTextField txtNombreIng;
    private JTextField txtIdIng;
    private JTextField txtMunicipioIng;
    private JTextField txtTipoCuerpoIng;
    private JTextField txtTipoAguaIng;
    private JTextField txtIrcaIng;

    // ===== Pestaña Procesar =====
    private JTextArea areaDatos;
    private JTextArea areaResultados;

    // ===== Pestaña Editar/Eliminar =====
    private JTextField txtIdBuscar;
    private JTextField txtNombreEdit;
    private JTextField txtIdEdit;
    private JTextField txtMunicipioEdit;
    private JTextField txtTipoCuerpoEdit;
    private JTextField txtTipoAguaEdit;
    private JTextField txtIrcaEdit;

    public MainFrame() {
        db = new Database();
        initUI();
    }

    private void initUI() {
        setTitle("Reto 3 - Lector del nivel de la calidad del agua en el Atlántico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Ingresar", crearPanelIngresar());
        tabs.addTab("Procesar datos", crearPanelProcesar());
        tabs.addTab("Editar/Eliminar", crearPanelEditarEliminar());

        add(tabs);
    }

    // ===================== PANEL INGRESAR =====================
    private JPanel crearPanelIngresar() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new TitledBorder("Datos del cuerpo de agua"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fila 1
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombreIng = new JTextField(15);
        form.add(txtNombreIng, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("ID:"), gbc);
        gbc.gridx = 3;
        txtIdIng = new JTextField(10);
        form.add(txtIdIng, gbc);

        // Fila 2
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Municipio:"), gbc);
        gbc.gridx = 1;
        txtMunicipioIng = new JTextField(15);
        form.add(txtMunicipioIng, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("IRCA:"), gbc);
        gbc.gridx = 3;
        txtIrcaIng = new JTextField(10);
        form.add(txtIrcaIng, gbc);

        // Fila 3
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Tipo de cuerpo de agua:"), gbc);
        gbc.gridx = 1;
        txtTipoCuerpoIng = new JTextField(15);
        form.add(txtTipoCuerpoIng, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("Tipo de agua:"), gbc);
        gbc.gridx = 3;
        txtTipoAguaIng = new JTextField(10);
        form.add(txtTipoAguaIng, gbc);

        // Botón
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(140, 35));
        btnIngresar.addActionListener(e -> ingresarDatos());
        botones.add(btnIngresar);

        panel.add(form, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    private void ingresarDatos() {
        try {
            String nombre = txtNombreIng.getText().trim();
            String idStr = txtIdIng.getText().trim();
            String municipio = txtMunicipioIng.getText().trim();
            String tipoCuerpo = txtTipoCuerpoIng.getText().trim();
            String tipoAgua = txtTipoAguaIng.getText().trim();
            String ircaStr = txtIrcaIng.getText().trim();

            if (nombre.isEmpty() || idStr.isEmpty() || municipio.isEmpty()
                    || tipoCuerpo.isEmpty() || tipoAgua.isEmpty() || ircaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idStr);
            double irca = Double.parseDouble(ircaStr);

            if (irca < 0 || irca > 100) {
                JOptionPane.showMessageDialog(this, "El IRCA debe estar entre 0 y 100.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (db.existeId(id)) {
                JOptionPane.showMessageDialog(this, "Ya existe un cuerpo de agua con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CuerpoDeAgua cuerpo = new CuerpoDeAgua(nombre, id, municipio, tipoCuerpo, tipoAgua, irca);
            if (db.insertar(cuerpo)) {
                JOptionPane.showMessageDialog(this, "Registro guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposIngresar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID e IRCA deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposIngresar() {
        txtNombreIng.setText("");
        txtIdIng.setText("");
        txtMunicipioIng.setText("");
        txtTipoCuerpoIng.setText("");
        txtTipoAguaIng.setText("");
        txtIrcaIng.setText("");
    }

    // ===================== PANEL PROCESAR =====================
    private JPanel crearPanelProcesar() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Áreas de texto
        JPanel areas = new JPanel(new GridLayout(1, 2, 12, 0));

        areaDatos = new JTextArea();
        areaDatos.setEditable(false);
        areaDatos.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollDatos = new JScrollPane(areaDatos);
        scrollDatos.setBorder(new TitledBorder("Registros de la base de datos"));

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setBorder(new TitledBorder("Resultados del procesamiento"));

        areas.add(scrollDatos);
        areas.add(scrollResultados);

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JButton btnObtener = new JButton("Obtener datos");
        btnObtener.setPreferredSize(new Dimension(150, 35));
        btnObtener.addActionListener(e -> obtenerDatos());

        JButton btnProcesar = new JButton("Procesar datos");
        btnProcesar.setPreferredSize(new Dimension(150, 35));
        btnProcesar.addActionListener(e -> procesarDatos());

        botones.add(btnObtener);
        botones.add(btnProcesar);

        panel.add(areas, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    private void obtenerDatos() {
        List<CuerpoDeAgua> lista = db.obtenerTodos();
        if (lista.isEmpty()) {
            areaDatos.setText("No hay registros en la base de datos.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (CuerpoDeAgua c : lista) {
            sb.append(c.toString()).append("\n");
        }
        areaDatos.setText(sb.toString());
    }

    private void procesarDatos() {
        List<CuerpoDeAgua> lista = db.obtenerTodos();
        if (lista.isEmpty()) {
            areaResultados.setText("No hay registros para procesar.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        // 1. Nivel de riesgo de cada cuerpo de agua
        sb.append("=== NIVELES DE RIESGO ===\n");
        for (CuerpoDeAgua c : lista) {
            sb.append(c.getNombre()).append(" → ").append(c.nivel()).append("\n");
        }
        sb.append("\n");

        // 2. Cantidad de cuerpos con nivel MEDIO o inferior
        int contadorMedioOInferior = 0;
        for (CuerpoDeAgua c : lista) {
            String nivel = c.nivel();
            if (nivel.equals("MEDIO") || nivel.equals("BAJO") || nivel.equals("SIN RIESGO")) {
                contadorMedioOInferior++;
            }
        }
        sb.append("Cuerpos con riesgo MEDIO o inferior: ").append(contadorMedioOInferior).append("\n\n");

        // 3. Nombres de los cuerpos con nivel MEDIO (o NA)
        List<String> nombresMedio = new ArrayList<>();
        for (CuerpoDeAgua c : lista) {
            if (c.nivel().equals("MEDIO")) {
                nombresMedio.add(c.getNombre());
            }
        }
        sb.append("Cuerpos con riesgo MEDIO: ");
        if (nombresMedio.isEmpty()) {
            sb.append("NA");
        } else {
            sb.append(String.join(", ", nombresMedio));
        }
        sb.append("\n\n");

        // 4. Cuerpo con IRCA más baja y su ID
        CuerpoDeAgua menor = lista.get(0);
        for (CuerpoDeAgua c : lista) {
            if (c.getIrca() < menor.getIrca()) {
                menor = c;
            }
        }
        sb.append("Cuerpo con IRCA más baja: ").append(menor.getNombre())
                .append(" (ID: ").append(menor.getId()).append(")");

        areaResultados.setText(sb.toString());
    }

    // ===================== PANEL EDITAR / ELIMINAR =====================
    private JPanel crearPanelEditarEliminar() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Búsqueda
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        busqueda.setBorder(new TitledBorder("Búsqueda por ID"));
        busqueda.add(new JLabel("ID:"));
        txtIdBuscar = new JTextField(12);
        busqueda.add(txtIdBuscar);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarPorId());
        busqueda.add(btnBuscar);

        // Formulario de resultados
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new TitledBorder("Resultados de la búsqueda / Edición"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fila 1
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombreEdit = new JTextField(15);
        form.add(txtNombreEdit, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("ID:"), gbc);
        gbc.gridx = 3;
        txtIdEdit = new JTextField(10);
        txtIdEdit.setEditable(false); // El ID no se modifica
        form.add(txtIdEdit, gbc);

        // Fila 2
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Municipio:"), gbc);
        gbc.gridx = 1;
        txtMunicipioEdit = new JTextField(15);
        form.add(txtMunicipioEdit, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("IRCA:"), gbc);
        gbc.gridx = 3;
        txtIrcaEdit = new JTextField(10);
        form.add(txtIrcaEdit, gbc);

        // Fila 3
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Tipo de cuerpo de agua:"), gbc);
        gbc.gridx = 1;
        txtTipoCuerpoEdit = new JTextField(15);
        form.add(txtTipoCuerpoEdit, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("Tipo de agua:"), gbc);
        gbc.gridx = 3;
        txtTipoAguaEdit = new JTextField(10);
        form.add(txtTipoAguaEdit, gbc);

        // Botones Editar y Eliminar
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 5));
        JButton btnEditar = new JButton("Editar");
        btnEditar.setPreferredSize(new Dimension(120, 35));
        btnEditar.addActionListener(e -> editarRegistro());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setPreferredSize(new Dimension(120, 35));
        btnEliminar.addActionListener(e -> eliminarRegistro());

        botones.add(btnEditar);
        botones.add(btnEliminar);

        panel.add(busqueda, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    private void buscarPorId() {
        try {
            String idStr = txtIdBuscar.getText().trim();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(idStr);
            CuerpoDeAgua c = db.buscarPorId(id);

            if (c == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún registro con ese ID.", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEditar();
                return;
            }

            txtNombreEdit.setText(c.getNombre());
            txtIdEdit.setText(String.valueOf(c.getId()));
            txtMunicipioEdit.setText(c.getMunicipio());
            txtTipoCuerpoEdit.setText(c.getTipoCuerpoAgua());
            txtTipoAguaEdit.setText(c.getTipoAgua());
            txtIrcaEdit.setText(String.valueOf(c.getIrca()));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarRegistro() {
        try {
            String idStr = txtIdEdit.getText().trim();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero busque un registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombre = txtNombreEdit.getText().trim();
            String municipio = txtMunicipioEdit.getText().trim();
            String tipoCuerpo = txtTipoCuerpoEdit.getText().trim();
            String tipoAgua = txtTipoAguaEdit.getText().trim();
            String ircaStr = txtIrcaEdit.getText().trim();

            if (nombre.isEmpty() || municipio.isEmpty() || tipoCuerpo.isEmpty()
                    || tipoAgua.isEmpty() || ircaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idStr);
            double irca = Double.parseDouble(ircaStr);

            if (irca < 0 || irca > 100) {
                JOptionPane.showMessageDialog(this, "El IRCA debe estar entre 0 y 100.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CuerpoDeAgua cuerpo = new CuerpoDeAgua(nombre, id, municipio, tipoCuerpo, tipoAgua, irca);
            if (db.actualizar(cuerpo)) {
                JOptionPane.showMessageDialog(this, "Registro actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "IRCA debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarRegistro() {
        String idStr = txtIdEdit.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero busque un registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar este registro?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idStr);
            if (db.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEditar();
                txtIdBuscar.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCamposEditar() {
        txtNombreEdit.setText("");
        txtIdEdit.setText("");
        txtMunicipioEdit.setText("");
        txtTipoCuerpoEdit.setText("");
        txtTipoAguaEdit.setText("");
        txtIrcaEdit.setText("");
    }
}
