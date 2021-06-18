package net.chewett.adventofcode.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class Point3DTest {

    @Test
    public void testConstructorWithInts() {
        Point3D p = new Point3D(1, 2, 3);
        Assert.assertNotNull(p);
    }

    @Test
    public void testConstructorWithPoint() {
        Point3D p = new Point3D(1, 2, 3);
        Assert.assertNotNull(p);
        Point3D p2 = new Point3D(p);
        Assert.assertNotNull(p2);
    }

    @Test
    public void testGetX() {
        Point3D p = new Point3D(1, 2, 3);
        Assert.assertEquals(1, p.getX());
    }

    @Test
    public void testGetY() {
        Point3D p = new Point3D(1, 2, 3);
        Assert.assertEquals(2, p.getY());
    }

    @Test
    public void testGetZ() {
        Point3D p = new Point3D(1, 2, 3);
        Assert.assertEquals(3, p.getZ());
    }


}
