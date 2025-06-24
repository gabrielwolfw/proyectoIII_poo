/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author isaac
 */
public class GestionarVehiculos implements IGestionar<Vehiculo>,Serializable {
    


    private List<Vehiculo> vehiculos;
    private final String archivo = "../Recursos/vehiculos.dat";

    public GestionarVehiculos() {
        vehiculos = leerArchivo();
    }

    @Override
    public boolean crear(Vehiculo vehiculo) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca()==vehiculo.getPlaca()) {
                return false;
            }
        }
        vehiculos.add(vehiculo);
        guardarArchivo();
        return true;
    }

    @Override
    public boolean modificar(int placa, Vehiculo vehiculoModificado) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPlaca() == placa) {
                vehiculos.set(i, vehiculoModificado);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(int placa) {
        boolean removed = vehiculos.removeIf(c -> c.getPlaca() == placa);
        if (removed) guardarArchivo();
        return removed;
    }

    @Override
    public List<Vehiculo> mostrarTodos() {
        return new ArrayList<>(vehiculos);
    }

    // Métodos de persistencia binaria
    private void guardarArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(vehiculos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Vehiculo> leerArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Vehiculo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

