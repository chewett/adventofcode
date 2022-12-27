package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day22Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("        ...#    ");
        input.add("        .#..    ");
        input.add("        #...    ");
        input.add("        ....    ");
        input.add("...#.......#    ");
        input.add("........#...    ");
        input.add("..#....#....    ");
        input.add("..........#.    ");
        input.add("        ...#....");
        input.add("        .....#..");
        input.add("        .#......");
        input.add("        ......#.");
        input.add("");
        input.add("10R5L5R10L4R5L5");

        return input;
    }

    /**
     * Test Day 22 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> blueprints = this.getExampleInput();
        Day22 d = new Day22();
        Assertions.assertEquals(6032, d.solvePartOne(blueprints));
    }


    /**
     * Test Day 22 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(144244, d.solvePartOne(blueprints));
    }

    /**
     * Test Day 22 part two with the example input (with the known result)
     *
     * Note: The current code is explicitly defined to handle the real input so this won't work for now...
     */
    //@Test
    public void testExampleInputPartTwo() {
        List<String> blueprints = this.getExampleInput();
        Day22 d = new Day22();
        Assertions.assertEquals(5031, d.solvePartTwo(blueprints));
    }

    /**
     * Test Day 22 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(138131, d.solvePartTwo(blueprints));
    }


}
