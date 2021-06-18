package net.chewett.adventofcode.datastructures;

import java.util.*;

/**
 * Simple discrete grid of x/y points that allows getting/putting values at any position with a default value
 * @param <T> Type of value you want to store in the grid
 */
public class Discrete4DPositionGrid<T> {

    Map<Integer, Map<Integer, Map<Integer, Map<Integer, T>>>> positionStore = new HashMap<>();
    private T defaultValue;

    public Discrete4DPositionGrid(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public T getValueAtPosition(Point4D point) {
        return this.getValueAtPosition(point.getX(), point.getY(), point.getZ(), point.getW());
    }

    /**
     * Gets the value at the X, Y, Z, and W position or returns the default value you have chosen
     * @param x The X position you want to check the value at
     * @param y The Y position you want to check the value at
     * @param z The Z position you want to check the value at
     * @param w The W position you want to check the value at
     * @return The value at the given position or the default value if not set
     */
    public T getValueAtPosition(int x, int y, int z, int w) {
        if(!this.positionStore.containsKey(x)) {
            return this.defaultValue;
        }

        if(!this.positionStore.get(x).containsKey(y)) {
            return this.defaultValue;
        }

        if(!this.positionStore.get(x).get(y).containsKey(z)) {
            return this.defaultValue;
        }

        return this.positionStore.get(x).get(y).get(z).getOrDefault(w, this.defaultValue);
    }

    public void setValueAtPosition(Point4D point, T value) {
        this.setValueAtPosition(point.getX(), point.getY(), point.getZ(), point.getW(), value);
    }

    /**
     * Given an X, Y and Z position this will set this position to the given value
     * @param x The X position to set the value at
     * @param y The Y position to set the value at
     * @param z The Z position to set the value at
     * @param w The W position to set the value at
     * @param val The new value to store in the data structure
     */
    public void setValueAtPosition(int x, int y, int z, int w, T val) {
        if(!this.positionStore.containsKey(x)) {
            this.positionStore.put(x, new HashMap<>());
        }

        if(!this.positionStore.get(x).containsKey(y)) {
            this.positionStore.get(x).put(y, new HashMap<>());
        }

        if(!this.positionStore.get(x).get(y).containsKey(z)) {
            this.positionStore.get(x).get(y).put(z, new HashMap<>());
        }

        this.positionStore.get(x).get(y).get(z).put(w, val);
    }

    /**
     * Counts the occurrences of the given value in the datastructure
     * @param value The value to search for
     * @return The number of times the value occurs in the datastructure
     */
    public int countInstancesOfValue(T value) {
        int count = 0;
        for(Map.Entry<Integer, Map<Integer, Map<Integer, Map<Integer, T>>>> xEntry : this.positionStore.entrySet()) {
            for (Map.Entry<Integer, Map<Integer, Map<Integer, T>>> yEntry : xEntry.getValue().entrySet()) {
                for (Map.Entry<Integer, Map<Integer, T>> zEntry : yEntry.getValue().entrySet()) {
                    for (Map.Entry<Integer, T> wEntry : zEntry.getValue().entrySet()) {
                        if (wEntry.getValue().equals(value)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Given a value to search for, this will return a list of 4D points that are neighbours of that value in one or more dimensions
     *
     * Note: This finds diagonal neighbours also:
     *
     *  w=1           w=2           w=3
     *  z=1 z=2 z=3   z=1 z=2 z=3   z=1 z=2 z=3
     *  OOO OOO OOO   OOO OOO OOO   OOO OOO OOO
     *  OOO OOO OOO   OOO OXO OOO   OOO OOO OOO
     *  OOO OOO OOO   OOO OOO OOO   OOO OOO OOO
     *
     *  All neighbours are found in all dimensions where X is a point with the value and O is an identified neighbour point
     *
     * @param value The value to search for to find neighbours
     * @return A list of all points that neighbour something
     */
    public List<Point4D> getNeighboursOfPointsWithValue(T value) {
        Set<Point4D> positionsSet = new HashSet<>();
        for(Map.Entry<Integer, Map<Integer, Map<Integer, Map<Integer, T>>>> xMap : this.positionStore.entrySet()) {
            int x = xMap.getKey();
            for(Map.Entry<Integer, Map<Integer, Map<Integer, T>>> yMap : xMap.getValue().entrySet()) {
                int y = yMap.getKey();
                for(Map.Entry<Integer, Map<Integer, T>> zMap : yMap.getValue().entrySet()) {
                    int z = zMap.getKey();
                    for (Map.Entry<Integer, T> wMap : zMap.getValue().entrySet()) {
                        int w = wMap.getKey();
                        if (wMap.getValue().equals(value)) {
                            for (int neighbourX = x - 1; neighbourX <= x + 1; neighbourX++) {
                                for (int neighbourY = y - 1; neighbourY <= y + 1; neighbourY++) {
                                    for (int neighbourZ = z - 1; neighbourZ <= z + 1; neighbourZ++) {
                                        for(int neighbourW = w - 1; neighbourW <= w + 1; neighbourW++) {
                                            positionsSet.add(new Point4D(neighbourX, neighbourY, neighbourZ, neighbourW));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(positionsSet);
    }

    /*
     * Given a specific 4D point this will check to see if any of its neighbours have the value given.
     *
     * Note: This finds diagonal neighbours also:
     *
     *  w=1           w=2           w=3
     *  z=1 z=2 z=3   z=1 z=2 z=3   z=1 z=2 z=3
     *  OOO OOO OOO   OOO OOO OOO   OOO OOO OOO
     *  OOO OOO OOO   OOO OXO OOO   OOO OOO OOO
     *  OOO OOO OOO   OOO OOO OOO   OOO OOO OOO
     *
     *  All neighbours are found in all dimensions where X is a point with the value and O is an identified neighbour point
     *
     * @param point Point to search its neighbours
     * @param value Value to count if one of the neighbours are set to this value
     * @return The number of neighbours which have this value set
     */
    public int countNeighboursWithValue(Point4D point, T value) {
        int activeNeighbours = 0;

        for(int neighbourX = point.getX()-1; neighbourX <= point.getX()+1; neighbourX++) {
            for(int neighbourY = point.getY()-1; neighbourY <= point.getY()+1; neighbourY++) {
                for(int neighbourZ = point.getZ()-1; neighbourZ <= point.getZ()+1; neighbourZ++) {
                    for(int neighbourW = point.getW() - 1; neighbourW <= point.getW()+1; neighbourW++) {
                        if(neighbourX != point.getX() || neighbourY != point.getY() || neighbourZ != point.getZ() || neighbourW != point.getW()) {
                            if(this.getValueAtPosition(neighbourX, neighbourY, neighbourZ, neighbourW).equals(value)) {
                                activeNeighbours++;
                            }
                        }
                    }
                }
            }
        }

        return activeNeighbours;
    }


}
