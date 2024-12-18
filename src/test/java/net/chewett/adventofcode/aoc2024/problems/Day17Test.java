package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day17Test {

    /**
     * Function to get the example input for part one
     * @return The example input
     */
    public List<String> getExampleInputForPartOne() {
        List<String> input = new ArrayList<>();

        input.add("Register A: 729");
        input.add("Register B: 0");
        input.add("Register C: 0");
        input.add("");
        input.add("Program: 0,1,5,4,3,0");

        return input;
    }

    /**
     * Function to get the example input for part two
     * @return The example input
     */
    public List<String> getExampleInputForPartTwo() {
        List<String> input = new ArrayList<>();

        input.add("Register A: 2024");
        input.add("Register B: 0");
        input.add("Register C: 0");
        input.add("");
        input.add("Program: 0,3,5,4,3,0");

        return input;
    }

    /**
     * Test Day 17 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInputForPartOne();
        Day17 d = new Day17();
        Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", d.solvePartOne(input));
    }


    /**
     * Test Day 17 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 17);
        Day17 d = new Day17();
        Assertions.assertEquals("4,3,7,1,5,3,0,5,4", d.solvePartOne(input));
    }

    /**
     * Test Day 17 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInputForPartTwo();
        Day17 d = new Day17();
        Assertions.assertEquals(117440, d.solvePartTwo(input));
    }

    /**
     * Test Day 17 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 17);
        Day17 d = new Day17();
        Assertions.assertEquals(190384615275535L, d.solvePartTwo(input));
    }

}
