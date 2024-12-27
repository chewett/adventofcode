package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day9Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "2333133121414131402";
    }

    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(1928, d.solvePartOne(input));
    }


    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2024, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(6242766523059L, d.solvePartOne(input));
    }

    /**
     * Test Day 9 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(2858, d.solvePartTwo(input));
    }

    /**
     * Test Day 9 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2024, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(6272188244509L, d.solvePartTwo(input));
    }

}
