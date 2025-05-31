package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day11Test {

    /**
     * Test Day 11 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(2720, d.solvePartOne(input));
    }

    /**
     * Test Day 11 part two with the real input (with the correct answer)
     *
     * FIXME: Work out how to nicely check the letters returned by a unit test and not visually getting the answer
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(-1, d.solvePartTwo(input));
    }

}
