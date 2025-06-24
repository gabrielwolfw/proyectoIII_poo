package modelo;

import java.io.Serializable;
import java.util.List;

public class Trayecto implements Serializable {
    private Ciudad origen;
    private Ciudad destino;
    private List<Tramo> tramos;
    private double totalDistancia;
    private double totalTiempo;

    public Trayecto(Ciudad origen, Ciudad destino, List<Tramo> tramos) {
        this.origen = origen;
        this.destino = destino;
        this.tramos = tramos;
        calcularTotales();
    }

    private void calcularTotales() {
        totalDistancia = 0;
        totalTiempo = 0;
        for (Tramo t : tramos) {
            totalDistancia += t.getDistancia();
            totalTiempo += t.getTiempoEstimado();
        }
    }

    public Ciudad getOrigen() { return origen; }
    public Ciudad getDestino() { return destino; }
    public List<Tramo> getTramos() { return tramos; }
    public double getTotalDistancia() { return totalDistancia; }
    public double getTotalTiempo() { return totalTiempo; }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
        calcularTotales();
    }
}