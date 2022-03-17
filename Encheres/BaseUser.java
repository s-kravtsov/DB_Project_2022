class BaseUser {
  int user_code;
  String email;
  String first_name;
  String last_name;
  Country country;
  City city;

  public BaseUser(int _user_code, String _email, String _first_name, String _last_name, Country _country, City _city ) {
    user_code = _user_code;
    email = _email;
    first_name = _first_name;
    last_name = _last_name;
    country = _country;
    city = _city;
  }


  public Bid makeBid(Lot lot) {
    lot.print();

    System.out.println("Which lines do you want to bid on ? (enter separated by commas) : ");
    int[] bid_lines = parseToIntArray(System.console().readLine());

    int[] bid_quantities;
    for (int bid_line : bid_lines) {
      System.out.println("How many units of product " + bid_line + " do you waht to buy ? : ");
      bid_quantities.add(Integer.parseInt(System.console().readLine()));
    }

    System.out.println("How much do you want to bid ? : ");
    float bid_amount = Float.parseFloat(System.console().readLine());

    Bid res_bid = new Bid(lot, bid_lines, bid_quantities, bid_amount);

    return res_bid;
  }

  int[] parseToIntArray(String s) {

    int[] res;

    String caught_number;

    for (int i = 0; i < s.length(); i++) {
      caught_number = "";
      for (int j = 0; j < s.length() - i; j++) {
        if (s[j] >= 48 && s[j] <= 57) {
          caught_number += s[j];
        } else if (s[j] == ',' || i + j == s.length()) {
          res.add(Integer.parseInt(caught_number));
          i = j + 1;
          break;
        } else {
          continue;
        }
      }
    }

    return res;
  }
}
