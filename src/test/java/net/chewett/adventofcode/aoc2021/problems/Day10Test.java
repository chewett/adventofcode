package net.chewett.adventofcode.aoc2021.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day10Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> badCode = new ArrayList<>();
        badCode.add("[({(<(())[]>[[{[]{<()<>>");
        badCode.add("[(()[<>])]({[<{<<[]>>(");
        badCode.add("{([(<{}[<>[]}>{[]{[(<()>");
        badCode.add("(((({<>}<{<{<>}{[]{[]{}");
        badCode.add("[[<[([]))<([[{}[[()]]]");
        badCode.add("[{[{({}]{}}([{[{{{}}([]");
        badCode.add("{<[[]]>}<{[{[{[]{()[[[]");
        badCode.add("[<(<(<(<{}))><([]([]()");
        badCode.add("<{([([[(<>()){}]>(<<{{");
        badCode.add("<{([{{}}[<[[[<>{}]]]>[]]");

        return badCode;
    }

    /**
     * Test Day 10 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> badCode = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(26397, d.solvePartOne(badCode));
    }


    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> badCode = ProblemLoader.loadProblemIntoStringArray(2021, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(288291, d.solvePartOne(badCode));
    }

    /**
     * Test Day 10 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> badCode = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(288957, d.solvePartTwo(badCode));
    }

    /**
     * Test Day 10 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> badCode = ProblemLoader.loadProblemIntoStringArray(2021, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(820045242, d.solvePartTwo(badCode));
    }
    
}
