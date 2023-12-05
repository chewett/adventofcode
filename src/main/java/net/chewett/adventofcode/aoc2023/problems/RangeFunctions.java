package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Pair;

import java.util.ArrayList;
import java.util.List;

public class RangeFunctions {

    /**
     * Returns true or false in the event the range of overlapping or not
     * @param startOne Starting point of the first range
     * @param endOne Ending point of the first range
     * @param startTwo Starting point of the second range
     * @param endTwo Ending point of the second range
     * @return True if one range overlaps the other
     */
    public static boolean isRangeOverlapping(long startOne, long endOne, long startTwo, long endTwo) {
        //Two starts after or the same as the start of one, but starts before or the same as the end of one
        if(startTwo >= startOne && startTwo <= endOne) {
            return true;

            //One starts after or the same as the start of two, but starts before or the same as the end of two
        }else if(startOne >= startTwo && startOne <= endTwo) {
            return true;

            //Two starts after one but ends before the end of one so its fully contained
        }else if(startTwo >= startOne && endTwo <= endOne) {
            return true;

            //One starts after two but ends before the end of two so its fully contained
        }else if(startOne >= startTwo && endOne <= endTwo) {
            return true;
        }

        return false;
    }

    public static List<Pair<Long>> getNewRanges(long sourceMin, long sourceMax, long rangeMin, long rangeMax) {
        List<Pair<Long>> finalRanges = new ArrayList<>();

        long intersectionA = Math.max(sourceMin, rangeMin);
        long intersectionB = Math.min(sourceMax, rangeMax);

        if(sourceMin < intersectionA) {
            finalRanges.add(new Pair<>(sourceMin, intersectionA - 1));
        }
        finalRanges.add(new Pair<>(intersectionA, intersectionB));
        if(intersectionB < sourceMax) {
            finalRanges.add(new Pair<>(intersectionB + 1, sourceMax));
        }

        return finalRanges;
    }

}
