package geometry;

import utils.Constants;

public class LightDot {
	private Line line1, line2;
	private Point location;
	Point direction;
	
	public void LightDot() {
		
	}
	
	public LightDot(int x, int y) {
		location = new Point(x, y);
		
		this.line1 = new Line(20, 20, 500, 200);
		this.line2 = new Line(20, 20, 200, 500);
	}
	
	public void setPoint(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public Line getLine1() {
		return this.line1;
	}
	
	public Line getLine2() {
		return this.line2;
	}
	
	public void setLine1(Line line) {
		this.line1 = line;
	}
	
	public void setLine2(Line line) {
		this.line2 = line;
	}
}
