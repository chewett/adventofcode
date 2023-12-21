package net.chewett.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The broadcaster is a simple module which just sends out a low or high pulse as it is sent to it
 */
public class Day20Broadcaster extends Day20Connector {

    public Day20Broadcaster(String name, String[] outputs) {
        super(name, outputs);
    }

    public List<String[]> pulseHigh(Map<String, Day20Connector> connectors, String from) {
        List<String[]> newPulses = new ArrayList<>();
        for (String output : this.outputs) {
            newPulses.add(new String[] {this.name, output, "high"});
        }

        return newPulses;
    }

    public List<String[]> pulseLow(Map<String, Day20Connector> connectors, String from) {
        List<String[]> newPulses = new ArrayList<>();
        for (String output : this.outputs) {
            newPulses.add(new String[] {this.name, output, "low"});
        }

        return newPulses;
    }

    public String toString() {
        return "Broadcaster()";
    }

}
