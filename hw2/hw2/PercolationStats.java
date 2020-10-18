package hw2;

import  edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] results;
    private double t;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (true) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                p.open(x, y);
                if (p.percolates()) {
                    double count = p.numberOfOpenSites();
                    double percentage = count / (N * N);
                    results[i] = percentage;
                    break;
                }
            }
        }
        t = T;
    }  // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(results);
    }                                           // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(results);
    }                                         // sample standard deviation of percolation threshold

    public double confidenceLow() {
        double mean = mean();
        double std = stddev();
        return mean - 1.96 * std / Math.sqrt(t);
    }                                  // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        double mean = mean();
        double std = stddev();
        return mean + 1.96 * std / Math.sqrt(t);
    }                                 // high endpoint of 95% confidence interval
    /*
    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(4, 10, pf);
        double t1 = ps.mean();
        double t2 = ps.stddev();
    }
    */
}
