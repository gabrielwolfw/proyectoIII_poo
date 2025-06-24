package modelo;

import java.io.Serializable;

public class Tramo implements Serializable {
    private Ciudad origen;
    private Ciudad destino;
    private double distancia;        // en kilómetros
    private double tiempoEstimado;   // en minutos

    public Tramo(Ciudad origen, Ciudad destino, double distancia, double tiempoEstimado) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Ciudad getOrigen() { return origen; }
    public Ciudad getDestino() { return destino; }
    public double getDistancia() { return distancia; }
    public double getTiempoEstimado() { return tiempoEstimado; }

    public void setOrigen(Ciudad origen) { this.origen = origen; }
    public void setDestino(Ciudad destino) { this.destino = destino; }
    public void setDistancia(double distancia) { this.distancia = distancia; }
    public void setTiempoEstimado(double tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }
}