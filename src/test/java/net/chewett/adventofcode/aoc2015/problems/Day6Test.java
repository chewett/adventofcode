package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day6Test {

    /**
     * Test Day 6 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> instructions = new ArrayList<>();
        instructions.add("turn on 0,0 through 999,999");
        Day6 d = new Day6();
        Assert.assertEquals(1000000, d.solvePartOne(instructions));
    }

    /**
     * Test Day 6 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        List<String> instructions = new ArrayList<>();
        instructions.add("toggle 0,0 through 999,0");
        Day6 d = new Day6();
        Assert.assertEquals(1000, d.solvePartOne(instructions));
    }

    /**
     * Test Day 6 part one with the example input 3  (with the known result)
     */
    @Test
    public void testExampleInput3PartOne() {
        List<String> instructions = new ArrayList<>();
        instructions.add("turn off 499,499 through 500,500");
        Day6 d = new Day6();
        Assert.assertEquals(0, d.solvePartOne(instructions));
    }

    /**
     * Test Day 6 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2015, 6);
        Day6 d = new Day6();
        Assert.assertEquals(569999, d.solvePartOne(instructions));
    }

    /**
     * Test Day 6 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> instructions = new ArrayList<>();
        instructions.add("turn on 0,0 through 0,0");
        Day6 d = new Day6();
        Assert.assertEquals(1, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 6 part two with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartTwo() {
        List<String> instructions = new ArrayList<>();
        instructions.add("toggle 0,0 through 999,999");
        Day6 d = new Day6();
        Assert.assertEquals(2000000, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 6 part two with an example with negative brightness (with the known result)
     * This is to ensure that the brightness never drops below 0 (because it is noted that the brightness is 0+ and
     * cannot drop below 0 and therefore a good corner case that they will try and trip you up on)
     */
    @Test
    public void testNegativeBrightnessWithPartTwo() {
        List<String> instructions = new ArrayList<>();
        instructions.add("turn off 0,0 through 0,2");
        Day6 d = new Day6();
        Assert.assertEquals(0, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 6 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2015, 6);
        Day6 d = new Day6();
        Assert.assertEquals(17836115, d.solvePartTwo(instructions));
    }

}
