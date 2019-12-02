package net.chewett.adventofcode2019.spaceship;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipModuleTest {

    @Test
    public void testSimple() {
        SpaceshipModule sm = new SpaceshipModule(12);
        Assert.assertEquals(2, sm.getFuelNeededToTransportModule());
    }

    @Test
    public void testSimpleTwo() {
        SpaceshipModule sm = new SpaceshipModule(14);
        Assert.assertEquals(2, sm.getFuelNeededToTransportModule());
    }

    @Test
    public void testComplex() {
        SpaceshipModule sm = new SpaceshipModule(1969);
        Assert.assertEquals(654, sm.getFuelNeededToTransportModule());
    }

    @Test
    public void testComplexTwo() {
        SpaceshipModule sm = new SpaceshipModule(100756);
        Assert.assertEquals(33583, sm.getFuelNeededToTransportModule());
    }


}
