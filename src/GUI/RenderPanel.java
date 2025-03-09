package GUI;

import javax.swing.*;
import java.awt.*;

import Objects.Triangle;

public class RenderPanel extends JPanel {
    public int pSizeX = 690;
    public int pSizeY = 690;

    public String fps;
    public Triangle[] meshes;

    public RenderPanel() {
        setPreferredSize(new Dimension(pSizeX, pSizeY));
        setBackground(Color.BLACK);

        fps = new String("FPS: 0");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.drawRect(0, 0, pSizeX, pSizeY);

        g2.setColor(Color.white);
        updateFPS(g2);

        if (meshes != null) {
            for (int i = 0; i < meshes.length; i++) drawTriangle(g, meshes[i]);
        }

        g.dispose();
    }

    private void updateFPS(Graphics2D g2) {
        g2.drawString(fps, 10, 10);
    }

    public void drawTriangle(Graphics g, Triangle t) {
        g.drawLine((int) t.vectors[0].vec[0], (int) t.vectors[0].vec[1], (int) t.vectors[1].vec[0], (int) t.vectors[1].vec[1]);
        g.drawLine((int) t.vectors[1].vec[0], (int) t.vectors[1].vec[1], (int) t.vectors[2].vec[0], (int) t.vectors[2].vec[1]);
        g.drawLine((int) t.vectors[2].vec[0], (int) t.vectors[2].vec[1], (int) t.vectors[0].vec[0], (int) t.vectors[0].vec[1]);
    }
}
