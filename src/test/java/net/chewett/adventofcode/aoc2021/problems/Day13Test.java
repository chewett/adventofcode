package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
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
        List<String> dotsAndInstructions = new ArrayList<>();
        dotsAndInstructions.add("6,10");
        dotsAndInstructions.add("0,14");
        dotsAndInstructions.add("9,10");
        dotsAndInstructions.add("0,3");
        dotsAndInstructions.add("10,4");
        dotsAndInstructions.add("4,11");
        dotsAndInstructions.add("6,0");
        dotsAndInstructions.add("6,12");
        dotsAndInstructions.add("4,1");
        dotsAndInstructions.add("0,13");
        dotsAndInstructions.add("10,12");
        dotsAndInstructions.add("3,4");
        dotsAndInstructions.add("3,0");
        dotsAndInstructions.add("8,4");
        dotsAndInstructions.add("1,10");
        dotsAndInstructions.add("2,14");
        dotsAndInstructions.add("8,10");
        dotsAndInstructions.add("9,0");
        dotsAndInstructions.add("");
        dotsAndInstructions.add("fold along y=7");
        dotsAndInstructions.add("fold along x=5");

        return dotsAndInstructions;

    }

    /**
     * Test Day 13 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> dotsAndInstructions = this.getExampleInput();
        Day13 d = new Day13();
        Assertions.assertEquals(17, d.solvePartOne(dotsAndInstructions));
    }


    /**
     * Test Day 13 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> dotsAndInstructions = ProblemLoader.loadProblemIntoStringArray(2021, 13);
        Day13 d = new Day13();
        Assertions.assertEquals(729, d.solvePartOne(dotsAndInstructions));
    }

    /**
     * Test Day 13 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> dotsAndInstructions = this.getExampleInput();
        Day13 d = new Day13();
        Discrete2DPositionGrid<Character> result = d.solvePartTwo(dotsAndInstructions);

        //This produces a square and you are meant to "look" at it to decide if it's good
        //But since I don't want to hardcode the square into the unit tests I'm currently just
        //checking that the right number of dots are present
        Assertions.assertEquals(16, result.countInstancesOfValue('#'));
    }

    /**
     * Test Day 13 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> dotsAndInstructions = ProblemLoader.loadProblemIntoStringArray(2021, 13);
        Day13 d = new Day13();
        Discrete2DPositionGrid<Character> result = d.solvePartTwo(dotsAndInstructions);

        //Really to check it you need to look at the actual string and read it but for now
        //the unit test is just going to verify the right number of dots appear
        Assertions.assertEquals(100, result.countInstancesOfValue('#'));
    }


}
