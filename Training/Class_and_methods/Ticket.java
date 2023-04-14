package _02_02;

// 1. Create a class called Ticket
// 2. Give the class an empty constructor
// 3. Create 3 private field variables in the class: 
// - A field of type String called destination
// - A field called price of type double
// - A field called isReturn of type boolean

public class Ticket {

  public Ticket(){

  }

    private String destination;
    private double price;
    private boolean isReturn;

    // Add three methods to set the value of each field, called setDestination,
    // setPrice and setIsReturn.
    public void setDestination(String destination){
      this.destination = destination ;
    }

    public void setPrice(double price){
      this.price = price;
    }

    public void setIsReturn(boolean isReturn) {
      this.isReturn = isReturn;
    }
    // Add a separate method to get the value of each field, called getDestination,
    // getPrice and getIsReturn.
    public String getDestination(){
      return destination;
    }

    public double getPrice(){
      return price;
    }

    public boolean getIsReturn(){
      return isReturn;
    }



  
}
