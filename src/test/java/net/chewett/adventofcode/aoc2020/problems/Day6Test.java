package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day6Test {

    /**
     * An example input string provided by AoC with the right answers
     * @return
     */
    private List<String> getExampleInput() {
        List<String> lines = new ArrayList<>();

        lines.add("abc");
        lines.add("");
        lines.add("a");
        lines.add("b");
        lines.add("c");
        lines.add("");
        lines.add("ab");
        lines.add("ac");
        lines.add("");
        lines.add("a");
        lines.add("a");
        lines.add("a");
        lines.add("a");
        lines.add("");
        lines.add("b");

        return lines;
    }

    /**
     * Test Day 6 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> lines = this.getExampleInput();

        Day6 d = new Day6();
        int partOneAnswer = d.solvePartOne(lines);
        Assert.assertEquals(11, partOneAnswer);
    }

    /**
     * Test Day 6 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 6);
        Day6 d = new Day6();
        int partOneAnswer = d.solvePartOne(lines);

        Assert.assertEquals(6625, partOneAnswer);
    }

    /**
     * Test Day 6 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> lines = this.getExampleInput();

        Day6 d = new Day6();
        int partTwoAnswer = d.solvePartTwo(lines);

        Assert.assertEquals(6, partTwoAnswer);
    }

    /**
     * Test Day 6 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 6);
        Day6 d = new Day6();
        int partTwoAnswer = d.solvePartTwo(lines);

        Assert.assertEquals(3360, partTwoAnswer);
    }

}
