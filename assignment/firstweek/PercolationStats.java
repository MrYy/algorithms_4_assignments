package assignment.firstweek;

/**
 * Created by ge on 2016/6/2.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
public class PercolationStats {
    //perform T times independent experiments
    //N-by-N grid
    private int sum = 0;
    private int T;
    private int N;
    private double[] p;
    public PercolationStats(int N, int T) {
        this.T = T;
        this.N = N;
        p = new double[T];
        for (int i=0;i<T;i++) {
            Percolation percolation = new Percolation(N);
            int count=0;
            while (!percolation.percolates()) {
                int randomX = StdRandom.uniform(N) + 1;
                int randomY = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(randomX, randomY)) {
                    percolation.open(randomX,randomY);
                    count++;
                }
            }
            p[i] = (double) count / (N * N);
        }
    }
    public double mean() {
        return (double) StdStats.mean(p);
//        return  1/3;
    }
    public double stddev() {
        return StdStats.stddev(p);
    }
    public double confidenceLo() {
        double u = mean();
        double delta = stddev();
        return (u - 1.96 * delta / Math.sqrt(T));
    }

    public double confidenceHi() {
        double u = mean();
        double delta = stddev();
        return (u + 1.96 * delta / Math.sqrt(T));
    }
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.print(percolationStats.mean());
        System.out.print(percolationStats.stddev());
        System.out.print(percolationStats.confidenceLo());
        System.out.print(percolationStats.confidenceHi());

    }
}
