package net.chewett.adventofcode.aoc2019.spaceship;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper method for Day 1 calculation of fuel weights
 * THis holds a list of spaceship modules which each need an amount of fuel to send them to space
 */
public class Spaceship {

    private List<SpaceshipModule> modules = new ArrayList<>();

    /**
     * Adds a module to the list of modules stored in the ship
     * @param module
     */
    public void addModule(SpaceshipModule module) {
        this.modules.add(module);
    }

    /**
     * Gets the fuel needed for the modules (ignoring the fuel needed for the fuel) on the ship
     * @return THe amount of fuel to get the modules to space
     */
    public long getFuelForModules() {
        long totalFuel = 0;
        for(SpaceshipModule sm : this.modules) {
            totalFuel += sm.getFuelNeededToTransportModule();
        }

        return totalFuel;
    }

    /**
     * Gets the fuel needed to actually get the modules into space, by taking into account the extra fuel needed for the
     * fuel
     * @return The amount of fuel needed to get the modules and fuel into space
     */
    public long getFuelForModulesCountingFuelMass() {
        long totalFuel = 0;
        for(SpaceshipModule sm : this.modules) {
            totalFuel += sm.getFuelNeededToTransportModuleIncludingFuelMass();
        }

        return totalFuel;
    }

}
