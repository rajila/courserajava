import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private final Point [] pointsData;
    private final int numberElements;
    private final ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) throw new IllegalArgumentException("Input Illegal");
        this.numberElements = points.length;
        this.pointsData = new Point[this.numberElements];
        if (!fullPoints(points)) throw new IllegalArgumentException("Input Illegal");
        segments = new ArrayList<>();
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
        double slopeA = 0.0;
        double slopeB = 0.0;
        double slopeC = 0.0;
        Arrays.sort(points);
        for (int i = 0; i < this.numberElements - 3; i++) {
            for (int j = i + 1; j < this.numberElements-2; j++) {
                slopeA = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < this.numberElements-1; k++) {
                    slopeB = points[i].slopeTo(points[k]);
                    if (slopeA != slopeB) continue;
                    for (int m = k + 1; m < this.numberElements; m++) {
                        slopeC = points[i].slopeTo(points[m]);
                        if (slopeB == slopeC) segments.add(new LineSegment(points[i], points[m]));
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[this.segments.size()]);
    }
}
