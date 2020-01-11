/*
Author: Atharva Lipare
*/
// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private LineSegment[] segmentsArray;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("null argument.");

        for (int i = 0; i < points.length; i++)
            if (points[i] == null) throw new IllegalArgumentException("null entry found.");

        // create new copyPoints array to sort original
        Point[] copyPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) copyPoints[i] = points[i];

        Arrays.sort(copyPoints);    // sort wrt y, tiebreaker x
        for (int i = 1; i < points.length; i++) {
            if (copyPoints[i].compareTo(copyPoints[i - 1]) == 0) throw new IllegalArgumentException("duplicate entry found.");
        }

        ArrayList<LineSegment> segmentsList = new ArrayList<>(); // create ArrayList<LineSegment> to store segments

        // this is brute-force logic which checks if slope equal between 4 pts.
        for (int a = 0; a < points.length - 3; a++) {
            for (int b = a + 1; b < points.length - 2; b++) {
                double m1 = copyPoints[a].slopeTo(copyPoints[b]);
                for (int c = b + 1; c < points.length - 1; c++) {
                    double m2 = copyPoints[a].slopeTo(copyPoints[c]);
                    if (m1 == m2) {
                        for (int d = c + 1; d < points.length; d++) {
                            double m3 = copyPoints[a].slopeTo(copyPoints[d]);
                            if (m1 == m3) {
                                LineSegment temp = new LineSegment(copyPoints[a], copyPoints[d]);
                                segmentsList.add(temp);
                            }
                        }
                    }
                }
            }
        }
        segmentsArray = segmentsList.toArray(new LineSegment[0]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
