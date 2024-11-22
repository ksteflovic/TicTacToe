import Board.Board;
import Presenter.Presenter;
import Model.Model;

import java.util.Scanner;

/**
 * @author KŠ
 * 22.11.2024 14:15
 */

public class TicTacToeGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the board size: ");
        int boardSize = scanner.nextInt();
        System.out.print("Enter the number of symbols in a row required to win: ");
        int requiredToWin = scanner.nextInt();

        Board board = new Board();
        Model model = new Model(boardSize, requiredToWin);
        Presenter presenter = new Presenter(model, board);

        presenter.startGame();
    }


}
