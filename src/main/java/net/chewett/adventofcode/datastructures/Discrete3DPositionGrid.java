package net.chewett.adventofcode.datastructures;

import java.util.*;
import java.util.List;

/**
 * Simple discrete grid of x/y/z points that allows getting/putting values at any position with a default value
 * @param <T> Type of value you want to store in the grid
 */
public class Discrete3DPositionGrid<T> {

    Map<Integer, Map<Integer, Map<Integer, T>>> positionStore = new HashMap<>();
    private T defaultValue;

    private int maxX;
    private int maxY;
    private int maxZ;
    private int minX;
    private int minY;
    private int minZ;

    public Discrete3DPositionGrid(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the value at the given 3D position or returns the default value you have chosen to use
     * @param point The position to check for a value at
     * @return The default or the given point stored at the location
     */
    public T getValueAtPosition(Point3D point) {
        return this.getValueAtPosition(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Gets the value at the X, Y and Z position or returns the default value you have chosen
     * @param x The X position you want to check the value at
     * @param y The Y position you want to check the value at
     * @param z The Z position you want to check the value at
     * @return The value at the given position or the default value if not set
     */
    public T getValueAtPosition(int x, int y, int z) {
        if(!this.positionStore.containsKey(x)) {
            return this.defaultValue;
        }

        if(!this.positionStore.get(x).containsKey(y)) {
            return this.defaultValue;
        }

        return this.positionStore.get(x).get(y).getOrDefault(z, this.defaultValue);
    }

    /**
     * Given a point 3D object and a value this will set the value at the given 3D point
     * @param point The place to set the value
     * @param value The value to set
     */
    public void setValueAtPosition(Point3D point, T value) {
        this.setValueAtPosition(point.getX(), point.getY(), point.getZ(), value);
    }

    /**
     * Given an X, Y and Z position this will set this position to the given value
     * @param x The X position to set the value at
     * @param y The Y position to set the value at
     * @param z The Z position to set the value at
     * @param val The new value to store in the data structure
     */
    public void setValueAtPosition(int x, int y, int z, T val) {
        if(!this.positionStore.containsKey(x)) {
            this.positionStore.put(x, new HashMap<>());
        }

        if(!this.positionStore.get(x).containsKey(y)) {
            this.positionStore.get(x).put(y, new HashMap<>());
        }

        this.positionStore.get(x).get(y).put(z, val);

        this.maxX = Math.max(x, this.maxX);
        this.maxY = Math.max(y, this.maxY);
        this.maxZ = Math.max(z, this.maxX);
        this.minX = Math.min(x, this.minX);
        this.minY = Math.min(x, this.minY);
        this.minZ = Math.min(x, this.minZ);
    }

    /**
     * Counts the occurrences of the given value in the datastructure
     * @param value The value to search for
     * @return The number of times the value occurs in the datastructure
     */
    public Long countInstancesOfValue(T value) {
        long count = 0;
        for(Map.Entry<Integer, Map<Integer, Map<Integer, T>>> xEntry : this.positionStore.entrySet()) {
            for(Map.Entry<Integer, Map<Integer, T>> yEntry : xEntry.getValue().entrySet()) {
                for(Map.Entry<Integer, T> zEntry : yEntry.getValue().entrySet()) {
                    if(zEntry.getValue().equals(value)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Returns the full list of points directly adjacent to all the locations with value T
     * Note: This may return more than one of the same point if a point of a neighbour more than once!
     *
     * Note: This does not find diagonal neighbours
     *
     *  z=1 z=2 z=3
     *       O
     *   O  OXO  O
     *       O
     *
     *  All neighbours are found in all dimensions where X is a point with the value and O is an identified neighbour point
     *
     * @param value The value to search for to find neighbours
     * @return A list of all points that neighbour something
     */
    public List<Point3D> getDirectlyAdjacentNeighboursOfPointsWithValue(T value) {
        List<Point3D> positionsSet = new ArrayList<>();
        for(Map.Entry<Integer, Map<Integer, Map<Integer, T>>> xMap : this.positionStore.entrySet()) {
            int x = xMap.getKey();
            for(Map.Entry<Integer, Map<Integer, T>> yMap : xMap.getValue().entrySet()) {
                int y = yMap.getKey();
                for(Map.Entry<Integer, T> zMap : yMap.getValue().entrySet()) {
                    int z = zMap.getKey();
                    if(zMap.getValue().equals(value)) {
                        Point3D thisPoint = new Point3D(x, y, z);
                        positionsSet.addAll(thisPoint.getDirectlyAdjacentNeighbours());
                    }
                }
            }
        }

        return positionsSet;
    }

    /**
     * Given a value to search for, this will return a list of 3D points that are neighbours of that value in one or more dimensions
     *
     * Note: This finds diagonal neighbours also:
     *
     *  z=1 z=2 z=3
     *  OOO OOO OOO
     *  OOO OXO OOO
     *  OOO OOO OOO
     *
     *  All neighbours are found in all dimensions where X is a point with the value and O is an identified neighbour point
     *
     * @param value The value to search for to find neighbours
     * @return A list of all points that neighbour something
     */
    public List<Point3D> getNeighboursOfPointsWithValue(T value) {
        Set<Point3D> positionsSet = new HashSet<>();
        for(Map.Entry<Integer, Map<Integer, Map<Integer, T>>> xMap : this.positionStore.entrySet()) {
            int x = xMap.getKey();
            for(Map.Entry<Integer, Map<Integer, T>> yMap : xMap.getValue().entrySet()) {
                int y = yMap.getKey();
                for(Map.Entry<Integer, T> zMap : yMap.getValue().entrySet()) {
                    int z = zMap.getKey();
                    if(zMap.getValue().equals(value)) {
                        for (int neighbourX = x - 1; neighbourX <= x + 1; neighbourX++) {
                            for (int neighbourY = y - 1; neighbourY <= y + 1; neighbourY++) {
                                for (int neighbourZ = z - 1; neighbourZ <= z + 1; neighbourZ++) {
                                    positionsSet.add(new Point3D(neighbourX, neighbourY, neighbourZ));
                                }
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(positionsSet);
    }

    /**
     * Given a specific 3D point this will check to see if any of its neighbours have the value given.
     *
     * Note: This finds diagonal neighbours also:
     *
     *  z=1 z=2 z=3
     *  OOO OOO OOO
     *  OOO OXO OOO
     *  OOO OOO OOO
     *
     *  All neighbours are found in all dimensions where X is a point with the value and O is an identified neighbour point
     *
     * @param point Point to search its neighbours
     * @param value Value to count if one of the neighbours are set to this value
     * @return The number of neighbours which have this value set
     */
    public int countNeighboursWithValue(Point3D point, T value) {
        int activeNeighbours = 0;

        for(int neighbourX = point.getX()-1; neighbourX <= point.getX()+1; neighbourX++) {
            for(int neighbourY = point.getY()-1; neighbourY <= point.getY()+1; neighbourY++) {
                for(int neighbourZ = point.getZ()-1; neighbourZ <= point.getZ()+1; neighbourZ++) {
                    if(neighbourX != point.getX() || neighbourY != point.getY() || neighbourZ != point.getZ()) {
                        if(this.getValueAtPosition(neighbourX, neighbourY, neighbourZ).equals(value)) {
                            activeNeighbours++;
                        }
                    }
                }
            }
        }

        return activeNeighbours;
    }


    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMinZ() {
        return minZ;
    }
}
