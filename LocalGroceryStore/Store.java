/**
 * Class for the store
 */

import java.util.Random;
import java.util.ArrayList;

public class Store
{
  private ArrayList<Category> allItems;
  
  private double distance;
  
  private EmployeeNode<Employee> one;
  
  private int totalItems;
  public Store()
  {
    allItems = new ArrayList<Category>();
    setDistance();
    initializeItems();
    initializeEmployees();
    
    System.out.println();
    System.out.println("Welcome to Nielsen's, your go-to online grocery store during the COVID-19 pandemic!");
    System.out.println("You have " + totalItems + " items to choose from!");
    System.out.println();
  }
  
  private void setDistance()
  {
    Random rand = new Random();
    
    distance = rand.nextDouble()*30; // Sets a random distance within 30 km
  }
  
  public double getDistance()
  {
    return distance;
  }
  
  public ArrayList<Category> getList()
  {
    return allItems;
  }
  
  public void displaySections()
  {
    System.out.println();
    for (int i = 0; i < allItems.size(); i++)
    {
      System.out.println(String.valueOf(i) + " -- " + allItems.get(i));
    }
    StoreRunner.extraOptions();
  }
  
  public void getHelp(String itemName) //Calls on employees in the linked list until one is able to help
  {
    EmployeeNode<Employee> temp = one;
    temp.getEmployee().introduceYourself();
    if (!temp.getEmployee().getGoodResponse())
    {
      do
      {
        temp = temp.getNext();
        temp.getEmployee().introduceYourself();
      } while (!temp.getEmployee().getGoodResponse() && temp.getNext() != null);
    }
    System.out.println(findItem(itemName));
  }
  
  public void initializeEmployees() //Seven employees in the linked list
  {
    one = new EmployeeNode<Employee>(new Employee("Stock Clerk", 40));
    EmployeeNode<Employee> two = new EmployeeNode<Employee>(new Employee("Stock Clerk", 60));
    EmployeeNode<Employee> three = new EmployeeNode<Employee>(new Employee("Stock Clerk", 60));
    EmployeeNode<Employee> four = new EmployeeNode<Employee>(new Employee("Cashier", 65));
    EmployeeNode<Employee> five = new EmployeeNode<Employee>(new Employee("Cashier", 70));
    EmployeeNode<Employee> six = new EmployeeNode<Employee>(new Employee("Assisstant Manager", 60));
    EmployeeNode<Employee> seven = new EmployeeNode<Employee>(new Employee("Department Manager", 60));
    
    one.setNext(two);
    two.setNext(three);
    three.setNext(four);
    four.setNext(five);
    five.setNext(six);
    six.setNext(seven);
  }
  
  public String findItem(String itemName) //Goes through each item in the store until it finds an item that contains the name specified by the user.
  {
    boolean found = false;
    String response = "Sorry. We don't have that item by that name.";
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < allItems.size())
    {
      while (j < allItems.get(i).list.size())
      {
        while (k < allItems.get(i).get(j).list.size())
        {
          if (allItems.get(i).get(j).get(k).toString().toLowerCase().contains(itemName.toLowerCase()))
          {
            response = "Check " + allItems.get(i) + " --> " + allItems.get(i).get(j);
            found = true;
            break;
          }
          k++;
        }
        if (found) { break; }
        k = 0;
        j++;
      }
      if (found) { break; }
      j = 0;
      i++;
    }
    return response;
  }
  
  private void initializeItems() // Adds all of the grocery store's items to the list
  {
    allItems.add(new Category("Bakery and Desserts"));
    allItems.add(new Category("Dairy and Eggs"));
    allItems.add(new Category("Drinks"));
    allItems.add(new Category("Fresh Produce"));
    allItems.add(new Category("Meat and Seafood"));
    allItems.add(new Category("Snacks"));
    allItems.add(new Category("Breakfast"));
    allItems.add(new Category("Baking"));
    allItems.add(new Category("Pantry"));
    allItems.add(new Category("Sauce and Spices"));
    allItems.add(new Category("Household"));
    
    allItems.get(0).add(new Subcategory("Bread"));
    allItems.get(0).add(new Subcategory("Cakes and Desserts"));
    allItems.get(0).add(new Subcategory("Ice Cream"));
    
    allItems.get(0).get(0).add(new Item("6 Bagels", 4.00));
    allItems.get(0).get(0).add(new Item("Sliced Bread (White)", 3.50));
    allItems.get(0).get(0).add(new Item("Sliced Bread (Whole Wheat)", 3.50));
    allItems.get(0).get(0).add(new Item("Pita Bread (10)", 3.00));
    allItems.get(0).get(0).add(new Item("Tortillas (10)", 3.50));
    allItems.get(0).get(0).add(new Item("Flatbread", 3.75));
    allItems.get(0).get(0).add(new Item("Baguette", 3));
    allItems.get(0).get(0).add(new Item("All-Butter Croissants (12)", 5));
    
    allItems.get(0).get(1).add(new Item("Banada Loaf Cake", 4.00));
    allItems.get(0).get(1).add(new Item("Lemon Loaf Cake", 4.00));
    allItems.get(0).get(1).add(new Item("Twinkies (6)", 2.50));
    allItems.get(0).get(1).add(new Item("Two-bite Cupcakes (12)", 3.25));
    allItems.get(0).get(1).add(new Item("Chocloate Box", 18));
    allItems.get(0).get(1).add(new Item("Tuxedo Cake", 8));
    allItems.get(0).get(1).add(new Item("Tiramisu", 9));
    allItems.get(0).get(1).add(new Item("Triple Chocolate Cake", 12));
    allItems.get(0).get(1).add(new Item("Carrot Cake", 11));
    allItems.get(0).get(1).add(new Item("New York Style Cake", 9));
    
    allItems.get(0).get(2).add(new Item("Chocolate Ice Cream", 4.97));
    allItems.get(0).get(2).add(new Item("Vanilla Ice Cream", 4.97));
    allItems.get(0).get(2).add(new Item("Ice Cream Sandwich", 4.19));
    allItems.get(0).get(2).add(new Item("Drumsticks", 5.29));
    
    allItems.get(1).add(new Subcategory("Eggs"));
    allItems.get(1).add(new Subcategory("Milk"));
    allItems.get(1).add(new Subcategory("Cheese"));
    allItems.get(1).add(new Subcategory("Yogurt"));
    allItems.get(1).add(new Subcategory("Butter"));
    
    allItems.get(1).get(0).add(new Item("Eggs (18)", 4.19));
    allItems.get(1).get(0).add(new Item("Brown Eggs (18)", 5.09));
    allItems.get(1).get(0).add(new Item("Egg Whites (Liquid)", 4.15));
    allItems.get(1).get(1).add(new Item("2% Milk (3 Bags)", 4.35));
    allItems.get(1).get(1).add(new Item("1% Milk (3 Bags)", 4.35));
    allItems.get(1).get(1).add(new Item("Almond Milk", 5));
    allItems.get(1).get(1).add(new Item("Skim Milk", 4.55));
    allItems.get(1).get(1).add(new Item("Chocolate Milk", 4));
    allItems.get(1).get(2).add(new Item("Cream Cheese (Philadelphia)", 4.20));
    allItems.get(1).get(2).add(new Item("Mozzarella Cheese", 5.5));
    allItems.get(1).get(2).add(new Item("Cheddar Cheese", 5.5));
    allItems.get(1).get(2).add(new Item("Swiss Cheese", 4.79));
    allItems.get(1).get(2).add(new Item("Cottage Cheese (/kg)", 6.29));
    allItems.get(1).get(2).add(new Item("Grated Parmesan Cheese", 4.79));
    allItems.get(1).get(2).add(new Item("Shredded Mozzarella", 5.97));
    allItems.get(1).get(2).add(new Item("Babybel (12)", 6.99));
    allItems.get(1).get(2).add(new Item("Cheddar Slices", 2.69));
    allItems.get(1).get(2).add(new Item("BD Cheese Strings (12)", 5.49));
    allItems.get(1).get(3).add(new Item("Activia (12)", 5.5));
    allItems.get(1).get(3).add(new Item("Greek Yogurt (/kg)", 7.77));
    allItems.get(1).get(3).add(new Item("Balkan Yogurt", 4.53));
    allItems.get(1).get(3).add(new Item("Iogo (12)", 5.49));
    allItems.get(1).get(3).add(new Item("Yop", .99));
    allItems.get(1).get(4).add(new Item("Salted Butter (0.5 kg)", 5));
    allItems.get(1).get(4).add(new Item("Unsalted Butter (0.5 kg)", 7.19));
    allItems.get(1).get(4).add(new Item("Margarine", 3.99));
    
    allItems.get(2).add(new Subcategory("Pop"));
    allItems.get(2).add(new Subcategory("Juice"));
    allItems.get(2).add(new Subcategory("Water"));
    allItems.get(2).add(new Subcategory("Coffee and Tea"));
    
    allItems.get(2).get(0).add(new Item("Canada Dry (12)", 4.79));
    allItems.get(2).get(0).add(new Item("Coca-Cola (12)", 4.79));
    allItems.get(2).get(0).add(new Item("Pepsi (12)", 4.59));
    allItems.get(2).get(0).add(new Item("Diet Coke (12)", 4.99));
    allItems.get(2).get(0).add(new Item("Diet Pepsi (12)", 4.99));
    allItems.get(2).get(0).add(new Item("Sprite (12)", 4.79));
    allItems.get(2).get(0).add(new Item("7-Up (12)", 4.79));
    allItems.get(2).get(0).add(new Item("Nestea (12)", 4.69));
    
    allItems.get(2).get(1).add(new Item("Orange Juice (with Pulp, 2.5 L)", 2.99));
    allItems.get(2).get(1).add(new Item("Orange Juice (no Pulp, 2.5 L)", 2.89));
    allItems.get(2).get(1).add(new Item("Apple Juice (1 L)", 1.19));
    allItems.get(2).get(1).add(new Item("Lemonade (1.5 L)", 3.19));
    allItems.get(2).get(1).add(new Item("Tomato Juice (2 L)", 3.89));
    allItems.get(2).get(1).add(new Item("Cranberry Juice (1.5 L)", 2.89));
    allItems.get(2).get(1).add(new Item("Mango Juice (1 L)", 1.5));
    allItems.get(2).get(1).add(new Item("Peach Juice (2 L)", 2.69));
    
    allItems.get(2).get(2).add(new Item("Bottled Water (24 pack)", 2.49));
    allItems.get(2).get(2).add(new Item("Water (5 L)", 2.29));
    allItems.get(2).get(2).add(new Item("Sparkling Water (24 pack)", 3.67));
    allItems.get(2).get(2).add(new Item("Perrier (10 pack)", 4.11));
    allItems.get(2).get(2).add(new Item("Coconut Water (1 L)", 4.19));
    
    allItems.get(2).get(3).add(new Item("Nescafe Coffee Mix", 4.97));
    allItems.get(2).get(3).add(new Item("Maxwell Coffee Mix", 3.24));
    allItems.get(2).get(3).add(new Item("Folgers Coffee Mix", 4.29));
    allItems.get(2).get(3).add(new Item("Van Houte Coffee Mix", 3.99));
    allItems.get(2).get(3).add(new Item("Herbal Tea Bags (200)", 11.24));
    
    allItems.get(3).add(new Subcategory("Fruits"));
    allItems.get(3).add(new Subcategory("Melons"));
    allItems.get(3).add(new Subcategory("Berries"));
    allItems.get(3).add(new Subcategory("Vegetables"));
    
    allItems.get(3).get(0).add(new Item("Bananas (6-8)", 1.79));
    allItems.get(3).get(0).add(new Item("Apples (10-12)", 5.99));
    allItems.get(3).get(0).add(new Item("Oranges (6-8)", 4.09));
    allItems.get(3).get(0).add(new Item("Grapefruits (6-8)", 4.79));
    allItems.get(3).get(0).add(new Item("Lemons (10-12)", 5.47));
    allItems.get(3).get(0).add(new Item("Limes (10-12)", 5.47));
    allItems.get(3).get(0).add(new Item("Mangoes (5-7)", 6.99));
    allItems.get(3).get(0).add(new Item("Pears (6-8)", 5.68));
    allItems.get(3).get(0).add(new Item("Kiwis (6-8)", 3.79));
    allItems.get(3).get(0).add(new Item("Grapes", 2.99));
    allItems.get(3).get(0).add(new Item("Pomegranate", 2.49));
    
    allItems.get(3).get(1).add(new Item("Cantaloupe", 2.79));
    allItems.get(3).get(1).add(new Item("Pineapple", 2.99));
    allItems.get(3).get(1).add(new Item("Watermelon", 4.79));
    allItems.get(3).get(1).add(new Item("Honeydew Melon", 3.99));
    
    allItems.get(3).get(2).add(new Item("Strawberries (0.5 kg)", 4.19));
    allItems.get(3).get(2).add(new Item("Rasberries (0.25 kg)", 2.19));
    allItems.get(3).get(2).add(new Item("Blueberries (0.25 kg)", 2.19));
    allItems.get(3).get(2).add(new Item("Blackberries (0.25 kg)", 2.29));
    
    allItems.get(3).get(3).add(new Item("Tomatoes (10-12)", 5.99));
    allItems.get(3).get(3).add(new Item("Cucumber", 1.79));
    allItems.get(3).get(3).add(new Item("Peppers (3)", 3.49));
    allItems.get(3).get(3).add(new Item("Mushrooms (12)", 2.79));
    allItems.get(3).get(3).add(new Item("Carrots (4)", 2.99));
    allItems.get(3).get(3).add(new Item("Onions (5-7)", 2.29));
    allItems.get(3).get(3).add(new Item("Celery", 2.49));
    allItems.get(3).get(3).add(new Item("Cauliflower", 3.59));
    allItems.get(3).get(3).add(new Item("Broccoli", 2.99));
    allItems.get(3).get(3).add(new Item("Zucchini", .97));
    allItems.get(3).get(3).add(new Item("Potatoes (3 kg)", 5.49));
    allItems.get(3).get(3).add(new Item("Cabbage", 3.59));
    allItems.get(3).get(3).add(new Item("Asparagus", 3.99));
    allItems.get(3).get(3).add(new Item("Garlic (6)", 1.79));
    allItems.get(3).get(3).add(new Item("Corn (4)", 3.99));
    allItems.get(3).get(3).add(new Item("Eggplant", 3.19));
    
    allItems.get(4).add(new Subcategory("Beef"));
    allItems.get(4).add(new Subcategory("Chicken"));
    allItems.get(4).add(new Subcategory("Turkey and Sliced Meat"));
    allItems.get(4).add(new Subcategory("Bacon and Pork"));
    allItems.get(4).add(new Subcategory("Seafood"));
    
    allItems.get(4).get(0).add(new Item("Ground Beef - Lean (/kg)", 13.29));
    allItems.get(4).get(0).add(new Item("Sirloin Steak (/kg)", 21.70));
    allItems.get(4).get(0).add(new Item("Rib Steak (/kg)", 33.47));
    
    allItems.get(4).get(1).add(new Item("Chicken Fillet (/kg)", 13.99));
    allItems.get(4).get(1).add(new Item("Chicken Thighs (/kg)", 11.10));
    allItems.get(4).get(1).add(new Item("Chicken Leg Quarters", 6.09));
    allItems.get(4).get(1).add(new Item("Chicken Drumsticks", 8.01));
    
    allItems.get(4).get(2).add(new Item("Turkey", 9.69));
    allItems.get(4).get(2).add(new Item("Sliced Turkey", 3.09));
    allItems.get(4).get(2).add(new Item("Sliced Ham", 3.09));
    allItems.get(4).get(2).add(new Item("Salami", 6.19));
    
    allItems.get(4).get(3).add(new Item("Bacon Strips", 5.29));
    allItems.get(4).get(3).add(new Item("Pork (/kg)", 10.49));
    allItems.get(4).get(3).add(new Item("Rib Steak (/kg)", 33.47));
    allItems.get(4).get(3).add(new Item("Hot Dogs (24)", 5.69));
    allItems.get(4).get(3).add(new Item("Pork Boneless Loin (/kg)", 10.49));
    allItems.get(4).get(3).add(new Item("Lean Ground Pork (/kg)", 33.47));
    
    allItems.get(4).get(4).add(new Item("Salmon (/kg)", 26.99));
    allItems.get(4).get(4).add(new Item("Rainbow Trout (/kg)", 24.49));
    allItems.get(4).get(4).add(new Item("Tilapia (/kg)", 16.99));
    allItems.get(4).get(4).add(new Item("Canned Tuna", 4.99));
    allItems.get(4).get(4).add(new Item("Frozen Shrimp", 7.49));
    allItems.get(4).get(4).add(new Item("Crab Meat)", 6.99));
    
    allItems.get(5).add(new Subcategory("Chips"));
    allItems.get(5).add(new Subcategory("Cookies"));
    allItems.get(5).add(new Subcategory("Granola Bars"));
    allItems.get(5).add(new Subcategory("Chocolate"));
    allItems.get(5).add(new Subcategory("Nuts"));
    
    allItems.get(5).get(0).add(new Item("Lays - Classic", 3.89));
    allItems.get(5).get(0).add(new Item("Doritos", 3.89));
    allItems.get(5).get(0).add(new Item("Tostitos", 4.09));
    allItems.get(5).get(0).add(new Item("Cheetos", 3.99));
    allItems.get(5).get(0).add(new Item("Ruffles", 3.79));
    
    allItems.get(5).get(1).add(new Item("Chocolate Chip Cookies", 4.89));
    allItems.get(5).get(1).add(new Item("Oreos", 4.29));
    allItems.get(5).get(1).add(new Item("Celebration Cookie Pack", 4.59));
    
    allItems.get(5).get(2).add(new Item("Chocolate Chip Granola Bars", 3.29));
    allItems.get(5).get(2).add(new Item("Peanut Butter Granola Bars", 3.29));
    allItems.get(5).get(2).add(new Item("Nature Valley Bars", 4.19));
    
    allItems.get(5).get(3).add(new Item("Chocolate Box (KitKat, Smarties, Aero, Coffee Crisp)", 10.89));
    allItems.get(5).get(3).add(new Item("Chcolate Bars (Oh Henry, Twix, Mars, Wonderbar)", 14.59));
    allItems.get(5).get(3).add(new Item("Lindt Dark Chocolate", 3.29));
    
    allItems.get(5).get(4).add(new Item("Mixed Nuts (Walnuts, Cashews, Peanuts)", 9.99));
    allItems.get(5).get(4).add(new Item("Almonds (/kg", 20.89));
    allItems.get(5).get(4).add(new Item("Cashews (/kg)", 15.19));
    allItems.get(5).get(4).add(new Item("Salted Peanuts (/kg)", 16.09));
    
    allItems.get(6).add(new Subcategory("Cereal"));
    allItems.get(6).add(new Subcategory("Pancakes and Waffles"));
    allItems.get(6).add(new Subcategory("Toppings"));
    
    allItems.get(6).get(0).add(new Item("Cheerios", 6.25));
    allItems.get(6).get(0).add(new Item("Lucky CHarms", 5.25));
    allItems.get(6).get(0).add(new Item("Fruit Loops", 5.99));
    allItems.get(6).get(0).add(new Item("Frosted Flakes", 5.99));
    allItems.get(6).get(0).add(new Item("Corn Flakes", 5.79));
    allItems.get(6).get(0).add(new Item("Quaker Oatmeal - Maple & Brown Sugar", 3.87));
    allItems.get(6).get(0).add(new Item("Quaker Oatmeal - Apples & Cinnamon", 3.87));
    allItems.get(6).get(0).add(new Item("Oatmeal - Regular", 3.87));
    
    allItems.get(6).get(1).add(new Item("Pancake Mix", 2.57));
    allItems.get(6).get(1).add(new Item("Waffle Mix", 2.40));
    
    allItems.get(6).get(2).add(new Item("Peanut Butter", 4.79));
    allItems.get(6).get(2).add(new Item("Nutella", 5.69));
    allItems.get(6).get(2).add(new Item("Strawberry Jam", 3.81));
    allItems.get(6).get(2).add(new Item("Honey", 5));
    allItems.get(6).get(2).add(new Item("Maple Syrup", 5.99));
    
    allItems.get(7).add(new Subcategory("Flour, Sugar, Salt"));
    allItems.get(7).add(new Subcategory("Mixes"));
    
    allItems.get(7).get(0).add(new Item("Flour (10 kg)", 10.09));
    allItems.get(7).get(0).add(new Item("White Sugar (3 kg)", 3.39));
    allItems.get(7).get(0).add(new Item("Brown Sugar (3 kg)", 3.39));
    allItems.get(7).get(0).add(new Item("Sweeteners (100 pack)", 6.19));
    allItems.get(7).get(0).add(new Item("Table Salt", 1.27));
    
    allItems.get(7).get(1).add(new Item("Cookie Mix", 4.19));
    allItems.get(7).get(1).add(new Item("Cake Mix", 4.09));
    
    allItems.get(8).add(new Subcategory("Pasta"));
    allItems.get(8).add(new Subcategory("Other"));
    
    allItems.get(8).get(0).add(new Item("Spaghetti", 1.09));
    allItems.get(8).get(0).add(new Item("Macaroni", 1.39));
    allItems.get(8).get(0).add(new Item("Lasagna", 2.39));
    allItems.get(8).get(0).add(new Item("Rotini", 2.19));
    
    allItems.get(8).get(1).add(new Item("Instant Noodles (12 pack", 12));
    allItems.get(8).get(1).add(new Item("Rice (5 kg)", 12.29));
    
    allItems.get(9).add(new Subcategory("Spices"));
    allItems.get(9).add(new Subcategory("Sauces"));
    allItems.get(9).add(new Subcategory("Dressing"));
    allItems.get(9).add(new Subcategory("Condiments"));
    
    allItems.get(9).get(0).add(new Item("Paprika", 1.99));
    allItems.get(9).get(0).add(new Item("Rosemary leaves", 1.99));
    allItems.get(9).get(0).add(new Item("Ground Cumin", 1.99));
    allItems.get(9).get(0).add(new Item("Cayenne Pepper", 1.99));
    allItems.get(9).get(0).add(new Item("Turmeric", 1.99));
    
    allItems.get(9).get(1).add(new Item("Teriyaki Sauce", 1.99));
    allItems.get(9).get(1).add(new Item("Tomato Sauce", 2.99));
    allItems.get(9).get(1).add(new Item("Plum Sauce", 1.99));
    allItems.get(9).get(1).add(new Item("Honey Garlic Sauce", 1.99));
    allItems.get(9).get(1).add(new Item("Turmeric", 1.99));
   
    allItems.get(9).get(2).add(new Item("Caesar Dressing", 2.99));
    allItems.get(9).get(2).add(new Item("Italian Dressing", 2.99));
    allItems.get(9).get(2).add(new Item("Ranch Dressing", 3.99));
    
    allItems.get(9).get(3).add(new Item("Honey Garlic Sauce", 1.99));
    allItems.get(9).get(3).add(new Item("Mayonnaise", 3.09));
    allItems.get(9).get(3).add(new Item("Ketchup (1 L)", 2.99));
    allItems.get(9).get(3).add(new Item("Mustard (1 L)", 3.29));
    allItems.get(9).get(3).add(new Item("BBQ Sauce", 1.77));
    
    allItems.get(10).add(new Subcategory("Other"));
    allItems.get(10).add(new Subcategory("Cleaning"));
    
    allItems.get(10).get(0).add(new Item("Paper Towels", 11.98));
    allItems.get(10).get(0).add(new Item("Toilet Paper", 8.98));
    allItems.get(10).get(0).add(new Item("Tissues (3 Pack)", 9.79));
    allItems.get(10).get(0).add(new Item("Napkins (500)", 6.55));
    
    allItems.get(10).get(1).add(new Item("Dishwashing Detergent", 3.79));
    allItems.get(10).get(1).add(new Item("All-Purpose Cleaner", 4.24));
    
    totalItems = allItems.get(0).get(0).get(0).totalStoreItems;
  }
  
}
