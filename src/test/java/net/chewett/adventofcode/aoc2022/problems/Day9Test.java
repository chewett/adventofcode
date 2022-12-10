package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day9Test {


    /**
     * Function to get the example input for part one
     * @return The example input
     */
    public List<String> getExampleInputForPartOne() {
        List<String> moves = new ArrayList<>();

        moves.add("R 4");
        moves.add("U 4");
        moves.add("L 3");
        moves.add("D 1");
        moves.add("R 4");
        moves.add("D 1");
        moves.add("L 5");
        moves.add("R 2");

        return moves;
    }

    /**
     * Function to get the example input for part two
     * @return The example input
     */
    public List<String> getExampleInputForPartTwo() {
        List<String> moves = new ArrayList<>();

        moves.add("R 5");
        moves.add("U 8");
        moves.add("L 8");
        moves.add("D 3");
        moves.add("R 17");
        moves.add("D 10");
        moves.add("L 25");
        moves.add("U 20");

        return moves;
    }




    /**
     * Test Day 9 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> moves = this.getExampleInputForPartOne();
        Day9 d = new Day9();
        Assertions.assertEquals(13, d.solvePartOne(moves));
    }


    /**
     * Test Day 9 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> moves = ProblemLoader.loadProblemIntoStringArray(2022, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(5695, d.solvePartOne(moves));
    }

    /**
     * Test Day 9 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> moves = this.getExampleInputForPartTwo();
        Day9 d = new Day9();
        Assertions.assertEquals(36, d.solvePartTwo(moves));
    }

    /**
     * Test Day 9 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> moves = ProblemLoader.loadProblemIntoStringArray(2022, 9);
        Day9 d = new Day9();
        Assertions.assertEquals(2434, d.solvePartTwo(moves));
    }



}
