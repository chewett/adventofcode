package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * --- Day 12: Hill Climbing Algorithm ---
 * You try contacting the Elves using your handheld device, but the river you're following must be too low to get a
 * decent signal.
 *
 * You ask the device for a heightmap of the surrounding area (your puzzle input). The heightmap shows the local area
 * from above broken into a grid; the elevation of each square of the grid is given by a single lowercase letter, where
 * a is the lowest elevation, b is the next-lowest, and so on up to the highest elevation, z.
 *
 * Also included on the heightmap are marks for your current position (S) and the location that should get the best
 * signal (E). Your current position (S) has elevation a, and the location that should get the best signal (E) has
 * elevation z.
 *
 * You'd like to reach E, but to save energy, you should do it in as few steps as possible. During each step, you can
 * move exactly one square up, down, left, or right. To avoid needing to get out your climbing gear, the elevation of
 * the destination square can be at most one higher than the elevation of your current square; that is, if your current
 * elevation is m, you could step to elevation n, but not to elevation o. (This also means that the elevation of the
 * destination square can be much lower than the elevation of your current square.)
 *
 * For example:
 *
 * Sabqponm
 * abcryxxl
 * accszExk
 * acctuvwj
 * abdefghi
 * Here, you start in the top-left corner; your goal is near the middle. You could start by moving down or right, but
 * eventually you'll need to head toward the e at the bottom. From there, you can spiral around to the goal:
 *
 * v..v<<<<
 * >v.vv<<^
 * .>vv>E^^
 * ..v>>>^^
 * ..>>>>>^
 * In the above diagram, the symbols indicate whether the path exits each square moving up (^), down (v), left (<), or
 * right (>). The location that should get the best signal is still E, and . marks unvisited squares.
 *
 * This path reaches the goal in 31 steps, the fewest possible.
 *
 * What is the fewest steps required to move from your current position to the location that should get the best
 * signal?
 *
 * --- Part Two ---
 * As you walk up the hill, you suspect that the Elves will want to turn this into a hiking trail. The beginning isn't
 * very scenic, though; perhaps you can find a better starting point.
 *
 * To maximize exercise while hiking, the trail should start as low as possible: elevation a. The goal is still the
 * square marked E. However, the trail should still be direct, taking the fewest steps to reach its goal. So, you'll
 * need to find the shortest path from any square at elevation a to the square marked E.
 *
 * Again consider the example from above:
 *
 * Sabqponm
 * abcryxxl
 * accszExk
 * acctuvwj
 * abdefghi
 * Now, there are six choices for starting position (five marked a, plus the square marked S that counts as being at
 * elevation a). If you start at the bottom-left square, you can reach the goal most quickly:
 *
 * ...v<<<<
 * ...vv<<^
 * ...v>E^^
 * .>v>>>^^
 * >^>>>>>^
 * This path reaches the goal in only 29 steps, the fewest possible.
 *
 * What is the fewest steps required to move starting from any square with elevation a to the location that should get
 * the best signal?
 */
public class Day12 {


    /**
     * Simple "cheat" to use the ascii value of the value as a number
     * We handle S and E by converting them to 'a' and 'z'
     * @param val Character to convert to terrain value
     * @return Returns a value representing the elevation value
     */
    private int getElevationValue(char val) {
        if(val == 'S') {
            return 'a';
        }else if(val == 'E') {
            return 'z';
        }else{
            return val;
        }
    }

    /**
     * Work out how many steps it will take to get to the end point from a given starting position
     * @param terrainData The terrain data representing the mountain
     * @param startPoint The starting point to move
     * @return The smallest number of steps up the mountain
     */
    private int solveStartingAtPointP(Discrete2DPositionGrid<Character> terrainData, Point startPoint) {

        //Keep track how much it costs to visit each point
        Discrete2DPositionGrid<Integer> visitValues = new Discrete2DPositionGrid<>(Integer.MAX_VALUE);

        //Set up tracking to make sure we visit all the points
        Queue<Point> pointsToCheck = new LinkedList<>();
        visitValues.setValueAtPosition(startPoint, 0);
        pointsToCheck.add(startPoint);

        //Keep going until we have exhausted the points
        while(!pointsToCheck.isEmpty()) {
            Point currentPoint = pointsToCheck.poll();
            int currentHeight = this.getElevationValue(terrainData.getValueAtPosition(currentPoint));
            int newValue = visitValues.getValueAtPosition(currentPoint) + 1;
            List<Point> directlyAdjacentPoints = terrainData.getDirectlyAdjacentPoints(currentPoint);

            for(Point pointToTry : directlyAdjacentPoints) {
                int newHeight = this.getElevationValue(terrainData.getValueAtPosition(pointToTry));
                if(newValue < visitValues.getValueAtPosition(pointToTry) && newHeight <= (currentHeight+1)) {
                    //Only consider this if it's a shortcut, otherwise we have visited here already with a quicker move
                    pointsToCheck.add(pointToTry);
                    visitValues.setValueAtPosition(pointToTry, newValue);
                }
            }
        }

        //Find the end value
        for(int x = 0; x <= terrainData.getMaxX(); x++) {
            for(int y = 0; y <= terrainData.getMaxY(); y++) {
                if(terrainData.getValueAtPosition(x, y) == 'E') {
                    return visitValues.getValueAtPosition(x, y);
                }
            }
        }

        //Error case, we should never get here
        return -1;
    }

    /**
     * Part one we just find the shorest path from the S position
     * @param terrainData The terrain data representing the mountain
     * @return Shortest number of steps to the end
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> terrainData) {

        Point startPoint = null;
        //Find the start
        for(int x = 0; x <= terrainData.getMaxX() && startPoint == null; x++) {
            for(int y = 0; y <= terrainData.getMaxY(); y++) {
                if(terrainData.getValueAtPosition(x, y) == 'S') {
                    startPoint = new Point(x, y);
                    break;
                }
            }
        }

        if(startPoint == null) {
            throw new RuntimeException("Unable to find starting point");
        }

        return this.solveStartingAtPointP(terrainData, startPoint);
    }


    /**
     * Here in part 2 I do the bruteforce method where I just iterate over every possible starting point.
     *
     * Actually you could modify the solve function to treat all the a points as "zero movement" which means all of them
     * could be used to move onto the best a starting position and solve it with one iteration rather than many.
     *
     * But this worked and I didn't have to change the function above!
     *
     * @param terrainData The terrain data representing the mountain
     * @return Shortest number of steps to the end
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> terrainData) {
        int minDistance = Integer.MAX_VALUE;
        List<Point> allStartPoints = terrainData.getPositionsOfValue('a');
        for(Point startPoint : allStartPoints) {
            int newDistance = this.solveStartingAtPointP(terrainData, startPoint);
            minDistance = Math.min(minDistance, newDistance);
        }

        return minDistance;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> terrainData = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2022, 12);

        Day12 d = new Day12();
        long partOne = d.solvePartOne(terrainData);
        System.out.println("The shortest number of steps from S to E is " + partOne);

        long partTwo = d.solvePartTwo(terrainData);
        System.out.println("The shortest number of steps from any a position is "+ partTwo);

    }

}
