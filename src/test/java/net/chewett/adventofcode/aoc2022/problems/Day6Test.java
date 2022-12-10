package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day6Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
    }

    /**
     * Test Day 6 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String signal = this.getExampleInput();
        Day6 d = new Day6();
        Assertions.assertEquals(7, d.solvePartOne(signal));
    }


    /**
     * Test Day 6 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String signal = ProblemLoader.loadProblemIntoString(2022, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(1909, d.solvePartOne(signal));
    }

    /**
     * Test Day 6 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String signal = this.getExampleInput();
        Day6 d = new Day6();
        Assertions.assertEquals(19, d.solvePartTwo(signal));
    }

    /**
     * Test Day 6 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String signal = ProblemLoader.loadProblemIntoString(2022, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(3380, d.solvePartTwo(signal));
    }


}
