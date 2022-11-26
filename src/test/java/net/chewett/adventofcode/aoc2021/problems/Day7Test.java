package net.chewett.adventofcode.aoc2021.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day7Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<Integer> getExampleInput() {
        return new ArrayList<>(Arrays.asList(16,1,2,0,4,2,7,1,2,14));
    }

    /**
     * Test Day 7 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> crabs = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(37, d.solvePartOne(crabs));
    }


    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> crabs = ProblemLoader.loadProblemFromCommaSeperatedStringIntoIntegerList(2021, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(349357, d.solvePartOne(crabs));
    }

    /**
     * Test Day 7 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> crabs = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(168, d.solvePartTwo(crabs));
    }

    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> crabs = ProblemLoader.loadProblemFromCommaSeperatedStringIntoIntegerList(2021, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(96708205, d.solvePartTwo(crabs));
    }

}
