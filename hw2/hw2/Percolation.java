package hw2;
import java.lang.IndexOutOfBoundsException;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] array;
    private WeightedQuickUnionUF mapF;
    private WeightedQuickUnionUF mapP;

    private boolean checkRange(int x, int y) {
        if (x < 0 || y < 0 || x > n || y > n) {
            throw new IndexOutOfBoundsException();
        } else {
            return true;
        }
    }

    private int locationFinder(int x, int y) {
        return (int) x * n + y;
    }

    public Percolation(int N) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        n = N;
        int nn = n * n;
        array = new boolean[nn];
        for (boolean b : array) {
            b = false;
        }

        mapF = new WeightedQuickUnionUF(nn + 2);
        mapP = new WeightedQuickUnionUF(nn + 2);
        for (int i = 0; i < n; i += 1) {
            mapF.union(nn, i); // nn as the virtual source.
            mapP.union(nn, i);
        }
        for (int i = n * (n - 1); i < nn; i += 1) {
            mapP.union(nn + 1, i);
        }
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        } else {
            int id = locationFinder(row, col);
            array[id] = true;

            for (int[] xx : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                int col2 = col + xx[1];
                int row2 = row + xx[0];
                if (col2 >= 0 && col2 < n && row2 >= 0 && row2 < n && isOpen(row2, col2)) {
                    int id2 = locationFinder(row2, col2);
                    mapF.union(id, id2);
                    mapP.union(id, id2);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int id = locationFinder(row, col);
        return array[id];
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int id = locationFinder(row, col);
        return mapF.connected(id, n * n);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : array) {
            if (b) {
                count += 1;
            }
        }
        return count;
    }

    public boolean percolates() {
        if (this.numberOfOpenSites() == 0) {
            return false;
        }
        return mapP.connected(n * n, n * n + 1);
    }

    public static void main(String[] args) {
        Percolation p1 = new Percolation(10);
        boolean t1 = p1.isOpen(0, 1);
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
