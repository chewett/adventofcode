package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 2: Red-Nosed Reports ---
 * Fortunately, the first location The Historians want to search isn't a long walk from the Chief Historian's office.
 *
 * While the Red-Nosed Reindeer nuclear fusion/fission plant appears to contain no sign of the Chief Historian, the
 * engineers there run up to you as soon as they see you. Apparently, they still talk about the time Rudolph was saved
 * through molecular synthesis from a single electron.
 *
 * They're quick to add that - since you're already here - they'd really appreciate your help analyzing some unusual
 * data from the Red-Nosed reactor. You turn to check if The Historians are waiting for you, but they seem to have
 * already divided into groups that are currently searching every corner of the facility. You offer to help with the
 * unusual data.
 *
 * The unusual data (your puzzle input) consists of many reports, one report per line. Each report is a list of numbers
 * called levels that are separated by spaces. For example:
 *
 * 7 6 4 2 1
 * 1 2 7 8 9
 * 9 7 6 2 1
 * 1 3 2 4 5
 * 8 6 4 4 1
 * 1 3 6 7 9
 * This example data contains six reports each containing five levels.
 *
 * The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only
 * tolerate levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe
 * if both of the following are true:
 *
 * The levels are either all increasing or all decreasing.
 * Any two adjacent levels differ by at least one and at most three.
 * In the example above, the reports can be found safe or unsafe by checking those rules:
 *
 * 7 6 4 2 1: Safe because the levels are all decreasing by 1 or 2.
 * 1 2 7 8 9: Unsafe because 2 7 is an increase of 5.
 * 9 7 6 2 1: Unsafe because 6 2 is a decrease of 4.
 * 1 3 2 4 5: Unsafe because 1 3 is increasing but 3 2 is decreasing.
 * 8 6 4 4 1: Unsafe because 4 4 is neither an increase or a decrease.
 * 1 3 6 7 9: Safe because the levels are all increasing by 1, 2, or 3.
 * So, in this example, 2 reports are safe.
 *
 * Analyze the unusual data from the engineers. How many reports are safe?
 *
 * --- Part Two ---
 * The engineers are surprised by the low number of safe reports until they realize they forgot to tell you about
 * the Problem Dampener.
 *
 * The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level
 * in what would otherwise be a safe report. It's like the bad level never happened!
 *
 * Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe,
 * the report instead counts as safe.
 *
 * More of the above example's reports are now safe:
 *
 * 7 6 4 2 1: Safe without removing any level.
 * 1 2 7 8 9: Unsafe regardless of which level is removed.
 * 9 7 6 2 1: Unsafe regardless of which level is removed.
 * 1 3 2 4 5: Safe by removing the second level, 3.
 * 8 6 4 4 1: Safe by removing the third level, 4.
 * 1 3 6 7 9: Safe without removing any level.
 * Thanks to the Problem Dampener, 4 reports are actually safe!
 *
 * Update your analysis by handling situations where the Problem Dampener can remove a single level from unsafe reports.
 * How many reports are now safe?
 *
 */
public class Day2 {

    /**
     * Given a list of values this determines if it is safe or not
     *
     * It is safe assuming that:
     * The numbers only go ascending or descending and do not repeat numbers consecutively
     * and it doesn't increase or decrease by 4 or more at a single step
     *
     * @param values List of reactor levels
     * @return A boolean representing whether the values are safe or not
     */
    public boolean isSafe(List<Integer> values) {
        boolean unsafe = false;

        //Find out if it is increasing or decreasing
        //If it is equal this would always pick decreasing but that is wrong and caught later
        boolean increasing;
        if (values.get(0) < values.get(1)) {
            increasing = true;
        } else {
            increasing = false;
        }

        //Loop over pairs of values
        for (int i = 0; i < values.size() - 1; i++) {
            //If they are equal it's wrong
            if (values.get(i) == values.get(i + 1)) {
                unsafe = true;
                break;
            }

            //If it's increasing but the values are going down it's unsafe
            if (increasing && values.get(i) > values.get(i + 1)) {
                unsafe = true;
                break;
            }

            //If it's decreasing but the values are going up it's unsafe
            if (!increasing && values.get(i) < values.get(i + 1)) {
                unsafe = true;
                break;
            }

            //If the absolute value between the two is larger than 3 it is unsafe
            if (Math.abs(values.get(i) - values.get(i + 1)) > 3) {
                unsafe = true;
                break;
            }
        }
        return !unsafe;
    }

    /**
     * Checks all the reactor values to determine the number of safe values
     * @param input List of reactor values
     * @return The number of safe values
     */
    public long solvePartOne(List<String> input) {
        long safe = 0;
        for(String line : input) {
            String[] parts = line.split(" ");

            List<Integer> listOfLevels = new ArrayList<>();
            for (String p : parts) {
                listOfLevels.add(Integer.parseInt(p));
            }

            boolean isThisSafe = this.isSafe(listOfLevels);
            if(isThisSafe) {
                safe++;
            }
        }

        return safe;
    }

    /**
     * Helper function to create a new list with the value removed.
     * Not exactly performant but this works well for what I need it to do (deep copy)
     * @param list List to modify
     * @param indexX Index to remove
     * @return New deep copied list with the removed index
     */
    public List<Integer> createNewListRemovingIndexX(List<Integer> list, int indexX) {
        List<Integer> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if(indexX != i) {
                newList.add(list.get(i) - indexX);
            }
        }
        return newList;
    }

    /**
     * Checks all the reactor values to see how many are safe if you can remove one of the values to make an unsafe
     * reactor value list safe
     * @param input List of reactor values
     * @return The number of safe values
     */
    public long solvePartTwo(List<String> input) {
        long safe = 0;
        for(String line : input) {
            String[] parts = line.split(" ");

            List<Integer> listOfLevels = new ArrayList<>();
            for (String p : parts) {
                listOfLevels.add(Integer.parseInt(p));
            }

            //If this is safe, we just increment it and move on
            boolean isThisSafe = this.isSafe(listOfLevels);
            if(isThisSafe) {
                safe++;
            }else {
                //Otherwise we keep looping over the list removing one at a time to see if we can make it safe
                //This is brute force but works nicely enough :)
                boolean isThisActuallySafeNow = false;
                for (int i = 0; i < listOfLevels.size(); i++) {
                    List<Integer> newList = this.createNewListRemovingIndexX(listOfLevels, i);
                    if (this.isSafe(newList)) {
                        isThisActuallySafeNow = true;
                        break;
                    }
                }

                if (isThisActuallySafeNow) {
                    safe++;
                }
            }
        }

        return safe;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 2);

        Day2 d = new Day2();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of safe levels are " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of safe levels with fault tolerance are " + partTwo);
    }
}


