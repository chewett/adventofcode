package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day10Test {

    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealInputPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2015, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(252594, d.solvePartOne(input).length());
    }

    /**
     * Test Day 10 part two with the real input (and the correct answer)
     */
    @Test
    public void testRealInputPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2015, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(3579328, d.solvePartTwo(input).length());
    }

}
