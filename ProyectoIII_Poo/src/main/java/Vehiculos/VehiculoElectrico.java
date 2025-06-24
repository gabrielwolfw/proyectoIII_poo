/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author isaac
 */
public class VehiculoElectrico extends Vehiculo{
    private Conector tipoConector;

    public VehiculoElectrico(Conector tipoConector, String modelo, int placa) {
        super(modelo, placa);
        this.tipoConector = tipoConector;
    }

    public Conector getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(Conector tipoConector) {
        this.tipoConector = tipoConector;
    }
    
    
    
}
