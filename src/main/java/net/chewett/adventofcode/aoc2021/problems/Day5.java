package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.List;

/**
 * --- Day 5: Hydrothermal Venture ---
 * You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque
 * clouds, so it would be best to avoid them if possible.
 *
 * They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for
 * you to review. For example:
 *
 * 0,9 -> 5,9
 * 8,0 -> 0,8
 * 9,4 -> 3,4
 * 2,2 -> 2,1
 * 7,0 -> 7,4
 * 6,4 -> 2,0
 * 0,9 -> 2,9
 * 3,4 -> 1,4
 * 0,0 -> 8,8
 * 5,5 -> 8,2
 * Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one
 * end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both
 * ends. In other words:
 *
 * An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
 * An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
 * For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.
 *
 * So, the horizontal and vertical lines from the above list would produce the following diagram:
 *
 * .......1..
 * ..1....1..
 * ..1....1..
 * .......1..
 * .112111211
 * ..........
 * ..........
 * ..........
 * ..........
 * 222111....
 * In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number
 * of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from
 * 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.
 *
 * To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In
 * the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.
 *
 * Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
 *
 * --- Part Two ---
 * Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture; you need to also
 * consider diagonal lines.
 *
 * Because of the limits of the hydrothermal vent mapping system, the lines in your list will only ever be horizontal,
 * vertical, or a diagonal line at exactly 45 degrees. In other words:
 *
 * An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
 * An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.
 * Considering all lines from the above example would now produce the following diagram:
 *
 * 1.1....11.
 * .111...2..
 * ..2.1.111.
 * ...1.2.2..
 * .112313211
 * ...1.2....
 * ..1...1...
 * .1.....1..
 * 1.......1.
 * 222111....
 * You still need to determine the number of points where at least two lines overlap. In the above example, this is
 * still anywhere in the diagram with a 2 or larger - now a total of 12 points.
 *
 * Consider all of the lines. At how many points do at least two lines overlap?
 */
public class Day5 {

    /**
     * Simple helper to parse the vent format into two x/y int pairs
     * @param vent String representing the vent line
     * @return An int array with four values representing the start and end x/y pairs
     */
    private int[] parseVentLocation(String vent) {
        int[] locations = new int[4];
        String[] coordParts = vent.split(" -> ");
        String[] coordA = coordParts[0].split(",");
        String[] coordB = coordParts[1].split(",");

        locations[0] = Integer.parseInt(coordA[0]);
        locations[1] = Integer.parseInt(coordA[1]);
        locations[2] = Integer.parseInt(coordB[0]);
        locations[3] = Integer.parseInt(coordB[1]);

        return locations;
    }

    /**
     * Process a vertical vent line and annotate the points on the grid
     * @param x X position on the grid
     * @param y1 Starting Y position on the grid
     * @param y2 Ending Y position on the grid
     * @param grid Grid to annotate
     */
    private void processVerticalLines(int x, int y1, int y2, Discrete2DPositionGrid<Integer> grid) {
        //Ensure y1 < y2 to make the code easier
        if(y2 < y1) {
            int tmp = y2;
            y2 = y1;
            y1 = tmp;
        }

        for(int yVal = y1; yVal <= y2; yVal++) {
            int curVal = grid.getValueAtPosition(x, yVal);
            grid.setValueAtPosition(x, yVal, curVal + 1);
        }
    }

    /**
     * Process a horizontal vent line and annotate the points on the grid
     * @param y Y position on the grid
     * @param x1 Starting X position on the grid
     * @param x2 Ending X position on the grid
     * @param grid Grid to annotate
     */
    private void processHorizontalLines(int y, int x1, int x2, Discrete2DPositionGrid<Integer> grid) {
        //Ensure x1 < x2 to make the code easier
        if(x2 < x1) {
            int tmp = x2;
            x2 = x1;
            x1 = tmp;
        }

        for(int xVal = x1; xVal <= x2; xVal++) {
            int curVal = grid.getValueAtPosition(xVal, y);
            grid.setValueAtPosition(xVal, y, curVal + 1);
        }
    }

    /**
     * Find the dangerous locations where two or more lines intersect and return the number of dangerous points
     * @param vents List of locations where the vents are
     * @return Number of dangerous locations
     */
    public long solvePartOne(List<String> vents) {
        Discrete2DPositionGrid<Integer> grid = new Discrete2DPositionGrid<>(0);

        for(String vent : vents) {
            int[] ventLocations = this.parseVentLocation(vent);
            int x1 = ventLocations[0];
            int y1 = ventLocations[1];
            int x2 = ventLocations[2];
            int y2 = ventLocations[3];

            //Handle vertical lines
            if(x1 == x2) {
                this.processVerticalLines(x1, y1, y2, grid);
            //Handle horizontal lines
            }else if(y1 == y2){
                this.processHorizontalLines(y1, x1, x2, grid);
            }
        }

        //Just count every value higher than 1
        List<Integer> allValues = grid.getAllValuesStored();
        int countOfMoreThanOne = 0;
        for(int i : allValues) {
            if(i > 1) {
                countOfMoreThanOne++;
            }
        }

        return countOfMoreThanOne;
    }

    /**
     * Find the dangerous locations where two or more lines intersect and return the number of dangerous points
     * @param vents List of locations where the vents are
     * @return Number of dangerous locations
     */
    public long solvePartTwo(List<String> vents) {
        Discrete2DPositionGrid<Integer> grid = new Discrete2DPositionGrid<>(0);

        for(String vent : vents) {
            int[] ventLocations = this.parseVentLocation(vent);
            int x1 = ventLocations[0];
            int y1 = ventLocations[1];
            int x2 = ventLocations[2];
            int y2 = ventLocations[3];

            //Here we do the same, but also handle diagonals
            if(x1 == x2) {
                this.processVerticalLines(x1, y1, y2, grid);
            }else if(y1 == y2) {
                this.processHorizontalLines(y1, x1, x2, grid);
            }else{
                if((y1 > y2 && x1 > x2)) {
                    int tmp = x2;
                    x2 = x1;
                    x1 = tmp;
                    tmp = y2;
                    y2 = y1;
                    y1 = tmp;
                }

                if(y2 > y1 && x2 > x1) {
                    for(int xVal = x1, yVal = y1; xVal <= x2; xVal++, yVal++) {
                        grid.setValueAtPosition(xVal, yVal, grid.getValueAtPosition(xVal, yVal) + 1);
                    }
                }

                if(y2 > y1 && x1 > x2) {
                    for(int xVal = x1, yVal = y1; xVal >= x2; xVal--, yVal++) {
                        grid.setValueAtPosition(xVal, yVal, grid.getValueAtPosition(xVal, yVal) + 1);
                    }
                }

                if(y1 > y2 && x2 > x1) {
                    for(int xVal = x1, yVal = y1; xVal <= x2; xVal++, yVal--) {
                        grid.setValueAtPosition(xVal, yVal, grid.getValueAtPosition(xVal, yVal) + 1);
                    }
                }
            }
        }

        List<Integer> allValues = grid.getAllValuesStored();
        int countOfMoreThanOne = 0;
        for(int i : allValues) {
            if(i > 1) {
                countOfMoreThanOne++;
            }
        }

        return countOfMoreThanOne;
    }

    public static void main(String[] args) {
        List<String> vents = ProblemLoader.loadProblemIntoStringArray(2021, 5);

        Day5 d = new Day5();
        long partOne = d.solvePartOne(vents);
        System.out.println("" + partOne);

        long partTwo = d.solvePartTwo(vents);
        System.out.println("" + partTwo);
    }


}
