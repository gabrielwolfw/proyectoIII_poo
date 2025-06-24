package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarCiudades implements IGestionar<Ciudad>, Serializable {
    private List<Ciudad> ciudades;
    private final String archivo = "ciudades.dat";

    public GestionarCiudades() {
        ciudades = leerArchivo();
    }

    @Override
    public boolean crear(Ciudad ciudad) {
        for (Ciudad c : ciudades) {
            if (c.getNombre().equalsIgnoreCase(ciudad.getNombre())) {
                return false;
            }
        }
        ciudades.add(ciudad);
        guardarArchivo();
        return true;
    }

    @Override
    public boolean modificar(int id, Ciudad ciudadModificada) {
        for (int i = 0; i < ciudades.size(); i++) {
            if (ciudades.get(i).getId() == id) {
                ciudades.set(i, ciudadModificada);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        boolean removed = ciudades.removeIf(c -> c.getId() == id);
        if (removed) guardarArchivo();
        return removed;
    }

    @Override
    public List<Ciudad> mostrarTodos() {
        return new ArrayList<>(ciudades);
    }

    // Métodos de persistencia binaria
    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(ciudades);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Ciudad> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Ciudad>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
