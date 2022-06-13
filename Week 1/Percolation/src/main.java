import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


public class main {
    public static void main(String[] args) {
//        Percolation x = new Percolation(5);
//        //x.open(0,3);
//        //System.out.println("does this run");
//        x.open(3,5);
//        x.open(2,2);
//        x.isOpen(1,3);
//        x.isOpen(1,1);
//        x.isOpen(0,1);
//        System.out.println(x.numberOfOpenSites());
//        WeightedQuickUnionUF x = new WeightedQuickUnionUF(9);
//        System.out.println(x.find(3));
//        x.union(3,2);
//        System.out.println(x.find(3));
//        x.union(0,2);
//        x.union(3,1);
//        System.out.println(x.find(3));
//        System.out.println(x.count());
//
//        System.out.println(x.find(0));
//        System.out.println(x.find(1));
//        System.out.println(x.find(2));
//        System.out.println(x.find(3));

//        Percolation x = new Percolation(5);
//        x.open(3,2);
//        x.isOpen(3,2);
//        System.out.println(x.sample.find(x.dimension*(2) + 1));
//        x.open(2,3);
//        x.open(2,2);
//        System.out.println(x.sample.find(x.dimension*(2) + 1));
//        System.out.println(x.sample.find(x.dimension*(1) + 1));
//        System.out.println(x.sample.find(x.dimension*(1) + 2));
//        x.percolates();
//        x.open(1,2);
//        x.percolates();
//        x.open(5,2);
//        x.percolates();
//        x.open(4,1);
//        x.open(4,3);
//        x.open(4,4);
//        x.open(4,5);
//        x.percolates();
//        x.open(4,2);
//        x.percolates();
        Stopwatch x = new Stopwatch();
        PercolationStats sample = new PercolationStats(200, 100);
        System.out.println("mean                    = " + sample.mean());
        System.out.println("stddev                  = " + sample.stddev());
        System.out.println("95% confidence interval = [" + sample.confidenceLo() + ", " + sample.confidenceHi() + "]");
        System.out.println(x.elapsedTime());

    }
}
