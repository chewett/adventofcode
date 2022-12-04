package net.chewett.adventofcode.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Point3DTest {

    /**
     * Test to make sure the constructor works with ints
     */
    @Test
    public void testConstructorWithInts() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertNotNull(p);
    }

    /**
     * Test to make sure that the constructor works with a point
     */
    @Test
    public void testConstructorWithPoint() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertNotNull(p);
        Point3D p2 = new Point3D(p);
        Assertions.assertNotNull(p2);
    }

    /**
     * Tests to make sure that the constructor works with a string input
     */
    @Test
    public void testConstructorWithString() {
        Point3D p = new Point3D("-10,5,1");
        Assertions.assertEquals(-10, p.getX());
        Assertions.assertEquals(5, p.getY());
        Assertions.assertEquals(1, p.getZ());
    }

    /**
     * Tests to make sure that the X getter works
     */
    @Test
    public void testGetX() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertEquals(1, p.getX());
    }

    /**
     * Tests to make sure that the Y getter works
     */
    @Test
    public void testGetY() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertEquals(2, p.getY());
    }

    /**
     * Tests to make sure that Z getter works
     */
    @Test
    public void testGetZ() {
        Point3D p = new Point3D(1, 2, 3);
        Assertions.assertEquals(3, p.getZ());
    }

    /**
     * Test to make sure getting all the 90 degree rotations works as expected
     */
    @Test
    public void testGetAll90DegreeRotations() {
        Point3D p = new Point3D(1,2,3);
        List<Point3D> allPoints = p.getAll90DegreeRotations();
        Assertions.assertEquals(24, allPoints.size());
    }

}
