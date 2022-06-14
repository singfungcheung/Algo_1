import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private double [] data;
    private final int dimensions;
    private final int trials;
    private Percolation sampleData;
    private double mean;
    private boolean didMeanRun;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // Check to make sure inputs are correct (greater than 0)
        check(n, trials);

        // Instantiate the variables
        this.data = new double[trials];
        this.dimensions = n;
        this.trials = trials;
        this.mean = 0;
        this.didMeanRun = false;

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
                col = randomInt % this.dimensions + 1;
//                System.out.println("col is " + col);

                // check to see if the site is already open.
                // If it is not, increment fractionOpenSites.
                if (!this.sampleData.isOpen(row, col)) {
                    fractionOpenSites++;
                }
                // open the site at row, col and increment fractionOpenSites.
                // Tests require every iteration to open the site.
                this.sampleData.open(row, col);

            }

            // Once the code gets here. System has percolated.
            // Calculate the fractionOpenSites (divide by this.dimension*this.dimension) and append into data array.
            fractionOpenSites /= (this.dimensions * this.dimensions);
            this.data[i] = fractionOpenSites;
//            System.out.println("fraction is " + fractionOpenSites);
        }

        this.mean = StdStats.mean(this.data);
        // calculate the mean value of data and set didMeanRun = true.
        didMeanRun = true;
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        // Check to see if mean() function ran. If not, run it!
        if (!didMeanRun) {
            this.mean();
        }

        return StdStats.stddev(this.data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        // Check to see if mean() function ran. If not, run it!
        if (!didMeanRun) {
            this.mean();
        }

        return this.mean - (CONFIDENCE_95*this.stddev())/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        // Check to see if mean() function ran. If not, run it!
        if (!didMeanRun) {
            this.mean();
        }
        return this.mean + (CONFIDENCE_95*this.stddev())/Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats sample = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + sample.mean());
        System.out.println("stddev                  = " + sample.stddev());
        System.out.println("95% confidence interval = [" + sample.confidenceLo() + ", " + sample.confidenceHi() + "]");
    }

    // Check to make sure the n and trial values are valid
    private void check(int n, int numTrials) {
        if (n <= 0 || numTrials <= 0) {
            throw new IllegalArgumentException("Please enter a value greater than 0!");
        }
    }

}