package geometry;

public class Line {
	private Point p1;
	private Point p2;
	
	public Line() {
	}
	
	public Line(int x1, int y1, int x2, int y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}
	
	public int getP1X() {
		return p1.getX();
	}
	
	public int getP1Y() {
		return p1.getY();
	}
	
	public int getP2X() {
		return p2.getX();
	}
	
	public int getP2Y() {
		return p2.getY();
	}
	
	public Point getPoint1() {
		return this.p1;
	}
	
	public Point getPoint2() {
		return this.p2;
	}
}
