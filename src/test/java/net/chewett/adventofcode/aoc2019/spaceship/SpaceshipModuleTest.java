package net.chewett.adventofcode.aoc2019.spaceship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpaceshipModuleTest {

    @Test
    public void testSimple() {
        SpaceshipModule sm = new SpaceshipModule(12);
        Assertions.assertEquals(2, sm.getFuelNeededToTransportModule());
    }

    @Test
    public void testSimpleTwo() {
        SpaceshipModule sm = new SpaceshipModule(14);
        Assertions.assertEquals(2, sm.getFuelNeededToTransportModule());
    }

    @Test
    public void testComplex() {
        SpaceshipModule sm = new SpaceshipModule(1969);
        Assertions.assertEquals(654, sm.getFuelNeededToTransportModule());
    }

    @Test
    public void testComplexTwo() {
        SpaceshipModule sm = new SpaceshipModule(100756);
        Assertions.assertEquals(33583, sm.getFuelNeededToTransportModule());
    }


}
