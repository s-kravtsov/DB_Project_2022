class Lot {
  int lot_code;
  String lot_name;

  LotLine[] lot_lines;

  public Lot(int _lot_code, String _lot_name) {
    lot_code = _lot_code;
    lot_name = _lot_name;
  }

  void addLotLine(LotLine lot_line) {
    lot_lines.add(lot_line);
  }
}
