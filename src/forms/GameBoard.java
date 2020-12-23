package forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import utils.Constants;

/**
 *
 * @author angel
 */
public class GameBoard extends javax.swing.JFrame implements KeyListener{

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
        
        this.addKeyListener(this);
    }

    @SuppressWarnings("unchecked")
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
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getExtendedKeyCode());
		this.canvas.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
