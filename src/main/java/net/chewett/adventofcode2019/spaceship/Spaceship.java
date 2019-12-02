package net.chewett.adventofcode2019.spaceship;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {

    private List<SpaceshipModule> modules = new ArrayList<>();

    public void addModule(SpaceshipModule module) {
        this.modules.add(module);
    }

    public int getFuelForModules() {
        int totalFuel = 0;
        for(SpaceshipModule sm : this.modules) {
            totalFuel += sm.getFuelNeededToTransportModule();
        }

        return totalFuel;
    }

    public int getFuelForModulesCountingFuelMass() {
        int totalFuel = 0;
        for(SpaceshipModule sm : this.modules) {
            totalFuel += sm.getFuelNeededToTransportModuleIncludingFuelMass();
        }

        return totalFuel;
    }

}
