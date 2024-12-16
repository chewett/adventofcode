package net.chewett.adventofcode.aoc2024;

import java.awt.Point;

/**
 * Used in Day 16 2024
 *
 * Mainly to hold some stuff because Java can't just do arbitrary data chunks
 */
public class DirectionPositionCost {

    public int direction;
    public Point pos ;
    public long cost;
    public String cameFrom;

    public DirectionPositionCost(int direction, Point pos, long cost) {
        this.direction = direction;
        this.pos = pos;
        this.cost = cost;
        this.cameFrom = "";
    }

    public DirectionPositionCost(int direction, Point pos, long cost, String cameFrom) {
        this.direction = direction;
        this.pos = pos;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "DirectionPositionCost(direction=" + this.direction + ", pos=" + this.pos + ", cost=" + this.cost + ", cameFrom=" + this.cameFrom + ")";
    }


}
