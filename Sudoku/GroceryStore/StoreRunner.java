/**
 * Main class to run the online store
 */

import java.util.Scanner;

public class StoreRunner
{
  private static Store online = new Store();
  private static Shopper user = new Shopper();
  
  public static void main(String[] args) throws InterruptedException
  {
    Thread.sleep(1500); //Taken from last year's final project resources
    Category a;
    Subcategory b;
    Item c;
    
    displayInstructions();
    
    Thread.sleep(2000);
    
    int response = 0; // Tracking responses for desired category, subcategory and item
    int response2 = 0;
    int response3 = 0;
    int quantity = 0;
    
    while (true) // First while loop: gets preffered category and breaks to go to checkout
    {
      System.out.println();
      System.out.println("Which section would you like to visit? -- ");
      online.displaySections();
      response = getResponse(online.getList().size());
      Thread.sleep(1000);
      if (response == 100)
      {
        System.out.println("CHECKOUT");
        break;
      }
      else if (response == 555)
      {
        System.out.println("Help on the way");
        helper();
      }
      else if (response == 999)
      {
        cartModifier();
      }
      else
      {
        a = online.getList().get(response);
        while (true) // Second while loop: get preffered subcategory and breaks to go back to category list
        {
          System.out.println("What would you like to purchase? -- ");
          a.displayContents();
          response2 = getResponse(a.list.size());
          Thread.sleep(1000);
          if (response2 == 100)
          {
            System.out.println("Going back...");
            break;
          }
          else if (response2 == 555)
          {
            System.out.println("Help on the way");
            helper();
          }
          else if (response2 == 999)
          {
            cartModifier();
          }
          else
          {
            b = online.getList().get(response).get(response2);
            while (true) // Third while loop: Gets desired item and breaks to go back to subcategory list
            {
              System.out.println("Add an item to your cart!");
              b.displayContents();
              response3 = getResponse(b.list.size());
              if (response3 == 100)
              {
                System.out.println("Going back...");
                break;
              }
              else if (response3 == 555)
              {
                System.out.println("Help on the way");
                helper();
              }
              else if (response3 == 999)
              {
                cartModifier();
              }
              else
              {
                c = online.getList().get(response).get(response2).get(response3);
                quantity = getQuantity(c);
                for (int i = 1; i <= quantity; i++)
                {
                  user.addToCart(c);
                }
                Thread.sleep(1500);
                System.out.println("You added " + quantity + " *" + c.getName() + "*(s) to your cart.");
              }
            }
          }
        }
      }
    }
    Thread.sleep(1200);
    checkoutOptions();
    Thread.sleep(1200);
    completeTransaction(0);
  }
  
  public static void displayInstructions()
  {
    System.out.println("---INSTRUCTIONS---");
    System.out.println("Enter the number corresponding to the sections you'd like to visit or item you'd like to add to your cart..");
    System.out.println("To view your cart and/or remove an item, enter '999'");
    System.out.println("If you need help, enter '555'");
    System.out.println("When you are done shopping, enter '100'");
    System.out.println("Any other entries will not yield a result.");
  }
  
  public static int getResponse(int size) //General method to get integers
  {
    Scanner input = new Scanner(System.in);
    int response = 0;
    while (true)
    {
      try
      {
        response = input.nextInt();
        if ((response >= 0 && response < size) || response == 555 || response == 100 || response == 999)
        {
          break;
        }
        else
        {
          System.out.print("Error: Invalid entry. Try again");
        }
      }
      catch (Exception e)
      {
        System.out.print("Please enter a valid number.");
      }
      finally
      {
        input.nextLine();
      }
    }
    input.close();
    return response;
  }
  
  public static int getQuantity(Item c) // Number of an item you'd like to purchase
  {
    Scanner input = new Scanner(System.in);
    int response = 0;
    while (true)
    {
      try
      {
        System.out.print("Enter quantity of " + c.getName() + " you'd like to purchase: ");
        response = input.nextInt();
        if (response < 1)
        {
          System.out.println("Invalid entry. Try again");
        }
        else if (response > 20)
        {
          System.out.println("That's too much! You can buy a maximum of 20 items.");
        }
        else
        {
          break;
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
    input.close();
    return response;
  }
  
  public static void extraOptions()
  {
    System.out.println();
    System.out.println("999 -- View Cart");
    System.out.println("555 -- Help");
    System.out.println("100 -- Checkout/Exit Section");
    System.out.println();
  }
  
  public static boolean yesOrNo()
  {
    Scanner input = new Scanner(System.in);
    String a = "";
    while (true)
    {
      a = input.nextLine();
      if (a.equalsIgnoreCase("yes"))
      {
        input.close();
        return true;
      }
      else if (a.equalsIgnoreCase("no"))
      {
        input.close();
        return false;
      }
      else
      {
        System.out.print("Please enter 'yes' or 'no'.");
      }
    }
  }
  
  public static void cartModifier() //Removing items from your cart
  {
    user.displayCart();
    int i = 0;
    while (user.getCart().size() != 0)
    {
      if (i == 0)
      {
        System.out.print("Would you like to remove an item? ");
      }
      else
      {
        System.out.print("Would you like to remove another item? ");
      }
      if (!yesOrNo())
      {
        break;
      }
      removeItem();
      i++;
    }
  }
  
  public static void removeItem() // Performs removing action
  {
    Scanner input = new Scanner(System.in);
    int removePlace = 0;
    do
    {
    System.out.print("Select the item you'd like to remove: ");
    removePlace = getResponse(user.getCart().size());
    } while (removePlace == 555 || removePlace == 100 || removePlace == 999);
    
    System.out.println();
    System.out.println("You removed " + user.remove(removePlace).getName() + " from your cart.");
    System.out.println();
    
    user.displayCart();
    
    input.close();
  }
  
  public static void checkoutOptions()
  {
    System.out.println();
    System.out.println("Press 0 for curbside pickup");
    System.out.println("Press 1 for delivery to home.");
    System.out.println();
    
    int lastResponse;
    double distance;
    do
    {
      System.out.print("Select your option: ");
      lastResponse = getResponse(2);
    } while (lastResponse == 555 || lastResponse == 100 || lastResponse == 999);
    System.out.println();
    if (lastResponse == 1)
    {
      distance = online.getDistance();
      distance *= 100;
      distance = Math.round(distance);
      distance /= 100;
      System.out.println("Delivery cost: $0.50 / km");
      System.out.println("You are " + distance + " km away from our nearest location.");
      System.out.println();
      System.out.println("Cost: $" + user.getCost());
      System.out.println("Delivery: $" + distance/2);
      System.out.println();
      System.out.println("Total: $" + user.finalCost(distance));
    }
    else
    {
      System.out.println("Your groceries will be ready for pickup in 1 hour. See you soon!");
    }
  }
  
  public static void completeTransaction(int i) throws InterruptedException
  {
    System.out.println();
    if (user.withinBudget())
    {
      System.out.println("Purchase successful! Thank you for shopping at Nielsen's.");
    }
    else
    {
      Thread.sleep(1000);
      if (i == 0)
      {
        System.out.println("You are not within budget. Please remove an item from you cart.");
      }
      else
      {
        System.out.println("Still not within budget. Keep removing items.");
      }
      System.out.println();
      user.displayCart();
      i++;
      removeItem();
      completeTransaction(i);
    }
  }
  
  public static void helper() throws InterruptedException //gets help
  {
    Scanner input = new Scanner(System.in);
    String response = "";
    do {
      System.out.print("What item are you looking for? ");
      response = input.nextLine();
    } while (response.isEmpty());
    response.trim();
    input.close();
    Thread.sleep(1000);
    online.getHelp(response);
  }
}
