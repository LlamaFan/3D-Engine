package GUI;

import javax.swing.*;

public class Window extends JFrame {
    public RenderPanel rp;

    public Window(RenderPanel rp) {
        this.rp = rp;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(rp);
        pack();

        setVisible(true);
        rp.repaint();
    }

}
