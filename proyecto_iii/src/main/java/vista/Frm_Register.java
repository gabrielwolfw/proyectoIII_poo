/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorAcceso;
import modelo.RolUsuario;

import javax.swing.*;
import java.awt.*;

public class Frm_Register extends JFrame {
    private ControladorAcceso controlador;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextField txtNombreCompleto;
    private JComboBox<RolUsuario> comboRol;

    public Frm_Register(ControladorAcceso controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Registrar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblUsuario = new JLabel("Usuario:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblUsuario, gbc);

        txtUsuario = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUsuario, gbc);

        JLabel lblContrasena = new JLabel("Contraseña:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtContrasena, gbc);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblNombre, gbc);

        txtNombreCompleto = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtNombreCompleto, gbc);

        JLabel lblRol = new JLabel("Rol:");
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblRol, gbc);

        comboRol = new JComboBox<>(RolUsuario.values());
        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        panel.add(comboRol, gbc);

        JButton btnRegistrar = new JButton("Registrar");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> registrarUsuario());

        add(panel);
    }

    private void registrarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String nombreCompleto = txtNombreCompleto.getText().trim();
        RolUsuario rol = (RolUsuario) comboRol.getSelectedItem();

        if (usuario.isEmpty() || contrasena.isEmpty() || nombreCompleto.isEmpty() || rol == null) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        boolean registrado = controlador.registrarUsuario(usuario, contrasena, nombreCompleto, rol);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario (¿usuario ya existe?).");
        }
    }
}
