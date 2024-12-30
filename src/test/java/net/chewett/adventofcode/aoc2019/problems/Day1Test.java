package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day1Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<Integer> getExampleInput() {
        List<Integer> input = new ArrayList<>();

        input.add(12);
        input.add(14);
        input.add(1969);
        input.add(100756);

        return input;
    }

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> input = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(2 + 2 + 654 + 33583, d.solvePartOne(input));
    }


    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> input = ProblemLoader.loadProblemIntoIntegerList(2019, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(3352674, d.solvePartOne(input));
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> input = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(2 + 2 + 966 + 50346, d.solvePartTwo(input));
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> input = ProblemLoader.loadProblemIntoIntegerList(2019, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(5026151, d.solvePartTwo(input));
    }

}
