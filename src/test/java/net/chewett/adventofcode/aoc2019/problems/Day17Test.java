package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day17Test {

    /**
     * Test Day 17 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2019, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(4600, d.solvePartOne(input));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2019, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(1113411, d.solvePartTwo(input));
    }

}
