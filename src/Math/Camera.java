package Math;

public class Camera {
    public int screenWidth = 690;
    public int screenHeight = 690;

    public float screenRatio = screenWidth / screenHeight; // = r

    public float fNear = 0.1f;
    public float fFar = 1000;
    public float fFov = 90;
    public float fFovRad = (float) (1 / Math.tan(fFov * 0.5 / 180 * Math.PI));

    public byte fowDeg;
    public byte distCam;

    // Based on javidx9 on YouTube
    // Field of view, f: 1 / tan (r/2)
    // q: dFar / (dFar - dNear)
    // x: (rfx) / z
    // y: (fy) / z
    // z: zq - znq

    public double dNear; // 1 for example
    public double dFar; // 10 for example

    public int depthZ; // (-dFar * dNear) / (dFar - dNear)
    public int maxDepth; // dFar / (dFar - dNear)
}
