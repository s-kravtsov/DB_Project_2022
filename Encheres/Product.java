import java.util.ArrayList;
import java.sql.*;

public class Product {
  int product_code;
  String product_name;
  float product_cost;
  Category category;

  public static DatabaseConnection connection = DatabaseConnection.getInstance();


  public Product(int _product_code, String _product_name, float _product_cost, Category _category) {
    product_code = _product_code;
    product_name = _product_name;
    product_cost = _product_cost;
    category = _category;
  }

  public static ArrayList<Product> fetch() {
		ArrayList<Product> products = new ArrayList<Product>();
		Product.connection.openConnection();
		ResultSet fetched_lines = Product.connection.executeQuery("SELECT * FROM Product;");
		Product.connection.closeConnection();
		while(fetched_lines.next()) {
      products.add(new Product(fetched_lines.getInt("product_code"), fetched_lines.getString("product_name"), fetched_lines.getFloat("product_cost"), Category.fetch("category_code = " + fetched_lines.getInt("category_code")).get(0)));
		}
		return products;
	}

  public static ArrayList<Product> fetch(String condition) {
    ArrayList<Product> products = new ArrayList<Product>();
		Product.connection.openConnection();
		ResultSet fetched_lines = Product.connection.executeQuery("SELECT * FROM Product WHERE " + condition + ";");
		Product.connection.closeConnection();
		while(fetched_lines.next()) {
      products.add(new Product(fetched_lines.getInt("product_code"), fetched_lines.getString("product_name"), fetched_lines.getFloat("product_cost"), Category.fetch("category_code = " + fetched_lines.getInt("category_code")).get(0)));
		}
		return products;
	}

  public void save() {
		Product.connection.openConnection();
		if(Product.fetch("product_code = " + this.product_code).size() == 0) {
			Product.connection.executeUpdate("INSERT INTO Product VALUES (" + this.product_code + ", " + this.product_name + ", " + this.product_cost + ", " + this.category.getCode() + ");");
		} else {
			Product.connection.executeUpdate("UPDATE Product SET product_code = " + this.product_code + ", product_name = " + this.product_name + ", product_cost = " + this.product_cost + ", category_code = " + this.category.getCode() + " WHERE product_code = " + this.product_code + ";");
		}
		Product.connection.closeConnection();
	}

  public int getCode() {
    return product_code;
  }

  public String show() {
    return this.product_code + ". " + this.product_name;
  }
}
