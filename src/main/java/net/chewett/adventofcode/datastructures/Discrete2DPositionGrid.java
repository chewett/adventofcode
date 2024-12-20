package net.chewett.adventofcode.datastructures;

import java.awt.*;
import java.util.*;
import java.util.List;

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
     * Removes the value from the given X and Y position. This can help other functions later
     * @param x
     * @param y
     */
    public void unsetValueAtPosition(int x, int y) {
        if(!this.positionStore.containsKey(x)) {
            return;
        }
        this.positionStore.get(x).remove(y);
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

    public List<Point> getAllPositionsAValueIsStoredAt() {
        List<Point> allPositions = new ArrayList<>();
        for(Map.Entry<Integer, Map<Integer, T>> xEntry : this.positionStore.entrySet()) {
            for(Map.Entry<Integer, T> yEntry : xEntry.getValue().entrySet()) {
                allPositions.add(new Point(xEntry.getKey(), yEntry.getKey()));
            }
        }
        return allPositions;
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
     * Given a value T this will find all the positions that this value is stored at
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
     * Given a value T this will find the position this value is stored at
     * @param t Value to find in the data structure
     * @return The position this value is stored at
     */
    public Point getPositionOfValueAssumingOnlyOne(T t) {
        List<Point> locations = getPositionsOfValue(t);
        if(locations.size() != 1) {
            throw new RuntimeException("Expected one position and found " + locations.size());
        }
        return locations.get(0);
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
     * Gets the directly adjacent points to this point regardless if this goes outside the well-defined grid
     * @param p Point to find the directly adjacent points of
     * @return List of points directly adjacent to this regardless of the size
     */
    public List<Point> getDirectlyAdjacentRegardlessOfSize(Point p) {
        return this.getDirectlyAdjacentRegardlessOfSize(p.x, p.y);
    }

    /**
     * Gets the directly adjacent points to this point regardless if this goes outside the well-defined grid
     * @param x X position to get the adjacent points from
     * @param y Y position to get the adjacent points from
     * @return List of points directly adjacent to this regardless of the size
     */
    public List<Point> getDirectlyAdjacentRegardlessOfSize(int x, int y) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(x-1, y));
        points.add(new Point(x, y-1));
        points.add(new Point(x+1, y));

        points.add(new Point(x, y+1));

        return points;
    }

    /**
     * Helper to get the point north of this point if it is inside the currently defined grid
     * @param p Point to find the value north of
     * @return Null if there is no point or a point representing the value north of this point
     */
    public Point getNorth(Point p) {
        if(p.y > 0) {
            return new Point(p.x, p.y-1);
        }
        return null;
    }

    /**
     * Helper to get the point south of this point if it is inside the currently defined grid
     * @param p Point to find the value south of
     * @return Null if there is no point or a point representing the value south of this point
     */
    public Point getSouth(Point p) {
        if(p.y < this.getMaxY()) {
            return new Point(p.x, p.y+1);
        }
        return null;
    }

    /**
     * Helper to get the point west of this point if it is inside the currently defined grid
     * @param p Point to find the value west of
     * @return Null if there is no point or a point representing the value west of this point
     */
    public Point getWest(Point p) {
        if(p.x > 0) {
            return new Point(p.x-1, p.y);
        }
        return null;
    }

    /**
     * Helper to get the point east of this point if it is inside the currently defined grid
     * @param p Point to find the value east of
     * @return Null if there is no point or a point representing the value east of this point
     */
    public Point getEast(Point p) {
        if(p.x < this.getMaxX()) {
            return new Point(p.x+1, p.y);
        }
        return null;
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
     * Print out the contents of the grid in a reverse order
     * Depending on the data used in the grid, this might look terrible or nice!
     */
    public void printReversed() {
        this.printReversed(null);
    }

    /**
     * Print out the contents of the grid in a reverse order and treat a specific value as whitespace
     * Depending on the data used in the grid, this might look terrible or nice!
     * @param treatValAsWhitespace A value to treat as whitespace
     */
    public void printReversed(T treatValAsWhitespace) {
        for(int y = this.getMaxY(); y >= this.getMinX(); y--) {
            StringBuilder row = new StringBuilder();
            for (int x = this.getMinX(); x <= this.getMaxX(); x++) {
                T thisVal = this.getValueAtPosition(x, y);
                if(thisVal == treatValAsWhitespace) {
                    row.append(" ");
                }else{
                    row.append(thisVal);
                }
            }
            System.out.println(row);
        }
        System.out.println();
    }

    /**
     * Print out the contents of the grid and treat a specific value as whitespace
     * Depending on the data used in the grid, this might look terrible or nice!
     * @param treatValAsWhitespace A value to treat as whitespace
     */
    public void print(T treatValAsWhitespace) {
        for(int y = this.getMinY(); y <= this.getMaxY(); y++) {
            StringBuilder row = new StringBuilder();
            for (int x = this.getMinX(); x <= this.getMaxX(); x++) {
                T thisVal = this.getValueAtPosition(x, y);
                if(thisVal == treatValAsWhitespace) {
                    row.append(" ");
                }else{
                    row.append(thisVal);
                }
            }
            System.out.println(row);
        }
        System.out.println();
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


    /**
     * Simple clone function to createa an exact duplicate of the current object
     * @return Exact duplicate of the current object
     */
    public Discrete2DPositionGrid<T> clone() {
        Discrete2DPositionGrid<T> newGrid = new Discrete2DPositionGrid<>(this.defaultValue);

        for(Map.Entry<Integer, Map<Integer, T>> xEntry : this.positionStore.entrySet()) {
            for (Map.Entry<Integer, T> yEntry : xEntry.getValue().entrySet()) {
                newGrid.setValueAtPosition(xEntry.getKey(), yEntry.getKey(), yEntry.getValue());
            }
        }

        return newGrid;
    }

    /**
     * Goes through the entire column and finds the highest value contained in it
     * This mostly assumes its an Int Discrete2DPositionalGrid but could work for a char too
     * @param x Column Index
     * @return The highest value in the column
     */
    public int getMaxYOfColumn(int x) {
        int maxVal = 0;
        for(Map.Entry<Integer, Map<Integer, T>> xEntry : this.positionStore.entrySet()) {
            if (xEntry.getKey() == x) {
                for (Map.Entry<Integer, T> yEntry : xEntry.getValue().entrySet()) {
                    maxVal = Math.max(maxVal, yEntry.getKey());
                }
            }
        }

        return maxVal;
    }

    /**
     * Gets a string representing the state of the grid
     * TODO: Consider if this gets very long we could just return a sha/md5 of the string
     * @return A string representing the state of the grid
     */
    public String getGridState() {
        StringBuilder totalString = new StringBuilder();
        for(int y = 0; y <= this.getMaxY(); y++) {
            for(int x = 0; x <= this.getMaxX(); x++) {
                totalString.append(this.getValueAtPosition(x, y));
            }
        }
        return totalString.toString();
    }

    /**
     * Helper to determine if a point is inside or outside the chart
     * @param x X position to check
     * @param y Y position to check
     * @return True if the point is inside the grid otherwise false
     */
    public boolean pointInsideGraph(int x, int y) {
        return x >= this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY;
    }

    /**
     * Given a value this will flood fill the grid area with that value where every point is the value to flood
     * Normally you tell it to flood the background character which is typically a .
     *
     * @param floodVal Value to flood the grid with
     * @param valToFlood Value that should be flooded
     */
    public void floodFillTopLeft(T floodVal, T valToFlood) {
        //Define a bounding box slightly larger so we can flood "around" the area
        int minX = this.getMinX() - 1;
        int maxX = this.getMaxX() + 1;
        int minY = this.getMinY() - 1;
        int maxY = this.getMaxY() + 1;

        Set<Point> visited = new HashSet<>();
        Stack<Point> pointsToFlood = new Stack<>();
        //Always start top left and flood around the bounding box
        pointsToFlood.add(new Point(minX, minY));

        //Keep going until we are done!
        while(!pointsToFlood.isEmpty()) {
            Point newFlood = pointsToFlood.pop();
            visited.add(newFlood);
            this.setValueAtPosition(newFlood, floodVal);
            List<Point> newFloods = this.getDirectlyAdjacentRegardlessOfSize(newFlood);
            for (Point newFloodAttempt : newFloods) {
                if (
                    !visited.contains(newFloodAttempt) && //We haven't visited this (stops loops)
                    this.getValueAtPosition(newFloodAttempt) == valToFlood && // This value we are visiting is the defined flood values
                    (newFloodAttempt.x >= minX && newFloodAttempt.x <= maxX && newFloodAttempt.y >= minY && newFloodAttempt.y <= maxY) //inside bounding box
                ) {
                    pointsToFlood.add(newFloodAttempt);
                }
            }
        }
    }

    /**
     * Gets the total number of positions in the grid.
     * @return Number of total positions on the grid
     */
    public int getTotalPositions() {
        return this.getWidth() * this.getHeight();
    }

    /**
     * Helper function to get the width of the grid
     * @return Width of the grid
     */
    public int getWidth() {
        return (this.maxX - this.minX + 1);
    }

    /**
     * Helper function to get the height of the grid
     * @return Height of the grid
     */
    public int getHeight() {
        return (this.maxY - this.minY + 1);
    }

    /**
     * Get all the points within the manhattan distance of the current point, ignoring the central point
     * @param p Point to get the values from
     * @param r Radius to get the points
     * @return All points within the manhattan distance not including this one
     */
    public List<Point> generateAllPointsInTheManhattenRegionOfThis(Point p, int r) {
        List<Point> allPoints = new ArrayList<>();

        for (int x = p.x - r; x <= p.x + r; x++) {
            for (int y = p.y - r; y <= p.y + r; y++) {
                //Make sure we ignore this one
                if (Math.abs(x - p.x) + Math.abs(y - p.y) <= r && !(p.x == x && p.y == y )) {
                    allPoints.add(new Point(x, y));
                }
            }
        }


        return allPoints;
    }

}
