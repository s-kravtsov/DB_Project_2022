class Lot extends DatabaseObject<Lot> {
  int lot_code;
  Stock stock_line;
  int quantity_to_sell;

  public Lot(int _lot_code, StockLine _stock_line, int _quantity_to_sell) {
    lot_code = _lot_code;
    stock_line = _stock_line;
    quantity_to_sell = _quantity_to_sell;
  }

  public static ArrayList<Lot> fetch() {
		ArrayList<Lot> lots = new ArrayList<Lot>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Lot;");
		connection.closeConnection();
		while(fetched_lines.next()) {
      lots.add(new Lot(fetched_lines.getInt("lot_code"), Stock.fetch("stock_code = " + fetched_lines.getString("stock_code")), fetched_lines.getInt("quantity_to_sell")));
		}
		return lots;
	}

  public static ArrayList<Lot> fetch(String condition) {
    ArrayList<Lot> lots = new ArrayList<Lot>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Lot WHERE " + condition + ";");
		connection.closeConnection();
		while(fetched_lines.next()) {
      lots.add(new Lot(fetched_lines.getInt("lot_code"), Stock.fetch("stock_code = " + fetched_lines.getString("stock_code")), fetched_lines.getInt("quantity_to_sell")));
		}
		return lots;
	}

  public void save() {
		connection.openConnection();
		if(Sale.fetch("sale_code = " + this.sale_code).size() == 0) {
			this.connection.executeUpdate("INSERT INTO Lot VALUES (" + this.lot_code + ", " + this.stock_line.getCode() + ", " + this.quantity_to_sell + ");");
		} else {
			connection.executeUpdate("UPDATE Lot SET lot_code = " + this.lot_code + ", stock_code = " + this.stock_line.getCode() + ", quantity_to_sell = " + this.quantity_to_sell + " WHERE lot_code = " + this.lot_code + ";");
		}
		connection.closeConnection();
	}

  public int getCode() {
    return this.lot_code;
  }

  public string show() {
    return this.lot_code + ". " + stock_line.glance() + " Quantité disponible : " + quantity_to_sell;
  }


}
