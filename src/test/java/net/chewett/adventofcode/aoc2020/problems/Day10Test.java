package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day10Test {

    /**
     * Simple example input 1 provided by aoc
     * @return
     */
    private List<Integer> getExampleInputOne() {
        List<Integer> jolts = new ArrayList<>();
        jolts.add(16);
        jolts.add(10);
        jolts.add(15);
        jolts.add(5);
        jolts.add(1);
        jolts.add(11);
        jolts.add(7);
        jolts.add(19);
        jolts.add(6);
        jolts.add(12);
        jolts.add(4);

        return jolts;
    }

    /**
     * Simple example input 2 provided by aoc
     * @return
     */
    private List<Integer> getExampleInputTwo() {
        List<Integer> jolts = new ArrayList<>();
        jolts.add(28);
        jolts.add(33);
        jolts.add(18);
        jolts.add(42);
        jolts.add(31);
        jolts.add(14);
        jolts.add(46);
        jolts.add(20);
        jolts.add(48);
        jolts.add(47);
        jolts.add(24);
        jolts.add(23);
        jolts.add(49);
        jolts.add(45);
        jolts.add(19);
        jolts.add(38);
        jolts.add(39);
        jolts.add(11);
        jolts.add(1);
        jolts.add(32);
        jolts.add(25);
        jolts.add(35);
        jolts.add(8);
        jolts.add(17);
        jolts.add(7);
        jolts.add(9);
        jolts.add(4);
        jolts.add(2);
        jolts.add(34);
        jolts.add(10);
        jolts.add(3);

        return jolts;
    }

    /**
     * Test Day 10 part one with example input one (with the known result)
     */
    @Test
    public void testExampleInputOnePartOne() {
        List<Integer> jolts = this.getExampleInputOne();

        Day10 d = new Day10();
        long partOneAnswer = d.solvePartOne(jolts);

        Assert.assertEquals(35, partOneAnswer);
    }

    /**
     * Test Day 10 part one with example input two (with the known result)
     */
    @Test
    public void testExampleInputTwoPartOne() {
        List<Integer> jolts = this.getExampleInputTwo();

        Day10 d = new Day10();
        long partOneAnswer = d.solvePartOne(jolts);

        Assert.assertEquals(220, partOneAnswer);
    }

    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> jolts = ProblemLoader.loadProblemIntoIntegerList(2020, 10);
        Day10 d = new Day10();
        long partOneAnswer = d.solvePartOne(jolts);

        Assert.assertEquals(1904, partOneAnswer);
    }

    /**
     * Test Day 10 part two with example input one (with the known result)
     */
    @Test
    public void testExampleInputOnePartTwo() {
        List<Integer> jolts = this.getExampleInputOne();

        Day10 d = new Day10();
        long partTwoAnswer = d.solvePartTwo(jolts);

        Assert.assertEquals(8, partTwoAnswer);
    }

    /**
     * Test Day 10 part two with example input two (with the known result)
     */
    @Test
    public void testExampleInputTwoPartTwo() {
        List<Integer> jolts = this.getExampleInputTwo();

        Day10 d = new Day10();
        long partTwoAnswer = d.solvePartTwo(jolts);

        Assert.assertEquals(19208, partTwoAnswer);
    }

    /**
     * Test Day 10 part Two with the real input (and the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> jolts = ProblemLoader.loadProblemIntoIntegerList(2020, 10);
        Day10 d = new Day10();
        long partTwoAnswer = d.solvePartTwo(jolts);

        Assert.assertEquals(10578455953408L, partTwoAnswer);
    }


}
