package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day3Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> backpacks = new ArrayList<>();

        backpacks.add("vJrwpWtwJgWrhcsFMMfFFhFp");
        backpacks.add("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        backpacks.add("PmmdzqPrVvPwwTWBwg");
        backpacks.add("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
        backpacks.add("ttgJtRGJQctTZtZT");
        backpacks.add("CrZsJsPPZsGzwwsLwLmpwMDw");

        return backpacks;
    }

    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> backpacks = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(157, d.solvePartOne(backpacks));
    }


    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> backpacks = ProblemLoader.loadProblemIntoStringArray(2022, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(7701, d.solvePartOne(backpacks));
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> backpacks = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(70, d.solvePartTwo(backpacks));
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> backpacks = ProblemLoader.loadProblemIntoStringArray(2022, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(2644, d.solvePartTwo(backpacks));
    }

    
}
