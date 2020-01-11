/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
// https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int moves;  // var to store moves required to reach goal
    private boolean isSolvable;
    private SearchNode solutionNode;    // see SearchNode class below. // Minimum Priority Queue of SearchNode is made

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("null input");

        isSolvable = false; // init var
        MinPQ<SearchNode> mpq = new MinPQ<>();  // MinPQ starting with initial board
        MinPQ<SearchNode> mpqTwin = new MinPQ<>();  // MinPQ starting with twin of initial board. made to identify unsolvable board. becoz unsolvable board becomes solvable if any two tiles interchanged.
        SearchNode current = new SearchNode(initial, null);
        SearchNode currentTwin = new SearchNode(initial.twin(), null);
        mpq.insert(current);
        mpqTwin.insert(currentTwin);

        while (true) {
            // board with least priority that is lowest Manhattan priority function value deleted from MinPQ
            current = mpq.delMin();
            currentTwin = mpqTwin.delMin();

            // board is solvable and solution found
            if (current.board.isGoal()) {
                solutionNode = current;
                isSolvable = true;
                moves = current.moves;
                break;
            }
            // twin board reached goal, thus is unsolvable
            if (currentTwin.board.isGoal()) {
                isSolvable = false;
                break;
            }
            // insertion of neighbouring boards of deleted node in MinPQ
            for (Board x : current.board.neighbors()) {
                if ((current.prev == null) || !(x.equals(current.prev.board)))
                    mpq.insert(new SearchNode(x, current)); // can check if inserted board is goal board in this problem but technically won't be A* algo
            }

            // if goal found, exit before adding to twin MinPQ
            if (mpq.min().board.isGoal()) {
                solutionNode = mpq.min();
                isSolvable = true;
                moves = mpq.min().moves;
                break;
            }

            // insertion of neighbouring boards of deleted node in twinMinPQ
            for (Board x : currentTwin.board.neighbors()) {
                if ((currentTwin.prev == null) || !(x.equals(currentTwin.prev.board)))
                    mpqTwin.insert(new SearchNode(x, currentTwin));
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (isSolvable) return moves;
        else return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!isSolvable) return null;
        Stack<Board> solutionStack = new Stack<>(); // stack used to show sequence of boards
        SearchNode node = solutionNode;
        while (node != null) {
            solutionStack.push(node.board);
            node = node.prev;
        }
        return solutionStack;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    // Search node class which consists of board, moves taken to reach, prev board
    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;  // moves taken to reach from initial board
        private SearchNode prev;    // link to prev SearchNode
        private int manhattanPriority;  // manhattanPriority = manhattan + moves taken to reach from initial board
        private int manhattan;

        public SearchNode(Board board, SearchNode prev) {
            this.board = board;
            if (prev == null) moves = 0;
            else moves = prev.moves + 1;
            this.prev = prev;
            this.manhattan = this.board.manhattan();
            manhattanPriority = moves + manhattan;
        }

        @Override
        public int compareTo(SearchNode other) {
            return ((this.manhattanPriority - other.manhattanPriority) == 0) ? (this.manhattan - other.manhattan) : (this.manhattanPriority - other.manhattanPriority); // if manhattanPriorities equal tieBreaker is manhattan
        }
    }

}
