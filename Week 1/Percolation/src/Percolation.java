import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] grid;
    private final int dimension;
    private int numOpenSites;
    private final WeightedQuickUnionUF sample;
    private boolean found;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        // check the input n to see if it is less than or equal to 0.
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.grid = new boolean[n][n];
        this.dimension = n;
        this.numOpenSites = 0;
        this.sample = new WeightedQuickUnionUF(n*n);
        this.found = false;

        // Set all the cells in the grid to zero.
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.grid[i][j] = false;
            }
        }

        // Connect all the top in this.sample for virtual top site.
        // Connect all the bottom in this.sample for virtual bottom site.
        for (int i = 1; i < this.dimension; i++) {
            this.sample.union(0, i);
            this.sample.union(this.dimension*(this.dimension - 1), this.dimension*(this.dimension - 1) + i);
        }



        // Print the grid to make sure it looks correct.
        // show();

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        // Check to make sure row and col are valid.
        check(row, col);

        if (!isOpen(row, col)) {
            this.grid[row - 1][col - 1] = true;

            // increment the value of numOpenSites;
            this.numOpenSites++;

            // see if there are adjacent cells and connect them (union)
            // Check the top of the input cell
            if (row - 1 > 0) {
                if (isOpen(row - 1, col)) {
                    this.sample.union(this.dimension*(row - 1) + (col - 1), this.dimension*((row - 1) - 1) + (col - 1));
                }
            }

            // Check the left of the input cell
            if (col - 1 > 0) {
                if (isOpen(row, col - 1)) {
                    this.sample.union(this.dimension*(row - 1) + (col - 1), this.dimension*(row - 1) + ((col - 1) - 1));
                }
            }

            // Check the bottom of the input cell
            if (row + 1 <= this.dimension) {
                if (isOpen(row + 1, col)) {
                    this.sample.union(this.dimension*(row - 1) + (col - 1), this.dimension*row + (col - 1));
                }
            }

            // Check the right of the input cell
            if (col + 1 <= this.dimension) {
                if (isOpen(row, col + 1)) {
                    this.sample.union(this.dimension * (row - 1) + (col - 1), this.dimension * (row - 1) + col);
                }
            }

        }

        // Print the grid to make sure it looks correct.
        // show();
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        // Check to make sure row and col are valid.
        check(row, col);

        return this.grid[row-1][col-1];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        // Check to make sure row and col are valid.
        check(row, col);

        // Just check if the sample cell connects with virtual top.
        // Make sure the site is already opened.
        if (isOpen(row, col)) {
            return (this.sample.find(this.dimension*(row - 1) + (col - 1)) == this.sample.find(0));
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

//        // Call isFull on the bottom row
//        for (int i = 1; i <= this.dimension; i++) {
//            if (isFull(this.dimension, i)) {
////                System.out.println("Percolates!");
//                found = true;
//                break;
//            }
//        }
//        // None found to percolate. So return false.
////        System.out.println("Does not percolate...");
//        return found;
        return this.sample.find(0) == this.sample.find(this.dimension*this.dimension - 1);
    }

    // Print the grid
//    private void show() {
//        for (int i = 0; i < this.dimension; i++) {
//            for (int j = 0; j < this.dimension; j++) {
//                System.out.print(this.grid[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//    }

    // Check to make sure row and col values are valid
    private void check(int row, int col) {
        if (row <= 0 || row > this.dimension || col <= 0 || col > this.dimension) {
            throw new IllegalArgumentException("Please enter a value greater than 0!");
        }
    }
}