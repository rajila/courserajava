import edu.princeton.cs.algs4.StdRandom;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;

public final class ConvexHullPoints {
    private PointCH [] points;
    private final Stack<PointCH> pointsCH;
    private final int sizeElements;

    public ConvexHullPoints(PointCH [] pointsIn) {
        if (pointsIn == null || pointsIn.length == 0) throw new IllegalArgumentException("Input Illegal");
        if (!validPoints(pointsIn)) throw new IllegalArgumentException("Validation -> Input Illegal");
        this.sizeElements = pointsIn.length;
        this.points = new PointCH[this.sizeElements];
        pointsCH = new Stack<PointCH>();
        this.points = pointsIn.clone();
        getAllPointsConvexHull(this.points, this.pointsCH);
    }

    private boolean validPoints(PointCH[] points) {
        Arrays.sort(points); // Sorter by coordena for validation de pointers repetidos
        for (int i = 0; i < points.length-1; i++) if (points[i].compareTo(points[i+1]) == 0) return false;
        return true;
    }

    private void getAllPointsConvexHull(PointCH [] points, Stack<PointCH> result) {
        Arrays.sort(points, PointCH.sortByCoordenateY);
        Arrays.sort(points, points[0].polarOrder());
        result.push(points[0]);
        result.push(points[1]);
        for (int i = 2; i < points.length; i++) {
        	PointCH top = result.pop();
        	while (result.peek().ccw(top, points[i]) < 0) top = result.pop(); // First ccw is > 0
        	result.push(top);
        	result.push(points[i]);
        }
    }

    public Stack<PointCH> getPointCHs() {
        return this.pointsCH;
    }
}
