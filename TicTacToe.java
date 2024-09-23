package com.csc;

import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        boolean gameWon = false;
        boolean gameDraw = false;

        printBoard();
        
        while (!gameWon && !gameDraw) {
            playerMove();
            printBoard();
            gameWon = checkWin();
            gameDraw = checkDraw();
            if (!gameWon && !gameDraw) {
                switchPlayer();
            }
        }

        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    // Method to print the tic-tac-toe board
    private static void printBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("-----------");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("-----------");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    // Method to handle player moves
    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int move;
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Player " + currentPlayer + " - Where would you like to move?");
            move = scanner.nextInt();

            if (move >= 1 && move <= 9) {
                int row = (move - 1) / 3;
                int col = (move - 1) % 3;
                if (board[row][col] != 'X' && board[row][col] != 'O') {
                    board[row][col] = currentPlayer;
                    validMove = true;
                } else {
                    System.out.println("That move is invalid! Cell already taken.");
                }
            } else {
                System.out.println("That move is invalid! Choose a number between 1 and 9.");
            }
        }
    }

    // Method to switch player turns
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to check if there is a winning condition
    private static boolean checkWin() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
               (board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    // Method to check if the game is a draw
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}