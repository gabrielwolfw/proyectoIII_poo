/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorTrayecto;
import controlador.ControladorApp;
import modelo.Ciudad;
import modelo.Trayecto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Frm_Trayectoria extends JFrame {
    private ControladorTrayecto controladorTrayecto;
    private ControladorApp controladorApp;
    private JComboBox<Ciudad> comboOrigen;
    private JComboBox<Ciudad> comboDestino;
    private JTextField txtDistancia;
    private JTextField txtTiempo;
    private JTextArea txtResultado;

    public Frm_Trayectoria(ControladorApp controladorApp) {
        this.controladorApp = controladorApp;
        this.controladorTrayecto = controladorTrayecto;
        initComponents();
    }

    private void initComponents() {
        setTitle("Planificador de Trayectoria");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 8, 8));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelSuperior.add(new JLabel("Ciudad Origen:"));
        comboOrigen = new JComboBox<>();
        panelSuperior.add(comboOrigen);

        panelSuperior.add(new JLabel("Ciudad Destino:"));
        comboDestino = new JComboBox<>();
        panelSuperior.add(comboDestino);

        panelSuperior.add(new JLabel("Distancia (km):"));
        txtDistancia = new JTextField();
        panelSuperior.add(txtDistancia);

        panelSuperior.add(new JLabel("Tiempo (horas):"));
        txtTiempo = new JTextField();
        panelSuperior.add(txtTiempo);

        JButton btnCalcular = new JButton("Calcular Trayecto");
        btnCalcular.addActionListener(e -> calcularTrayecto());
        panelSuperior.add(btnCalcular);

        add(panelSuperior, BorderLayout.NORTH);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado de la Trayectoria"));
        add(scroll, BorderLayout.CENTER);

        // Cargar ciudades desde ControladorApp
        cargarCiudades();
    }

    private void cargarCiudades() {
        comboOrigen.removeAllItems();
        comboDestino.removeAllItems();
        List<Ciudad> ciudadesDisponibles = controladorApp.mostrarCiudades();
        for (Ciudad c : ciudadesDisponibles) {
            comboOrigen.addItem(c);
            comboDestino.addItem(c);
        }
    }

    private void calcularTrayecto() {
        Ciudad origen = (Ciudad) comboOrigen.getSelectedItem();
        Ciudad destino = (Ciudad) comboDestino.getSelectedItem();

        if (origen == null || destino == null) {
            JOptionPane.showMessageDialog(this, "Seleccione las ciudades de origen y destino.");
            return;
        }
        if (origen.equals(destino)) {
            JOptionPane.showMessageDialog(this, "El origen y destino no pueden ser la misma ciudad.");
            return;
        }

        double distancia, tiempo;
        try {
            distancia = Double.parseDouble(txtDistancia.getText().trim());
            tiempo = Double.parseDouble(txtTiempo.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para distancia y tiempo.");
            return;
        }

        Trayecto trayecto = controladorTrayecto.planificarTrayecto(origen, destino, distancia, tiempo);

        StringBuilder sb = new StringBuilder();
        sb.append("Trayectoria de: ").append(origen.getNombre())
          .append("  ---->  ").append(destino.getNombre()).append("\n\n")
          .append("Distancia total: ").append(controladorTrayecto.obtenerTotalDistancia(trayecto)).append(" km\n")
          .append("Tiempo total: ").append(controladorTrayecto.obtenerTotalTiempo(trayecto)).append(" horas\n\n");

        sb.append("Ciudades involucradas:\n");
        for (Ciudad c : controladorTrayecto.obtenerCiudadesDelTrayecto(trayecto)) {
            sb.append(" - ").append(c.getNombre()).append("\n");
        }
        sb.append("\nTramos:\n");
        for (String info : controladorTrayecto.obtenerInformacionTramos(trayecto)) {
            sb.append(" - ").append(info).append("\n");
        }

        txtResultado.setText(sb.toString());
    }

    // Método de ejemplo para probar la ventana
    public static void main(String[] args) {
        ControladorApp controladorApp = new ControladorApp();
        ControladorTrayecto controladorTrayecto = new ControladorTrayecto();
        Frm_Trayectoria ventana = new Frm_Trayectoria(controladorApp);
        ventana.setVisible(true);
    }
}
