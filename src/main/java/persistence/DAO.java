package persistence;

import exception.ExceptionDAO;

import java.util.List;

public interface DAO <T, K>{
    void agregar(T t) throws ExceptionDAO;
    void eliminar(K k) throws ExceptionDAO;
    void actualizar(T t) throws ExceptionDAO;
    T obtener(K k) throws ExceptionDAO;
    List<T> listar() throws ExceptionDAO;
}
