package net.chewett.adventofcode.aoc2021.problems;

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
        List<String> report = new ArrayList<>();
        report.add("00100");
        report.add("11110");
        report.add("10110");
        report.add("10111");
        report.add("10101");
        report.add("01111");
        report.add("00111");
        report.add("11100");
        report.add("10000");
        report.add("11001");
        report.add("00010");
        report.add("01010");

        return report;
    }


    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(198, d.solvePartOne(report));
    }

    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(841526, d.solvePartOne(report));
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(230, d.solvePartTwo(report));
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(4790390, d.solvePartTwo(report));
    }




}
