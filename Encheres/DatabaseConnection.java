import java.sql.*;

public class DatabaseConnection {
  private static DatabaseConnection instance;

  public static DatabaseConnection getInstance() {
    if(instance == null) {
      instance = new DatabaseConnection();
    }
    return instance;
  }

  String url;
  Connection connection;
  Statement statement;
  String username;
  String password;

  public DatabaseConnection() {
    url = "jdbc:postgresql://127.0.0.1:5740/encheres";
    username = "postgres";
    password = "postgres";
  }

  public void openConnection() {
    this.connection = DriverManager.getConnection(this.url, this.username, this.password);
    Statement statement = this.connection.createStatement();
  }

  public ResultSet executeQuery(String query) {
    return statement.executeQuery(query);
  }

  public void executeUpdate(String query) {
    statement.executeUpdate(query);
  }

  public void closeConnection() {
    this.connection.close();
  }

}
