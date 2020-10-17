package hw2;
import java.lang.IndexOutOfBoundsException;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] array;
    private WeightedQuickUnionUF mapF;
    private WeightedQuickUnionUF mapP;

    private boolean checkRange(int x, int y) {
        if (x<0 || y<0 || x>n || y>n) {
            throw new IndexOutOfBoundsException();
        } else return true;
    }

    private int locationFinder(int x, int y) {
        return (int) x * n + y;
    }

    public Percolation(int N) {
        if (N<0) throw new java.lang.IllegalArgumentException();
        n = N;
        array = new boolean[n*n];
        for (boolean b : array) {
            b = false;
        }

        mapF = new WeightedQuickUnionUF(n*n + 2);
        mapP = new WeightedQuickUnionUF(n*n + 2);
        for (int i = 0; i < n; i += 1) {
            mapF.union(n*n-2, i); // n*n-2 as the virtual source.
            mapP.union(n*n-2, i);
        }
        for (int i=n*(n-1); i<n*n; i+=1) {
            mapP.union(n*n-1, i);
        }
    }

    private void connectOpens(int i) {
        for (int step : new int[]{-n, -1, 1, n}) {
            if (i + step >= 0 || i + step < n*n) {
                if (this.array[i+step]) { // is open
                    mapF.union(i, i+step);
                    mapP.union(i, i+step);
                }
            }
        }
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        } else {
            int id = locationFinder(row, col);
            array[id] = true;
            connectOpens(id);
        }
    }

    public boolean isOpen(int row, int col) {
        int id = locationFinder(row, col);
        return array[id];
    }

    public boolean isFull(int row, int col) {
        int id = locationFinder(row, col);
        return mapF.connected(id, n*n-2);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : array) {
            if (b) count += 1;
        }
        return count;
    }

    public boolean percolates() {
        return mapP.connected(n*n-2, n*n-1);
    }

    public static void main(String[] args) {}  // use for unit testing (not required)
}
