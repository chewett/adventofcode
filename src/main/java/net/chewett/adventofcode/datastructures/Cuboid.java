package net.chewett.adventofcode.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Cuboid {

    private int x;
    private int x2;
    private int y;
    private int y2;
    private int z;
    private int z2;

    public Cuboid(int x, int x2, int y, int y2, int z, int z2) {
        this.x = x;
        this.x2 = x2;
        this.y = y;
        this.y2 = y2;
        this.z = z;
        this.z2 = z2;
    }

    public boolean intersects(Cuboid c) {
        //Intersection is that
        //For every axis (X, Y, Z)
        //  - MinX (defined as x) is less than or equal to Max X of C (defined as x2)
        //  - MinX of C (defined as x) is less than or equal to Max X (defined as x2)

        return (this.x <= c.x2 && c.x <= this.x2) && (this.y <= c.y2 && c.y <= this.y2) && (this.z <= c.z2 && c.z <= this.z2);
    }

    public long getContainedArea() {
        return (1L+ this.x2 - this.x) * (1L+ this.y2 - this.y) * (1L+ this.z2 - this.z);
    }

    /**
     * Given a cuboid, this removes the sections from this cuboid and returns a list of "new cubes"
     * @param newCube Cuboid to remove from this cuboid
     * @return List of cuboids which are the resultant area after removing the given cuboid (max six)
     */
    public List<Cuboid> removeCuboid(Cuboid newCube) {

        //Go through each planar axis and work out how the new part splits up the older cube
        //Replace the older cube with a new "smaller" one each time it intersects
        //Keep track of these new cube parts and return them
        List<Cuboid> newSetOfCubes = new ArrayList<>();

        //Go through each "planer difference" to determine if this new cube chops it, if it does then we chop and
        //Reduce the cube by the ne planer difference

        // X Axis cross-section
        if (this.x <= newCube.x2 && newCube.x2 <= this.x2) {
            newSetOfCubes.add(new Cuboid(newCube.x2 + 1, this.x2, this.y, this.y2, this.z, this.z2));
            this.x2 = newCube.x2;
        }
        if (this.x <= newCube.x && newCube.x <= this.x2) {
            newSetOfCubes.add(new Cuboid(this.x, newCube.x - 1, this.y, this.y2, this.z, this.z2));
            this.x = newCube.x;
        }

        // Y Axis cross-section
        if (this.y <= newCube.y2 && newCube.y2 <= this.y2) {
            newSetOfCubes.add(new Cuboid(this.x, this.x2, newCube.y2 + 1, this.y2, this.z, this.z2));
            this.y2 = newCube.y2;
        }
        if (this.y <= newCube.y && newCube.y <= this.y2) {
            newSetOfCubes.add(new Cuboid(this.x, this.x2, this.y, newCube.y - 1, this.z, this.z2));
            this.y = newCube.y;
        }

        // Z Axis cross-section
        if (this.z <= newCube.z2 && newCube.z2 <= this.z2) {
            newSetOfCubes.add(new Cuboid(this.x, this.x2, this.y, this.y2, newCube.z2 + 1, this.z2));
            this.z2 = newCube.z2;
        }

        if (this.z <= newCube.z && newCube.z <= this.z2) {
            newSetOfCubes.add(new Cuboid(this.x, this.x2, this.y, this.y2, this.z, newCube.z - 1));
            this.z = newCube.z;
        }

        return newSetOfCubes;
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "x=" + x +
                ", x2=" + x2 +
                ", y=" + y +
                ", y2=" + y2 +
                ", z=" + z +
                ", z2=" + z2 +
                '}';
    }
}
