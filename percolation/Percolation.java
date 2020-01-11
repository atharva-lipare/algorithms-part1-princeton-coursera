import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name: Atharva Lipare
 *  Date:
 *  Description: Week 1 assignment
 **************************************************************************** */
public class Percolation {
    private boolean[][] grid;
    private final int size;
    private int openSites = 0;
    private WeightedQuickUnionUF uf, uf1;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Exception");
        grid = new boolean[n][n];
        size = n;
        uf = new WeightedQuickUnionUF((n * n) + 2);
        uf1 = new WeightedQuickUnionUF((n * n) + 1);    // two UF created becoz isFull returns wrong answer for certain cases.
    }                                                   // uf1 used specifically for isFull() to remove the problem, has only upper parent node
                                                        // as opposed to uf havind parent node for uppermost and lowermost row.
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("Exception");
        if (size == 1) {
            if (!grid[row - 1][col - 1]) {
                grid[row - 1][col - 1] = true;
                openSites++;
                uf.union(0, 1);
                uf.union(0, 2);
                uf1.union(0, 1);
            }
            return;
        }
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            openSites++;
        }
        if (row == 1) {
            uf.union(col - 1, size * size);
            uf1.union(col - 1, size * size);
        }
        if (row == size) {
            uf.union(size * (row - 1) + col - 1, size * size + 1);
        }
        if (row > 1) {
            if (/* isOpen(row - 1, col)*/ grid[row - 2][col - 1]) {
                uf.union(size * (row - 1) + col - 1, size * (row - 2) + col - 1);
                uf1.union(size * (row - 1) + col - 1, size * (row - 2) + col - 1);
            }
        }
        if (row < size) {
            if (/* isOpen(row + 1, col)*/ grid[row][col - 1]) {
                uf.union(size * (row - 1) + col - 1, size * row + col - 1);
                uf1.union(size * (row - 1) + col - 1, size * row + col - 1);
            }
        }
        if (col > 1) {
            if (/* isOpen(row, col - 1)*/ grid[row - 1][col - 2]) {
                uf.union(size * (row - 1) + col - 1, size * (row - 1) + col - 2);
                uf1.union(size * (row - 1) + col - 1, size * (row - 1) + col - 2);
            }
        }
        if (col < size) {
            if (/* isOpen(row, col + 1)*/ grid[row - 1][col]) {
                uf.union(size * (row - 1) + col - 1, size * (row - 1) + col);
                uf1.union(size * (row - 1) + col - 1, size * (row - 1) + col);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("Exception");
        if (grid[row - 1][col - 1]) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException("Exception");
        return uf1.connected(size * size, size * (row - 1) + col - 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(size * size, size * size + 1);
    }

    // test client (optional)
    // public static void main(String[] args)
}
