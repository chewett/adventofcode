package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day2Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> instructions = new ArrayList<>();
        instructions.add("forward 5");
        instructions.add("down 5");
        instructions.add("forward 8");
        instructions.add("up 3");
        instructions.add("down 8");
        instructions.add("forward 2");

        return instructions;
    }


    /**
     * Test Day 2 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> instructions = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(150, d.solvePartOne(instructions));
    }

    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(1648020, d.solvePartOne(instructions));
    }

    /**
     * Test Day 2 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> instructions = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(900, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(1759818555, d.solvePartTwo(instructions));
    }




}
