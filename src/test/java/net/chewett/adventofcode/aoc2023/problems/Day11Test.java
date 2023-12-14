package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day11Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("...#......");
        input.add(".......#..");
        input.add("#.........");
        input.add("..........");
        input.add("......#...");
        input.add(".#........");
        input.add(".........#");
        input.add("..........");
        input.add(".......#..");
        input.add("#...#.....");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 11 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day11 d = new Day11();
        Assertions.assertEquals(374, d.solvePartOne(input));
    }


    /**
     * Test Day 11 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(9605127, d.solvePartOne(input));
    }

    /**
     * Test Day 11 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day11 d = new Day11();
        //This isn't the value given in the example but is the "actual" result of the example with the full factor applied
        Assertions.assertEquals(82000210, d.solvePartTwo(input));
    }

    /**
     * Test Day 11 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(458191688761L, d.solvePartTwo(input));
    }

}
