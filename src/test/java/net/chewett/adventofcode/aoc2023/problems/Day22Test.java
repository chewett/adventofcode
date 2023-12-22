package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day22Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("1,0,1~1,2,1");
        input.add("0,0,2~2,0,2");
        input.add("0,2,3~2,2,3");
        input.add("0,0,4~0,2,4");
        input.add("2,0,5~2,2,5");
        input.add("0,1,6~2,1,6");
        input.add("1,1,8~1,1,9");

        return input;
    }

    /**
     * Test Day 22 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day22 d = new Day22();
        Assertions.assertEquals(5, d.solvePartOne(input));
    }


    /**
     * Test Day 22 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(475, d.solvePartOne(input));
    }

    /**
     * Test Day 22 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day22 d = new Day22();
        Assertions.assertEquals(7, d.solvePartTwo(input));
    }

    /**
     * Test Day 22 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(79144, d.solvePartTwo(input));
    }

}
