package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day12Test {

    private List<String> getExampleInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("F10");
        instructions.add("N3");
        instructions.add("F7");
        instructions.add("R90");
        instructions.add("F11");

        return instructions;
    }

    /**
     * Test Day 12 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> instructions = this.getExampleInstructions();
        Day12 d = new Day12();

        Assert.assertEquals(25, d.solvePartOne(instructions));
    }

    /**
     * Test Day 12 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 12);
        Day12 d = new Day12();
        Assert.assertEquals(1152, d.solvePartOne(instructions));
    }

    /**
     * Test Day 12 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> instructions = this.getExampleInstructions();
        Day12 d = new Day12();

        Assert.assertEquals(286, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 12 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 12);
        Day12 d = new Day12();
        Assert.assertEquals(58637, d.solvePartTwo(instructions));
    }

}
