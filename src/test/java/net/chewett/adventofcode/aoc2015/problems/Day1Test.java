package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

public class Day1Test {

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String inputString = "))(((((";
        Day1 d = new Day1();
        long partOneAnswer = d.solvePartOne(inputString);

        Assert.assertEquals(3, partOneAnswer);
    }

    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String problemInput = ProblemLoader.loadProblemIntoString(2015, 1);
        Day1 d = new Day1();
        long partOneAnswer = d.solvePartOne(problemInput);

        Assert.assertEquals(232, partOneAnswer);
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String puzzleInput = "()())";

        Day1 d = new Day1();
        long partTwoAnswer = d.solvePartTwo(puzzleInput);

        Assert.assertEquals(5, partTwoAnswer);
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String problemInput = ProblemLoader.loadProblemIntoString(2015, 1);
        Day1 d = new Day1();
        long partTwoAnswer = d.solvePartTwo(problemInput);

        Assert.assertEquals(1783, partTwoAnswer);
    }

}
