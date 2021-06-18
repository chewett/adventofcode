package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day15Test {

    /**
     * Test Day 15 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day15 d = new Day15();
        String numbers = "1,3,2";
        Assert.assertEquals(1, d.solvePartOne(numbers));
    }

    /**
     * Test Day 15 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        Day15 d = new Day15();
        String numbers = "2,1,3";
        Assert.assertEquals(10, d.solvePartOne(numbers));
    }

    /**
     * Test Day 15 part one with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartOne() {
        Day15 d = new Day15();
        String numbers = "1,2,3";
        Assert.assertEquals(27, d.solvePartOne(numbers));
    }

    /**
     * Test Day 15 part one with the example input 4 (with the known result)
     */
    @Test
    public void testExampleInput4PartOne() {
        Day15 d = new Day15();
        String numbers = "2,3,1";
        Assert.assertEquals(78, d.solvePartOne(numbers));
    }

    /**
     * Test Day 15 part one with the example input 5 (with the known result)
     */
    @Test
    public void testExampleInput5PartOne() {
        Day15 d = new Day15();
        String numbers = "3,2,1";
        Assert.assertEquals(438, d.solvePartOne(numbers));
    }

    /**
     * Test Day 15 part one with the example input 6 (with the known result)
     */
    @Test
    public void testExampleInput6PartOne() {
        Day15 d = new Day15();
        String numbers = "3,1,2";
        Assert.assertEquals(1836, d.solvePartOne(numbers));
    }

    /**
     * Test Day 15 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String numbers = ProblemLoader.loadProblemIntoString(2020, 15);
        Day15 d = new Day15();
        long partOneAnswer = d.solvePartOne(numbers);

        Assert.assertEquals(276, partOneAnswer);
    }

    /**
     * Test Day 15 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {

    }

    /**
     * Test Day 15 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String numbers = ProblemLoader.loadProblemIntoString(2020, 15);
        Day15 d = new Day15();

        Assert.assertEquals(31916, d.solvePartTwo(numbers));
    }

}
