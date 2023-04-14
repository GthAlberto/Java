package _02_02;

public class Ticketachine {

  public static void main(String[] args) {
  // Create an object called ticket of type Ticket
  Ticket ticket = new Ticket();

  // Set the destination of the ticket to New York
  ticket.setDestination("New york");
  // Set the price of the ticket to 15.30
  ticket.setPrice(15.3);
  // Set the isReturn value to true
  ticket.setIsReturn(true);
  // Print the ticket's destination to the console
  System.out.println("destination:  "+ticket.getDestination());
  // Print the ticket's price to the console
  System.out.println("Price:  "+ticket.getPrice());
  // Print the ticket's isReturn value to the console
  System.out.println("Return?:  "+ticket.getIsReturn());
}

}
