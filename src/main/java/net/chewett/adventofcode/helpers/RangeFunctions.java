package net.chewett.adventofcode.helpers;

import net.chewett.adventofcode.datastructures.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * Various helper functions to handle ranges and the maths around it
 */
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

    /**
     * Given two ranges this works the intersection of the two and splits up the first passed in range
     * @param rangeToSplitMin Min of the range to be split up
     * @param rangeToSplitMax Max of the range to be split up
     * @param intersectionRangeMin Intersection min to be used for splitting
     * @param intersectionRangeMax Intersection max to be used for splitting
     * @return Returns a list of pairs representing the ways we have split up the first range
     */
    public static List<Pair<Long>> getNewRanges(long rangeToSplitMin, long rangeToSplitMax, long intersectionRangeMin, long intersectionRangeMax) {
        List<Pair<Long>> finalRanges = new ArrayList<>();

        //Find the intersection points between the two ranges
        long intersectionA = Math.max(rangeToSplitMin, intersectionRangeMin);
        long intersectionB = Math.min(rangeToSplitMax, intersectionRangeMax);

        //This handles the left hand side
        if(rangeToSplitMin < intersectionA) {
            finalRanges.add(new Pair<>(rangeToSplitMin, intersectionA - 1));
        }
        //There will always be a middle (if you called isRangeOverlapping before)
        finalRanges.add(new Pair<>(intersectionA, intersectionB));
        //Handles the right hand side
        if(intersectionB < rangeToSplitMax) {
            finalRanges.add(new Pair<>(intersectionB + 1, rangeToSplitMax));
        }

        return finalRanges;
    }

}
