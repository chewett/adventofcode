package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day21Test {


    /**
     * Tests one of the inputs where I had a corner case and it failed to proecss properly
     */
    @Test
    public void test179A() {
        Day21 day = new Day21();
        Assertions.assertEquals(68, day.runAlgorithmOnSingleString("179A", 3));
        //TODO: Try and get it to check the actual string after the refactor
        //Correct value is: <v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A
    }

    /**
     * Tests the shortest path function for the key pad to ensure it avoids the empty space
     */
    @Test
    public void testFindShortestKeyPadSequence() {
        Day21 day = new Day21();

        char[][] valsToTest = new char[][] {
                { 'A', 'A' },
                { 'A', '0' },
                { '0', 'A' },
                { '0', '1'}, // handle not moving across the empty space
                { '1', '0' },  // handle not moving across the empty space
                { '0', '4'}, // handle not moving across the empty space and bigger move
                { '4', '0' },  // handle not moving across the empty space and bigger move
                { 'A', '7' },  // handle not moving across the empty space and massive move
        };

        int[] expectedNumberOfWays = new int[] {
            1, 1, 1, 1, 1, 2, 2, 9
        };

        for(int i = 0; i < valsToTest.length; i++) {
            List<String> seqs = day.findShortestKeyPadSequence(valsToTest[i][0], valsToTest[i][1]);
            Assertions.assertEquals(expectedNumberOfWays[i], seqs.size());
        }
    }

    /**
     * Tests the shortest path function for the position pad to ensure it avoids the empty space
     */
    @Test
    public void testFindShortestPositionPadSequence() {
        Day21 day = new Day21();

        char[][] valsToTest = new char[][] {
                { 'A', 'A' },
                { 'A', '^' },
                { '^', 'A' },
                { '<', '^'}, // handle not moving across the empty space
                { '^', '<' },  // handle not moving across the empty space
                { 'A', '<'}, // handle not moving across the empty space and bigger move
                { '<', 'A' },  // handle not moving across the empty space and bigger move
        };

        int[] expectedNumberOfWays = new int[] {
                1, 1, 1, 1, 1, 2, 2
        };

        for(int i = 0; i < valsToTest.length; i++) {
            List<String> seqs = day.findShortestPositionPadSequence(valsToTest[i][0], valsToTest[i][1]);
            Assertions.assertEquals(expectedNumberOfWays[i], seqs.size());
        }
    }


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("029A");
        input.add("980A");
        input.add("179A");
        input.add("456A");
        input.add("379A");

        return input;
    }

    /**
     * Test Day 21 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day21 d = new Day21();
        Assertions.assertEquals(126384, d.solvePartOne(input));
    }


    /**
     * Test Day 21 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 21);
        Day21 d = new Day21();
        Assertions.assertEquals(211930, d.solvePartOne(input));
    }

    /**
     * Test Day 21 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 21);
        Day21 d = new Day21();
        Assertions.assertEquals(263492840501566L, d.solvePartTwo(input));
    }

}
