package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day9Test {


    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> example = new ArrayList<>();
        example.add("London to Dublin = 464");
        example.add("London to Belfast = 518");
        example.add("Dublin to Belfast = 141");

        Day9 d = new Day9();
        int val = d.solvePartOne(example);
        Assertions.assertEquals(605, val);
    }

    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealInputPartOne() {
        List<String> realData = ProblemLoader.loadProblemIntoStringArray(2015, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(141, d.solvePartOne(realData));
    }

    /**
     * Test Day 9 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> example = new ArrayList<>();
        example.add("London to Dublin = 464");
        example.add("London to Belfast = 518");
        example.add("Dublin to Belfast = 141");

        Day9 d = new Day9();
        Assertions.assertEquals(982, d.solvePartTwo(example));
    }

    /**
     * Test Day 9 part two with the real input (and the correct answer)
     */
    @Test
    public void testRealInputPartTwo() {
        List<String> realData = ProblemLoader.loadProblemIntoStringArray(2015, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(736, d.solvePartTwo(realData));
    }


}
