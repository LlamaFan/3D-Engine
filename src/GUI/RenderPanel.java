package GUI;

import javax.swing.*;
import java.awt.*;
import Math.CalcView;

public class RenderPanel extends JPanel {
    public int pSizeX = 690;
    public int pSizeY = 690;

    public String fps;

    public RenderPanel() {
        setPreferredSize(new Dimension(pSizeX, pSizeY));
        setBackground(Color.BLACK);

        fps = new String("FPS: 0");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        updateFPS(g2);

        g.dispose();
    }

    private void updateFPS(Graphics2D g2) {
        g2.drawString(fps, 10, 10);
    }
}
