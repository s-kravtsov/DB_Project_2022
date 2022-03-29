import java.util.ArrayList;
import java.sql.*;
/*
La classe principale pour entrer dans le programme.
*/

public class Main {
  public static void main(String[] args) {
    Building building = Building.getInstance();

    boolean programm_running = true;
    while(programm_running) {
      programm_running = building.startSaleCycle();
    }
    System.out.println("Merci d'utiliser notre site des ventes aux enchères. À la prochaine !");
  }
}
