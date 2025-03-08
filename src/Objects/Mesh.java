package Objects;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Mesh {
    public Triangle[] mesh;

    public Mesh() {
        readData("/src/Resources/Mesh/" + this.getClass().getSimpleName() + ".txt");
        printVectors();
    }

    public void readData(String path) {
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

                    Vector v = new Vector(Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]));
                    vs[i] = v;
                }

                meshArray.add(new Triangle(vs));
            }

            sc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mesh = meshArray.toArray(new Triangle[meshArray.size()]);
    }

    public void printVectors() {
        for (int i = 0; i < mesh.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(
                        "XYZ: " + mesh[i].vectors[j].x + " " + mesh[i].vectors[j].y + " " + mesh[i].vectors[j].z);
            }
        }
    }
}
