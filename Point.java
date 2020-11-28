/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    private final Comparator<Point> sortForOrder = new SortBySlope();

    private class SortBySlope implements Comparator<Point> {
        public int compare(Point start, Point end) {
            double pointA = slopeTo(start);
            double pointB = slopeTo(end);
            if (pointA < pointB) return -1;
            else if (pointA > pointB) return 1;
            else return 0;
        }
    }

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
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
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x) return Double.POSITIVE_INFINITY;
        else if (this.y == that.y) return 0.0;
        return (that.y - this.y)*1.0/(that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.x == that.x && this.y == that.y) return 0;
        else if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else return 1;
    }


    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return this.sortForOrder;
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

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        // Point [] points = new Point[5];
        // points[0] = new Point(3, 6);
        // points[1] = new Point(1, 10);
        // points[2] = new Point(6, 2);
        // points[3] = new Point(2, 8);
        // points[4] = new Point(10, 5);
        // // for(Point element : points) StdOut.println(element);
        // // Arrays.sort(points);
        // // StdOut.println("\n");
        // // for(Point element : points) StdOut.println(element);
        // // Arrays.sort(points, points[0].slopeOrder());
        // // StdOut.println("\n");
        // // for(Point element : points) StdOut.println(element);
        // LineSegment lineSegmento = new LineSegment(points[0], points[1]);
        // points[0].draw();
        // points[1].draw();
        // lineSegmento.draw();
        In in = new In(args[0]);      // input file
        int n = in.readInt();        // n-by-n percolation system
        Point [] points = new Point[n];
        // StdOut.println(n);
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // points[n] = null;
        // repeatedly read in sites to open and draw resulting system
        // draw the points
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) p.draw();
        StdDraw.show();

        // print and draw the line segments
        // BruteCollinearPoints bcollinear = new BruteCollinearPoints(points);
        // System.out.println("\n");
        // StdOut.println(bcollinear.numberOfSegments());
        // for (LineSegment segment : bcollinear.segments()) {
        //     StdOut.println(segment);
        //     // segment.draw();
        // }

        FastCollinearPoints fcollinear = new FastCollinearPoints(points);
        StdOut.println(fcollinear.numberOfSegments());
        for (LineSegment segment : fcollinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
