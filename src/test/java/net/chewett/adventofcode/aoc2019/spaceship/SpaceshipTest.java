package net.chewett.adventofcode.aoc2019.spaceship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpaceshipTest {

    @Test
    public void noModulesTest() {
        Spaceship emptyShip = new Spaceship();
        Assertions.assertEquals(0, emptyShip.getFuelForModules());
    }

    @Test
    public void singleModuleTest() {
        Spaceship emptyShip = new Spaceship();
        emptyShip.addModule(new SpaceshipModule(12));
        Assertions.assertEquals(2, emptyShip.getFuelForModules());
    }

    @Test
    public void singleModuleTestTwo() {
        Spaceship emptyShip = new Spaceship();
        emptyShip.addModule(new SpaceshipModule(14));
        Assertions.assertEquals(2, emptyShip.getFuelForModules());
    }

    @Test
    public void twoModuleTest() {
        Spaceship emptyShip = new Spaceship();
        emptyShip.addModule(new SpaceshipModule(12));
        emptyShip.addModule(new SpaceshipModule(12));
        Assertions.assertEquals(4, emptyShip.getFuelForModules());
    }


}
