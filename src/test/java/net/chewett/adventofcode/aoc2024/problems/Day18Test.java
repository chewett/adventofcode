package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day18Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("5,4");
        input.add("4,2");
        input.add("4,5");
        input.add("3,0");
        input.add("2,1");
        input.add("6,3");
        input.add("2,4");
        input.add("1,5");
        input.add("0,6");
        input.add("3,3");
        input.add("2,6");
        input.add("5,1");
        input.add("1,2");
        input.add("5,5");
        input.add("2,5");
        input.add("6,5");
        input.add("1,4");
        input.add("0,4");
        input.add("6,4");
        input.add("1,1");
        input.add("6,1");
        input.add("1,0");
        input.add("0,5");
        input.add("1,6");
        input.add("2,0");

        return input;
    }

    /**
     * Test Day 18 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day18 d = new Day18(false);
        Assertions.assertEquals(22, d.solvePartOne(input));
    }


    /**
     * Test Day 18 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(262, d.solvePartOne(input));
    }

    /**
     * Test Day 18 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day18 d = new Day18(false);
        Assertions.assertEquals("6,1", d.solvePartTwo(input));
    }

    /**
     * Test Day 18 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 18);
        Day18 d = new Day18();
        Assertions.assertEquals("22,20", d.solvePartTwo(input));
    }

}
