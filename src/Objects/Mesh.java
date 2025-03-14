package Objects;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import Maths.CalcView;

public abstract class Mesh {
    public Triangle[] mesh;
    public Color[] colors = {
            Color.CYAN,
            Color.GREEN,
            Color.ORANGE,
            Color.PINK,
            Color.MAGENTA,
            Color.BLUE,
            Color.WHITE,
            Color.DARK_GRAY,
            Color.GRAY,
            Color.LIGHT_GRAY,
            Color.RED,
            Color.YELLOW
    };

    // The constructor is basically the same for all the meshes
    // But in case you want to change something about a specific mesh there are still the classes for each one
    public Mesh(Vector pos) {
        readData("/src/Resources/Mesh/" + this.getClass().getSimpleName() + ".txt", 100);
        //printVectors();
        correctPosition(pos);
    }

    // This reads the data of a .txt and sets a mesh with all the triangles needed
    // The file needs to be in the right format, as I didn't calculate for wrong inputs yet
    public void readData(String path, int scale) {
        ArrayList<Triangle> meshArray = new ArrayList<>();

        try {
            String filePath = new File("").getAbsolutePath();
            Scanner sc = new Scanner(new FileReader(filePath + path));

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("")) continue;

                String[] vectors = line.split(",");
                Vector[] vs = new Vector[3];

                for (int i = 0; i < 3; i++) {
                    String[] cords = vectors[i].split(" ");

                    Vector v = new Vector(
                            Integer.valueOf(cords[0]) * scale, Integer.valueOf(cords[1]) * scale, Integer.valueOf(cords[2]) * scale);
                    vs[i] = v;
                }

                meshArray.add(new Triangle(vs, colors[Integer.valueOf(vectors[3])]));
            }

            sc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mesh = meshArray.toArray(new Triangle[meshArray.size()]);
    }

    // Sets the position of the object in the world
    public void correctPosition(Vector v) {
        if (v != null) {
            for (int i = 0; i < mesh.length; i++)
                for (int j = 0; j < mesh[i].vectors.length; j++)
                    mesh[i].vectors[j] = CalcView.moveVecByVec(mesh[i].vectors[j], v);
        }
    }

    // This is mostly for debugging in case I need to check the position
    // I left it inside for when I need to change something
    public void printVectors() {
        for (int i = 0; i < mesh.length; i++) {
            System.out.println("Triangle: " + i);

            for (int j = 0; j < 3; j++) {
                System.out.println(
                        "XYZ: " + mesh[i].vectors[j].vec[0] + " " + mesh[i].vectors[j].vec[1] + " " + mesh[i].vectors[j].vec[2]);
            }

            System.out.println(" ");
        }
    }
}
