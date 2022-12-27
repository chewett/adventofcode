package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day19Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.");
        input.add("Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.");

        return input;
    }

    /**
     * Test Day 19 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> blueprints = this.getExampleInput();
        Day19 d = new Day19();
        Assertions.assertEquals(33, d.solvePartOne(blueprints));
    }


    /**
     * Test Day 19 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(1599, d.solvePartOne(blueprints));
    }

    /**
     * Test Day 19 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> blueprints = this.getExampleInput();
        Day19 d = new Day19();
        Assertions.assertEquals(3472, d.solvePartTwo(blueprints));
    }

    /**
     * Test Day 19 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(14112, d.solvePartTwo(blueprints));
    }


}
