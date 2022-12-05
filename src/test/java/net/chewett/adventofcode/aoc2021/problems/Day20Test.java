package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day20Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> str = new ArrayList<>();
        str.add("..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
                "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
                ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
                ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
                ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
                "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
                "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#");
        str.add("");
        str.add("#..#.");
        str.add("#....");
        str.add("##..#");
        str.add("..#..");
        str.add("..###");

        return str;
    }


    /**
     * Test Day 20 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> instructions = this.getExampleInput();
        Day20 d = new Day20();
        Assertions.assertEquals(35, d.solvePartOne(instructions));
    }

    /**
     * Test Day 20 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 20);
        Day20 d = new Day20();
        Assertions.assertEquals(5057, d.solvePartOne(instructions));
    }

    /**
     * Test Day 20 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> instructions = this.getExampleInput();
        Day20 d = new Day20();
        Assertions.assertEquals(3351, d.solvePartTwo(instructions));
    }

    /**
     * Test Day 20 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 20);
        Day20 d = new Day20();
        Assertions.assertEquals(18502, d.solvePartTwo(instructions));
    }

}
