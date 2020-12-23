package geometry;

public class Wall {
	private Line line;
	
	public Wall() {
	}
	
	public Wall(int x1, int y1, int x2, int y2) {
		this.line = new Line(x1, y1, x2, y2);
	}
	
	public Line getPoints() {
		return line;
	}
}
