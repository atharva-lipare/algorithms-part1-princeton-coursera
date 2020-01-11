/* *****************************************************************************
 *  Name: 47#4R\/4
 *  Date: 26/9/19
 *  Description: 8puzzle
 **************************************************************************** */
// https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private int[][] board;  // 2D arr
    private int emptyRow;   //  row index with empty tile
    private int emptyCol;   //  col index with empty tile

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        board = new int[tiles.length][tiles.length];
        getCopy(board, tiles);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    return;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(board.length + "\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                buf.append(" " + board[i][j]);
            }
            buf.append("\n");
        }
        String s = buf.toString();
        return s;
    }

    // board dimension n
    public int dimension() {
        return board.length;
    }

    // number of tiles out of place
    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] != (i * board.length) + j + 1) cnt++;
            }
        }
        return cnt;
    }

    // sum of Manhattan distances between tiles and goal // sum of horizontal and vertical moves to right place
    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) continue; // don't consider empty tile
                if (board[i][j] != (i * board.length) + j + 1) {
                    int manY = ((board[i][j] - 1) / board.length) - i;
                    int manX = ((board[i][j] - 1) % board.length) - j;
                    cnt += Math.abs(manX) + Math.abs(manY);
                }
            }
        }
        return cnt;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == board.length - 1 && j == board.length - 1) return true;
                if (board[i][j] != (i * board.length) + j + 1) return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;    // null obj
        if (y.getClass() != this.getClass()) return false;  // classes should be equal
        Board yBoard = (Board) y;
        if ((board.length != yBoard.board.length)) return false;
        return Arrays.deepEquals(this.board, yBoard.board); // deepEquals can compare arrays deeply // read documentation for exact attributes it compares
    }

    // all neighboring boards // boards obtained after making all possible boards by moving empty tile in possible directions min(2) max(4)
    public Iterable<Board> neighbors() {
        List<Board> neighbours = new LinkedList<>();
        if (emptyRow != 0) {
            int[][] twinBoard = new int[board.length][board.length];
            getCopy(twinBoard, this.board);
            swap(twinBoard, emptyRow, emptyCol, emptyRow - 1, emptyCol);
            neighbours.add(new Board(twinBoard));
        }
        if (emptyRow != (board.length - 1)) {
            int[][] twinBoard = new int[board.length][board.length];
            getCopy(twinBoard, this.board);
            swap(twinBoard, emptyRow, emptyCol, emptyRow + 1, emptyCol);
            neighbours.add(new Board(twinBoard));
        }
        if (emptyCol != 0) {
            int[][] twinBoard = new int[board.length][board.length];
            getCopy(twinBoard, this.board);
            swap(twinBoard, emptyRow, emptyCol, emptyRow, emptyCol - 1);
            neighbours.add(new Board(twinBoard));
        }
        if (emptyCol != (board.length - 1)) {
            int[][] twinBoard = new int[board.length][board.length];
            getCopy(twinBoard, this.board);
            swap(twinBoard, emptyRow, emptyCol, emptyRow, emptyCol + 1);
            neighbours.add(new Board(twinBoard));
        }
        return neighbours;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinBoard = new int[board.length][board.length];
        getCopy(twinBoard, this.board);
        if (emptyRow != 1) {
            swap(twinBoard, 1, 0, 1, 1);    // have exch (1, 0) and (1, 1)
        }
        else {
            swap(twinBoard, 0, 0, 0, 1);    // have exch (0, 0) and (0, 1)
        }
        return new Board(twinBoard);
    }

    // swap two tiles funxn
    private void swap(int[][] newBoard, int row1, int col1, int row2, int col2) {
        newBoard[row1][col1] += newBoard[row2][col2];
        newBoard[row2][col2] = newBoard[row1][col1] - newBoard[row2][col2];
        newBoard[row1][col1] -= newBoard[row2][col2];
    }

    // copy two boards funxn
    private void getCopy(int[][] destBoard, int[][] srcBoard) {
        for (int i = 0; i < srcBoard.length; i++) {
            destBoard[i] = srcBoard[i].clone();
        }
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] exarr = {{1, 2}, {3, 4}};
        Board test = new Board(exarr);
        String ex = test.toString();
        System.out.println(ex);
    }

}
