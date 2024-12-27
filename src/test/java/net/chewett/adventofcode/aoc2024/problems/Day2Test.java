package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day2Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("7 6 4 2 1");
        input.add("1 2 7 8 9");
        input.add("9 7 6 2 1");
        input.add("1 3 2 4 5");
        input.add("8 6 4 4 1");
        input.add("1 3 6 7 9");

        return input;
    }

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(2, d.solvePartOne(input));
    }


    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(432, d.solvePartOne(input));
    }

    /**
     * Test Day 2 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(4, d.solvePartTwo(input));
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(488, d.solvePartTwo(input));
    }

}
