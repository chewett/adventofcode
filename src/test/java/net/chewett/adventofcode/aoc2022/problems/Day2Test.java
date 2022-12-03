package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day2Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> moves = new ArrayList<>();

        moves.add("A Y");
        moves.add("B X");
        moves.add("C Z");

        return moves;
    }

    /**
     * Test Day 2 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> moves = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(15, d.solvePartOne(moves));
    }


    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> moves = ProblemLoader.loadProblemIntoStringArray(2022, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(13268, d.solvePartOne(moves));
    }

    /**
     * Test Day 2 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> moves = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(12, d.solvePartTwo(moves));
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> moves = ProblemLoader.loadProblemIntoStringArray(2022, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(15508, d.solvePartTwo(moves));
    }

}
