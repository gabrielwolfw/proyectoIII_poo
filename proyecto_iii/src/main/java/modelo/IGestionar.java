package modelo;

import java.util.List;

public interface IGestionar<T> {
    boolean crear(T objeto);
    boolean modificar(int id, T objetoModificado);
    boolean eliminar(int id);
    List<T> mostrarTodos();
}