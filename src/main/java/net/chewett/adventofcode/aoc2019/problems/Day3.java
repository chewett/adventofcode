package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.wiring.WiringDistanceCalculator;

import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/3
 * Go have a try yourself!
 * ----------------------------------------------------------
 *
 * The number of steps a wire takes is the total number of grid squares the wire has entered to get to that location,
 * including the intersection being considered. Again consider the example from above:
 *
 * ...........
 * .+-----+...
 * .|.....|...
 * .|..+--X-+.
 * .|..|..|.|.
 * .|.-X--+.|.
 * .|..|....|.
 * .|.......|.
 * .o-------+.
 * ...........
 * In the above example, the intersection closest to the central port is reached after 8+5+5+2 = 20 steps by the
 * first wire and 7+6+4+3 = 20 steps by the second wire for a total of 20+20 = 40 steps.
 *
 * However, the top-right intersection is better: the first wire takes only 8+5+2 = 15 and the second wire takes
 * only 7+6+2 = 15, a total of 15+15 = 30 steps.
 *
 * Here are the best steps for the extra examples from above:
 *
 * R75,D30,R83,U83,L12,D49,R71,U7,L72
 * U62,R66,U55,R34,D71,R55,D58,R83 = 610 steps
 * R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
 * U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = 410 steps
 * What is the fewest combined steps the wires must take to reach an intersection?
 */
public class Day3 {

    public long solvePartOne(List<String> input) {
        String wireOneString = input.get(0);
        String wireTwoString = input.get(1);
        return WiringDistanceCalculator.calculateManhattan(wireOneString, wireTwoString);
    }

    public long solvePartTwo(List<String> input) {
        String wireOneString = input.get(0);
        String wireTwoString = input.get(1);
        return WiringDistanceCalculator.calculateWireDistance(wireOneString, wireTwoString);
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 3);

        Day3 d = new Day3();
        long partOne = d.solvePartOne(input);
        System.out.println("Manhattan distance "+ partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("Wire distance added together "+ partTwo);
    }
}


