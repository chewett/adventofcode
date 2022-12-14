package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.awt.*;
import java.util.List;

/**
 * --- Day 14: Regolith Reservoir ---
 * The distress signal leads you to a giant waterfall! Actually, hang on - the signal seems like it's coming from the
 * waterfall itself, and that doesn't make any sense. However, you do notice a little path that leads behind the
 * waterfall.
 *
 * Correction: the distress signal leads you behind a giant waterfall! There seems to be a large cave system here,
 * and the signal definitely leads further inside.
 *
 * As you begin to make your way deeper underground, you feel the ground rumble for a moment. Sand begins pouring into
 * the cave! If you don't quickly figure out where the sand is going, you could quickly become trapped!
 *
 * Fortunately, your familiarity with analyzing the path of falling material will come in handy here. You scan a
 * two-dimensional vertical slice of the cave above you (your puzzle input) and discover that it is mostly air with
 * structures made of rock.
 *
 * Your scan traces the path of each solid rock structure and reports the x,y coordinates that form the shape of the
 * path, where x represents distance to the right and y represents distance down. Each path appears as a single line
 * of text in your scan. After the first point of each path, each point indicates the end of a straight horizontal or
 * vertical line to be drawn from the previous point. For example:
 *
 * 498,4 -> 498,6 -> 496,6
 * 503,4 -> 502,4 -> 502,9 -> 494,9
 * This scan means that there are two paths of rock; the first path consists of two straight lines, and the second path
 * consists of three straight lines. (Specifically, the first path consists of a line of rock from 498,4 through 498,6
 * and another line of rock from 498,6 through 496,6.)
 *
 * The sand is pouring into the cave from point 500,0.
 *
 * Drawing rock as #, air as ., and the source of the sand as +, this becomes:
 *
 *
 *   4     5  5
 *   9     0  0
 *   4     0  3
 * 0 ......+...
 * 1 ..........
 * 2 ..........
 * 3 ..........
 * 4 ....#...##
 * 5 ....#...#.
 * 6 ..###...#.
 * 7 ........#.
 * 8 ........#.
 * 9 #########.
 * Sand is produced one unit at a time, and the next unit of sand is not produced until the previous unit of sand comes
 * to rest. A unit of sand is large enough to fill one tile of air in your scan.
 *
 * A unit of sand always falls down one step if possible. If the tile immediately below is blocked (by rock or sand),
 * the unit of sand attempts to instead move diagonally one step down and to the left. If that tile is blocked, the unit
 * of sand attempts to instead move diagonally one step down and to the right. Sand keeps moving as long as it is able
 * to do so, at each step trying to move down, then down-left, then down-right. If all three possible destinations are
 * blocked, the unit of sand comes to rest and no longer moves, at which point the next unit of sand is created back at
 * the source.
 *
 * So, drawing sand that has come to rest as o, the first unit of sand simply falls straight down and then stops:
 *
 * ......+...
 * ..........
 * ..........
 * ..........
 * ....#...##
 * ....#...#.
 * ..###...#.
 * ........#.
 * ......o.#.
 * #########.
 * The second unit of sand then falls straight down, lands on the first one, and then comes to rest to its left:
 *
 * ......+...
 * ..........
 * ..........
 * ..........
 * ....#...##
 * ....#...#.
 * ..###...#.
 * ........#.
 * .....oo.#.
 * #########.
 * After a total of five units of sand have come to rest, they form this pattern:
 *
 * ......+...
 * ..........
 * ..........
 * ..........
 * ....#...##
 * ....#...#.
 * ..###...#.
 * ......o.#.
 * ....oooo#.
 * #########.
 * After a total of 22 units of sand:
 *
 * ......+...
 * ..........
 * ......o...
 * .....ooo..
 * ....#ooo##
 * ....#ooo#.
 * ..###ooo#.
 * ....oooo#.
 * ...ooooo#.
 * #########.
 * Finally, only two more units of sand can possibly come to rest:
 *
 * ......+...
 * ..........
 * ......o...
 * .....ooo..
 * ....#ooo##
 * ...o#ooo#.
 * ..###ooo#.
 * ....oooo#.
 * .o.ooooo#.
 * #########.
 * Once all 24 units of sand shown above have come to rest, all further sand flows out the bottom, falling into the
 * endless void. Just for fun, the path any new sand takes before falling forever is shown here with ~:
 *
 * .......+...
 * .......~...
 * ......~o...
 * .....~ooo..
 * ....~#ooo##
 * ...~o#ooo#.
 * ..~###ooo#.
 * ..~..oooo#.
 * .~o.ooooo#.
 * ~#########.
 * ~..........
 * ~..........
 * ~..........
 * Using your scan, simulate the falling sand. How many units of sand come to rest before sand starts flowing into the
 * abyss below?
 *
 * --- Part Two ---
 * You realize you misread the scan. There isn't an endless void at the bottom of the scan - there's floor, and you're
 * standing on it!
 *
 * You don't have time to scan the floor, so assume the floor is an infinite horizontal line with a y coordinate equal
 * to two plus the highest y coordinate of any point in your scan.
 *
 * In the example above, the highest y coordinate of any point is 9, and so the floor is at y=11. (This is as if your
 * scan contained one extra rock path like -infinity,11 -> infinity,11.) With the added floor, the example above now
 * looks like this:
 *
 *         ...........+........
 *         ....................
 *         ....................
 *         ....................
 *         .........#...##.....
 *         .........#...#......
 *         .......###...#......
 *         .............#......
 *         .............#......
 *         .....#########......
 *         ....................
 * <-- etc #################### etc -->
 * To find somewhere safe to stand, you'll need to simulate falling sand until a unit of sand comes to rest at 500,0,
 * blocking the source entirely and stopping the flow of sand into the cave. In the example above, the situation
 * finally looks like this after 93 units of sand come to rest:
 *
 * ............o............
 * ...........ooo...........
 * ..........ooooo..........
 * .........ooooooo.........
 * ........oo#ooo##o........
 * .......ooo#ooo#ooo.......
 * ......oo###ooo#oooo......
 * .....oooo.oooo#ooooo.....
 * ....oooooooooo#oooooo....
 * ...ooo#########ooooooo...
 * ..ooooo.......ooooooooo..
 * #########################
 * Using your scan, simulate the falling sand until the source of the sand becomes blocked. How many units of sand come
 * to rest?
 */
public class Day14 {

    /**
     * Helper function to convert a string with an X and Y value into a Point
     * @param str String in the format x,y
     * @return Point representing the String
     */
    private Point convertToPoint(String str) {
        String[] parts = str.split(",");
        return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    /**
     * Takes the input and then returns a grid with all the rocks stored on it
     * @param input List input of the rocks
     * @return Grid representing all the rocks
     */
    private Discrete2DPositionGrid<Character> processInput(List<String> input) {
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');

        //Save all the rock parts
        for(String line : input) {
            String[] lineParts = line.split(" -> ");
            for(int linePartIndex = 0; linePartIndex < lineParts.length -1; linePartIndex++) {
                Point p1 = this.convertToPoint(lineParts[linePartIndex]);
                Point p2 = this.convertToPoint(lineParts[linePartIndex + 1]);

                if(p1.x == p2.x) {
                    int minY = Math.min(p1.y, p2.y);
                    int maxY = Math.max(p1.y, p2.y);

                    for(int yToSave = minY; yToSave <= maxY; yToSave++) {
                        grid.setValueAtPosition(p1.x, yToSave, '#');
                    }
                }else{
                    int minX = Math.min(p1.x, p2.x);
                    int maxX = Math.max(p1.x, p2.x);

                    for(int xToSave = minX; xToSave <= maxX; xToSave++) {
                        grid.setValueAtPosition(xToSave, p1.y, '#');
                    }
                }
            }
        }

        return grid;
    }

    /**
     * Attempts to find out how many sand particles will land before they start falling out of the bottom
     * @param input List of Rock positions
     * @return The number of sand particles that will land and stay still
     */
    public long solvePartOne(List<String> input) {
        Discrete2DPositionGrid<Character> grid = this.processInput(input);

        //If the sand drops past this, we have finished!
        int maxYInDataset = grid.getMaxY();

        boolean sandDroppedPastMaxY = false;
        while(!sandDroppedPastMaxY) {
            Point currentSandPosition = new Point(500, 0);
            boolean sandCameToRest = false;
            while(!sandCameToRest) {

                //Check to see if the sand has dropped past the last block so we have finished
                if(currentSandPosition.y > maxYInDataset) {
                    sandCameToRest = true;
                    sandDroppedPastMaxY = true;

                //check to see if we can move down
                }else if(grid.getValueAtPosition(currentSandPosition.x, currentSandPosition.y + 1) == '.') {
                    currentSandPosition.y += 1;

                //Check to see if we can move down and left
                }else if(grid.getValueAtPosition(currentSandPosition.x - 1, currentSandPosition.y + 1) == '.') {
                    currentSandPosition.y += 1;
                    currentSandPosition.x -= 1;

                //Check to see if we can move down and right
                }else if(grid.getValueAtPosition(currentSandPosition.x + 1, currentSandPosition.y + 1) == '.') {
                    currentSandPosition.y += 1;
                    currentSandPosition.x += 1;

                //No more movement, set the grid and end this loop
                }else{
                    grid.setValueAtPosition(currentSandPosition, 'S');
                    sandCameToRest = true;
                }
            }
        }

        return grid.countInstancesOfValue('S');
    }


    /**
     * Attempts to find out how many sand particles will land before they block the source point
     * @param input List of Rock positions
     * @return The number of sand particles on the rocks after it has reached the source point
     */
    public long solvePartTwo(List<String> input) {
        Discrete2DPositionGrid<Character> grid = this.processInput(input);
        int floorLevel = grid.getMaxY() + 2;
        //Set the floor in the grid
        for(int xFloorToSet = -1000000; xFloorToSet <= 1000000; xFloorToSet++) {
            grid.setValueAtPosition(xFloorToSet, floorLevel, '#');
        }

        boolean sandReachedSourcePoint = false;
        while(!sandReachedSourcePoint) {
            Point currentSandPosition = new Point(500, 0);
            boolean sandCameToRest = false;
            while(!sandCameToRest) {

                //check to see if we can move down
                if(grid.getValueAtPosition(currentSandPosition.x, currentSandPosition.y + 1) == '.') {
                    currentSandPosition.y += 1;

                    //Check to see if we can move down and left
                }else if(grid.getValueAtPosition(currentSandPosition.x - 1, currentSandPosition.y + 1) == '.') {
                    currentSandPosition.y += 1;
                    currentSandPosition.x -= 1;

                    //Check to see if we can move down and right
                }else if(grid.getValueAtPosition(currentSandPosition.x + 1, currentSandPosition.y + 1) == '.') {
                    currentSandPosition.y += 1;
                    currentSandPosition.x += 1;

                    //No more movement, set the grid and end this loop
                }else{
                    if(currentSandPosition.x == 500 && currentSandPosition.y == 0) {
                        grid.setValueAtPosition(currentSandPosition, 'S');
                        sandReachedSourcePoint = true;
                        sandCameToRest = true;
                    }else {
                        grid.setValueAtPosition(currentSandPosition, 'S');
                        sandCameToRest = true;
                    }
                }
            }
        }

        return grid.countInstancesOfValue('S');
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 14);

        Day14 d = new Day14();
        long partOne = d.solvePartOne(input);
        System.out.println("The sand particles that rest before they continue to fall forever is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of sand particles until they block the source hole is "+ partTwo);

    }



}
