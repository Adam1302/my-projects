/**
 * Base class for an item
 */
public class Item implements ValidItem
{
  private String name;
  private double price;
  public static int totalStoreItems = 0; //Adds one every time an item is initialized
  
  public Item(String name, double price)
  {
    this.name = name;
    this.price = price;
    totalStoreItems++;
  }
  
  public double getPrice()
  {
    return price;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String toString()
  {
    return "$" + price + " -- " + name;
  }
  
}
