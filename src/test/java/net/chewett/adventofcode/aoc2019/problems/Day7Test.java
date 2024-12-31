package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day7Test {

    /**
     * Function to get the example inputs for part one
     * @return The example input
     */
    public String getExampleInputForPartOne() {
        return "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
    }


    /**
     * Function to get the example inputs for part two
     * @return The example input
     */
    public String getExampleInputForPartTwo() {
        return "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
    }

    /**
     * Test Day 7 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInputForPartOne();
        Day7 d = new Day7();
        Assertions.assertEquals(54321, d.solvePartOne(input));
    }


    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(929800, d.solvePartOne(input));
    }

    /**
     * Test Day 7 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInputForPartTwo();
        Day7 d = new Day7();
        Assertions.assertEquals(139629729, d.solvePartTwo(input));
    }

    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(15432220, d.solvePartTwo(input));
    }

}
