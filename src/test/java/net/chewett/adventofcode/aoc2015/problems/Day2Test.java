package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day2Test {

    /**
     * Test Day 2 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> presents = new ArrayList<>();
        presents.add("2x3x4");
        Day2 d = new Day2();
        long partOneAnswer = d.solvePartOne(presents);

        Assert.assertEquals(58L, partOneAnswer);
    }

    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> presents = ProblemLoader.loadProblemIntoStringArray(2015, 2);
        Day2 d = new Day2();
        long partOneAnswer = d.solvePartOne(presents);

        Assert.assertEquals(1598415L, partOneAnswer);
    }

    /**
     * Test Day 2 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> presents = new ArrayList<>();
        presents.add("2x3x4");
        Day2 d = new Day2();
        long partTwoAnswer = d.solvePartTwo(presents);

        Assert.assertEquals(34, partTwoAnswer);
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> presents = ProblemLoader.loadProblemIntoStringArray(2015, 2);
        Day2 d = new Day2();
        long partTwoAnswer = d.solvePartTwo(presents);

        Assert.assertEquals(3812909, partTwoAnswer);
    }




}
