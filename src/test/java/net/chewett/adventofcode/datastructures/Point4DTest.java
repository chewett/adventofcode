package net.chewett.adventofcode.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Point4DTest {

    @Test
    public void testConstructorWithInts() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assertions.assertNotNull(p);
    }

    @Test
    public void testConstructorWithPoint() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assertions.assertNotNull(p);
        Point4D p2 = new Point4D(p);
        Assertions.assertNotNull(p2);
    }

    @Test
    public void testGetX() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assertions.assertEquals(1, p.getX());
    }

    @Test
    public void testGetY() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assertions.assertEquals(2, p.getY());
    }

    @Test
    public void testGetZ() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assertions.assertEquals(3, p.getZ());
    }

    @Test
    public void testGetW() {
        Point4D p = new Point4D(1, 2, 3, 4);
        Assertions.assertEquals(4, p.getW());
    }

}
