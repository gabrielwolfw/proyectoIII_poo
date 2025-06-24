package vista;

import controlador.ControladorApp;
import modelo.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Frm_Conexiones extends JFrame {
    private ControladorApp controlador;
    private JTextField txtCiudad1;
    private JTextField txtCiudad2;
    private JTable tablaConexiones;
    private DefaultTableModel modeloTabla;

    public Frm_Conexiones(ControladorApp controlador) {
        this.controlador = controlador;
        initComponents();
        cargarConexiones();
    }

    private void initComponents() {
        setTitle("Gestión de Conexiones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(new JLabel("Ciudad 1:"));
        txtCiudad1 = new JTextField(10);
        panelSuperior.add(txtCiudad1);

        panelSuperior.add(new JLabel("Ciudad 2:"));
        txtCiudad2 = new JTextField(10);
        panelSuperior.add(txtCiudad2);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarConexion());
        panelSuperior.add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarConexion());
        panelSuperior.add(btnEliminar);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Ciudad 1", "Ciudad 2"}, 0);
        tablaConexiones = new JTable(modeloTabla);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaConexiones), BorderLayout.CENTER);
    }

    private void agregarConexion() {
        String ciudad1 = txtCiudad1.getText().trim();
        String ciudad2 = txtCiudad2.getText().trim();
        if (ciudad1.isEmpty() || ciudad2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ambas ciudades.");
            return;
        }
        Conexion conexion = new Conexion(ciudad1, ciudad2); // Asegúrate que el constructor existe en tu modelo
        boolean exito = controlador.crearConexion(conexion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Conexión agregada.");
            cargarConexiones();
            txtCiudad1.setText("");
            txtCiudad2.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar la conexión.");
        }
    }

    private void eliminarConexion() {
        int fila = tablaConexiones.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        boolean exito = controlador.eliminarConexion(id);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Conexión eliminada.");
            cargarConexiones();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la conexión.");
        }
    }

    private void cargarConexiones() {
        modeloTabla.setRowCount(0);
        List<Conexion> lista = controlador.mostrarConexiones();
        for (Conexion c : lista) {
            modeloTabla.addRow(new Object[]{c.getId(), c.getCiudadOrigen(), c.getCiudadDestino()});
        }
    }

    // Método para probar la ventana directamente
    public static void main(String[] args) {
        ControladorApp controlador = new ControladorApp(); // O usa tu mecanismo de inyección
        Frm_Conexiones ventana = new Frm_Conexiones(controlador);
        ventana.setVisible(true);
    }
}