/**
 * Attempt at a Linked List with Employees
 * Edited from class examples
 */
public class EmployeeNode<Employee>
{
  private Employee person;
  private EmployeeNode<Employee> next; //Each employee is linked to the employee in a postion above him/her
  
  public EmployeeNode(Employee person)
  {
    this.person = person;
    next = null;
  }
  
  public EmployeeNode<Employee> getNext()
  {
    return next;
  }
  
  public void setNext(EmployeeNode<Employee> next)
  {
    this.next = next;
  }
  
  public Employee getEmployee()
  {
    return person;
  }
}
