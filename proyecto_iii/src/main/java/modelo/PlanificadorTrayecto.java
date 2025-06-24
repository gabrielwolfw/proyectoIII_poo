package modelo;

import java.util.ArrayList;
import java.util.List;

public class PlanificadorTrayecto {

    // Calcula una ruta simple directa (puedes reemplazar por Dijkstra si tienes conexiones y mapas)
    public List<Tramo> calcularRuta(Ciudad origen, Ciudad destino, double distancia, double tiempo) {
        List<Tramo> ruta = new ArrayList<>();
        ruta.add(new Tramo(origen, destino, distancia, tiempo));
        return ruta;
    }

    // Calcula totales de un trayecto (puede ser más complejo si hay varios tramos)
    public double calcularTotalDistancia(List<Tramo> tramos) {
        double total = 0;
        for (Tramo t : tramos) {
            total += t.getDistancia();
        }
        return total;
    }

    public double calcularTotalTiempo(List<Tramo> tramos) {
        double total = 0;
        for (Tramo t : tramos) {
            total += t.getTiempoEstimado();
        }
        return total;
    }

    // Construye un trayecto completo
    public Trayecto crearTrayecto(Ciudad origen, Ciudad destino, List<Tramo> tramos) {
        return new Trayecto(origen, destino, tramos);
    }

    // Devuelve las ciudades del trayecto (por cada tramo)
    public List<Ciudad> obtenerCiudadesDelTrayecto(List<Tramo> tramos) {
        List<Ciudad> ciudades = new ArrayList<>();
        if (tramos.isEmpty()) return ciudades;
        ciudades.add(tramos.get(0).getOrigen());
        for (Tramo t : tramos) {
            ciudades.add(t.getDestino());
        }
        return ciudades;
    }

    // Devuelve la información por tramo (distancia y tiempo)
    public List<String> obtenerInformacionTramos(List<Tramo> tramos) {
        List<String> info = new ArrayList<>();
        for (Tramo t : tramos) {
            info.add("De " + t.getOrigen().getNombre() + " a " + t.getDestino().getNombre() +
                     ": " + t.getDistancia() + " km, " +
                     t.getTiempoEstimado() + " min");
        }
        return info;
    }
}