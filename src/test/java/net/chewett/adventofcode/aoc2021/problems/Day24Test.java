package net.chewett.adventofcode.aoc2021.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day24Test {

    /**
     * Test Day 24 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Day24 d = new Day24();
        Assertions.assertEquals(52926995971999L, d.solvePartOne());
    }


    /**
     * Test Day 24 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Day24 d = new Day24();
        Assertions.assertEquals(11811951311485L, d.solvePartTwo());
    }

    /**
     * Quick check to make sure the maths is implemented for a multiply step
     */
    @Test
    public void testRunCodeSegment() {
        Day24 d = new Day24();
        Assertions.assertEquals(16, d.runCodeSegment(0, 1, 1, 10, 15));
    }

    /**
     * Quick check to make sure the maths is implemented for a divide step
     */
    @Test
    public void testRunCodeSegment2() {
        Day24 d = new Day24();
        Assertions.assertEquals(5, d.runCodeSegment(150, 8, 26, -12, 13));
    }

}
