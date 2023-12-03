package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Day11Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Integer> getExampleInput() {
        List<String> octopi = new ArrayList<>();
        octopi.add("5483143223");
        octopi.add("2745854711");
        octopi.add("5264556173");
        octopi.add("6141336146");
        octopi.add("6357385478");
        octopi.add("4167524645");
        octopi.add("2176841721");
        octopi.add("6882881134");
        octopi.add("4846848554");
        octopi.add("5283751526");

        List<List<Character>> octopiArray = FormatConversion.convertStringArrayToCharListList(octopi);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(octopiArray);
    }

    /**
     * Test Day 11 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Integer> octopi = this.getExampleInput();
        Day11 d = new Day11();
        Assertions.assertEquals(1656, d.solvePartOne(octopi));
    }


    /**
     * Test Day 11 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Integer> octopi = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2021, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(1725, d.solvePartOne(octopi));
    }

    /**
     * Test Day 11 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Integer> octopi = this.getExampleInput();
        Day11 d = new Day11();
        Assertions.assertEquals(195, d.solvePartTwo(octopi));
    }

    /**
     * Test Day 11 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Integer> octopi = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2021, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(308, d.solvePartTwo(octopi));
    }
    
}
