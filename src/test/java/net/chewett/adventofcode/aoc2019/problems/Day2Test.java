package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day2Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "1,1,1,4,99,5,6,0,99";
    }

    /**
     * Test Day 2 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(30, d.solvePartOne(input));
    }


    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 2);
        Day2 d = new Day2();
        //TODO: Verify these are right once I have internet
        Assertions.assertEquals(5305097, d.solvePartOne(input));
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 2);
        Day2 d = new Day2();
        //TODO: Verify these are right once I have internet
        Assertions.assertEquals(4925, d.solvePartTwo(input));
    }

}
