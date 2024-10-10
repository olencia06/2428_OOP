
/*
 * Author: Olencia Fernandes
 * Roll no: 2428
 * Topic: Simple Game(minesweeper)
 * Start Date: 05 August 2024
 * Modified Date: 19 August 2024
 * Description: Creating a Game using java
 */

import java.util.Random;
import java.util.Scanner;

public class SimpleMinesweeper {

    static final int FIELD_SIZE = 5;
    static final int NUM_MINES = 3;
    static final int SAFE_CELLS = FIELD_SIZE * FIELD_SIZE - NUM_MINES;
    static char[][] gameField = new char[FIELD_SIZE][FIELD_SIZE];
    static boolean[][] mines = new boolean[FIELD_SIZE][FIELD_SIZE];
    static int revealedCells = 0;

    public static void main(String[] args) {
        printIntroduction(); // Print the introduction and instructions
        setupField();
        placeMines();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayField();
            System.out.print("Enter row and column (e.g., 1 1): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (mines[row][col]) {
                System.out.println("Boom! You hit a mine. Game Over!");
                displayAllMines();
                break;
            } else {
                int mineCount = countMines(row, col);
                gameField[row][col] = (char) (mineCount + '0');
                revealedCells++;
                clearConsole();

                if (revealedCells == SAFE_CELLS) {
                    displayField();
                    System.out.println("Congratulations! You've revealed all safe cells. You win!");
                    displayAllMines();
                    break;
                }
            }
        }

        scanner.close();
    }

    static void printIntroduction() {
        System.out.println(
                "Welcome to Simple Minesweeper!\n" +
                        "This is a simplified version of the classic Minesweeper game.\n\n" +
                        "How to Play:\n" +
                        "- The game field is a 5x5 grid with 3 hidden mines.\n" +
                        "- Your goal is to reveal all the safe cells without hitting a mine.\n" +
                        "- Each safe cell will display the number of mines in the surrounding cells.\n" +
                        "- Enter the row and column numbers to reveal a cell.\n" +
                        "- If you hit a mine, the game is over. If you reveal all safe cells, you win!\n\n" +
                        "Let's start the game!\n" +
                        "---------------------------------------------");
    }

    static void setupField() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                gameField[row][col] = '-';
            }
        }
    }

    static void placeMines() {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < NUM_MINES) {
            int row = random.nextInt(FIELD_SIZE);
            int col = random.nextInt(FIELD_SIZE);

            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    static int countMines(int row, int col) {
        int mineCount = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < FIELD_SIZE && j >= 0 && j < FIELD_SIZE && mines[i][j]) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    static void displayField() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                System.out.print(gameField[row][col] + " ");
            }
            System.out.println();
        }
    }

    static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void displayAllMines() {
        System.out.println("Final field with all mines:");
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (mines[row][col]) {
                    System.out.print("* ");
                } else {
                    int mineCount = countMines(row, col);
                    System.out.print(mineCount + " ");
                }
            }
            System.out.println();
        }
    }
}
