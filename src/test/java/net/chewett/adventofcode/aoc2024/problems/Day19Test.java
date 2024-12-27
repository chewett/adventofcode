package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day19Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("r, wr, b, g, bwu, rb, gb, br");
        input.add("");
        input.add("brwrr");
        input.add("bggr");
        input.add("gbbr");
        input.add("rrbgbr");
        input.add("ubwu");
        input.add("bwurrg");
        input.add("brgr");
        input.add("bbrgwb");

        return input;
    }

    /**
     * Test Day 19 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day19 d = new Day19();
        Assertions.assertEquals(6, d.solvePartOne(input));
    }


    /**
     * Test Day 19 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(338, d.solvePartOne(input));
    }

    /**
     * Test Day 19 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day19 d = new Day19();
        Assertions.assertEquals(16, d.solvePartTwo(input));
    }

    /**
     * Test Day 19 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(841533074412361L, d.solvePartTwo(input));
    }

}
