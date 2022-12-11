package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day11Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Monkey 0:");
        input.add("  Starting items: 79, 98");
        input.add("  Operation: new = old * 19");
        input.add("  Test: divisible by 23");
        input.add("    If true: throw to monkey 2");
        input.add("    If false: throw to monkey 3");
        input.add("");
        input.add("Monkey 1:");
        input.add("  Starting items: 54, 65, 75, 74");
        input.add("  Operation: new = old + 6");
        input.add("  Test: divisible by 19");
        input.add("    If true: throw to monkey 2");
        input.add("    If false: throw to monkey 0");
        input.add("");
        input.add("Monkey 2:");
        input.add("  Starting items: 79, 60, 97");
        input.add("  Operation: new = old * old");
        input.add("  Test: divisible by 13");
        input.add("    If true: throw to monkey 1");
        input.add("    If false: throw to monkey 3");
        input.add("");
        input.add("Monkey 3:");
        input.add("  Starting items: 74");
        input.add("  Operation: new = old + 3");
        input.add("  Test: divisible by 17");
        input.add("    If true: throw to monkey 0");
        input.add("    If false: throw to monkey 1");

        return input;
    }

    /**
     * Test Day 11 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day11 d = new Day11();
        Assertions.assertEquals(10605, d.solvePartOne(input));
    }


    /**
     * Test Day 11 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(120756, d.solvePartOne(report));
    }

    /**
     * Test Day 11 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day11 d = new Day11();
        Assertions.assertEquals(2713310158L, d.solvePartTwo(report));
    }

    /**
     * Test Day 11 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 11);
        Day11 d = new Day11();
        Assertions.assertEquals(39109444654L, d.solvePartTwo(report));
    }

}
