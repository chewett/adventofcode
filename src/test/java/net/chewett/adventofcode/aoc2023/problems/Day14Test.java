package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day14Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("O....#....");
        input.add("O.OO#....#");
        input.add(".....##...");
        input.add("OO.#O....O");
        input.add(".O.....O#.");
        input.add("O.#..O.#.#");
        input.add("..O..#O..O");
        input.add(".......O..");
        input.add("#....###..");
        input.add("#OO..#....");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 14 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(136, d.solvePartOne(input));
    }


    /**
     * Test Day 14 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(112048, d.solvePartOne(input));
    }

    /**
     * Test Day 14 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(64, d.solvePartTwo(input));
    }

    /**
     * Test Day 14 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(105606, d.solvePartTwo(input));
    }

}
