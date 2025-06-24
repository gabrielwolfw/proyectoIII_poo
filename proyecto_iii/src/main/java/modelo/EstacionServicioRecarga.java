package modelo;

import java.io.Serializable;
import java.util.List;

public class EstacionServicioRecarga implements Serializable {
    private int id;
    private String nombre;
    private String ciudad; // nombre o id de la ciudad asociada
    private boolean esElectrica;
    private boolean esPublica;
    private List<Combustible> combustibles; // Si es estación de combustible
    private List<Conector> conectores;      // Si es estación de recarga eléctrica

    public EstacionServicioRecarga(
            int id, String nombre, String ciudad, boolean esElectrica, boolean esPublica,
            List<Combustible> combustibles,
            List<Conector> conectores
    ) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.esElectrica = esElectrica;
        this.esPublica = esPublica;
        this.combustibles = combustibles;
        this.conectores = conectores;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public boolean isEsElectrica() { return esElectrica; }
    public boolean isEsPublica() { return esPublica; }
    public List<Combustible> getCombustibles() { return combustibles; }
    public List<Conector> getConectores() { return conectores; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setEsElectrica(boolean esElectrica) { this.esElectrica = esElectrica; }
    public void setEsPublica(boolean esPublica) { this.esPublica = esPublica; }
    public void setCombustibles(List<Combustible> combustibles) { this.combustibles = combustibles; }
    public void setConectores(List<Conector> conectores) { this.conectores = conectores; }
}