package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day5Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("47|53");
        input.add("97|13");
        input.add("97|61");
        input.add("97|47");
        input.add("75|29");
        input.add("61|13");
        input.add("75|53");
        input.add("29|13");
        input.add("97|29");
        input.add("53|29");
        input.add("61|53");
        input.add("97|53");
        input.add("61|29");
        input.add("47|13");
        input.add("75|47");
        input.add("97|75");
        input.add("47|61");
        input.add("75|61");
        input.add("47|29");
        input.add("75|13");
        input.add("53|13");
        input.add("");
        input.add("75,47,61,53,29");
        input.add("97,61,53,29,13");
        input.add("75,29,13");
        input.add("75,97,47,61,53");
        input.add("61,13,29");
        input.add("97,13,75,29,47");

        return input;
    }

    /**
     * Test Day 5 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals(143, d.solvePartOne(input));
    }


    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(4905, d.solvePartOne(input));
    }

    /**
     * Test Day 5 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals(123, d.solvePartTwo(input));
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(6204, d.solvePartTwo(input));
    }

}
