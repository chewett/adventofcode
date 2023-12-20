package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day18Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("R 6 (#70c710)");
        input.add("D 5 (#0dc571)");
        input.add("L 2 (#5713f0)");
        input.add("D 2 (#d2c081)");
        input.add("R 2 (#59c680)");
        input.add("D 2 (#411b91)");
        input.add("L 5 (#8ceee2)");
        input.add("U 2 (#caa173)");
        input.add("L 1 (#1b58a2)");
        input.add("U 2 (#caa171)");
        input.add("R 2 (#7807d2)");
        input.add("U 3 (#a77fa3)");
        input.add("L 2 (#015232)");
        input.add("U 2 (#7a21e3)");

        return input;
    }

    /**
     * Test Day 18 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day18 d = new Day18();
        Assertions.assertEquals(62, d.solvePartOne(input));
    }


    /**
     * Test Day 18 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(62573, d.solvePartOne(input));
    }

    /**
     * Test Day 18 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day18 d = new Day18();
        Assertions.assertEquals(952408144115L, d.solvePartTwo(input));
    }

    /**
     * Test Day 18 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(54662804037719L, d.solvePartTwo(input));
    }

}
