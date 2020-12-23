package forms;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import geometry.Line;
import geometry.Point;
import geometry.WallWorld;
import utils.Constants;

public class MyCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	WallWorld world = new WallWorld();
	int searchType = 0;
	public MyCanvas() {
		
	}
	
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Background
        g.setColor(Constants.BACK_GROUND);
        g.fillRect(0, 0, Constants.WINDOWS_X, Constants.WINDOWS_Y);
        
        // Draw walls
        g.setColor(Constants.LINE_COLOR);
        for(int i = 0; i < world.getWallsLength(); i++) {
            Line line = world.getWall(i).getPoints();
            
            g.drawLine(line.getP1X(), line.getP1Y(), line.getP2X(), line.getP2Y());
        }
        
        g.setColor(Constants.LIGHT_COLOR);
        // Search Intersections between lines
        
        if(searchType == 0) {
        	this.world.searchIntersectionsCircularForm(g);
        }else if(searchType == 1){
        	this.world.searchIntersectionsFullLight(g);
        }else {
        	this.world.searchIntersectionsNDegrees(g);
        }
        
        g.setColor(Color.RED);
        Point lightDot = world.getLightDot().getLocation();
        g.fillOval(lightDot.getX() - Constants.CIRCLE_SIZE/2, lightDot.getY()-Constants.CIRCLE_SIZE/2, Constants.CIRCLE_SIZE, Constants.CIRCLE_SIZE);
        
    }
    
    public void repaintCanvas() {
    	this.repaint();
    }
}
