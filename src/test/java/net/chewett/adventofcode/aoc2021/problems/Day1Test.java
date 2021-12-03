package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class Day1Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<Integer> getExampleInput() {
        List<Integer> ints = new ArrayList<>();
        ints.add(199);
        ints.add(200);
        ints.add(208);
        ints.add(210);
        ints.add(200);
        ints.add(207);
        ints.add(240);
        ints.add(269);
        ints.add(260);
        ints.add(263);
        return ints;
    }


    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> ints = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(7, d.solvePartOne(ints));
    }

    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> ints = ProblemLoader.loadProblemIntoIntegerList(2021, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(1655, d.solvePartOne(ints));
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> ints = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(5, d.solvePartTwo(ints));
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> ints = ProblemLoader.loadProblemIntoIntegerList(2021, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(1683, d.solvePartTwo(ints));
    }





}
