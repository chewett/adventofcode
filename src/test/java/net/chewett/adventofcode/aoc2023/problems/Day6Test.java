package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day6Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Time:      7  15   30");
        input.add("Distance:  9  40  200");

        return input;
    }

    /**
     * Test Day 6 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day6 d = new Day6();
        Assertions.assertEquals(288, d.solvePartOne(input));
    }


    /**
     * Test Day 6 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(303600, d.solvePartOne(input));
    }

    /**
     * Test Day 6 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day6 d = new Day6();
        Assertions.assertEquals(71503, d.solvePartTwo(input));
    }

    /**
     * Test Day 6 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(23654842, d.solvePartTwo(input));
    }

}
