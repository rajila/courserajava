/**
    Compilar (W10):
        javac -cp "algs4.jar" Percolation.java
    Ejecutar (W10):
        java -cp ".;algs4.jar" Percolation input20.txt

    Link of reference:
    https://algs4.cs.princeton.edu/code/
*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF gridFull;

    private final int n;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private final boolean [][] gridState;
    private int countOpenSites; 

    private int rowCurrent;
    private int colCurrent;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N can not equal or less than 0");
        this.rowCurrent = -1;
        this.colCurrent = -1;
        this.countOpenSites = 0;
        this.n = n;
        this.virtualTopSite = 0;
        this.virtualBottomSite = n * n + 1;
        this.grid = new WeightedQuickUnionUF(n * n + 2);
        this.gridFull = new WeightedQuickUnionUF(n * n + 2);

        // sites initially blocked
        this.gridState = new boolean[n][n]; // default to FALSE
        // for(int _i = 1; _i <= n; _i++ ) this._grid.union(_i, this._virtualTopSite);
        // for(int _i = n * n - n + 1; _i <= n * n; _i++ ) this._grid.union(_i, this._virtualBottomSite);
    }

    private int getIndexGrid(int row, int col) {
        return this.n * (row - 1) + col;
    }

    private boolean isValidCoordinates(int row, int col) {
        return row > 0 && row <= this.n && col > 0 && col <= this.n;
    }

    private void doUnion(int row, int col, int indexCurrent, WeightedQuickUnionUF gridT) {
        if (isValidCoordinates(row, col) && isOpen(row, col))
            gridT.union(indexCurrent, getIndexGrid(row, col));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        if (!isValidCoordinates(row, col))
            throw new IllegalArgumentException("(row, col) index (i,j) out of bounds");

        int indexCurrent = getIndexGrid(row, col);
        this.gridState[row-1][col-1] = true; // Opened
        this.countOpenSites++;
        this.rowCurrent = row;
        this.colCurrent = col;

        if (row == 1) {
            this.grid.union(this.virtualTopSite, indexCurrent);
            this.gridFull.union(this.virtualTopSite, indexCurrent);
        }
        if (row == this.n) this.grid.union(this.virtualBottomSite, indexCurrent);

        // Union Current & Top
        doUnion(row - 1, col, indexCurrent, this.grid);
        doUnion(row - 1, col, indexCurrent, this.gridFull);
        // Union Current & Bottom
        doUnion(row + 1, col, indexCurrent, this.grid);
        doUnion(row + 1, col, indexCurrent, this.gridFull);
        // Union Current & Left
        doUnion(row, col - 1, indexCurrent, this.grid);
        doUnion(row, col - 1, indexCurrent, this.gridFull);
        // Union Current & Right
        doUnion(row, col + 1, indexCurrent, this.grid);
        doUnion(row, col + 1, indexCurrent, this.gridFull);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValidCoordinates(row, col))
            throw new IllegalArgumentException("(row, col) index (i,j) out of bounds");
        return this.gridState[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValidCoordinates(row, col))
            throw new IllegalArgumentException("(row, col) index (i,j) out of bounds");
        return this.gridFull.find(this.virtualTopSite) == this.gridFull.find(getIndexGrid(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.countOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.grid.find(this.virtualTopSite) == this.grid.find(this.virtualBottomSite);
    }

    public String toString() {
        return "#OpenSites: " + countOpenSites + " , (r, c) -> (" + rowCurrent + ", " + colCurrent + "), isPercolate: " + percolates();
    }

    // test client (optional)
    public static void main(String[] args) {
        // In in = new In(args[0]);      // input file
        // int n = in.readInt();         // n-by-n percolation system
        //
        // // repeatedly read in sites to open and draw resulting system
        // Percolation perc = new Percolation(n);
        // while (!in.isEmpty()) {
        //     int i = in.readInt();
        //     int j = in.readInt();
        //     perc.open(i, j);
        //     System.out.println(perc);
        // }
    }
}
