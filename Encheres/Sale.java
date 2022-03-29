public class Sale extends DatabaseObject<Sale> {
  int sale_code;
  Lot lot;
  float start_price;
  String sale_type;
  boolean multiple_offer;
  boolean limited;
  TimePoint start_tstamp;
  TimePoint end_tstamp;
  boolean revocable;
  int room_code;

  Bid best_bid;
  boolean closed;

  public Sale(int _sale_code, Lot _lot, float _start_price, String _sale_type, boolean _multiple_offer, boolean _limited, TimePoint _start_tstamp, TimePoint _end_tstamp, boolean _revocable, int _room_code) {
    sale_code = _sale_code;
    lot = _lot;
    start_price = _start_price;
    sale_type = _sale_type;
    multiple_offer = _multiple_offer;
    limited = _limited;
    start_tstamp = _start_tstamp;
    end_tstamp = _end_tstamp;
    revocable = _revocable;
    room_code = _room_code;

    best_bid = null;
    closed = false;
  }

  public static ArrayList<Sale> fetch() {
		ArrayList<Sale> sales = new ArrayList<Sale>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Sale;");
		connection.closeConnection();
		while(fetched_lines.next()) {
      sales.add(new Sale(fetched_lines.getInt("sale_code"), Lot.fetch("lot_code = " + fetched_lines.getString("lot_code")), fetched_lines.getFloat("start_price"), fetched_lines.getString("sale_type"), fetched_lines.getBoolean("multiple_offer"), fetched_lines.getBoolean("limited"), new TimePoint(fetched_lines.getString("start_tstamp")), new TimePoint(fetched_lines.getString("end_tstamp")), fetched_lines.getBoolean("revocable"), fetched_lines.getInt("room_code")));
		}
		return sales;
	}

  public static ArrayList<Sale> fetch(String condition) {
    ArrayList<Sale> sales = new ArrayList<Sale>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Sale WHERE " + condition + ";");
		connection.closeConnection();
		while(fetched_lines.next()) {
      sales.add(new Sale(fetched_lines.getInt("sale_code"), Lot.fetch("lot_code = " + fetched_lines.getString("lot_code")), fetched_lines.getFloat("start_price"), fetched_lines.getString("sale_type"), fetched_lines.getBoolean("multiple_offer"), fetched_lines.getBoolean("limited"), new TimePoint(fetched_lines.getString("start_tstamp")), new TimePoint(fetched_lines.getString("end_tstamp")), fetched_lines.getBoolean("revocable"), fetched_lines.getInt("room_code")));
		}
		return sales;
	}

  public void save() {
		connection.openConnection();
		if(Sale.fetch("sale_code = " + this.sale_code).size() == 0) {
			this.connection.executeUpdate("INSERT INTO Sale VALUES (" + this.sale_code + ", " + this.lot.getCode() + ", " + this.start_price + ", " + this.sale_type + ", " + this.multiple_offer + ", " + this.limited + ", TO_DATE('" + this.start_tstamp.toSqlDateTime() + "', 'YYYY-MM-DD HH24:MI')" + ", TO_DATE('" + this.end_tstamp.toSqlDateTime() + "', 'YYYY-MM-DD HH24:MI')" + ", " + this.revocable + ", " + this.room_code + ");");
		} else {
			connection.executeUpdate("UPDATE Sale SET sale_code = " + this.sale_code + ", lot_code = " + this.lot.getCode() + ", start_price = " + this.start_price + ", sale_type = " + this.sale_type + ", multiple_offer = " + this.multiple_offer + ", limited = " + this.limited + ", start_tstamp = TO_DATE('" + this.start_tstamp.toSqlDateTime() + "', 'YYYY-MM-DD HH24:MI')" + ", end_tstamp = TO_DATE('" + this.end_tstamp.toSqlDateTime() + "', 'YYYY-MM-DD HH24:MI')" + ", revocable = " + this.revocable + ", room_code = " + this.room_code + " WHERE sale_code = " + this.sale_code + ";");
		}
		connection.closeConnection();
	}

  public int getId() {
    return sale_code;
  }

  public String show() {
    return sale_code + ". " + this.lot.show() + " : "+ best_bid.show() + "€\nClôture dans : " + (end_tstamp - TimePoint.now()).inMinutes();
  }

  public boolean placeNewBid(BaseUser bidder, Float amount) {
    Bid new_bid = new Bid(bidder, amount);
    if(this.best_bid == null || new_bid > this.best_bid) {
      this.best_bid = new_bid;
      new_bid.save();
      this.save();
      return true;
    } else {
      return false;
    }
  }

}
