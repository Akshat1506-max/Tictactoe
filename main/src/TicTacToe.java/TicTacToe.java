import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private static final char EMPTY = ' ';
    private Scanner scanner;
    private Random random;
    private boolean isComputerPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        random = new Random();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void displayBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("\n-----------");
        }
        System.out.println();
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == EMPTY) return false;
            }
        }
        return true;
    }

    private boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private void makeMove(int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        if (board[row][col] == EMPTY) {
            board[row][col] = currentPlayer;
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        } else {
            System.out.println("Invalid move! Try again.");
        }
    }

    private void makeRandomMove() {
        ArrayList<Integer> emptyPositions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    emptyPositions.add(i * 3 + j + 1);
                }
            }
        }
        int randomMove = emptyPositions.get(random.nextInt(emptyPositions.size()));
        makeMove(randomMove);
        System.out.println("The computer player moved in space " + randomMove + ".");
    }

    private void displayMainMenu() {
        System.out.println("Welcome to Tic-Tac-Toe!\nPlease choose a game mode:");
        System.out.println("(1) Human vs Human");
        System.out.println("(2) Human vs Computer");

        while (true) {
            int choice = scanner.nextInt();
            if (choice == 1) {
                isComputerPlayer = false;
                break;
            } else if (choice == 2) {
                isComputerPlayer = true;
                break;
            } else {
                System.out.println("That is not a valid choice! Please choose again:");
            }
        }
    }

    private boolean exitMenu() {
        System.out.println("Would you like to play again?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");

        while (true) {
            int choice = scanner.nextInt();
            if (choice == 1) return true;
            else if (choice == 2) {
                System.out.println("Goodbye!");
                return false;
            } else {
                System.out.println("That is not a valid choice! Please choose again:");
            }
        }
    }

    public void playGame() {
        displayMainMenu();
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            currentPlayer = 'X';
            boolean gameEnded = false;

            while (!gameEnded) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " - where would you like to move?");

                if (currentPlayer == 'X' || !isComputerPlayer) {
                    int move = scanner.nextInt();
                    makeMove(move);
                } else {
                    makeRandomMove();
                }

                if (isWinner('X')) {
                    displayBoard();
                    System.out.println("Player X wins!");
                    gameEnded = true;
                } else if (isWinner('O')) {
                    displayBoard();
                    System.out.println("Player O wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    gameEnded = true;
                }
            }

            playAgain = exitMenu();
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}