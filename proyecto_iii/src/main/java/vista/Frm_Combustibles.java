package vista;

import controlador.ControladorApp;
import modelo.Combustible;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Frm_Combustibles extends JFrame {
    private ControladorApp controlador;
    private JTextField txtNombre;
    private JTable tablaCombustibles;
    private DefaultTableModel modeloTabla;
    private int combustibleSeleccionadoId = -1;

    public Frm_Combustibles(ControladorApp controlador) {
        this.controlador = controlador;
        initComponents();
        cargarCombustibles();
    }

    private void initComponents() {
        setTitle("Gestión de Combustibles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        panelSuperior.add(txtNombre);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarCombustible());
        panelSuperior.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarCombustible());
        panelSuperior.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarCombustible());
        panelSuperior.add(btnEliminar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCombustibles = new JTable(modeloTabla);
        tablaCombustibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCombustibles.getSelectionModel().addListSelectionListener(e -> seleccionarCombustible());

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaCombustibles), BorderLayout.CENTER);
    }

    private void cargarCombustibles() {
        modeloTabla.setRowCount(0);
        List<Combustible> lista = controlador.mostrarCombustibles();
        for (Combustible c : lista) {
            modeloTabla.addRow(new Object[]{c.getId(), c.getNombre()});
        }
        combustibleSeleccionadoId = -1;
        txtNombre.setText("");
    }

    private void agregarCombustible() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del combustible.");
            return;
        }
        Combustible combustible = new Combustible(0, nombre); // El id lo maneja el backend
        boolean exito = controlador.crearCombustible(combustible);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Combustible agregado correctamente.");
            cargarCombustibles();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el combustible.");
        }
    }

    private void modificarCombustible() {
        if (combustibleSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un combustible en la tabla.");
            return;
        }
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del combustible.");
            return;
        }
        Combustible combustibleModificado = new Combustible(combustibleSeleccionadoId, nombre);
        boolean exito = controlador.modificarCombustible(combustibleSeleccionadoId, combustibleModificado);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Combustible modificado correctamente.");
            cargarCombustibles();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar el combustible.");
        }
    }

    private void eliminarCombustible() {
        if (combustibleSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un combustible en la tabla.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el combustible?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            boolean exito = controlador.eliminarCombustible(combustibleSeleccionadoId);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Combustible eliminado correctamente.");
                cargarCombustibles();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el combustible.");
            }
        }
    }

    private void seleccionarCombustible() {
        int fila = tablaCombustibles.getSelectedRow();
        if (fila != -1) {
            combustibleSeleccionadoId = (int) modeloTabla.getValueAt(fila, 0);
            txtNombre.setText((String) modeloTabla.getValueAt(fila, 1));
        } else {
            combustibleSeleccionadoId = -1;
            txtNombre.setText("");
        }
    }

    // Método para probar la ventana directamente
    public static void main(String[] args) {
        ControladorApp controlador = new ControladorApp();
        Frm_Combustibles ventana = new Frm_Combustibles(controlador);
        ventana.setVisible(true);
    }
}