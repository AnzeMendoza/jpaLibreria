package persistence;

import entity.Autor;
import entity.Editorial;
import entity.Libro;
import exception.ExceptionDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class LibroBBDD implements LibroDAO {

    private EntityManager em = null;

    public LibroBBDD() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void agregar(Libro libro) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();

            if ((libro.getIsbn() != null) && (getEntityManager().find(Libro.class, libro.getIsbn()) == null)) {
                throw new ExceptionDAO("Ya existe un [Libro] con el mismo isbn ");
            }

            getEntityManager().persist(libro);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe) {
            getEntityManager().getTransaction().rollback();
            throw new ExceptionDAO("Excepcion al agregar [Libro]" + pe);
        }
    }

    @Override
    public void eliminar(Long isbn) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            Libro libroAborrar = (Libro) getEntityManager().find(Libro.class, isbn);

            if (libroAborrar == null) {
                throw new ExceptionDAO("No se encontro el [Autor] en la tabla");
            }
            getEntityManager().remove(libroAborrar);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problemas al eliminar un [Autor]" + pe);
        }
    }

    @Override
    public void actualizar(Libro libro) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            if (getEntityManager().find(Libro.class, libro.getIsbn()) == null) {
                throw new ExceptionDAO("No se encontro al autor en la tabla");
            }
            libro = getEntityManager().merge(libro);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al actualizar [Autor]" + pe);
        }
    }

    @Override
    public Libro obtener(Long isbn) throws ExceptionDAO {
        Libro libro;
        try {
            getEntityManager().getTransaction().begin();
            libro = getEntityManager().find(Libro.class, isbn);
            if (libro == null) {
                throw new ExceptionDAO("No se encontro al libro en la tabla");
            }
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al obtener un libro" + pe);
        }
        return libro;
    }

    @Override
    public List<Libro> listar() throws ExceptionDAO {
        List<Libro> libros = new ArrayList<>();

        try {
            getEntityManager().getTransaction().begin();
            libros = getEntityManager().createQuery("from Libro").getResultList();

        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al listar [libro]" + pe);
        }
        return libros;
    }

    public void closeEntityManager() {
        getEntityManager().close();
    }

    public List<Libro> queryLibro(String campo, String data) throws ExceptionDAO {
        String query = String.format("SELECT l FROM Libro l WHERE l.%s = '%s'", campo, data);
        List<Libro> libros = new ArrayList<>();
        try {
            libros = getEntityManager().createQuery(query).getResultList();
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al listar [Libro]" + pe);
        }
        return libros;
    }
}
