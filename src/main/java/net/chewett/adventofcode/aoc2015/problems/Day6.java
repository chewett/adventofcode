package net.chewett.adventofcode.aoc2015.problems;
import net.chewett.adventofcode.datastructures.DiscretePositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 6: Probably a Fire Hazard ---
 * Because your neighbors keep defeating you in the holiday house decorating contest year after year, you've decided to
 * deploy one million lights in a 1000x1000 grid.
 *
 * Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how to display the
 * ideal lighting configuration.
 *
 * Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are at
 * 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or toggle various inclusive
 * ranges given as coordinate pairs. Each coordinate pair represents opposite corners of a rectangle, inclusive; a
 * coordinate pair like 0,0 through 2,2 therefore refers to 9 lights in a 3x3 square. The lights all start turned off.
 *
 * To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent you
 * in order.
 *
 * For example:
 *
 * turn on 0,0 through 999,999 would turn on (or leave on) every light.
 * toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and turning
 * on the ones that were off.
 * turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
 * After following the instructions, how many lights are lit?
 *
 * --- Part Two ---
 * You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from
 * Ancient Nordic Elvish.
 *
 * The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or
 * more. The lights all start at zero.
 *
 * The phrase turn on actually means that you should increase the brightness of those lights by 1.
 *
 * The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.
 *
 * The phrase toggle actually means that you should increase the brightness of those lights by 2.
 *
 * What is the total brightness of all lights combined after following Santa's instructions?
 *
 * For example:
 *
 * turn on 0,0 through 0,0 would increase the total brightness by 1.
 * toggle 0,0 through 999,999 would increase the total brightness by 2000000.
 */
public class Day6 {

    /**
     * Simple parser to convert the string of the x/y point into a Point which can be used for the various parts.
     * @param str String to convert into a Point object
     * @return Point object representing an x/y pairing
     */
    private Point parseXYStringToPoint(String str) {
        String[] startingXySplit = str.split(",");
        int x = Integer.parseInt(startingXySplit[0]);
        int y = Integer.parseInt(startingXySplit[1]);

        return new Point(x, y);
    }

    /**
     * Function to solve part one where on = 1, off = 0, toggle flips the state
     * @param instructions A set of instructions to run
     * @return The number of lights that have been lit
     */
    public long solvePartOne(List<String> instructions) {
        DiscretePositionGrid<Integer> dpg = new DiscretePositionGrid<>(0);

        for(String instruction : instructions) {
            //String faffing to produce what we need
            String[] instructionParts = instruction.split(" ");
            String startingXy;
            String endingXy;
            int mode = 0;
            if(instructionParts[0].equals("toggle")) {
                mode = 0;
                startingXy = instructionParts[1];
                endingXy = instructionParts[3];
            }else{
                startingXy = instructionParts[2];
                endingXy = instructionParts[4];
                if(instructionParts[1].equals("on")) {
                    mode = 1;
                }else{
                    mode = 2;
                }
            }

            Point startingXY = this.parseXYStringToPoint(startingXy);
            Point endingXY = this.parseXYStringToPoint(endingXy);

            //Go across the rows first, so need to start the loop with Y
            for(int y = startingXY.y; y <= endingXY.y; y++) {
                for(int x = startingXY.x; x <= endingXY.x; x++) {
                    if(mode == 0) {
                        int curVal = dpg.getValueAtPosition(x, y);
                        if(curVal == 1) {
                            dpg.setValueAtPosition(x, y, 0);
                        }else{
                            dpg.setValueAtPosition(x, y, 1);
                        }

                    }else if(mode == 1) {
                        dpg.setValueAtPosition(x, y, 1);
                    }else if(mode == 2) {
                        dpg.setValueAtPosition(x, y, 0);
                    }
                }
            }
        }

        return dpg.countInstancesOfValue(1);
    }


    /**
     * When given a set of instructions this will return the sum total of brightness after performing all actions.
     * This is where on = +1, toggle = +2, off = -1 (and the brightness can only be 0 at minimum)
     * @param instructions
     * @return
     */
    public long solvePartTwo(List<String> instructions) {
        DiscretePositionGrid<Integer> dpg = new DiscretePositionGrid<>(0);

        for(String instruction : instructions) {
            //String faffing to produce what we need
            String[] instructionParts = instruction.split(" ");
            String startingXy;
            String endingXy;
            int mode = 0;
            if(instructionParts[0].equals("toggle")) {
                mode = 0;
                startingXy = instructionParts[1];
                endingXy = instructionParts[3];
            }else{
                startingXy = instructionParts[2];
                endingXy = instructionParts[4];
                if(instructionParts[1].equals("on")) {
                    mode = 1;
                }else{
                    mode = 2;
                }
            }

            Point startingXY = this.parseXYStringToPoint(startingXy);
            Point endingXY = this.parseXYStringToPoint(endingXy);

            //Go across the rows first, so need to start the loop with Y
            for(int y = startingXY.y; y <= endingXY.y; y++) {
                for(int x = startingXY.x; x <= endingXY.x; x++) {
                    int curVal = dpg.getValueAtPosition(x, y);
                    int newVal = 0;
                    if(mode == 0) {
                        newVal = curVal + 2;
                    }else if(mode == 1) {
                        newVal = curVal + 1;
                    }else if(mode == 2) {
                        newVal = curVal - 1;
                    }
                    //Ensure that we have a 0 minimum brightness in case they try and trip us out here!
                    newVal = Math.max(0, newVal);
                    dpg.setValueAtPosition(x, y, newVal);
                }
            }
        }

        //Get all the values and iterate over them, Possibly its easier to create an iterator on my new datastructure of some kind
        List<Integer> vals = dpg.getAllValuesStored();
        int sumTotalOfBrightness = 0;
        for(int i : vals) {
            sumTotalOfBrightness += i;
        }

        return sumTotalOfBrightness;
    }

    public static void main(String[] args) {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2015, 6);
        Day6 d = new Day6();
        long partOneLightsOn = d.solvePartOne(instructions);
        System.out.println("If I run the instructions in the first mode the total number of lights on is " + partOneLightsOn);
        long totalBrightness = d.solvePartTwo(instructions);
        System.out.println("If I run the instructions in the second mode the total brightness is " + totalBrightness);
    }

}
