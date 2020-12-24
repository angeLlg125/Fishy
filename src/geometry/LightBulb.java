package geometry;

public class LightBulb {
	private Point location;
	private int angle = 0;
	
	public LightBulb() {
	}
	
	public LightBulb(int x, int y) {
		location = new Point(x, y);
	}
	
	public void setPoint(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public int getAngle() {
		return this.angle;
	}
}
