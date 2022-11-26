package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<Integer> getExampleInput() {
        return new ArrayList<>(Arrays.asList(3, 4, 3, 1, 2));
    }

    /**
     * Test Day 6 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> fish = this.getExampleInput();
        Day6 d = new Day6();
        Assertions.assertEquals(5934, d.solvePartOne(fish));
    }


    /**
     * Test Day 6 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> ages = ProblemLoader.loadProblemFromCommaSeperatedStringIntoIntegerList(2021, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(373378, d.solvePartOne(ages));
    }

    /**
     * Test Day 6 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> fish = this.getExampleInput();
        Day6 d = new Day6();
        Assertions.assertEquals(26984457539L, d.solvePartTwo(fish));
    }

    /**
     * Test Day 6 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> ages = ProblemLoader.loadProblemFromCommaSeperatedStringIntoIntegerList(2021, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(1682576647495L, d.solvePartTwo(ages));
    }

}
