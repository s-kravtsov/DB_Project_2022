class Bid {
  Lot lot;
  float bid_amount;
  double value;

  BidLine[] bid_lines;

  public Bid(Lot _lot, int[] _bid_lines, int[] _bid_quantities, float _bid_amount) {
    lot = _lot;
    bid_amount = _bid_amount;
    for(int i = 0; i < _bid_lines.length(); i++) {
      BidLine bid_line = new BidLine(_bid_lines[i], _bid_quantities[i]);
      bid_lines.add(bid_line);
    }
    int quantity_sum = 0;
    for(int i = 0; i < _bid_quantities; i++) {
      quantity_sum += _bid_quantities[i];
    }
    this.value = _bid_amount / quantity_sum;
  }

  public double getValue() {
    return this.value;
  }
}
