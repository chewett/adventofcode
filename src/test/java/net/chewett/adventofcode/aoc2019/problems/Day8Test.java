package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day8Test {


    /**
     * Test Day 8 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(1703, d.solvePartOne(input));
    }

    /**
     * Test Day 8 part two with the real input (with the correct answer)
     *
     * FIXME: Work out how to nicely check the letters returned by a unit test and not visually getting the answer
     *
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(0, d.solvePartTwo(input));
    }

}
