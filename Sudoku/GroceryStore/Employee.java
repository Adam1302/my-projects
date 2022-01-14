/**
 * Employee class to be used as a Linked List
 */

import java.util.Random;

public class Employee
{
  private String name;
  private String role; //Store position
  private int helpRating; //Higher-ranked employees will be more helpful
  
  public Employee(String position, int rating)
  {
    role = position;
    name = randomizeName();
    helpRating = rating;
  }
  
  public void introduceYourself()
  {
    System.out.println("Hi! My name is " + name + " and I'm the " + role + " here at Nielsen's.");
  }
  
  public boolean getGoodResponse() //Method is type boolean since I need to track whether or not the response is good or bad
  {
    Random rand = new Random();
    if ((rand.nextInt(100) + 1) > helpRating)
    {
      badResponse();
      return false;
    }
    else
    {
      return true;
    }
  }
  
  private String randomizeName()
  {
    String[] names = {"Rachel", "Samuel", "Courtney", "Nick", "Lauren", "Caitlin", "Hayden", "Kayla", "Zach", "Jasmine", "Brad", "Tamara", "Rhys", "Kevin", "Shannon", "Ty", "Zoe", "Julia"};
    Random rand = new Random();
    return names[rand.nextInt(names.length)];
  }
  
  private void badResponse() // Unhelpful responses that would prompt one to ask to speak to a manager
  {
    Random rand = new Random();
    String[] responses = {"Sorry, I'm new here. I can't help you with that.", "I have no idea!", "Does it look like I know?!", "I'm on break, and won't help you right now..."};
    System.out.println(responses[rand.nextInt(responses.length)]);
    System.out.println("I'll get someone who can help you.");
  }
}
