/*
 * javac -cp ".;algs4.jar" Collinear.java
 * java -cp ".;algs4.jar" Collinear <name_file>
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Stack;

public class ConvexHull {
    private static final int MAX_VALUE_COORDINATE = 100;
    private static final int SCALE = 100;

    public static void main (String [] args) {
        int sizeArray = Integer.parseInt(args[0]);
        PointCH [] points = new PointCH[sizeArray];
        initArray(points, sizeArray);
        ConvexHullPoints cvp = new ConvexHullPoints(points);
        Stack<PointCH> pointsCH = cvp.getPointCHs();

        // points on screen
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, SCALE);
        StdDraw.setYscale(0, SCALE);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        for (PointCH p : points) p.draw();
        StdDraw.show();

        // Lines on screen
        Arrays.sort(points, PointCH.sortByCoordenateY);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        points[0].draw();
        StdDraw.show();

        Arrays.sort(points, points[0].polarOrder());
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.0005);
        for (int i = 1; i < points.length; i++) {
        	points[0].drawTo(points[i]);
            StdDraw.show();
            StdDraw.pause(100);
        }

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.002);
        int index = 0;
        int sizeChull = pointsCH.size();
        PointCH firstP = pointsCH.peek();
        while (!pointsCH.isEmpty() && index++ < sizeChull-1) {
        	PointCH top = pointsCH.pop();
        	PointCH next = pointsCH.peek();
        	top.drawTo(next);
            StdDraw.show();
        	StdDraw.pause(500);
        }
        pointsCH.pop().drawTo(firstP);
        StdDraw.show();
    }

    public static void initArray(PointCH [] points, int sizeArray) {
        for (int i = 0; i < sizeArray;) {
            int x = StdRandom.uniform(5,MAX_VALUE_COORDINATE-4);
            int y = StdRandom.uniform(5,MAX_VALUE_COORDINATE-4);
            PointCH pTmp = new PointCH(x, y);
            if (!existPoint(points, pTmp, i)) {
                points[i++] = new PointCH(x, y);
            }
        }
    }

    public static boolean existPoint(PointCH [] points, PointCH pointSearch, int sizeArray) {
        if (sizeArray == 0) return false;
        for (int i = 0; i < sizeArray; i++) {
            if (points[i].compareTo(pointSearch) == 0) return true;
        }
        return false;
    }
}
