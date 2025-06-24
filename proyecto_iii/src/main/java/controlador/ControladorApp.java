package controlador;

import modelo.Ciudad;
import modelo.Conexion;
import modelo.GestionarCiudades;
import modelo.GestionarConexiones;

import java.util.List;

public class ControladorApp {
    private GestionarCiudades gestorCiudades;
    private GestionarConexiones gestorConexiones;

    public ControladorApp() {
        gestorCiudades = new GestionarCiudades();
        gestorConexiones = new GestionarConexiones();
    }

    // Métodos para ciudades
    public boolean crearCiudad(Ciudad ciudad) {
        return gestorCiudades.crear(ciudad);
    }

    public boolean modificarCiudad(int id, Ciudad ciudadModificada) {
        return gestorCiudades.modificar(id, ciudadModificada);
    }

    public boolean eliminarCiudad(int id) {
        return gestorCiudades.eliminar(id);
    }

    public List<Ciudad> mostrarCiudades() {
        return gestorCiudades.mostrarTodos();
    }

    // Métodos para conexiones
    public boolean crearConexion(Conexion conexion) {
        return gestorConexiones.crear(conexion);
    }

    public boolean modificarConexion(int id, Conexion conexionModificada) {
        return gestorConexiones.modificar(id, conexionModificada);
    }

    public boolean eliminarConexion(int id) {
        return gestorConexiones.eliminar(id);
    }

    public List<Conexion> mostrarConexiones() {
        return gestorConexiones.mostrarTodos();
    }
}