import GUI.RenderPanel;
import GUI.Window;

public class Main {
    public static RenderPanel rp;
    public static Run run;

    public static void main(String[] args) {
        // Sets the GUI
        rp = new RenderPanel();
        new Window(rp);

        // This has all the logic of the program, because I don't like to code in the main class
        run = new Run(rp);
        run.start();
    }
}