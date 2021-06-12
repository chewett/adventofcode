package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Day5Test {

    @Test
    public void testFirstNiceString() {
        Day5 d = new Day5();
        Assert.assertTrue(d.isNiceStringPartOne("ugknbfddgicrmopn"));
    }

    @Test
    public void testSecondNiceString() {
        Day5 d = new Day5();
        Assert.assertTrue(d.isNiceStringPartOne("aaa"));
    }

    @Test
    public void testFirstNaughtyString() {
        Day5 d = new Day5();
        Assert.assertFalse(d.isNiceStringPartOne("jchzalrnumimnmhp"));
    }

    @Test
    public void testSecondNaughtyString() {
        Day5 d = new Day5();
        Assert.assertFalse(d.isNiceStringPartOne("haegwjzuvuyypxyu"));
    }

    @Test
    public void testThirdNaughtyString() {
        Day5 d = new Day5();
        Assert.assertFalse(d.isNiceStringPartOne("dvszwmarrgswjxmb"));
    }


    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> strings = ProblemLoader.loadProblemIntoStringArray(2015, 5);
        Day5 d = new Day5();
        Assert.assertEquals(236, d.solvePartOne(strings));
    }


    @Test
    public void testNiceStringPartTwo() {
        Day5 d = new Day5();
        Assert.assertTrue(d.isNiceStringPartTwo("qjhvhtzxzqqjkmpb"));
    }

    @Test
    public void testNiceStringTwoPartTwo() {
        Day5 d = new Day5();
        Assert.assertTrue(d.isNiceStringPartTwo("xxyxx"));
    }

    @Test
    public void testNaughtyStringPartTwo() {
        Day5 d = new Day5();
        Assert.assertFalse(d.isNiceStringPartTwo("uurcxstgmygtbstg"));
    }

    @Test
    public void testNaughtyTwoStringPartTwo() {
        Day5 d = new Day5();
        Assert.assertFalse(d.isNiceStringPartTwo("ieodomkazucvgmuy"));
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> strings = ProblemLoader.loadProblemIntoStringArray(2015, 5);
        Day5 d = new Day5();
        Assert.assertEquals(51, d.solvePartTwo(strings));
    }

}
