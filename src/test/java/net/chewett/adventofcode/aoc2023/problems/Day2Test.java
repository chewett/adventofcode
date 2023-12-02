package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day2Test {

    /**
     * Function to get the example input
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> example = new ArrayList<>();

        example.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        example.add("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        example.add("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        example.add("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        example.add("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");

        return example;
    }

    /**
     * Test Day 2 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(8, d.solvePartOne(input));
    }


    /**
     * Test Day 2 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(2377, d.solvePartOne(input));
    }

    /**
     * Test Day 2 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day2 d = new Day2();
        Assertions.assertEquals(2286, d.solvePartTwo(input));
    }

    /**
     * Test Day 2 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 2);
        Day2 d = new Day2();
        Assertions.assertEquals(71220, d.solvePartTwo(input));
    }

}
