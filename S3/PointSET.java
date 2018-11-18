package S3;
 
/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:    
 *  Dependencies:
 *  Author:
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points,
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/
 
import java.util.Arrays;
 
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
 
public class PointSET {
   
    private SET<Point2D> set;
   
    // construct an empty set of points
    public PointSET() {
        set = new SET<>();
    }
 
    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }
 
    // number of points in the set
    public int size() {
        return set.size();
    }
 
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        set.add(p);
    }
 
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
 
    // draw all of the points to standard draw
    public void draw() {
       
        // Iterates over the set and prints each point.
        for(Point2D point : set) {
            StdDraw.point(point.x(), point.y());
        }
 
    }
 
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
       
        // Initialize the set points which we will insert the points that are inside the rectangle.
        SET<Point2D> points = new SET<>();
   
        //Iterate over all the points in the set and if they are in the rectangle we add the to points
        for(Point2D point : set) {
            if(rect.contains(point)) {
                points.add(point);
            }
        }
       
        return points;
    }
 
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
       
        // Initialize a point we call nearest as null, we will use it to compare with other points to determine
        // which is nearer to the point p.
        Point2D nearest = null;
       
        // Iterate over all the points in the set and if any are nearer than the point nearest,
        // if so we give nearest it's coordinates.
        for(Point2D point : set) {
            if(nearest == null) {
                nearest = point;
            }
           
            if(point.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
                nearest = point;
            }
           
        }
       
        return nearest;
       
    }
 
    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        PointSET set = new PointSET();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }
 
        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }
 
        out.println();
    }
 
}