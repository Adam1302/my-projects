/** 
 * Chess (for beginners)
 * Adam Abdulhamid
 * 
 * The program is very long; however, a large portion of the code is repitition of a recurring process that changes slightly depending on each piece 
 * All USER_____MOVE and COMPUTER_____MOVE classes involved similar calculations. See ComputerRookMoves and UserRookMoves classes for comments on the code.
 * 
 * The program captures all of the rules and gameplay of chess (tutorial on rules is in the code); however, it does not include advanced chess moves such as EN PASSANT and CASTLING which are only valid moves under very specific conditions.
 * 
 * The computer is not very 'intelligent'. Its possible moves are scored based on the piece's value and the value of the location to which it is moving.
 * So, a pawn capturing a queen would have more value than a bishop capturing a knight; therefore, the computer would select the higher scored move.
 * 
 * Concepts Included:
 * 
 * Variables:
 * ---Used throughout the program in piece selection, piece position, player names, etc.
 * 
 * Conditional loops
 * ---Checking for proper input for piece selection
 * ---Getting all possible moves with a specific piece of code
 * ---Repeating code until the user inputs a valid move
 * ---Repeating a code to check if the King is under attack
 * ---Finding all possible moves for a piece: contuining to move in a direction until a blockage
 * 
 * Methods
 * ---Used for everything throughout the code
 * 
 * Selection (If/Switch statements)
 * ---Used to check if a certain square is occupied
 * ---If the user enters a valid square number
 * ---Defining boundaries
 * etc.
 * 
 * Randomization
 * ---Displaying a different celebratory message when a player wins
 * ---Randomizing the computer's move
 * 
 * User Input:
 * ---Getting player names
 * ---Getting user moves (current position, desired move)
 * 
 * Read/Write Files:
 * ---Stores game result (LAST METHOD in main class)
 * 
 * Arrays (Bonus for appropriate use of 2 dimensional arrays):
 * ---Chess board is an integer array. All work done in the code uses arrays.
 * ---Also, randomized congratulatory or warning messages are held in string arrays.
 *
 * String manipulation/formatting:
 * ---Used in converting a position string (ex. a4) to a location on the board
 * ---Used in splitting a string containing all possible moves of a piece
 * ---Used to check if a string of all possible moves contains a user input
 * 
 * Classes and Objects
 * ---Used throughout the code, usually in defining the moves allowed for specific pieces
 * 
 * LINKS USED:
 * https://www.quora.com/How-can-a-character-literal-be-converted-into-an-integer-in-java - FOR CHAR CONVERSIONS
 * https://www.mkyong.com/java/java-convert-string-to-int/ - FOR STRING TO INT (both used in converting a string position to an array location on the board)
 * 
 * Used for:
 * char a = letter1.charAt(0);
 * num2 = Character.getNumericValue(a);
 * num1 = Integer.parseInt(number1);
 * 
 */

import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class ExperimentingWithChess 
{
  public static void main(String[] args)  throws InterruptedException, FileNotFoundException
  { 
    String name = "";
    int[][] board;
    
    board = setBoard();
    name = getName();
    introduction();
    play(board, name);
    
    System.out.println("Good game " + name);
  }
  
  public static String getName()
  {
    Scanner input = new Scanner(System.in);
    String name = "";
    do
    {
      System.out.print("Hello! What is your name?: ");
      name = input.nextLine();
    } while (name.isEmpty() == true);
    
    input.close();
    return name;
  }
  
  public static void introduction() throws InterruptedException 
  {
    Scanner input = new Scanner(System.in);
    
    int ready = 0;
    
    System.out.println("Welcome to Chess!");
    Thread.sleep(2000);
    System.out.println();
    System.out.println("Here are the rules of Chess:");
    Thread.sleep(2000);
    String[] intro = {"    ? The chess board is 8x8, with the 64 spaces alternating between dark and light shades.", 
      "    ? There are 2 players (white and black) each with 16 pieces made up of 6 different types of pieces (1 King, 1 Queen, 2 Rooks, 2 Bishops, 2 Knights, and 8 pawns).",
      "       o The pawn can only move 1 piece up, apart from 2 moves up on its first move and move 1 space diagonally when capturing a piece.",
      "       o The knight is the only pieces that can jump over other pieces when moving. Its valid moves are moving 2 spaces in one direction and one space in a direction perpendicular to the first (ex. it can move 2 spaces up and one right, or 2 spaces left and one down).",
      "       o The bishop can only move diagonally, remaining on the same colour of the board throughout the game.",
      "       o The rook can move vertically or horizontally.",
      "       o The Queen can move vertically, horizontally, or diagonally.",
      "       o The King can only move 1 space each turn (to a surrounding square).",
      "    ? The game is won when the opponent has no way to stop a direct attack on the King (checkmate). The game is drawn when a player has no possible moves while the King is in safety (stalemate).",
      "\nHere is the square representation that will be used in the game:",
      "\na8 b8 c8 d8 e8 f8 g8 h8",
      "a7 b7 c7 d7 e7 f7 g7 h7",
      "a6 b6 c6 d6 e6 f6 g6 h6",
      "a5 b5 c5 d5 e5 f5 g5 h5",
      "a4 b4 c4 d4 e4 f4 g4 h4",
      "a3 b3 c3 d3 e3 f3 g3 h3",
      "a2 b2 c2 d2 e2 f2 g2 h2",
      "a1 b1 c1 d1 e1 f1 g1 h1",
      "\nThe pawn are represented by the letter t, while the Kings by the letter a. Everything else by its first letter.",
      "Your pieces are capital letters, while the computer pieces are lowercase."
    };
    for (int i = 0; i < intro.length; i++)
    {
      System.out.println(intro[i]);
    }
    
    System.out.println("\nPlease read over the instructions on how to play chess, specifically with regards to this program.");
    while (true)
    {
      System.out.print("ENTER 5 when finished: ");
      try
      {
        ready = input.nextInt();
        if (ready == 5)
        {
          break;
        }
        else
        {
          System.out.print("Invalid input. ");
        }
      }
      catch (Exception e)
      {
        System.out.print("Invalid input. ");
      }
      finally
      {
        input.nextLine();
      }
    }
    Thread.sleep(1000);
    System.out.println("Get ready...");
    Thread.sleep(2000);
    input.close();
  }
  
  public static int[][] setBoard()
  {
    // Integer values of the board are assigned according to value of each piece. King is infinitely valuable in the game.
    int[][] board = {
      {-4, -2, -3, -5, -100000, -3, -2, -4},
      {-1, -1, -1, -1, -1, -1, -1, -1},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {1, 1, 1, 1, 1, 1, 1, 1},
      {4, 2, 3, 5, 100000, 3, 2, 4}
    };
    return board;
  }
  
  public static void displayBoard (int[][] board) 
  {
    //Your pieces are represented by capital letters, while the computer pieces are represented by lowercase letters.
    System.out.println();
    for (int i = 0; i <= 7; i++)
    {
      for (int j = 0; j <= 7; j++)
      {
        if (board[i][j] == -100000)
        {
          System.out.print("a");
        }
        else if (board[i][j] == -5)
        {
          System.out.print("q");
        }
        else if (board[i][j] == -4)
        {
          System.out.print("r");
        }
        else if (board[i][j] == -3)
        {
          System.out.print("b");
        }
        else if (board[i][j] == -2)
        {
          System.out.print("k");
        }
        else if (board[i][j] == -1)
        {
          System.out.print("t");
        }
        else if (board[i][j] == 100000)
        {
          System.out.print("A");
        }
        else if (board[i][j] == 5)
        {
          System.out.print("Q");
        }
        else if (board[i][j] == 4)
        {
          System.out.print("R");
        }
        else if (board[i][j] == 3)
        {
          System.out.print("B");
        }
        else if (board[i][j] == 2)
        {
          System.out.print("K");
        }
        else if (board[i][j] == 1)
        {
          System.out.print("T");
        }
        else
        {
          System.out.print("_");
        }
        System.out.print("  ");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  public static void play(int[][] board, String name) throws InterruptedException, FileNotFoundException
  {
    ComputerMoves ai = new ComputerMoves();
    UserInCheckmate userCheckmate = new UserInCheckmate();
    IsComputerKingInCheck compCheck = new IsComputerKingInCheck();
    int[][] newBoard = duplicateBoard(board); //Temporary Board
    int turns = 1;
    while (true)
    {
      displayBoard(board);
      if (turns % 2 != 0)
      {
        if (userCheckmate.checkmate(board) == true) //Checks if user is in checkmate after every computer move
        {
          System.out.println("CHECKMATE");
          break;
        }
        System.out.println("It is your turn.");
        board = makeAMove(board);
      }
      else
      {
        newBoard = ai.bestMove(board);
        if (compCheck.checked(newBoard) == true) //If the computer returns a board where the King is still in check, then it is in checkmate. This is because the first priority of its moves are to protect the King, meaning that if it doesn't do that, it can't - checkmate.
        {
          System.out.println("CHECKMATE");
          break;
        }
        else
        {
          board = duplicateBoard(newBoard); // If it's not checkmate, the temporary board becomes the permanent one.
        }
        System.out.println("\nComputer playing...\n");
        Thread.sleep(1000);
      }
      board = pawnPromotion(board); //If a pawn reaches the other side of the board, it is promoted into a queen
      turns++;
    }
    
    System.out.println(closingMessages(turns, name));
  }
  
  public static int[][] makeAMove (int[][] board)
  {
    IsUserKingInCheck valid = new IsUserKingInCheck(); //If the user king in check, the user must makeAMove that protects it.
    
    int[][] newBoard;
    
    //Original piece position in array
    int num1 = 0;
    int num2 = 0;
    
    //Original piece position in array
    int new1 = 0;
    int new2 = 0;
    
    String pos = ""; 
    String possibleMoves = "";
    String newPos = "";
    
    while (true) //Making sure a selected move is valid
    {
      newBoard = duplicateBoard(board);
      while (true) //Making sure a selected piece has possible moves
      {
        pos = pickPiece(board);
        possibleMoves = getMoves(board, pos);
        if (possibleMoves.isEmpty() == true)
        {
          System.out.println("No possible moves for that piece.");
        }
        else
        {
          break;
        }
      }
      newPos = pickDestination(board, possibleMoves);
      
      // Original position
      String letter1 = "";
      String number1 = "";
  
      letter1 = pos.substring(0,1);
      number1 = pos.substring(1);
  
      char a = letter1.charAt(0);
      num2 = Character.getNumericValue(a);
      num1 = Integer.parseInt(number1);
  
      num1 = 9 - num1 - 1;
      num2 -= 9;
      num2 -= 1;
      
      // New position
      String letter2 = "";
      String number2 = "";
  
      letter2 = newPos.substring(0,1);
      number2 = newPos.substring(1);
  
      char b = letter2.charAt(0);
      new2 = Character.getNumericValue(b);
      new1 = Integer.parseInt(number2);
  
      new1 = 9 - new1 - 1;
      new2 -= 9;
      new2 -= 1;
    
      newBoard[new1][new2] = newBoard[num1][num2]; //The new position takes the value of the old one - the piece moves
      newBoard[num1][num2] = 0; //The original position becomes empty (0).
      
      if (valid.checked(newBoard) == false) //If the King is still in check, the move can't be done.
      {
        break;
      }
      else
      {
        System.out.println("ERROR: User King would be in check.");
      }
    }
    
    return newBoard;
  }
  
  public static String pickPiece(int[][] board)
  {
    Scanner input = new Scanner(System.in);
    String position = "";
    String letter = "";
    String number = "";
    while (true)
    {
      System.out.print("Enter the location of the piece you would like to move (ex. a4, f2 - Check instructions): ");
      position = input.nextLine();
      position = position.trim().toLowerCase();
         //Position must be 2 characters long
      if (checkLength(position) == true)
      {
        letter = position.substring(0,1);
        number = position.substring(1);
         //Letter must be a-h
         //Number must be 1-8
        if ((validLetter(letter) == true) && (validNumber(number) == true) && (yourPiece(letter, number, board) == true))
        {
          break;
        }
      }
    }
    input.close();
    return position;
  }
  
  public static String pickDestination (int[][] board, String possibleMoves)
  {
    Scanner input = new Scanner(System.in);
    String position = "";
    String letter = "";
    String number = "";
    while (true)
    {
      System.out.print("Where would you like to move? (ex. a4, f2): ");
      position = input.nextLine();
      position = position.trim().toLowerCase();
         //Position must be 2 characters long
      if (checkLength(position) == true)
      {
        letter = position.substring(0,1);
        number = position.substring(1);
         //Letter must be a-h
         //Number must be 1-8
        if ((validLetter(letter) == true) && (validNumber(number) == true) && (possibleMoves.contains(position) == true))
        {
          break;
        }
        else
        {
          System.out.println("Not a valid move.");
        }
      }
    }
    input.close();
    return position;
  }
  
  public static boolean checkLength(String pos)
  {
    if (pos.length() == 2)
    {
      return true;
    }
    else
    {
       System.out.println("Error: Please enter a letter and a number (ex. d3, g5).");
       return false;
    }
  }
  
  public static boolean validLetter(String letter)
  {
    
    if ((letter.equals("a")) || (letter.equals("b")) || (letter.equals("c")) || (letter.equals("d")) || (letter.equals("e")) || (letter.equals("f")) || (letter.equals("g")) || (letter.equals("h")))
    {
      return true;
    }
    else
    {
      System.out.println("Error: Letter must be between a and h");
      return false;
    }
  }
  
  public static boolean validNumber (String number)
  {
    if ((number.equals("1")) || (number.equals("2")) || (number.equals("3")) || (number.equals("4")) ||  (number.equals("5")) || (number.equals("6")) || (number.equals("7")) || (number.equals("8")))
    {
      return true;
    }
    else
    {
      System.out.println("Error: Number must be between 1 - 8.");
      return false;
    }
  }
  
  public static boolean yourPiece(String letter, String number, int[][] board) //Making sure a position has a user piece.
  {
    int num1 = 0;
    int num2 = 0;
    int piece = 0;
    char a = letter.charAt(0);
    num2 = Character.getNumericValue(a);
    num1 = Integer.parseInt(number);
    
    num1 = 9 - num1;
    num2 -= 9;
    
    piece = board[num1 - 1][num2 - 1]; //A user piece would be positive.
    if (piece == 0)
    {
      System.out.println("Error: This square is empty.");
      return false;
    }
    else if (piece < 0)
    {
      System.out.println("Error: This is not your piece.");
      return false;
    }
    else
    {
      return true;
    }
  }
  
  public static String getMoves (int[][] board, String position) //getting all possible moves
  {
    int piece = 0;
    
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
    
    piece = board[num1][num2];
    
    if (piece == 1)
    {
      UserPawnMoves pawn = new UserPawnMoves();
      possibleMoves = pawn.possiblePawnMoves(position, board);
    }
    else if (piece == 2)
    {
      UserKnightMoves knight = new UserKnightMoves();
      possibleMoves = knight.possibleKnightMoves(position, board);
    }
    else if (piece == 3)
    {
      UserBishopMoves bishop = new UserBishopMoves();
      possibleMoves = bishop.possibleBishopMoves(position, board);
    }
    else if (piece == 4)
    {
      UserRookMoves rook = new UserRookMoves();
      possibleMoves = rook.possibleRookMoves(position, board);
    }
    else if (piece == 5)
    {
      UserQueenMoves queen = new UserQueenMoves();
      possibleMoves = queen.possibleQueenMoves(position, board);
    }
    else if (piece == 100000)
    {
      UserKingMoves king = new UserKingMoves();
      possibleMoves = king.possibleKingMoves(position, board);
    }
    return possibleMoves;
  }
  
  public static int[][] pawnPromotion (int[][] board) //Pawn to queen promotion if the pawn reaches the end of the board
  {
    for (int j = 0; j <= 7; j++)
    {
      if (board[0][j] == 1)
      {
        board[0][j] = 5;
      }
    }
    
    for (int j = 0; j <= 7; j++)
    {
      if (board[7][j] == -1)
      {
        board[7][j] = -5;
      }
    }
    
    return board;
  }
  
  public static int[][] duplicateBoard (int[][] board)
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
  
  public static String closingMessages (int turns, String name) throws FileNotFoundException
  {
    Random rand = new Random();
    PrintStream diskWriter = new PrintStream("ComputerDiary.txt");
    
    String[] win = {"Great job!", "Amazing", "Grandmaster status reached...", "Efficient win", "You are a Chess Machine!"};
    String[] loss = {"What happened?", "Better luck next time", "MWAHAHA", "Improve your game and come back", "Tough luck..."};
    if (turns % 2 == 0)
    {
      diskWriter.println(name + " beat me today.");
      System.out.println("YOU WIN!");
      diskWriter.close();
      return win[rand.nextInt(5)];
    }
    else
    {
      diskWriter.println("I beat " + name + " today.");
      System.out.println("YOU LOSE!");
      diskWriter.close();
      return loss[rand.nextInt(5)];
    }
  }
}
