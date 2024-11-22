import model.Board;

import java.util.Scanner;

/**
 * @author KŠ
 * 22.11.2024 14:15
 */

public class App {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the board size: ");
        int boardSize = scanner.nextInt();
        System.out.print("Enter the number of symbols in a row required to win: ");
        int required = scanner.nextInt();

        char[][] board = new char[boardSize][boardSize];
        int winner = -1; // -1 = no winner, game continues; 0 = no winner and board space (draw); 1 = X wins; 2 = O wins
        boolean isPlayer1Turn = true;

        // board initialization
        boardInit(board);

        while (winner == -1) {
            boolean validMove = false;
            int row = 0;
            int column = 0;
            while (!validMove) {
                printBoard(board);
                System.out.print(((isPlayer1Turn)? "[X]" : "[O]") + " Enter row: ");
                row = scanner.nextInt() - 1;
                System.out.print(((isPlayer1Turn)? "[X]" : "[O]") + " Enter column: ");
                column = scanner.nextInt() - 1;

                validMove = isValidMove(board, row, column);
                if (!validMove) {
                    System.out.println("Invalid move!");
                }
            }

            if (isPlayer1Turn) {
                board[row][column] = 'X';
            } else {
                board[row][column] = 'O';
            }

            winner = getWinner(board, required, isPlayer1Turn);
            if (winner != -1) {
                printBoard(board);
                if (winner == 0) {
                    System.out.println("It's a draw!");
                } else {
                    System.out.println("Player " + winner + " won!");
                }
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    public static boolean isValidMove(char[][] board, int row, int column) {
        return row >= 0 && row < board.length && column >= 0 && column < board[0].length && board[row][column] == ' ';
    }

    public static void boardInit(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getWinner(char[][] board, int required, boolean firstMoved) {
        char moved = firstMoved ? 'X' : 'O';
        // check rows and columns
        for (int i = 0; i < board.length; i++) {
            int inARowA = 0;
            int inARowB = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == moved) inARowA++; else inARowA = 0;
                if (board[j][i] == moved) inARowB++; else inARowB = 0;
                if (inARowA == required || inARowB == required) return firstMoved ? 1 : 2;
            }
        }
        // check diagonals
        for (int i = 0; i < board.length; i++) {
            int inARowA = 0;
            int inARowB = 0;
            for (int j = 0; j <= i; j++) {
                int row = j;
                int column = board.length - (i + 1) + j;
                if (board[row][column] == moved) inARowA++; else inARowA = 0;
                if (board[column][row] == moved) inARowB++; else inARowB = 0;
                if (inARowA == required || inARowB == required) return firstMoved ? 1 : 2;
            }
        }
        for (int i = 0; i < board.length; i++) {
            int inARowA = 0;
            int inARowB = 0;
            for (int j = 0; j <= i; j++) {
                int row = i - j;
                int column = j;
                if (board[row][column] == moved) inARowA++; else inARowA = 0;
                if (board[board.length - 1 - row][board.length - 1 - column] == moved) inARowB++; else inARowB = 0;
                if (inARowA == required || inARowB == required) return firstMoved ? 1 : 2;
            }
        }
        if (isBoardFull(board)) return 0;
        return -1;
    }

    public static void printBoard(char[][] board) {
        int length = String.valueOf(board.length).length();
        String verticalSep = " ".repeat(length) + " " + "----".repeat(board[0].length) + "-";
        StringBuilder header = new StringBuilder(" ".repeat(length) + "  ");
        for (int i = 0; i < board[0].length; i++) {
            header.append(" ").append(i + 1).append("  ");
        }
        System.out.println(header);
        System.out.println(verticalSep);
        for (int i = 0; i < board.length; i++) {
            System.out.print((i + 1) + " ".repeat(length - String.valueOf(i + 1).length() + 1) + "| ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[0].length - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.print(" |");
            System.out.println();
            System.out.println(verticalSep);
        }
    }

}
