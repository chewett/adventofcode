package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day13Test {

    private List<String> getExampleInput() {
        List<String> notes = new ArrayList<>();
        notes.add("939");
        notes.add("7,13,x,x,59,x,31,19");

        return notes;
    }

    /**
     * Test Day 13 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> notes = this.getExampleInput();

        Day13 d = new Day13();
        int partOneAnswer = d.solvePartOne(notes);

        Assertions.assertEquals(295, partOneAnswer);
    }

    /**
     * Test Day 13 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> notes = ProblemLoader.loadProblemIntoStringArray(2020, 13);
        Day13 d = new Day13();
        int partOneAnswer = d.solvePartOne(notes);

        Assertions.assertEquals(104, partOneAnswer);
    }

    /**
     * Test Day 13 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> notes = this.getExampleInput();

        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(1068781, partTwoAnswer);
    }

    /**
     * Test Day 13 part two with example input two (with the known result)
     */
    @Test
    public void testExampleInputTwoPartTwo() {
        List<String> notes = new ArrayList<>();
        notes.add("0");
        notes.add("17,x,13,19");

        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(3417, partTwoAnswer);
    }

    /**
     * Test Day 13 part two with example input three (with the known result)
     */
    @Test
    public void testExampleInputThreePartTwo() {
        List<String> notes = new ArrayList<>();
        notes.add("0");
        notes.add("67,7,59,61");

        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(754018, partTwoAnswer);
    }

    /**
     * Test Day 13 part two with example input four (with the known result)
     */
    @Test
    public void testExampleInputFourPartTwo() {
        List<String> notes = new ArrayList<>();
        notes.add("0");
        notes.add("67,x,7,59,61");

        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(779210, partTwoAnswer);
    }

    /**
     * Test Day 13 part two with example input five (with the known result)
     */
    @Test
    public void testExampleInputFivePartTwo() {
        List<String> notes = new ArrayList<>();
        notes.add("0");
        notes.add("67,7,x,59,61");

        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(1261476, partTwoAnswer);
    }

    /**
     * Test Day 13 part two with example input six (with the known result)
     */
    @Test
    public void testExampleInputSixPartTwo() {
        List<String> notes = new ArrayList<>();
        notes.add("0");
        notes.add("1789,37,47,1889");

        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(1202161486, partTwoAnswer);
    }

    /**
     * Test Day 13 part Two with the real input (and the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> notes = ProblemLoader.loadProblemIntoStringArray(2020, 13);
        Day13 d = new Day13();
        long partTwoAnswer = d.solvePartTwo(notes);

        Assertions.assertEquals(842186186521918L, partTwoAnswer);
    }


}
