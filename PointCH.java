/******************************************************************************
 *  Compilation:  javac PointCH.java
 *  Execution:    java PointCH
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class PointCH implements Comparable<PointCH> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    private final Comparator<PointCH> sortByPolar = new SortByPolar();
    public static final Comparator<PointCH> sortByCoordenateY = new SortByCoordenateY();

    private class SortByPolar implements Comparator<PointCH> {
        public int compare(PointCH q, PointCH r) {
            double dx1 = q.x - x;
            double dy1 = q.y - y;
            double dx2 = r.x - x;
            double dy2 = r.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q above; r below
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q below; r above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(q, r);     // both above or below
        }
    }

    private static class SortByCoordenateY implements Comparator<PointCH> {
        public int compare(PointCH p, PointCH q) {
            if (p.y < q.y) return -1;
            else if (p.y > q.y) return 1;
            else return 0;
        }
    }

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public PointCH(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(PointCH that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int ccw(PointCH q, PointCH r) {
        double area = (q.x-this.x)*(r.y-this.y) - (q.y-this.y)*(r.x-this.x);
        if      (area < 0) return -1;
        else if (area > 0) return +1;
        else               return  0;
    }

    public int compareTo(PointCH that) {
        /* YOUR CODE HERE */
        if (this.x == that.x && this.y == that.y) return 0;
        else if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else return 1;
    }

    public Comparator<PointCH> polarOrder() {
        /* YOUR CODE HERE */
        return this.sortByPolar;
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}
