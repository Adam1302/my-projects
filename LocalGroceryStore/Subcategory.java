/**
 * Class for subcategory (milk, yogurt are both subcategories in dairy)
 */
import java.util.ArrayList;

public class Subcategory extends BaseCategory
{
  public ArrayList<Item> list;
  public Subcategory(String name)
  {
    super(name);
    list = new ArrayList<Item>();
  }
  
  public void add(Item a)
  {
    list.add(a);
  }
  
  public Item get(int i)
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
