import GUI.RenderPanel;
import GUI.Window;
import Math.CalcView;

public class Main {
    public static RenderPanel rp;
    public static CalcView cw;

    public static void main(String[] args) {
        rp = new RenderPanel();
        cw = new CalcView(rp);

        new Window(rp);

        cw.start();
    }
}