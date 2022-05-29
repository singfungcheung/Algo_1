public class Percolation {
    private int[][] grid;
    private int dimension;
    private int numOpenSites;

    // creates n-by-n grid, with all sites initially blocked.
    public Percolation(int n) {
        this.grid = new int[n][n];
        this.dimension = n;
        this.numOpenSites = 0;

        // Set the values to 0
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.grid[i][j] = 0;
//                // Print the values out to make sure the grid is actually 0's.
//                System.out.print(this.grid[i][j]);
            }
//            // Print a new line when j reaches n.
//            System.out.println();
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

        this.grid[row-1][col-1] = 1;

        // increment the value of numOpenSites;
        this.numOpenSites++;

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

    // is the site (row, col) full? aka does the site connect to the top of the grid?
    public boolean isFull(int row, int col) {
        // Check to make sure row and col are valid.
        try {
            check(row,col);
        }
        catch(IllegalArgumentException e) {
            System.out.println("Invalid input!");
            return false;
        }

        // Right away we can return false if the input cell is closed.
        if (isOpen(row, col) == false) {
            return false;
        }
        // If it reaches this part then the cell is open.
        // If its row = 1 then return true. System percolates.
        if (row == 1) {
            return true;
        } else {
            // row is not = 1. Time to check if adjacent cells are open.
            // If open, run a recursive function (call isFull) on that cell.
            // Cell ABOVE input cell
            if (isOpen(row - 1, col) == true) {
                return isFull(row - 1, col);
            }
            // Cell to the LEFT input cell
            if (isOpen(row, col - 1) == true) {
                return isFull(row, col - 1);
            }
            // Cell BELOW input cell
            if (isOpen(row + 1, col) == true) {
                return isFull(row + 1, col);
            }
            // Cell to the RIGHT input cell
            if (isOpen(row, col + 1) == true) {
                return isFull(row, col + 1);
            }
        }

        // Iteration hits a dead end (no adjacent cells open or it reached the top but cell is not open).
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Call isFull on the bottom row
        for (int i = 0; i < this.dimension; i++) {
            if (isFull(this.dimension, i)) {
                return true;
            }
        }
        // None found to percolate. So return false.
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
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Please enter a value greater than 0!");
        }
    }

    // test client (optional)
//    public static void main(String[] args) {}
}
