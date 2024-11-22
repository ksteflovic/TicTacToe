package Board;

import java.util.Scanner;

/**
 * @author KŠ
 * 22.11.2024 14:17
 */

// View
public class Board {

public void displayBoard(char[][] board) {
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

    public int getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextInt() - 1;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

}
