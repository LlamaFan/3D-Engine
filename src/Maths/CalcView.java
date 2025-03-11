package Maths;

import Objects.Triangle;
import Objects.Vector;

import java.util.ArrayList;

public class CalcView {
    // In order to render the scene we need to know what triangles we are looking at and also if they are visible
    public Triangle[] updateMesh(Triangle[] m, Camera cam, double time) {
        //printVectors(m);

        for (int i = 0; i < m.length; i++) {
            m[i] = rotateTriangle(m[i], time);
        }

        // This checks for the visible triangles
        m = calcCrossProduct(m);

        // This updates the mesh according to our camera
        return projectMesh(m, cam, time);
    }

    // Updates all the meshes one by one
    public static Triangle[] projectMesh(Triangle[] m, Camera cam, double time) {
        // Goes through all the meshes
        for (int i = 0; i < m.length; i++) {
            // After the object is rotated it's also moved by the camera position
            // I want to add a perspective projection matrix as well, but it doesn't work quite well yet
            for (int j = 0; j < m[i].vectors.length; j++) {
                //t.vectors[j] = cam.mProjToVec(t.vectors[j], cam.projMatrix);
                m[i].vectors[j] = moveVecByVec(m[i].vectors[j], cam.posCam);
            }
        }

        //printVectors(nm);
        return m;
    }

    // The cross product is used to determine if a mesh / triangle is visible
    // First calculation this and then only adding the visible sites is better
    public static Triangle[] calcCrossProduct(Triangle[] meshes) {
        ArrayList<Triangle> nm = new ArrayList<>();

        for (int i = 0; i < meshes.length; i++) {
            for (int j = 0; j < meshes[i].vectors.length; j++) {
                Vector[] crp = new Vector[]{
                        new Vector(0, 0, 0),
                        new Vector(0, 0, 0),
                        new Vector(0, 0, 0)
                };

                // I could have used another for loop, but the code would look weird then
                for (int k = 0; k < 3; k++) crp[0].vec[k] = meshes[1].vectors[j].vec[k] - meshes[0].vectors[j].vec[k];

                for (int k = 0; k < 3; k++) crp[1].vec[k] = meshes[2].vectors[j].vec[k] - meshes[1].vectors[j].vec[k];

                crp[2].vec[0] = crp[0].vec[1] * crp[1].vec[2] - crp[0].vec[2] * crp[1].vec[1];
                crp[2].vec[1] = crp[0].vec[2] * crp[1].vec[0] - crp[0].vec[0] * crp[1].vec[2];
                crp[2].vec[2] = crp[0].vec[0] * crp[1].vec[1] - crp[0].vec[1] * crp[1].vec[0];

                double len = Math.sqrt(
                        Math.pow(crp[2].vec[0], 2) + Math.pow(crp[2].vec[1], 2) + Math.pow(crp[2].vec[2], 2));

                //System.out.println(len);
                if (crp[2].vec[2] / len > 0) nm.add(meshes[i]);
            }
        }

        return nm.toArray(new Triangle[nm.size()]);
    }

    // It just rotates the object so it looks cooler
    public static Triangle rotateTriangle(Triangle t, double time) {
        // This first changes the triangle by the wanted rotation in the axis
        // It's needed so that we don't just see one side of the object
        for (int j = 0; j < t.vectors.length; j++) {
            Rotate r = new Rotate();
            r.calcRotation(time);

            t.vectors[j] = vecMatToVec(t.vectors[j], r.rX); // X-Axis
            t.vectors[j] = vecMatToVec(t.vectors[j], r.rY); // Y-Axis
            t.vectors[j] = vecMatToVec(t.vectors[j], r.rZ); // Z-Axis
        }

        return t;
    }

    // This method moves a vector by another vector
    // Mostly needed to move the object by the position of the camera
    public static Vector moveVecByVec(Vector or, Vector mul) {
        Vector v = new Vector(0, 0, 0);

        for (int i = 0; i < or.vec.length; i++) v.vec[i] = or.vec[i] + mul.vec[i];

        return v;
    }

    // This is just a matrix-vector multiplication, which outputs a vector
    public static Vector vecMatToVec(Vector i, double[][] m) {
        Vector v = new Vector(0, 0,0);

        for (int j = 0; j < 3; j++) v.vec[j] = i.vec[0] * m[0][j] + i.vec[1] * m[1][j] + i.vec[2] * m[2][j];

        return v;
    }

    // That's the same as in the meshes, but still only needed for debugging
    public static void printVectors(Triangle[] t) {
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
