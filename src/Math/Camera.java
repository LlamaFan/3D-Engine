package Math;

import Objects.Vector;

public class Camera {
    public float[][] projMatrix;

    public Vector posCam;

    // Screen width and height, used for the ratio
    public int sWidth = 690;
    public int sHeight = 690;

    public float r = sWidth / sHeight; // = r

    // Some settings of the camera
    public float dNear = 0.01f;
    public float dFar = 1000;
    public float fov = 90;

    public Camera() {
        // Sets the position of the camera in space
        // The starting object should be in the middle, so everything is moved by half the screen
        // The middle of the screen marks the origin (0 | 0)
        posCam = new Vector(sWidth / 2, sHeight / 2, 0);

        setProjection();
    }

    // This a perspective projection matrix, which is used to simulate the view of a camera
    // Some things here are wrong and I still need to correct them
    public void setProjection() {
        float s = (float) (1 / Math.tan((fov / 2) * (Math.PI / 180)));

        projMatrix = new float[][] {
                {s, 0, 0, 0},
                {0, s, 0, 0},
                {0, 0, - (dFar / (dFar - dNear)), -1},
                {0, 0, - ((dFar * dNear) / (dFar - dNear)), 0}
        };
    }

    // This method is used to update the meshes and their view relative to the camera
    public Vector mProjToVec(Vector i, float[][] m) {
        Vector v = new Vector(0, 0,0);

        for (int j = 0; j < 3; j++) v.vec[j] = i.vec[0] * m[0][j] + i.vec[1] * m[1][j] + i.vec[2] * m[2][j] + m[3][j];

        float w = i.vec[0] * m[0][3] + i.vec[1] * m[1][3] + i.vec[2] * m[2][3] + m[3][3];

        if (w != 0) {
            v.vec[0] /= w;
            v.vec[1] /= w;
            v.vec[2] /= w;
        }

        return v;
    }
}
