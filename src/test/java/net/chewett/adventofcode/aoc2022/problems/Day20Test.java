package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day20Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<Integer> getExampleInput() {
        List<Integer> input = new ArrayList<>();

        input.add(1);
        input.add(2);
        input.add(-3);
        input.add(3);
        input.add(-2);
        input.add(0);
        input.add(4);

        return input;
    }

    /**
     * Test Day 20 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> blueprints = this.getExampleInput();
        Day20 d = new Day20();
        Assertions.assertEquals(3, d.solvePartOne(blueprints));
    }


    /**
     * Test Day 20 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> blueprints = ProblemLoader.loadProblemIntoIntegerList(2022, 20);
        Day20 d = new Day20();
        Assertions.assertEquals(13522, d.solvePartOne(blueprints));
    }

    /**
     * Test Day 20 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> blueprints = this.getExampleInput();
        Day20 d = new Day20();
        Assertions.assertEquals(1623178306L, d.solvePartTwo(blueprints));
    }

    /**
     * Test Day 20 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> blueprints = ProblemLoader.loadProblemIntoIntegerList(2022, 20);
        Day20 d = new Day20();
        Assertions.assertEquals(17113168880158L, d.solvePartTwo(blueprints));
    }


}
