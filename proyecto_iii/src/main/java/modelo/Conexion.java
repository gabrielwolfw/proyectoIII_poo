package modelo;

import java.io.Serializable;

public class Conexion implements Serializable {
    private int id;
    private String ciudadOrigen;
    private String ciudadDestino;
    private int kilometros;
    private int minutos;

    // Constructor, getters, setters, etc.
    public Conexion(int id, String ciudadOrigen, String ciudadDestino, int kilometros, int minutos) {
        this.id = id;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.kilometros = kilometros;
        this.minutos = minutos;
    }
}