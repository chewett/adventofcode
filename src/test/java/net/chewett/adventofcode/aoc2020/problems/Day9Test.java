package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day9Test {

    private List<Long> getExampleInput() {
        List<Long> longs = new ArrayList<>();
        longs.add(35L);
        longs.add(20L);
        longs.add(15L);
        longs.add(25L);
        longs.add(47L);
        longs.add(40L);
        longs.add(62L);
        longs.add(55L);
        longs.add(65L);
        longs.add(95L);
        longs.add(102L);
        longs.add(117L);
        longs.add(150L);
        longs.add(182L);
        longs.add(127L);
        longs.add(219L);
        longs.add(299L);
        longs.add(277L);
        longs.add(309L);
        longs.add(576L);

        return longs;
    }

    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Long> longs = this.getExampleInput();
        Day9 d = new Day9();
        long partOneAnswer = d.solvePartOne(longs, 5);

        Assert.assertEquals(127, partOneAnswer);
    }

    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Long> longs = ProblemLoader.loadProblemIntoLongList(2020, 9);
        Day9 d = new Day9();
        long partOneAnswer = d.solvePartOne(longs, 25);

        Assert.assertEquals(258585477, partOneAnswer);
    }

    /**
     * Test Day 9 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Long> longs = this.getExampleInput();
        Day9 d = new Day9();
        long partTwoAnswer = d.solvePartTwo(longs, 127);

        Assert.assertEquals(62, partTwoAnswer);
    }

    /**
     * Test Day 9 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Long> longs = ProblemLoader.loadProblemIntoLongList(2020, 9);
        Day9 d = new Day9();
        long partTwoAnswer = d.solvePartTwo(longs, 258585477);

        Assert.assertEquals(36981213, partTwoAnswer);
    }

}
