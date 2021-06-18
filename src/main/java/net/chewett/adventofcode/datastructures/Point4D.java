package net.chewett.adventofcode.datastructures;

/**
 * Rough class modelled after awt Point for use with discrete 4d points
 */
public class Point4D {

    private int x;
    private int y;
    private int z;
    private int w;

    public Point4D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Point4D(Point4D point) {
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
        this.w = point.getW();
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

    /**
     * Gets the W value stored in this point
     * @return W value
     */
    public int getW() {
        return this.w;
    }
}
