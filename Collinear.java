/*
 * javac -cp ".;algs4.jar" Collinear.java
 * java -cp ".;algs4.jar" Collinear <name_file>
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class Collinear {
    public static void main (String [] args) {
        In input = new In(args[0]);      // input file
        int sizeArray = input.readInt();        // n-by-n percolation system
        Point [] points = new Point[sizeArray];
        initArray(input, points, sizeArray);

        // points on screen
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.009);
        for (Point p : points) p.draw();
        StdDraw.show();

        // Lines on screen
        StdDraw.setPenRadius(0.0009);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        FastCollinearPoints fcollinear = new FastCollinearPoints(points);
        for (LineSegment segment : fcollinear.segments()) segment.draw();
        StdDraw.show();
    }

    public static void initArray(In input, Point [] points, int sizeArray) {
        for (int i = 0; i < sizeArray; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
        }
    }
}
