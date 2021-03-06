/**
 * Class to store user info, cart info.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Shopper
{
  private double budget;
  private double cost;
  private ArrayList<Item> cart; //Upon reflection, maybe a linked list would have worked well too to represent a cart (I only realized this after finishing)
  public Shopper()
  {
    cart = new ArrayList<Item>();
    setBudget();
    cost = 0;
  }
  
  public void addToCart(Item a)
  {
    cart.add(a);
    cost += a.getPrice();
    sortCart();
    roundPrice();
  }
  
  private void roundPrice()
  {
    cost *= 100;
    cost = Math.round(cost);
    cost/= 100;
  }
  
  public boolean withinBudget()
  {
    return (cost <= budget);
  }
  
  public double finalCost(double distance)
  {
    cost += (distance/2);
    roundPrice();
    return cost;
  }
  
  public double getCost()
  {
    roundPrice();
    return cost;
  }
  
  public Item remove(int i)
  {
    Item a = cart.get(i);
    cart.remove(i);
    cost -= a.getPrice();
    roundPrice();
    return a;
  }
  
  public ArrayList<Item> getCart()
  {
    return cart;
  }
  
  public void sortCart() //Insertion sort used since it was the quickest for 10-50 items in my Sorting project
  {
    Item temp;
    int j;
    
    for(int i = 1; i < cart.size(); i++)
    {
      temp = cart.get(i);
      j = i;
      while((j > 0) && (cart.get(j - 1).getPrice() > temp.getPrice())) // Modified from class example
      {
        cart.set(j, cart.get(j - 1));
        j--;
      }
      cart.set(j, temp);
    }
  }
  
  public void displayCart() //Displays a sorted cart
  {
    System.out.println();
    System.out.println("For your convenience, your cart has been sorted by price.");
    System.out.println();
    System.out.println("Here's what's currently in your cart:");
    for (int i = 0; i < cart.size(); i++)
    {
      System.out.println(i + " -- " + cart.get(i));
    }
    if (cart.size() == 0)
    {
      System.out.println("Your cart is empty. Get shopping!");
    }
    System.out.println("Current Cost: $" + cost);
  }
  
  public void setBudget() // Gets a valid budget, if the user has one; if not, sets budget to $100000
  {
    Scanner input = new Scanner(System.in);
    String response = "";
    while (true)
    {
      System.out.print("Do you have a budget? ");
      response = input.nextLine();
      if (response.equalsIgnoreCase("yes"))
      {
        while (true)
        {
          System.out.print("Please enter your budget: ");
          try
          {
            budget = input.nextDouble();
            if (budget > 0)
            {
              break;
            }
            else
            {
              System.out.println("Error: You cannot have a negative budget.");
            }
          }
          catch (Exception e)
          {
            System.out.println("Please enter a valid number.");
          }
          finally
          {
            input.nextLine();
          }
        }
        break;
      }
      else if (response.equalsIgnoreCase("no"))
      {
        budget = 100000.00;
        break;
      }
      else
      {
        System.out.println("Invalid response. Please enter 'yes' or 'no'.");
      }
    }
    input.close();
  }
}
