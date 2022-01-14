/**
 * Main class is ExperimentingWithChess.java
 */
public class ComputerKingMoves 
{
  public String possibleKingMoves(String position, int[][] board)
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
    
    if (num1 > 0)
    {
      possibleMoves += movingUp(board, num1, num2);
      if (num2 > 0)
      {
        possibleMoves += movingNorthWest(board, num1, num2);
      }
      if (num2 < 7)
      {
        possibleMoves += movingNorthEast(board, num1, num2);
      }
    }
    if (num1 < 7)
    {
      possibleMoves += movingDown(board, num1, num2);
      if (num2 > 0)
      {
        possibleMoves += movingSouthWest(board, num1, num2);
      }
      if (num2 < 7)
      {
        possibleMoves += movingSouthEast(board, num1, num2);
      }
    }
    if (num2 > 0)
    {
      possibleMoves += movingLeft(board, num1, num2);
    }
    if (num2 < 7)
    {
      possibleMoves += movingRight(board, num1, num2);
    }
    
    return possibleMoves;
  }
  
  public String movingUp (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1 - 1][num2] >= 0)
    {
      letter = Character.toString((char) (num2 + 65));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56 + 1));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingDown (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1 + 1][num2] >= 0)
    {
      letter = Character.toString((char) (num2 + 65));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56 - 1));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingLeft (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1][num2 - 1] >= 0)
    {
      letter = Character.toString((char) (num2 + 65 - 1));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingRight (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1][num2 + 1] >= 0)
    {
      letter = Character.toString((char) (num2 + 65 + 1));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingNorthWest (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1 - 1][num2 - 1] >= 0)
    {
      letter = Character.toString((char) (num2 + 65 - 1));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56 + 1));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingNorthEast (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1 - 1][num2 + 1] >= 0)
    {
      letter = Character.toString((char) (num2 + 65 + 1));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56 + 1));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingSouthWest (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1 + 1][num2 - 1] >= 0)
    {
      letter = Character.toString((char) (num2 + 65 - 1));
      letter = letter.toLowerCase();
      position += letter;
        
      number = Character.toString((char) ((num1 * -1) + 56 - 1));
      position += number;
        
      moves += position;
    }
    
    return moves;
  }
  
  public String movingSouthEast (int[][] board, int num1, int num2)
  {
    String position = "";
    String letter = "";
    String number = "";
    String moves = "";
    
    if (board[num1 + 1][num2 + 1] >= 0)
    {
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
