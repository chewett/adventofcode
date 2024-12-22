package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day22Test {

    /**
     * Function to get the example inputs for part one
     * @return The example input
     */
    public List<Integer> getExampleInputPartOne() {
        List<Integer> input = new ArrayList<>();

        input.add(1);
        input.add(10);
        input.add(100);
        input.add(2024);

        return input;
    }

    /**
     * Function to get the example inputs for part two
     * @return The example input
     */
    public List<Integer> getExampleInputPartTwo() {
        List<Integer> input = new ArrayList<>();

        input.add(1);
        input.add(2);
        input.add(3);
        input.add(2024);

        return input;
    }



    /**
     * Test Day 22 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<Integer> input = this.getExampleInputPartOne();
        Day22 d = new Day22();
        Assertions.assertEquals(37327623, d.solvePartOne(input));
    }


    /**
     * Test Day 22 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<Integer> input = ProblemLoader.loadProblemIntoIntegerList(2024, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(13185239446L, d.solvePartOne(input));
    }

    /**
     * Test Day 22 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<Integer> input = this.getExampleInputPartTwo();
        Day22 d = new Day22();
        Assertions.assertEquals(23, d.solvePartTwo(input));
    }

    /**
     * Test Day 22 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<Integer> input = ProblemLoader.loadProblemIntoIntegerList(2024, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(1501, d.solvePartTwo(input));
    }

}
