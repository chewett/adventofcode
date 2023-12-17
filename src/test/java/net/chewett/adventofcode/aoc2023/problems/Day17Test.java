package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day17Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Integer> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("2413432311323");
        input.add("3215453535623");
        input.add("3255245654254");
        input.add("3446585845452");
        input.add("4546657867536");
        input.add("1438598798454");
        input.add("4457876987766");
        input.add("3637877979653");
        input.add("4654967986887");
        input.add("4564679986453");
        input.add("1224686865563");
        input.add("2546548887735");
        input.add("4322674655533");

        List<List<Character>> inputArr = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(inputArr);
    }

    /**
     * Function to get the second example input for part two
     * @return The example input 2
     */
    public Discrete2DPositionGrid<Integer> getExample2InputForPartTwo() {
        List<String> input = new ArrayList<>();

        input.add("111111111111");
        input.add("999999999991");
        input.add("999999999991");
        input.add("999999999991");
        input.add("999999999991");

        List<List<Character>> inputArr = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(inputArr);
    }

    /**
     * Test Day 17 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Integer> input = this.getExampleInput();
        Day17 d = new Day17();
        Assertions.assertEquals(102, d.solvePartOne(input));
    }

    /**
     * Test Day 17 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Integer> input = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2023, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(851, d.solvePartOne(input));
    }

    /**
     * Test Day 17 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Integer> input = this.getExampleInput();
        Day17 d = new Day17();
        Assertions.assertEquals(94, d.solvePartTwo(input));
    }

    /**
     * Test Day 17 part two with the example input 2 (with the known result)
     */
    @Test
    public void testExample2InputPartTwo() {
        Discrete2DPositionGrid<Integer> input = this.getExample2InputForPartTwo();
        Day17 d = new Day17();
        Assertions.assertEquals(71, d.solvePartTwo(input));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Integer> input = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2023, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(982, d.solvePartTwo(input));
    }

}
