package net.chewett.adventofcode.aoc2021.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day16Test {


    /**
     * Test Day 16 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String packet = "A0016C880162017C3686B18A3D4780";
        Day16 d = new Day16();
        Assertions.assertEquals(31, d.solvePartOne(packet));
    }


    /**
     * Test Day 16 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String packet = ProblemLoader.loadProblemIntoString(2021, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(1002, d.solvePartOne(packet));
    }

    /**
     * Test Day 16 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String packet = "9C0141080250320F1802104A08";
        Day16 d = new Day16();
        Assertions.assertEquals(1, d.solvePartTwo(packet));
    }

    /**
     * Test Day 16 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String packet = ProblemLoader.loadProblemIntoString(2021, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(1673210814091L, d.solvePartTwo(packet));
    }

}
