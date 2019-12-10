package net.chewett.adventofcode2019;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic class that holds Asteroid data
 *
 * In addition this has a static helper function that will take in an asteroid field and produce a list
 * of asteroids in the field.
 */
public class Asteroid implements Comparable<Asteroid> {

    private int x; // x position of the asteroid
    private int y; // y position of the asteroid
    private double distanceToStation = 0; // Stores the distance to the station, this is a helper method to be used later

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gives the angler to the asteroid passed in. This angle goes from 0 representing above you to 359.999999
     * representing up and really slightly left.
     * @param a Asteroid that you wish to check the angle to
     * @return Angle that the second asteroid is to the first
     */
    public double getAngleToAsteroid(Asteroid a) {

        int xDiff = this.x - a.x;
        int yDiff = this.y - a.y;

        //This produces an angle from 270 to -90, Shift it into the region that the laser will relate to.
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        angle = (angle + 270) % 360;

        return angle;
    }

    /**
     * Simple method to work out the distance to another asteroid.
     * This uses standard pythagoras to find the answer.
     * @param a Second asteroid to use for calculcating the distance to
     * @return Distance to the second asteroid
     */
    public double getDistanceTo(Asteroid a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2) + Math.pow(a.y - this.y, 2));
    };

    /**
     * Given an array of Strings representing an asteroid field, this will look for # characters in the string and
     * create an asteroid object once per # found in the string.
     * Each string element represents a row of data. Each element in the string is the column data.
     *
     * This means that the second character in the first string is (1, 0) in (x, y) notation.
     * @param field Array of strings representing the asteroid field
     * @return A list of newly created asteroid objects.
     */
    public static List<Asteroid> parseFromField(String[] field) {
        List<Asteroid> asteroidField = new ArrayList<>();
        int y = 0;
        for(String s : field) {
            for(int x = 0; x < s.length(); x++) {
                if(s.substring(x, x+1).equals("#")) {
                    asteroidField.add(new Asteroid(x, y));
                }
            }
            y++;
        }
        return asteroidField;
    }

    /**
     * Stores the distance to the observation station. This is merely used as a helper to store this value for part
     * two of the puzzle.
     * @param d Distance to the observation station.
     */
    public void storeDistanceToStation(double d) {
        this.distanceToStation = d;
    }

    /**
     * Getter for the distance to the observation station
     * @return Distance to the observation station
     */
    public double getDistanceToStation() {
        return this.distanceToStation;
    }

    @Override
    public int compareTo(Asteroid o) {
        //The compareTo() function uses the distance to the station for the second part of the puzzle
        return (int)(this.getDistanceToStation() - o.getDistanceToStation());
    }

    /**
     * The asteroid number is used to denote an individual asteroid within a field using one number.
     * The calculation allows you to specifically identify the asteroid without using two numbers.
     *
     * @return The given asteroids number.
     */
    public int getAsteroidNumber() {
        return (this.x * 100) + this.y;
    }
}
