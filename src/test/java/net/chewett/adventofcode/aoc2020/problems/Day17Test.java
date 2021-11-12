package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day17Test {

    /**
     * Test Day 17 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> gridData = new ArrayList<>();
        gridData.add(".#.");
        gridData.add("..#");
        gridData.add("###");
        List<List<Character>> listListChar = FormatConversion.convertStringArrayToCharListList(gridData);

        Day17 d = new Day17();
        Assertions.assertEquals(112, d.solvePartOne(listListChar));
    }

    /**
     * Test Day 17 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<List<Character>> ticketLines = ProblemLoader.loadProblemIntoXYCharList(2020, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(368, d.solvePartOne(ticketLines));
    }

    /**
     * Test Day 17 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> gridData = new ArrayList<>();
        gridData.add(".#.");
        gridData.add("..#");
        gridData.add("###");
        List<List<Character>> listListChar = FormatConversion.convertStringArrayToCharListList(gridData);

        Day17 d = new Day17();
        Assertions.assertEquals(848, d.solvePartTwo(listListChar));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<List<Character>> ticketLines = ProblemLoader.loadProblemIntoXYCharList(2020, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(2696, d.solvePartTwo(ticketLines));
    }

}
