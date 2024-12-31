package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day3Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        input.add("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

        return input;
    }

    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(135, d.solvePartOne(input));
    }


    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(1064, d.solvePartOne(input));
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(410, d.solvePartTwo(input));
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(25676 , d.solvePartTwo(input));
    }

}
