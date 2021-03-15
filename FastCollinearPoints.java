/**
    Compilar (W10):
        javac -cp "algs4.jar" Point.java LineSegment.java FastCollinearPoints.java
    Ejecutar (W10):
        java -cp ".;algs4.jar" Point <name_file>

    Link of reference:
    https://algs4.cs.princeton.edu/code/
*/

import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author RONALD
 */
public class FastCollinearPoints {
    private Point [] pointsData;
    private final ArrayList<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) throw new IllegalArgumentException("Input Illegal");
        this.pointsData = new Point[points.length];
        if (!fullPoints(points)) throw new IllegalArgumentException("Input Illegal");
        segments = new ArrayList<>();
        findAllSegment(this.pointsData);
    }

    private boolean fullPoints(Point[] points) {
        this.pointsData = points.clone();
        for (int i = 0; i < this.pointsData.length; i++) if (points[i] == null) return false;
        Arrays.sort(this.pointsData); // Sorter by coordena for validation de pointers repetidos
        for (int i = 0; i < this.pointsData.length-1; i++) if (this.pointsData[i].compareTo(this.pointsData[i+1]) == 0) return false;
        return true;
    }

    private void findAllSegment(Point[] points) {
        Point pointBase;
        Point pointFirst;
        for (int i = 0; i < points.length - 3; i++) { // Hasta N-1 puntos
            Arrays.sort(points);
            pointBase = points[i];
            Arrays.sort(points, pointBase.slopeOrder());
            int count = 1, j;
            pointFirst = points[1];
            double slopeCurrent = pointBase.slopeTo(pointFirst);
            double slopeNext = 0.0;
            for (j = 2; j < points.length; j++) {
                slopeNext = pointBase.slopeTo(points[j]);
                if (slopeCurrent == slopeNext) count++;
                else {
                    if (count > 2 && pointBase.compareTo(pointFirst) < 0) segments.add(new LineSegment(pointBase, points[j-1]));
                    slopeCurrent = slopeNext;
                    pointFirst = points[j];
                    count = 1;
                }
            }
            if (count > 2 && pointBase.compareTo(pointFirst) < 0) segments.add(new LineSegment(pointBase, points[j-1]));
            if (count == points.length - 1) break;
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}
