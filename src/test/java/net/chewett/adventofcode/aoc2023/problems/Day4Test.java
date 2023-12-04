package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day4Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
        input.add("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
        input.add("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
        input.add("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
        input.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
        input.add("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");

        return input;
    }

    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(13, d.solvePartOne(input));
    }


    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(21568, d.solvePartOne(input));
    }

    /**
     * Test Day 4 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(30, d.solvePartTwo(input));
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(11827296, d.solvePartTwo(input));
    }

}
