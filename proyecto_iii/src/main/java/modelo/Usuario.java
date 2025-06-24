package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario;
    private String contrasena;
    private String nombreCompleto;
    private RolUsuario rol;

    public Usuario(String usuario, String contrasena, String nombreCompleto, RolUsuario rol) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    public String getNombreCompleto() { return nombreCompleto; }
    public RolUsuario getRol() { return rol; }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setRol(RolUsuario rol) { this.rol = rol; }
}