/**
 * Main class is ExperimentingWithChess.java
 */
public class ComputerRookMoves 
{
  
  public String possibleRookMoves(String position, int[][] board)
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
    
    possibleMoves += movingUp(board, num1, num2);
    possibleMoves += movingDown(board, num1, num2);
    possibleMoves += movingLeft(board, num1, num2);
    possibleMoves += movingRight(board, num1, num2);
    
    return possibleMoves;
  }
  
  public String movingUp (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingUp = 0; // Each of these 'going' variables represents the amount of diagonal moves the bishop can make in each direction.
    
    int newNum1 = num1;
    
    while (newNum1 >= 1)
    {
      if (board[--newNum1][num2] < 0)
      {
        break;
      }
      else if (board[newNum1][num2] > 0)
      {
        goingUp++;
        break;
      }
      else
      {
        goingUp++;
      }
    }
    
    if (goingUp != 0)
    {
      for (int i = 1; i <= goingUp; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56 + i));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String movingDown (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingDown = 0;
    
    int newNum1 = num1;
    
    while (newNum1 <= 6)
    {
      if (board[++newNum1][num2] < 0)
      {
        break;
      }
      else if (board[newNum1][num2] > 0)
      {
        goingDown++;
        break;
      }
      else
      {
        goingDown++;
      }
    }
    if (goingDown != 0)
    {
      for (int i = 1; i <= goingDown; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65)); 
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56 - i));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String movingLeft (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingLeft = 0;
    
    int newNum2 = num2;
    
    while (newNum2 >= 1)
    {
      if (board[num1][--newNum2] < 0)
      {
        break;
      }
      else if (board[num1][newNum2] > 0)
      {
        goingLeft++;
        break;
      }
      else
      {
        goingLeft++;
      }
    }
    if (goingLeft != 0)
    {
      for (int i = 1; i <= goingLeft; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65 - i));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String movingRight (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingRight = 0;
    
    int newNum2 = num2;
    
    while (newNum2 <= 6)
    {
      if (board[num1][++newNum2] < 0)
      {
        break;
      }
      else if (board[num1][newNum2] > 0)
      {
        goingRight++;
        break;
      }
      else
      {
        goingRight++;
      }
    }
    if (goingRight != 0)
    {
      for (int i = 1; i <= goingRight; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65 + i));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
}
