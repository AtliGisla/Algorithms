package d0;

import edu.princeton.cs.algs4.StdDraw;

public class Drawing {
	
	public static void main(String[] args) {
		DrawGiraffe();
		DrawGround();
		DrawText();
	}
	
	public static void DrawGiraffe() {
		StdDraw.setPenColor(255,211,0);
		StdDraw.filledRectangle(0.4, 0.3, 0.2, 0.1);
		StdDraw.filledRectangle(0.225, 0.2, 0.025, 0.1);
		StdDraw.filledRectangle(0.575, 0.2, 0.025, 0.1);
		double[] xarray = {0.5, 0.6, 0.75, 0.65};
		double[] yarray = {0.37, 0.37, 0.66, 0.66};
		StdDraw.filledPolygon(xarray, yarray);
		StdDraw.filledCircle(0.73, 0.7, 0.1);
		StdDraw.setPenColor(0,0,0);
		StdDraw.line(0.72, 0.68, 0.815, 0.65);
		StdDraw.circle(0.73, 0.7, 0.01);
		StdDraw.square(0.32, 0.3, 0.06);
		StdDraw.square(0.4, 0.26, 0.03);
		StdDraw.square(0.43, 0.33, 0.02);
		StdDraw.square(0.5, 0.36, 0.02);
		StdDraw.square(0.53, 0.27, 0.05);
	}
	
	public static void DrawGround() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledEllipse(0.5, 0, 1, 0.1);
	}
	
	public static void DrawText() {
		StdDraw.setPenColor(0,0,0);
		StdDraw.text(0.2, 0.8, "The Humble Giraffe", 10);
	}
}
