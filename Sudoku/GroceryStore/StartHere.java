/*
 * Notes for Mr. Cheeseman
 * 
 * Hi sir. The program is an online grocery store simulation. The main class is the StoreRunner class
 * 
 * Here are the implementation of main concepts:
 * 
 * Variables - Conditional loops - Methods - Selection (If/Switch statements) - Randomization - User Input - Arrays/ArrayLists - Classes/Objects 
 * 
 * All concepts listed above can be found easily throughout the code (commonly in Shopper, Store, and StoreRunner classes). The concepts below are less commonly used.
 * 
 * Data Structures
 * ----- See Employee and EmployeeNode classes (instantiated in the Store class). Used to represent ascending ranks of staff that you can speak with.
 * 
 * Recursion
 * ----- Used in StoreRunner class to keep removing items if you are over budget. Method Name: completeTransaction (near the bottom)
 * 
 * Abstract Classes
 * ----- BaseCategory is the abstract class for the child classes Category and Subcategory, which are both used to represent section and subsections in the store, respectively
 * 
 * Interfaces
 * ----- ValidItem is the interface for the Item class (initially, I planned to create different classes of items, so an interface would have been more helpful there)
 * 
 * Sorting
 * ----- Used to sort one's shopping cart according to price. Find this in the Shopper class. -- sortCart() class
 * 
 * Static Variables
 * ----- See Item class and StoreRunner
 * 
 * */