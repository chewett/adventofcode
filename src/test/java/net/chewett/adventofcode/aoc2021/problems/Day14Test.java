package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day14Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> instructions = new ArrayList<>();

        instructions.add("NNCB");
        instructions.add("");
        instructions.add("CH -> B");
        instructions.add("HH -> N");
        instructions.add("CB -> H");
        instructions.add("NH -> C");
        instructions.add("HB -> C");
        instructions.add("HC -> B");
        instructions.add("HN -> C");
        instructions.add("NN -> C");
        instructions.add("BH -> H");
        instructions.add("NC -> B");
        instructions.add("NB -> B");
        instructions.add("BN -> B");
        instructions.add("BB -> N");
        instructions.add("BC -> B");
        instructions.add("CC -> N");
        instructions.add("CN -> C");

        return instructions;
}

    /**
     * Test Day 14 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> instructions = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(1588, d.solvePartOne(instructions));
    }


    /**
     * Test Day 14 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(2587, d.solvePartOne(instructions));
    }

    /**
     * Test Day 14 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> instructions = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(2188189693529L, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 14 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(3318837563123L, d.solvePartTwo(instructions));
    }
    
}
