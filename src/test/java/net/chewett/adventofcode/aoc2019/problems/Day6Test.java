package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day6Test {

    /**
     * Function to get the example inputs for part one
     * @return The example input
     */
    public List<String> getExampleInputForPartOne() {
        List<String> input = new ArrayList<>();

        input.add("COM)B");
        input.add("B)C");
        input.add("C)D");
        input.add("D)E");
        input.add("E)F");
        input.add("B)G");
        input.add("G)H");
        input.add("D)I");
        input.add("E)J");
        input.add("J)K");
        input.add("K)L");

        return input;
    }

    /**
     * Function to get the example inputs for part two
     * @return The example input
     */
    public List<String> getExampleInputForPartTwo() {
        List<String> input = new ArrayList<>();

        input.add("COM)B");
        input.add("B)C");
        input.add("C)D");
        input.add("D)E");
        input.add("E)F");
        input.add("B)G");
        input.add("G)H");
        input.add("D)I");
        input.add("E)J");
        input.add("J)K");
        input.add("K)L");
        input.add("K)YOU");
        input.add("I)SAN");

        return input;
    }

    /**
     * Test Day 6 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInputForPartOne();
        Day6 d = new Day6();
        Assertions.assertEquals(42, d.solvePartOne(input));
    }


    /**
     * Test Day 6 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(247089, d.solvePartOne(input));
    }

    /**
     * Test Day 6 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInputForPartTwo();
        Day6 d = new Day6();
        Assertions.assertEquals(4, d.solvePartTwo(input));
    }

    /**
     * Test Day 6 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 6);
        Day6 d = new Day6();
        Assertions.assertEquals(442, d.solvePartTwo(input));
    }

}
