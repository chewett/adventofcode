package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day8Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("LLR");
        input.add("");
        input.add("AAA = (BBB, BBB)");
        input.add("BBB = (AAA, ZZZ)");
        input.add("ZZZ = (ZZZ, ZZZ))");

        return input;
    }

    /**
     * Test Day 8 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day8 d = new Day8();
        Assertions.assertEquals(6, d.solvePartOne(input));
    }


    /**
     * Test Day 8 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(21389, d.solvePartOne(input));
    }

    /**
     * Test Day 8 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day8 d = new Day8();
        Assertions.assertEquals(6, d.solvePartTwo(input));
    }

    /**
     * Test Day 8 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(21083806112641L, d.solvePartTwo(input));
    }

}
