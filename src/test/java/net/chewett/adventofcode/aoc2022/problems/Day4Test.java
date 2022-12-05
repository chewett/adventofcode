package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Day4Test {

    /**
     * Tests to make sure that the two ranges that are next to each other but not overlapping are marked as non-overlapping
     */
    @Test
    public void testNonOverlapping() {
        Day4 d = new Day4();
        Assertions.assertFalse(d.isRangeOverlapping(10, 14, 15, 20));
    }

    /**
     * Tests to make sure that the two ranges that overlap on the left are reported as overlapping
     */
    @Test
    public void testLeftOverlap() {
        Day4 d = new Day4();
        Assertions.assertTrue(d.isRangeOverlapping(10, 20, 15, 20));
    }

    /**
     * Tests to make sure a slightly different left overlap is reported as overlapping
     */
    @Test
    public void testLeftOverlap2() {
        Day4 d = new Day4();
        Assertions.assertTrue(d.isRangeOverlapping(10, 16, 15, 20));
    }

    /**
     * Tests to make sure that one range containing another properly reports the ranges as overlapping
     */
    @Test
    public void testLeftContains() {
        Day4 d = new Day4();
        Assertions.assertTrue(d.isRangeOverlapping(16, 16, 15, 20));
    }

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> assignments = new ArrayList<>();

        assignments.add("2-4,6-8");
        assignments.add("2-3,4-5");
        assignments.add("5-7,7-9");
        assignments.add("2-8,3-7");
        assignments.add("6-6,4-6");
        assignments.add("2-6,4-8");

        return assignments;
    }

    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(2, d.solvePartOne(report));
    }


    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(459, d.solvePartOne(report));
    }

    /**
     * Test Day 4 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(4, d.solvePartTwo(report));
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(779, d.solvePartTwo(report));
    }
    
}
