package net.chewett.adventofcode.aoc2021.problems;

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
        List<String> hydrothermalVentLines = new ArrayList<>();
        hydrothermalVentLines.add("0,9 -> 5,9");
        hydrothermalVentLines.add("8,0 -> 0,8");
        hydrothermalVentLines.add("9,4 -> 3,4");
        hydrothermalVentLines.add("2,2 -> 2,1");
        hydrothermalVentLines.add("7,0 -> 7,4");
        hydrothermalVentLines.add("6,4 -> 2,0");
        hydrothermalVentLines.add("0,9 -> 2,9");
        hydrothermalVentLines.add("3,4 -> 1,4");
        hydrothermalVentLines.add("0,0 -> 8,8");
        hydrothermalVentLines.add("5,5 -> 8,2");

        return hydrothermalVentLines;
    }


    /**
     * Test Day 5 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals(5, d.solvePartOne(report));
    }

    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(8060, d.solvePartOne(report));
    }

    /**
     * Test Day 5 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals(12, d.solvePartTwo(report));
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(21577, d.solvePartTwo(report));
    }




}
