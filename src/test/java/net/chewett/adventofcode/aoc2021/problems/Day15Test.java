package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day15Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Integer> getExampleInput() {
        List<String> roofMapStrArray = new ArrayList<>();
        roofMapStrArray.add("1163751742");
        roofMapStrArray.add("1381373672");
        roofMapStrArray.add("2136511328");
        roofMapStrArray.add("3694931569");
        roofMapStrArray.add("7463417111");
        roofMapStrArray.add("1319128137");
        roofMapStrArray.add("1359912421");
        roofMapStrArray.add("3125421639");
        roofMapStrArray.add("1293138521");
        roofMapStrArray.add("2311944581");

        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(
            FormatConversion.convertStringArrayToCharListList(roofMapStrArray)
        );
    }

    /**
     * Test Day 15 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Integer> roofMap = this.getExampleInput();
        Day15 d = new Day15();
        Assertions.assertEquals(40, d.solvePartOne(roofMap));
    }


    /**
     * Test Day 15 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Integer> roofMap = ProblemLoader.loadProblemIntoDiscrete2DPositionGrid(2021, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(748, d.solvePartOne(roofMap));
    }

    /**
     * Test Day 15 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Integer> roofMap = this.getExampleInput();
        Day15 d = new Day15();
        Assertions.assertEquals(315, d.solvePartTwo(roofMap));
    }

    /**
     * Test Day 15 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Integer> roofMap = ProblemLoader.loadProblemIntoDiscrete2DPositionGrid(2021, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(3045, d.solvePartTwo(roofMap));
    }
    
    
}
