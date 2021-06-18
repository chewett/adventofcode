package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day18Test {


    @Test
    public void testSimpleAdditionPartTwo() {
        List<String> problem = new ArrayList<>();
        problem.add("7 + 5");
        Day18 d = new Day18();
        long ans = d.solvePartTwo(problem);
        Assert.assertEquals(12, ans);
    }

    @Test
    public void testSimpleMultPartTwo() {
        List<String> problem = new ArrayList<>();
        problem.add("7 * 5");
        Day18 d = new Day18();
        long ans = d.solvePartTwo(problem);
        Assert.assertEquals(35, ans);
    }

    @Test
    public void testSimpleAdditionMultPartTwo() {
        List<String> problem = new ArrayList<>();
        problem.add("5 * 2 + 5");
        Day18 d = new Day18();
        long ans = d.solvePartTwo(problem);
        Assert.assertEquals(35, ans);
    }


    /**
     * Test Day 18 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> problems = new ArrayList<>();
        problems.add("1 + 2 * 3 + 4 * 5 + 6");
        Day18 d = new Day18();
        Assert.assertEquals(71, d.solvePartOne(problems));
    }

    /**
     * Test Day 18 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        List<String> problems = new ArrayList<>();
        problems.add("1 + (2 * 3) + (4 * (5 + 6))");
        Day18 d = new Day18();
        Assert.assertEquals(51, d.solvePartOne(problems));
    }

    /**
     * Test Day 18 part one with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartOne() {
        List<String> problems = new ArrayList<>();
        problems.add("2 * 3 + (4 * 5)");
        Day18 d = new Day18();
        Assert.assertEquals(26, d.solvePartOne(problems));
    }

    /**
     * Test Day 18 part one with the example input 4 (with the known result)
     */
    @Test
    public void testExampleInput4PartOne() {
        List<String> problems = new ArrayList<>();
        problems.add("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        Day18 d = new Day18();
        Assert.assertEquals(437, d.solvePartOne(problems));
    }

    /**
     * Test Day 18 part one with the example input 5 (with the known result)
     */
    @Test
    public void testExampleInput5PartOne() {
        List<String> problems = new ArrayList<>();
        problems.add("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        Day18 d = new Day18();
        Assert.assertEquals(12240, d.solvePartOne(problems));
    }

    /**
     * Test Day 18 part one with the example input 6 (with the known result)
     */
    @Test
    public void testExampleInput6PartOne() {
        List<String> problems = new ArrayList<>();
        problems.add("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        Day18 d = new Day18();
        Assert.assertEquals(13632, d.solvePartOne(problems));
    }

    /**
     * Test Day 18 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> problems = ProblemLoader.loadProblemIntoStringArray(2020, 18);
        Day18 d = new Day18();
        Assert.assertEquals(13976444272545L, d.solvePartOne(problems));
    }

    /**
     * Test Day 17 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> problems = new ArrayList<>();
        problems.add("1 + 2 * 3 + 4 * 5 + 6");
        Day18 d = new Day18();
        Assert.assertEquals(231, d.solvePartTwo(problems));
    }

    /**
     * Test Day 17 part two with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartTwo() {
        List<String> problems = new ArrayList<>();
        problems.add("1 + (2 * 3) + (4 * (5 + 6))");
        Day18 d = new Day18();
        Assert.assertEquals(51, d.solvePartTwo(problems));
    }

    /**
     * Test Day 17 part two with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartTwo() {
        List<String> problems = new ArrayList<>();
        problems.add("2 * 3 + (4 * 5)");
        Day18 d = new Day18();
        Assert.assertEquals(46, d.solvePartTwo(problems));
    }

    /**
     * Test Day 17 part two with the example input 4 (with the known result)
     */
    @Test
    public void testExampleInput4PartTwo() {
        List<String> problems = new ArrayList<>();
        problems.add("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        Day18 d = new Day18();
        Assert.assertEquals(1445, d.solvePartTwo(problems));
    }

    /**
     * Test Day 17 part two with the example input 5 (with the known result)
     */
    @Test
    public void testExampleInput5PartTwo() {
        List<String> problems = new ArrayList<>();
        problems.add("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        Day18 d = new Day18();
        Assert.assertEquals(669060, d.solvePartTwo(problems));
    }

    /**
     * Test Day 17 part two with the example input 6 (with the known result)
     */
    @Test
    public void testExampleInput6PartTwo() {
        List<String> problems = new ArrayList<>();
        problems.add("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        Day18 d = new Day18();
        Assert.assertEquals(23340, d.solvePartTwo(problems));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> problems = ProblemLoader.loadProblemIntoStringArray(2020, 18);
        Day18 d = new Day18();
        Assert.assertEquals(88500956630893L, d.solvePartTwo(problems));
    }



}
