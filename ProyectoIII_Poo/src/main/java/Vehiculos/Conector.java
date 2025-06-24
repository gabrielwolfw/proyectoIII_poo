/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author isaac
 */
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
