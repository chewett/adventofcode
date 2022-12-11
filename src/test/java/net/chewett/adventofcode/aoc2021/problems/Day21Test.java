package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class Day21Test {

    /**
     * Test Day 21 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day21 d = new Day21();
        Assertions.assertEquals(739785, d.solvePartOne(4, 8));
    }


    /**
     * Test Day 21 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2021, 21);
        int playerOnePosition = Integer.parseInt(lines.get(0).split(": ")[1]);
        int playerTwoPosition = Integer.parseInt(lines.get(1).split(": ")[1]);

        Day21 d = new Day21();
        Assertions.assertEquals(925605, d.solvePartOne(playerOnePosition, playerTwoPosition));
    }

    /**
     * Test Day 21 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Day21 d = new Day21();
        Assertions.assertEquals(444356092776315L, d.solvePartTwo(4, 8));
    }

    /**
     * Test Day 21 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2021, 21);
        int playerOnePosition = Integer.parseInt(lines.get(0).split(": ")[1]);
        int playerTwoPosition = Integer.parseInt(lines.get(1).split(": ")[1]);


        Day21 d = new Day21();
        Assertions.assertEquals(486638407378784L, d.solvePartTwo(playerOnePosition, playerTwoPosition));
    }


}
