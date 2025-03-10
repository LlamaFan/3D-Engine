package GUI;

import javax.swing.*;

public class Window extends JFrame {
    public RenderPanel rp;

    public Window(RenderPanel rp) {
        this.rp = rp;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Simple-3D-Engine");

        ImageIcon icon = new ImageIcon("src/Resources/Images/Icon.png");
        setIconImage(icon.getImage());

        add(rp);
        pack();

        setVisible(true);
        rp.repaint();
    }

}
