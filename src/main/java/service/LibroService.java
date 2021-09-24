package service;

import entity.Autor;
import entity.Editorial;
import entity.Libro;
import exception.ExceptionDAO;
import persistence.LibroBBDD;

import java.util.ArrayList;
import java.util.List;

public class LibroService {

    public static Libro crearLibro(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
                                   Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        return new Libro(titulo, anio, ejemplares, ejemplaresPrestados,
                ejemplaresRestantes, alta, autor, editorial);
    }

    public static Libro crearLibroValidado() {
        TecladoService teclado = new TecladoService();
        Libro libro = new Libro();

        libro.setTitulo(teclado.ingresarString("Ingrese titulo: "));
        libro.setAnio(teclado.ingresarInteger("Ingrese el año: "));
        libro.setEjemplares(teclado.ingresarInteger("Ingrese la cantidad de ejemplares: "));
        libro.setEjemplares(teclado.ingresarInteger("ingrese la cantidad de ejemplares prestados: "));
        libro.setEjemplaresRestantes(teclado.ingresarInteger("Ingrese la cantidad de ejemplares restantes: "));
        libro.setAlta(teclado.ingresarBoolean("Esta en alta? [false o true]: "));
        libro.setAutor(AutorService.crearAutorValidado());
        libro.setEditorial(EditorialService.crearEditorialValidado());
        return libro;
    }

    public void guardarLibro(Libro libro) throws ExceptionDAO {
        LibroBBDD l = new LibroBBDD();
        l.agregar(libro);
        l.closeEntityManager();
    }

    public void eliminarLibro(Long isbn) throws ExceptionDAO {
        LibroBBDD l = new LibroBBDD();
        l.eliminar(isbn);
        l.closeEntityManager();
    }

    public void modificarLibro(Libro libro) throws ExceptionDAO {
        LibroBBDD l = new LibroBBDD();
        l.actualizar(libro);
        l.closeEntityManager();
    }

    public Libro obtenerLibro(Long isbn) throws ExceptionDAO {
        LibroBBDD l = new LibroBBDD();
        Libro libro = new Libro();
        l.obtener(isbn);
        l.closeEntityManager();
        return libro;
    }
    public List<Libro> listarLibros() throws ExceptionDAO {
        LibroBBDD l = new LibroBBDD();
        List<Libro> libros = l.listar();
        l.closeEntityManager();
        return libros;
    }

    public List<Libro> busquedaPorTitulo(String titulo) throws ExceptionDAO {
        List<Libro> libros = new ArrayList<>();
        LibroBBDD l = new LibroBBDD();
        libros = l.queryLibro("titulo", titulo);
        return libros;
    }

    public List<Libro> busquedaPorNombre(String nombre) throws ExceptionDAO {
        List<Libro> libros = new ArrayList<>();
        LibroBBDD l = new LibroBBDD();
        libros = l.queryLibro("nombre", nombre);
        return libros;
    }

    public List<Libro> busquedaPorEditorial(String editorial) throws ExceptionDAO {
        List<Libro> libros = new ArrayList<>();
        LibroBBDD l = new LibroBBDD();
        libros = l.queryLibro("editorial", editorial);
        return libros;
    }
}