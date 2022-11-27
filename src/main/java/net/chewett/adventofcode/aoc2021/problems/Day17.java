package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 17: Trick Shot ---
 * You finally decode the Elves' message. HI, the message says. You continue searching for the sleigh keys.
 *
 * Ahead of you is what appears to be a large ocean trench. Could the keys have fallen into it? You'd better send a
 * probe to investigate.
 *
 * The probe launcher on your submarine can fire the probe with any integer velocity in the x (forward) and y (upward,
 * or downward if negative) directions. For example, an initial x,y velocity like 0,10 would fire the probe straight
 * up, while an initial velocity like 10,-1 would fire the probe forward at a slight downward angle.
 *
 * The probe's x,y position starts at 0,0. Then, it will follow some trajectory by moving in steps. On each step, these
 * changes occur in the following order:
 *
 * The probe's x position increases by its x velocity.
 * The probe's y position increases by its y velocity.
 * Due to drag, the probe's x velocity changes by 1 toward the value 0; that is, it decreases by 1 if it is greater than
 * 0, increases by 1 if it is less than 0, or does not change if it is already 0.
 * Due to gravity, the probe's y velocity decreases by 1.
 * For the probe to successfully make it into the trench, the probe must be on some trajectory that causes it to be
 * within a target area after any step. The submarine computer has already calculated this target area (your puzzle
 * input). For example:
 *
 * target area: x=20..30, y=-10..-5
 * This target area means that you need to find initial x,y velocity values such that after any step, the probe's x
 * position is at least 20 and at most 30, and the probe's y position is at least -10 and at most -5.
 *
 * Given this target area, one initial velocity that causes the probe to be within the target area after any step is 7,2:
 *
 * .............#....#............
 * .......#..............#........
 * ...............................
 * S........................#.....
 * ...............................
 * ...............................
 * ...........................#...
 * ...............................
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTT#TT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * In this diagram, S is the probe's initial position, 0,0. The x coordinate increases to the right, and the y
 * coordinate increases upward. In the bottom right, positions that are within the target area are shown as T. After
 * each step (until the target area is reached), the position of the probe is marked with #. (The bottom-right # is both
 * a position the probe reaches and a position in the target area.)
 *
 * Another initial velocity that causes the probe to be within the target area after any step is 6,3:
 *
 * ...............#..#............
 * ...........#........#..........
 * ...............................
 * ......#..............#.........
 * ...............................
 * ...............................
 * S....................#.........
 * ...............................
 * ...............................
 * ...............................
 * .....................#.........
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................T#TTTTTTTTT
 * ....................TTTTTTTTTTT
 * Another one is 9,0:
 *
 * S........#.....................
 * .................#.............
 * ...............................
 * ........................#......
 * ...............................
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTT#
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * ....................TTTTTTTTTTT
 * One initial velocity that doesn't cause the probe to be within the target area after any step is 17,-4:
 *
 * S..............................................................
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * .................#.............................................
 * ....................TTTTTTTTTTT................................
 * ....................TTTTTTTTTTT................................
 * ....................TTTTTTTTTTT................................
 * ....................TTTTTTTTTTT................................
 * ....................TTTTTTTTTTT..#.............................
 * ....................TTTTTTTTTTT................................
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * ................................................#..............
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * ...............................................................
 * ..............................................................#
 * The probe appears to pass through the target area, but is never within it after any step. Instead, it continues down
 * and to the right - only the first few steps are shown.
 *
 * If you're going to fire a highly scientific probe out of a super cool probe launcher, you might as well do it with
 * style. How high can you make the probe go while still reaching the target area?
 *
 * In the above example, using an initial velocity of 6,9 is the best you can do, causing the probe to reach a maximum
 * y position of 45. (Any higher initial y velocity causes the probe to overshoot the target area entirely.)
 *
 * Find the initial velocity that causes the probe to reach the highest y position and still eventually be within the
 * target area after any step. What is the highest y position it reaches on this trajectory?
 *
 * --- Part Two ---
 * Maybe a fancy trick shot isn't the best idea; after all, you only have one probe, so you had better not miss.
 *
 * To get the best idea of what your options are for launching the probe, you need to find every initial velocity that
 * causes the probe to eventually be within the target area after any step.
 *
 * In the above example, there are 112 different initial velocity values that meet these criteria:
 *
 * 23,-10  25,-9   27,-5   29,-6   22,-6   21,-7   9,0     27,-7   24,-5
 * 25,-7   26,-6   25,-5   6,8     11,-2   20,-5   29,-10  6,3     28,-7
 * 8,0     30,-6   29,-8   20,-10  6,7     6,4     6,1     14,-4   21,-6
 * 26,-10  7,-1    7,7     8,-1    21,-9   6,2     20,-7   30,-10  14,-3
 * 20,-8   13,-2   7,3     28,-8   29,-9   15,-3   22,-5   26,-8   25,-8
 * 25,-6   15,-4   9,-2    15,-2   12,-2   28,-9   12,-3   24,-6   23,-7
 * 25,-10  7,8     11,-3   26,-7   7,1     23,-9   6,0     22,-10  27,-6
 * 8,1     22,-8   13,-4   7,6     28,-6   11,-4   12,-4   26,-9   7,4
 * 24,-10  23,-8   30,-8   7,0     9,-1    10,-1   26,-5   22,-9   6,5
 * 7,5     23,-6   28,-10  10,-2   11,-1   20,-9   14,-2   29,-7   13,-3
 * 23,-5   24,-8   27,-9   30,-7   28,-5   21,-10  7,9     6,6     21,-5
 * 27,-10  7,2     30,-9   21,-8   22,-7   24,-9   20,-6   6,9     29,-5
 * 8,-2    27,-8   30,-5   24,-7
 * How many distinct initial velocity values cause the probe to be within the target area after any step?
 */
public class Day17 {

    /**
     * Simulate the y movement until it is lower or equal to than the Y Max and determine if it is higher or equal to  yMin
     * @param yMomentum Y momentum of the shot
     * @param yMin Minimum Y location we need to target
     * @param yMax Maximum Y location we need to target
     * @return Number of steps taken to reach the first
     */
    private int willShotLandInValidYPosition(int yMomentum, int yMin, int yMax) {
        int y = 0;
        int steps = 0;
        while(y > yMax) {
            y += yMomentum;
            yMomentum--;
            steps++;
        }

        //At this point we know y <= yMax
        return (y >= yMin) ? steps : 0;
    }

    /**
     * Simulate the X movement while it's less than the minimum or has run out of momentum
     * @param xMomentum X momentum of the shot
     * @param xMin Minimum X position to land in zone
     * @param xMax Maximum X position to land in zone
     * @return The number of steps taken to get to the zone or 0 if it won't reach
     */
    private int willShotLandInValidXPosition(int xMomentum, int xMin, int xMax) {
        int x = 0;
        int steps = 0;
        //keep going until its in range or it has ran out of momentum
        while(x < xMin && xMomentum > 0) {
            x += xMomentum;
            xMomentum--;
            steps++;
        }

        //Its only valid if its between the min and max (inclusive)
        return (x >= xMin && x <= xMax) ? steps : 0;
    }

    /**
     * Given the number of steps to run and the momentum this works out the highest point it will reach
     * @param yMomentum Initial Y momentum
     * @param stepsToRun Steps to simulate
     * @return Highest X position reached
     */
    private int getHighestPoint(int yMomentum, int stepsToRun) {
        int y = 0;
        int maxY = 0;
        int steps = 0;
        while(steps < stepsToRun) {
            y += yMomentum;
            yMomentum--;
            if(y > maxY) {
                maxY = y;
            }
            steps++;
        }

        return maxY;
    }

    /**
     * Given and X and Y momentum and the area that you want to land the probe in this will return whether it will
     * reach the location or not
     * @param xMomentum X momentum at the start
     * @param yMomentum Y momentum at the start
     * @param xMin Minimum X to be in the zone
     * @param xMax Maximum X to be in the zone
     * @param yMin Minimum Y to be in the zone
     * @param yMax Maximum Y to be in the zone
     * @return Returhns true if this will reach the zone, otherwise it will return false
     */
    private boolean willXYReach(int xMomentum, int yMomentum, int xMin, int xMax, int yMin, int yMax) {
        int x = 0;
        int y = 0;
        while(x < xMax && y >= yMin) {
            x += xMomentum;
            y += yMomentum;

            if(xMomentum > 0) {
                xMomentum--;
            }

            yMomentum--;

            if(x >= xMin && x <= xMax && y <= yMax && y >= yMin) {
                return true;
            }
        }

        return false;
    }

    /**
     * Find the highest direction we can shoot to be in the zone.
     * In this case the X position/movement doesn't actually matter so we only work out the Y data.
     * @param position Position range you want to shoot the probe into
     * @return The highest y position the probe can reach to hit hhe zone
     */
    public long solvePartOne(String position) {
        String[] tmp1 = position.split("(x=|, y=|\\.\\.)");
        int yMin = Integer.parseInt(tmp1[3]);
        int yMax = Integer.parseInt(tmp1[4]);

        int currentBestYMomentum = 1;
        int currentBestSteps = 0;

        //trying y momentum from 1 to 500 is sufficient for what we need!
        for(int yMomentum = 1; yMomentum < 500; yMomentum++) {
            int steps = this.willShotLandInValidYPosition(yMomentum, yMin, yMax);
            if(steps != 0) {
                currentBestYMomentum = yMomentum;
                currentBestSteps = steps;
            }
        }

        return this.getHighestPoint(currentBestYMomentum, currentBestSteps);
    }

    /**
     * Attempt to find the different ways we can shoot a shot into the zone
     * @param position Position range you want to shoot the probe into
     * @return The number of differeny ways to reach the right zone
     */
    public long solvePartTwo(String position) {
        String[] tmp1 = position.split("(x=|, y=|\\.\\.)");
        int xMin = Integer.parseInt(tmp1[1]);
        int xMax = Integer.parseInt(tmp1[2]);
        int yMin = Integer.parseInt(tmp1[3]);
        int yMax = Integer.parseInt(tmp1[4]);


        List<Integer> yMomentumsThatWillReach = new ArrayList<>();
        //Try a massive range of Y momentum's to find the areas which will reach
        for(int yMomentum = -500; yMomentum < 5000; yMomentum++) {
            int steps = this.willShotLandInValidYPosition(yMomentum, yMin, yMax);
            if(steps != 0) {
                yMomentumsThatWillReach.add(yMomentum);
            }
        }

        List<Integer> xMomentumsThatWillReach = new ArrayList<>();
        //Try a massive range of X momentum's to find the areas which will reach
        for(int xMomentum = -500; xMomentum < 5000; xMomentum++) {
            int steps = this.willShotLandInValidXPosition(xMomentum, xMin, xMax);
            if(steps != 0) {
                xMomentumsThatWillReach.add(xMomentum);
            }
        }

        int countOfReached = 0;
        //Loop over the X and Y values and see if the pairings will reach the location
        for(int yMomentum : yMomentumsThatWillReach) {
            for(int xMomentum : xMomentumsThatWillReach) {
                if(this.willXYReach(xMomentum, yMomentum, xMin, xMax, yMin, yMax)) {
                    countOfReached++;
                }
            }
        }

        return countOfReached;
    }

    public static void main(String[] args) {
        String position = ProblemLoader.loadProblemIntoString(2021, 17);

        Day17 d = new Day17();
        long partOne = d.solvePartOne(position);
        System.out.println("The highest Y position this can reach is " + partOne);

        long partTwo = d.solvePartTwo(position);
        System.out.println("The number of velocities which can reach the zone is " + partTwo);
    }

}
