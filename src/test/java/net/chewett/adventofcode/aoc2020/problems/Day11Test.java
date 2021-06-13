package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class Day11Test {

    private List<List<Character>> getExampleInput() {
        String[] seatsStr = new String[]{
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL",
        };

        return FormatConversion.convertStringArrayToCharListList(seatsStr);
    }

    /**
     * Test Day 11 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<List<Character>> seats = this.getExampleInput();

        Day11 d = new Day11();
        int partOneAnswer = d.solvePartOne(seats);

        Assert.assertEquals(37, partOneAnswer);
    }

    /**
     * Test Day 11 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<List<Character>> seats = ProblemLoader.loadProblemIntoXYCharList(2020, 11);
        Day11 d = new Day11();
        int partOneAnswer = d.solvePartOne(seats);

        Assert.assertEquals(2281, partOneAnswer);
    }

    /**
     * Test Day 11 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<List<Character>> seats = this.getExampleInput();

        Day11 d = new Day11();
        int partTwoAnswer = d.solvePartTwo(seats);

        Assert.assertEquals(26, partTwoAnswer);
    }


    /**
     * Test Day 11 part Two with the real input (and the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<List<Character>> seats = ProblemLoader.loadProblemIntoXYCharList(2020, 11);
        Day11 d = new Day11();
        int partTwoAnswer = d.solvePartTwo(seats);

        Assert.assertEquals(2085, partTwoAnswer);
    }


}
