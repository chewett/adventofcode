package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day14Test {

    //TODO: Add the example in, but handle the fact that it's actually smaller than the main one

    /**
     * Test Day 14 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(224969976, d.solvePartOne(input));
    }

    /**
     * Test Day 14 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(7892, d.solvePartTwo(input));
    }

}
