package controlador;

import modelo.*;

import java.util.List;

public class ControladorTrayecto {
    private PlanificadorTrayecto planificador;

    public ControladorTrayecto() {
        this.planificador = new PlanificadorTrayecto();
    }

    // Planifica un trayecto simple directo (puedes expandir para rutas complejas)
    public Trayecto planificarTrayecto(Ciudad origen, Ciudad destino, double distancia, double tiempo) {
        List<Tramo> ruta = planificador.calcularRuta(origen, destino, distancia, tiempo);
        return planificador.crearTrayecto(origen, destino, ruta);
    }

    // Devuelve el total de distancia del trayecto
    public double obtenerTotalDistancia(Trayecto trayecto) {
        return planificador.calcularTotalDistancia(trayecto.getTramos());
    }

    // Devuelve el total de tiempo del trayecto
    public double obtenerTotalTiempo(Trayecto trayecto) {
        return planificador.calcularTotalTiempo(trayecto.getTramos());
    }

    // Devuelve la lista de ciudades intervinientes en el trayecto
    public List<Ciudad> obtenerCiudadesDelTrayecto(Trayecto trayecto) {
        return planificador.obtenerCiudadesDelTrayecto(trayecto.getTramos());
    }

    // Devuelve información resumida de los tramos
    public List<String> obtenerInformacionTramos(Trayecto trayecto) {
        return planificador.obtenerInformacionTramos(trayecto.getTramos());
    }
}