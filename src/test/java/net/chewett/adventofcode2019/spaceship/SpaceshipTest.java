package net.chewett.adventofcode2019.spaceship;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTest {

    @Test
    public void noModulesTest() {
        Spaceship emptyShip = new Spaceship();
        Assert.assertEquals(0, emptyShip.getFuelForModules());
    }

    @Test
    public void singleModuleTest() {
        Spaceship emptyShip = new Spaceship();
        emptyShip.addModule(new SpaceshipModule(12));
        Assert.assertEquals(2, emptyShip.getFuelForModules());
    }

    @Test
    public void singleModuleTestTwo() {
        Spaceship emptyShip = new Spaceship();
        emptyShip.addModule(new SpaceshipModule(14));
        Assert.assertEquals(2, emptyShip.getFuelForModules());
    }

    @Test
    public void twoModuleTest() {
        Spaceship emptyShip = new Spaceship();
        emptyShip.addModule(new SpaceshipModule(12));
        emptyShip.addModule(new SpaceshipModule(12));
        Assert.assertEquals(4, emptyShip.getFuelForModules());
    }


}
