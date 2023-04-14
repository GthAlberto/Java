package bank;

import javax.security.auth.login.LoginException;

public class Aunthenticator {
  public static Customer login(String username, String password) throws LoginException{// hay que expecificar la excepcion en la cabezera del metodo
    Customer customer = DataSource.getCustomer(username);// hay que asegurarse que se recivan los datos, usamos un if 
    if(customer == null){
      throw new LoginException("Username not found"); // login viene con su expecion de java 
    }  
    
    if(password.equals(customer.getPassword())){ // mejor usar equals para comparar objetos 
      customer.setAuthenticated(true);
      return customer;
    }
    // si la contraseña es incorrecta
    else throw new LoginException("Incorrect password");
  }

    // hay que autentificar el usuario
  public static void logout (Customer customer){
    customer.setAuthenticated(false);
  }
}
