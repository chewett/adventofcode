package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day3Test {

    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day3 d = new Day3();
        Assertions.assertEquals(2, d.solvePartOne(">"));
    }

    /**
     * Test Day 3 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        Day3 d = new Day3();
        Assertions.assertEquals(4, d.solvePartOne("^>v<"));
    }

    /**
     * Test Day 3 part one with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartOne() {
        Day3 d = new Day3();
        Assertions.assertEquals(2, d.solvePartOne("^v^v^v^v^v"));
    }

    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Day3 d = new Day3();
        Assertions.assertEquals(2592, d.solvePartOne(ProblemLoader.loadProblemIntoString(2015, 3)));
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Day3 d = new Day3();
        Assertions.assertEquals(3, d.solvePartTwo("^v"));
    }

    /**
     * Test Day 3 part two with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartTwo() {
        Day3 d = new Day3();
        Assertions.assertEquals(3, d.solvePartTwo("^>v<"));
    }

    /**
     * Test Day 3 part two with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartTwo() {
        Day3 d = new Day3();
        Assertions.assertEquals(11, d.solvePartTwo("^v^v^v^v^v"));
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Day3 d = new Day3();
        Assertions.assertEquals(2360, d.solvePartTwo(ProblemLoader.loadProblemIntoString(2015, 3)));
    }

}
