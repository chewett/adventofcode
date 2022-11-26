package net.chewett.adventofcode.datastructures;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple discrete grid of x/y points that allows getting/putting values at any position with a default value
 * @param <T> Type of value you want to store in the grid
 */
public class Discrete2DPositionGrid<T> {

    Map<Integer, Map<Integer, T>> positionStore = new HashMap<>();
    private final T defaultValue;
    private int maxX = 0;
    private int maxY = 0;
    private int minX = 0;
    private int minY = 0;

    public Discrete2DPositionGrid(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the value at the X and Y position or returns the default value you have chosen
     * @param x The X position you want to check the value at
     * @param y The Y position you want to check the value at
     * @return The value at the given position or the default value if not set
     */
    public T getValueAtPosition(int x, int y) {
        if(!this.positionStore.containsKey(x)) {
            return this.defaultValue;
        }

        return this.positionStore.get(x).getOrDefault(y, this.defaultValue);
    }

    /**
     * Gets the value at the X and Y position or returns the default value you have chosen
     * @param p A point object representing the X and Y value to check the value at
     * @return The value at the given position or the default value if not set
     */
    public T getValueAtPosition(Point p) {
        return this.getValueAtPosition(p.x, p.y);
    }

    /**
     * Given an X and Y position this will set this position to the given value
     * @param x The X position to set the value at
     * @param y The Y position to set the value at
     * @param val The new value to store in the data structure
     */
    public void setValueAtPosition(int x, int y, T val) {
        if(!this.positionStore.containsKey(x)) {
            this.positionStore.put(x, new HashMap<>());
        }

        this.positionStore.get(x).put(y, val);

        //Keep track of the max X and Y values in the grid on insertion to avoid looping to find it when queried
        if(x > this.maxX) {
            this.maxX = x;
        }
        if(y > this.maxY) {
            this.maxY = y;
        }

        if(x < this.minX) {
            this.minX = x;
        }
        if(y < this.minY) {
            this.minY = y;
        }
    }

    /**
     * Given an X and Y position this will set this position to the given value
     * @param p A point object representing the X and Y value to set the value at
     * @param val The new value to store in the data structure
     */
    public void setValueAtPosition(Point p, T val) {
        this.setValueAtPosition(p.x, p.y, val);
    }

    /**
     * Counts the occurrences of the given value in the datastructure
     * @param value The value to search for
     * @return The number of times the value occurs in the datastructure
     */
    public int countInstancesOfValue(T value) {
        int count = 0;
        for(Map.Entry<Integer, Map<Integer, T>> xEntry : this.positionStore.entrySet()) {
            for(Map.Entry<Integer, T> yEntry : xEntry.getValue().entrySet()) {
                if(yEntry.getValue().equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns a list of all the values stored in the data structure that have once been set
     * This will not return values from X/Y positions that have not been set but if it has been set to the default
     * value it will return that
     * @return List of values in the data structure
     */
    public List<T> getAllValuesStored() {
        List<T> vals = new ArrayList<>();
        for(Map.Entry<Integer, Map<Integer, T>> xEntry : this.positionStore.entrySet()) {
            for(Map.Entry<Integer, T> yEntry : xEntry.getValue().entrySet()) {
                vals.add(yEntry.getValue());
            }
        }

        return vals;
    }

    /**
     * Givne a value T this will find all the positions that this value is stored at
     * @param t Value to find in the data structure
     * @return All positions the value is found
     */
    public List<Point> getPositionsOfValue(T t) {
        List<Point> locations = new ArrayList<>();
        for(Map.Entry<Integer, Map<Integer, T>> xEntry : this.positionStore.entrySet()) {
            for(Map.Entry<Integer, T> yEntry : xEntry.getValue().entrySet()) {
                if(yEntry.getValue().equals(t)) {
                    locations.add(new Point(xEntry.getKey(), yEntry.getKey()));
                }
            }
        }

        return locations;
    }

    /**
     * Given a point generate a list of all directly adjacent points
     * @param p Point to find the adjacent values for
     * @return List of all points directly adjacent to this one
     */
    public List<Point> getDirectlyAdjacentPoints(Point p) {
        return this.getDirectlyAdjacentPoints(p.x, p.y);
    }

    /**
     * Given an X and Y position generate a list of all directly adjacent points
     * @param x X position to find adjacent values for
     * @param y Y position to find adjacent values for
     * @return List of all points directly adjacent to this one
     */
    public List<Point> getDirectlyAdjacentPoints(int x, int y) {
        int maxX = this.getMaxX();
        int maxY = this.getMaxY();

        List<Point> points = new ArrayList<>();
        if(x > 0) {
            points.add(new Point(x-1, y));
        }
        if(y > 0) {
            points.add(new Point(x, y-1));
        }
        if(x < maxX) {
            points.add(new Point(x+1, y));
        }
        if(y < maxY) {
            points.add(new Point(x, y+1));
        }

        return points;
    }


    /**
     * Given an X and Y position generate a list of all adjacent points
     * @param x X position to find adjacent values for
     * @param y Y position to find adjacent values for
     * @return List of all points adjacent to this one
     */
    public List<Point> getAdjacentPoints(int x, int y) {
        int maxX = this.getMaxX();
        int maxY = this.getMaxY();

        List<Point> adjacentPoints = new ArrayList<>();
        if(x > 0) {
            if(y > 0) {
                adjacentPoints.add(new Point(x-1, y-1));
            }
            if(y < maxY) {
                adjacentPoints.add(new Point(x-1, y+1));
            }
            adjacentPoints.add(new Point(x-1, y));
        }

        if(x < maxX) {
            if(y > 0) {
                adjacentPoints.add(new Point(x+1, y-1));
            }
            if(y < maxY) {
                adjacentPoints.add(new Point(x+1, y+1));
            }
            adjacentPoints.add(new Point(x+1, y));
        }

        if(y > 0) {
            adjacentPoints.add(new Point(x, y-1));
        }
        if(y < maxY) {
            adjacentPoints.add(new Point(x, y+1));
        }

        return adjacentPoints;
    }

    /**
     * Given a point generate a list of all adjacent points
     * @param p Point to find the adjacent values for
     * @return List of all points adjacent to this one
     */
    public List<Point> getAdjacentPoints(Point p) {
        return this.getAdjacentPoints(p.x, p.y);
    }

    /**
     * Print out the contents of the grid
     * Depending on the data used in the grid, this might look terrible or nice!
     */
    public void print() {
        this.print(null);
    }

    /**
     * Print out the contents of the grid and treat a specific value as whitespace
     * Depending on the data used in the grid, this might look terrible or nice!
     * @param treatValAsWhitespace A value to treat as whitespace
     */
    public void print(T treatValAsWhitespace) {
        for(int y = 0; y <= this.getMaxY(); y++) {
            for (int x = 0; x <= this.getMaxX(); x++) {
                T thisVal = this.getValueAtPosition(x, y);
                if(thisVal == treatValAsWhitespace) {
                    System.out.print(" ");
                }else{
                    System.out.print(thisVal);
                }
            }
            System.out.println();
        }
    }


    /**
     * Returns the maximum X position stored in the grid
     * @return highest X position stored in the grid
     */
    public int getMaxX() {
        return this.maxX;
    }

    /**
     * Returns the maximum y position stored in the grid
     * @return highest y position stored in the grid
     */
    public int getMaxY() {
        return this.maxY;
    }

    /**
     * Returns the minimum x position stored in the grid
     * @return position x value stored in the grid
     */
    public int getMinX() {
        return minX;
    }

    /**
     * Returns the minimum y position stored in the grid
     * @return smallest y position stored in the grid
     */
    public int getMinY() {
        return minY;
    }
}
