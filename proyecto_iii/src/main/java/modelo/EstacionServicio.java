
package modelo;

public class EstacionServicio extends ElementoMapa {
    private String tipo;

    public EstacionServicio(int id, String nombre, String tipo) {
        super(id, nombre);
        this.tipo = tipo;
    }

    @Override
    public String descripcion() {
        return "Estación: " + nombre + ", Tipo: " + tipo;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
