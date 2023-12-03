package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day8Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Integer> getExampleInput() {
        List<String> lines = new ArrayList<>();

        lines.add("30373");
        lines.add("25512");
        lines.add("65332");
        lines.add("33549");
        lines.add("35390");

        List<List<Character>> arr = FormatConversion.convertStringArrayToCharListList(lines);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(arr);
    }

    /**
     * Test Day 8 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Integer> trees = this.getExampleInput();
        Day8 d = new Day8();
        Assertions.assertEquals(21, d.solvePartOne(trees));
    }


    /**
     * Test Day 8 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Integer> trees = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2022, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(1818, d.solvePartOne(trees));
    }

    /**
     * Test Day 8 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Integer> trees = this.getExampleInput();
        Day8 d = new Day8();
        Assertions.assertEquals(8, d.solvePartTwo(trees));
    }

    /**
     * Test Day 8 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Integer> trees = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2022, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(368368, d.solvePartTwo(trees));
    }
    
}
