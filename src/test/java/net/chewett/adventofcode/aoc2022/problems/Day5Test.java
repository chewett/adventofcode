package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day5Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> billOfWork = new ArrayList<>();

        billOfWork.add("    [D]");
        billOfWork.add("[N] [C]");
        billOfWork.add("[Z] [M] [P]");
        billOfWork.add(" 1   2   3");
        billOfWork.add("");
        billOfWork.add("move 1 from 2 to 1");
        billOfWork.add("move 3 from 1 to 3");
        billOfWork.add("move 2 from 2 to 1");
        billOfWork.add("move 1 from 1 to 2");

        return billOfWork;

    }

    /**
     * Test Day 5 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> billOfWork = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals("CMZ", d.solvePartOne(billOfWork));
    }


    /**
     * Test Day 5 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 5);
        Day5 d = new Day5();
        Assertions.assertEquals("GFTNRBZPF", d.solvePartOne(report));
    }

    /**
     * Test Day 5 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day5 d = new Day5();
        Assertions.assertEquals("MCD", d.solvePartTwo(report));
    }

    /**
     * Test Day 5 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 5);
        Day5 d = new Day5();
        Assertions.assertEquals("VRQWPDSGP", d.solvePartTwo(report));
    }



}
