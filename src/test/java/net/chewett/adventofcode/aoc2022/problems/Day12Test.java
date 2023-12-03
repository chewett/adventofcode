package net.chewett.adventofcode.aoc2022.problems;

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
        input.add("Sabqponm");
        input.add("abcryxxl");
        input.add("accszExk");
        input.add("acctuvwj");
        input.add("abdefghi");

        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
            FormatConversion.convertStringArrayToCharListList(input)
        );
    }

    /**
     * Test Day 12 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> terrainData = this.getExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(31, d.solvePartOne(terrainData));
    }


    /**
     * Test Day 12 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> terrainData = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2022, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(449, d.solvePartOne(terrainData));
    }

    /**
     * Test Day 12 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> terrainData = this.getExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(29, d.solvePartTwo(terrainData));
    }

    /**
     * Test Day 12 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> terrainData = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2022, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(443, d.solvePartTwo(terrainData));
    }
    

}
