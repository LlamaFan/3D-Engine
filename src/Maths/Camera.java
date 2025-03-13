package Maths;

import Objects.Vector;

public class Camera {
    public double[][] projMatrix;

    public Vector posCam;

    // Screen width and height, used for the ratio
    public int sWidth = 690;
    public int sHeight = 690;

    public double r = sWidth / sHeight; // = r

    // Some settings of the camera
    public double dNear = 0.01;
    public double dFar = 1;
    public double fov = 120;

    public Camera() {
        // Sets the position of the camera in space
        // The starting object should be in the middle, so everything is moved by half the screen
        // The middle of the screen marks the origin (0 | 0)
        posCam = new Vector(sWidth / 2, sHeight / 2, -1000);

        setProjection();
    }

    // This a perspective projection matrix, which is used to simulate the view of a camera
    // Some things here are wrong and I still need to correct them
    public void setProjection() {
        double s = (1 / Math.tan(Math.toRadians(fov / 2)));
        double range = dNear - dFar;

        projMatrix = new double[][] {
                {1 / (s * r), 0, 0, 0},
                {0, 1 / s, 0, 0},
                {0, 0, -range / range, 2 * dFar * dNear / range},
                {0, 0, -1, 0}
        };
    }

    // This method is used to update the meshes and their view relative to the camera
    public Vector mProjToVec(Vector i, double[][] m) {
        Vector v = new Vector(0, 0,0);

        for (int j = 0; j < 3; j++) v.vec[j] = i.vec[0] * m[0][j] + i.vec[1] * m[1][j] + i.vec[2] * m[2][j] + m[3][j];

        double w = i.vec[0] * m[0][3] + i.vec[1] * m[1][3] + i.vec[2] * m[2][3] + m[3][3];

        if (w != 0) {
            v.vec[0] /= w;
            v.vec[1] /= w;
            v.vec[2] /= w;
        }

        return v;
    }
}
