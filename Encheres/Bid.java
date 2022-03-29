public class Bid extends DatabaseObject<Bid> {
  int bid_code;
  int sale_code;
  BaseUser user;
  boolean accepted;
  float amount;
  TimePoint tstamp;


  public Bid(int sale_code, BaseUser _user, float _amount) {
    int new_code = 0;
    connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT MAX(bid_code) as max_code FROM Bid;");
		connection.closeConnection();
		while(fetched_lines.next()) {
      new_code = fetched_lines.getInt("max_code");
		}
    user = _user;
    accepted = false;
    amount = _amount;
    tstamp = TimePoint.now();
  }

  public Bid(int _bid_code, int sale_code, BaseUser _user, boolean _accepted, float _amount, TimePoint _tstamp) {
    bid_code = _bid_code;
    sale_code = _sale_code;
    user = _user;
    amount = _amount;
    tstamp = _tstamp;
  }

  public static ArrayList<Bid> fetch() {
		ArrayList<Bid> bids = new ArrayList<Bid>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Bid;");
		connection.closeConnection();
		while(fetched_lines.next()) {
      bids.add(new Bid(fetched_lines.getInt("bid_code"), fetched_lines.getInt("sale_code"), BaseUser.fetch("user_code = " + fetched_lines.getString("user_code")), fetched_lines.getBoolean("accepted"), fetched_lines.getFloat("amount"), new TimePoint(fetched_lines.getString("tstamp"))));
		}
		return bids;
	}

  public static ArrayList<Bid> fetch(String condition) {
    ArrayList<Bid> bids = new ArrayList<Bid>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM Bid WHERE " + condition + ";");
		connection.closeConnection();
		while(fetched_lines.next()) {
      bids.add(new Bid(fetched_lines.getInt("bid_code"), fetched_lines.getInt("sale_code"), BaseUser.fetch("user_code = " + fetched_lines.getString("user_code")), fetched_lines.getBoolean("accepted"), fetched_lines.getFloat("amount"), new TimePoint(fetched_lines.getString("tstamp"))));
		}
		return bids;
	}

  public void save() {
		connection.openConnection();
		if(Bid.fetch("bid_code = " + this.bid_code).size() == 0) {
			this.connection.executeUpdate("INSERT INTO Bid VALUES (" + this.bid_code + ", " + this.sale_code + ", " + this.user.getCode() + ", " + this.accepted + ", " + this.amount + ", TO_DATE('" + this.timestamp.toSqlDateTime() + "', 'YYYY-MM-DD HH24:MI')" + ");");
		} else {
			connection.executeUpdate("UPDATE Bid SET bid_code = " + this.bid_code + ", sale_code = " + this.sale_code + ", user_code" + this.user.getCode() + ", accepted = " + this.accepted + ", amount = " + this.amount + ", tstamp = TO_DATE('" + this.timestamp.toSqlDateTime() + "', 'YYYY-MM-DD HH24:MI')" + " WHERE bid_code = " + this.bid_code + ";");
		}
		connection.closeConnection();
	}

  public int getCode() {
    return this.bid_code;
  }

  public String show() {
    return this.bid_code + ". " + this.amount + "€ " + " par : " + user.show();
  }

  public float getAmount() {
    return bid_amount;
  }

  public bool compareToUsing(Bid next_bid, GT) {
    if(this.getAmount() > next_bid.getAmount()) {
      return true;
    }
    return false;
  }

  public bool compareToUsing(Bid next_bid, LT) {
    if(this.getAmount() < next_bid.getAmount()) {
      return true;
    }
    return false;
  }

  public bool compareToUsing(Bid next_bid, EQ) {
    if(this.getAmount() == next_bid.getAmount()) {
      return true;
    }
    return false;
  }

  public bool compareToUsing(Bid next_bid, NE) {
    if(this.getAmount() != next_bid.getAmount()) {
      return true;
    }
    return false;
  }


}
