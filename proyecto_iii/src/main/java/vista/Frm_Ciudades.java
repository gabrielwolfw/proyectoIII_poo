package vista;

import controlador.ControladorApp;
import modelo.Ciudad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Frm_Ciudades extends JFrame {
    private ControladorApp controlador;
    private JTextField txtNombreCiudad;
    private JTextField txtDescripcionCiudad;
    private JTable tablaCiudades;
    private DefaultTableModel modeloTabla;
    private int ciudadSeleccionadaId = -1;

    public Frm_Ciudades(ControladorApp controlador) {
        this.controlador = controlador;
        initComponents();
        cargarCiudades();
    }

    private void initComponents() {
        setTitle("Gestión de Ciudades");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(new JLabel("Nombre ciudad:"));
        txtNombreCiudad = new JTextField(12);
        panelSuperior.add(txtNombreCiudad);

        panelSuperior.add(new JLabel("Descripción:"));
        txtDescripcionCiudad = new JTextField(15);
        panelSuperior.add(txtDescripcionCiudad);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarCiudad());
        panelSuperior.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarCiudad());
        panelSuperior.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarCiudad());
        panelSuperior.add(btnEliminar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir edición directa en la tabla
            }
        };
        tablaCiudades = new JTable(modeloTabla);
        tablaCiudades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCiudades.getSelectionModel().addListSelectionListener(e -> seleccionarCiudad());

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaCiudades), BorderLayout.CENTER);
    }

    private void cargarCiudades() {
        modeloTabla.setRowCount(0);
        List<Ciudad> lista = controlador.mostrarCiudades();
        for (Ciudad c : lista) {
            modeloTabla.addRow(new Object[]{c.getId(), c.getNombre(), c.getDescripcion()});
        }
        ciudadSeleccionadaId = -1;
        txtNombreCiudad.setText("");
        txtDescripcionCiudad.setText("");
    }

    private void agregarCiudad() {
        String nombre = txtNombreCiudad.getText().trim();
        String descripcion = txtDescripcionCiudad.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la ciudad.");
            return;
        }
        // El id es generado por la capa de gestión, aquí se coloca 0 o -1 (no importa el valor)
        Ciudad ciudad = new Ciudad(0, nombre, descripcion);
        boolean exito = controlador.crearCiudad(ciudad);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Ciudad agregada correctamente.");
            cargarCiudades();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar la ciudad.");
        }
    }

    private void modificarCiudad() {
        if (ciudadSeleccionadaId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una ciudad en la tabla.");
            return;
        }
        String nombre = txtNombreCiudad.getText().trim();
        String descripcion = txtDescripcionCiudad.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la ciudad.");
            return;
        }
        Ciudad ciudadModificada = new Ciudad(ciudadSeleccionadaId, nombre, descripcion);
        boolean exito = controlador.modificarCiudad(ciudadSeleccionadaId, ciudadModificada);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Ciudad modificada correctamente.");
            cargarCiudades();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar la ciudad.");
        }
    }

    private void eliminarCiudad() {
        if (ciudadSeleccionadaId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una ciudad en la tabla.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la ciudad?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            boolean exito = controlador.eliminarCiudad(ciudadSeleccionadaId);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Ciudad eliminada correctamente.");
                cargarCiudades();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la ciudad.");
            }
        }
    }

    private void seleccionarCiudad() {
        int fila = tablaCiudades.getSelectedRow();
        if (fila != -1) {
            ciudadSeleccionadaId = (int) modeloTabla.getValueAt(fila, 0);
            txtNombreCiudad.setText((String) modeloTabla.getValueAt(fila, 1));
            txtDescripcionCiudad.setText((String) modeloTabla.getValueAt(fila, 2));
        } else {
            ciudadSeleccionadaId = -1;
            txtNombreCiudad.setText("");
            txtDescripcionCiudad.setText("");
        }
    }

    // Método para probar la ventana directamente
    public static void main(String[] args) {
        ControladorApp controlador = new ControladorApp();
        Frm_Ciudades ventana = new Frm_Ciudades(controlador);
        ventana.setVisible(true);
    }
}