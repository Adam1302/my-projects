/**
 * Main class is ExperimentingWithChess.java
 */
public class ComputerQueenMoves 
{
  public String possibleQueenMoves(String position, int[][] board)
  {
    String possibleMoves = "";
    ComputerBishopMoves diagonals = new ComputerBishopMoves();
    ComputerRookMoves horizAndVerts = new ComputerRookMoves();
    
    possibleMoves += diagonals.possibleBishopMoves(position, board);
    possibleMoves += horizAndVerts.possibleRookMoves(position, board);
    
    return possibleMoves;
  }
}
