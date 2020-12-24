package forms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import geometry.Line;
import geometry.Point;
import geometry.WallWorld;
import utils.Constants;
import utils.Functions;

public class MyCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private WallWorld world = new WallWorld();
	
	// Change between 0 and 2, or click on the screen to see different light modes
	private int lightType = 0;
	
	// Change variables to see different view modes
	private boolean drawLines = false;
	private boolean drawContent = false;
	private boolean drawCircinference = true;
	private boolean drawShadows = true;
	
	Rectangle [] rec = new Rectangle[13];
	public MyCanvas() {
        for (int i = 0; i < 13; i++) {
        	rec[i] = new Rectangle(Functions.getRandomNumber(0, Constants.WINDOWS_X), Functions.getRandomNumber(0, Constants.WINDOWS_Y), 
        			20, 20);
		}
	}
	
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Background
        g.setColor(Constants.BACK_GROUND);
        g.fillRect(0, 0, Constants.WINDOWS_X, Constants.WINDOWS_Y);
        
        g.setColor(Constants.CIRCLE_COLOR);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < 13; i++) {
        	g2d.fill(rec[i]);
		}
        
        // Draw walls
        g.setColor(Constants.LINE_COLOR);
        for(int i = 0; i < world.getWallsLength(); i++) {
            Line line = world.getWall(i).getPoints();
            
            g.drawLine(line.getP1X(), line.getP1Y(), line.getP2X(), line.getP2Y());
        }
        
        g.setColor(Constants.LIGHT_COLOR);
        // Search Intersections, depending of the searchType value
        if(lightType == 0) {
        	this.world.searchIntersectionsCircularForm(g, drawLines, drawShadows);
        }else if(lightType == 1){
        	this.world.searchIntersectionsFullLight(g, drawLines, drawShadows);
        }else if(lightType == 2){
        	this.world.searchIntersectionsNDegrees(g, drawLines, drawContent, drawShadows);
        }else {
        	this.world.searchIntersectionsCircularForm(g, drawLines, drawShadows); 
        }
        
        // Draw circumference or fill figure. It depends of the flags
        if(drawContent && !drawLines) {
        	g.setColor(Constants.LIGHT_COLOR);
        	g.fillPolygon(this.world.getFillX(), this.world.getFillY(), this.world.getFillX().length);
        }else if(drawCircinference) {
        	g.setColor(Constants.LIGHT_COLOR);
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
