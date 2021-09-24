package service;

import java.util.Scanner;

public class TecladoService {
    private Scanner leer = new Scanner(System.in);

    public String ingresarString(String msg){
        System.out.print(msg);
        return leer.useDelimiter("\n").next();
    }

    public Integer ingresarInteger(String msg){
        System.out.print(msg);
        return leer.nextInt();
    }

    public Long ingresarLong(String msg){
        System.out.print(msg);
        return leer.nextLong();
    }

    public boolean ingresarBoolean(String msg){
        System.out.print(msg);
        return leer.nextBoolean();
    }
}
