package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.aoc2023.problems.Day1;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day1Test {

    /**
     * Function to get the example input for part one
     * @return The example input
     */
    public List<String> getExampleInputPartOne() {
        List<String> example = new ArrayList<>();

        example.add("1abc2");
        example.add("pqr3stu8vwx");
        example.add("a1b2c3d4e5f");
        example.add("treb7uchet");
        
        return example;
    }

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInputPartOne();
        Day1 d = new Day1();
        Assertions.assertEquals(142, d.solvePartOne(input));
    }


    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(53080, d.solvePartOne(input));
    }

    /**
     * Function to get the example input for part one
     * @return The example input
     */
    public List<String> getExampleInputPartTwo() {
        List<String> example = new ArrayList<>();

        example.add("two1nine");
        example.add("eightwothree");
        example.add("abcone2threexyz");
        example.add("xtwone3four");
        example.add("4nineeightseven2");
        example.add("zoneight234");
        example.add("7pqrstsixteen");

        return example;
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInputPartTwo();
        Day1 d = new Day1();
        Assertions.assertEquals(281, d.solvePartTwo(input));
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(53268, d.solvePartTwo(input));
    }

}
