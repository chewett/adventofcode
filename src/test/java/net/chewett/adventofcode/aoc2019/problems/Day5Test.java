package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day5Test {

    /**
     * Function to get the example inputs for part two
     * @return The example input
     */
    public String getExampleInputForPartTwo() {
         return "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
    }

    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(13294380, d.solvePartOne(input));
    }

    /**
     * Test Day 5 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInputForPartTwo();
        Day5 d = new Day5();
        Assertions.assertEquals(999, d.solvePartTwo(input));
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 5);
        Day5 d = new Day5();
        Assertions.assertEquals(11460760, d.solvePartTwo(input));
    }

}
