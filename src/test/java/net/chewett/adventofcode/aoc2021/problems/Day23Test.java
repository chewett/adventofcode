package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day23Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "00BA0CD0BC0DA00";
    }


    /**
     * Test Day 23 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day23 d = new Day23();
        Assertions.assertEquals(12521, d.solvePartOne(this.getExampleInput()));
    }

    /**
     * Attempts to solve part one by starting one step before the finish
     */
    @Test
    public void testExamplePartOneOneStepBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(8, d.solvePartOne("000A0BB0CC0DDA0"));
    }

    /**
     * Attempts to solve part one by starting three steps before the finish
     */
    @Test
    public void testExamplePartOneThreeStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(7008, d.solvePartOne("000A0BBDCCD00A0"));
    }

    /**
     * Attempts to solve part one by starting five steps before the finish
     */
    @Test
    public void testExamplePartOneFiveStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(9011, d.solvePartOne("000A0BBDCC0DA00"));
    }

    /**
     * Test Day 23 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2021, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(15322, d.solvePartOne(input));
    }

    /**
     * Test Day 23 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Day23 d = new Day23();
        Assertions.assertEquals(44169, d.solvePartTwo(this.getExampleInput()));
    }

    /**
     * Attempts to solve part two by starting one step before the finish
     */
    @Test
    public void testExamplePartTwoOneStepBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(3000, d.solvePartTwo("00AA0BB0CC00D0DAABBCCDD"));
    }

    /**
     * Attempts to solve part one by starting two steps before the finish
     */
    @Test
    public void testExamplePartTwoTwoStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(3008, d.solvePartTwo("000A0BB0CC00DADAABBCCDD"));
    }

    /**
     * Attempts to solve part one by starting three steps before the finish
     */
    @Test
    public void testExamplePartTwoThreeStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(10008, d.solvePartTwo("000ADBB0CC000ADAABBCCDD"));
    }

    /**
     * Attempts to solve part one by starting four steps before the finish
     */
    @Test
    public void testExamplePartTwoFourStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(10012, d.solvePartTwo("A000DBB0CC000ADAABBCCDD"));
    }

    /**
     * Attempts to solve part one by starting five steps before the finish
     */
    @Test
    public void testExamplePartTwoFiveStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(10016, d.solvePartTwo("AA00DBB0CC000AD0ABBCCDD"));
    }

    /**
     * Attempts to solve part one by starting eight steps before the finish
     */
    @Test
    public void testExamplePartTwoEightStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(25056, d.solvePartTwo("AABD00B0CC000ADDABBCC0D"));
    }

    /**
     * Attempts to solve part one by starting fifteen steps before the finish
     */
    @Test
    public void testExamplePartTwoFifteenStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(39841, d.solvePartTwo("AABD000B0CB00BDDA0DCCCA"));
    }

    /**
     * Attempts to solve part one by starting eighteen steps before the finish
     */
    @Test
    public void testExamplePartTwoEighteenStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(41081, d.solvePartTwo("AABD0CC000B00BDDABD0CCA"));
    }

    /**
     * Attempts to solve part one by starting nineteen steps before the finish
     */
    @Test
    public void testExamplePartTwoNineteenStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(41089, d.solvePartTwo("A0BD0CC000B00BDDABDACCA"));
    }

    /**
     * Attempts to solve part one by starting twenty steps before the finish
     */
    @Test
    public void testExamplePartTwoTwentyStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(41119, d.solvePartTwo("A0BD0CC00B000BDDABDACCA"));
    }

    /**
     * Attempts to solve part one by starting twenty one steps before the finish
     */
    @Test
    public void testExamplePartTwoTwentyOneStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(41159, d.solvePartTwo("A0BD0CC0BB0000DDABDACCA"));
    }

    /**
     * Attempts to solve part one by starting twenty two steps before the finish
     */
    @Test
    public void testExamplePartTwoTwentyTwoStepsBack() {
        Day23 d = new Day23();
        Assertions.assertEquals(41169, d.solvePartTwo("00BD0CC0BB00A0DDABDACCA"));
    }

    /**
     * Test Day 23 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2021, 23);

        Day23 d = new Day23();
        Assertions.assertEquals(56324, d.solvePartTwo(input));
    }


}
