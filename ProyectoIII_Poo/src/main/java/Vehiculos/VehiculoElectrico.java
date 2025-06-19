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
    private tipoConector tipoConector;

    public VehiculoElectrico(tipoConector tipoConector, String modelo, int placa) {
        super(modelo, placa);
        this.tipoConector = tipoConector;
    }

    public tipoConector getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(tipoConector tipoConector) {
        this.tipoConector = tipoConector;
    }
    
    
    
}
