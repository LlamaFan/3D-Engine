package Math;

public class Rotate {
    public float[][] rX;
    public float[][] rY;
    public float[][] rZ;

    public void calcRotation(double time) {
        rX = new float[][]{
                {1, 0, 0, 0},
                {0, (float) Math.cos(time), (float) -Math.sin(time), 0},
                {0, (float) Math.sin(time), (float) Math.cos(time), 0},
                {0, 0, 0, 1}
        };
    }
}
