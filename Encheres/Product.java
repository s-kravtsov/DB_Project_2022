class Product {
  int product_code;
  String product_name;
  float product_cost;
  Category category;

  public Product(int _product_code, String _product_name, float _product_cost, Category _category) {
    product_code = _product_code;
    product_name = _product_name;
    product_cost = _product_cost;
    category = _category;
  }
}
