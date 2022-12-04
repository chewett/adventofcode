package net.chewett.adventofcode.datastructures;

import java.util.*;

/**
 * Rough class modelled after awt Point for use with discrete 3d points
 */
public class Point3D implements Comparable<Point3D> {

    private int x;
    private int y;
    private int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Simple helper to duplicate the Point3D
     * @param point
     */
    public Point3D(Point3D point) {
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
    }

    /**
     * Create a point from a string of three ints split be a comma
     * @param str String to create the Point3D from
     */
    public Point3D(String str) {
        String[] parts = str.trim().split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
        this.z = Integer.parseInt(parts[2]);
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
     * Returns the Euclidean distance between this and the passed in point
     * @param p Point to compare to
     * @return Euclidean distance between the two points
     */
    public double getEuclideanDistance(Point3D p) {
        return Math.sqrt(
            Math.pow(p.x - this.x, 2) +
            Math.pow(p.y - this.y, 2) +
            Math.pow(p.z - this.z, 2)
        );
    }

    /**
     * Gets the Manhattan distance between this point and the passed in point
     * @param p Point to compare to
     * @return Manhattan distance between the two points
     */
    public int getManhattanDistance(Point3D p) {
        return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) + Math.abs(p.z - this.z);
    }


    /**
     * Creates a new point representing the distance to the second point compared to this one
     * @param p Point to compare to
     * @return New point representing the distance between the two
     */
    public Point3D getDistanceToPoint(Point3D p) {
        return new Point3D(
            this.x - p.x,
            this.y - p.y,
            this.z - p.z
        );
    }

    /**
     * Multiple this point with the passed in point and return a new point with the resulting vector
     * @param p Point to multiply with this one
     * @return New point representing the multiplication of the two points
     */
    public Point3D multPointsTogether(Point3D p) {
        return new Point3D(
            this.x * p.x,
            this.y * p.y,
            this.z * p.z
        );
    }

    /**
     * Subtract the passed in point from this one and return a new point representing the result
     * @param p Point to subtract from this one
     * @return New Point representing the result of the subtraction
     */
    public Point3D subtract(Point3D p) {
        return new Point3D(
        this.x - p.x,
        this.y - p.y,
        this.z - p.z
        );
    }

    /**
     * Add this point to the passed in point and return a new point with the resulting value
     * @param p Point to add to this point
     * @return New point representing adding both points together
     */
    public Point3D add(Point3D p) {
        return new Point3D(
            this.x + p.x,
            this.y + p.y,
            this.z + p.z
        );
    }

    /**
     * Helper for debugging and general viewing of this point
     * @return String representing this 3D point
     */
    @Override
    public String toString() {
        return "Point3D(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ");";
    }

    /**
     * Comparison function utlising the Integer.compare() for each of the 3 units
     * @param o the object to be compared.
     * @return int representing the comparison of this and another Point3D
     */
    @Override
    public int compareTo(Point3D o) {
        if(this.x != o.x) {
            return Integer.compare(this.x, o.x);
        }else if(this.y != o.y) {
            return Integer.compare(this.y, o.y);
        }else{
            return Integer.compare(this.z, o.z);
        }
    }

    /**
     * Rotate the current point about the X axis and return the rotated Point3D as a new object
     * @return New object representing the rotation
     */
    public Point3D rotateAroundX() {
        return new Point3D(this.x, -this.z, this.y);
    }

    /**
     * Rotate the current point around the Y axis and return the rotated Point3D as a new object
     * @return New object representing the rotation
     */
    public Point3D rotateAroundY() {
        return new Point3D(-this.z, this.y, this.x);
    }

    /**
     * Rotate the current point around the Z axis and return the rotated Point3D as a new object
     * @return New object representing the rotation
     */
    public Point3D rotateAroundZ() {
        return new Point3D(this.y, -this.x, this.z);
    }

    /**
     * Slowly rotate the current Point3D and get all the possible 90 degree rotations as a map
     * @return All possible 90 degree rotations as a map
     */
    public Map<Integer, Point3D> getAll90DegreeRotationsAsMap() {
        Map<Integer, Point3D> pointList = new HashMap<>();

        int rotationIndex = 0;
        Point3D start = new Point3D(this);
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                pointList.put(rotationIndex, start);
                rotationIndex++;
                start = start.rotateAroundX();
            }
            start = start.rotateAroundY();
        }

        start = start.rotateAroundZ();
        for(int y = 0; y < 4; y++) {
            pointList.put(rotationIndex, start);
            rotationIndex++;
            start = start.rotateAroundX();
        }
        start = start.rotateAroundZ();
        start = start.rotateAroundZ();
        for(int y = 0; y < 4; y++) {
            pointList.put(rotationIndex, start);
            rotationIndex++;
            start = start.rotateAroundX();
        }

        return pointList;
    }

    /**
     * Slowly rotate the current Point3D and get all the possible 90 degree rotations as a list
     * @return
     */
    public List<Point3D> getAll90DegreeRotations() {
        List<Point3D> pointList = new ArrayList<>();
        for(Map.Entry<Integer, Point3D> e : this.getAll90DegreeRotationsAsMap().entrySet()) {
            pointList.add(e.getValue());
        }

        return pointList;
    }

    /**
     * Hash code function
     * @return Hash of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }

    /**
     * Simple equals function to determine if something is the same as this
     * Roughly it compares X, Y, and Z
     * @param obj Object to compare
     * @return True if the object is the same (numerically) otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point3D p) {
            return p.x == this.x && p.y == this.y && p.z == this.z;
        }else{
            return false;
        }
    }
}
