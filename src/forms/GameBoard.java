package forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import geometry.Point;
import utils.Constants;

/**
 *
 * @author angel
 */
public class GameBoard extends javax.swing.JFrame implements MouseMotionListener, KeyListener, MouseListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyCanvas canvas = new MyCanvas();
    
    public GameBoard() {
        initComponents();
        // Define canvas components
        canvas.setSize(Constants.WINDOWS_X, Constants.WINDOWS_Y);
        canvas.setBackground(Color.yellow);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);

        this.setSize(Constants.WINDOWS_X, Constants.WINDOWS_Y);
        Dimension dimension = new Dimension(Constants.MIN_WINDOWS_X, Constants.MIN_WINDOWS_Y);
        this.setMinimumSize(dimension);
        this.add(canvas);
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
        
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.addMouseListener(this);
    }
    
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.canvas.searchType != 2) {
			this.getCoordinades(this.getInsets(), e);
			
			this.canvas.repaintCanvas();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(this.canvas.searchType == 2) {
			Point point = this.canvas.world.getLightDot().getLocation();

			if(e.getKeyChar() == 'a') {//Right
				this.canvas.world.getLightDot().setPoint(new Point(point.getX() - Constants.CIRCLE_SPEED, point.getY()));
				this.canvas.world.getLightDot().setLocation(new Point(point.getX() - Constants.CIRCLE_SPEED, point.getY()));
			}else if(e.getKeyChar() == 'd') {//right
				this.canvas.world.getLightDot().setPoint(new Point(point.getX() + Constants.CIRCLE_SPEED, point.getY()));
				this.canvas.world.getLightDot().setLocation(new Point(point.getX() + Constants.CIRCLE_SPEED, point.getY()));
			}else if(e.getKeyChar() == 'w') {//Up
				this.canvas.world.getLightDot().setPoint(new Point(point.getX(), point.getY() - Constants.CIRCLE_SPEED));
				this.canvas.world.getLightDot().setLocation(new Point(point.getX(), point.getY() - Constants.CIRCLE_SPEED));
			}else if(e.getKeyChar() == 's') {//Down
				this.canvas.world.getLightDot().setPoint(new Point(point.getX(), point.getY() + Constants.CIRCLE_SPEED));
				this.canvas.world.getLightDot().setLocation(new Point(point.getX(), point.getY() + Constants.CIRCLE_SPEED));
			}else if(e.getKeyChar() == 'e') {//q
				this.canvas.world.getLightDot().setAngle(this.canvas.world.getLightDot().getAngle() - Constants.CIRCLE_SPEED);
			}else if(e.getKeyChar() == 'q') {//w
				this.canvas.world.getLightDot().setAngle(this.canvas.world.getLightDot().getAngle() + Constants.CIRCLE_SPEED);
			}
		
			this.canvas.repaintCanvas();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public void getCoordinades(Insets insets, MouseEvent e) {
		this.canvas.world.getLightDot().setPoint(new Point(e.getX() - insets.left, e.getY()-insets.top));
		this.canvas.world.getLightDot().setLocation(new Point(e.getX() - insets.left, e.getY()-insets.top));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.canvas.searchType ++;
		
		if(this.canvas.searchType == 3) {
			this.canvas.searchType = 0;
		}
		this.canvas.repaintCanvas();
		
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

}
