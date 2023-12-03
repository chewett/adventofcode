package net.chewett.adventofcode.aoc2021.problems;


import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day9Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Integer> getExampleInput() {

        List<String> strings = new ArrayList<>();
        strings.add("2199943210");
        strings.add("3987894921");
        strings.add("9856789892");
        strings.add("8767896789");
        strings.add("9899965678");

        List<List<Character>> charArray = FormatConversion.convertStringArrayToCharListList(strings);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(charArray);
    }

    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Integer> seafloor = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(15, d.solvePartOne(seafloor));
    }


    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Integer> seafloor = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2021, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(465, d.solvePartOne(seafloor));
    }

    /**
     * Test Day 9 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Integer> seafloor = this.getExampleInput();
        Day9 d = new Day9();
        Assertions.assertEquals(1134, d.solvePartTwo(seafloor));
    }

    /**
     * Test Day 9 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Integer> seafloor = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2021, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(1269555, d.solvePartTwo(seafloor));
    }
    
}
