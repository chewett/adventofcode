package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Day3Test {

    private List<List<Character>> getExampleInput() {
        String[] slopeStr = new String[]{
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
        };
        return FormatConversion.convertStringArrayToCharListList(slopeStr);
    }

    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<List<Character>> slope = this.getExampleInput();

        Day3 d = new Day3();
        int partOneAnswer = d.solvePartOne(slope);
        Assertions.assertEquals(7, partOneAnswer);
    }

    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<List<Character>> slope = ProblemLoader.loadProblemIntoXYCharList(2020, 3);
        Day3 d = new Day3();
        int partOneAnswer = d.solvePartOne(slope);

        Assertions.assertEquals(178, partOneAnswer);
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<List<Character>> slope = this.getExampleInput();
        Day3 d = new Day3();
        long partTwoAnswer = d.solvePartTwo(slope);

        Assertions.assertEquals(336, partTwoAnswer);
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<List<Character>> slope = ProblemLoader.loadProblemIntoXYCharList(2020, 3);
        Day3 d = new Day3();
        long partTwoAnswer = d.solvePartTwo(slope);

        Assertions.assertEquals(3492520200L, partTwoAnswer);
    }

}
