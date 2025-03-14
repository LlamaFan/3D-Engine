package Maths;

public class Rotate {
    public double[][] rX;
    public double[][] rY;
    public double[][] rZ;

    // This is a rotation matrix, which will rotate a simple objects so every side will be visible from time to time
    // This article explains the matrices: https://en.wikipedia.org/wiki/Rotation_matrix
    public void calcRotation(double time) {
        time = Math.toRadians(time);

        // There are three axis we need can rotate independently
        // The object should move around the different axis at different speed so that is doesn't look weird
        // These matrices are also 4d, because of something with the camera, so it might look weird here
        rX = new double[][]{
                {1, 0, 0, 0},
                {0, (float) Math.cos(time / 2), (float) -Math.sin(time / 2), 0},
                {0, (float) Math.sin(time / 2), (float) Math.cos(time / 2), 0},
                {0, 0, 0, 1}
        };

        rY = new double[][] {
                {(float) Math.cos(time / 3), 0, (float) Math.sin(time / 3), 0},
                {0, 1, 0, 0},
                {(float) -Math.sin(time / 3), 0, (float) Math.cos(time / 3)},
                {0, 0, 0, 1}
        };

        rZ = new double[][] {
                {(float) Math.cos(time / 4), (float) -Math.sin(time / 4), 0},
                {(float) Math.sin(time / 4), (float) Math.cos(time / 4), 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }
}
