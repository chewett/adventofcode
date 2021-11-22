package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Day7Test {

    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> circuitLines = ProblemLoader.loadProblemIntoStringArray(2015, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(956, d.solvePartOne(circuitLines));
    }


    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> circuitLines = ProblemLoader.loadProblemIntoStringArray(2015, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(40149, d.solvePartTwo(circuitLines));
    }


}
