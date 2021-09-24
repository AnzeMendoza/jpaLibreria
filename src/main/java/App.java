import exception.ExceptionDAO;
import menu.Menu;

public class App {
    public static void main(String[] args) throws ExceptionDAO {
        Menu menu = new Menu();
        menu.managerMenu();
    }
}
