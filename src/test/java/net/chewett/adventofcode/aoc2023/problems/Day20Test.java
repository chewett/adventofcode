package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day20Test {

    /**
     * Function to get the example input one
     * @return The example input
     */
    public List<String> getExampleInputOne() {
        List<String> input = new ArrayList<>();

        input.add("broadcaster -> a, b, c");
        input.add("%a -> b");
        input.add("%b -> c");
        input.add("%c -> inv");
        input.add("&inv -> a");

        return input;
    }

    /**
     * Function to get the example input two
     * @return The example input
     */
    public List<String> getExampleInputTwo() {
        List<String> input = new ArrayList<>();

        input.add("broadcaster -> a");
        input.add("%a -> inv, con");
        input.add("&inv -> b");
        input.add("%b -> con");
        input.add("&con -> output");

        return input;
    }

    /**
     * Test Day 20 part one with example input one (with the known result)
     */
    @Test
    public void testExampleInputOnePartOne() {
        List<String> input = this.getExampleInputOne();
        Day20 d = new Day20();
        Assertions.assertEquals(32000000, d.solvePartOne(input));
    }

    /**
     * Test Day 20 part one with example input two (with the known result)
     */
    @Test
    public void testExampleInputTwoPartOne() {
        List<String> input = this.getExampleInputTwo();
        Day20 d = new Day20();
        Assertions.assertEquals(11687500, d.solvePartOne(input));
    }


    /**
     * Test Day 20 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 20);
        Day20 d = new Day20();
        Assertions.assertEquals(711650489, d.solvePartOne(input));
    }

    /**
     * Test Day 20 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 20);
        Day20 d = new Day20();
        Assertions.assertEquals(219388737656593L, d.solvePartTwo(input));
    }

}
