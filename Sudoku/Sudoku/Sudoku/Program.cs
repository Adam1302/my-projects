using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Media;

namespace Trying_it_out
{
    class Program
    {
        static void Main(string[] args)
        {
            /* Name: Adam Abdulhamid
            * Project: Sudoku Project
            * Sources: (CLASS NOTES) +  https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/arrays/multidimensional-arrays 
            * + http://www.theasciicode.com.ar/ascii-codes.txt - Used for the boxes when printing the puzzle (#254)
            * 
            * */

            string playAgain;
            string userName;
            Console.Title = "Let's Play Sudoku!";

            do
            {
                SoundPlayer music = new SoundPlayer("YouTube.wav");
                music.PlayLooping();

                userName = IntroductionAndName();

                string level = GetLevel();

                Random rndm = new Random();

                int[,] originalPuzzle = GetBoard(rndm, level);

                int[,] puzzleArray = originalPuzzle;

                Console.WriteLine("Here is your puzzle:");

                PrintTheBoard(ref puzzleArray);

                bool valid = true;
                bool complete;
                int selection = 0;
                int boxNum = 0;
                int userInput = 0;
                int currentX = 0;
                int currentY = 0;
                int guesses = 0;

                int[,] smallBox;

                do
                {
                    Thread.Sleep(2500);

                    selection = WhichSmallBox();

                    smallBox = SmallBoxes(puzzleArray, selection);

                    GetInput(smallBox, ref boxNum, ref userInput, selection, ref currentX, ref currentY);

                    guesses++;

                    valid = Validity(valid, puzzleArray, smallBox, ref currentX, ref currentY, ref userInput);

                    if (valid == true)
                    {
                        puzzleArray = Reassign(puzzleArray, smallBox, ref boxNum, ref userInput, selection);
                        Console.Beep(1000, 100);
                        Console.WriteLine(OfferEncouragement(rndm));
                    }
                    else
                    {
                        Console.WriteLine("Invalid Input. Please check the boxes, rows and columns for the same numbers.");
                        Console.Write("Would you like reset your puzzle?(YES or NO): ");
                        if (Console.ReadLine().Trim().ToUpper() == "YES")
                        {
                            puzzleArray = originalPuzzle;
                        }
                    }

                    PrintTheBoard(ref puzzleArray);

                    complete = IsItComplete(ref puzzleArray);
                } while (complete == false);

                Console.WriteLine("Congratulations! You have completed the Sudoku! It took you {0} guesses!", guesses);

                playAgain = PlayingAgain();
            } while (playAgain == "YES");
        }

        static string IntroductionAndName()
        {
            Console.WriteLine("Welcome to Sudoku! Turn your volume up!");
            Console.Write("Please enter your name: ");
            string name = Console.ReadLine();
            Console.WriteLine("Hi {0}!", name);
            RulesOfSudoku();
            return name;
        }

        static void RulesOfSudoku ()
        {
            Console.Write("Do you know how to play Sudoku? (YES or NO): ");
            string response;
            while (true)
            {
                response = Console.ReadLine().Trim().ToUpper();
                if (response == "YES")
                {
                    Console.WriteLine("Great! Have fun...");
                    break;
                }
                else if (response == "NO")
                {
                    Console.WriteLine("The rules of Sudoku are simple: \n " +
                        "- There are nine boxes each made up of nine smaller squares. Together, they make up one big 9 x 9 square. \n" +
                        "- These squares must be filled using the numbers 1 to 9. Each number must be used only once in every box, row and column");
                    break;
                }
                else
                {
                    Console.WriteLine("Invalid input. Please enter YES or NO...");
                }
            }
        }

        static string GetLevel()
        {
            string level = "";
            Console.WriteLine("There are three levels to this game: EASY, MEDIUM, and HARD...");
            while (true)
            {
                Console.Write("Enter the level you would like to play on: ");
                level = Console.ReadLine().ToUpper().Trim();
                if (level == "EASY" || level == "MEDIUM" || level == "HARD")
                {
                    break;
                }
                else
                {
                    Console.WriteLine("Invalid Input");
                }
            }
            Console.WriteLine("You picked {0}. Good luck!", level);
            return level;
        }

        static int[,] GetBoard(Random rand, string level)
        {
            int puzzleNumber;

            int[,] board = new int[9, 9];

            puzzleNumber = rand.Next(1, 6);

            if (level == "EASY")
            {
                if (puzzleNumber == 1)
                {
                    board = new int[,] {
                        { 8, 4, 9, 2, 1, 0, 3, 0, 6},
                        { 0, 6, 5, 0, 0, 8, 0, 0, 1},
                        { 0, 0, 7, 0, 6, 0, 0, 0, 0},
                        { 5, 0, 0, 7, 0, 0, 0, 0, 0},
                        { 0, 2, 6, 0, 5, 0, 9, 1, 0},
                        { 0, 0, 0, 0, 0, 1, 0, 0, 4},
                        { 0, 0, 0, 0, 8, 0, 4, 0, 0},
                        { 1, 0, 0, 5, 0, 0, 2, 8, 0},
                        { 9, 0, 8, 0, 7, 2, 1, 6, 3} };
                }

                else if (puzzleNumber == 2)
                {
                    board = new int[,] {
                        { 0, 0, 6, 0, 8, 0, 2, 0, 0},
                        { 7, 9, 0, 1, 0, 0, 8, 0, 0},
                        { 8, 4, 0, 6, 0, 0, 0, 0, 0},
                        { 4, 1, 0, 8, 3, 6, 0, 9, 0},
                        { 0, 8, 0, 2, 0, 1, 0, 4, 0},
                        { 0, 6, 0, 9, 4, 5, 0, 7, 8},
                        { 0, 0, 0, 0, 0, 2, 0, 3, 1},
                        { 0, 0, 4, 0, 0, 7, 0, 8, 6},
                        { 0, 0, 9, 0, 6, 0, 4, 0, 0} };
                }

                else if (puzzleNumber == 3)
                {
                    board = new int[,] {
                        { 8, 0, 7, 0, 9, 3, 0, 0, 0},
                        { 0, 9, 6, 0, 8, 0, 0, 1, 7},
                        { 1, 4, 0, 0, 0, 6, 0, 0, 0},
                        { 0, 0, 0, 0, 2, 4, 6, 0, 0},
                        { 7, 3, 0, 6, 0, 1, 0, 9, 2},
                        { 0, 0, 2, 7, 3, 0, 0, 0, 0},
                        { 0, 0, 0, 8, 0, 0, 0, 2, 4},
                        { 2, 8, 0, 0, 4, 0, 7, 3, 0},
                        { 0, 0, 0, 3, 6, 0, 1, 0, 8} };
                }

                else if (puzzleNumber == 4)
                {
                    board = new int[,] {
                        { 7, 9, 0, 0, 4, 0, 0, 5, 0},
                        { 0, 0, 4, 5, 1, 0, 0, 0, 9},
                        { 3, 8, 0, 0, 0, 0, 6, 0, 0},
                        { 9, 5, 3, 2, 0, 4, 0, 8, 0},
                        { 0, 4, 0, 0, 0, 0, 0, 9, 0},
                        { 0, 1, 0, 3, 0, 9, 4, 2, 7},
                        { 0, 0, 8, 0, 0, 0, 0, 4, 1},
                        { 4, 0, 0, 0, 8, 1, 9, 0, 0},
                        { 0, 3, 0, 0, 2, 0, 0, 6, 8} };
                }

                else if (puzzleNumber == 5)
                {
                    board = new int[,] {
                        { 6, 0, 3, 9, 0, 0, 4, 0, 0},
                        { 0, 0, 0, 1, 0, 8, 6, 2, 3},
                        { 2, 0, 0, 0, 4, 0, 5, 0, 7},
                        { 4, 1, 0, 0, 0, 0, 2, 7, 0},
                        { 0, 0, 0, 0, 6, 0, 0, 0, 0},
                        { 0, 9, 6, 0, 0, 0, 0, 3, 4},
                        { 1, 0, 9, 0, 3, 0, 0, 0, 5},
                        { 7, 6, 5, 2, 0, 1, 0, 0, 0},
                        { 0, 0, 4, 0, 0, 9, 1, 0, 2} };
                }
            }

            if (level == "MEDIUM")
            {
                if (puzzleNumber == 1)
                {
                    board = new int[,] {
                        { 0, 5, 9, 0, 8, 0, 0, 4, 1},
                        { 0, 0, 0, 0, 0, 1, 0, 0, 8},
                        { 0, 0, 0, 0, 0, 4, 3, 7, 0},
                        { 0, 0, 0, 7, 6, 0, 0, 0, 5},
                        { 0, 3, 0, 0, 2, 0, 0, 8, 0},
                        { 1, 0, 0, 0, 4, 8, 0, 0, 0},
                        { 0, 1, 8, 4, 0, 0, 0, 0, 0},
                        { 5, 0, 0, 2, 0, 0, 0, 0, 0},
                        { 3, 7, 0, 0, 9, 0, 1, 2, 0} };
                }

                else if (puzzleNumber == 2)
                {
                    board = new int[,] {
                        { 2, 4, 0, 0, 0, 6, 0, 8, 5},
                        { 6, 0, 5, 0, 0, 0, 0, 1, 4},
                        { 0, 0, 1, 0, 0, 0, 0, 0, 0},
                        { 1, 5, 0, 2, 0, 0, 0, 0, 3},
                        { 7, 0, 0, 9, 0, 3, 0, 0, 6},
                        { 3, 0, 0, 0, 0, 4, 0, 7, 2},
                        { 0, 0, 0, 0, 0, 0, 2, 0, 0},
                        { 4, 1, 0, 0, 0, 0, 5, 0, 9},
                        { 9, 8, 0, 5, 0, 0, 0, 3, 1} };
                }

                else if (puzzleNumber == 3)
                {
                    board = new int[,] {
                        { 0, 5, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 9, 0, 6, 0, 4, 3, 0},
                        { 8, 0, 0, 7, 5, 9, 0, 0, 0},
                        { 0, 0, 0, 6, 0, 0, 2, 1, 3},
                        { 0, 0, 0, 0, 2, 0, 0, 0, 0},
                        { 9, 1, 2, 0, 0, 8, 0, 0, 0},
                        { 0, 0, 0, 4, 7, 5, 0, 0, 1},
                        { 0, 2, 6, 0, 1, 0, 8, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 7, 0} };
                }

                else if (puzzleNumber == 4)
                {
                    board = new int[,] {
                        { 0, 0, 3, 1, 9, 5, 0, 0, 2},
                        { 5, 7, 0, 0, 0, 2, 0, 0, 0},
                        { 0, 0, 0, 0, 3, 0, 0, 0, 9},
                        { 0, 0, 0, 0, 0, 3, 0, 6, 0},
                        { 4, 0, 5, 0, 0, 0, 2, 0, 7},
                        { 0, 9, 0, 2, 0, 0, 0, 0, 0},
                        { 7, 0, 0, 0, 1, 0, 0, 0, 0},
                        { 0, 0, 0, 7, 0, 0, 0, 4, 1},
                        { 1, 0, 0, 5, 8, 9, 7, 0, 0} };
                }

                else if (puzzleNumber == 5)
                {
                    board = new int[,] {
                        { 0, 3, 0, 7, 2, 0, 9, 0, 0},
                        { 2, 1, 4, 9, 0, 0, 0, 0, 0},
                        { 0, 7, 0, 0, 0, 6, 0, 0, 0},
                        { 0, 0, 0, 5, 0, 0, 0, 9, 3},
                        { 0, 0, 0, 0, 6, 0, 0, 0, 0},
                        { 9, 8, 0, 0, 0, 3, 0, 0, 0},
                        { 0, 0, 0, 8, 0, 0, 0, 6, 0},
                        { 0, 0, 0, 0, 0, 7, 8, 5, 1},
                        { 0, 0, 6, 0, 5, 4, 0, 3, 0} };
                }
            }

            if (level == "HARD")
            {
                if (puzzleNumber == 1)
                {
                    board = new int[,] {
                        { 0, 0, 9, 0, 0, 4, 0, 0, 0},
                        { 5, 0, 6, 0, 1, 2, 0, 0, 0},
                        { 1, 4, 0, 0, 0, 0, 0, 6, 9},
                        { 2, 8, 0, 5, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 8, 0, 3, 1},
                        { 4, 9, 0, 0, 0, 0, 0, 1, 3},
                        { 0, 0, 0, 2, 9, 0, 6, 0, 7},
                        { 0, 0, 0, 1, 0, 0, 8, 0, 0} };
                }

                else if (puzzleNumber == 2)
                {
                    board = new int[,] {
                        { 0, 1, 0, 0, 0, 0, 0, 6, 0},
                        { 0, 2, 0, 0, 0, 0, 0, 0, 3},
                        { 8, 0, 0, 1, 0, 7, 9, 0, 2},
                        { 0, 0, 0, 6, 5, 0, 0, 0, 4},
                        { 0, 0, 0, 4, 0, 8, 0, 0, 0},
                        { 4, 0, 0, 0, 3, 1, 0, 0, 0},
                        { 2, 0, 1, 8, 0, 3, 0, 0, 9},
                        { 6, 0, 0, 0, 0, 0, 0, 7, 0},
                        { 0, 3, 0, 0, 0, 0, 0, 1, 0} };
                }

                else if (puzzleNumber == 3)
                {
                    board = new int[,] {
                        { 0, 0, 0, 4, 9, 0, 0, 0, 0},
                        { 4, 0, 2, 0, 0, 0, 0, 8, 0},
                        { 0, 6, 0, 1, 0, 0, 0, 5, 0},
                        { 0, 0, 0, 6, 0, 9, 0, 7, 0},
                        { 0, 0, 8, 0, 7, 0, 6, 0, 0},
                        { 0, 3, 0, 5, 0, 4, 0, 0, 0},
                        { 0, 8, 0, 0, 0, 1, 0, 3, 0},
                        { 0, 9, 0, 0, 0, 0, 4, 0, 1},
                        { 0, 0, 0, 0, 3, 7, 0, 0, 0} };
                }

                else if (puzzleNumber == 4)
                {
                    board = new int[,] {
                        { 9, 0, 2, 0, 0, 0, 0, 4, 0},
                        { 0, 0, 8, 6, 0, 1, 0, 5, 0},
                        { 0, 0, 0, 0, 9, 0, 0, 0, 0},
                        { 0, 0, 3, 9, 0, 7, 0, 6, 1},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 8, 1, 0, 4, 0, 2, 7, 0, 0},
                        { 0, 0, 0, 0, 4, 0, 0, 0, 0},
                        { 0, 2, 0, 5, 0, 6, 3, 0, 0},
                        { 0, 8, 0, 0, 0, 0, 9, 0, 5} };
                }

                else if (puzzleNumber == 5)
                {
                    board = new int[,] {
                        { 0, 0, 7, 0, 8, 0, 6, 2, 0},
                        { 3, 6, 0, 5, 2, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 5, 3, 0, 0, 0, 0, 0, 0, 7},
                        { 0, 0, 6, 0, 7, 0, 8, 0, 0},
                        { 8, 0, 0, 0, 0, 0, 0, 4, 2},
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        { 0, 0, 0, 0, 4, 8, 0, 9, 3},
                        { 0, 1, 8, 0, 5, 0, 2, 0, 0} };
                }
            }
            return board;
        }

        static bool Validity(bool valid, int[,] array, int[,] smallBox, ref int currentX, ref int currentY, ref int userInput)
        {
            valid = true;
            for (int y = 0; y <= 2; y++)
            {
                for (int x = 0; x <= 2; x++)
                {
                    if (smallBox[x, y] == userInput)
                    {
                        valid = false;
                        break;
                    }
                }
                if (valid == false)
                {
                    break;
                }
            }

            if (valid == true)
            {
                for (int x = 0; x <= 8; x++)
                {
                    if (array[x, currentY] == userInput)
                    {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid == true)
            {
                for (int y = 0; y <= 8; y++)
                {
                    if (array[currentX, y] == userInput)
                    {
                        valid = false;
                    }
                }
            }
            return valid;
        }

        static int[,] SmallBoxes(int[,] array, int selection)
        {
            int i = 0;
            int[,] returnBox = new int[3, 3];

            if (selection == 1)
            {
                for (int x = 0; x <= 2; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        returnBox[x, y] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 2)
            {
                for (int x = 3; x <= 5; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        returnBox[x - 3, y] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 3)
            {
                for (int x = 6; x <= 8; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        returnBox[x - 6, y] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 4)
            {
                for (int x = 0; x <= 2; x++)
                {
                    for (int y = 3; y <= 5; y++)
                    {
                        returnBox[x, y - 3] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 5)
            {
                for (int x = 3; x <= 5; x++)
                {
                    for (int y = 3; y <= 5; y++)
                    {
                        returnBox[x - 3, y - 3] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 6)
            {
                for (int x = 6; x <= 8; x++)
                {
                    for (int y = 3; y <= 5; y++)
                    {
                        returnBox[x - 6, y - 3] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 7)
            {
                for (int x = 0; x <= 2; x++)
                {
                    for (int y = 6; y <= 8; y++)
                    {
                        returnBox[x, y - 6] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 8)
            {
                for (int x = 3; x <= 5; x++)
                {
                    for (int y = 6; y <= 8; y++)
                    {
                        returnBox[x - 3, y - 6] = array[x, y];
                        i++;
                    }
                }
            }

            else if (selection == 9)
            {
                for (int x = 6; x <= 8; x++)
                {
                    for (int y = 6; y <= 8; y++)
                    {
                        returnBox[x - 6, y - 6] = array[x, y];
                        i++;
                    }
                }
            }

            return returnBox;
        }

        static string OfferEncouragement (Random rndm)
        {
            string[] encouragement = { "Nice move!", "Well played!", "That works!", "Awesome!", "Sudoku Expert?...", "Way to go!", "Keep it up!" };
            int i = rndm.Next(0, encouragement.Length);
            return encouragement[i];
        }

        static int WhichSmallBox()
        {
            Console.WriteLine("The boxes are numbered 1-9 from top left to bottom right, moving horizontally.");
            Console.Write("Enter the small box you would like to get a number from: ");

            int smallBox = GetGoodBoxNumber();

            return smallBox;
        }

        static void GetInput(int[,] smallBox, ref int boxNum, ref int userNumber, int selection, ref int currentX, ref int currentY)
        {
            for (int y = 0; y <= 2; y++)
            {
                for (int x = 0; x <= 2; x++)
                {
                    if (smallBox[x, y] != 0)
                    {
                        Console.Write(smallBox[x, y] + " ");
                    }
                    else
                    {
                        Console.Write("■ ");
                    }
                }
                Console.WriteLine();
            }

            Console.WriteLine("The boxes are numbered 1-9 from top left to bottom right, moving horizontally.");

            Console.Write("Box number: ");

            boxNum = GetGoodBoxNumber();

            Console.Write("Enter Input: ");

            userNumber = GetGoodBoxNumber();

            currentX = GetCurrentX(ref boxNum, selection, currentX);

            currentY = GetCurrentY(ref boxNum, selection, currentY);
        }

        static int GetCurrentX(ref int boxNum, int selection, int currentX)
        {
            if (selection == 1 || selection == 4 || selection == 7)
            {
                currentX = 0;
                if (boxNum == 2 || boxNum == 5 || boxNum == 8)
                {
                    currentX++;
                }
                else if (boxNum == 3 || boxNum == 6 || boxNum == 9)
                {
                    currentX += 2;
                }
            }

            else if (selection == 2 || selection == 5 || selection == 8)
            {
                currentX = 3;
                if (boxNum == 2 || boxNum == 5 || boxNum == 8)
                {
                    currentX++;
                }
                else if (boxNum == 3 || boxNum == 6 || boxNum == 9)
                {
                    currentX += 2;
                }
            }

            else if (selection == 3 || selection == 6 || selection == 9)
            {
                currentX = 6;
                if (boxNum == 2 || boxNum == 5 || boxNum == 8)
                {
                    currentX++;
                }
                else if (boxNum == 3 || boxNum == 6 || boxNum == 9)
                {
                    currentX += 2;
                }
            }
            return currentX;
        }

        static int GetCurrentY(ref int boxNum, int selection, int currentY)
        {
            if (selection >= 1 && selection <= 3)
            {
                currentY = 0;
                if (boxNum >= 4 && boxNum <= 6)
                {
                    currentY++;
                }
                else if (boxNum >= 7 && boxNum <= 9)
                {
                    currentY += 2;
                }
            }

            else if (selection >= 4 && selection <= 6)
            {
                currentY = 3;
                if (boxNum >= 4 && boxNum <= 6)
                {
                    currentY++;
                }
                else if (boxNum >= 7 && boxNum <= 9)
                {
                    currentY += 2;
                }
            }

            else if (selection >= 7 && selection <= 9)
            {
                currentY = 6;
                if (boxNum >= 4 && boxNum <= 6)
                {
                    currentY++;
                }
                else if (boxNum >= 7 && boxNum <= 9)
                {
                    currentY += 2;
                }
            }
            return currentY;
        }

        static int[,] Reassign(int[,] array, int[,] smallBox, ref int boxNum, ref int userNumber, int selection)
        {
            smallBox = InsertNumber(smallBox, ref boxNum, ref userNumber);

            if (selection == 1)
            {
                for (int x = 0; x <= 2; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        array[x, y] = smallBox[x, y];
                    }
                }
            }

            else if (selection == 2)
            {
                for (int x = 3; x <= 5; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        array[x, y] = smallBox[x - 3, y];
                    }
                }
            }

            else if (selection == 3)
            {
                for (int x = 6; x <= 8; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        array[x, y] = smallBox[x - 6, y];
                    }
                }
            }

            else if (selection == 4)
            {
                for (int x = 0; x <= 2; x++)
                {
                    for (int y = 3; y <= 5; y++)
                    {
                        array[x, y] = smallBox[x, y - 3];
                    }
                }
            }

            else if (selection == 5)
            {
                for (int x = 3; x <= 5; x++)
                {
                    for (int y = 3; y <= 5; y++)
                    {
                        array[x, y] = smallBox[x - 3, y - 3];
                    }
                }
            }

            else if (selection == 6)
            {
                for (int x = 6; x <= 8; x++)
                {
                    for (int y = 3; y <= 5; y++)
                    {
                        array[x, y] = smallBox[x - 6, y - 3];
                    }
                }
            }

            else if (selection == 7)
            {
                for (int x = 0; x <= 2; x++)
                {
                    for (int y = 6; y <= 8; y++)
                    {
                        array[x, y] = smallBox[x, y - 6];
                    }
                }
            }

            else if (selection == 8)
            {
                for (int x = 3; x <= 5; x++)
                {
                    for (int y = 6; y <= 8; y++)
                    {
                        array[x, y] = smallBox[x - 3, y - 6];
                    }
                }
            }

            else if (selection == 9)
            {
                for (int x = 6; x <= 8; x++)
                {
                    for (int y = 6; y <= 8; y++)
                    {
                        array[x, y] = smallBox[x - 6, y - 6];
                    }
                }
            }
            return array;
        }

        static int[,] InsertNumber(int[,] smallBox, ref int boxNum, ref int userNumber)
        {
            if (boxNum == 1)
            {
                smallBox[0, 0] = userNumber;
            }
            else if (boxNum == 2)
            {
                smallBox[1, 0] = userNumber;
            }
            else if (boxNum == 3)
            {
                smallBox[2, 0] = userNumber;
            }
            else if (boxNum == 4)
            {
                smallBox[0, 1] = userNumber;
            }
            else if (boxNum == 5)
            {
                smallBox[1, 1] = userNumber;
            }
            else if (boxNum == 6)
            {
                smallBox[2, 1] = userNumber;
            }
            else if (boxNum == 7)
            {
                smallBox[0, 2] = userNumber;
            }
            else if (boxNum == 8)
            {
                smallBox[1, 2] = userNumber;
            }
            else if (boxNum == 9)
            {
                smallBox[2, 2] = userNumber;
            }
            return smallBox;
        } // used inside Reassign

        static int GetGoodBoxNumber()// used inside InputAndReassign and WhichSmallBox 
        {
            int boxNumber = 0;

            while (true)
            {
                try
                {
                    boxNumber = Convert.ToInt32(Console.ReadLine());
                    if (boxNumber >= 1 && boxNumber <= 9)
                    {
                        break;
                    }
                    else
                    {
                        Console.WriteLine("The integer must be from 1 to 9. Try again.");
                    }
                }
                catch
                {
                    Console.WriteLine("Invalid Input. You did not enter a valid integer. Try again.");
                }
            }
            return boxNumber;
        }

        static void PrintTheBoard(ref int[,] puzzleArray)
        {
            Thread.Sleep(1500);
            Console.Clear();

            Console.ForegroundColor = ConsoleColor.Yellow;

            for (int y = 0; y <= 8; y++)
            {
                for (int x = 0; x <= 8; x++)
                {
                    if (puzzleArray[x, y] != 0)
                    {
                        Console.Write(puzzleArray[x, y] + " ");
                    }
                    else
                    {
                        Console.Write("■ ");
                    }
                    if ((x + 1) % 3 == 0)
                    {
                        Console.Write(" ");
                    }
                }
                Console.WriteLine();
                if ((y + 1) % 3 == 0)
                {
                    Console.WriteLine();
                }
            }

            Console.ForegroundColor = ConsoleColor.White;
        }

        static bool IsItComplete(ref int[,] array)
        {
            bool complete = true;
            for (int y = 0; y <= 8; y++)
            {
                for (int x = 0; x <= 8; x++)
                {
                    if (array[x, y] == 0)
                    {
                        complete = false;
                        break;
                    }
                }
                if (complete == false)
                {
                    break;
                }
            }
            return complete;
        }

        static string PlayingAgain()
        {
            string playAgain;
            while (true)
            {
                Console.Write("Would you like to play again? Enter YES or NO: ");
                playAgain = Console.ReadLine().Trim().ToUpper();
                if (playAgain == "YES")
                {
                    Console.WriteLine("Great!");
                    return "YES";
                }
                else if (playAgain == "NO")
                {
                    Console.WriteLine("Thank you for playing!");
                    return "NO";
                }
                else
                {
                    Console.WriteLine("Invalid Input. Try again.");
                }
            }
        }
    }
}

