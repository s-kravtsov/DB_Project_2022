public abstract class DatabaseObject<T> {
  DatabaseConnection connection = DatabaseConnection.getInstance();

  public static abstract ArrayList<T> fetch();
  public static abstract ArrayList<T> fetch(String condition);

  public abstract void save();

}
