/**
 * Main class is ExperimentingWithChess.java
 */
public class UserQueenMoves 
{
  public String possibleQueenMoves(String position, int[][] board)
  {
    String possibleMoves = "";
    UserBishopMoves diagonals = new UserBishopMoves();
    UserRookMoves horizAndVerts = new UserRookMoves();
    
    possibleMoves += diagonals.possibleBishopMoves(position, board);
    possibleMoves += horizAndVerts.possibleRookMoves(position, board);
    
    return possibleMoves;
  }
}
