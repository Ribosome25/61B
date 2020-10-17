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
            mapF.union(n*n, i); // n*n as the virtual source.
            mapP.union(n*n, i);
        }
        for (int i=n*(n-1); i<n*n; i+=1) {
            mapP.union(n*n+1, i);
        }
    }

    private void connectOpens(int i) {
        for (int step : new int[]{-n, -1, 1, n}) {
            if (i + step >= 0 && i + step < n*n) {
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
        if (! isOpen(row, col)) {
            return false;
        }
        int id = locationFinder(row, col);
        return mapF.connected(id, n*n);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : array) {
            if (b) count += 1;
        }
        return count;
    }

    public boolean percolates() {
        return mapP.connected(n*n, n*n+1);
    }

    public static void main(String[] args) {
        Percolation p1 = new Percolation(10);
        boolean t1 = p1.isOpen(0,1);
        p1.open(0, 1);
        boolean t2 = p1.isOpen(0, 1);
        System.out.println(t1);
        System.out.println(t2);

        t1 = p1.isFull(0, 2);
        t2 = p1.isFull(0, 1);
        System.out.println(t1);
        System.out.println(t2);

        p1.open(1, 3);
        t1 = p1.isFull(1, 3);
        p1.open(0, 3);
        t2 = p1.isFull(1, 3);
        System.out.println(t1);
        System.out.println(t2);
    }  // use for unit testing (not required)
}
