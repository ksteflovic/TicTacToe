package Presenter;

import Board.Board;
import Model.Model;

import java.util.Scanner;

/**
 * @author KŠ
 * 22.11.2024 14:30
 */

public class Presenter {
    private final Model model;
    private final Board board;
    private final Scanner scanner;

    public Presenter(Model model, Board view) {
        this.model = model;
        this.board = view;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        boolean isPlayer1Turn = true;
        int winner = -1;

        while (winner == -1) {
            board.displayBoard(model.getBoard());
            char currentPlayer = isPlayer1Turn ? 'X' : 'O';

            boolean validMove = false;
            int row = 0, col = 0;
            while (!validMove) {
                row = board.getInput("[" + currentPlayer + "] Enter row: ", scanner);
                col = board.getInput("[" + currentPlayer + "] Enter column: ", scanner);
                validMove = model.isValidMove(row, col);
                if (!validMove) {
                    board.displayMessage("Invalid move!");
                }
            }

            model.makeMove(row, col, currentPlayer);
            winner = model.checkWinner(currentPlayer);

            if (winner != -1) {
                board.displayBoard(model.getBoard());
                if (winner == 0) {
                    board.displayMessage("It's a draw!");
                } else {
                    board.displayMessage("Player " + winner + " won!");
                }
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }
}
