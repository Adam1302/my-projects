/**
 * Class for a category (ex. Dairy, Fresh Produce)
 */
import java.util.ArrayList;

public class Category extends BaseCategory
{
  public ArrayList<Subcategory> list; // Dairy category contains subcategories of milk, cheese, yogurt, etc.
  public Category(String name)
  {
    super(name);
    list = new ArrayList<Subcategory>();
  }
  
  public void add(Subcategory a)
  {
    list.add(a);
  }
  
  public Subcategory get(int i)
  {
    return list.get(i);
  }
  
  public void displayContents()
  {
    System.out.println();
    for (int i = 0; i < list.size(); i++)
    {
      System.out.println(String.valueOf(i) + " -- " + list.get(i));
    }
    StoreRunner.extraOptions();
  }
}
