/**
 * Main class is ExperimentingWithChess.java
 */
public class ComputerPawnMoves 
{
  
  public String possiblePawnMoves(String position, int[][] board)
  {
    String possibleMoves = "";
    String letter = "";
    String number = "";
  
    letter = position.substring(0,1);
    number = position.substring(1);
  
    int num1 = 0;
    int num2 = 0;
  
    char a = letter.charAt(0);
    num2 = Character.getNumericValue(a);
    num1 = Integer.parseInt(number);
  
    num1 = 9 - num1 - 1;
    num2 -= 9;
    num2 -= 1;
    
    possibleMoves += movingForward(num1, num2, board, possibleMoves);
    possibleMoves += capturing(num1, num2, board, possibleMoves);
    
    return possibleMoves;
  }
  
  public String movingForward (int num1, int num2, int[][] board, String moves)
  {
    String position = "";
    String letter = "";
    String number = "";
    
    boolean canMove = false;
    
    canMove = (num1 <= 6);
    
    if ((canMove == true) && (board[num1 + 1][num2] == 0))
    {
      letter = Character.toString((char) (num2 + 65));
      letter = letter.toLowerCase();
      position += letter;
      
      number = Character.toString((char) ((num1 * -1) + 56 - 1));
      position += number;
      
      moves += position;
      
      canMove = (num1 <= 5);
      
      if ((num1 == 1) && (board[num1 + 2][num2] == 0))
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65));
        letter = letter.toLowerCase();
        position += letter;
    
        number = Character.toString((char) ((num1 * -1) + 56 - 2));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String capturing (int num1, int num2, int[][] board, String moves)
  {
    String position = "";
    String letter = "";
    String number = "";
    
    boolean canMove = false;
    position = "";
    letter = "";
    number = "";
    
    canMove = ((num1 <= 6) && (num2 >= 1));
    
    if ((canMove == true) && (board[num1 + 1][num2 - 1] > 0))
    {
      letter = Character.toString((char) (num2 + 65 - 1));
      letter = letter.toLowerCase();
      position += letter;
    
      number = Character.toString((char) ((num1 * -1) + 56 - 1));
      position += number;
      
      moves += position;
    }
      
    canMove = ((num1 <= 6) && (num2 <= 6));
    
    if ((canMove == true) && (board[num1 + 1][num2 + 1] > 0))
    {
      position = "";
      letter = "";
      number = ""; 
      
      letter = Character.toString((char) (num2 + 65 + 1));
      letter = letter.toLowerCase();
      position += letter;
    
      number = Character.toString((char) ((num1 * -1) + 56 - 1)); 
      position += number;
      
      moves += position;
    }
    return moves;
  }
  
}
