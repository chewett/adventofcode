package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day10Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Integer> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("89010123");
        input.add("78121874");
        input.add("87430965");
        input.add("96549874");
        input.add("45678903");
        input.add("32019012");
        input.add("01329801");
        input.add("10456732");

        List<List<Character>> inputArr = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(inputArr);
    }

    /**
     * Test Day 10 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Integer> input = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(36, d.solvePartOne(input));
    }


    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Integer> input = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2024, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(652, d.solvePartOne(input));
    }

    /**
     * Test Day 10 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Integer> input = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(81, d.solvePartTwo(input));
    }

    /**
     * Test Day 10 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Integer> input = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2024, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(1432, d.solvePartTwo(input));
    }

}
