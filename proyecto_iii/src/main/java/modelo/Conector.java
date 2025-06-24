package modelo;

import java.io.Serializable;

public class Conector implements Serializable {
    private int id;
    private String tipo;

    public Conector(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return tipo;
    }
}