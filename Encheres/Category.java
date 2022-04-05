import java.util.ArrayList;
import java.sql.*;

public class Category {
  int category_code;
  String category_name;

  public static DatabaseConnection connection = DatabaseConnection.getInstance();

  public Category(int _category_code, String _category_name) {
    category_code = _category_code;
    category_name = _category_name;
  }

  public static ArrayList<Category> fetch() {
		ArrayList<Category> categories = new ArrayList<Category>();
    try {
      Category.connection.openConnection();
  		ResultSet fetched_lines = Category.connection.executeQuery("SELECT * FROM Category");
  		Category.connection.closeConnection();
  		while(fetched_lines.next()) {
        categories.add(new Category(fetched_lines.getInt("category_code"), fetched_lines.getString("category_name")));
  		}
    } catch (SQLException e) {
      e.printStackTrace();
    }

		return categories;
	}

  public static ArrayList<Category> fetch(String condition) {
    ArrayList<Category> categories = new ArrayList<Category>();
    try {
      Category.connection.openConnection();
  		ResultSet fetched_lines = Category.connection.executeQuery("SELECT * FROM Category WHERE " + condition + "");
  		Category.connection.closeConnection();
  		while(fetched_lines.next()) {
        categories.add(new Category(fetched_lines.getInt("category_code"), fetched_lines.getString("category_name")));
  		}
    } catch (SQLException e) {
      e.printStackTrace();
    }

		return categories;
	}

  public void save() {
		Category.connection.openConnection();
		if(Category.fetch("category_code = " + this.category_code).size() == 0) {
			Category.connection.executeUpdate("INSERT INTO Category VALUES (" + this.category_code + ", " + this.category_name + ")");
		} else {
			Category.connection.executeUpdate("UPDATE Category SET category_code = " + this.category_code + ", category_name = " + this.category_name + " WHERE category_code = " + this.category_code + "");
		}
		Category.connection.closeConnection();
	}

  public int getCode() {
    return category_code;
  }

  public String show() {
    return this.category_code + ". " + this.category_name;
  }
}
