package net.chewett.adventofcode;

import java.awt.Point;

/**
 * Simple helper class to handle directions generally
 */
public class Directions {

    public static final int NORTH = 1;
    public static final int UP = 1;
    public static final int EAST = 2;
    public static final int RIGHT = 2;
    public static final int SOUTH = 3;
    public static final int DOWN =3;
    public static final int WEST = 4;
    public static final int LEFT = 4;


    /**
     * Gets the direction left (90 degrees anticlockwise) from this
     * @param currentDirection Current direction
     * @return Direction left of this one (90 degrees anticlockwise)
     */
    public static int getDirectionLeftOfThis(int currentDirection) {
        if(currentDirection == Directions.SOUTH) {
            return Directions.EAST;
        }else if(currentDirection == Directions.NORTH) {
            return Directions.WEST;
        }else if(currentDirection == Directions.WEST) {
            return Directions.SOUTH;
        }else if(currentDirection == Directions.EAST) {
            return Directions.NORTH;
        }

        throw new RuntimeException("Bad parameter passed to getDirectionLeftOfThis");
    }

    /**
     * Gets the direction right (90 degrees clockwise) from this
     * @param currentDirection Current direction
     * @return Direction right of this one (90 degrees clockwise)
     */
    public static int getDirectionRightOfThis(int currentDirection) {
        if(currentDirection == Directions.SOUTH) {
            return Directions.WEST;
        }else if(currentDirection == Directions.NORTH) {
            return Directions.EAST;
        }else if(currentDirection == Directions.WEST) {
            return Directions.NORTH;
        }else if(currentDirection == Directions.EAST) {
            return Directions.SOUTH;
        }

        throw new RuntimeException("Bad parameter passed to getDirectionRightOfThis");
    }

    /**
     * Get a point representing the x/y change if you want to move in that direction
     * @param direction Int representing the direction
     * @return Point representing the x/y change to move in that direction
     */
    public static Point getDirectionModifier(int direction) {
        if(direction == Directions.SOUTH) {
            return new Point(0, 1);
        }else if(direction == Directions.NORTH) {
            return new Point(0, -1);
        }else if(direction == Directions.WEST) {
            return new Point(-1, 0);
        }else if(direction == Directions.EAST) {
            return new Point(1, 0);
        }

        throw new RuntimeException("Bad parameter passed to getDirectionModifier");
    }

    /**
     * Given a character representing a move (either > < ^ v ) return a point modifier
     * @param c Character representing a move (must be one of > < ^ v )
     * @return Direction modifier as a Point
     */
    public static Point getDirectionModifierFromChar(char c) {
        if(c == '^') {
            return Directions.getDirectionModifier(Directions.NORTH);
        }else if(c == 'v') {
            return Directions.getDirectionModifier(Directions.SOUTH);
        }else if(c == '>') {
            return Directions.getDirectionModifier(Directions.EAST);
        }else if(c == '<') {
            return Directions.getDirectionModifier(Directions.WEST);
        }else{
            throw new RuntimeException("Unknown direction character " + c);
        }
    }

    /**
     * Starting from P2, work out which cardinal direction this is from
     * Assumes the points are one cardinal direction from each other
     * @param p
     * @param p2
     * @return
     */
    public static int getDirectionFromPointToPoint(Point p, Point p2) {
        if(p.y < p2.y) {
            return Directions.SOUTH;
        }else if(p.y > p2.y) {
            return Directions.NORTH;
        }else if(p.x < p2.x) {
            return Directions.EAST;
        }else if(p.x > p2.x) {
            return Directions.WEST;
        }else{
            throw new RuntimeException("Unexpected input of points...");
        }
    }

    /**
     * Helper function to work out how many turns you need to go from one direction to another
     * @param direction1 Direction ID
     * @param direction2 Direction ID
     * @return Number of 90 degree turns needed to move from one direction to another
     */
    public static int getTurnsNeededToMoveFromDirectionToDirection(int direction1, int direction2) {
        if(direction1 == direction2) {
            return 0;
        }

        if(direction1 == Directions.NORTH) {
            if(direction2 == Directions.SOUTH) { return 2; }else{ return 1; }
        }else if(direction1 == Directions.SOUTH) {
            if(direction2 == Directions.NORTH) { return 2; }else{ return 1; }
        }else if(direction1 == Directions.EAST) {
            if(direction2 == Directions.WEST) { return 2; }else{ return 1; }
        }else if(direction1 == Directions.WEST) {
            if(direction2 == Directions.EAST) { return 2; }else{ return 1; }
        }else{
            throw new RuntimeException("Unexpected input of direction...");
        }
    }



}
