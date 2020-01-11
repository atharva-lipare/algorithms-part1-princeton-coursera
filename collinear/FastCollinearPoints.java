/*
Author: Atharva Lipare
*/
// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    // finds all line segments containing 4 or more points
    private final LineSegment[] segmentsArray;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("null argument.");

        for (int i = 0; i < points.length; i++)
            if (points[i] == null) throw new IllegalArgumentException("null entry found.");

        // create new sortedPoints array to sort original
        Point[] sortedPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) sortedPoints[i] = points[i];

        Arrays.sort(sortedPoints);  // sort wrt y, tiebreaker x
        for (int i = 1; i < points.length; i++)
            if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0)
                throw new IllegalArgumentException("duplicate entry found.");

        final LinkedList<LineSegment> segmentsList = new LinkedList<>();    // create LinkedList<LineSegment> to store segments

        for (int i = 0; i < points.length; i++) {
            Point[] pointsSortedBySlope = sortedPoints.clone(); // new array which sorts sortedPoints array wrt to slope, stable sort used
            Arrays.sort(pointsSortedBySlope, sortedPoints[i].slopeOrder());

            int j = 1;  // start from j = 1, bcoz pointsSortedBySlope[0] will be sortedPoints[i] as slope to same point is NEGATIVE_INFINITY
            while (j < points.length) {

                LinkedList<Point> collinearPointsList = new LinkedList<>(); // temporary list to store collinear points, we need atleast 4 in this case including sortedPoints[i]
                final double refSlope = sortedPoints[i].slopeTo(pointsSortedBySlope[j]);    // ref slope to compare

                while(j < points.length && sortedPoints[i].slopeTo(pointsSortedBySlope[j]) == refSlope) {
                    collinearPointsList.add(pointsSortedBySlope[j++]);  // if have same slopes add to collinear points list
                }

                if(collinearPointsList.size() >= 3 && sortedPoints[i].compareTo(collinearPointsList.peek()) < 0) { // size of list of collinear points should >= 3 in this case
                    segmentsList.add(new LineSegment(sortedPoints[i], collinearPointsList.removeLast()));          // and first ele of list shouldn't have negative slope as we want maximal segment i.e LineSegment(smallestPoint, LargestPoint)
                }
            }
        }
        segmentsArray = segmentsList.toArray(new LineSegment[0]); // conversion of list to array
    }
    // the number of line segments
    public int numberOfSegments() {
        return segmentsArray.length;
    }
    // the line segments
    public LineSegment[] segments() {
        return segmentsArray.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}