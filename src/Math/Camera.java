package Math;

import Objects.Vector;

public class Camera {
    public float[][] projMatrix;

    public Vector posCam;

    public int sWidth = 690;
    public int sHeight = 690;

    public float r = sWidth / sHeight; // = r

    public float dNear = 0.01f;
    public float dFar = 1000;
    public float fov = 90;
    public float fovRad = (float) (1 / Math.tan(fov * 0.5 / 180 * Math.PI));

    public Camera() {
        posCam = new Vector(sWidth / 2, sHeight / 2, 0);

        setProjection();
    }

    public void setProjection() {
        projMatrix = new float[4][4];

        projMatrix[0][0] = (float) (1 / (r * Math.tan(fov / 2)));
        projMatrix[1][1] = (float) (1 / Math.tan(fov / 2));
        projMatrix[2][2] = dFar / (dFar - dNear);
        projMatrix[2][3] = -(dFar * dNear) / (dFar - dNear);
        projMatrix[3][2] = 1;
    }

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

    public Vector moveVecByVec(Vector or, Vector mul) {
        Vector v = new Vector(0, 0, 0);

        for (int i = 0; i < or.vec.length; i++) v.vec[i] = or.vec[i] + mul.vec[i];

        return v;
    }
}
