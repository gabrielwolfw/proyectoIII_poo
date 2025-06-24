package vista;

import controlador.ControladorApp;
import modelo.Conector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Frm_Conectores extends JFrame {
    private ControladorApp controlador;
    private JTextField txtTipo;
    private JTable tablaConectores;
    private DefaultTableModel modeloTabla;
    private int conectorSeleccionadoId = -1;

    public Frm_Conectores(ControladorApp controlador) {
        this.controlador = controlador;
        initComponents();
        cargarConectores();
    }

    private void initComponents() {
        setTitle("Gestión de Conectores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(new JLabel("Tipo:"));
        txtTipo = new JTextField(15);
        panelSuperior.add(txtTipo);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarConector());
        panelSuperior.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarConector());
        panelSuperior.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarConector());
        panelSuperior.add(btnEliminar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Tipo"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaConectores = new JTable(modeloTabla);
        tablaConectores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaConectores.getSelectionModel().addListSelectionListener(e -> seleccionarConector());

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaConectores), BorderLayout.CENTER);
    }

    private void cargarConectores() {
        modeloTabla.setRowCount(0);
        List<Conector> lista = controlador.mostrarConectores();
        for (Conector c : lista) {
            modeloTabla.addRow(new Object[]{c.getId(), c.getTipo()});
        }
        conectorSeleccionadoId = -1;
        txtTipo.setText("");
    }

    private void agregarConector() {
        String tipo = txtTipo.getText().trim();
        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el tipo de conector.");
            return;
        }
        Conector conector = new Conector(0, tipo); // El id lo maneja el backend
        boolean exito = controlador.crearConector(conector);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Conector agregado correctamente.");
            cargarConectores();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el conector.");
        }
    }

    private void modificarConector() {
        if (conectorSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un conector en la tabla.");
            return;
        }
        String tipo = txtTipo.getText().trim();
        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el tipo de conector.");
            return;
        }
        Conector conectorModificado = new Conector(conectorSeleccionadoId, tipo);
        boolean exito = controlador.modificarConector(conectorSeleccionadoId, conectorModificado);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Conector modificado correctamente.");
            cargarConectores();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar el conector.");
        }
    }

    private void eliminarConector() {
        if (conectorSeleccionadoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un conector en la tabla.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el conector?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            boolean exito = controlador.eliminarConector(conectorSeleccionadoId);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Conector eliminado correctamente.");
                cargarConectores();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el conector.");
            }
        }
    }

    private void seleccionarConector() {
        int fila = tablaConectores.getSelectedRow();
        if (fila != -1) {
            conectorSeleccionadoId = (int) modeloTabla.getValueAt(fila, 0);
            txtTipo.setText((String) modeloTabla.getValueAt(fila, 1));
        } else {
            conectorSeleccionadoId = -1;
            txtTipo.setText("");
        }
    }

    // Método para probar la ventana directamente
    public static void main(String[] args) {
        ControladorApp controlador = new ControladorApp();
        Frm_Conectores ventana = new Frm_Conectores(controlador);
        ventana.setVisible(true);
    }
}