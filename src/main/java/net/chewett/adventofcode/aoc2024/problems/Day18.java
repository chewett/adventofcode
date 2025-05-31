package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.aoc2024.DirectionPositionCost;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 18: RAM Run ---
 * You and The Historians look a lot more pixelated than you remember. You're inside a computer at the North Pole!
 *
 * Just as you're about to check out your surroundings, a program runs up to you. "This region of memory isn't safe!
 * The User misunderstood what a pushdown automaton is and their algorithm is pushing whole bytes down on top of us!
 * Run!"
 *
 * The algorithm is fast - it's going to cause a byte to fall into your memory space once every nanosecond! Fortunately,
 * you're faster, and by quickly scanning the algorithm, you create a list of which bytes will fall (your puzzle input)
 * in the order they'll land in your memory space.
 *
 * Your memory space is a two-dimensional grid with coordinates that range from 0 to 70 both horizontally and
 * vertically. However, for the sake of example, suppose you're on a smaller grid with coordinates that range from
 * 0 to 6 and the following list of incoming byte positions:
 *
 * 5,4
 * 4,2
 * 4,5
 * 3,0
 * 2,1
 * 6,3
 * 2,4
 * 1,5
 * 0,6
 * 3,3
 * 2,6
 * 5,1
 * 1,2
 * 5,5
 * 2,5
 * 6,5
 * 1,4
 * 0,4
 * 6,4
 * 1,1
 * 6,1
 * 1,0
 * 0,5
 * 1,6
 * 2,0
 * Each byte position is given as an X,Y coordinate, where X is the distance from the left edge of your memory space
 * and Y is the distance from the top edge of your memory space.
 *
 * You and The Historians are currently in the top left corner of the memory space (at 0,0) and need to reach the exit
 * in the bottom right corner (at 70,70 in your memory space, but at 6,6 in this example). You'll need to simulate the
 * falling bytes to plan out where it will be safe to run; for now, simulate just the first few bytes falling into your
 * memory space.
 *
 * As bytes fall into your memory space, they make that coordinate corrupted. Corrupted memory coordinates cannot be
 * entered by you or The Historians, so you'll need to plan your route carefully. You also cannot leave the boundaries
 * of the memory space; your only hope is to reach the exit.
 *
 * In the above example, if you were to draw the memory space after the first 12 bytes have fallen (using . for safe
 * and # for corrupted), it would look like this:
 *
 * ...#...
 * ..#..#.
 * ....#..
 * ...#..#
 * ..#..#.
 * .#..#..
 * #.#....
 * You can take steps up, down, left, or right. After just 12 bytes have corrupted locations in your memory space,
 * the shortest path from the top left corner to the exit would take 22 steps. Here (marked with O) is one such path:
 *
 * OO.#OOO
 * .O#OO#O
 * .OOO#OO
 * ...#OO#
 * ..#OO#.
 * .#.O#..
 * #.#OOOO
 * Simulate the first kilobyte (1024 bytes) falling onto your memory space. Afterward, what is the minimum number of
 * steps needed to reach the exit?
 *
 * --- Part Two ---
 * The Historians aren't as used to moving around in this pixelated universe as you are. You're afraid they're not
 * going to be fast enough to make it to the exit before the path is completely blocked.
 *
 * To determine how fast everyone needs to go, you need to determine the first byte that will cut off the path to the
 * exit.
 *
 * In the above example, after the byte at 1,1 falls, there is still a path to the exit:
 *
 * O..#OOO
 * O##OO#O
 * O#OO#OO
 * OOO#OO#
 * ###OO##
 * .##O###
 * #.#OOOO
 * However, after adding the very next byte (at 6,1), there is no longer a path to the exit:
 *
 * ...#...
 * .##..##
 * .#..#..
 * ...#..#
 * ###..##
 * .##.###
 * #.#....
 * So, in this example, the coordinates of the first byte that prevents the exit from being reachable are 6,1.
 *
 * Simulate more of the bytes that are about to corrupt your memory space. What are the coordinates of the first byte
 * that will prevent the exit from being reachable from your starting position? (Provide the answer as two integers
 * separated by a comma with no other characters.)
 */
public class Day18 {

    public Day18() {
        this(true);
    }

    private int size;
    private int bytesToSimulate;

    public Day18(boolean largeSize) {
        if(largeSize) {
            this.size = 71;
            this.bytesToSimulate = 1024;
        }else{
            this.size = 7;
            this. bytesToSimulate = 12;
        }
    }

    /**
     * Given the input of the puzzle and the number of bytes to simulate this works out how many steps it takes
     * to reach the end from the start. It returns -1 if there isn't a way to get out
     * @param input Input of broken bytes
     * @param bytesToSimulate Number of bytes falling to simulate
     * @return Number of steps to the end or -1 if there isn't a path
     */
    public long simulateAndFindNumberOfStepsToEnd(List<String> input, int bytesToSimulate) {
        //Just set up the grid with movable things
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('#');
        for(int x = 0; x < this.size; x++) {
            for(int y = 0; y < this.size; y++) {
                grid.setValueAtPosition(x, y, '.');
            }
        }

        //Mark all the fallen bytes into the grid
        for(int bytesFallen = 0; bytesFallen < bytesToSimulate; bytesFallen++) {
            String[] memoryParts = input.get(bytesFallen).split(",");
            int x = Integer.parseInt(memoryParts[0]);
            int y = Integer.parseInt(memoryParts[1]);
            grid.setValueAtPosition(x, y, '#');
        }

        //Simple breadth first search with a priority queue
        PriorityQueue<DirectionPositionCost> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        Discrete2DPositionGrid<Integer> visited = new Discrete2DPositionGrid<>(Integer.MAX_VALUE);
        Point end = new Point(grid.getMaxX(), grid.getMaxY());
        pq.add(new DirectionPositionCost(0, new Point(0,0), 0));
        while(pq.size() > 0) {
            DirectionPositionCost cur = pq.poll();
            //We found the end!
            if(cur.pos.equals(end)) {
                return cur.cost;
            }

            //If we visited this point before at a higher or equal cost give up
            if(visited.getValueAtPosition(cur.pos) <= cur.cost) {
                continue;
            }

            visited.setValueAtPosition(cur.pos, (int)cur.cost);

            //Add adjacent points
            List<Point> adjacentPoints = grid.getDirectlyAdjacentPoints(cur.pos);
            for(Point adjacentPoint : adjacentPoints) {
                if(grid.getValueAtPosition(adjacentPoint) == '.') {
                    if(visited.getValueAtPosition(adjacentPoint) > cur.cost+1) {
                        pq.add(new DirectionPositionCost(0, adjacentPoint, cur.cost + 1));
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Simulates the input with 1024 bytes and work out how many steps you need to take to get to the end
     * @param input Bytes falling
     * @return Length to the end after simulating 1024 bytes falling
     */
    public long solvePartOne(List<String> input) {
        return this.simulateAndFindNumberOfStepsToEnd(input, this.bytesToSimulate);
    }

    /**
     * Work out which byte makes the escape impossible by just iterating over each one and seeing if there is now a path
     *
     * The "better" way would be to iteratively run the maze and then add in the extra piece, and do it again, but
     * I was being lazy and it's fast enough
     *
     * @param input Bytes falling
     * @return The first byte that falls which will block the way out
     */
    public String solvePartTwo(List<String> input) {
        long steps = 1;
        int bytesToSimulate = this.bytesToSimulate;
        while(steps != -1) {
            bytesToSimulate++;
            steps = this.simulateAndFindNumberOfStepsToEnd(input, bytesToSimulate);
        }

        return input.get(bytesToSimulate-1);
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 18);

        Day18 d = new Day18();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of steps to the exit is " + partOne);

        String partTwo = d.solvePartTwo(input);
        System.out.println("The first byte to fall that blocks the exit path is " + partTwo);
    }
}


