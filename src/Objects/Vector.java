package Objects;

public class Vector {
    public float[] vec;

    public Vector(float x, float y, float z) {
        vec = new float[3];

        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
    }

    public Vector(float x, float y, float z, float i) {
        vec = new float[4];

        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
        vec[3] = i;
    }
}
