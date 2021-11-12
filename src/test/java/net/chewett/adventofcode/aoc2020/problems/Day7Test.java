package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day7Test {

    /**
     * Returns the first example
     * @return
     */
    private List<String> getExampleOne() {
        List<String> bagDetails = new ArrayList<>();
        bagDetails.add("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        bagDetails.add("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        bagDetails.add("bright white bags contain 1 shiny gold bag.");
        bagDetails.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.");
        bagDetails.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.");
        bagDetails.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.");
        bagDetails.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.");
        bagDetails.add("faded blue bags contain no other bags.");
        bagDetails.add("dotted black bags contain no other bags.");

        return bagDetails;
    }

    /**
     * Returns the second example
     * @return
     */
    private List<String> getExampleTwo() {
        List<String> bagDetails = new ArrayList<>();
        bagDetails.add("shiny gold bags contain 2 dark red bags.");
        bagDetails.add("dark red bags contain 2 dark orange bags.");
        bagDetails.add("dark orange bags contain 2 dark yellow bags.");
        bagDetails.add("dark yellow bags contain 2 dark green bags.");
        bagDetails.add("dark green bags contain 2 dark blue bags.");
        bagDetails.add("dark blue bags contain 2 dark violet bags.");
        bagDetails.add("dark violet bags contain no other bags.");

        return bagDetails;
    }

    /**
     * Test Day 7 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> bagDetails = getExampleOne();

        Day7 d = new Day7();
        long partOneAnswer = d.solvePartOne(bagDetails);

        Assertions.assertEquals(4, partOneAnswer);
    }

    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> bagDetails = ProblemLoader.loadProblemIntoStringArray(2020, 7);
        Day7 d = new Day7();
        int partOneAnswer = d.solvePartOne(bagDetails);
        Assertions.assertEquals(337, partOneAnswer);
    }

    /**
     * Test Day 7 part two with the first example input (with the known result)
     */
    @Test
    public void testFirstExampleInputPartTwo() {
       List<String> bagDetails = getExampleOne();

        Day7 d = new Day7();
        int partTwoAnswer = d.solvePartTwo(bagDetails);

        Assertions.assertEquals(32, partTwoAnswer);
    }

    /**
     * Test Day 7 part two with the second example input (with the known result)
     */
    @Test
    public void testSecondExampleInputPartTwo() {
        List<String> bagDetails = getExampleTwo();

        Day7 d = new Day7();
        int partTwoAnswer = d.solvePartTwo(bagDetails);

        Assertions.assertEquals(126, partTwoAnswer);
    }

    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> bagDetails = ProblemLoader.loadProblemIntoStringArray(2020, 7);
        Day7 d = new Day7();
        int partTwoAnswer = d.solvePartTwo(bagDetails);
        Assertions.assertEquals(50100, partTwoAnswer);
    }

}
