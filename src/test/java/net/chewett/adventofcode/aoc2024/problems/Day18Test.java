package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day18Test {

    /**
     * TODO: add the example and a configurable size to handle the smaller example
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        //Add inputs

        return input;
    }

    /**
     * Test Day 18 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        //TODO implement test
        Assertions.assertTrue(false);
//        List<String> input = this.getExampleInput();
//        Day18 d = new Day18();
//        Assertions.assertEquals(0, d.solvePartOne(input));
    }


    /**
     * Test Day 18 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(262, d.solvePartOne(input));
    }

    /**
     * Test Day 18 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        //TODO implement test
        Assertions.assertTrue(false);
//        List<String> input = this.getExampleInput();
//        Day18 d = new Day18();
//        Assertions.assertEquals(0, d.solvePartTwo(input));
    }

    /**
     * Test Day 18 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 18);
        Day18 d = new Day18();
        Assertions.assertEquals("22,20", d.solvePartTwo(input));
    }

}
