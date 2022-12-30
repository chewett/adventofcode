package net.chewett.adventofcode.datastructures;

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
        return false;

    }

    public long getContainedArea() {
        return (1+ this.x2 - this.x) * (1+ this.y2 - this.y) * (1+ this.z2 - this.z);
    }



}
