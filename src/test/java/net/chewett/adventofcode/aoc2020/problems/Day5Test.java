package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day5Test {


    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 5);
        Day5 d = new Day5();
        int partOneAnswer = d.solvePartOne(lines);

        Assertions.assertEquals(915, partOneAnswer);
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 5);
        Day5 d = new Day5();
        long partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(699, partTwoAnswer);
    }

}
