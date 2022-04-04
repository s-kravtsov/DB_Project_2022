import java.sql.*;
import java.util.ArrayList;
import oracle.jdbc.*;

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
    url = "jdbc:oracle:thin//195.221.228.253:1521/oracle1";
    username = "kravtsos";
    password = "kravtsos";
    try {
      Class.forName("oracle.jdbc.OracleDriver");
    } catch (ClassNotFoundException e) {

    }
    try {
      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  public void openConnection() {
    try {
      this.connection = DriverManager.getConnection(this.url, this.username, this.password);
      Statement statement = this.connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public ResultSet executeQuery(String query) {
    try {
      return statement.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void executeUpdate(String query) {
    try {
      statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void closeConnection() {
    try {
      this.connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
