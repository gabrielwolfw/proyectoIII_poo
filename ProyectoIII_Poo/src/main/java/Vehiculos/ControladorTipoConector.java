/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import java.util.ArrayList;

/**
 *
 * @author isaac
 */
public class ControladorTipoConector {
    private ArrayList<tipoConector> listaConectores;

    public ControladorTipoConector() {
        this.listaConectores=new ArrayList<tipoConector>();
    }
    public void agregarTipo(tipoConector tipo){
        this.listaConectores.add(tipo);
    }
    public void quitarTipo(String nombre){
        for(tipoConector t:this.listaConectores){
            if(t.getNombre().equals(nombre)){
                this.listaConectores.remove(t);
            }
        }
    }
    
}
