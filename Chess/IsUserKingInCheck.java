/**
 * Main class is ExperimentingWithChess.java
 * Comments for this code are in IsComputerKingInCheck class (very similar code)
 */
public class IsUserKingInCheck 
{
  public boolean checked (int[][] board)
  {
    int a = 0;
    int b = 0;
    boolean kingFound = false;
    while (a < 8)
    {
      while (b < 8)
      {
        if (board[a][b] == 100000)
        {
          kingFound = true;
          break;
        }
        b++;
      }
      if (kingFound == true)
      {
        break;
      }
      a++;
      b = 0;
    }
    
    String position = "";
    String letter = "";
    String number = "";
        
    letter = Character.toString((char) (b + 65));
    letter = letter.toLowerCase();
    position += letter;
        
    number = Character.toString((char) ((a * -1) + 56));
    position += number;
    
    if ((isRookOrQueenChecking(position, board) == false) && (isBishopOrQueenChecking(position, board) == false) && (isKnightChecking(position, board) == false) && (isPawnChecking(position, board) == false) && (surroundingKing(position, board) == false))
    {
      return false;
    }
    else
    {
      return true;
    }
  }
  
  public boolean isRookOrQueenChecking (String position, int[][] board)
  {
    UserRookMoves rook = new UserRookMoves();
    
    String rookMoves = rook.possibleRookMoves(position, board);
    String newMoves = rookMoves;
    String currentSpace = "";
    String letter = "";
    String number = "";
  
    boolean checked = false;
    int i = 0;
    int num1 = 0;
    int num2 = 0;
    
    while (i < (newMoves.length() - 1))
    {
      currentSpace = rookMoves.substring(i, i + 2);
      
      letter = currentSpace.substring(0,1);
      number = currentSpace.substring(1);
      
      char a = letter.charAt(0);
      num2 = Character.getNumericValue(a);
      num1 = Integer.parseInt(number);
  
      num1 = 9 - num1 - 1;
      num2 -= 9;
      num2 -= 1;
      
      if ((board[num1][num2] == -4) || (board[num1][num2] == -5))
      {
        checked = true;
        break;
      }
      
      i += 2;
    }
    return checked;
  }
  
  public boolean isBishopOrQueenChecking (String position, int[][] board)
  {
    UserBishopMoves bishop = new UserBishopMoves();
    
    String bishopMoves = bishop.possibleBishopMoves(position, board);
    String newMoves = bishopMoves;
    String currentSpace = "";
    String letter = "";
    String number = "";
  
    boolean checked = false;
    
    int i = 0;
    int num1 = 0;
    int num2 = 0;
    
    while (i < (newMoves.length() - 1))
    {
      currentSpace = bishopMoves.substring(i, i + 2);
      
      letter = currentSpace.substring(0,1);
      number = currentSpace.substring(1);
      
      char a = letter.charAt(0);
      num2 = Character.getNumericValue(a);
      num1 = Integer.parseInt(number);
  
      num1 = 9 - num1 - 1;
      num2 -= 9;
      num2 -= 1;
      
      if ((board[num1][num2] == -3) || (board[num1][num2] == -5))
      {
        checked = true;
        break;
      }
      i += 2;
    }
    return checked;
  }
  
  public boolean isKnightChecking (String position, int[][] board)
  {
    UserKnightMoves knight = new UserKnightMoves();
    
    String knightMoves = knight.possibleKnightMoves(position, board);
    String newMoves = knightMoves;
    String currentSpace = "";
    String letter = "";
    String number = "";
  
    boolean checked = false;
    
    int i = 0;
    int num1 = 0;
    int num2 = 0;
    
    while (i < (newMoves.length() - 1))
    {
      currentSpace = knightMoves.substring(i, i + 2);
      
      letter = currentSpace.substring(0,1);
      number = currentSpace.substring(1);
      
      char a = letter.charAt(0);
      num2 = Character.getNumericValue(a);
      num1 = Integer.parseInt(number);
  
      num1 = 9 - num1 - 1;
      num2 -= 9;
      num2 -= 1;
      
      if (board[num1][num2] == -2)
      {
        checked = true;
        break;
      }
      i += 2;
    }
    return checked;
  }
  
  public boolean isPawnChecking (String position, int[][] board)
  {
    UserPawnMoves pawn = new UserPawnMoves();
    
    String currentSpace = "";
    String letter = "";
    String number = "";
    
    int num1 = 0;
    int num2 = 0;
    
    letter = position.substring(0,1);
    number = position.substring(1);
      
    char a = letter.charAt(0);
    num2 = Character.getNumericValue(a);
    num1 = Integer.parseInt(number);
  
    num1 = 9 - num1 - 1;
    num2 -= 9;
    num2 -= 1;
    
    String pawnMoves = "";
    pawnMoves += pawn.capturing(num1, num2, board, "");
    String newMoves = pawnMoves;
    
    char b;
    boolean checked = false;
    
    int i = 0;
    
    while (i < (newMoves.length() - 1))
    {
      currentSpace = pawnMoves.substring(i, i + 2);
      
      letter = currentSpace.substring(0,1);
      number = currentSpace.substring(1);
      
      b = letter.charAt(0);
      num2 = Character.getNumericValue(b);
      num1 = Integer.parseInt(number);
  
      num1 = 9 - num1 - 1;
      num2 -= 9;
      num2 -= 1;
      
      if (board[num1][num2] == -1)
      {
        checked = true;
        break;
      }
      i += 2;
    }
    return checked;
  }
  
  public boolean surroundingKing(String position, int[][] board)
  {
    boolean checked = false;
    
    String letter = "";
    String number = "";
    
    int num1 = 0;
    int num2 = 0;
    
    letter = position.substring(0,1);
    number = position.substring(1);
      
    char a = letter.charAt(0);
    num2 = Character.getNumericValue(a);
    num1 = Integer.parseInt(number);
  
    num1 = 9 - num1 - 1;
    num2 -= 9;
    num2 -= 1;
    
    if (num1 > 0)
    {
      if (board[num1 - 1][num2] == -100000)
      {
        checked = true;
      }
      else if ((num2 > 0) && (board[num1 - 1][num2 - 1] == -100000))
      {
        checked = true;
      }
      else if ((num2 < 7) && (board[num1 - 1][num2 + 1] == -100000))
      {
        checked = true;
      }
    }
    
    if ((num1 < 7) && (checked == false))
    {
      if (board[num1 + 1][num2] == -100000)
      {
        checked = true;
      }
      else if ((num2 > 0) && (board[num1 + 1][num2 - 1] == -100000))
      {
        checked = true;
      }
      else if ((num2 < 7) && (board[num1 + 1][num2 + 1] == -100000))
      {
        checked = true;
      }
    }
    
    if ((num2 > 0) && (board[num1][num2 - 1] == -100000) && (checked == false)) // CHECK AGAIN IF IT DOESN"T WORK
    {
      checked = true;
    }
    if ((num2 < 7) && (board[num1][num2 + 1] == -100000) && (checked == false)) // CHECK AGAIN IF IT DOESN"T WORK
    {
      checked = true;
    }
    
    return checked;
  }
}
