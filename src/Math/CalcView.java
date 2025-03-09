package Math;

import GUI.RenderPanel;
import Objects.Forms.Cube;
import Objects.Triangle;
import Objects.Vector;

public class CalcView implements Runnable {
    public Thread thread;
    public RenderPanel rp;
    public Camera cam;

    public Cube cube;

    public CalcView(RenderPanel rp) {
        this.rp = rp;
        cam = new Camera();
        loadObjects();
    }

    public void loadObjects() {
        cube = new Cube();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public Triangle[] updateMesh(double time) {
        Triangle[] m = cube.mesh;
        Triangle[] nm = new Triangle[m.length];

        for (int i = 0; i < 4; i++){}

        for (int i = 0; i < m.length; i++) {
            Triangle t = new Triangle(new Vector[3]);
            Triangle tRX = new Triangle(new Vector[3]);

            for (int j = 0; j < tRX.vectors.length; j++) {
                Vector v = cam.mProjToVec(m[i].vectors[j], cam.projMatrix);
                tRX.vectors[j] = cam.moveVecByVec(v, cam.posCam);
            }

            for (int j = 0; j < t.vectors.length; j++) {
                Rotate r = new Rotate();
                r.calcRotation(time);
                t.vectors[j] = cam.mProjToVec(tRX.vectors[j], r.rX);
            }

            nm[i] = t;
        }

        return nm;
    }

    @Override
    public void run() {
        int tick = 60;
        double lastTick = System.currentTimeMillis();

        int fps = 0;
        double lastHalfSec = System.currentTimeMillis();

        while (thread != null) {
            if (lastTick + 1000 / tick <= System.currentTimeMillis()) {
                // TODO, calculate here

                fps++;
                if (lastHalfSec + 500 <= System.currentTimeMillis()) {
                    rp.fps = "FPS: " + (fps * 2);

                    fps = 0;
                    lastHalfSec = System.currentTimeMillis();
                }

                rp.meshes = updateMesh(System.nanoTime() / 1000000);
                rp.repaint();

                lastTick = System.currentTimeMillis();
            }
        }
    }
}
