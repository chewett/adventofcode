package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day13Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Button A: X+94, Y+34");
        input.add("Button B: X+22, Y+67");
        input.add("Prize: X=8400, Y=5400");
        input.add("");

        input.add("Button A: X+26, Y+66");
        input.add("Button B: X+67, Y+21");
        input.add("Prize: X=12748, Y=12176");
        input.add("");

        input.add("Button A: X+17, Y+86");
        input.add("Button B: X+84, Y+37");
        input.add("Prize: X=7870, Y=6450");
        input.add("");

        input.add("Button A: X+69, Y+23");
        input.add("Button B: X+27, Y+71");
        input.add("Prize: X=18641, Y=10279");

        return input;
    }

    /**
     * Test Day 13 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day13 d = new Day13();
        Assertions.assertEquals(480, d.solvePartOne(input));
    }


    /**
     * Test Day 13 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(29877, d.solvePartOne(input));
    }

    /**
     * Test Day 13 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(99423413811305L, d.solvePartTwo(input));
    }

}
