import java.util.Scanner;
public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    // PSUEDOCODE
    //class TicTacToe
    //	Main()
    //	{
    //		String currentPlayer = " X "
    //		clearBoard();
    //		for (int i = 0; i < 9; i++)
    //		{
    //			if (i = 1 || 3 || 5 || 7 || 9 ||) // if number = 1,3,5,7,9
    //			{
    //				currentPlayer = " X "
    //			else // if number = 0,2,4,6,8
    //			{
    //				currentPlayer = " O "
    //			}
    //
    //			do {
    //				row = SafeInput.getRangedInt(in, "Enter your row coordinate", 3, 1) - 1;
    //				col = SafeInput.getRangedInt(in, "Enter your column coordinate", 3, 1) - 1;
    //			}while(!isValidMove(row, col));
    //
    //			board[row][col] = currentPlayer
    //
    //			numPlays += 1
    //
    //			if (numPlays > 5) // numPlays greater than 5
    //			{
    //				if (isWin)
    //				{
    //					output "currentPlayer + " wins!" // display 
    //				}
    //			else if (numPlays > 7)
    //			{
    //				if (isTie)
    //				{
    //					output "It's a tie!" // display
    //				}
    //			}
    //
    //		}
    //
    //	}
    //
    //	return
    //endClass

    public static void main(String[] args)
    {
        // create new scanner
        Scanner in = new Scanner(System.in);
        // declaratiton of variable 
        int row;
        int col;
        String Name1 = "Player 1";
        String P1 = " X ";
        String Name2 = "Player 2";
        String P2 = " O ";
        String playerMove;
        String playerString;

        // Placing X or O //
        do{
            clearBoard();
            display();
            // declaration variable 
            int plays = 0;
            for (int turns = 0; turns < 9; turns++) // turns = 0,1,2,3,4,5,6,7,8
            {
                if (plays % 2 == 0) // plays = 0 
                {
                    playerMove = Name1;
                    playerString = P1;
                }
                else
                {
                    playerMove = Name2;
                    playerString = P2;
                }

                System.out.println("Its " + playerMove + "'s turn!" ); // display announcing turns 

                do {
                    row = SafeInput.getRangedInt(in, "Enter your row coordinate", 3, 1) - 1;
                    col = SafeInput.getRangedInt(in, "Enter your column coordinate", 3, 1) - 1;
                }while(!isValidMove(row, col));

                board[row][col] = playerString;
                display(); //Refreshes Display
                plays += 1;

               // checking
               if (plays >= 4) ///Check for full row/col
               {
                   if (isWin(playerString))
                   {
                       System.out.println(playerMove + " Wins!");
                       break;
                   }
                   else if (plays >= 7) //Impossible for a winner once past 7 turns and no winner
                   {
                       if(isTie())
                       {
                           System.out.println("Its a tie");
                           break;
                       }
                   }
               }
           }
        }while(SafeInput.getYNConfirm(in, "Play Again?")); // asking the user if they want to play again
    }
    // create a new private static class
    private static void display()
    {
        // decalration of variable 
        String displayBoard = "";
        for (int r = 0; r < ROW; r++) // if r =  1 or = 2
        {
            for (int c = 0; c < COL; c++) // if c = 1 or c =2
            {
                if (c == COL - 1)
                {
                    displayBoard += board[r][c];
                } else
                {
                    displayBoard += board[r][c] + "|";
                }
            }
            if (r != ROW - 1)
            {
                displayBoard += "\n---+---+---\n";
            }
        }
        System.out.println(displayBoard);
    }
    // create a new private static class
    private static void clearBoard()
    {
        for (int r = 0; r < ROW; r++)// if r =  1 or = 2
        {
            for (int c = 0; c < COL; c++)// if c = 1 or c =2
            {
                board[r][c] = "   ";
            }
        }
    }
    // create a new private static boolean
    private static boolean isValidMove(int row, int col)
    {
        return (board[row][col].equals("   "));
    }

    //Checking for Wins//
    // create a new private static boolean
    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    // create a new private static boolean
    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    // create a new private static boolean
    private static boolean isDiagonalWin(String player)
    {
        if ((board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) ||
                (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // create a new private static boolean
    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // create a new private static boolean
    private static boolean isTie()
    {
        // declaration variable
        int deadX = 0;
        int deadO = 0;
        int numTies = 0;
        for (int r = 0; r < ROW; r++)
        {
            for (int c = 0; c < COL; c++)
            {
                if (board[r][c].equals(" X "))
                {
                    deadX++;
                }
                else if (board[r][c].equals(" O "))
                {
                    deadO++;
                }
                if (deadX >= 1 && deadO >= 1) {
                    numTies++;
                }
            }
        }
        if (numTies >= 3) // numTies greater or equal 3
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
