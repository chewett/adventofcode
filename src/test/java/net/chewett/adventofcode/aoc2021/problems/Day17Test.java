package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day17Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "x=20..30, y=-10..-5";
    }

    /**
     * Test Day 17 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String position = this.getExampleInput();
        Day17 d = new Day17();
        Assertions.assertEquals(45, d.solvePartOne(position));
    }


    /**
     * Test Day 17 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String position = ProblemLoader.loadProblemIntoString(2021, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(35511, d.solvePartOne(position));
    }

    /**
     * Test Day 17 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String position = this.getExampleInput();
        Day17 d = new Day17();
        Assertions.assertEquals(112, d.solvePartTwo(position));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String position = ProblemLoader.loadProblemIntoString(2021, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(3282, d.solvePartTwo(position));
    }

}
