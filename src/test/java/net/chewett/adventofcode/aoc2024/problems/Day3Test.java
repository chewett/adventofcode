package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day3Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))\n";
    }

    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(161, d.solvePartOne(input));
    }


    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2024, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(191183308, d.solvePartOne(input));
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(48, d.solvePartTwo(input));
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2024, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(92082041, d.solvePartTwo(input));
    }

}
