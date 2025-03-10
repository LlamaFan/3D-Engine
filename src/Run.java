import GUI.RenderPanel;
import Math.CalcView;
import Math.Camera;
import Objects.Forms.Cube;
import Objects.Forms.Tetraeda;
import Objects.Triangle;

public class Run implements Runnable {
    public CalcView calcView;
    public RenderPanel rp;
    public Camera camera;

    public Triangle[] meshes;
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
        tetraeda = new Tetraeda();
        //cube = new Cube();
        meshes = tetraeda.mesh;
    }

    // This just starts the thread
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    // The thread handles the basic logic of the program and also updates the screen
    @Override
    public void run() {
        // The maximum amount of updates per second
        // It shouldn't be set over 1000, as I use currentTimeMillis() (More is not needed)
        int tick = 100;
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
                rp.meshes = calcView.updateMesh(meshes, camera, System.currentTimeMillis() / 10);
                rp.repaint(); // Updates the graphics

                lastTick = System.currentTimeMillis();
            }
        }
    }
}
