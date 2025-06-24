package modelo;

import java.io.Serializable;

public class Combustible implements Serializable {
    private int id;
    private String nombre;

    public Combustible(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre;
    }
}