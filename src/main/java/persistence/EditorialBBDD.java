package persistence;

import entity.Autor;
import entity.Editorial;
import exception.ExceptionDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class EditorialBBDD implements EditorialDAO {

    private EntityManager em;

    public EditorialBBDD() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void agregar(Editorial editorial) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            if ((editorial.getId() != null) && (getEntityManager().find(Editorial.class, editorial.getId()) == null)) {
                throw new ExceptionDAO("Ya existe una [Editorial] con el mismo id");
            }

            getEntityManager().persist(editorial);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe) {
            getEntityManager().getTransaction().rollback();
            throw new ExceptionDAO("Excepcion al agregar [Autor]" + pe);
        }
    }

    @Override
    public void eliminar(Integer id) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            Editorial editorialAborrar = (Editorial) getEntityManager().find(Editorial.class, id);

            if (editorialAborrar == null) {
                throw new ExceptionDAO("No se encontro el [Editorial] en la tabla");
            }
            getEntityManager().remove(editorialAborrar);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problemas al eliminar un [Editorial]" + pe);
        }
    }

    @Override
    public void actualizar(Editorial editorial) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            if ((editorial.getId() != null) && (getEntityManager().find(Autor.class, editorial.getId()) == null)) {
                throw new ExceptionDAO("No se encontro al editorial en la tabla");
            }
            editorial = getEntityManager().merge(editorial);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al actualizar [Autor]" + pe);
        }
    }

    @Override
    public Editorial obtener(Integer id) throws ExceptionDAO {
        Editorial editorial;
        try {
            getEntityManager().getTransaction().begin();
            editorial = getEntityManager().find(Editorial.class, id);
            if (editorial == null) {
                throw new ExceptionDAO("No se encontro al editorial en la tabla");
            }
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al obtener un editorial" + pe);
        }
        return editorial;
    }

    @Override
    public List<Editorial> listar() throws ExceptionDAO {
        List<Editorial> editoriales = new ArrayList<>();
        try {
            getEntityManager().getTransaction().begin();
            editoriales = getEntityManager().createQuery("from Editorial").getResultList();
        } catch (PersistenceException pe) {
            throw new ExceptionDAO("Problema al listar [libro]" + pe);
        }
        return editoriales;
    }

    public void closeEntityManager() {
        getEntityManager().close();
    }
}
