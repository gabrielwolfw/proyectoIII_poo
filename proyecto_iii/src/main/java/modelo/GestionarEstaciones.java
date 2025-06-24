package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarEstaciones implements IGestionar<EstacionServicioRecarga>, Serializable {
    private List<EstacionServicioRecarga> estaciones;
    private final String archivo = "Recursos/estaciones.dat";

    public GestionarEstaciones() {
        estaciones = leerArchivo();
    }

    @Override
    public boolean crear(EstacionServicioRecarga estacion) {
        for (EstacionServicioRecarga e : estaciones) {
            if (e.getNombre().equalsIgnoreCase(estacion.getNombre()) &&
                e.getCiudad().equalsIgnoreCase(estacion.getCiudad())) {
                return false; // Ya existe una estación con ese nombre en esa ciudad
            }
        }
        estaciones.add(estacion);
        guardarArchivo();
        return true;
    }

    @Override
    public boolean modificar(int id, EstacionServicioRecarga estacionModificada) {
        for (int i = 0; i < estaciones.size(); i++) {
            if (estaciones.get(i).getId() == id) {
                estaciones.set(i, estacionModificada);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        boolean removed = estaciones.removeIf(e -> e.getId() == id);
        if (removed) guardarArchivo();
        return removed;
    }

    @Override
    public List<EstacionServicioRecarga> mostrarTodos() {
        return new ArrayList<>(estaciones);
    }

    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(estaciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<EstacionServicioRecarga> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<EstacionServicioRecarga>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public boolean asignarCombustibleAEstacion(int idEstacion, Combustible combustible) {
        for (EstacionServicioRecarga e : estaciones) {
            if (e.getId() == idEstacion) {
                List<Combustible> lista = e.getCombustibles();
                // Evitar duplicados
                for (Combustible c : lista) {
                    if (c.getId() == combustible.getId()) return false;
                }
                lista.add(combustible);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    public boolean quitarCombustibleDeEstacion(int idEstacion, int idCombustible) {
        for (EstacionServicioRecarga e : estaciones) {
            if (e.getId() == idEstacion) {
                boolean removed = e.getCombustibles().removeIf(c -> c.getId() == idCombustible);
                if (removed) guardarArchivo();
                return removed;
            }
        }
        return false;
    }

    public boolean asignarConectorAEstacion(int idEstacion, Conector conector) {
        for (EstacionServicioRecarga e : estaciones) {
            if (e.getId() == idEstacion) {
                List<Conector> lista = e.getConectores();
                for (Conector c : lista) {
                    if (c.getId() == conector.getId()) return false;
                }
                lista.add(conector);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    public boolean quitarConectorDeEstacion(int idEstacion, int idConector) {
        for (EstacionServicioRecarga e : estaciones) {
            if (e.getId() == idEstacion) {
                boolean removed = e.getConectores().removeIf(c -> c.getId() == idConector);
                if (removed) guardarArchivo();
                return removed;
            }
        }
        return false;
    }
    
    public List<EstacionServicioRecarga> consultarEstacionesPorCiudad(String nombreCiudad) {
        List<EstacionServicioRecarga> resultado = new ArrayList<>();
        for (EstacionServicioRecarga estacion : estaciones) {
            if (estacion.getCiudad().equalsIgnoreCase(nombreCiudad)) {
                resultado.add(estacion);
            }
        }
        return resultado;
    }
}
