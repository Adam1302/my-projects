/**
 * This class will get all possible user moves in make them. If, after each and every one of them, the King is still in check, then we know that the user is in Checkmate.
 * 
 * Main class is ExperimentingWithChess.java
 */
public class UserInCheckmate 
{
  
  public boolean checkmate (int[][] board)
  {
    UserKingMoves king = new UserKingMoves();
    UserQueenMoves queen = new UserQueenMoves();
    UserRookMoves rook = new UserRookMoves();
    UserBishopMoves bishop = new UserBishopMoves();
    UserKnightMoves knight = new UserKnightMoves();
    UserPawnMoves pawn = new UserPawnMoves();
    IsUserKingInCheck valid = new IsUserKingInCheck();
    
    boolean checkmate = true;
    
    String position = "";
    String letter = "";
    String number = "";
    String move = "";
    String letter2 = "";
    String number2 = "";
    
    int[][] newBoard = duplicateBoard(board);
    
    int whichMove = 0;
    int i = 0;
    int j = 0;
    
    int new1 = 0;
    int new2 = 0;
    
    while (i < 8)
    {
      while (j < 8)
      {
        whichMove = 0;
        letter = Character.toString((char) (j + 65)); //Explained in UserRookMoves
        letter = letter.toLowerCase();
        position += letter;
        
        number = Character.toString((char) ((i * -1) + 56));
        position += number;
        if (board[i][j] == 5)
        {
          String queenMoves = queen.possibleQueenMoves(position, board);
    
          while (whichMove < queenMoves.length() - 1)
          {
            move = queenMoves.substring(whichMove, whichMove + 2);
            
            new1 = 0;
            new2 = 0;
    
            letter2 = "";
            number2 = "";
  
            letter2 = move.substring(0,1);
            number2 = move.substring(1);
  
            char a = letter2.charAt(0);
            new2 = Character.getNumericValue(a);
            new1 = Integer.parseInt(number2);
  
            new1 = 9 - new1 - 1;
            new2 -= 9;
            new2 -= 1;
            
            newBoard[new1][new2] = newBoard[i][j];
            newBoard[i][j] = 0;
     
            if (valid.checked(newBoard) == false)
            {
              return false;
            }
            newBoard = duplicateBoard(board);
            whichMove += 2;
          }
        }
        else if (board[i][j] == 4)
        {
          String rookMoves = rook.possibleRookMoves(position, board);
    
          while (whichMove < rookMoves.length() - 1)
          {
            move = rookMoves.substring(whichMove, whichMove + 2);
            
            new1 = 0;
            new2 = 0;
    
            letter2 = "";
            number2 = "";
  
            letter2 = move.substring(0,1);
            number2 = move.substring(1);
  
            char b = letter2.charAt(0);
            new2 = Character.getNumericValue(b);
            new1 = Integer.parseInt(number2);
  
            new1 = 9 - new1 - 1;
            new2 -= 9;
            new2 -= 1;
            
            newBoard[new1][new2] = newBoard[i][j];
            newBoard[i][j] = 0;
     
            if (valid.checked(newBoard) == false)
            {
              return false;
            }
            newBoard = duplicateBoard(board);
            whichMove += 2;
          }
        }
        else if (board[i][j] == 3)
        {
          String bishopMoves = bishop.possibleBishopMoves(position, board);
    
          while (whichMove < bishopMoves.length() - 1)
          {
            move = bishopMoves.substring(whichMove, whichMove + 2);
            
            new1 = 0;
            new2 = 0;
    
            letter2 = "";
            number2 = "";
  
            letter2 = move.substring(0,1);
            number2 = move.substring(1);
  
            char c = letter2.charAt(0);
            new2 = Character.getNumericValue(c);
            new1 = Integer.parseInt(number2);
  
            new1 = 9 - new1 - 1;
            new2 -= 9;
            new2 -= 1;
            
            newBoard[new1][new2] = newBoard[i][j];
            newBoard[i][j] = 0;
     
            if (valid.checked(newBoard) == false)
            {
              return false;
            }
            newBoard = duplicateBoard(board);
            whichMove += 2;
          }
        }
        else if (board[i][j] == 2)
        {
          String knightMoves = knight.possibleKnightMoves(position, board);
    
          while (whichMove < knightMoves.length() - 1)
          {
            move = knightMoves.substring(whichMove, whichMove + 2);
    
            new1 = 0;
            new2 = 0;
    
            letter2 = "";
            number2 = "";
  
            letter2 = move.substring(0,1);
            number2 = move.substring(1);
  
            char d = letter2.charAt(0);
            new2 = Character.getNumericValue(d);
            new1 = Integer.parseInt(number2);
  
            new1 = 9 - new1 - 1;
            new2 -= 9;
            new2 -= 1;
      
            newBoard[new1][new2] = newBoard[i][j];
            newBoard[i][j] = 0;
     
            if (valid.checked(newBoard) == false)
            {
              return false;
            }
            newBoard = duplicateBoard(board);
            whichMove += 2;
          }
        }
        else if (board[i][j] == 1)
        {
          String pawnMoves = pawn.possiblePawnMoves(position, board);
    
          while (whichMove < pawnMoves.length() - 1)
          {
            move = pawnMoves.substring(whichMove, whichMove + 2);
    
            new1 = 0;
            new2 = 0;
    
            letter2 = "";
            number2 = "";
  
            letter2 = move.substring(0,1);
            number2 = move.substring(1);
  
            char e = letter2.charAt(0);
            new2 = Character.getNumericValue(e);
            new1 = Integer.parseInt(number2);
  
            new1 = 9 - new1 - 1;
            new2 -= 9;
            new2 -= 1;
      
            newBoard[new1][new2] = newBoard[i][j];
            newBoard[i][j] = 0;
     
            if (valid.checked(newBoard) == false)
            {
              return false;
            }
            newBoard = duplicateBoard(board);
            whichMove += 2;
          }
        }
        else if (board[i][j] == 100000)
        {
          String kingMoves = king.possibleKingMoves(position, board);
    
          while (whichMove < kingMoves.length() - 1)
          {
            move = kingMoves.substring(whichMove, whichMove + 2);
    
            new1 = 0;
            new2 = 0;
    
            letter2 = "";
            number2 = "";
  
            letter2 = move.substring(0,1);
            number2 = move.substring(1);
  
            char f = letter2.charAt(0);
            new2 = Character.getNumericValue(f);
            new1 = Integer.parseInt(number2);
  
            new1 = 9 - new1 - 1;
            new2 -= 9;
            new2 -= 1;
      
            newBoard[new1][new2] = newBoard[i][j];
            newBoard[i][j] = 0;
     
            if (valid.checked(newBoard) == false)
            {
              return false;
            }
            newBoard = duplicateBoard(board);
            whichMove += 2;
          }
        }
        position = "";
        j++;
      }
      i++;
      j = 0;
    }
    return checkmate;
  }
  
  public int[][] duplicateBoard (int[][] board)
  {
    int[][] newBoard = new int[8][8];
    
    for (int i = 0; i < 8; i++)
    {
      for (int j = 0; j < 8; j++)
      {
        newBoard[i][j] = board[i][j];
      }
    }
    
    return newBoard;
  }
}
