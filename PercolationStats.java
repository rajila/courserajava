/**
    Compilar (W10):
        javac -cp "algs4.jar" Percolation.java PercolationStats.java
    Ejecutar (W10):
        java -cp ".;algs4.jar" PercolationStats 20 10

    Link of reference:
    https://algs4.cs.princeton.edu/code/
*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private static final double CONFIDENCE_LEVEL = 1.96;
    private final int t;
    private final double [] threshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("N & T can not equal or less than 0");

        this.t = trials;
        threshold = new double[trials];
        int row = 0;
        int col = 0;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                do {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                } while (percolation.isOpen(row, col));
                percolation.open(row, col);
                // System.out.println(_percolation);
            }
            // System.out.println("\n\n");
            threshold[i] = (percolation.numberOfOpenSites() * 1.0) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold) * 1.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold)  * 1.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((PercolationStats.CONFIDENCE_LEVEL * stddev() * 1.0) / Math.sqrt(this.t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((PercolationStats.CONFIDENCE_LEVEL * stddev() * 1.0) / Math.sqrt(this.t));
    }

   // test client (see below)
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int t = Integer.parseInt(args[1]);

       PercolationStats percolationS = new PercolationStats(n, t);

       // for(int _i=0; _i < _t; _i++) System.out.println(_percolationS._threshold[_i]);

       StdOut.printf("mean\t\t\t\t = %.6f\n", percolationS.mean());
       StdOut.printf("stddev\t\t\t\t = %.17f\n", percolationS.stddev());
       StdOut.printf("95%s confidence interval\t\t = [%.16f, %.16f]\n", "%", percolationS.confidenceLo(), percolationS.confidenceHi());
   }
}
