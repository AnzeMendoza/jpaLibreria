package service;

import entity.Editorial;
import exception.ExceptionDAO;
import persistence.EditorialBBDD;

import java.util.List;

public class EditorialService {
    public static Editorial crearEditorial(String nombre, Boolean alta){
        return new Editorial(nombre, alta);
    }

    public static Editorial crearEditorialValidado(){
        Editorial ed = new Editorial();
        TecladoService teclado = new TecladoService();
        ed.setAlta(teclado.ingresarBoolean("Ingrese true o false: "));
        ed.setNombre(teclado.ingresarString("Ingrese nombre de la editorial: "));
        return ed;
    }

    public static Editorial crearEditorialConIdValidado(){
        Editorial ed = new Editorial();
        TecladoService teclado = new TecladoService();
        ed.setId(teclado.ingresarInteger("Ingrese id de la editorial: "));
        ed.setAlta(teclado.ingresarBoolean("Ingrese true o false: "));
        ed.setNombre(teclado.ingresarString("Ingrese nombre de la editorial: "));
        return ed;
    }

    public void guardarEditorial(Editorial editorial) throws ExceptionDAO {
        EditorialBBDD e = new EditorialBBDD();
        e.agregar(editorial);
        e.closeEntityManager();
    }

    public void eliminarEditorial(Integer id) throws ExceptionDAO {
        EditorialBBDD e = new EditorialBBDD();
        e.eliminar(id);
        e.closeEntityManager();
    }

    public void modificarEditorial(Editorial editorial) throws ExceptionDAO {
        EditorialBBDD e = new EditorialBBDD();
        e.actualizar(editorial);
        e.closeEntityManager();
    }

    public Editorial obtenerEditorial(Integer id) throws ExceptionDAO {
        EditorialBBDD e = new EditorialBBDD();
        Editorial editorial = e.obtener(id);
        return editorial;
    }

    public List<Editorial> listarEditorial() throws ExceptionDAO {
        EditorialBBDD e = new EditorialBBDD();
        List<Editorial> editoriales = e.listar();
        return editoriales;
    }

}
