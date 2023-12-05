package net.chewett.adventofcode.aoc2023.problems;


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

        input.add("seeds: 79 14 55 13");
        input.add("");
        input.add("seed-to-soil map:");
        input.add("50 98 2");
        input.add("52 50 48");
        input.add("");
        input.add("soil-to-fertilizer map:");
        input.add("0 15 37");
        input.add("37 52 2");
        input.add("39 0 15");
        input.add("");
        input.add("fertilizer-to-water map:");
        input.add("49 53 8");
        input.add("0 11 42");
        input.add("42 0 7");
        input.add("57 7 4");
        input.add("");
        input.add("water-to-light map:");
        input.add("88 18 7");
        input.add("18 25 70");
        input.add("");
        input.add("light-to-temperature map:");
        input.add("45 77 23");
        input.add("81 45 19");
        input.add("68 64 13");
        input.add("");
        input.add("temperature-to-humidity map:");
        input.add("0 69 1");
        input.add("1 0 69");
        input.add("");
        input.add("humidity-to-location map:");
        input.add("60 56 37");
        input.add("56 93 4");

        return input;
    }

    /**
     * Test Day 5 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals(35, d.solvePartOne(input));
    }


    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(424490994, d.solvePartOne(input));
    }

    /**
     * Test Day 5 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals(46, d.solvePartTwo(input));
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(15290096, d.solvePartTwo(input));
    }


}
