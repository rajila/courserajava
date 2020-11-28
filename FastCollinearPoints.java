import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private final Point [] pointsData;
    private final int numberElements;
    private Double [] skipeSlope;
    private Point [] skipePoint;
    private LineSegment [] segmentsOk;
    private int dimSegment;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) throw new IllegalArgumentException("Input Illegal");
        this.numberElements = points.length;
        this.pointsData = new Point[this.numberElements];
        if (!fullPoints(points)) throw new IllegalArgumentException("Input Illegal");
        skipeSlope = new Double[1];
        skipePoint = new Point[1];
        segmentsOk = new LineSegment[1];
        dimSegment = 0;
        findAllSegment(this.pointsData);
    }

    private boolean fullPoints(Point[] points) {
        for (int i = 0; i < this.numberElements; i++) {
            if (points[i] == null) return false;
            for (int j = i + 1; j < this.numberElements; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) return false;
            }
            this.pointsData[i] = points[i];
        }
        return true;
    }

    private void findAllSegment(Point[] points) {
        Point pointSort;
        Arrays.sort(points);
        Point [] auxPoint;
        int indexAux;
        for (int i = 0; i < this.numberElements; i++) {
            if (this.numberElements-i <= 3) break;
            auxPoint = new Point[this.numberElements-i];
            indexAux = i;
            for (int m = 0; m < this.numberElements - i; m++) auxPoint[m] = points[indexAux++];
            pointSort = auxPoint[0];
            Arrays.sort(auxPoint, pointSort.slopeOrder());
            int count = 1, j = 2;
            double slopeCurrent = pointSort.slopeTo(auxPoint[1]);
            for (; j < this.numberElements - i; j++) {
                if (slopeCurrent == pointSort.slopeTo(auxPoint[j])) count++;
                else {
                    slopeCurrent = pointSort.slopeTo(auxPoint[j]);
                    if (count > 2) addSegment(pointSort, auxPoint[j-1]);
                    count = 1;
                }
            }
            if (count > 2) addSegment(pointSort, auxPoint[j-1]);
            if (count == auxPoint.length - 1) break;
        }
    }

    private void addSegment(Point pointStart, Point pointEnd) {
        double slopeAux = pointStart.slopeTo(pointEnd);
        for (int i = 0; i < dimSegment; i++)
            if (slopeAux == skipeSlope[i] && pointEnd.compareTo(skipePoint[i]) == 0) return;
        if (dimSegment == segmentsOk.length) resizeSegment(dimSegment + 1);
        segmentsOk[dimSegment++] = new LineSegment(pointStart, pointEnd);
        dimSegment--;
        if (dimSegment == skipeSlope.length) resizeSkipeSlope(dimSegment + 1);
        skipeSlope[dimSegment++] = slopeAux;
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

    private void resizeSegment(int capacity)
    {
        LineSegment[] copy = new LineSegment[capacity];
        for (int i = 0; i < dimSegment; i++) copy[i] = segmentsOk[i];
        segmentsOk = copy;
    }

    // the number of line segments
    public int numberOfSegments() {
        return dimSegment;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[dimSegment];
        if (dimSegment == 0) return copy;
        for (int i = 0; i < dimSegment; i++) copy[i] = segmentsOk[i];
        return copy;
    }
}
