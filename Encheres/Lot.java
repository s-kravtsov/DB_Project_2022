import java.util.ArrayList;
import java.sql.*;

class Lot {
  int lot_code;
  Stock stock_line;
  int quantity_to_sell;

  public static DatabaseConnection connection = DatabaseConnection.getInstance();


  public Lot(int _lot_code, Stock _stock_line, int _quantity_to_sell) {
    lot_code = _lot_code;
    stock_line = _stock_line;
    quantity_to_sell = _quantity_to_sell;
  }

  public static ArrayList<Lot> fetch() {
		ArrayList<Lot> lots = new ArrayList<Lot>();
    try {
      Lot.connection.openConnection();
  		ResultSet fetched_lines = Lot.connection.executeQuery("SELECT * FROM Lot;");
  		Lot.connection.closeConnection();
  		while(fetched_lines.next()) {
        lots.add(new Lot(fetched_lines.getInt("lot_code"), Stock.fetch("stock_code = " + fetched_lines.getString("stock_code")).get(0), fetched_lines.getInt("quantity_to_sell")));
  		}
    } catch (SQLException e) {
      e.printStackTrace();
    }

		return lots;
	}

  public static ArrayList<Lot> fetch(String condition) {
    ArrayList<Lot> lots = new ArrayList<Lot>();
    try {
      Lot.connection.openConnection();
  		ResultSet fetched_lines = Lot.connection.executeQuery("SELECT * FROM Lot WHERE " + condition + ";");
  		Lot.connection.closeConnection();
  		while(fetched_lines.next()) {
        lots.add(new Lot(fetched_lines.getInt("lot_code"), Stock.fetch("stock_code = " + fetched_lines.getString("stock_code")).get(0), fetched_lines.getInt("quantity_to_sell")));
  		}
    } catch (SQLException e) {
      e.printStackTrace();
    }

		return lots;
	}

  public void save() {
		Lot.connection.openConnection();
		if(Lot.fetch("lot_code = " + this.lot_code).size() == 0) {
			Lot.connection.executeUpdate("INSERT INTO Lot VALUES (" + this.lot_code + ", " + this.stock_line.getCode() + ", " + this.quantity_to_sell + ");");
		} else {
			Lot.connection.executeUpdate("UPDATE Lot SET lot_code = " + this.lot_code + ", stock_code = " + this.stock_line.getCode() + ", quantity_to_sell = " + this.quantity_to_sell + " WHERE lot_code = " + this.lot_code + ";");
		}
		Lot.connection.closeConnection();
	}

  public int getCode() {
    return this.lot_code;
  }

  public String show() {
    return this.lot_code + ". " + stock_line.glance() + " Quantité disponible : " + quantity_to_sell;
  }


}
