package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day21Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("...........");
        input.add(".....###.#.");
        input.add(".###.##..#.");
        input.add("..#.#...#..");
        input.add("....#.#....");
        input.add(".##..S####.");
        input.add(".##..#...#.");
        input.add(".......##..");
        input.add(".##.#.####.");
        input.add(".##..##.##.");
        input.add("...........");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 21 part one with the example input (with the known result)
     *
     * This actually just calls the function with the smaller number of steps which are known
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day21 d = new Day21();
        Assertions.assertEquals(16, d.findFinalCountOfStatesForStep(input, 6));
    }

    /**
     * Test Day 21 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 21);
        Day21 d = new Day21();
        Assertions.assertEquals(3578, d.solvePartOne(input));
    }

    /**
     * Test Day 21 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 21);
        Day21 d = new Day21();
        Assertions.assertEquals(594115391548176L, d.solvePartTwo(input));
    }

}
