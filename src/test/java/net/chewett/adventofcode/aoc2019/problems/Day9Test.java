package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day9Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "104,1125899906842624,99";
    }

    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(1125899906842624L, d.solvePartOne(input));
    }

    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(4006117640L, d.solvePartOne(input));
    }


    /**
     * Test Day 9 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(88231, d.solvePartTwo(input));
    }

}
