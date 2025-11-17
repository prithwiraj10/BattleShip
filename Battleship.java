import java.util.Scanner;

public class Battleship {

   
    public static final int SIZE = 8;

    // 2D array for the board
    public static char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {
        System.out.println("=== Battleship Game (Early Version) ===");

        initializeBoard();
        printBoard();

        System.out.println("\nSetup complete — next step will be adding ships and player input.");
    }

    /**
     * Fill the board with water (~)
     */
    public static void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = '~'; // water
            }
        }
    }

    /**
     * Print the current board
     */
    public static void printBoard() {
        System.out.print("  ");
        for (int col = 0; col < SIZE; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}