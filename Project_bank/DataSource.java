package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  
  public static Connection connect(){
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null; // Connection connection = DriverManager.getConnection(db_file);
    
    try{
      connection = DriverManager.getConnection(db_file); // teniamos una excepcion no controlada, para controlarla usamos un bloque try
      System.out.println("we´are connected");
    } catch(SQLException e){
      e.printStackTrace();
    }
    return connection; // retorna al objeto connection
  }

  public static Customer getCustomer(String username) { //necesitamos una sql query que nos de los datos de los usuarios de la base de datos
    String sql = "select * from customers where username = ?"; //nunca colocar el username que pedimos del metodo porque puede dejar una puerta abierta para robar datos
    Customer customer = null;

    try(Connection connection = connect();   // try tambien sirve para cerrar procesos dentro del parentesis depues que se completa el try, en este caso sirve porque la base de datos nunca se cierra para poder estar siempre disponible, pero para este metodo sirve cerrarla 
        PreparedStatement statement = connection.prepareStatement(sql)) {

          statement.setString(1, username);// buscamos pasar de string a query real (posicion, value)
          try(ResultSet resultSet = statement.executeQuery()){ //ejecuta la query y devuelve el resultado, se cierra cuando termina asi que usemos otro try
            customer = new Customer(
              resultSet.getInt("id"), 
              resultSet.getString("name"),
              resultSet.getString("username"),
              resultSet.getString("password"),
              resultSet.getInt("account_id"));
            
          }

    }catch(SQLException e){
      e.printStackTrace();
    }

    return customer;
  }

  public static Account getAccount(int id){
    String sql = "select * from accounts where id = ?";
    Account account = null;

    try(Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql)){

        statement.setInt(1, id);
        try(ResultSet resultSet = statement.executeQuery()){
          account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));

      }
    }catch (SQLException e) {
      e.printStackTrace();
    }
    return account;

  }

  public static void updateAccountBalance(int accountId, double balance){
    String sql = "update accounts set balance = ? where id = ?";
    try{
      Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setDouble(1,balance);
      statement.setInt(2,accountId);

      statement.executeUpdate();

    }catch(SQLException e){
      e.printStackTrace();
    }
  }

// main metodo es el primero que se corre en un codigo java
  public static void main(String[] args){
    Customer customer = getCustomer("aalbersq1@pbs.org");
    System.out.println(customer.getName());
    
    Account account = getAccount(11963);
    System.out.println(account.getBalance());
  }
}
