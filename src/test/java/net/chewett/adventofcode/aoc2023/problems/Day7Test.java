package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day7Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("32T3K 765");
        input.add("T55J5 684");
        input.add("KK677 28");
        input.add("KTJJT 220");
        input.add("QQQJA 483");

        return input;
    }

    /**
     * Test Day 7 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(6440, d.solvePartOne(input));
    }


    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(250602641, d.solvePartOne(input));
    }

    /**
     * Test Day 7 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(5905, d.solvePartTwo(input));
    }

    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(251037509, d.solvePartTwo(input));
    }

}
