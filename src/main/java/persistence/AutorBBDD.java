package persistence;

import entity.Autor;
import exception.ExceptionDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class AutorBBDD implements AutorDAO{

    private EntityManager em = null;

    public AutorBBDD() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
        em = emf.createEntityManager();
    }

    private EntityManager getEntityManager(){
        return em;
    }

    @Override
    public void agregar(Autor autor) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            if((autor.getId() != null) && (getEntityManager().find(Autor.class, autor.getId()) == null)){
                throw new ExceptionDAO("Ya existe un [Autor] con el mismo id");
            }

            getEntityManager().persist(autor);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe){
            getEntityManager().getTransaction().rollback();
            throw new ExceptionDAO("Excepcion al agregar [Autor]" + pe);
        }
    }

    @Override
    public void eliminar(Integer id) throws ExceptionDAO {
        try{
            getEntityManager().getTransaction().begin();
            Autor autorAborrar = (Autor) getEntityManager().find(Autor.class, id);

            if(autorAborrar == null){
                throw new ExceptionDAO("No se encontro el [Autor] en la tabla");
            }
            getEntityManager().remove(autorAborrar);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe){
            throw new ExceptionDAO("Problemas al eliminar un [Autor]" + pe);
        }
    }

    @Override
    public void actualizar(Autor autor) throws ExceptionDAO {
        try {
            getEntityManager().getTransaction().begin();
            if((autor.getId() != null) && (getEntityManager().find(Autor.class, autor.getId()) == null)){
                throw new ExceptionDAO("No se encontro al autor en la tabla");
            }
            autor = getEntityManager().merge(autor);
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException pe){
            throw new ExceptionDAO("Problema al actualizar [Autor]" + pe);
        }
    }

    @Override
    public Autor obtener(Integer id) throws ExceptionDAO {
        Autor autor;
        try{
            getEntityManager().getTransaction().begin();
            autor = getEntityManager().find(Autor.class, id);
            if( autor == null){
                throw new ExceptionDAO("No se encontro al autor en la tabla");
            }
        }catch (PersistenceException pe){
            throw new ExceptionDAO("Problema al obtener un autor" + pe);
        }
        return autor;
    }

    @Override
    public List<Autor> listar() throws ExceptionDAO {
        List<Autor> autores = new ArrayList<>();
        try{
            getEntityManager().getTransaction().begin();
            autores = getEntityManager().createQuery("from Autor").getResultList();

        } catch (PersistenceException pe){
            throw new ExceptionDAO("Problema al listar [autores]" + pe);
        }
        return autores;
    }

    public void closeEntityManager(){
        em.close();
    }

    public List<Autor> queryAutor(String campo) throws ExceptionDAO {
        String query = String.format("SELECT a FROM Autor a WHERE a.nombre = '%s'", campo);
        List<Autor> autores = new ArrayList<>();
        try{
            autores = getEntityManager().createQuery(query).getResultList();
        } catch (PersistenceException pe){
            throw new ExceptionDAO("Problema al listar [autores]" + pe);
        }
        return autores;
    }
}
