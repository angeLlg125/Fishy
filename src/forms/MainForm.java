package forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author angel
 */
public class MainForm extends javax.swing.JFrame {

    MyCanvas canvas = new MyCanvas();

    public MainForm() {
        initComponents();
        // Define canvas components
        canvas.setSize(600, 600);
        canvas.setBackground(Color.yellow);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);

        this.setSize(600, 600);
        Dimension dimension = new Dimension(500, 500);
        this.setMinimumSize(dimension);
        this.add(canvas);
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

}
