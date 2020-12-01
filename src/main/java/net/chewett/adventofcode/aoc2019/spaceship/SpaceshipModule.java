package net.chewett.adventofcode.aoc2019.spaceship;

public class SpaceshipModule {

    private int mass;

    public SpaceshipModule(int mass) {
        this.mass = mass;
    }

    private int calculateFuelForMass(int mass) {
        return (int)(Math.floor(mass / 3.0) - 2);
    }

    public int getFuelNeededToTransportModule() {
        return this.calculateFuelForMass(this.mass);
    }

    public int getFuelNeededToTransportModuleIncludingFuelMass() {
        int totalFuelCosts = this.calculateFuelForMass(this.mass);
        int newMass = totalFuelCosts;
        while(newMass > 0) {
            newMass = this.calculateFuelForMass(newMass);
            if(newMass > 0) {
                totalFuelCosts += newMass;
            }
        }

        return totalFuelCosts;
    }

}
