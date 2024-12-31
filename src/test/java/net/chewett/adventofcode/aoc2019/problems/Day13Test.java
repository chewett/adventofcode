package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day13Test {

    /**
     * Test Day 13 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(265, d.solvePartOne(input));
    }

    /**
     * Test Day 13 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(13331, d.solvePartTwo(input));
    }

}
