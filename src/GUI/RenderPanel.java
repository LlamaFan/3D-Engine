package GUI;

import javax.swing.*;
import java.awt.*;

import Maths.CalcView;
import Objects.Triangle;

public class RenderPanel extends JPanel {
    public int pSizeX = 690;
    public int pSizeY = 690;

    public String fps;
    public Triangle[] meshes;

    // Just the constructor
    public RenderPanel() {
        setPreferredSize(new Dimension(pSizeX, pSizeY));
        setBackground(Color.BLACK);

        fps = new String("FPS: 0");
    }

    // The logic for the repaint of the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Paints the panel black after each new draw
        g2.setColor(Color.black);
        g2.drawRect(0, 0, pSizeX, pSizeY);

        // Updates the fps
        g2.setColor(Color.white);
        updateFPS(g2);

        // This updates all the meshes
        if (meshes != null) {
            for (int i = 0; i < meshes.length; i++) drawTriangle(g, meshes[i]);
        }

        g.dispose();
    }

    private void updateFPS(Graphics2D g2) {
        g2.drawString(fps, 10, 10);
    }

    // Draws the meshes by a singular triangle each time
    public void drawTriangle(Graphics g, Triangle t) {
        Polygon drawnTriangle = new Polygon();

        drawnTriangle.addPoint((int) t.vectors[0].vec[0], (int) t.vectors[0].vec[1]);
        drawnTriangle.addPoint((int) t.vectors[1].vec[0], (int) t.vectors[1].vec[1]);
        drawnTriangle.addPoint((int) t.vectors[2].vec[0], (int) t.vectors[2].vec[1]);

        g.setColor(t.color);
        g.fillPolygon(drawnTriangle);


        /*g.setColor(Color.white);

        //CalcView.printVectors(new Triangle[]{t});
        g.drawLine((int) t.vectors[0].vec[0], (int) t.vectors[0].vec[1], (int) t.vectors[1].vec[0], (int) t.vectors[1].vec[1]);
        g.drawLine((int) t.vectors[1].vec[0], (int) t.vectors[1].vec[1], (int) t.vectors[2].vec[0], (int) t.vectors[2].vec[1]);
        g.drawLine((int) t.vectors[2].vec[0], (int) t.vectors[2].vec[1], (int) t.vectors[0].vec[0], (int) t.vectors[0].vec[1]);*/
    }
}
