package net.chewett.adventofcode.aoc2021.problems;

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
        List<String> bingoInstructions = new ArrayList<>();
        bingoInstructions.add("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1");
        bingoInstructions.add("");
        bingoInstructions.add("22 13 17 11  0");
        bingoInstructions.add("8  2 23  4 24");
        bingoInstructions.add("21  9 14 16  7");
        bingoInstructions.add("6 10  3 18  5");
        bingoInstructions.add("1 12 20 15 19");
        bingoInstructions.add("");
        bingoInstructions.add("3 15  0  2 22");
        bingoInstructions.add("9 18 13 17  5");
        bingoInstructions.add("19  8  7 25 23");
        bingoInstructions.add("20 11 10 24  4");
        bingoInstructions.add("14 21 16 12  6");
        bingoInstructions.add("");
        bingoInstructions.add("14 21 17 24  4");
        bingoInstructions.add("10 16 15  9 19");
        bingoInstructions.add("18  8 23 26 20");
        bingoInstructions.add("22 11 13  6  5");
        bingoInstructions.add("2  0 12  3  7");

        return bingoInstructions;
    }


    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(4512, d.solvePartOne(report));
    }

    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(38594, d.solvePartOne(report));
    }

    /**
     * Test Day 4 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day4 d = new Day4();
        Assertions.assertEquals(1924, d.solvePartTwo(report));
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 4);
        Day4 d = new Day4();
        Assertions.assertEquals(21184, d.solvePartTwo(report));
    }




}
