package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Day23Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "00BA0CD0BC0DA00";
    }


    /**
     * Test Day 23 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day23 d = new Day23();
        Assertions.assertEquals(12521, d.solvePartOne(this.getExampleInput()));
    }


    /**
     * Test Day 23 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2021, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(15322, d.solvePartOne(input));
    }

    /**
     * Test Day 23 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Day23 d = new Day23();
        Assertions.assertEquals(-2, d.solvePartTwo(this.getExampleInput()));
    }

    /**
     * Test Day 23 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2021, 23);

        Day23 d = new Day23();
        Assertions.assertEquals(-2, d.solvePartTwo(input));
    }


}
