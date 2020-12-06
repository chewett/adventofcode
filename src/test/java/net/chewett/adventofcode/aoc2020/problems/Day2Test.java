package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day2Test {

    /**
     * Test Day 2 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> passwordsStrings = new ArrayList<>();
        passwordsStrings.add("1-3 a: abcde");
        passwordsStrings.add("1-3 b: cdefg");
        passwordsStrings.add("2-9 c: ccccccccc");

        Day2 d = new Day2();
        int partOneAnswer = d.solvePartOne(passwordsStrings);

        Assert.assertEquals(2, partOneAnswer);
    }

    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> passwords = ProblemLoader.loadProblemIntoStringArray(2020, 2);
        Day2 d = new Day2();
        int partOneAnswer = d.solvePartOne(passwords);

        Assert.assertEquals(600, partOneAnswer);
    }

    /**
     * Test Day 2 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> passwordsStrings = new ArrayList<>();
        passwordsStrings.add("1-3 a: abcde");
        passwordsStrings.add("1-3 b: cdefg");
        passwordsStrings.add("2-9 c: ccccccccc");

        Day2 d = new Day2();
        int partTwoAnswer = d.solvePartTwo(passwordsStrings);

        Assert.assertEquals(1, partTwoAnswer);
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> passwords = ProblemLoader.loadProblemIntoStringArray(2020, 2);
        Day2 d = new Day2();
        int partTwoAnswer = d.solvePartTwo(passwords);

        Assert.assertEquals(245, partTwoAnswer);
    }
}
