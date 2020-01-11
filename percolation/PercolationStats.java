import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* *****************************************************************************
 *  Name: Atharva Lipare
 *  Date:
 *  Description:
 **************************************************************************** */

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int numTrials;
    private final double[] fracArr;
    private final double meanVal, stdVal, confHiVal, confLoVal;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Exception");
        }

        numTrials = trials;
        fracArr = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                grid.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            fracArr[i] = (double) grid.numberOfOpenSites() / (n * n);
        }
        meanVal = StdStats.mean(fracArr);
        stdVal = StdStats.stddev(fracArr);
        confLoVal = (meanVal - CONFIDENCE_95 * stdVal / Math.sqrt(numTrials));
        confHiVal = (meanVal + CONFIDENCE_95 * stdVal / Math.sqrt(numTrials));
    }


    // sample mean of percolation threshold
    public double mean() {
        return meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stdVal;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confLoVal;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confHiVal;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(Integer.parseInt(args[0]),
                                                     Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println(
                "95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi()
                        + "]");
    }
}
