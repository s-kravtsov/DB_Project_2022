/*
Building represente le site des enchères. Il contient des Salles dans lequels on peut "entrer" pour
enchèrir sur les differents types des produits.
*/
public class Building {
  private static Building instance;

  ArrayList<Room> rooms;
  BaseUser user;

  // Le constructeur
  private Building() {
    rooms = Room.fetch();
    user = BaseUser.identify();
  }

  // Crée une instance si elle est pas créée et la retourne dans le code
  public static Building getInstance() {
    if(instance == null) {
      instance = new Building();
    }
    return instance;
  }

  // Affiche les action possibles dans le batiment et execute une action en fonction du choix de l'utilisateur
  public boolean startSaleCycle() {
    System.out.println("Bienvenue sur notre site des ventes aux enchères !\n\nChoisissez la salle qui vous intéresse : ");
    for(Room room : this.rooms) {
      System.out.println(room.show());
    }

    String user_choice = System.console().readLine();

    if(user_choice == "exit") {
      return false;
    }

    for(Room room : this.rooms) {
      if(room.getCode() == Integer.parseInt(user_choice)) {
        room.enter(user);
      }
    }

    return true;
  }
}
