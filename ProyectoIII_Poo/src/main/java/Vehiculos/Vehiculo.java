/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author isaac
 */
public class Vehiculo {
    private String modelo;
    private int placa;
    private boolean favorito;
    private boolean unaVez;

    public Vehiculo(String modelo, int placa) {
        this.modelo = modelo;
        this.placa = placa;
        this.favorito = false;
        this.unaVez = false;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public boolean getFavorito() {
        return favorito;
    }

    public void activarFavorito() {
        this.favorito = true;
    }
    public void desactivarFavorito(){
        this.favorito = false;
    }

    public boolean getUnaVez() {
        return unaVez;
    }

    public void setUnaVez() {
        this.unaVez = true;
    }
    public void removeUnaVez(){
        this.unaVez = false;
    }
    
}
