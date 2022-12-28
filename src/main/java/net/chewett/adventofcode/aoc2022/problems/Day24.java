package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.aoc2022.CustomPriorityQueueElement;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *--- Day 24: Blizzard Basin ---
 * With everything replanted for next year (and with elephants and monkeys to tend the grove), you and the Elves leave
 * for the extraction point.
 *
 * Partway up the mountain that shields the grove is a flat, open area that serves as the extraction point. It's a bit
 * of a climb, but nothing the expedition can't handle.
 *
 * At least, that would normally be true; now that the mountain is covered in snow, things have become more difficult
 * than the Elves are used to.
 *
 * As the expedition reaches a valley that must be traversed to reach the extraction site, you find that strong,
 * turbulent winds are pushing small blizzards of snow and sharp ice around the valley. It's a good thing everyone
 * packed warm clothes! To make it across safely, you'll need to find a way to avoid them.
 *
 * Fortunately, it's easy to see all of this from the entrance to the valley, so you make a map of the valley and the
 * blizzards (your puzzle input). For example:
 *
 * #.#####
 * #.....#
 * #>....#
 * #.....#
 * #...v.#
 * #.....#
 * #####.#
 * The walls of the valley are drawn as #; everything else is ground. Clear ground - where there is currently no
 * blizzard - is drawn as .. Otherwise, blizzards are drawn with an arrow indicating their direction of motion:
 * up (^), down (v), left (<), or right (>).
 *
 * The above map includes two blizzards, one moving right (>) and one moving down (v). In one minute, each blizzard
 * moves one position in the direction it is pointing:
 *
 * #.#####
 * #.....#
 * #.>...#
 * #.....#
 * #.....#
 * #...v.#
 * #####.#
 * Due to conservation of blizzard energy, as a blizzard reaches the wall of the valley, a new blizzard forms on the
 * opposite side of the valley moving in the same direction. After another minute, the bottom downward-moving blizzard
 * has been replaced with a new downward-moving blizzard at the top of the valley instead:
 *
 * #.#####
 * #...v.#
 * #..>..#
 * #.....#
 * #.....#
 * #.....#
 * #####.#
 * Because blizzards are made of tiny snowflakes, they pass right through each other. After another minute, both
 * blizzards temporarily occupy the same position, marked 2:
 *
 * #.#####
 * #.....#
 * #...2.#
 * #.....#
 * #.....#
 * #.....#
 * #####.#
 * After another minute, the situation resolves itself, giving each blizzard back its personal space:
 *
 * #.#####
 * #.....#
 * #....>#
 * #...v.#
 * #.....#
 * #.....#
 * #####.#
 * Finally, after yet another minute, the rightward-facing blizzard on the right is replaced with a new one on the left
 * facing the same direction:
 *
 * #.#####
 * #.....#
 * #>....#
 * #.....#
 * #...v.#
 * #.....#
 * #####.#
 * This process repeats at least as long as you are observing it, but probably forever.
 *
 * Here is a more complex example:
 *
 * #.######
 * #>>.<^<#
 * #.<..<<#
 * #>v.><>#
 * #<^v^^>#
 * ######.#
 * Your expedition begins in the only non-wall position in the top row and needs to reach the only non-wall position in
 * the bottom row. On each minute, you can move up, down, left, or right, or you can wait in place. You and the
 * blizzards act simultaneously, and you cannot share a position with a blizzard.
 *
 * In the above example, the fastest way to reach your goal requires 18 steps. Drawing the position of the expedition
 * as E, one way to achieve this is:
 *
 * Initial state:
 * #E######
 * #>>.<^<#
 * #.<..<<#
 * #>v.><>#
 * #<^v^^>#
 * ######.#
 *
 * Minute 1, move down:
 * #.######
 * #E>3.<.#
 * #<..<<.#
 * #>2.22.#
 * #>v..^<#
 * ######.#
 *
 * Minute 2, move down:
 * #.######
 * #.2>2..#
 * #E^22^<#
 * #.>2.^>#
 * #.>..<.#
 * ######.#
 *
 * Minute 3, wait:
 * #.######
 * #<^<22.#
 * #E2<.2.#
 * #><2>..#
 * #..><..#
 * ######.#
 *
 * Minute 4, move up:
 * #.######
 * #E<..22#
 * #<<.<..#
 * #<2.>>.#
 * #.^22^.#
 * ######.#
 *
 * Minute 5, move right:
 * #.######
 * #2Ev.<>#
 * #<.<..<#
 * #.^>^22#
 * #.2..2.#
 * ######.#
 *
 * Minute 6, move right:
 * #.######
 * #>2E<.<#
 * #.2v^2<#
 * #>..>2>#
 * #<....>#
 * ######.#
 *
 * Minute 7, move down:
 * #.######
 * #.22^2.#
 * #<vE<2.#
 * #>>v<>.#
 * #>....<#
 * ######.#
 *
 * Minute 8, move left:
 * #.######
 * #.<>2^.#
 * #.E<<.<#
 * #.22..>#
 * #.2v^2.#
 * ######.#
 *
 * Minute 9, move up:
 * #.######
 * #<E2>>.#
 * #.<<.<.#
 * #>2>2^.#
 * #.v><^.#
 * ######.#
 *
 * Minute 10, move right:
 * #.######
 * #.2E.>2#
 * #<2v2^.#
 * #<>.>2.#
 * #..<>..#
 * ######.#
 *
 * Minute 11, wait:
 * #.######
 * #2^E^2>#
 * #<v<.^<#
 * #..2.>2#
 * #.<..>.#
 * ######.#
 *
 * Minute 12, move down:
 * #.######
 * #>>.<^<#
 * #.<E.<<#
 * #>v.><>#
 * #<^v^^>#
 * ######.#
 *
 * Minute 13, move down:
 * #.######
 * #.>3.<.#
 * #<..<<.#
 * #>2E22.#
 * #>v..^<#
 * ######.#
 *
 * Minute 14, move right:
 * #.######
 * #.2>2..#
 * #.^22^<#
 * #.>2E^>#
 * #.>..<.#
 * ######.#
 *
 * Minute 15, move right:
 * #.######
 * #<^<22.#
 * #.2<.2.#
 * #><2>E.#
 * #..><..#
 * ######.#
 *
 * Minute 16, move right:
 * #.######
 * #.<..22#
 * #<<.<..#
 * #<2.>>E#
 * #.^22^.#
 * ######.#
 *
 * Minute 17, move down:
 * #.######
 * #2.v.<>#
 * #<.<..<#
 * #.^>^22#
 * #.2..2E#
 * ######.#
 *
 * Minute 18, move down:
 * #.######
 * #>2.<.<#
 * #.2v^2<#
 * #>..>2>#
 * #<....>#
 * ######E#
 * What is the fewest number of minutes required to avoid the blizzards and reach the goal?
 *
 * --- Part Two ---
 * As the expedition reaches the far side of the valley, one of the Elves looks especially dismayed:
 *
 * He forgot his snacks at the entrance to the valley!
 *
 * Since you're so good at dodging blizzards, the Elves humbly request that you go back for his snacks. From the same
 * initial conditions, how quickly can you make it from the start to the goal, then back to the start, then back to
 * the goal?
 *
 * In the above example, the first trip to the goal takes 18 minutes, the trip back to the start takes 23 minutes, and
 * the trip back to the goal again takes 13 minutes, for a total time of 54 minutes.
 *
 * What is the fewest number of minutes required to reach the goal, go back to the start, then reach the goal again?
 */
public class Day24 {

    /**
     * Given a list of all the blizzards and the walls this will increase the simulation time by one minute (adjusting
     * all the point locations in the list) and then return a grid of blizzard (and wall) locations
     * @param moveRight Blizzards moving left to right
     * @param moveLeft Blizzards moving right to let
     * @param moveUp Blizzards moving down to up
     * @param moveDown Blizzards moving up to down
     * @param walls Walls that cannot be stepped on
     * @return A grid showing the blizzard and wall locations
     */
    private Discrete2DPositionGrid<Character> incrementSimulationByOneMinute(
            List<Point> moveRight, List<Point> moveLeft, List<Point> moveUp, List<Point> moveDown, List<Point> walls
    ) {

        Discrete2DPositionGrid<Character> map = new Discrete2DPositionGrid<>('.');
        //Set up all the walls first
        for(Point wall : walls) {
            map.setValueAtPosition(wall, '#');
        }

        for(Point right : moveRight) {
            right.x++;
            if(right.x == map.getMaxX()) {
                right.x = map.getMinX() + 1;
            }
        }

        for(Point left : moveLeft) {
            left.x--;
            if(left.x == map.getMinX()) {
                left.x = map.getMaxX() - 1;
            }
        }

        for(Point up : moveUp) {
            up.y--;
            if(up.y == map.getMinY()) {
                up.y = map.getMaxY() - 1;
            }
        }

        for(Point down : moveDown) {
            down.y++;
            if(down.y == map.getMaxY()) {
                down.y = map.getMinY() + 1;
            }
        }

        for(Point p : moveRight) {
            map.setValueAtPosition(p, 'B');
        }
        for(Point p : moveLeft) {
            map.setValueAtPosition(p, 'B');
        }
        for(Point p : moveUp) {
            map.setValueAtPosition(p, 'B');
        }
        for(Point p : moveDown) {
            map.setValueAtPosition(p, 'B');
        }

        return map;
    }

    /**
     * Simple Manhattan distance function which I need to abstract out to my special point
     * @param p1 First point to compare
     * @param p2 Second point to compare
     * @return Manhattan distance of the two points
     */
    public int getManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    /**
     * Gets the full list of blizzard mapping to minutes
     * @param moveRight Blizzards moving left to right
     * @param moveLeft Blizzards moving right to let
     * @param moveUp Blizzards moving down to up
     * @param moveDown Blizzards moving up to down
     * @param walls Walls that cannot be stepped on
     * @return A list of blizzard mappings for every possible minute
     */
    private Map<Integer, Discrete2DPositionGrid<Character>> getBlizzardMapping(List<Point> moveRight, List<Point> moveLeft, List<Point> moveUp, List<Point> moveDown, List<Point> walls) {
        Map<Integer, Discrete2DPositionGrid<Character>> blizzardMapping = new HashMap<>();
        //Blizzard is 120 * 25, the lowest common multiple is 600 so after 600 we repeat!
        //For the example this is overkill but that's fine
        for(int minuteId = 1; minuteId <= 600; minuteId++) {
            blizzardMapping.put(minuteId, this.incrementSimulationByOneMinute(moveRight, moveLeft, moveUp, moveDown, walls));
        }

        return blizzardMapping;
    }

    /**
     * Solves moving from the start to the end point given the initial blizzard mapping and the starting minute
     * @param startingMinute The minute to start the simulation from
     * @param startingPoint The point to start from
     * @param endingPoint The point to move to
     * @param blizzardMapping The blizzard mapping of all the possible blizzard state
     * @return The minute that the objective is met
     */
    private int solveFromStartingMinute(int startingMinute, Point startingPoint, Point endingPoint, Map<Integer, Discrete2DPositionGrid<Character>> blizzardMapping) {
        PriorityQueue<CustomPriorityQueueElement<Integer, Point>> processQueue = new PriorityQueue<>();

        int manHatDistance = this.getManhattanDistance(startingPoint, endingPoint);
        processQueue.add(new CustomPriorityQueueElement<Integer, Point>(manHatDistance, startingMinute, startingPoint));

        int currentBestValue = 99999999;
        Set<String> visited = new HashSet<>();

        //Keep going til we find it!
        while(!processQueue.isEmpty()) {
            CustomPriorityQueueElement<Integer, Point> currentState = processQueue.poll();

            int thisSimulationMinute = currentState.getValueOne();
            int thisNewSimulationMinute = thisSimulationMinute + 1;
            Point thisSimulationPoint = currentState.getValueTwo();

            //Don't bother if we can't beat the best
            int manhatToEnd = this.getManhattanDistance(thisSimulationPoint, endingPoint);
            if((manhatToEnd + thisNewSimulationMinute) > currentBestValue) {
                continue;
            }

            int blizzardMinute = thisNewSimulationMinute;
            //Probably use mod but need to check the maths here
            while(blizzardMinute > 600) {
                blizzardMinute -= 600;
            }

            Discrete2DPositionGrid<Character> map = blizzardMapping.get(blizzardMinute);

            List<Point> possibleMoves = map.getDirectlyAdjacentPoints(thisSimulationPoint);
            //Add the possibility of not moving
            possibleMoves.add(new Point(thisSimulationPoint));
            for(Point possibleMove : possibleMoves) {
                if(possibleMove.equals(endingPoint)) {
                    currentBestValue = Math.min(thisNewSimulationMinute, currentBestValue);
                }else {
                    int thisManhatDistance = this.getManhattanDistance(possibleMove, endingPoint);
                    if (map.getValueAtPosition(possibleMove) == '.' && (thisNewSimulationMinute + thisManhatDistance) < currentBestValue) {

                        String setValue = thisNewSimulationMinute + "_" + possibleMove.x + "_" + possibleMove.y;
                        if(!visited.contains(setValue)) {
                            visited.add(setValue);
                            processQueue.add(new CustomPriorityQueueElement<>(thisManhatDistance, thisNewSimulationMinute, possibleMove));
                        }
                    }
                }
            }
        }

        return currentBestValue;
    }

    /**
     * Solves moving from the top left to the button right and work out how long it takes
     * @param grid The initial grid (blizzard) state
     * @return The number of minutes to move from the top left to the bottom right
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> grid) {
        List<Point> moveRight = grid.getPositionsOfValue('>');
        List<Point> moveLeft = grid.getPositionsOfValue('<');
        List<Point> moveUp = grid.getPositionsOfValue('^');
        List<Point> moveDown = grid.getPositionsOfValue('v');
        List<Point> walls = grid.getPositionsOfValue('#');

        int minX = grid.getMinX();
        int minY = grid.getMinY();
        int maxX = grid.getMaxX();
        int maxY = grid.getMaxY();

        Point startingPoint = null;
        Point endingPoint = null;
        for(int x = minX; x <= maxX; x++) {
            if(grid.getValueAtPosition(x, minY) == '.') {
                startingPoint = new Point(x, minY);
            }
            if(grid.getValueAtPosition(x, maxY) == '.') {
                endingPoint = new Point(x, maxY);
            }
        }

        Map<Integer, Discrete2DPositionGrid<Character>> blizzardMapping = this.getBlizzardMapping(moveRight, moveLeft, moveUp, moveDown, walls);
        return this.solveFromStartingMinute(0, startingPoint, endingPoint, blizzardMapping);
    }


    /**
     * Solves moving from the top left to the button right and back, and back again, and work out how long it takes
     * @param grid The initial grid (blizzard) state
     * @return The number of minutes to move from the top left to the bottom right
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> grid) {
        List<Point> moveRight = grid.getPositionsOfValue('>');
        List<Point> moveLeft = grid.getPositionsOfValue('<');
        List<Point> moveUp = grid.getPositionsOfValue('^');
        List<Point> moveDown = grid.getPositionsOfValue('v');
        List<Point> walls = grid.getPositionsOfValue('#');

        int minX = grid.getMinX();
        int minY = grid.getMinY();
        int maxX = grid.getMaxX();
        int maxY = grid.getMaxY();

        Point startingPoint = null;
        Point endingPoint = null;
        for(int x = minX; x <= maxX; x++) {
            if(grid.getValueAtPosition(x, minY) == '.') {
                startingPoint = new Point(x, minY);
            }
            if(grid.getValueAtPosition(x, maxY) == '.') {
                endingPoint = new Point(x, maxY);
            }
        }

        Map<Integer, Discrete2DPositionGrid<Character>> blizzardMapping = this.getBlizzardMapping(moveRight, moveLeft, moveUp, moveDown, walls);
        int firstMoveTime = this.solveFromStartingMinute(0, startingPoint, endingPoint, blizzardMapping);
        int secondMoveTime = this.solveFromStartingMinute(firstMoveTime, endingPoint, startingPoint, blizzardMapping);
        return this.solveFromStartingMinute(secondMoveTime, startingPoint, endingPoint, blizzardMapping);
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> grid = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2022, 24);

        Day24 d = new Day24();
        long partOne = d.solvePartOne(grid);
        System.out.println("The time taken to go from the top to the bottom is " + partOne);

        long partTwo = d.solvePartTwo(grid);
        System.out.println("The time taken to go from the top, bottom, top, and bottom again is "+ partTwo);

    }


}
