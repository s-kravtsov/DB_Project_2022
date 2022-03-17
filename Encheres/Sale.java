class Sale {
  int sale_code;
  Lot lot;
  float start_price;
  String sale_type;
  boolean multiple_offer;
  boolean limited;
  Timestamp deadline;
  boolean revocable;

  public Sale(int _sale_code, Lot _lot, float _start_price) {
    sale_code = _sale_code;
    lot = _lot;
    start_price = _start_price;
    sale_type = "Up";
    multiple_offer = true;
    limited = false;
    deadline = null;
    revocable = true;
  }

  public Sale(int _sale_code, Lot _lot, float _start_price, String _sale_type, boolean _multiple_offer, boolean _limited, Timestamp _deadline, boolean _revocable) {
    sale_code = _sale_code;
    lot = _lot;
    start_price = _start_price;
    sale_type = _sale_type;
    multiple_offer = _multiple_offer;
    limited = _limited;
    deadline = _deadline;
    revocable = _revocable;
  }

  public void setSaleOptions(String _sale_type, boolean _multiple_offer, boolean _limited, Timestamp _deadline, boolean _revocable) {
    sale_type = _sale_type;
    multiple_offer = _multiple_offer;
    limited = _limited;
    deadline = _deadline;
    revocable = _revocable;
  }
}
