package controlador;

import modelo.ElementoMapa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarElementoMapa<T extends ElementoMapa> {
    private List<T> elementos;
    private final String archivo;

    public GestionarElementoMapa(String archivo) {
        this.archivo = archivo;
        this.elementos = leerArchivo();
    }

    public boolean crear(T elemento) {
        for (T e : elementos) {
            if (e.getNombre().equalsIgnoreCase(elemento.getNombre())) {
                return false;
            }
        }
        elementos.add(elemento);
        guardarArchivo();
        return true;
    }

    public boolean modificar(int id, T elementoModificado) {
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getId() == id) {
                elementos.set(i, elementoModificado);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        boolean removed = elementos.removeIf(e -> e.getId() == id);
        if (removed) guardarArchivo();
        return removed;
    }

    public List<T> mostrarTodos() {
        return new ArrayList<>(elementos);
    }

    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(elementos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<T> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
