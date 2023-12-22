package net.chewett.adventofcode.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CuboidTest {

    @Test
    public void testInit() {
        Cuboid c = new Cuboid(0, 10, 0, 10, 0, 10);
        Assertions.assertNotNull(c);
    }

    @Test
    public void testVolume() {
        Cuboid c = new Cuboid(0, 9, 0, 9, 0, 9);
        Assertions.assertEquals(1000, c.getContainedArea());
    }

    @Test
    public void testIntersectsFalse() {
        Cuboid c = new Cuboid(0, 10, 0, 10, 0, 10);
        Cuboid c2 = new Cuboid(15, 20, 15, 20, 15, 20);
        Assertions.assertFalse(c.intersects(c2));
    }

    @Test
    public void testIntersectsTrueOneSize() {
        Cuboid c = new Cuboid(0, 0, 1, 1, 2, 2);
        Cuboid c2 = new Cuboid(0, 0, 1, 1, 2, 2);
        Assertions.assertTrue(c.intersects(c2));
    }

    @Test
    public void testIntersectsTrueSimpleCase() {
        Cuboid c = new Cuboid(0, 10, 0, 10, 0, 10);
        Cuboid c2 = new Cuboid(0, 10, 0, 10, 0, 10);
        Assertions.assertTrue(c.intersects(c2));
    }

    @Test
    public void testIntersectsTrueEntirelyInside() {
        Cuboid c = new Cuboid(0, 10, 0, 10, 0, 10);
        Cuboid c2 = new Cuboid(5, 6, 5, 6, 5, 6);
        Assertions.assertTrue(c.intersects(c2));
    }

    @Test
    public void testIntersectsTruePartiallyInside() {
        Cuboid c = new Cuboid(0, 10, 0, 10, 0, 10);
        Cuboid c2 = new Cuboid(0, 5, 0, 5, -5, 5);
        Assertions.assertTrue(c.intersects(c2));
    }

}
