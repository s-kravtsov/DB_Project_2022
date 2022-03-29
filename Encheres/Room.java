import java.util.ArrayList;
import java.sql.*;

/*
Room representeune salle de vente où on peut enchèrir sur les produits d'une certaine categorie.
*/
public class Room {
	int room_code;
	Category category;

	public static DatabaseConnection connection = DatabaseConnection.getInstance();


	// Le constructeur privé
	private Room(int _room_code, Category _category) {
		room_code = _room_code;
		category = _category;

	}

	public static ArrayList<Room> fetch() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		Room.connection.openConnection();
		ResultSet fetched_lines = Room.connection.executeQuery("SELECT * FROM Room;");
		Room.connection.closeConnection();
		while(fetched_lines.next()) {
			rooms.add(new Room(fetched_lines.getInt("room_code"), Category.fetch("category_code = " + fetched_lines.getInt("category_code")).get(0)));
		}
		return rooms;
	}

  public static ArrayList<Room> fetch(String condition) {
		ArrayList<Room> rooms = new ArrayList<Room>();
		Room.connection.openConnection();
		ResultSet fetched_lines = Room.connection.executeQuery("SELECT * FROM Room WHERE " + condition + ";");
		Room.connection.closeConnection();
		while(fetched_lines.next()) {
			rooms.add(new Room(fetched_lines.getInt("room_code"), Category.fetch("category_code = " + fetched_lines.getInt("category_code")).get(0)));
		}
		return rooms;
	}

  public void save() {
		Room.connection.openConnection();
		if(Room.fetch("room_code = " + this.room_code).size() == 0) {
			Room.connection.executeUpdate("INSERT INTO Room VALUES (" + this.room_code + ", " + this.category.getCode() + ");");
		} else {
			Room.connection.executeUpdate("UPDATE Room SET room_code = " + this.room_code + ", category_code = " + this.category.getCode() + " WHERE room_code = " + this.room_code + ";");
		}
		Room.connection.closeConnection();
	}

	public int getCode() {
		return this.room_code;
	}

	public String show() {
		return "Salle " + this.room_code + " : " + this.category.show();
	}

	public void enter(BaseUser user) {
		ArrayList<Sale> sales = Sale.fetch("room_code = " + this.room_code);
		System.out.println("Bienvenue dans la salle \"" + this.show() + "\"");
		System.out.println("Voici les ventes en cours :");
		for(Sale sale : sales) {
			System.out.println(sale.show());
		}
		System.out.println("Choisissez la vente pour encherir :");
		String user_choice = System.console().readLine();

    if(user_choice == "exit") {
      return;
    }

		for(Sale sale : sales) {
			if(sale.getCode() == Integer.parseInt(user_choice)) {
				user.bid(sale);
			}
		}
	}

}
