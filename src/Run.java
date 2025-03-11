import GUI.RenderPanel;
import Maths.CalcView;
import Maths.Camera;
import Objects.Forms.Cube;
import Objects.Forms.Tetraeda;
import Objects.Mesh;
import Objects.Triangle;
import Objects.Vector;

import java.util.ArrayList;

public class Run implements Runnable {
    public CalcView calcView;
    public RenderPanel rp;
    public Camera camera;

    // All the objects in the world
    public ArrayList<Mesh> meshes;
    public Tetraeda tetraeda;
    public Cube cube;

    public Thread thread;

    // This is just the constructor
    public Run(RenderPanel rp) {
        camera = new Camera();
        calcView = new CalcView();
        this.rp = rp;

        loadObjects();
    }

    // All the objects need to be set first
    public void loadObjects() {
        meshes = new ArrayList<>();

        //tetraeda = new Tetraeda(new Vector(0, 0, 0));
        // Adds a basic cube at the position (0; 0; 0)
        // I did something wrong, so it can't be moved in the z-axis yet
        cube = new Cube(new Vector(0, 0, 0));
        meshes.add(cube);
    }

    // This just starts the thread
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    // This method gives back an array of all the meshes that exist to work with
    public Triangle[] getMeshes() {
        ArrayList<Triangle> t = new ArrayList<>();

        for (int i = 0; i < meshes.size(); i++) {
            for (int j = 0; j < meshes.get(i).mesh.length; j++) {
                t.add(meshes.get(i).mesh[j]);
            }
        }

        CalcView.printVectors(t.toArray(new Triangle[t.size()]));
        return t.toArray(new Triangle[t.size()]);
    }

    // The thread handles the basic logic of the program and also updates the screen
    @Override
    public void run() {
        // The maximum amount of updates per second
        // It shouldn't be set over 1000, as I use currentTimeMillis() (More is not needed)
        int tick = 10;
        double lastTick = System.currentTimeMillis();

        // Counts the current fps
        // Updates every 500 milli sec.
        int fps = 0;
        double lastHalfSec = System.currentTimeMillis();

        while (thread != null) {
            if (lastTick + 1000 / tick <= System.currentTimeMillis()) {
                fps++;

                // Just sets the fps
                if (lastHalfSec + 500 <= System.currentTimeMillis()) {
                    rp.fps = "FPS: " + (fps * 2);

                    fps = 0;
                    lastHalfSec = System.currentTimeMillis();
                }

                // Updates all the vectors and their positions
                rp.meshes = calcView.updateMesh(getMeshes(), camera, System.currentTimeMillis() / 20);
                rp.repaint(); // Updates the graphics

                lastTick = System.currentTimeMillis();
            }
        }
    }
}
