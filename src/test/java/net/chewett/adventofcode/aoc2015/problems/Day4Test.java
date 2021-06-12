package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

public class Day4Test {

    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day4 d = new Day4();
        Assert.assertEquals(609043, d.solvePartOne("abcdef"));
    }

    /**
     * Test Day 4 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        Day4 d = new Day4();
        Assert.assertEquals(1048970, d.solvePartOne("pqrstuv"));
    }

    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Day4 d = new Day4();
        String secretKey = ProblemLoader.loadProblemIntoString(2015, 4);
        Assert.assertEquals(282749, d.solvePartOne(secretKey));
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Day4 d = new Day4();
        String secretKey = ProblemLoader.loadProblemIntoString(2015, 4);
        Assert.assertEquals(9962624, d.solvePartTwo(secretKey));
    }

}
