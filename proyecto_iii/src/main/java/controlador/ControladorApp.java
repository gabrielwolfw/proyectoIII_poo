package controlador;

import modelo.*;

import java.util.List;

public class ControladorApp {
    private GestionarCiudades gestorCiudades;
    private GestionarConexiones gestorConexiones;
    private GestionarEstaciones gestorEstaciones;
    private GestionarCombustibles gestorCombustibles;
    private GestionarConectores gestorConectores;

    public ControladorApp() {
        gestorCiudades = new GestionarCiudades();
        gestorConexiones = new GestionarConexiones();
        gestorEstaciones = new GestionarEstaciones();
        gestorCombustibles = new GestionarCombustibles();
        gestorConectores = new GestionarConectores();
    }

    // ----- Ciudades -----
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

    // ----- Conexiones -----
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

    // ----- Estaciones -----
    public boolean crearEstacion(EstacionServicioRecarga estacion) {
        return gestorEstaciones.crear(estacion);
    }

    public boolean modificarEstacion(int id, EstacionServicioRecarga estacionModificada) {
        return gestorEstaciones.modificar(id, estacionModificada);
    }

    public boolean eliminarEstacion(int id) {
        return gestorEstaciones.eliminar(id);
    }

    public List<EstacionServicioRecarga> mostrarEstaciones() {
        return gestorEstaciones.mostrarTodos();
    }

    public List<EstacionServicioRecarga> consultarEstacionesPorCiudad(String nombreCiudad) {
        return gestorEstaciones.consultarEstacionesPorCiudad(nombreCiudad);
    }

    // Asignación de combustibles a estación
    public boolean asignarCombustibleAEstacion(int idEstacion, Combustible combustible) {
        return gestorEstaciones.asignarCombustibleAEstacion(idEstacion, combustible);
    }

    public boolean quitarCombustibleDeEstacion(int idEstacion, int idCombustible) {
        return gestorEstaciones.quitarCombustibleDeEstacion(idEstacion, idCombustible);
    }

    // Asignación de conectores a estación
    public boolean asignarConectorAEstacion(int idEstacion, Conector conector) {
        return gestorEstaciones.asignarConectorAEstacion(idEstacion, conector);
    }

    public boolean quitarConectorDeEstacion(int idEstacion, int idConector) {
        return gestorEstaciones.quitarConectorDeEstacion(idEstacion, idConector);
    }

    // ----- Combustibles -----
    public boolean crearCombustible(Combustible combustible) {
        return gestorCombustibles.crear(combustible);
    }

    public boolean modificarCombustible(int id, Combustible combustibleModificado) {
        return gestorCombustibles.modificar(id, combustibleModificado);
    }

    public boolean eliminarCombustible(int id) {
        return gestorCombustibles.eliminar(id);
    }

    public List<Combustible> mostrarCombustibles() {
        return gestorCombustibles.mostrarTodos();
    }

    // ----- Conectores -----
    public boolean crearConector(Conector conector) {
        return gestorConectores.crear(conector);
    }

    public boolean modificarConector(int id, Conector conectorModificado) {
        return gestorConectores.modificar(id, conectorModificado);
    }

    public boolean eliminarConector(int id) {
        return gestorConectores.eliminar(id);
    }

    public List<Conector> mostrarConectores() {
        return gestorConectores.mostrarTodos();
    }
}