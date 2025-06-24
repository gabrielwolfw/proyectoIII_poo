
package modelo;

import java.io.Serializable;

public abstract class ElementoMapa implements Serializable {
    protected int id;
    protected String nombre;

    public ElementoMapa(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public abstract String descripcion();
}