import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    double [] data;
    int dimensions;
    int trials;
    Percolation sampleData;
    double mean;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // Check to make sure inputs are correct (greater than 0)
        check(n, trials);

        // Instantiate the variables
        this.data = new double[trials];
        this.dimensions = n;
        this.trials = trials;
        this.mean = 0;

        // Instantiate a Percolation object
        this.sampleData = new Percolation(n);
    }

    // sample mean of percolation threshold
    public double mean() {
        // For loop number of trials
        double fractionOpenSites = 0;
        int row = 0;
        int col = 0;
        int randomInt = 0;

        for (int i = 0; i < this.trials; i++) {
            // while loop (while the grid doesn't percolate. Keep looping until it percolates)
            fractionOpenSites = 0;
            // reset the percolation object for the next trial.
            this.sampleData = new Percolation(this.dimensions);
            while (!this.sampleData.percolates()) {
                // generate a random integer between [1,n*n]
                randomInt = StdRandom.uniform(0, this.dimensions * this.dimensions);
//                System.out.println("random int is " + randomInt);
                // get the row and col values
                row = randomInt/this.dimensions + 1;
//                System.out.println("row is " + row);
                col = randomInt%this.dimensions + 1;
//                System.out.println("col is " + col);

                // check to see if the site is already open.
                // If it is, continue the while loop (so fractionOpenSites don't get incremented).
                if (this.sampleData.isOpen(row, col)) {
                    continue;
                } else {
                    // open the site at row, col and increment fractionOpenSites.
                    this.sampleData.open(row, col);
                    fractionOpenSites++;
                }

            }

            // Once the code gets here. System has percolated.
            // Calculate the fractionOpenSites (divide by this.dimension*this.dimension) and append into data array.
            fractionOpenSites /= (this.dimensions * this.dimensions);
            this.data[i] = fractionOpenSites;
//            System.out.println("fraction is " + fractionOpenSites);
        }

        // calculate the mean value of data.
        return this.mean = StdStats.mean(this.data);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean - (1.96*this.stddev())/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean + (1.96*this.stddev())/Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats sample = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + sample.mean());
        System.out.println("stddev                  = " + sample.stddev());
        System.out.println("95% confidence interval = [" + sample.confidenceLo() + ", " + sample.confidenceHi() + "]");
    }

    // Check to make sure the n and trial values are valid
    public void check(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Please enter a value greater than 0!");
        }
    }

}