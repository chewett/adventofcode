package net.chewett.adventofcode.datastructures;

public class Cuboid {

    private int x;
    private int x2;
    private int y;
    private int y2;
    private int z;
    private int z2;

    public Cuboid(int x, int x2, int y, int y2, int z, int z2) {
        //Min/max forces an ordering in the event this cuboid is oddly dimensioned
        this.x = Math.min(x, x2);
        this.x2 = Math.max(x, x2);
        this.y = Math.min(y, y2);
        this.y2 = Math.max(y, y2);
        this.z = Math.min(z, z2);
        this.z2 =Math.max(z, z2);
    }

    public boolean intersects(Cuboid c) {
        //Insection is that
        //For every axis (X, Y, Z)
        //  - MinX (defined as x) is less than or equal to Max X of C (defined as x2)
        //  - MinX of C (defined as x) is less than or equal to Max X (defined as x2)

        return (this.x <= c.x2 && c.x <= this.x2) && (this.y <= c.y2 && c.y <= this.y2) && (this.z <= c.z2 && c.z <= this.z2);
    }

    public long getContainedArea() {
        return (1+ this.x2 - this.x) * (1+ this.y2 - this.y) * (1+ this.z2 - this.z);
    }



}
