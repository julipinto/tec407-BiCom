package bicom;

import facade.Facade;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class BiCom {

    public static void main(String[] args) throws NotBoundException {
        int servidor = options();
        
        while (servidor < 1 || servidor > 3){
            System.out.println("Opção errada");
            servidor = options();
        }
        
        Facade facade = Facade.getInstance();
        facade.initializeServer(servidor);
    }
    
    public static int options(){
        System.out.println("Qual servidor deseja iniciar?");
        System.out.println("1 - Azul");
        System.out.println("2 - Gol");
        System.out.println("3 - Latam");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    
    
}
