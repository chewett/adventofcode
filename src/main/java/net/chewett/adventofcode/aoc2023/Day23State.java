package net.chewett.adventofcode.aoc2023;

import java.awt.*;
import java.util.Set;

/**
 * Simple state object holding the cost, current position, and the set of visited points
 */
public class Day23State {

    private int cost;
    private Point cur;
    private Set<Point> visited;

    public Day23State(int cost, Point cur, Set<Point> visited) {
        this.cost = cost;
        this.cur = cur;
        this.visited = visited;
    }

    public int getCost() {
        return cost;
    }

    public Point getCur() {
        return cur;
    }

    public Set<Point> getVisited() {
        return visited;
    }
}
