import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int [][] grid;
    int dimension;
    int numOpenSites;
    WeightedQuickUnionUF sample;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.grid = new int[n][n];
        this.dimension = n;
        this.numOpenSites = 0;
        this.sample = new WeightedQuickUnionUF(n*n);

        // Set all the cells in the grid to zero.
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.grid[i][j] = 0;
            }
        }

        // Connect all the top in this.sample for virtual top site.
        for (int i = 1; i < this.dimension; i++) {
            this.sample.union(0,i);
        }

        // Print the grid to make sure it looks correct.
        show();

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // Check to make sure row and col are valid.
        try {
            check(row,col);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Invalid input!");
        }

        if (isOpen(row, col) == false) {
            this.grid[row - 1][col - 1] = 1;

            // increment the value of numOpenSites;
            this.numOpenSites++;

            // see if there are adjacent cells and connect them (union)
            // Check the top of the input cell
            if (isOpen(row - 1, col)) {
                this.sample.union(this.dimension*(row - 1) + (col - 1), this.dimension*((row - 1) - 1) + (col - 1));
            }

            // Check the left of the input cell
            if (isOpen(row, col - 1)) {
                this.sample.union(this.dimension*(row - 1) + (col - 1), this.dimension*(row - 1) + ((col - 1) - 1));
            }

            // Check the bottom of the input cell
            if (isOpen(row + 1, col)) {
                this.sample.union(this.dimension*(row - 1) + (col - 1), this.dimension*row + (col - 1));
            }

            // Check the right of the input cell
            if (isOpen(row, col + 1)) {
                this.sample.union(this.dimension * (row - 1) + (col - 1), this.dimension * (row - 1) + col);
            }
        }

        // Print the grid to make sure it looks correct.
        show();
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // Check to make sure row and col are valid.
        try {
            check(row,col);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Invalid input!");
            return false;
        }

        if (this.grid[row-1][col-1] == 1) {
            System.out.println("true");
            return true;
        }
        System.out.println("false");
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // Check to make sure row and col are valid.
        try {
            check(row,col);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Invalid input!");
            return false;
        }

        // Just check if the sample cell connects with virtual top.
        return this.sample.find(this.dimension*(row - 1) + (col - 1)) == this.sample.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Call isFull on the bottom row
        for (int i = 1; i <= this.dimension; i++) {
            if (isFull(this.dimension, i)) {
                System.out.println("Percolates!");
                return true;
            }
        }
        // None found to percolate. So return false.
        System.out.println("Does not percolate...");
        return false;
    }

    // Print the grid
    public void show() {
        for (int i = 0; i < this.dimension; i ++) {
            for (int j = 0; j < this.dimension; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    // Check to make sure row and col values are valid
    public void check(int row, int col) {
        if (row <= 0 || row > this.dimension || col <= 0 || col > this.dimension) {
            throw new IllegalArgumentException("Please enter a value greater than 0!");
        }
    }
}