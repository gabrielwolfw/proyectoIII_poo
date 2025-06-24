package modelo;

import java.io.Serializable;

public class Conexion implements Serializable {
    private int id;
    private String ciudadOrigen;
    private String ciudadDestino;
    private int kilometros;
    private int minutos;

    public Conexion(int id, String ciudadOrigen, String ciudadDestino, int kilometros, int minutos) {
        this.id = id;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.kilometros = kilometros;
        this.minutos = minutos;
    }

    public int getId() {
        return id;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public int getKilometros() {
        return kilometros;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return "Conexion{" +
                "id=" + id +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                ", ciudadDestino='" + ciudadDestino + '\'' +
                ", kilometros=" + kilometros +
                ", minutos=" + minutos +
                '}';
    }
}