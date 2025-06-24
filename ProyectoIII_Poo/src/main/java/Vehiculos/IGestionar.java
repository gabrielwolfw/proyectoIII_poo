/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Vehiculos;

/**
 *
 * @author isaac
 */
import java.util.List;

public interface IGestionar<T> {
    boolean crear(T objeto);
    boolean modificar(int id, T objetoModificado);
    boolean eliminar(int id);
    List<T> mostrarTodos();
}
