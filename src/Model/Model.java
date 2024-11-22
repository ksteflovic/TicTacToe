package Model;

/**
 * @author KŠ
 * 22.11.2024 14:29
 */

public class Model {
    private final char[][] board;
    private final int boardSize;
    private final int requiredToWin;

    public Model(int boardSize, int requiredToWin) {
        this.boardSize = boardSize;
        this.requiredToWin = requiredToWin;
        this.board = new char[boardSize][boardSize];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] == ' ';
    }

    public void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkLine(player, i, 0, 0, 1) || checkLine(player, 0, i, 1, 0)) {
                return player == 'X' ? 1 : 2;
            }
        }

        // Check diagonals
        if (checkLine(player, 0, 0, 1, 1) || checkLine(player, 0, boardSize - 1, 1, -1)) {
            return player == 'X' ? 1 : 2;
        }

        // Check for draw
        if (isBoardFull()) return 0;

        return -1; // Game continues
    }

    private boolean checkLine(char player, int startRow, int startCol, int rowStep, int colStep) {
        int count = 0;
        for (int i = 0; i < boardSize; i++) {
            int row = startRow + i * rowStep;
            int col = startCol + i * colStep;
            if (row >= boardSize || col >= boardSize || col < 0 || board[row][col] != player) {
                count = 0;
            } else {
                count++;
            }
            if (count == requiredToWin) return true;
        }
        return false;
    }
}
