package menu;

import exception.ExceptionDAO;
import service.AutorService;
import service.EditorialService;
import service.LibroService;
import service.TecladoService;

public class Menu {

    private int opcionPrincipal;
    private int opcionSecundaria;
    TecladoService teclado = new TecladoService();
    private final String[] TABLAS = {"Autor", "Editorial", "Libro"};

    public void mostrarMenuPrincipal() {
        System.out.println("Eliga la tabla con la cual quiere interactuar");
        System.out.println(" 1 - Autor");
        System.out.println(" 2 - Editorial");
        System.out.println(" 3 - Libro");
        System.out.println(" 0 - Salir");
        opcionPrincipal = teclado.ingresarInteger("ingrese opcion principal: ");
    }

    public void mostrarMenuSecundario() {
        System.out.println("-----------" + TABLAS[opcionPrincipal - 1] + "-------------");
        System.out.println(" 1 - Agregar");
        System.out.println(" 2 - Eliminar");
        System.out.println(" 3 - Modificar");
        System.out.println(" 4 - Obtener");
        System.out.println(" 5 - Listar toda la tabla");
        if (opcionPrincipal - 1 == 0) {
            System.out.println(" 6 - Busqueda de Autor por nombre");
        }
        if (opcionPrincipal - 1 == 2) {
            System.out.println(" 6 - Busqueda de un libro por isbn");
            System.out.println(" 7 - Busqueda de libro por titulo");
            System.out.println(" 8 - Busqueda de libro/os por nombre de autor");
            System.out.println(" 9 - Busqueda de libro/os por nombre de editorial");
        }
        System.out.println(" 0 - Volver al menu principal");
        opcionSecundaria = teclado.ingresarInteger("ingrese opcion secundaria: ");
    }

    public void managerMenu() throws ExceptionDAO {
        do {
            mostrarMenuPrincipal();
            do {
                if (validarOpcionPrincipal() && opcionPrincipal != 0)
                    mostrarMenuSecundario();
                validarOpcionSecundaria();
                ejecutarOpciones();
            } while (opcionSecundaria != 0);
        } while (opcionPrincipal != 0);
        System.out.println("Gracias vuelvan pronto!");
    }

    private boolean validarOpcionSecundaria() {
        if (opcionSecundaria > -1 && opcionSecundaria < 10) {
            return true;
        }
        System.out.println("Ingrese una opción secundaria valida");
        return false;
    }

    private boolean validarOpcionPrincipal() {
        if (opcionPrincipal > -1 && opcionPrincipal < 4) {
            return true;
        }
        System.out.println("Ingrese una opción principal valida");
        return false;
    }

    private void ejecutarOpciones() throws ExceptionDAO {
        switch (opcionPrincipal) {
            case 1:
                AutorService as = new AutorService();
                switch (opcionSecundaria) {
                    case 1:
                        as.guardarAutor(AutorService.crearAutorValidado());
                        break;
                    case 2:
                        as.eliminarAutor(teclado.ingresarInteger("Ingrese el id del autor a eliminar: "));
                        break;
                    case 3:
                        as.modificarAutor(AutorService.crearAutorConIdValidado());
                        break;
                    case 4:
                        System.out.println((as.obtenerAutor(teclado.ingresarInteger("ingrese id de autor: "))));
                        break;
                    case 5:
                        System.out.println(as.listarAutor());
                        break;
                    case 6:
                        System.out.println(as.listaAutorPorNombre(teclado.ingresarString("Ingrese nombre del autor: ")));
                        break;
                    default:
                        opcionSecundaria = 0;
                }
                break;
            case 2:
                EditorialService es = new EditorialService();
                switch (opcionSecundaria) {
                    case 1:
                        es.guardarEditorial(EditorialService.crearEditorialValidado());
                        break;
                    case 2:
                        es.eliminarEditorial(teclado.ingresarInteger("Ingrese id de la editorial: "));
                        break;
                    case 3:
                        es.modificarEditorial(EditorialService.crearEditorialConIdValidado());
                        break;
                    case 4:
                        System.out.println(es.obtenerEditorial(teclado.ingresarInteger("Ingresar id de la editorial: ")));
                        break;
                    case 5:
                        System.out.println(es.listarEditorial());
                        break;
                    default:
                        opcionSecundaria = 0;
                }
                break;
            case 3:
                LibroService ls = new LibroService();
                switch (opcionSecundaria) {
                    case 1:
                        ls.guardarLibro(LibroService.crearLibroValidado());
                        break;
                    case 2:
                        ls.eliminarLibro(teclado.ingresarLong("Ingresar isbn del libro a eliminar: "));
                        break;
                    case 3:
                        ls.modificarLibro(LibroService.crearLibroValidado());
                        break;
                    case 4:
                        System.out.println(ls.obtenerLibro(teclado.ingresarLong("Ingrese el isbn")));
                        break;
                    case 5:
                        System.out.println(ls.listarLibros());
                        break;
                    case 6:
                        System.out.println(ls.busquedaPorTitulo(teclado.ingresarString("Ingrese el titulo del libro: ")));
                        break;
                    case 7:
                        System.out.println(ls.busquedaPorNombre(teclado.ingresarString("Ingrese el nombre del libro: ")));
                        break;
                    case 8:
                        System.out.println(ls.busquedaPorEditorial(teclado.ingresarString("Ingrese nombre de la editorial: ")));
                        break;
                    default:
                        opcionSecundaria = 0;
                }
                break;
            default:
                opcionPrincipal = 0;
                opcionSecundaria = 0;
        }
    }
}
