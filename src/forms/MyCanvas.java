package forms;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyCanvas extends JPanel {

	public static Color backGroundColor = new Color(51, 161, 73);
	
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(backGroundColor);
        
        g.drawRect(200, 200, 300, 300);
    }
}
