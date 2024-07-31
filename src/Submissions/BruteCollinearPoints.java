package Submissions;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();


    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new IllegalArgumentException("Argument is null");

        // Check for null points
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Point is null");
        }

        // Find all line segments containing 4 points
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                for (int k = 0; k < points.length; k++) {
                    if (i == k || j == k) continue;
                    for (int l = 0; l < points.length; l++) {
                        if (i == l || j == l || k == l) continue;
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            segmentsList.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

    }
    public int numberOfSegments() {
        return segmentsList.size();
    }

    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        // Add your test cases here
    }
}
