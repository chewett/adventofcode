package net.chewett.adventofcode.aoc2023;

import net.chewett.adventofcode.datastructures.OrderedPair;

/**
 * Simple object used to store a mapping of object to count/distance
 * Possibly consider templated object so I can use it generally
 * This is mostly because Java is very strongly typed, in python you would
 * just have an array of two types.
 */
public class Day25NodeVisitCount implements Comparable<Day25NodeVisitCount> {

    private OrderedPair location;
    private int count;

    public Day25NodeVisitCount(OrderedPair location, int count) {
        this.location = location;
        this.count = count;
    }

    public OrderedPair getLocation() {
        return location;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Day25NodeVisitCount o) {
        return Integer.compare(this.count, o.getCount());
    }
}
