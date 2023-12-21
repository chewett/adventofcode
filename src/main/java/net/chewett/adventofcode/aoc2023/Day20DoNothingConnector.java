package net.chewett.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple object to represent sending data to a "nothing module" which does nothing.
 * This simplifies the handling since we don't have to special case these modules we just create a "fake" one
 */
public class Day20DoNothingConnector extends Day20Connector {

    public Day20DoNothingConnector(String name) {
        super(name, new String[0]);
    }

    public List<String[]> pulseHigh(Map<String, Day20Connector> connectors, String from) {
        return new ArrayList<>();
    }

    public List<String[]> pulseLow(Map<String, Day20Connector> connectors, String from) {
        return new ArrayList<>();
    }

}
