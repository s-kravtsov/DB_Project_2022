/*
StockManagement represente une instance qui effectue toutes les operations sur le stock des produits.
*/

public class Stock extends DatabaseObject<Stock> {
  int stock_code;
  BaseUser owner;
  Product product;
  int quantity;

  public Stock(int _stock_code, BaseUser _user, Product _product, int _quantity) {
    stock_code = _stock_code;
    owner = _user;
    product = _product;
    quantity = _quantity;
  }

  public static ArrayList<Stock> fetch() {
		ArrayList<Stock> stock_lines = new ArrayList<Stock>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Stock;");
		connection.closeConnection();
		while(fetched_lines.next()) {
      stock_lines.add(new Stock(fetched_lines.getInt("stock_code"), BaseUser.fetch("user_code = " + fetched_lines.getString("user_code")), Product.fetch("product_code = " + fetched_lines.getString("product_code")), fetched_lines.getInt("quantity")));
		}
		return stock_lines;
	}

  public static ArrayList<Stock> fetch(String condition) {
    ArrayList<Stock> stock_lines = new ArrayList<Stock>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Stock WHERE " + condition + ";");
		connection.closeConnection();
		while(fetched_lines.next()) {
      stock_lines.add(new Stock(fetched_lines.getInt("stock_code"), BaseUser.fetch("user_code = " + fetched_lines.getString("user_code")), Product.fetch("product_code = " + fetched_lines.getString("product_code")), fetched_lines.getInt("quantity")));
		}
		return stock_lines;
	}

  public void save() {
		connection.openConnection();
		if(Stock.fetch("stock_code = " + this.stock_code).size() == 0) {
			this.connection.executeUpdate("INSERT INTO Stock VALUES (" + this.stock_code + ", " + this.owner.getCode() + ", " + this.product.getCode() + ", " + this.quantity + ");");
		} else {
			connection.executeUpdate("UPDATE Stock SET stock_code = " + this.stock_code + ", user_code = " + this.owner.getCode() + ", product_code = " + this.product.getCode() + ", quantity = " + this.quantity + " WHERE stock_code = " + this.stock_code + ";");
		}
		connection.closeConnection();
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
