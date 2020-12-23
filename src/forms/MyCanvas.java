package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import geometry.LightDot;
import geometry.Line;
import geometry.Point;
import geometry.WallWorld;
import utils.Constants;

public class MyCanvas extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private WallWorld world = new WallWorld();
	
	public MyCanvas() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Search intersections");
        
        // Background
        g.setColor(Constants.BACK_GROUND);
        g.fillRect(0, 0, Constants.WINDOWS_X, Constants.WINDOWS_Y);
        
        g.setColor(Constants.CIRCLE_COLOR);
        LightDot lightDot = world.getLightDot();
        g.fillOval(lightDot.getLocation().getX() - Constants.CIRCLE_SIZE/2, lightDot.getLocation().getY()- Constants.CIRCLE_SIZE/2, Constants.CIRCLE_SIZE, Constants.CIRCLE_SIZE);
        
        // Draw walls
        g.setColor(Constants.LINE_COLOR);
        for(int i = 0; i < world.getWallsLength(); i++) {
            Line line = world.getWall(i).getPoints();
            
            g.drawLine(line.getP1X(), line.getP1Y(), line.getP2X(), line.getP2Y());
        }
        
        
        
        g.setColor(Constants.LIGHT_COLOR);
        // Search Intersections between lines
        this.world.searchIntersections(g);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		this.world.getLightDot().setPoint(new Point(e.getX(), e.getY()));
		
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		/*this.world.getLightDot().setPoint(new Point(e.getX(), e.getY()));
		
		this.repaint();*/
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.world.getLightDot().setPoint(new Point(e.getX(), e.getY()));
		
		this.repaint();
	}
}
