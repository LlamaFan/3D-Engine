package Objects;

import java.awt.*;

public class Triangle {
    public Vector[] vectors;
    public Color color;

    public Triangle(Vector[] v, Color c) {
        vectors = v;
        color = c;
    }

    public Triangle(Vector[] v) {
        vectors = v;
    }
}
