package Objects;

public class Face {
    public double x;
    public double y;
    public double z;

    public int width;
    public int height;

    public double rotHor;
    public double rotVer;

    public Face(int pX, int pY, int pZ, int pRotHor, int pRotVer, int pWidth, int pHeight) {
        x = pX;
        y = pY;
        z = pZ;

        rotHor = pRotHor;
        rotVer = pRotVer;
    }

    public Face(int pX, int pY, int pZ, int pWidth, int pHeight) {
        x = pX;
        y = pY;
        z = pZ;

        width = pWidth;
        height = pHeight;
    }
}
