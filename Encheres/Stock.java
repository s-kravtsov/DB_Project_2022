import java.util.ArrayList;
import java.sql.*;

/*
StockManagement represente une instance qui effectue toutes les operations sur le stock des produits.
*/

public class Stock {
  int stock_code;
  BaseUser owner;
  Product product;
  int quantity;

  public static DatabaseConnection connection = DatabaseConnection.getInstance();


  public Stock(int _stock_code, BaseUser _user, Product _product, int _quantity) {
    stock_code = _stock_code;
    owner = _user;
    product = _product;
    quantity = _quantity;
  }

  public static ArrayList<Stock> fetch() {
		ArrayList<Stock> stock_lines = new ArrayList<Stock>();
		Stock.connection.openConnection();
		ResultSet fetched_lines = Stock.connection.executeQuery("SELECT * FROM Stock;");
		Stock.connection.closeConnection();
		while(fetched_lines.next()) {
      stock_lines.add(new Stock(fetched_lines.getInt("stock_code"), BaseUser.fetch("user_code = " + fetched_lines.getString("user_code")).get(0), Product.fetch("product_code = " + fetched_lines.getString("product_code")).get(0), fetched_lines.getInt("quantity")));
		}
		return stock_lines;
	}

  public static ArrayList<Stock> fetch(String condition) {
    ArrayList<Stock> stock_lines = new ArrayList<Stock>();
		Stock.connection.openConnection();
		ResultSet fetched_lines = Stock.connection.executeQuery("SELECT * FROM Stock WHERE " + condition + ";");
		Stock.connection.closeConnection();
		while(fetched_lines.next()) {
      stock_lines.add(new Stock(fetched_lines.getInt("stock_code"), BaseUser.fetch("user_code = " + fetched_lines.getString("user_code")).get(0), Product.fetch("product_code = " + fetched_lines.getString("product_code")).get(0), fetched_lines.getInt("quantity")));
		}
		return stock_lines;
	}

  public void save() {
		Stock.connection.openConnection();
		if(Stock.fetch("stock_code = " + this.stock_code).size() == 0) {
			Stock.connection.executeUpdate("INSERT INTO Stock VALUES (" + this.stock_code + ", " + this.owner.getCode() + ", " + this.product.getCode() + ", " + this.quantity + ");");
		} else {
			Stock.connection.executeUpdate("UPDATE Stock SET stock_code = " + this.stock_code + ", user_code = " + this.owner.getCode() + ", product_code = " + this.product.getCode() + ", quantity = " + this.quantity + " WHERE stock_code = " + this.stock_code + ";");
		}
		Stock.connection.closeConnection();
	}

  public String show() {
    return this.stock_code + ". Produit : " + this.product.show() + ", Quantité : " + this.quantity + ", Proprietaire : " + this.owner.show();
  }

  public String glance() {
    return this.product.show();
  }

  public int getCode() {
    return stock_code;
  }
}
