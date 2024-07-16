import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] results;
    private Percolation pc;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            pc = new Percolation(n);

            // Perform random openings until system percolates
            while (!pc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!pc.isOpen(row, col)) {
                    pc.open(row, col);
                }
            }
            results[i] = (double) pc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        double sum = 0;
        for (double num : results) {
            sum += num;
        }
        return sum / results.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double sum = 0;
        for (double num : results) {
            sum += Math.pow(num - mean(), 2);
        }
        return Math.sqrt(sum/(results.length - 1));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - ((1.96 * stddev()) / Math.sqrt(results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(results.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.print(ps.confidenceLo());
        StdOut.print("\t");
        StdOut.print(ps.confidenceHi());
    }
}
