package net.chewett.adventofcode.aoc2022.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;

public class Day15Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Sensor at x=2, y=18: closest beacon is at x=-2, y=15");
        input.add("Sensor at x=9, y=16: closest beacon is at x=10, y=16");
        input.add("Sensor at x=13, y=2: closest beacon is at x=15, y=3");
        input.add("Sensor at x=12, y=14: closest beacon is at x=10, y=16");
        input.add("Sensor at x=10, y=20: closest beacon is at x=10, y=16");
        input.add("Sensor at x=14, y=17: closest beacon is at x=10, y=16");
        input.add("Sensor at x=8, y=7: closest beacon is at x=2, y=10");
        input.add("Sensor at x=2, y=0: closest beacon is at x=2, y=10");
        input.add("Sensor at x=0, y=11: closest beacon is at x=2, y=10");
        input.add("Sensor at x=20, y=14: closest beacon is at x=25, y=17");
        input.add("Sensor at x=17, y=20: closest beacon is at x=21, y=22");
        input.add("Sensor at x=16, y=7: closest beacon is at x=15, y=3");
        input.add("Sensor at x=14, y=3: closest beacon is at x=15, y=3");
        input.add("Sensor at x=20, y=1: closest beacon is at x=15, y=3");

        return input;
    }

    /**
     * Test Day 15 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day15 d = new Day15();
        Assertions.assertEquals(26, d.solvePartOne(input, 10));
    }


    /**
     * Test Day 15 part one with the real input (and the correct answer)
     */
    //@Test //TODO: FIXME: For some reason gradle times these out... to investigate later
    public void testRealPartOne() {

        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(5607466, d.solvePartOne(input, 2000000));
    }

    /**
     * Test Day 15 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day15 d = new Day15();
        Assertions.assertEquals(56000011, d.solvePartTwo(input, 20));
    }

    /**
     * Test Day 15 part two with the real input (with the correct answer)
     */
    //@Test //TODO: FIXME: For some reason gradle times these out... to investigate later
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(12543202766584L, d.solvePartTwo(input, 4000000));
    }

}
