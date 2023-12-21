package net.chewett.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The second most complex connector, a standard flip flop keeping track of the state
 */
public class Day20FlipFlop extends Day20Connector {

    private boolean stateOn = false;

    public Day20FlipFlop(String name, String[] outputs) {
        super(name, outputs);
    }

    public List<String[]> pulseHigh(Map<String, Day20Connector> connectors, String from) {
        //For high pulses we do nothing
        return new ArrayList<String[]>();
    }

    public List<String[]> pulseLow(Map<String, Day20Connector> connectors, String from) {
        //For a low pulse, flip the state and then send either a high or low depending on it
        this.stateOn = !this.stateOn;

        List<String[]> newPulses = new ArrayList<>();
        for (String output : this.outputs) {

            if (this.stateOn) {
                newPulses.add(new String[] {this.name, output, "high"});
            } else {
                newPulses.add(new String[] {this.name, output, "low"});
            }
        }

        return newPulses;
    }

    public String toString() {
        String onStateString = this.stateOn ? "ON" : "OFF";
        return this.name + " FlipFlop(" + onStateString +" )";
    }

}
