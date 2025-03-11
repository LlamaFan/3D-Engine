package Objects;

public class Vector {
    public double[] vec;

    public Vector(double x, double y, double z) {
        vec = new double[3];

        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
    }
}
