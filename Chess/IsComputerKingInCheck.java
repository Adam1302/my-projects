/**
 * Main class is ExperimentingWithChess.java
 */
public class IsComputerKingInCheck 
{
  public boolean checked (int[][] board)
  {
    int a = 0;
    int b = 0;
    boolean kingFound = false;
    while (a < 8) // Getting the position of the King
    {
      while (b < 8)
      {
        if (board[a][b] == -100000)
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
        
    letter = Character.toString((char) (b + 65)); //See UserRookMoves for explanation of these.
    letter = letter.toLowerCase();
    position += letter;
        
    number = Character.toString((char) ((a * -1) + 56));
    position += number;
    
    if ((isRookOrQueenChecking(position, board) == false) && (isBishopOrQueenChecking(position, board) == false) && (isKnightChecking(position, board) == false) && (isPawnChecking(position, board) == false) && (surroundingKing(position, board) == false))
    {
      // If none of the pieces are attacking the king, the king is not in check
      return false;
    }
    else
    {
      return true;
    }
  }
  // For each method, I am starting from the position of the King and finding the possible moves if it was another piece (ex. Rook). If one of those possible moves contains that opposing piece, the King is in check.
  //      ex. The King's position will return multiple moves if the rook was in that position. If an opposing rook is in anyone of those positions, it means it is directly attacking the King. 
  public boolean isRookOrQueenChecking (String position, int[][] board)
  {
    ComputerRookMoves rook = new ComputerRookMoves();
    
    String rookMoves = rook.possibleRookMoves(position, board);
    String newMoves = rookMoves;
    String currentSpace = "";
    String letter = "";
    String number = "";
  
    boolean checked = false;
    int i = 0;
    int num1 = 0;
    int num2 = 0;
    
    while (i < (newMoves.length() - 1)) //Going through every possible move.
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
      
      if ((board[num1][num2] == 4) || (board[num1][num2] == 5)) //Rook and Queen can make these moves.
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
    ComputerBishopMoves bishop = new ComputerBishopMoves();
    
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
      
      if ((board[num1][num2] == 3) || (board[num1][num2] == 5)) //Bishop and Queen can make these moves.
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
    ComputerKnightMoves knight = new ComputerKnightMoves();
    
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
      
      if (board[num1][num2] == 2)
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
    ComputerPawnMoves pawn = new ComputerPawnMoves();
    
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
    pawnMoves += pawn.capturing(num1, num2, board, ""); //Pawn can only 'attack' diagonally.
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
      
      if (board[num1][num2] == 1)
      {
        checked = true;
        break;
      }
      i += 2;
    }
    
    return checked;
  }
  
  public boolean surroundingKing(String position, int[][] board) //A King cannot move to a space adjacent to the opposing King.
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
      if (board[num1 - 1][num2] == 100000)
      {
        checked = true;
      }
      else if ((num2 > 0) && (board[num1 - 1][num2 - 1] == 100000))
      {
        checked = true;
      }
      else if ((num2 < 7) && (board[num1 - 1][num2 + 1] == 100000))
      {
        checked = true;
      }
    }
    
    if ((num1 < 7) && (checked == false))
    {
      if (board[num1 + 1][num2] == 100000)
      {
        checked = true;
      }
      else if ((num2 > 0) && (board[num1 + 1][num2 - 1] == 100000))
      {
        checked = true;
      }
      else if ((num2 < 7) && (board[num1 + 1][num2 + 1] == 100000))
      {
        checked = true;
      }
    }
    
    if ((num2 > 0) && (board[num1][num2 - 1] == 100000) && (checked == false))
    {
      checked = true;
    }
    if ((num2 < 7) && (board[num1][num2 + 1] == 100000) && (checked == false))
    {
      checked = true;
    }
    
    return checked;
  }
  
}
