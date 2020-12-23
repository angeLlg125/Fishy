package geometry;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import utils.Constants;

public class WallWorld {
	private ArrayList<Wall> gameWalls = new ArrayList<>();
	private LightBulb lightBulb;
	
	private int[] fillX;
	private int[] fillY;
	private int count = 0;
	
	
	public WallWorld() {
		this.setUpLightLocation();
		this.setUpWalls();
	}
	
	private void setUpWalls() {

		// Window walls, borders
		addWall(0, 0, 0, Constants.WINDOWS_Y);
		addWall(0, 0, Constants.WINDOWS_X, 0);
		addWall(0, Constants.WINDOWS_Y, Constants.WINDOWS_X, Constants.WINDOWS_Y);
		addWall(Constants.WINDOWS_X, 0, Constants.WINDOWS_X, Constants.WINDOWS_Y);
		
		this.generateRandomLines();
	}
	
	private void generateRandomLines() {
		
		for (int i = 0; i < Constants.RANDOM_LINES; i++) {
			addWall(getRandomNumber(0, Constants.WINDOWS_X), getRandomNumber(0, Constants.WINDOWS_Y), getRandomNumber(0, Constants.WINDOWS_X), getRandomNumber(0, Constants.WINDOWS_Y));
		}
	
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public void searchIntersectionsFullLight(Graphics g, boolean drawLines) {
		double angle = 0;
		Point currentLine = new Point(lightBulb.getLocation().getX(), lightBulb.getLocation().getY());
		
		fillX = new int[720];
		fillY = new int[720];
		count = 0;
		
		Point distance = new Point(currentLine.getX() + Constants.SEARCH_DISTANCE, currentLine.getY() + Constants.SEARCH_DISTANCE);
		for (int i = 0; i < 720; i++) {
			double[] pt = {distance.getX(), distance.getY()};
			AffineTransform.getRotateInstance(Math.toRadians(angle), currentLine.getX(), currentLine.getY()).transform(pt, 0, pt, 0, 1);
			int newX = (int)pt[0];
			int newY = (int)pt[1];
			
			this.searchPointIntersection(g, new Line(currentLine.getX(), currentLine.getY(), newX, newY), 720, drawLines);
			angle += 0.5;
		}
	}
	
	/*
	 * Calculate intersection and increase the degrees n (0.5) times to rotate the point. Repeat process.
	 * 
	 * the result looks like <  (depending of the angle the direction will be different)
	 */
	public void searchIntersectionsNDegrees(Graphics g, boolean drawLines, boolean drawContent) {
		double angle = lightBulb.angle;
		Point currentLine = lightBulb.getLocation();
		fillX = new int[Constants.PARTIAL_CIRCLE_SIZE + 1];
		fillY = new int[Constants.PARTIAL_CIRCLE_SIZE + 1];
		
		count = 0;
		fillX[count] = lightBulb.getLocation().getX();
		fillY[count] = lightBulb.getLocation().getY();
		count++;
		
		Point distance = new Point(currentLine.getX() + Constants.SEARCH_DISTANCE, currentLine.getY() + Constants.SEARCH_DISTANCE);
		for (int i = lightBulb.angle; i < lightBulb.angle + Constants.PARTIAL_CIRCLE_SIZE; i++) {
			double[] pt = {distance.getX(), distance.getY()};
			AffineTransform.getRotateInstance(Math.toRadians(angle), currentLine.getX(), currentLine.getY()).transform(pt, 0, pt, 0, 1);
			int newX = (int)pt[0];
			int newY = (int)pt[1];
			
			this.searchPointIntersection(g, new Line(currentLine.getX(), currentLine.getY(), newX, newY), lightBulb.angle + Constants.PARTIAL_CIRCLE_SIZE, drawLines);
			g.drawOval(newX, newY, Constants.LIGHT_BULB_SIZE, Constants.LIGHT_BULB_SIZE);
			angle += 0.5;
		}
	}
	
	/*
	 * This will create a circle. The size depends of SEARCH_DISTANCE_CIRCULAR
	 * 
	 * In this case is required to calculate 360 points in order to complete the circumference, an increasing in 1 the degrees
	 */
	public void searchIntersectionsCircularForm(Graphics g, boolean drawLines) {
		double angle = 0;
		Point currentLine = lightBulb.getLocation();
		
		Point distance = new Point(currentLine.getX() + Constants.SEARCH_DISTANCE_CIRCULAR, currentLine.getY() + Constants.SEARCH_DISTANCE_CIRCULAR);
		fillX = new int[360];
		fillY = new int[360];
		count = 0;
		for (int i = 0; i < 360; i++) {
			double[] pt = {distance.getX(), distance.getY()};
			AffineTransform.getRotateInstance(Math.toRadians(angle), currentLine.getX(), currentLine.getY()).transform(pt, 0, pt, 0, 1);
			int newX = (int)pt[0];
			int newY = (int)pt[1];
			
			boolean result = this.searchPointIntersection(g, new Line(currentLine.getX(), currentLine.getY(), newX, newY), 360, drawLines);
			
			if(!result) {
				
				if(drawLines)
					g.drawLine(currentLine.getX(), currentLine.getY(), newX, newY);
				
				if(count < fillX.length) {
					fillX[count] = newX;
					fillY[count] = newY;
					count++;
				}
			}
			
			angle += 1;
		}
	}
	
	/*
	 * Calculate intersection between current line to evaluate.
	 * 
	 * The center is the lightDot, and the other point is the one calculated in previous methods
	 */
	private boolean searchPointIntersection(Graphics g, Line lineToEvaluate, int size, boolean drawLines) {
		Point currentPoint = null;
		int currentDistance = 0;
		for (int i = 0; i < gameWalls.size(); i++) {
			Line line = this.getWall(i).getPoints();
			
			Point result = this.calculateIntersectionPoint(lineToEvaluate.getPoint1(), lineToEvaluate.getPoint2(), line.getPoint1(), line.getPoint2());

			// If result is null, no intersection found
			if(result != null) {
				
				// Take the first point when no point selected
				if(currentPoint == null) {
					currentPoint = result;
					currentDistance = this.distanceBetweenPoints(this.lightBulb.getLocation(), result);
					
				}else {
					
					// Else, calculate the distance between lightDot and the intersected point.
					// if the new distance is lower, that means the new intersection is closer the lightDot, only the 
					// closer one has to be selected to avoid showing the light in two lines at the same time
					int tempDistance = this.distanceBetweenPoints(this.lightBulb.getLocation(), result);
					if(tempDistance < currentDistance) {
						currentPoint = result;
						currentDistance = tempDistance;
					}
				}
			}
				
		}
		
		// To avoid null pointer
		if(currentPoint != null) {
			// Only draw lines if the flag is active
			if(drawLines)
					g.drawLine(lineToEvaluate.getP1X(), lineToEvaluate.getP1Y(), currentPoint.getX(), currentPoint.getY());
			
			// Save all the points, event the ones with no intersection.
			// Those points will be used to draw the circumference if the flag is activated in MyCanvas class
			if(count < fillX.length) {
				fillX[count] = currentPoint.getX();
				fillY[count] = currentPoint.getY();
				count++;
			}
			return true;
		}else {
			return false;
		}
	}
	
	private Point calculateIntersectionPoint(Point A, Point B, Point C, Point D) {
		
		/* If lines are not intersected, end process.
		   if this if is removed, the code bellow will calculate an intersection. Since the lines are being projected 
		   to infinite, there is not intersection only when lines are parallel
		  
		  Then this if is used to calculate the the lines (considering its real size) are intersected 
		*/
		if(!areLinesIntersected(A, B, C, D)) {
			return null;
		}
		
		// Line AB represented as a2x + b2y = c1
		int a1 = B.y - A.y; 
        int b1 = A.x - B.x; 
        int c1 = a1*(A.x) + b1*(A.y); 
       
        // Line CD represented as c2x + d2y = c2 
        int a2 = D.y - C.y; 
        int b2 = C.x - D.x; 
        int c2 = a2*(C.x)+ b2*(C.y); 
       
        int determinant = a1*b2 - a2*b1; 
       
        if (determinant == 0) 
        { 
            // The lines are parallel. This is simplified
        	return null;
        } 
        else
        { 
        	// Calculate the intersection point
            int x = (b2*c1 - b1*c2)/determinant; 
            int y = (a1*c2 - a2*c1)/determinant; 
            return new Point(x, y); 
        } 
	}
	
	private boolean areLinesIntersected(Point A, Point B, Point C, Point D) {
		if(!Line2D.linesIntersect(A.x,A.y,B.x,B.y,C.x,C.y,D.x,D.y)) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * D = Srt((p1.x -p2x)^2 + (p1.x -p2x)^2 )
	 * 
	 * Calculate distance between two lines 
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private int distanceBetweenPoints(Point p1, Point p2) {
		
		return (int)Point2D.distance(p1.x, p1.y, p2.x, p2.y);
	}
	
	private void setUpLightLocation() {
		lightBulb = new LightBulb(20, 20);
	}
	
	public void addWall(int x1, int y1, int x2, int y2) {
		this.gameWalls.add(new Wall(x1, y1, x2, y2));
	}
	
	public Wall getWall(int index) {
		return gameWalls.get(index);
	}
	
	public int getWallsLength() {
		return this.gameWalls.size();
	}
	
	public void setLightDotLocation(int x, int y) {
		
	}
	
	public LightBulb getLightBulb() {
		return this.lightBulb;
	}
	
	public int[] getFillX() {
		return this.fillX;
	}
	
	public int[] getFillY() {
		return this.fillY;
	}
}
