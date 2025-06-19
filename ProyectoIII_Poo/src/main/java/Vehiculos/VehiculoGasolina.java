/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author isaac
 */
public class VehiculoGasolina extends Vehiculo{
    private tipoGasolina tipoGasolina;

    public VehiculoGasolina(tipoGasolina tipoGasolina, String modelo, int placa) {
        super(modelo, placa);
        this.tipoGasolina = tipoGasolina;
    }

    public tipoGasolina getTipoGasolina() {
        return tipoGasolina;
    }

    public void setTipoGasolina(tipoGasolina tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
    }
    
    
    
    
    
            
}
