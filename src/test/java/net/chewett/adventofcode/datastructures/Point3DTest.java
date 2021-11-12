package net.chewett.adventofcode.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Point3DTest {

    @Test
    public void testConstructorWithInts() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertNotNull(p);
    }

    @Test
    public void testConstructorWithPoint() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertNotNull(p);
        Point3D p2 = new Point3D(p);
        Assertions.assertNotNull(p2);
    }

    @Test
    public void testGetX() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertEquals(1, p.getX());
    }

    @Test
    public void testGetY() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertEquals(2, p.getY());
    }

    @Test
    public void testGetZ() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertEquals(3, p.getZ());
    }


}
