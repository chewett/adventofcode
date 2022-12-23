package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day17Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
    }

    /**
     * Test Day 17 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String jetPattern = this.getExampleInput();
        Day17 d = new Day17();
        Assertions.assertEquals(3068, d.solvePartOne(jetPattern));
    }


    /**
     * Test Day 17 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String jetPattern = ProblemLoader.loadProblemIntoString(2022, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(3235, d.solvePartOne(jetPattern));
    }

    /**
     * Test Day 17 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String jetPattern = this.getExampleInput();
        Day17 d = new Day17();
        Assertions.assertEquals(1514285714288L, d.solvePartTwo(jetPattern));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String jetPattern = ProblemLoader.loadProblemIntoString(2022, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(1591860465110L, d.solvePartTwo(jetPattern));
    }


}
