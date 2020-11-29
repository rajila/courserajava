import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private Point [] pointsData;
    private final int numberElements;
    private Double [] skipeSlope;
    private Point [] skipePoint;
    private final ArrayList<LineSegment> segments;
    private int dimSegment;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) throw new IllegalArgumentException("Input Illegal");
        this.numberElements = points.length;
        this.pointsData = new Point[this.numberElements];
        if (!fullPoints(points)) throw new IllegalArgumentException("Input Illegal");
        skipeSlope = new Double[1];
        skipePoint = new Point[1];
        segments = new ArrayList<>();
        dimSegment = 0;
        if (!findAllSegment(this.pointsData)) throw new IllegalArgumentException("Input Illegal");
    }

    private boolean fullPoints(Point[] points) {
        this.pointsData = points.clone();
        for (int i = 0; i < this.numberElements; i++) if (points[i] == null) return false;
        return true;
    }

    private boolean findAllSegment(Point[] points) {
        Point [] aux = points.clone();
        Arrays.sort(aux, 0, this.numberElements);
        Point pointSort;
        for (int i = 0; i < this.numberElements-1; i++) { // Hasta N-1 puntos
            points = aux.clone();
            if (points[i].compareTo(points[i+1]) == 0) return false; // Valida que ningun punto este repetido
            if (this.numberElements-i <= 3) continue; // No seguir buscando adyacentes
            pointSort = points[i];
            Arrays.sort(points, i, this.numberElements, pointSort.slopeOrder());
            int count = 1, j;
            double slopeCurrent = pointSort.slopeTo(points[i+1]);
            double slopeNext = 0.0;
            for (j = i + 2; j < this.numberElements; j++) {
                slopeNext = pointSort.slopeTo(points[j]);
                if (slopeCurrent == slopeNext) count++;
                else {
                    if (count > 2) addSegment(pointSort, points[j-1], slopeCurrent);
                    slopeCurrent = slopeNext;
                    count = 1;
                }
            }
            if (count > 2) addSegment(pointSort, points[j-1], slopeCurrent);
            if (count == this.numberElements - i - 1) break;
        }
        return true;
    }

    private void addSegment(Point pointStart, Point pointEnd, double slopeSegment) {
        for (int i = 0; i < dimSegment; i++) {
            if (slopeSegment == skipeSlope[i] && pointEnd.compareTo(skipePoint[i]) == 0) return;
        }
        segments.add(new LineSegment(pointStart, pointEnd));
        if (dimSegment == skipeSlope.length) resizeSkipeSlope(dimSegment + 1);
        skipeSlope[dimSegment++] = slopeSegment;
        dimSegment--;
        if (dimSegment == skipePoint.length) resizeSkipePoint(dimSegment + 1);
        skipePoint[dimSegment++] = pointEnd;
    }

    private void resizeSkipeSlope(int capacity)
    {
        Double[] copy = new Double[capacity];
        for (int i = 0; i < dimSegment; i++) copy[i] = skipeSlope[i];
        skipeSlope = copy;
    }

    private void resizeSkipePoint(int capacity)
    {
        Point[] copy = new Point[capacity];
        for (int i = 0; i < dimSegment; i++) copy[i] = skipePoint[i];
        skipePoint = copy;
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
