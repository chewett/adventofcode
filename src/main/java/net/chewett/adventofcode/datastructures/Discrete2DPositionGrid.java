package net.chewett.adventofcode.datastructures;

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
    private T defaultValue;

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


}
