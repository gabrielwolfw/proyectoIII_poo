package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarUsuarios implements Serializable {
    private List<Usuario> usuarios;
    private final String archivo = "../Recursos/usuarios.dat";
    private Usuario usuarioActual = null;

    public GestionarUsuarios() {
        usuarios = leerArchivo();
    }

    // Registrar usuario
    public boolean registrarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
                return false; // Ya existe
            }
        }
        usuarios.add(usuario);
        guardarArchivo();
        return true;
    }

    // Autenticar usuario
    public boolean autenticarUsuario(String usuario, String contrasena) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario) && u.getContrasena().equals(contrasena)) {
                usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    // Cerrar sesión
    public void cerrarSesion() {
        usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Persistencia
    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Usuario> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}