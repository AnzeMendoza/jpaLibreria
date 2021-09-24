package service;

import entity.Autor;
import exception.ExceptionDAO;
import persistence.AutorBBDD;

import java.util.List;

public class AutorService {

    public static Autor crearAutor(String nombre, Boolean alta){
        return new Autor(nombre, alta);
    }

    public static Autor crearAutorValidado(){
        Autor autor = new Autor();
        TecladoService teclado = new TecladoService();
        // todo terminar de validar
        autor.setNombre(teclado.ingresarString("Ingrese nombre del autor: "));
        autor.setAlta(teclado.ingresarBoolean("Ingrese true o false: "));
        return autor;
    }

    public static Autor crearAutorConIdValidado(){
        Autor autor = new Autor();
        TecladoService teclado = new TecladoService();
        autor.setId(teclado.ingresarInteger("Ingrese id del autor: "));
        autor.setNombre(teclado.ingresarString("Ingrese nombre del autor: "));
        autor.setAlta(teclado.ingresarBoolean("Ingrese true o false: "));
        return autor;
    }

    public void guardarAutor(Autor autor) throws ExceptionDAO {
        AutorBBDD a = new AutorBBDD();
        a.agregar(autor);
        a.closeEntityManager();
    }

    public void eliminarAutor(Integer id) throws ExceptionDAO {
        AutorBBDD a = new AutorBBDD();
        a.eliminar(id);
        a.closeEntityManager();
    }

    public void modificarAutor(Autor autor) throws ExceptionDAO {
        AutorBBDD a = new AutorBBDD();
        a.actualizar(autor);
        a.closeEntityManager();
    }

    public Autor obtenerAutor(int id) throws ExceptionDAO {
        AutorBBDD a = new AutorBBDD();
        Autor autor = a.obtener(id);
        a.closeEntityManager();
        return autor;
    }

    public List<Autor> listarAutor() throws ExceptionDAO {
        AutorBBDD a = new AutorBBDD();
        List<Autor> autores = a.listar();
        a.closeEntityManager();
        return autores;
    }

    public List<Autor> listaAutorPorNombre(String nombre) throws ExceptionDAO {
        AutorBBDD a = new AutorBBDD();
        return a.queryAutor(nombre);
    }
}
