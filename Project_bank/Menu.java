package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  
  private Scanner scanner;

  public static void main(String[] args){ //como main es un metodo estatico no tiene acceso a campos y metodos no estaticos
    System.out.println("Welcom to the Globe Bank International <3"); // por lo tanto hay que crear una instancia para la clase menu

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in); // de donde queremos sacar la informacion 

    Customer customer = menu.authenticateUser();

    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer,account);

    }


    menu.scanner.close();
  }


  private Customer authenticateUser(){
    System.out.println("Enter your username");
    String username = scanner.next();

    System.out.println("Enter yoyr password");
    String password = scanner.next();

    Customer customer = null;
    try{
     customer = Aunthenticator.login(username, password);
    }catch(LoginException e){
      System.out.println("There was an error:" + e.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {

    int selection = 0; //en el menu 1= deposito, 2=retiro, 3=balance, 4=exit

    while(selection !=4 && customer.isAuthenticated()){ // customer.isAuthenticated() entrega un booleano que si es true esta autenticado
      System.out.println("=================================");
      System.out.println("Select one of this option");
      System.out.println("1:Deposit");
      System.out.println("2:Withdraw");
      System.out.println("3:Check Balance");
      System.out.println("4:Exit");
      System.out.println("=================================");

      selection = scanner.nextInt();

      //queremos ejecutar diferentes bloques de codigo dependiendo de la seleccion, para eso usamos the switch
      double amount = 0;
      switch(selection){
        case 1:
        System.out.println("How much would you like to deposit?");
        amount = scanner.nextDouble();
        try{
          account.deposit(amount); // hay que crear este metodo en la clase account
          }catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
          }
        break;

        case 2:
        System.out.println("How much would you like yo withdraw?");
        amount = scanner.nextDouble();
        try{
          account.withdraw(amount);
          }catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again");
          }
        break;

        case 3:
        System.out.println("Current balance:  " + account.getBalance());
        break;

        case 4:
        Aunthenticator.logout(customer);
        System.out.println("Thanks, byee");
        break;

        default:
        System.out.println("This is the invalid option");
        break;
      }

    }
  }


}
