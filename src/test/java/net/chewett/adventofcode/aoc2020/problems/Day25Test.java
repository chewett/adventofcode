package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class Day25Test {

    /**
     * Test Day 25 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> publicKeys = new ArrayList<>();
        publicKeys.add("5764801"); publicKeys.add("17807724");

        Day25 d = new Day25();
        long partOneAnswer = d.solvePartOne(publicKeys);

        Assert.assertEquals(14897079, partOneAnswer);
    }

    /**
     * Test Day 25 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> publicKeys = ProblemLoader.loadProblemIntoStringArray(2020, 25);
        Day25 d = new Day25();
        long partOneAnswer = d.solvePartOne(publicKeys);

        Assert.assertEquals(12929, partOneAnswer);
    }

}
