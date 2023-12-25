package net.chewett.adventofcode.aoc2023;

import net.chewett.adventofcode.datastructures.OrderedPair;

import java.util.Set;

/**
 * Simple state object tracking the current state and a set of ordered pairs we have crossed
 */
public class Day25State {

    private String cur;
    private Set<OrderedPair> visited;

    public Day25State(String cur, Set<OrderedPair> visited) {
        this.cur = cur;
        this.visited = visited;
    }

    public String getCur() {
        return cur;
    }

    public Set<OrderedPair> getVisited() {
        return visited;
    }
}
