package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day8Test {

    /**
     * An example input string provided by AoC with the right answers
     * @return
     */
    private List<String> getExampleInput() {
        List<String> lines = new ArrayList<>();

        lines.add("nop +0");
        lines.add("acc +1");
        lines.add("jmp +4");
        lines.add("acc +3");
        lines.add("jmp -3");
        lines.add("acc -99");
        lines.add("acc +1");
        lines.add("jmp -4");
        lines.add("acc +6");

        return lines;
    }

    /**
     * Test Day 8 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> lines = this.getExampleInput();

        Day8 d = new Day8();
        int partOneAnswer = d.solvePartOne(lines);
        Assertions.assertEquals(5, partOneAnswer);
    }

    /**
     * Test Day 8 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 8);
        Day8 d = new Day8();
        int partOneAnswer = d.solvePartOne(lines);

        Assertions.assertEquals(1939, partOneAnswer);
    }

    /**
     * Test Day 8 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> lines = this.getExampleInput();

        Day8 d = new Day8();
        int partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(8, partTwoAnswer);
    }

    /**
     * Test Day 8 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 8);
        Day8 d = new Day8();
        int partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(2212, partTwoAnswer);
    }

}
