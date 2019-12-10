package net.chewett.adventofcode2019;

import java.util.ArrayList;
import java.util.List;

public class Asteroid implements Comparable<Asteroid> {

    private int x;
    private int y;
    private double distanceToStation = 0;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Angle goes from 0 representing above you to 359.999999 representing up and really slightly left.
     * @param a
     * @return angle
     */
    public double getAngleToAsteroid(Asteroid a) {

        int xDiff = this.x - a.x;
        int yDiff = this.y - a.y;

        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        angle = (angle + 270) % 360;

        return angle;
    }

    public double getDistanceTo(Asteroid a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2) + Math.pow(a.y - this.y, 2));
    };

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

    public void storeDistanceToStation(double d) {
        this.distanceToStation = d;
    }

    public double getDistanceToStation() {
        return this.distanceToStation;
    }

    @Override
    public int compareTo(Asteroid o) {
        return (int)(this.getDistanceToStation() - o.getDistanceToStation());
    }

    public int getAsteroidNumber() {
        return (this.x * 100) + this.y;
    }
}
