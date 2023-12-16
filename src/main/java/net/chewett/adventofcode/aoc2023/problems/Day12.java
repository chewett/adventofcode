package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.CombinationGenerator;
import net.chewett.adventofcode.helpers.ProblemLoader;
import net.chewett.adventofcode.helpers.StringConversion;

import java.util.*;

/**
 * --- Day 12: Hot Springs ---
 * You finally reach the hot springs! You can see steam rising from secluded areas attached to the primary, ornate
 * building.
 *
 * As you turn to enter, the researcher stops you. "Wait - I thought you were looking for the hot springs, weren't
 * you?" You indicate that this definitely looks like hot springs to you.
 *
 * "Oh, sorry, common mistake! This is actually the onsen! The hot springs are next door."
 *
 * You look in the direction the researcher is pointing and suddenly notice the massive metal helixes towering
 * overhead. "This way!"
 *
 * It only takes you a few more steps to reach the main gate of the massive fenced-off area containing the springs.
 * You go through the gate and into a small administrative building.
 *
 * "Hello! What brings you to the hot springs today? Sorry they're not very hot right now; we're having a lava
 * shortage at the moment." You ask about the missing machine parts for Desert Island.
 *
 * "Oh, all of Gear Island is currently offline! Nothing is being manufactured at the moment, not until we get more
 * lava to heat our forges. And our springs. The springs aren't very springy unless they're hot!"
 *
 * "Say, could you go up and see why the lava stopped flowing? The springs are too cold for normal operation, but we
 * should be able to find one springy enough to launch you up there!"
 *
 * There's just one problem - many of the springs have fallen into disrepair, so they're not actually sure which
 * springs would even be safe to use! Worse yet, their condition records of which springs are damaged (your puzzle
 * input) are also damaged! You'll need to help them repair the damaged records.
 *
 * In the giant field just outside, the springs are arranged into rows. For each row, the condition records show
 * every spring and whether it is operational (.) or damaged (#). This is the part of the condition records that is
 * itself damaged; for some springs, it is simply unknown (?) whether the spring is operational or damaged.
 *
 * However, the engineer that produced the condition records also duplicated some of this information in a different
 * format! After the list of springs for a given row, the size of each contiguous group of damaged springs is listed
 * in the order those groups appear in the row. This list always accounts for every damaged spring, and each number is
 * the entire size of its contiguous group (that is, groups are always separated by at least one operational
 * spring: #### would always be 4, never 2,2).
 *
 * So, condition records with no unknown spring conditions might look like this:
 *
 * #.#.### 1,1,3
 * .#...#....###. 1,1,3
 * .#.###.#.###### 1,3,1,6
 * ####.#...#... 4,1,1
 * #....######..#####. 1,6,5
 * .###.##....# 3,2,1
 * However, the condition records are partially damaged; some of the springs' conditions are actually unknown (?).
 * For example:
 *
 * ???.### 1,1,3
 * .??..??...?##. 1,1,3
 * ?#?#?#?#?#?#?#? 1,3,1,6
 * ????.#...#... 4,1,1
 * ????.######..#####. 1,6,5
 * ?###???????? 3,2,1
 * Equipped with this information, it is your job to figure out how many different arrangements of operational and
 * broken springs fit the given criteria in each row.
 *
 * In the first line (???.### 1,1,3), there is exactly one way separate groups of one, one, and three broken springs
 * (in that order) can appear in that row: the first three unknown springs must be broken, then operational, then
 * broken (#.#), making the whole row #.#.###.
 *
 * The second line is more interesting: .??..??...?##. 1,1,3 could be a total of four different arrangements. The
 * last ? must always be broken (to satisfy the final contiguous group of three broken springs), and each ?? must
 * hide exactly one of the two broken springs. (Neither ?? could be both broken springs or they would form a single
 * contiguous group of two; if that were true, the numbers afterward would have been 2,3 instead.) Since each ?? can
 * either be #. or .#, there are four possible arrangements of springs.
 *
 * The last line is actually consistent with ten different arrangements! Because the first number is 3, the first and
 * second ? must both be . (if either were #, the first number would have to be 4 or higher). However, the remaining
 * run of unknown spring conditions have many different ways they could hold groups of two and one broken springs:
 *
 * ?###???????? 3,2,1
 * .###.##.#...
 * .###.##..#..
 * .###.##...#.
 * .###.##....#
 * .###..##.#..
 * .###..##..#.
 * .###..##...#
 * .###...##.#.
 * .###...##..#
 * .###....##.#
 * In this example, the number of possible arrangements for each row is:
 *
 * ???.### 1,1,3 - 1 arrangement
 * .??..??...?##. 1,1,3 - 4 arrangements
 * ?#?#?#?#?#?#?#? 1,3,1,6 - 1 arrangement
 * ????.#...#... 4,1,1 - 1 arrangement
 * ????.######..#####. 1,6,5 - 4 arrangements
 * ?###???????? 3,2,1 - 10 arrangements
 * Adding all of the possible arrangement counts together produces a total of 21 arrangements.
 *
 * For each row, count all of the different arrangements of operational and broken springs that meet the given
 * criteria. What is the sum of those counts?
 *
 * --- Part Two ---
 * As you look out at the field of springs, you feel like there are way more springs than the condition records list.
 * When you examine the records, you discover that they were actually folded up this whole time!
 *
 * To unfold the records, on each row, replace the list of spring conditions with five copies of itself (separated by
 * ?) and replace the list of contiguous groups of damaged springs with five copies of itself (separated by ,).
 *
 * So, this row:
 *
 * .# 1
 * Would become:
 *
 * .#?.#?.#?.#?.# 1,1,1,1,1
 * The first line of the above example would become:
 *
 * ???.###????.###????.###????.###????.### 1,1,3,1,1,3,1,1,3,1,1,3,1,1,3
 * In the above example, after unfolding, the number of possible arrangements for some rows is now much larger:
 *
 * ???.### 1,1,3 - 1 arrangement
 * .??..??...?##. 1,1,3 - 16384 arrangements
 * ?#?#?#?#?#?#?#? 1,3,1,6 - 1 arrangement
 * ????.#...#... 4,1,1 - 16 arrangements
 * ????.######..#####. 1,6,5 - 2500 arrangements
 * ?###???????? 3,2,1 - 506250 arrangements
 * After unfolding, adding all of the possible arrangement counts together produces 525152.
 *
 * Unfold your condition records; what is the new sum of possible arrangement counts?
 */
public class Day12 {

    //These are used in part 2 to keep track of the current thing we are running over
    private Map<String, Long> memoizeHelper = new HashMap<>();
    private String currentSpring = "";
    private int[] currentReportInts;

    /**
     * Returns a report representing the damaged strings
     * @param springs List of springs in string format
     * @return Report detailing the damage status
     */
    private String getDamageReport(String springs) {
        StringBuilder currentReport = new StringBuilder();
        int damageCount = 0;
        //Loop over the string and find each set of bad strings
        for(int i = 0; i < springs.length(); i++) {
            //If its a bad string, then increment the count
            if(springs.charAt(i) == '#') {
                damageCount++;
            }else{
                //If it's not a broken spring and we have seen some then add to the report
                if(damageCount != 0) {
                    currentReport.append(",").append(damageCount);
                    damageCount = 0;
                }
            }
        }

        //Handle the final broken springs if there was some
        if(damageCount > 0) {
            currentReport.append(",").append(damageCount);
        }

        //The report might not have any damaged things so handle that case too (which is always going to be wrong)
        if(!currentReport.isEmpty()) {
            return currentReport.substring(1);
        }else{
            return currentReport.toString();
        }
    }

    /**
     * Solves part one in a naive way by generating every possible way to represent the string
     * @param input List of springs and spring reports
     * @return Number of ways to arrange all the springs
     */
    public long solvePartOne(List<String> input) {
        long totalArrangements = 0;
        for(String str : input) {
            String[] numberString = str.split(" ");
            //Work out all possibilities
            List<String> possibilities = CombinationGenerator.createPossibilitiesOfStrings(numberString[0], '?', new char[]{'.', '#'});
            for(String pos : possibilities) {
                //Work out the damage report for the possibility
                String damageReport = this.getDamageReport(pos);
                //And then if it's the same as the damage report then we found a match!
                if(damageReport.equals(numberString[1])) {
                    totalArrangements++;
                }
            }
        }

        return totalArrangements;
    }

    /**
     * This is a somewhat complex function to determine the number of permutations
     * It (as named) memoizes the results so we don't have an insane amount of work to do
     * Memoization is based on tracking the three inputs to this function
     *
     * @param currentSpringIndex The current index spring inside the string of springs
     * @param currentIndexInReport The current index in the damaged spring report
     * @param accumulatedBrokenSprings The accumulated broken springs
     * @return Number of possible permutations
     */
    private long memoize(int currentSpringIndex, int currentIndexInReport, int accumulatedBrokenSprings) {
        String memKey = currentSpringIndex + "_" + currentIndexInReport + "_" + accumulatedBrokenSprings;
        //Lovely memoization step
        if(this.memoizeHelper.containsKey(memKey)) {
            return this.memoizeHelper.get(memKey);
        }

        //////////////////////////////////////////
        //These are our base cases

        //If we have reached the end then check everything else is in the correct "end phase"
        if(currentSpringIndex == currentSpring.length()) {
            //If we are at the end of reportInts and also have no more springs then it's good
            if(currentIndexInReport == this.currentReportInts.length && accumulatedBrokenSprings == 0) {
                return 1;
            //if we have one spring report left and the accumulated springs is the same size as the final value in the report then we are good!
            }else if(currentIndexInReport == this.currentReportInts.length-1 && this.currentReportInts[currentIndexInReport] == accumulatedBrokenSprings) {
                return 1;
            //if those two conditions are not true then this isn't a valid combination and we can stop
            }else{
                return 0;
            }
        }

        //////////////////////////////////////////
        //Now the recursive phase!

        long totalOptions = 0;

        char currentChar = this.currentSpring.charAt(currentSpringIndex);
        if(currentChar == '.') {

            //If the accumulated springs is zero then we just keep going
            if(accumulatedBrokenSprings == 0) {
                totalOptions += this.memoize(currentSpringIndex+1, currentIndexInReport, 0);

            //If we have reached a new block and the current number of springs meets what we expect in the reportInts we continue
            }else if(accumulatedBrokenSprings > 0 && currentIndexInReport < this.currentReportInts.length && this.currentReportInts[currentIndexInReport] == accumulatedBrokenSprings) {
                totalOptions += this.memoize(currentSpringIndex+1, currentIndexInReport+1, 0);

            }else{
                //No else phase, if we got here then it's not valid so don't add any options on!
            }

        }else if(currentChar == '#') {
            //If the current spring is broken we just accumulate and move on
            totalOptions += this.memoize(currentSpringIndex+1, currentIndexInReport, accumulatedBrokenSprings+1);


        }else if(currentChar == '?') {

            //////////////////////////////////////////
            //Assume its .
            //If the accumulated springs is zero then we just keep going
            if(accumulatedBrokenSprings == 0) {
                totalOptions += this.memoize(currentSpringIndex+1, currentIndexInReport, 0);

            //If we have reached a new block and the current number of springs meets what we expect in the reportInts we continue
            }else if(accumulatedBrokenSprings > 0 && currentIndexInReport < this.currentReportInts.length && this.currentReportInts[currentIndexInReport] == accumulatedBrokenSprings) {
                totalOptions += this.memoize(currentSpringIndex+1, currentIndexInReport+1, 0);

            }else{
                //No else phase, if we got here then it's not valid so don't add any options on!
            }

            //////////////////////////////////////////
            //Assume its #
            //If the current spring is broken we just accumulate and move on
            totalOptions += this.memoize(currentSpringIndex+1, currentIndexInReport, accumulatedBrokenSprings+1);

        }

        this.memoizeHelper.put(memKey, totalOptions);
        return totalOptions;
    }

    /**
     * "Entry point" to the memoization setting up the variables needed to run it
     * @param springs String representing the sprint state
     * @param report Report as a set of ints
     * @return Number of ways to arrange the springs
     */
    public long solveWithDynamicProgramming(String springs, int[] report) {
        //Set up the data to memoize this!
        this.memoizeHelper = new HashMap<>();
        this.currentSpring = springs;
        this.currentReportInts = report;

        return this.memoize(0, 0, 0);
    }

    /**
     * Works out the sum total of all different options of arranging the springs to meet the spring report
     *
     * Since this is much larger than the previous problem this needs to use a smarter way of doing it
     *
     * @param input List of springs and spring reports
     * @return Number of ways to arrange all the springs
     */
    public long solvePartTwo(List<String> input) {
        long totalArrangements = 0;
        for(String str : input) {
            String[] numberString = str.split(" ");
            //Work out the real report and sprints by making it five times longer
            String realSpring = numberString[0] + "?" + numberString[0] + "?" + numberString[0] + "?" + numberString[0] + "?" + numberString[0];
            String realReport = numberString[1] + "," + numberString[1] + "," + numberString[1] + "," + numberString[1] + "," + numberString[1];
            int[] reports = StringConversion.convertStringToIntArray(realReport, ",");

            totalArrangements += this.solveWithDynamicProgramming(realSpring, reports);
        }

        return totalArrangements;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 12);

        Day12 d = new Day12();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of arrangements is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The real number of arrangements is " + partTwo);
    }
}


