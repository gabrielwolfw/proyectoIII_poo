package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarCombustibles implements IGestionar<Combustible>, Serializable {
    private List<Combustible> combustibles;
    private final String archivo = "../Recursos/combustibles.dat";

    public GestionarCombustibles() {
        combustibles = leerArchivo();
    }

    @Override
    public boolean crear(Combustible combustible) {
        for (Combustible c : combustibles) {
            if (c.getNombre().equalsIgnoreCase(combustible.getNombre())) {
                return false;
            }
        }
        combustibles.add(combustible);
        guardarArchivo();
        return true;
    }

    @Override
    public boolean modificar(int id, Combustible combustibleModificado) {
        for (int i = 0; i < combustibles.size(); i++) {
            if (combustibles.get(i).getId() == id) {
                combustibles.set(i, combustibleModificado);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        boolean removed = combustibles.removeIf(c -> c.getId() == id);
        if (removed) guardarArchivo();
        return removed;
    }

    @Override
    public List<Combustible> mostrarTodos() {
        return new ArrayList<>(combustibles);
    }

    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(combustibles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Combustible> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Combustible>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}