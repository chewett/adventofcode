package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Day4Test {

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
