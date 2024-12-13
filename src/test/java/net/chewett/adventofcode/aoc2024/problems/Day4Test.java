package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day4Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("MMMSXXMASM");
        input.add("MSAMXMSMSA");
        input.add("AMXSXMAAMM");
        input.add("MSAMASMSMX");
        input.add("XMASAMXAMM");
        input.add("XXAMMXXAMA");
        input.add("SMSMSASXSS");
        input.add("SAXAMASAAA");
        input.add("MAMMMXMMMM");
        input.add("MXMXAXMASX");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(18, d.solvePartOne(input));
    }


    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(2496, d.solvePartOne(input));
    }

    /**
     * Test Day 4 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(9, d.solvePartTwo(input));
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(1967, d.solvePartTwo(input));
    }

}
