package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day18Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("2,2,2");
        input.add("1,2,2");
        input.add("3,2,2");
        input.add("2,1,2");
        input.add("2,3,2");
        input.add("2,2,1");
        input.add("2,2,3");
        input.add("2,2,4");
        input.add("2,2,6");
        input.add("1,2,5");
        input.add("3,2,5");
        input.add("2,1,5");
        input.add("2,3,5");

        return input;
    }

    /**
     * Test Day 18 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> lavaDroplets = this.getExampleInput();
        Day18 d = new Day18();
        Assertions.assertEquals(64, d.solvePartOne(lavaDroplets));
    }


    /**
     * Test Day 18 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> lavaDroplets = ProblemLoader.loadProblemIntoStringArray(2022, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(4418, d.solvePartOne(lavaDroplets));
    }

    /**
     * Test Day 18 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> lavaDroplets = this.getExampleInput();
        Day18 d = new Day18();
        Assertions.assertEquals(58, d.solvePartTwo(lavaDroplets));
    }

    /**
     * Test Day 18 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> lavaDroplets = ProblemLoader.loadProblemIntoStringArray(2022, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(2486, d.solvePartTwo(lavaDroplets));
    }


}
