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
	
	private WallWorld world = new WallWorld();
	
	// Change between 0 and 2, or click on the screen to see different light modes
	private int lightType = 0;
	
	// Change variables to see different view modes
	private boolean drawLines = false;
	private boolean drawContent = false;
	private boolean drawCircinference = true;
	
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
        // Search Intersections, depending of the searchType value
        if(lightType == 0) {
        	this.world.searchIntersectionsCircularForm(g, drawLines);
        }else if(lightType == 1){
        	this.world.searchIntersectionsFullLight(g, drawLines);
        }else if(lightType == 2){
        	this.world.searchIntersectionsNDegrees(g, drawLines, drawContent);
        }else {
        	this.world.searchIntersectionsCircularForm(g, drawLines);
        }
        
        // Draw circumference or fill figure. It depends of the flags
        if(drawContent && !drawLines) {
        	g.fillPolygon(this.world.getFillX(), this.world.getFillY(), this.world.getFillX().length);
        }else if(drawCircinference) {
        	g.drawPolygon(this.world.getFillX(), this.world.getFillY(), this.world.getFillX().length);
        }
        
        // Draw dot
        g.setColor(Constants.CIRCLE_COLOR);
        Point lightDot = world.getLightBulb().getLocation();
        g.fillOval(lightDot.getX() - Constants.LIGHT_BULB_SIZE/2, 
        		lightDot.getY()-Constants.LIGHT_BULB_SIZE/2, 
        		Constants.LIGHT_BULB_SIZE, Constants.LIGHT_BULB_SIZE);
    }
    
    public void repaintCanvas() {
    	this.repaint();
    }
    
    public int getLightType() {
    	return this.lightType;
    }
    
    public WallWorld getWorld() {
    	return this.world;
    }
    
    public void addAmountToSearchType(int amount) {
    	this.lightType += amount;
    }
    
    public void restartSearchType() {
    	this.lightType = 0;
    }
    
}
