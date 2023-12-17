package net.chewett.adventofcode.aoc2023;

import java.awt.*;

/**
 * Simple helper function to hold details about the current position
 */
public class Day17Pos implements Comparable<Day17Pos> {

    //Define a set of directional values to make it easier to use

    public int distance;
    public Point p;
    public int direction;

    public Day17Pos(int distance, Point p, int direction) {
        this.distance = distance;
        this.p = p;
        this.direction = direction;
    }

    /**
     * Get a string representing the current "visit details" so we can keep track of what we have visited
     * @return String representing the current "visit details"
     */
    public String getVisitString() {
        return this.p.x + "_" + this.p.y + "_" + this.direction;
    }

    @Override
    public int compareTo(Day17Pos o) {
        //Only use the single comparison value to compare between the two elements
        return Integer.compare(this.distance, o.distance);
    }
}
