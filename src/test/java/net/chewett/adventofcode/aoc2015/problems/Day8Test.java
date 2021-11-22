package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day8Test {

    /**
     * Test Day 8 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> example = new ArrayList<>();
        example.add("\"\"");
        example.add("\"abc\"");
        example.add("\"aaa\\\"aaa\"");
        example.add("\"\\x27\"");

        Day8 d = new Day8();
        int val = d.solvePartOne(example);
        Assertions.assertEquals(12, val);
    }

    /**
     * Test Day 8 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealInputPartOne() {
        List<String> realData = ProblemLoader.loadProblemIntoStringArray(2015, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(1350, d.solvePartOne(realData));
    }

    /**
     * Test Day 8 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> example = new ArrayList<>();
        example.add("\"\"");
        example.add("\"abc\"");
        example.add("\"aaa\\\"aaa\"");
        example.add("\"\\x27\"");

        Day8 d = new Day8();
        int val = d.solvePartTwo(example);
        Assertions.assertEquals(19, val);
    }

    /**
     * Test Day 8 part two with the real input (and the correct answer)
     */
    @Test
    public void testRealInputPartTwo() {
        List<String> realData = ProblemLoader.loadProblemIntoStringArray(2015, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(2085, d.solvePartTwo(realData));
    }

}
