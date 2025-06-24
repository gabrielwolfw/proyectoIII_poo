package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarConectores implements IGestionar<Conector>, Serializable {
    private List<Conector> conectores;
    private final String archivo = "../Recursos/conectores.dat";

    public GestionarConectores() {
        conectores = leerArchivo();
    }

    @Override
    public boolean crear(Conector conector) {
        for (Conector c : conectores) {
            if (c.getTipo().equalsIgnoreCase(conector.getTipo())) {
                return false; // Ya existe un conector de ese tipo
            }
        }
        conectores.add(conector);
        guardarArchivo();
        return true;
    }

    @Override
    public boolean modificar(int id, Conector conectorModificado) {
        for (int i = 0; i < conectores.size(); i++) {
            if (conectores.get(i).getId() == id) {
                conectores.set(i, conectorModificado);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        boolean removed = conectores.removeIf(c -> c.getId() == id);
        if (removed) guardarArchivo();
        return removed;
    }

    @Override
    public List<Conector> mostrarTodos() {
        return new ArrayList<>(conectores);
    }

    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(conectores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Conector> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Conector>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}