package net.chewett.adventofcode.datastructures;

/**
 * Rough class modelled after awt Point for use with discrete 3d points
 */
public class Point3D {

    private int x;
    private int y;
    private int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D point) {
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
    }

    /**
     * Gets the X value stored in this point
     * @return X value
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Y value stored in this point
     * @return Y value
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the Z value stored in this point
     * @return Z value
     */
    public int getZ() {
        return this.z;
    }
}
