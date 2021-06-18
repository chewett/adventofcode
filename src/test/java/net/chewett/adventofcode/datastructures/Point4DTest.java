package net.chewett.adventofcode.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class Point4DTest {

    @Test
    public void testConstructorWithInts() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assert.assertNotNull(p);
    }

    @Test
    public void testConstructorWithPoint() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assert.assertNotNull(p);
        Point4D p2 = new Point4D(p);
        Assert.assertNotNull(p2);
    }

    @Test
    public void testGetX() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assert.assertEquals(1, p.getX());
    }

    @Test
    public void testGetY() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assert.assertEquals(2, p.getY());
    }

    @Test
    public void testGetZ() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assert.assertEquals(3, p.getZ());
    }

    @Test
    public void testGetW() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assert.assertEquals(4, p.getW());
    }

}
