package Math;

import GUI.RenderPanel;

public class CalcView implements Runnable {
    public Thread thread;
    public RenderPanel rp;

    public CalcView(RenderPanel rp) {
        this.rp = rp;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int tick = 10;
        double lastTick = System.currentTimeMillis();

        int fps = 0;
        double lastHalfSec = System.currentTimeMillis();

        while (thread != null) {
            if (lastTick + 1000 / tick <= System.currentTimeMillis()) {
                // TODO, calculate here

                fps++;
                if (lastHalfSec + 500 <= System.currentTimeMillis()) {
                    rp.fps = "FPS: " + (fps * 2);
                    rp.repaint();

                    fps = 0;
                    lastHalfSec = System.currentTimeMillis();
                }

                lastTick = System.currentTimeMillis();
            }
        }
    }
}
