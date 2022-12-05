package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.List;

/**
 * --- Day 4: Camp Cleanup ---
 * Space needs to be cleared before the last supplies can be unloaded from the ships, and so several Elves have been
 * assigned the job of cleaning up sections of the camp. Every section has a unique ID number, and each Elf is assigned
 * a range of section IDs.
 *
 * However, as some of the Elves compare their section assignments with each other, they've noticed that many of the
 * assignments overlap. To try to quickly find overlaps and reduce duplicated effort, the Elves pair up and make a big
 * list of the section assignments for each pair (your puzzle input).
 *
 * For example, consider the following list of section assignment pairs:
 *
 * 2-4,6-8
 * 2-3,4-5
 * 5-7,7-9
 * 2-8,3-7
 * 6-6,4-6
 * 2-6,4-8
 * For the first few pairs, this list means:
 *
 * Within the first pair of Elves, the first Elf was assigned sections 2-4 (sections 2, 3, and 4), while the second Elf
 * was assigned sections 6-8 (sections 6, 7, 8).
 * The Elves in the second pair were each assigned two sections.
 * The Elves in the third pair were each assigned three sections: one got sections 5, 6, and 7, while the other also got
 * 7, plus 8 and 9.
 * This example list uses single-digit section IDs to make it easier to draw; your actual list might contain larger
 * numbers. Visually, these pairs of section assignments look like this:
 *
 * .234.....  2-4
 * .....678.  6-8
 *
 * .23......  2-3
 * ...45....  4-5
 *
 * ....567..  5-7
 * ......789  7-9
 *
 * .2345678.  2-8
 * ..34567..  3-7
 *
 * .....6...  6-6
 * ...456...  4-6
 *
 * .23456...  2-6
 * ...45678.  4-8
 * Some of the pairs have noticed that one of their assignments fully contains the other. For example, 2-8 fully
 * contains 3-7, and 6-6 is fully contained by 4-6. In pairs where one assignment fully contains the other, one Elf in
 * the pair would be exclusively cleaning sections their partner will already be cleaning, so these seem like the most
 * in need of reconsideration. In this example, there are 2 such pairs.
 *
 * In how many assignment pairs does one range fully contain the other?
 *
 * --- Part Two ---
 * It seems like there is still quite a bit of duplicate work planned. Instead, the Elves would like to know the number
 * of pairs that overlap at all.
 *
 * In the above example, the first two pairs (2-4,6-8 and 2-3,4-5) don't overlap, while the remaining four pairs
 * (5-7,7-9, 2-8,3-7, 6-6,4-6, and 2-6,4-8) do overlap:
 *
 * 5-7,7-9 overlaps in a single section, 7.
 * 2-8,3-7 overlaps all of the sections 3 through 7.
 * 6-6,4-6 overlaps in a single section, 6.
 * 2-6,4-8 overlaps in sections 4, 5, and 6.
 * So, in this example, the number of overlapping assignment pairs is 4.
 *
 * In how many assignment pairs do the ranges overlap?
 */
public class Day4 {

    /**
     * Loop over the assignments and work out if one contains the other entirely
     * @param assignments List of assignments to process
     * @return Number of regions entirely containing the other regions
     */
    public long solvePartOne(List<String> assignments) {
        int pairsContainingEachOther = 0;
        for(String assignment : assignments) {
            String[] assignmentParts = assignment.split("[-,]");
            int assignmentOneStart = Integer.parseInt(assignmentParts[0]);
            int assignmentOneEnd = Integer.parseInt(assignmentParts[1]);
            int assignmentTwoStart = Integer.parseInt(assignmentParts[2]);
            int assignmentTwoEnd = Integer.parseInt(assignmentParts[3]);

            //Check if the first region is inside the second
            if(assignmentOneStart >= assignmentTwoStart && assignmentOneEnd <= assignmentTwoEnd) {
                pairsContainingEachOther++;
            //Check if the second region is inside the first
            }else if(assignmentTwoStart >= assignmentOneStart && assignmentTwoEnd <= assignmentOneEnd) {
                pairsContainingEachOther++;
            }
        }

        return pairsContainingEachOther;
    }

    /**
     * Returns true or false in the event the range of overlapping or not
     * @param startOne Starting point of the first range
     * @param endOne Ending point of the first range
     * @param startTwo Starting point of the second range
     * @param endTwo Ending point of the second range
     * @return True if one range overlaps the other
     */
    public boolean isRangeOverlapping(int startOne, int endOne, int startTwo, int endTwo) {
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
     * Loop over the assignments and work out if one overlaps the other
     * @param assignments List of assignments to process
     * @return The number of regions which overlap each other
     */
    public long solvePartTwo(List<String> assignments) {
        int overlappingPairs = 0;
        for(String assignment : assignments) {
            String[] assignmentParts = assignment.split("[-,]");
            int assignmentOneStart = Integer.parseInt(assignmentParts[0]);
            int assignmentOneEnd = Integer.parseInt(assignmentParts[1]);
            int assignmentTwoStart = Integer.parseInt(assignmentParts[2]);
            int assignmentTwoEnd = Integer.parseInt(assignmentParts[3]);

            if(this.isRangeOverlapping(assignmentOneStart, assignmentOneEnd, assignmentTwoStart, assignmentTwoEnd)) {
                overlappingPairs++;
            }
        }

        return overlappingPairs;
    }

    public static void main(String[] args) {
        List<String> assignments = ProblemLoader.loadProblemIntoStringArray(2022, 4);

        Day4 d = new Day4();
        long partOne = d.solvePartOne(assignments);
        System.out.println("The number of regions which contain each other entirely are " + partOne);

        long partTwo = d.solvePartTwo(assignments);
        System.out.println("The number of regions which overlap each other are " + partTwo);
    }

}
