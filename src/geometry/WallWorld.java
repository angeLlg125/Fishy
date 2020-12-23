package geometry;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import utils.Constants;

public class WallWorld {
	private ArrayList<Wall> gameWalls = new ArrayList<>();
	private LightDot lightDot;
	
	public WallWorld() {
		this.setUpLightLocation();
		this.setUpWalls();
	}
	
	private void setUpWalls() {
		
		// Game window
		addWall(0, 0, 0, Constants.WINDOWS_Y);
		addWall(0, 0, Constants.WINDOWS_X, 0);
		addWall(0, Constants.WINDOWS_Y, Constants.WINDOWS_X, Constants.WINDOWS_Y);
		addWall(Constants.WINDOWS_X, 0, Constants.WINDOWS_X, Constants.WINDOWS_Y);
		
		this.generateRandomLines();
	}
	
	private void generateRandomLines() {
		
		for (int i = 0; i < 5; i++) {
			addWall(getRandomNumber(0, Constants.WINDOWS_X), getRandomNumber(0, Constants.WINDOWS_Y), getRandomNumber(0, Constants.WINDOWS_X), getRandomNumber(0, Constants.WINDOWS_Y));
		}
	
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	private void setUpLightLocation() {
		lightDot = new LightDot(20, 20);
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
	
	public LightDot getLightDot() {
		return this.lightDot;
	}
	
	public void searchIntersections(Graphics g) {
		//this.searchPointIntersection(g, this.lightDot.getLine1());
		double angle = 0;
		Point currentLine = lightDot.getLocation();
		
		Point distance = new Point(currentLine.getX()+600, currentLine.getY()+600);
		for (int i = 0; i < 2000; i++) {
			double[] pt = {distance.getX(), distance.getY()};
			AffineTransform.getRotateInstance(Math.toRadians(angle), currentLine.getX(), currentLine.getY()).transform(pt, 0, pt, 0, 1);
			int newX = (int)pt[0];
			int newY = (int)pt[1];
			
			distance = new Point(newX, newY);
			this.searchPointIntersection(g, new Line(currentLine.getX(), currentLine.getY(), newX, newY));
			angle+=.1;
		}
	}
	
	private void searchPointIntersection(Graphics g, Line lineToEvaluate) {
		Point currentPoint = null;
		int currentDistance = 0;
		for (int i = 0; i < gameWalls.size(); i++) {
			Line line = this.getWall(i).getPoints();
			
			Point result = this.calculateLineIntersection(line.getPoint1(), line.getPoint2(), lineToEvaluate.getPoint1(), lineToEvaluate.getPoint2());

			if(result != null) {
				//g.fillOval(result.x -Constants.CIRCLE_SIZE/2, result.y-Constants.CIRCLE_SIZE/2, Constants.CIRCLE_SIZE, Constants.CIRCLE_SIZE);
				if(currentPoint == null) {
					currentPoint = result;
					currentDistance = this.distanceBetweenPoints(this.lightDot.getLocation(), result);
					
				}else {
					int tempDistance = this.distanceBetweenPoints(this.lightDot.getLocation(), result);
					if(tempDistance < currentDistance) {
						currentPoint = result;
						currentDistance = tempDistance;
						
					}
				}
			}
				
		}
		if(currentPoint != null) {
			g.drawLine(lineToEvaluate.getP1X(), lineToEvaluate.getP1Y(), currentPoint.getX(), currentPoint.getY());
	        //g.drawLine(lightDot.getLine2().getP1X(), lightDot.getLine2().getP1Y(), lightDot.getLine2().getP2X(), lightDot.getLine2().getP2Y());
			//g.fillOval(currentPoint.x -Constants.CIRCLE_SIZE/2, currentPoint.y-Constants.CIRCLE_SIZE/2, Constants.CIRCLE_SIZE, Constants.CIRCLE_SIZE);
		}
	}
	
	private Point calculateLineIntersection(Point A, Point B, Point C, Point D) {
		
		if(!Line2D.linesIntersect(A.x,A.y,B.x,B.y,C.x,C.y,D.x,D.y)) {
			return null;
		}
		
		int a1 = B.y - A.y; 
        int b1 = A.x - B.x; 
        int c1 = a1*(A.x) + b1*(A.y); 
       
        // Line CD represented as a2x + b2y = c2 
        int a2 = D.y - C.y; 
        int b2 = C.x - D.x; 
        int c2 = a2*(C.x)+ b2*(C.y); 
       
        int determinant = a1*b2 - a2*b1; 
       
        if (determinant == 0) 
        { 
            // The lines are parallel. This is simplified 
            // by returning a pair of FLT_MAX 
        	return null;
        } 
        else
        { 
            int x = (b2*c1 - b1*c2)/determinant; 
            int y = (a1*c2 - a2*c1)/determinant; 
            return new Point(x, y); 
        } 
	}
	
	/**
	 * D = Srt((p1.x -p2x)^2 + (p1.x -p2x)^2 )
	 * @param p1
	 * @param p2
	 * @return
	 */
	private int distanceBetweenPoints(Point p1, Point p2) {
		
		return (int)Point2D.distance(p1.x, p1.y, p2.x, p2.y);
	}
}
