package net.chewett.adventofcode.aoc2023;

import java.util.List;
import java.util.Map;

/**
 * This abstract class allows me to treat all of the connectors the same and they override the handling for
 * high and low pulses
 */
public abstract class Day20Connector {

    public String name;
    public String[] outputs;

    public Day20Connector(String name, String[] outputs) {
        this.name = name;
        this.outputs = outputs;
    }

    /**
     * Runs the pulse high and return a list of new pulses
     * @param connectors Details of all the connectors
     * @param from Where the pulse came from
     * @return List of new generated pulses
     */
    public abstract List<String[]> pulseHigh(Map<String, Day20Connector> connectors, String from);

    /**
     * Runs the pulse low and return a list of new pulses
     * @param connectors Details of all the connectors
     * @param from Where the pulse came from
     * @return List of new generated pulses
     */
    public abstract List<String[]> pulseLow(Map<String, Day20Connector> connectors, String from);

    /**
     * Return a list of outputs from this
     * @return Array of outputs as strings
     */
    public String[] getOutputs() {
        return this.outputs;
    }

}
