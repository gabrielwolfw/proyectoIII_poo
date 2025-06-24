package vista;

import controlador.ControladorApp;
import modelo.EstacionServicioRecarga;
import modelo.Combustible;
import modelo.Conector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Frm_Estaciones extends JFrame {
    private ControladorApp controlador;
    private JTextField txtNombre;
    private JComboBox<String> comboCiudad;
    private JCheckBox chkElectrica;
    private JCheckBox chkPublica;
    private JTable tablaEstaciones;
    private DefaultTableModel modeloTabla;
    private int estacionSeleccionadaId = -1;

    // Para selección de combustibles y conectores
    private JList<Combustible> listaCombustibles;
    private JList<Conector> listaConectores;
    private DefaultListModel<Combustible> modeloCombustibles;
    private DefaultListModel<Conector> modeloConectores;

    public Frm_Estaciones(ControladorApp controlador) {
        this.controlador = controlador;
        initComponents();
        cargarCiudades();
        cargarCombustiblesYConectores();
        cargarEstaciones();
    }

    private void initComponents() {
        setTitle("Gestión de Estaciones de Servicio y Recarga");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        JPanel panelDatos = new JPanel(new FlowLayout());
        panelDatos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(10);
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Ciudad:"));
        comboCiudad = new JComboBox<>();
        panelDatos.add(comboCiudad);

        chkElectrica = new JCheckBox("Eléctrica");
        panelDatos.add(chkElectrica);

        chkPublica = new JCheckBox("Pública");
        panelDatos.add(chkPublica);

        // Combustibles
        panelDatos.add(new JLabel("Combustibles:"));
        modeloCombustibles = new DefaultListModel<>();
        listaCombustibles = new JList<>(modeloCombustibles);
        listaCombustibles.setVisibleRowCount(3);
        listaCombustibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollCombustibles = new JScrollPane(listaCombustibles);
        scrollCombustibles.setPreferredSize(new Dimension(120,60));
        panelDatos.add(scrollCombustibles);

        // Botón para abrir ventana de gestión de combustibles
        JButton btnGestionarCombustibles = new JButton("Gestionar Combustibles");
        btnGestionarCombustibles.addActionListener(e -> abrirVentanaCombustibles());
        panelDatos.add(btnGestionarCombustibles);

        // Conectores
        panelDatos.add(new JLabel("Conectores:"));
        modeloConectores = new DefaultListModel<>();
        listaConectores = new JList<>(modeloConectores);
        listaConectores.setVisibleRowCount(3);
        listaConectores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollConectores = new JScrollPane(listaConectores);
        scrollConectores.setPreferredSize(new Dimension(120,60));
        panelDatos.add(scrollConectores);

        // Botón para abrir ventana de gestión de conectores
        JButton btnGestionarConectores = new JButton("Gestionar Conectores");
        btnGestionarConectores.addActionListener(e -> abrirVentanaConectores());
        panelDatos.add(btnGestionarConectores);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarEstacion());
        panelDatos.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarEstacion());
        panelDatos.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarEstacion());
        panelDatos.add(btnEliminar);

        modeloTabla = new DefaultTableModel(new Object[]{
                "ID", "Nombre", "Ciudad", "Eléctrica", "Pública", "Combustibles", "Conectores"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tablaEstaciones = new JTable(modeloTabla);
        tablaEstaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEstaciones.getSelectionModel().addListSelectionListener(e -> seleccionarEstacion());

        add(panelDatos, BorderLayout.NORTH);
        add(new JScrollPane(tablaEstaciones), BorderLayout.CENTER);
    }

    private void cargarCiudades() {
        comboCiudad.removeAllItems();
        controlador.mostrarCiudades().forEach(ciudad -> comboCiudad.addItem(ciudad.getNombre()));
    }

    private void cargarCombustiblesYConectores() {
        modeloCombustibles.clear();
        for (Combustible c : controlador.mostrarCombustibles()) {
            modeloCombustibles.addElement(c);
        }
        modeloConectores.clear();
        for (Conector con : controlador.mostrarConectores()) {
            modeloConectores.addElement(con);
        }
    }

    private void cargarEstaciones() {
        modeloTabla.setRowCount(0);
        List<EstacionServicioRecarga> lista = controlador.mostrarEstaciones();
        for (EstacionServicioRecarga e : lista) {
            String nombresCombustibles = (e.getCombustibles() != null && !e.getCombustibles().isEmpty())
                    ? e.getCombustibles().toString() : "";
            String nombresConectores = (e.getConectores() != null && !e.getConectores().isEmpty())
                    ? e.getConectores().toString() : "";
            modeloTabla.addRow(new Object[]{
                    e.getId(),
                    e.getNombre(),
                    e.getCiudad(),
                    e.isEsElectrica() ? "Sí" : "No",
                    e.isEsPublica() ? "Sí" : "No",
                    nombresCombustibles,
                    nombresConectores
            });
        }
        estacionSeleccionadaId = -1;
        limpiarCampos();
    }

    private void agregarEstacion() {
        String nombre = txtNombre.getText().trim();
        String ciudad = (String) comboCiudad.getSelectedItem();
        boolean electrica = chkElectrica.isSelected();
        boolean publica = chkPublica.isSelected();
        List<Combustible> combustibles = listaCombustibles.getSelectedValuesList();
        List<Conector> conectores = listaConectores.getSelectedValuesList();

        if (nombre.isEmpty() || ciudad == null) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre y seleccione la ciudad.");
            return;
        }

        EstacionServicioRecarga estacion = new EstacionServicioRecarga(
                0, nombre, ciudad, electrica, publica,
                electrica ? null : combustibles,
                electrica ? conectores : null
        );

        boolean exito = controlador.crearEstacion(estacion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Estación agregada correctamente.");
            cargarEstaciones();
            cargarCombustiblesYConectores(); // Por si hubo cambios en otras ventanas
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar la estación.");
        }
    }

    private void modificarEstacion() {
        if (estacionSeleccionadaId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una estación en la tabla.");
            return;
        }
        String nombre = txtNombre.getText().trim();
        String ciudad = (String) comboCiudad.getSelectedItem();
        boolean electrica = chkElectrica.isSelected();
        boolean publica = chkPublica.isSelected();
        List<Combustible> combustibles = listaCombustibles.getSelectedValuesList();
        List<Conector> conectores = listaConectores.getSelectedValuesList();

        if (nombre.isEmpty() || ciudad == null) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre y seleccione la ciudad.");
            return;
        }

        EstacionServicioRecarga estacion = new EstacionServicioRecarga(
                estacionSeleccionadaId, nombre, ciudad, electrica, publica,
                electrica ? null : combustibles,
                electrica ? conectores : null
        );

        boolean exito = controlador.modificarEstacion(estacionSeleccionadaId, estacion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Estación modificada correctamente.");
            cargarEstaciones();
            cargarCombustiblesYConectores();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar la estación.");
        }
    }

    private void eliminarEstacion() {
        if (estacionSeleccionadaId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una estación en la tabla.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la estación?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            boolean exito = controlador.eliminarEstacion(estacionSeleccionadaId);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Estación eliminada correctamente.");
                cargarEstaciones();
                cargarCombustiblesYConectores();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la estación.");
            }
        }
    }

    private void seleccionarEstacion() {
        int fila = tablaEstaciones.getSelectedRow();
        if (fila != -1) {
            estacionSeleccionadaId = (int) modeloTabla.getValueAt(fila, 0);
            txtNombre.setText((String) modeloTabla.getValueAt(fila, 1));
            comboCiudad.setSelectedItem((String) modeloTabla.getValueAt(fila, 2));
            chkElectrica.setSelected("Sí".equals(modeloTabla.getValueAt(fila, 3)));
            chkPublica.setSelected("Sí".equals(modeloTabla.getValueAt(fila, 4)));
            // Combustibles y conectores: no se actualizan en la lista visual aquí (opcional)
        } else {
            estacionSeleccionadaId = -1;
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        comboCiudad.setSelectedIndex(-1);
        chkElectrica.setSelected(false);
        chkPublica.setSelected(false);
        listaCombustibles.clearSelection();
        listaConectores.clearSelection();
    }

    // Métodos para abrir las ventanas de gestión de combustibles y conectores
    private void abrirVentanaCombustibles() {
        Frm_Combustibles ventanaCombustibles = new Frm_Combustibles(this.controlador);
        ventanaCombustibles.setVisible(true);
        ventanaCombustibles.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                cargarCombustiblesYConectores();
            }
        });
    }

    private void abrirVentanaConectores() {
        Frm_Conectores ventanaConectores = new Frm_Conectores(this.controlador);
        ventanaConectores.setVisible(true);
        ventanaConectores.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                cargarCombustiblesYConectores();
            }
        });
    }

    // Método para probar la ventana directamente
    public static void main(String[] args) {
        ControladorApp controlador = new ControladorApp();
        Frm_Estaciones ventana = new Frm_Estaciones(controlador);
        ventana.setVisible(true);
    }
}