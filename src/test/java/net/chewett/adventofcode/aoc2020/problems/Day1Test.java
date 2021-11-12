package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.aoc2019.spaceship.SpaceshipModule;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day1Test {

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1721);
        ints.add(979);
        ints.add(366);
        ints.add(299);
        ints.add(675);
        ints.add(1456);

        Day1 d = new Day1();
        long partOneAnswer = d.solvePartOne(ints);

        Assertions.assertEquals(514579, partOneAnswer);
    }

    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> ints = ProblemLoader.loadProblemIntoIntegerList(2020, 1);
        Day1 d = new Day1();
        long partOneAnswer = d.solvePartOne(ints);

        Assertions.assertEquals(100419, partOneAnswer);
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1721);
        ints.add(979);
        ints.add(366);
        ints.add(299);
        ints.add(675);
        ints.add(1456);

        Day1 d = new Day1();
        long partTwoAnswer = d.solvePartTwo(ints);

        Assertions.assertEquals(241861950, partTwoAnswer);
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> ints = ProblemLoader.loadProblemIntoIntegerList(2020, 1);
        Day1 d = new Day1();
        long partTwoAnswer = d.solvePartTwo(ints);

        Assertions.assertEquals(265253940, partTwoAnswer);
    }

}
