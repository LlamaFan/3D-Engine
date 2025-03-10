package Math;

import Objects.Triangle;
import Objects.Vector;

public class CalcView {
    // Updates all the meshes one by one
    public Triangle[] updateMesh(Triangle[] m, Camera cam, double time) {
        // I don't want to change the actual position of the object in the world, but only the displayed one
        Triangle[] nm = new Triangle[m.length];

        // Goes through all the meshes
        for (int i = 0; i < m.length; i++) {
            Triangle t = new Triangle(new Vector[3]);

            // This first changes the triangle by the wanted rotation in the axis
            // It's needed so that we don't just see one side of the object
            for (int j = 0; j < t.vectors.length; j++) {
                Rotate r = new Rotate();
                r.calcRotation(time);

                t.vectors[j] = vecMatToVec(m[i].vectors[j], r.rX); // X-Axis
                t.vectors[j] = vecMatToVec(t.vectors[j], r.rY); // Y-Axis
                t.vectors[j] = vecMatToVec(t.vectors[j], r.rZ); // Z-Axis
            }

            // After the object is rotated it's also moved by the camera position
            // I want to add a perspective projection matrix as well, but it doesn't work quite well yet
            for (int j = 0; j < t.vectors.length; j++) {
                t.vectors[j] = moveVecByVec(t.vectors[j], cam.posCam);
            }

            nm[i] = t;
        }

        //printVectors(nm);

        return nm;
    }

    // This method moves a vector by another vector
    // Mostly needed to move the object by the position of the camera
    public Vector moveVecByVec(Vector or, Vector mul) {
        Vector v = new Vector(0, 0, 0);

        for (int i = 0; i < or.vec.length; i++) v.vec[i] = or.vec[i] + mul.vec[i];

        return v;
    }

    // This is just a matrix-vector multiplication, which outputs a vector
    public Vector vecMatToVec(Vector i, float[][] m) {
        Vector v = new Vector(0, 0,0);

        for (int j = 0; j < 3; j++) v.vec[j] = i.vec[0] * m[0][j] + i.vec[1] * m[1][j] + i.vec[2] * m[2][j];

        return v;
    }

    // That's the same as in the meshes, but still only needed for debugging
    public void printVectors(Triangle[] t) {
        for (int i = 0; i < t.length; i++) {
            System.out.println("Triangle: " + i);

            for (int j = 0; j < 3; j++) {
                System.out.println(
                        "XYZ: " + t[i].vectors[j].vec[0] + " " + t[i].vectors[j].vec[1]+ " " + t[i].vectors[j].vec[2]);
            }

            System.out.println(" ");
        }
    }
}
