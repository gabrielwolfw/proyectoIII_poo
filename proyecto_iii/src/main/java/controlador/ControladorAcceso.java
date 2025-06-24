package controlador;

import modelo.GestionarUsuarios;
import modelo.RolUsuario;
import modelo.Usuario;

public class ControladorAcceso {
    private GestionarUsuarios gestorUsuarios;

    public ControladorAcceso() {
        gestorUsuarios = new GestionarUsuarios();
    }

    public boolean registrarUsuario(String usuario, String contrasena, String nombreCompleto, RolUsuario rol) {
        return gestorUsuarios.registrarUsuario(new Usuario(usuario, contrasena, nombreCompleto, rol));
    }

    public boolean autenticarUsuario(String usuario, String contrasena) {
        return gestorUsuarios.autenticarUsuario(usuario, contrasena);
    }

    public void cerrarSesion() {
        gestorUsuarios.cerrarSesion();
    }

    public Usuario getUsuarioActual() {
        return gestorUsuarios.getUsuarioActual();
    }
}
