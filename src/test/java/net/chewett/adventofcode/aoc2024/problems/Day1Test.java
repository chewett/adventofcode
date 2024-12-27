package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day1Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("3   4");
        input.add("4   3");
        input.add("2   5");
        input.add("1   3");
        input.add("3   9");
        input.add("3   3");

        return input;
    }

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(11, d.solvePartOne(input));
    }


    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(1646452, d.solvePartOne(input));
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(31, d.solvePartTwo(input));
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(23609874, d.solvePartTwo(input));
    }

}
