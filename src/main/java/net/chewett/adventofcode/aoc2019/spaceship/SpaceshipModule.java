package net.chewett.adventofcode.aoc2019.spaceship;

/**
 * A spaceship module to be added to a Spaceship for Day 1 2019. This is used to calculate the fuel requirements
 */
public class SpaceshipModule {

    private long mass;

    public SpaceshipModule(long mass) {
        this.mass = mass;
    }

    /**
     * Works out the amount of fuel needed for the given mass
     * @param mass Mass to calculate for
     * @return The total fuel needed for the given mass
     */
    private long calculateFuelForMass(long mass) {
        return (long)(Math.floor(mass / 3.0) - 2);
    }

    /**
     * Gets the fuel needed to move this transport module (does not account for own fuels weight)
     * @return Fuel needed to move this transport module
     */
    public long getFuelNeededToTransportModule() {
        return this.calculateFuelForMass(this.mass);
    }

    /**
     * The correct calculation where the fuel's mass is considered iteratively and updated until we have enough fuel
     * to move the fuel
     * @return The amount of fuel needed to move the fuel and module
     */
    public long getFuelNeededToTransportModuleIncludingFuelMass() {
        long totalFuelCosts = this.calculateFuelForMass(this.mass);
        long newMass = totalFuelCosts;
        while(newMass > 0) {
            newMass = this.calculateFuelForMass(newMass);
            if(newMass > 0) {
                totalFuelCosts += newMass;
            }
        }

        return totalFuelCosts;
    }

}
