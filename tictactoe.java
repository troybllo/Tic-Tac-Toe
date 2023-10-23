import java.util.*;
import java.util.Scanner;

public class tictactoe {
    private char[][] board; // 3x3 board
    private char currentPlayer; // 'X' or 'O'//
    private char cpu;

    public static void main(String[] args) {
        tictactoe game = new tictactoe();
        game.play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        board = new char[3][3]; // initialize the board with empty spaces
        currentPlayer = 'X';
        cpu = 'O';

        int x, y;
        do {
            System.out.println("Current player: " + currentPlayer);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | "); // print the board
                }
                System.out.println("\n---------");
            }

            // prompt the user to enter their move (x,y coordinates)
            do {
                System.out.print("Enter your move (row col): ");
                String input = scanner.nextLine();
                x = Integer.parseInt(input.split(",")[0]);
                y = Integer.parseInt(input.split(",")[1]);
            } while (!isValidMove(x, y)); // check if the move is valid (in bounds)

            board[x][y] = currentPlayer; // make the move
            currentPlayer = switch (currentPlayer) {
                case 'X' -> 'O';
                case 'O' -> 'X';
                default -> throw new AssertionError("Unexpected character");
            };
            cpuMove();
        } while (!checkWin()); // check if the game has ended (winner or draw)
                               // 
        printResult(); // print the final result
    }

    public void cpuMove () {
      Random random = new Random();
      int x, y;
      System.out.println("CPU" + cpu);
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          System.out.print(board[i][j] + " | "); // print the board 
        }
        System.out.println("\n---------");
      }

      do {
        System.out.println("CPU's turn to move!");
        x = (int)(Math.random()*3);
        y = (int)(Math.random()*3);
        
        
      }
      while(!isValidMove(x, y));

       board[x][y] = cpu; // make the move
       cpu = switch (cpu) {
            case 'O' -> 'X';
            case 'X' -> 'O';
            default -> throw new AssertionError("Unexpected character");
            };
        printResult(); // print the final result
        System.out.println(board[x][y]);

 
   }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private boolean checkWin() {
        // check rows and columns for a win
       for (int i = 0; i <3; i++) {
         if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true;
       } 

       for (int i = 0; i <3; i++) {
         if (board[i][0] == cpu && board[i][1] == cpu && board[i][2] == cpu)
                return true;
            if (board[0][i] == cpu && board[1][i] == cpu && board[2][i] == cpu)
                return true;
       } 


       if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            return true;

        // check for a draw (full board)
        boolean allFilled = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    allFilled = false;
                }
            }
        }
        if (allFilled) {
            return false;
        }

        // no win or draw, game still in progress
        return true;
    }

    private void printResult() {
        System.out.println("Current player: " + currentPlayer);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | "); // print the board
            }
            System.out.println("\n---------");
        }
        if (checkWin()) {
            System.out.println("Player " + currentPlayer + " wins!");
        }
        else if (checkWin()) {
          System.out.println("CPU " + cpu + " wins!"); 
        }
        else {
            System.out.println("It's a draw!");
        }
    }
}
