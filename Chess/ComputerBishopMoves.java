/**
 * Main class is ExperimentingWithChess.java
 */
public class ComputerBishopMoves 
{
  public String possibleBishopMoves(String position, int[][] board)
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
    
    possibleMoves += movingNorthEast(board, num1, num2);
    possibleMoves += movingNorthWest(board, num1, num2);
    possibleMoves += movingSouthEast(board, num1, num2);
    possibleMoves += movingSouthWest(board, num1, num2);
    
    return possibleMoves;
  }
  
  public String movingNorthEast (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingNE = 0; // Each of these 'going' variables represents the amount of diagonal moves the bishop can make in each direction.
    
    int newNum1 = num1;
    int newNum2 = num2;
    
    while ((newNum1 >= 1) && (newNum2 <= 6))
    {
      if (board[--newNum1][++newNum2] < 0)
      {
        break;
      }
      else if (board[newNum1][newNum2] > 0)
      {
        goingNE++;
        break;
      }
      else
      {
        goingNE++;
      }
    }
    
    if (goingNE != 0)
    {
      for (int i = 1; i <= goingNE; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65 + i));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56 + i));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String movingNorthWest (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingNW = 0;
    
    int newNum1 = num1;
    int newNum2 = num2;
    
    while ((newNum1 >= 1) && (newNum2 >= 1))
    {
      if (board[--newNum1][--newNum2] < 0)
      {
        break;
      }
      else if (board[newNum1][newNum2] > 0)
      {
        goingNW++;
        break;
      }
      else
      {
        goingNW++;
      }
    }
    if (goingNW != 0)
    {
      for (int i = 1; i <= goingNW; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65 - i));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56 + i));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String movingSouthEast (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingSE = 0;
    
    int newNum1 = num1;
    int newNum2 = num2;
    
    while ((newNum1 <= 6) && (newNum2 <= 6))
    {
      if (board[++newNum1][++newNum2] < 0)
      {
        break;
      }
      else if (board[newNum1][newNum2] > 0)
      {
        goingSE++;
        break;
      }
      else
      {
        goingSE++;
      }
    }
    if (goingSE != 0)
    {
      for (int i = 1; i <= goingSE; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65 + i));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56 - i));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
  
  public String movingSouthWest (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    int goingSW = 0;
    
    int newNum1 = num1;
    int newNum2 = num2;
    
    while ((newNum1 <= 6) && (newNum2 >= 1))
    {
      if (board[++newNum1][--newNum2] < 0)
      {
        break;
      }
      else if (board[newNum1][newNum2] > 0)
      {
        goingSW++;
        break;
      }
      else
      {
        goingSW++;
      }
    }
    if (goingSW != 0)
    {
      for (int i = 1; i <= goingSW; i++)
      {
        position = "";
        letter = "";
        number = "";
        
        letter = Character.toString((char) (num2 + 65 - i));
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((num1 * -1) + 56 - i));
        position += number;
        
        moves += position;
      }
    }
    return moves;
  }
}
