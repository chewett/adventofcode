package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day24Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("19, 13, 30 @ -2,  1, -2");
        input.add("18, 19, 22 @ -1, -1, -2");
        input.add("20, 25, 34 @ -2, -2, -4");
        input.add("12, 31, 28 @ -1, -2, -1");
        input.add("20, 19, 15 @  1, -5, -3");

        return input;
    }

    /**
     * Test Day 24 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day24 d = new Day24();
        Assertions.assertEquals(2, d.solvePartOne(input));
    }

    /**
     * Test Day 24 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(17906, d.solvePartOne(input));
    }

    /**
     * Test Day 24 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day24 d = new Day24();
        Assertions.assertEquals(47, d.solvePartTwo(input));
    }

    /**
     * Test Day 24 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(571093786416929L, d.solvePartTwo(input));
    }

}
