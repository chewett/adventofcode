package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        String[] programDataArr = new String[] {
            "addx 15", "addx -11", "addx 6", "addx -3", "addx 5", "addx -1", "addx -8", "addx 13", "addx 4", "noop",
                "addx -1", "addx 5", "addx -1", "addx 5", "addx -1", "addx 5", "addx -1", "addx 5", "addx -1",
                "addx -35", "addx 1", "addx 24", "addx -19", "addx 1", "addx 16", "addx -11", "noop", "noop", "addx 21",
                "addx -15", "noop", "noop", "addx -3", "addx 9", "addx 1", "addx -3", "addx 8", "addx 1", "addx 5",
                "noop", "noop", "noop", "noop", "noop", "addx -36", "noop", "addx 1", "addx 7", "noop", "noop", "noop",
                "addx 2", "addx 6", "noop", "noop", "noop", "noop", "noop", "addx 1", "noop", "noop", "addx 7", "addx 1",
                "noop", "addx -13", "addx 13", "addx 7", "noop", "addx 1", "addx -33", "noop", "noop", "noop", "addx 2",
                "noop", "noop", "noop", "addx 8", "noop", "addx -1", "addx 2", "addx 1", "noop", "addx 17", "addx -9",
                "addx 1", "addx 1", "addx -3", "addx 11", "noop", "noop", "addx 1", "noop", "addx 1", "noop", "noop",
                "addx -13", "addx -19", "addx 1", "addx 3", "addx 26", "addx -30", "addx 12", "addx -1", "addx 3",
                "addx 1", "noop", "noop", "noop", "addx -9", "addx 18", "addx 1", "addx 2", "noop", "noop", "addx 9",
                "noop", "noop", "noop", "addx -1", "addx 2", "addx -37", "addx 1", "addx 3", "noop", "addx 15",
                "addx -21", "addx 22", "addx -6", "addx 1", "noop", "addx 2", "addx 1", "noop", "addx -10", "noop",
                "noop", "addx 20", "addx 1", "addx 2", "addx 2", "addx -6", "addx -11", "noop", "noop", "noop",
        };

        return Arrays.asList(programDataArr);
    }

    /**
     * Test Day 10 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> programData = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(13140, d.solvePartOne(programData));
    }


    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> programData = ProblemLoader.loadProblemIntoStringArray(2022, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(16880, d.solvePartOne(programData));
    }

    /**
     * Test Day 10 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> programData = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(123, d.solvePartTwo(programData));
    }

    /**
     * Test Day 10 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> programData = ProblemLoader.loadProblemIntoStringArray(2022, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(103, d.solvePartTwo(programData));
    }


}
