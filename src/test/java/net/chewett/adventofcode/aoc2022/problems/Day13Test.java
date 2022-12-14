package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day13Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();
        
        input.add("[1,1,3,1,1]");
        input.add("[1,1,5,1,1]");
        input.add("");
        input.add("[[1],[2,3,4]]");
        input.add("[[1],4]");
        input.add("");
        input.add("[9]");
        input.add("[[8,7,6]]");
        input.add("");
        input.add("[[4,4],4,4]");
        input.add("[[4,4],4,4,4]");
        input.add("");
        input.add("[7,7,7,7]");
        input.add("[7,7,7]");
        input.add("");
        input.add("[]");
        input.add("[3]");
        input.add("");
        input.add("[[[]]]");
        input.add("[[]]");
        input.add("");
        input.add("[1,[2,[3,[4,[5,6,7]]]],8,9]");
        input.add("[1,[2,[3,[4,[5,6,0]]]],8,9]");
        input.add("");

        return input;
        
    }

    /**
     * Test Day 13 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day13 d = new Day13();
        Assertions.assertEquals(13, d.solvePartOne(report));
    }


    /**
     * Test Day 13 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(5529, d.solvePartOne(report));
    }

    /**
     * Test Day 13 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day13 d = new Day13();
        Assertions.assertEquals(140, d.solvePartTwo(report));
    }

    /**
     * Test Day 13 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(27690, d.solvePartTwo(report));
    }


}
