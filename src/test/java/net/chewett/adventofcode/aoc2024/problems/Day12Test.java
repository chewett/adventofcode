package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day12Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("RRRRIICCFF");
        input.add("RRRRIICCCF");
        input.add("VVRRRCCFFF");
        input.add("VVRCCCJFFF");
        input.add("VVVVCJJCFE");
        input.add("VVIVCCJJEE");
        input.add("VVIIICJJEE");
        input.add("MIIIIIJJEE");
        input.add("MIIISIJEEE");
        input.add("MMMISSJEEE");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 12 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(1930, d.solvePartOne(input));
    }


    /**
     * Test Day 12 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(1488414, d.solvePartOne(input));
    }

    /**
     * Test Day 12 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(1206, d.solvePartTwo(input));
    }

    /**
     * Test Day 12 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(911750, d.solvePartTwo(input));
    }

}
