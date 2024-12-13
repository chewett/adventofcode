package net.chewett.adventofcode.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String, Integer> countOccurencesInlist(List<String> list) {
        Map<String, Integer> map = new HashMap();
        for(String s : list) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        return map;
    }

}
