package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day15Test {

    /**
     * Test Day 15 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(208, d.solvePartOne(input));
    }

    /**
     * Test Day 15 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(306, d.solvePartTwo(input));
    }

}
