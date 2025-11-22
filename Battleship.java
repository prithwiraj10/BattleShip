import java.util.Scanner;

public class Battleship {

    public static final int SIZE = 10;

    // 5 classic ships
    public static final int[] SHIP_SIZES = {5, 4, 3, 3, 2};
    public static final int NUM_SHIPS = SHIP_SIZES.length;

    // Board symbols:
    // ~ = water
    // S = ship (hidden)
    // X = hit
    // O = miss
    public static char[][] board = new char[SIZE][SIZE];

    // Total number of ship cells (to detect win)
    public static int totalShipCells = 0;
    public static int hits = 0;

    public static void main(String[] args) {
        System.out.println("=== Battleship Game ===");

        initializeBoard();
        placeAllShips();

        // Uncomment to see ships while debugging:
        // printBoardDebug();

        playGame();
    }

    /**
     * Initialize board with water
     */
    public static void initializeBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = '~';
            }
        }
    }

    /**
     * Place all ships
     */
    public static void placeAllShips() {
        for (int ship : SHIP_SIZES) {
            placeSingleShip(ship);
            totalShipCells += ship;
        }
    }

    /**
     * Place one ship randomly
     */
    public static void placeSingleShip(int size) {
        boolean placed = false;

        while (!placed) {
            int row = (int)(Math.random() * SIZE);
            int col = (int)(Math.random() * SIZE);
            boolean horizontal = Math.random() < 0.5;

            if (canPlaceShip(row, col, size, horizontal)) {
                for (int i = 0; i < size; i++) {
                    if (horizontal) {
                        board[row][col + i] = 'S';
                    } else {
                        board[row + i][col] = 'S';
                    }
                }
                placed = true;
            }
        }
    }

    /**
     * Checks if ship fits in the chosen location
     */
    public static boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (board[row][col + i] == 'S') return false;
            }
        } else {
            if (row + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (board[row + i][col] == 'S') return false;
            }
        }
        return true;
    }

    /**
     * Main game loop
     */
    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (hits < totalShipCells) {
            printBoard();

            System.out.println("\nEnter coordinates to fire:");
            System.out.print("Row (1-10): ");
            int row = scanner.nextInt() - 1;

            System.out.print("Column (1-10): ");
            int col = scanner.nextInt() - 1;

            // Input validation
            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                System.out.println("Invalid coordinates! Try again.");
                continue;
            }

            char cell = board[row][col];

            if (cell == 'X' || cell == 'O') {
                System.out.println("You already fired here! Try again.");
                continue;
            }

            if (cell == 'S') {
                System.out.println("🔥 HIT!");
                board[row][col] = 'X';
                hits++;
            } else {
                System.out.println("💧 MISS!");
                board[row][col] = 'O';
            }
        }

        System.out.println("\n🎉 You sank ALL ships! You win! 🎉");
        revealAllShips();
        printBoardReveal();
    }

    /**
     * Print board with ships HIDDEN
     */
    public static void printBoard() {
        System.out.print("   ");
        for (int col = 0; col < SIZE; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.printf("%2d ", (row + 1));
            for (int col = 0; col < SIZE; col++) {
                char cell = board[row][col];
                if (cell == 'S') {
                    System.out.print("~ ");  // hide ships
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Reveal ships after winning
     */
    public static void revealAllShips() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 'S') {
                    board[r][c] = 'S';
                }
            }
        }
    }

    public static void printBoardReveal() {
        System.out.println("\nFINAL BOARD:");
        System.out.print("   ");
        for (int col = 0; col < SIZE; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.printf("%2d ", (row + 1));
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Debug printer showing ships
     */
    public static void printBoardDebug() {
        System.out.println("\nDEBUG BOARD (Shows Ships):");

        System.out.print("   ");
        for (int col = 0; col < SIZE; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.printf("%2d ", (row + 1));
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}