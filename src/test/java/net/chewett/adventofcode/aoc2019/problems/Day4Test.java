package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day4Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "111140-111147";
    }

    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(4, d.solvePartOne(input));
    }


    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(1729, d.solvePartOne(input));
    }

    /**
     * Test Day 4 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(1, d.solvePartTwo(input));
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(1172, d.solvePartTwo(input));
    }

}
