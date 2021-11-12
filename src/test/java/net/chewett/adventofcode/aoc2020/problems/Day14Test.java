package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day14Test {

    /**
     * Test Day 14 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> instructions = new ArrayList<>();
        instructions.add("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        instructions.add("mem[8] = 11");
        instructions.add("mem[7] = 101");
        instructions.add("mem[8] = 0");

        Day14 d = new Day14();
        long partOneAnswer = d.solvePartOne(instructions);

        Assertions.assertEquals(165, partOneAnswer);
    }

    /**
     * Test Day 14 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 14);
        Day14 d = new Day14();
        long partOneAnswer = d.solvePartOne(instructions);

        Assertions.assertEquals(15514035145260L, partOneAnswer);
    }

    /**
     * Test Day 14 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> instructions = new ArrayList<>();
        instructions.add("mask = 000000000000000000000000000000X1001X");
        instructions.add("mem[42] = 100");
        instructions.add("mask = 00000000000000000000000000000000X0XX");
        instructions.add("mem[26] = 1");

        Day14 d = new Day14();
        long partTwoAnswer = d.solvePartTwo(instructions);

        Assertions.assertEquals(208, partTwoAnswer);
    }

    /**
     * Test Day 14 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 14);
        Day14 d = new Day14();
        long partTwoAnswer = d.solvePartTwo(instructions);

        Assertions.assertEquals(3926790061594L, partTwoAnswer);
    }

}
