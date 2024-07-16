import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private WeightedQuickUnion uf;
    private int topVirtualSite;
    private int bottomVirtualSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }
        size = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnion(n * n + 2); // Union-Find with additional virtual top and bottom sites
        topVirtualSite = 0;
        bottomVirtualSite = n * n + 1;

        // Perform union operations for virtual sites
        for (int i = 1; i <= n; i++) {
            uf.union(topVirtualSite, i);
            uf.union(bottomVirtualSite, n * n + 1 - i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("Invalid grid position");
        }
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            int id = getIndex(row, col);
            if (row == 1) {
                uf.union(topVirtualSite, id); // Connect to virtual top site
            }
            if (row == size) {
                uf.union(bottomVirtualSite, id); // Connect to virtual bottom site
            }
            // Connect to adjacent open sites
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(id, getIndex(row - 1, col));
            }
            if (row < size && isOpen(row + 1, col)) {
                uf.union(id, getIndex(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(id, getIndex(row, col - 1));
            }
            if (col < size && isOpen(row, col + 1)) {
                uf.union(id, getIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("Invalid grid position");
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("Invalid grid position");
        }
        return uf.connected(topVirtualSite, getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }

    // Check if the given indices are valid for the grid
    private boolean isValid(int row, int col) {
        return row >= 1 && row <= size && col >= 1 && col <= size;
    }

    // Get the 1D index from 2D grid coordinates
    private int getIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 20;
        Percolation pc = new Percolation(n);

        // Perform random openings until system percolates
        while (!pc.percolates()) {
            int i = StdRandom.uniform(1, n + 1);
            int j = StdRandom.uniform(1, n + 1);
            if (!pc.isOpen(i, j)) {
                pc.open(i, j);
            }
        }

        // Output the number of open sites
        StdOut.print(pc.numberOfOpenSites());
    }
}
