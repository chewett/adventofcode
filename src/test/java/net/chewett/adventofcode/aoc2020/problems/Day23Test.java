package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

public class Day23Test {

    /**
     * Test Day 23 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String cupLabels = "389125467";
        Day23 d = new Day23();
        Assert.assertEquals(67384529, d.solvePartOne(cupLabels));
    }

    /**
     * Test Day 23 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String cupLabels = ProblemLoader.loadProblemIntoString(2020, 23);
        Day23 d = new Day23();
        Assert.assertEquals(97624853, d.solvePartOne(cupLabels));
    }

    /**
     * Test Day 23 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String cupLabels = "389125467";
        Day23 d = new Day23();
        Assert.assertEquals(149245887792L, d.solvePartTwo(cupLabels));
    }

    /**
     * Test Day 23 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String cupLabels = ProblemLoader.loadProblemIntoString(2020, 23);
        Day23 d = new Day23();
        Assert.assertEquals(664642452305L, d.solvePartTwo(cupLabels));
    }

}
