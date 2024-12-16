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


}
