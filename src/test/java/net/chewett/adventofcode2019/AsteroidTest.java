package net.chewett.adventofcode2019;

import org.junit.Assert;
import org.junit.Test;

public class AsteroidTest {

    @Test
    public void testAboveAngle() {
        Asteroid a1 = new Asteroid(0, 0);
        Asteroid a2 = new Asteroid(0, 1);
        Assert.assertEquals(0, a2.getAngleToAsteroid(a1), 0.1);
    }

    @Test
    public void testRightAngle() {
        Asteroid a1 = new Asteroid(1, 0);
        Asteroid a2 = new Asteroid(0, 0);
        Assert.assertEquals(90, a2.getAngleToAsteroid(a1), 0.1);
    }

    @Test
    public void testLeftAngle() {
        Asteroid a1 = new Asteroid(0, 0);
        Asteroid a2 = new Asteroid(1, 0);
        Assert.assertEquals(270, a2.getAngleToAsteroid(a1), 0.1);
    }

    @Test
    public void testBottomAngle() {
        Asteroid a1 = new Asteroid(0, 1);
        Asteroid a2 = new Asteroid(0, 0);
        Assert.assertEquals(180, a2.getAngleToAsteroid(a1), 0.1);
    }

    @Test
    public void test45Degrees() {
        Asteroid a1 = new Asteroid(1, 0);
        Asteroid a2 = new Asteroid(0, 1);
        Assert.assertEquals(45, a2.getAngleToAsteroid(a1), 0.1);
    }

    @Test
    public void test225Degrees() {
        Asteroid a1 = new Asteroid(0, 1);
        Asteroid a2 = new Asteroid(1, 0);
        Assert.assertEquals(225, a2.getAngleToAsteroid(a1), 0.1);
    }

}
