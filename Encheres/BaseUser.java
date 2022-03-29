class BaseUser extends DatabaseObject<BaseUser> {
  int user_code;
  String email;
  String first_name;
  String last_name;
  String address;


  public BaseUser(int _user_code, String _email, String _first_name, String _last_name, String _address ) {
    user_code = _user_code;
    email = _email;
    first_name = _first_name;
    last_name = _last_name;
    country = _country;
    city = _city;
  }

  public static ArrayList<BaseUser> fetch() {
		ArrayList<BaseUser> users = new ArrayList<BaseUser>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM BaseUser;");
		connection.closeConnection();
		while(fetched_lines.next()) {
			users.add(new BaseUser(fetched_lines.getInt("user_code"), fetched_lines.getString("email"), fetched_lines.getString("first_name"), fetched_lines.getString("last_name"), fetched_lines.getString("address")));
		}
		return users;
	}

  public static ArrayList<BaseUser> fetch(String condition) {
    ArrayList<BaseUser> users = new ArrayList<BaseUser>();
		connection.openConnection();
		ResultSet fetched_lines = connection.executeQuery("SELECT * FROM BaseUser WHERE " + condition + ";");
		connection.closeConnection();
		while(fetched_lines.next()) {
			users.add(new BaseUser(fetched_lines.getInt("user_code"), fetched_lines.getString("email"), fetched_lines.getString("first_name"), fetched_lines.getString("last_name"), fetched_lines.getString("address")));
		}
		return users;
	}

  public void save() {
		connection.openConnection();
		if(BaseUser.fetch("user_code = " + this.user_code).size() == 0) {
			this.connection.executeUpdate("INSERT INTO BaseUser VALUES (" + this.user_code + ", " + this.email + ", " + this.first_name + ", " + this.last_name + ", " + this.address + ");");
		} else {
			connection.executeUpdate("UPDATE BaseUser SET user_code = " + this.user_code + ", email = " + this.email + ", first_name = " + this.first_name + ", last_name = " + this.last_name + ", address = " + this.address + " WHERE room_code = " + this.user_code + ";");
		}
		connection.closeConnection();
	}

  public int getCode() {
    return user_code;
  }

  public String show() {
    return this.user_code + ". " + this.first_name + " " + this.last_name;
  }

  public static BaseUser identify() {

    ArrayList<BaseUser> users = BaseUser.fetch();
    System.out.println("Vous êtes : ");
    for(BaseUser user : users) {
      user.show();
    }

    String user_choice = System.console().readLine();

    for(BaseUser user : users) {
      if(user.getCode() == Integer.parseInt(user_choice)) {
        return user;
      }
    }

  }

  public int getCode() {
    return user_code;
  }

  public String show() {
    return this.user_code + ". " + this.first_name + " " + this.last_name;
  }

  public void bid(Sale sale) {
    System.out.println("Combien voulez-vous encherir : ");
    String user_choice = System.console().readLine();
    Float amount = Float.parseFloat(user_choice);
    boolean accepted = sale.placeNewBid(sale.getCode(), this, amount);
    if(accepted) {
      System.out.println("Votre enchère est prise en compte.");
    } else {
      System.out.println("Votre enchère n'est pas valide. Ressayez.")
    }
  }

}
