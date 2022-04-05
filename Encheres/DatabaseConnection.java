import java.sql.*;
import java.util.ArrayList;
import oracle.jdbc.*;
import oracle.jdbc.driver.OracleDriver;

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
    this.url = "jdbc:oracle:thin:@localhost:1521/ORCLPDB1.localdomain";
    this.username = "sergei";
    this.password = "dbfmb96o";
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {

    }

    try {
      DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    } catch (SQLException e) {
      e.printStackTrace();
    }



  }

  public void openConnection() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {

    }
    try {
      this.connection = DriverManager.getConnection(this.url, this.username, this.password);
      this.statement = this.connection.createStatement();
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
