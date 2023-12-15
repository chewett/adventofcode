package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 14: Parabolic Reflector Dish ---
 * You reach the place where all of the mirrors were pointing: a massive parabolic reflector dish attached to the side
 * of another large mountain.
 *
 * The dish is made up of many small mirrors, but while the mirrors themselves are roughly in the shape of a parabolic
 * reflector dish, each individual mirror seems to be pointing in slightly the wrong direction. If the dish is meant
 * to focus light, all it's doing right now is sending it in a vague direction.
 *
 * This system must be what provides the energy for the lava! If you focus the reflector dish, maybe you can go where
 * it's pointing and use the light to fix the lava production.
 *
 * Upon closer inspection, the individual mirrors each appear to be connected via an elaborate system of ropes and
 * pulleys to a large metal platform below the dish. The platform is covered in large rocks of various shapes.
 * Depending on their position, the weight of the rocks deforms the platform, and the shape of the platform controls
 * which ropes move and ultimately the focus of the dish.
 *
 * In short: if you move the rocks, you can focus the dish. The platform even has a control panel on the side that
 * lets you tilt it in one of four directions! The rounded rocks (O) will roll when the platform is tilted,
 * while the cube-shaped rocks (#) will stay in place. You note the positions of all of the empty spaces (.)
 * and rocks (your puzzle input). For example:
 *
 * O....#....
 * O.OO#....#
 * .....##...
 * OO.#O....O
 * .O.....O#.
 * O.#..O.#.#
 * ..O..#O..O
 * .......O..
 * #....###..
 * #OO..#....
 * Start by tilting the lever so all of the rocks will slide north as far as they will go:
 *
 * OOOO.#.O..
 * OO..#....#
 * OO..O##..O
 * O..#.OO...
 * ........#.
 * ..#....#.#
 * ..O..#.O.O
 * ..O.......
 * #....###..
 * #....#....
 * You notice that the support beams along the north side of the platform are damaged; to ensure the platform doesn't
 * collapse, you should calculate the total load on the north support beams.
 *
 * The amount of load caused by a single rounded rock (O) is equal to the number of rows from the rock to the south
 * edge of the platform, including the row the rock is on. (Cube-shaped rocks (#) don't contribute to load.) So, the
 * amount of load caused by each rock in each row is as follows:
 *
 * OOOO.#.O.. 10
 * OO..#....#  9
 * OO..O##..O  8
 * O..#.OO...  7
 * ........#.  6
 * ..#....#.#  5
 * ..O..#.O.O  4
 * ..O.......  3
 * #....###..  2
 * #....#....  1
 * The total load is the sum of the load caused by all of the rounded rocks. In this example, the total load is 136.
 *
 * Tilt the platform so that the rounded rocks all roll north. Afterward, what is the total load on the north support
 * beams?
 *
 * --- Part Two ---
 * The parabolic reflector dish deforms, but not in a way that focuses the beam. To do that, you'll need to move the
 * rocks to the edges of the platform. Fortunately, a button on the side of the control panel labeled "spin cycle"
 * attempts to do just that!
 *
 * Each cycle tilts the platform four times so that the rounded rocks roll north, then west, then south, then east.
 * After each tilt, the rounded rocks roll as far as they can before the platform tilts in the next direction. After
 * one cycle, the platform will have finished rolling the rounded rocks in those four directions in that order.
 *
 * Here's what happens in the example above after each of the first few cycles:
 *
 * After 1 cycle:
 * .....#....
 * ....#...O#
 * ...OO##...
 * .OO#......
 * .....OOO#.
 * .O#...O#.#
 * ....O#....
 * ......OOOO
 * #...O###..
 * #..OO#....
 *
 * After 2 cycles:
 * .....#....
 * ....#...O#
 * .....##...
 * ..O#......
 * .....OOO#.
 * .O#...O#.#
 * ....O#...O
 * .......OOO
 * #..OO###..
 * #.OOO#...O
 *
 * After 3 cycles:
 * .....#....
 * ....#...O#
 * .....##...
 * ..O#......
 * .....OOO#.
 * .O#...O#.#
 * ....O#...O
 * .......OOO
 * #...O###.O
 * #.OOO#...O
 * This process should work if you leave it running long enough, but you're still worried about the north support
 * beams. To make sure they'll survive for a while, you need to calculate the total load on the north support beams
 * after 1000000000 cycles.
 *
 * In the above example, after 1000000000 cycles, the total load on the north support beams is 64.
 *
 * Run the spin cycle for 1000000000 cycles. Afterward, what is the total load on the north support beams?
 */
public class Day14 {

    /**
     * Gets the state of the grid so that we can track how it changes
     * @param grid The grid to calculate the state for
     * @return A string representing the state of the grid
     */
    public String getState(Discrete2DPositionGrid<Character> grid) {
        StringBuilder totalString = new StringBuilder();
        for(int y = 0; y <= grid.getMaxY(); y++) {
            for(int x = 0; x <= grid.getMaxX(); x++) {
                totalString.append(grid.getValueAtPosition(x, y));
            }
        }
        return totalString.toString();
    }

    /**
     * Move all the moving rocks to the north
     * @param toMoveNorth Grid to move north
     */
    public void moveNorth(Discrete2DPositionGrid<Character> toMoveNorth) {
        boolean somethingMoved = true;
        //Keep looping over until we have no more rocks that need to move
        while(somethingMoved) {
            somethingMoved = false;

            //Loop over every location and see if this location is a movable rock and we can move it in the direction
            for(int y = 1; y <= toMoveNorth.getMaxY(); y++) {
                for(int x = 0; x <= toMoveNorth.getMaxX(); x++) {
                    char curChar = toMoveNorth.getValueAtPosition(x, y);
                    char northChar = toMoveNorth.getValueAtPosition(x, y-1);
                    if(curChar == 'O' && northChar == '.') {
                        toMoveNorth.setValueAtPosition(x, y-1, curChar);
                        toMoveNorth.setValueAtPosition(x, y, '.');
                        somethingMoved = true;
                    }
                }
            }
        }
    }

    /**
     * Move all the moving rocks to the south
     * @param toMoveSouth Grid to move south
     */
    public void moveSouth(Discrete2DPositionGrid<Character> toMoveSouth) {
        boolean somethingMoved = true;
        //Keep looping over until we have no more rocks that need to move
        while(somethingMoved) {
            somethingMoved = false;

            //Loop over every location and see if this location is a movable rock and we can move it in the direction
            for(int y = toMoveSouth.getMaxY() -1; y >= 0; y--) {
                for(int x = 0; x <= toMoveSouth.getMaxX(); x++) {
                    char curChar = toMoveSouth.getValueAtPosition(x, y);
                    char southChar = toMoveSouth.getValueAtPosition(x, y+1);
                    if(curChar == 'O' && southChar == '.') {
                        toMoveSouth.setValueAtPosition(x, y+1, curChar);
                        toMoveSouth.setValueAtPosition(x, y, '.');
                        somethingMoved = true;
                    }
                }
            }
        }
    }

    /**
     * Move all the moving rocks to the west
     * @param toMoveWest Grid to move west
     */
    public void moveWest(Discrete2DPositionGrid<Character> toMoveWest) {
        boolean somethingMoved = true;
        //Keep looping over until we have no more rocks that need to move
        while(somethingMoved) {
            somethingMoved = false;

            //Loop over every location and see if this location is a movable rock and we can move it in the direction
            for(int x = 1; x <= toMoveWest.getMaxX(); x++) {
                for(int y = 0; y <= toMoveWest.getMaxY(); y++) {
                    char curChar = toMoveWest.getValueAtPosition(x, y);
                    char westChar = toMoveWest.getValueAtPosition(x-1, y);
                    if(curChar == 'O' && westChar == '.') {
                        toMoveWest.setValueAtPosition(x-1, y, curChar);
                        toMoveWest.setValueAtPosition(x, y, '.');
                        somethingMoved = true;
                    }
                }
            }
        }
    }

    /**
     * Move all the moving rocks to the east
     * @param toMoveEast Grid to move east
     */
    public void moveEast(Discrete2DPositionGrid<Character> toMoveEast) {
        boolean somethingMoved = true;
        //Keep looping over until we have no more rocks that need to move
        while(somethingMoved) {
            somethingMoved = false;

            //Loop over every location and see if this location is a movable rock and we can move it in the direction
            for(int x = toMoveEast.getMaxX()-1; x >= 0; x--) {
                for(int y = 0; y <= toMoveEast.getMaxY(); y++) {
                    char curChar = toMoveEast.getValueAtPosition(x, y);
                    char eastChar = toMoveEast.getValueAtPosition(x+1, y);
                    if(curChar == 'O' && eastChar == '.') {
                        toMoveEast.setValueAtPosition(x+1, y, curChar);
                        toMoveEast.setValueAtPosition(x, y, '.');
                        somethingMoved = true;
                    }
                }
            }
        }
    }

    /**
     * Calculate the load on the grid
     * @param input Grid to calculate the load of
     * @return Load on the grid
     */
    public long calculateLoad(Discrete2DPositionGrid<Character> input) {
        List<Point> oPositions = input.getPositionsOfValue('O');
        long totalLoad = 0;
        for(Point p : oPositions) {
            totalLoad += input.getMaxY() - p.y + 1;
        }
        return totalLoad;
    }

    /**
     * Move all the movable rocks north and then work out the load on the grid
     * @param input Grid to move the rocks of
     * @return Load on the grid
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Character> movedNorth = input.clone();
        this.moveNorth(movedNorth);
        return this.calculateLoad(movedNorth);
    }

    /**
     * Move all of the rocks north, west, south, east 1000000000 times
     * @param input Grid to move the rocks for
     * @return The final load after 1000000000 moves
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Character> movedNorth = input.clone();
        Map<String, Long> cycleMap = new HashMap<>();

        cycleMap.put(this.getState(movedNorth), 0L);

        //What we are going to do is keep track of the state and stop when we work out when we have found a cycle
        long cyclePeriod = 0;
        long cycleNo = 0;
        while(cycleNo < 1000000000 && cyclePeriod == 0) {
            //Do the dance of north, west, south, east
            this.moveNorth(movedNorth);
            this.moveWest(movedNorth);
            this.moveSouth(movedNorth);
            this.moveEast(movedNorth);
            cycleNo++;

            String thisLoad = this.getState(movedNorth);
            if(cycleMap.containsKey(thisLoad)) {
                //Awesome stuff! We have found a cycle, now we can use that to cheat :D
                cyclePeriod = cycleNo - cycleMap.get(thisLoad);
            }else{
                cycleMap.put(thisLoad, cycleNo);
            }
        }

        //Keep looping over the cycle until we get to the point of "just before" the 1000000000 value
        long stopBefore = 1000000000 - cyclePeriod;
        while(cycleNo < stopBefore) {
            cycleNo += cyclePeriod;
        }

        //Once we are near, we do the final steps ourselves
        while(cycleNo < 1000000000) {
            this.moveNorth(movedNorth);
            this.moveWest(movedNorth);
            this.moveSouth(movedNorth);
            this.moveEast(movedNorth);
            cycleNo++;
        }

        //And now we have reached the 1000000000'th cycle number and we can check the load
        return this.calculateLoad(movedNorth);
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 14);

        Day14 d = new Day14();
        long partOne = d.solvePartOne(input);
        System.out.println("The load after one move north " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The load after 1000000000 cycles in all the directions " + partTwo);
    }
}


