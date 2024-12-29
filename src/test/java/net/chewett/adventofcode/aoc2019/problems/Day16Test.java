package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day16Test {

    /**
     * Function to get the example inputs for part one
     * @return The example input
     */
    public String getExampleInputForPartOne() {
        return "80871224585914546619083218645595";
    }

    /**
     * Function to get the example inputs for part two
     * @return The example input
     */
    public String getExampleInputForPartTwo() {
        return "03036732577212944063491565474664";
    }

    /**
     * Test Day 16 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInputForPartOne();
        Day16 d = new Day16();
        Assertions.assertEquals(24176176, d.solvePartOne(input));
    }


    /**
     * Test Day 16 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(32002835, d.solvePartOne(input));
    }

    /**
     * Test Day 16 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInputForPartTwo();
        Day16 d = new Day16();
        Assertions.assertEquals(84462026, d.solvePartTwo(input));
    }

    /**
     * Test Day 16 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(69732268, d.solvePartTwo(input));
    }

}
