package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 7: Bridge Repair ---
 * The Historians take you to a familiar rope bridge over a river in the middle of a jungle. The Chief isn't on this
 * side of the bridge, though; maybe he's on the other side?
 *
 * When you go to cross the bridge, you notice a group of engineers trying to repair it. (Apparently, it breaks pretty
 * frequently.) You won't be able to cross until it's fixed.
 *
 * You ask how long it'll take; the engineers tell you that it only needs final calibrations, but some young elephants
 * were playing nearby and stole all the operators from their calibration equations! They could finish the calibrations
 * if only someone could determine which test values could possibly be produced by placing any combination of operators
 * into their calibration equations (your puzzle input).
 *
 * For example:
 *
 * 190: 10 19
 * 3267: 81 40 27
 * 83: 17 5
 * 156: 15 6
 * 7290: 6 8 6 15
 * 161011: 16 10 13
 * 192: 17 8 14
 * 21037: 9 7 18 13
 * 292: 11 6 16 20
 * Each line represents a single equation. The test value appears before the colon on each line; it is your job to
 * determine whether the remaining numbers can be combined with operators to produce the test value.
 *
 * Operators are always evaluated left-to-right, not according to precedence rules. Furthermore, numbers in the
 * equations cannot be rearranged. Glancing into the jungle, you can see elephants holding two different types of
 * operators: add (+) and multiply (*).
 *
 * Only three of the above equations can be made true by inserting operators:
 *
 * 190: 10 19 has only one position that accepts an operator: between 10 and 19. Choosing + would give 29, but
 * choosing * would give the test value (10 * 19 = 190).
 * 3267: 81 40 27 has two positions for operators. Of the four possible configurations of the operators, two cause
 * the right side to match the test value: 81 + 40 * 27 and 81 * 40 + 27 both equal 3267 (when evaluated left-to-right)!
 * 292: 11 6 16 20 can be solved in exactly one way: 11 + 6 * 16 + 20.
 * The engineers just need the total calibration result, which is the sum of the test values from just the equations
 * that could possibly be true. In the above example, the sum of the test values for the three equations listed above
 * is 3749.
 *
 * Determine which equations could possibly be true. What is their total calibration result?
 *
 * --- Part Two ---
 * The engineers seem concerned; the total calibration result you gave them is nowhere close to being within safety
 * tolerances. Just then, you spot your mistake: some well-hidden elephants are holding a third type of operator.
 *
 * The concatenation operator (||) combines the digits from its left and right inputs into a single number. For
 * example, 12 || 345 would become 12345. All operators are still evaluated left-to-right.
 *
 * Now, apart from the three equations that could be made true using only addition and multiplication, the above
 * example has three more equations that can be made true by inserting operators:
 *
 * 156: 15 6 can be made true through a single concatenation: 15 || 6 = 156.
 * 7290: 6 8 6 15 can be made true using 6 * 8 || 6 * 15.
 * 192: 17 8 14 can be made true using 17 || 8 + 14.
 * Adding up all six test values (the three that could be made before using only + and * plus the new three that
 * can now be made by also using ||) produces the new total calibration result of 11387.
 *
 * Using your new knowledge of elephant hiding spots, determine which equations could possibly be true. What is their
 * total calibration result?
 *
 */
public class Day7 {

    public long totalUpValidCalibrationValues(List<String> input, boolean allowDoubleBarForPartTwo) {
        long foundGoodTotals = 0;
        for(String line : input) {
            String[] parts = line.split(":? ");

            //First number is total we want, following numbers are stuff we need to handle
            List<Long> numbers = new ArrayList<>();
            for(int i = 0; i < parts.length; i++) {
                numbers.add(Long.parseLong(parts[i]));
            }

            //Use a stack for Depth First Search
            Stack<List<Long>> statesToVisit = new Stack<>();
            statesToVisit.add(numbers);

            boolean foundGoodValue = false;
            while(statesToVisit.size() > 0) {
                List<Long> newState = statesToVisit.pop();

                //If there are just two values and they match, we have found an option :)
                if(newState.size() == 2) {
                    if(Objects.equals(newState.get(0), newState.get(1))) {
                        foundGoodValue = true;
                        break;
                    }else{
                        continue;
                    }
                }

                //Handle plus
                List<Long> newPlusState = new LinkedList<>();
                newPlusState.addAll(newState);
                //Work out the new value
                long newVal = newPlusState.get(1) + newPlusState.get(2);
                //If it's smaller or equal it could be correct so add it to the states to visit
                if(newVal <= newPlusState.get(0)) {
                    newPlusState.set(1, newVal);
                    newPlusState.remove(2);
                    statesToVisit.push(newPlusState);
                }
                //Do the same for mult and double bar

                List<Long> newMultState = new LinkedList<>();
                newMultState.addAll(newState);
                newVal = newMultState.get(1) * newMultState.get(2);
                if(newVal <= newMultState.get(0)) {
                    newMultState.set(1, newVal);
                    newMultState.remove(2);
                    statesToVisit.push(newMultState);
                }

                //Only do double bar in the event we allow it
                if(allowDoubleBarForPartTwo) {
                    List<Long> newDoubleBarState = new LinkedList<>();
                    newDoubleBarState.addAll(newState);
                    newVal = Long.parseLong("" + newDoubleBarState.get(1) + newDoubleBarState.get(2));
                    if (newVal <= newDoubleBarState.get(0)) {
                        newDoubleBarState.set(1, newVal);
                        newDoubleBarState.remove(2);
                        statesToVisit.push(newDoubleBarState);
                    }
                }

            }

            if(foundGoodValue) {
                foundGoodTotals += numbers.get(0);
            }
        }
        return foundGoodTotals;
    }


    /**
     * Totals up the calibration values for all the valid values using just addition and multiplication
     * @param input The calibration values
     * @return Total of valid calibration values
     */
    public long solvePartOne(List<String> input) {
        return this.totalUpValidCalibrationValues(input, false);
    }

    /**
     Totals up the calibration values for all the valid values using addition, multiplication, and double bar
     * @param input The calibration values
     * @return Total of valid calibration values
     */
    public long solvePartTwo(List<String> input) {
        return this.totalUpValidCalibrationValues(input, true);
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 7);

        Day7 d = new Day7();
        long partOne = d.solvePartOne(input);
        System.out.println("The total calibration values just using multiplication and addition is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The total calibration values for all operators is " + partTwo);
    }
}


