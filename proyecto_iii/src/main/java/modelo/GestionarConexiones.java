package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarConexiones implements IGestionar<Conexion>, Serializable {
    private List<Conexion> conexiones;
    private final String archivo = "conexiones.dat";

    public GestionarConexiones() {
        conexiones = leerArchivo();
    }

    @Override
    public boolean crear(Conexion conexion) {
        for (Conexion c : conexiones) {
            if (c.getCiudadOrigen().equalsIgnoreCase(conexion.getCiudadOrigen()) &&
                c.getCiudadDestino().equalsIgnoreCase(conexion.getCiudadDestino())) {
                return false;
            }
        }
        conexiones.add(conexion);
        guardarArchivo();
        return true;
    }

    @Override
    public boolean modificar(int id, Conexion conexionModificada) {
        for (int i = 0; i < conexiones.size(); i++) {
            if (conexiones.get(i).getId() == id) {
                conexiones.set(i, conexionModificada);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        boolean removed = conexiones.removeIf(c -> c.getId() == id);
        if (removed) guardarArchivo();
        return removed;
    }

    @Override
    public List<Conexion> mostrarTodos() {
        return new ArrayList<>(conexiones);
    }

    // Métodos de persistencia binaria
    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(conexiones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Conexion> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Conexion>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}