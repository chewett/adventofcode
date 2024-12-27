package net.chewett.adventofcode.aoc2024.problems;


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

        input.add("190: 10 19");
        input.add("3267: 81 40 27");
        input.add("83: 17 5");
        input.add("156: 15 6");
        input.add("7290: 6 8 6 15");
        input.add("161011: 16 10 13");
        input.add("192: 17 8 14");
        input.add("21037: 9 7 18 13");
        input.add("292: 11 6 16 20");

        return input;
    }

    /**
     * Test Day 7 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(3749, d.solvePartOne(input));
    }


    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(1985268524462L, d.solvePartOne(input));
    }

    /**
     * Test Day 7 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(11387, d.solvePartTwo(input));
    }

    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(150077710195188L, d.solvePartTwo(input));
    }

}
