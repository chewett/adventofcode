package net.chewett.adventofcode.aoc2022.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day14Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("498,4 -> 498,6 -> 496,6");
        input.add("503,4 -> 502,4 -> 502,9 -> 494,9");

        return input;
    }

    /**
     * Test Day 14 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(24, d.solvePartOne(report));
    }


    /**
     * Test Day 14 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(674, d.solvePartOne(report));
    }

    /**
     * Test Day 14 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(93, d.solvePartTwo(report));
    }

    /**
     * Test Day 14 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(24958, d.solvePartTwo(report));
    }


}
