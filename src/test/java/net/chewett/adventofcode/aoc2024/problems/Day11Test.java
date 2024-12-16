package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day11Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "125 17";
    }

    /**
     * Test Day 11 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day11 d = new Day11();
        Assertions.assertEquals(55312, d.solvePartOne(input));
    }


    /**
     * Test Day 11 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2024, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(217443, d.solvePartOne(input));
    }

    /**
     * Test Day 11 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2024, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(257246536026785L, d.solvePartTwo(input));
    }

}
