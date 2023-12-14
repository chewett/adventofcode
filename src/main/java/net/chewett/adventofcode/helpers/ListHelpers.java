package net.chewett.adventofcode.helpers;

import java.util.List;

public class ListHelpers {

    /**
     * Finds the max value inside a list
     * @param ints List of ints
     * @return Max value inside the list
     */
    public static int findMax(List<Integer> ints) {
        int maxInt = Integer.MIN_VALUE;
        for(int i : ints) {
            maxInt = Math.max(maxInt, i);
        }
        return maxInt;
    }

}
