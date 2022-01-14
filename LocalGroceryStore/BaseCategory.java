/**
 * Parent class for CATEGORY and SUBCATEGORY classes.
 */
public abstract class BaseCategory
{
  private String name;
  public BaseCategory(String name)
  {
    this.name = name;
  }
  public abstract void displayContents();
  
  public String toString()
  {
    return name;
  }
  
}
