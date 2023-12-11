package net.chewett.adventofcode.aoc2023.problems;


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
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("0 3 6 9 12 15");
        input.add("1 3 6 10 15 21");
        input.add("10 13 16 21 30 45");

        return input;
    }

    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(114, d.solvePartOne(input));
    }


    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(1684566095, d.solvePartOne(input));
    }

    /**
     * Test Day 9 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(2, d.solvePartTwo(input));
    }

    /**
     * Test Day 9 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(1136, d.solvePartTwo(input));
    }

}
