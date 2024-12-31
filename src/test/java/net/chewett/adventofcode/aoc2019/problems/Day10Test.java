package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day10Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add(".#..##.###...#######");
        input.add("##.############..##.");
        input.add(".#.######.########.#");
        input.add(".###.#######.####.#.");
        input.add("#####.##.#.##.###.##");
        input.add("..#####..#.#########");
        input.add("####################");
        input.add("#.####....###.#.#.##");
        input.add("##.#################");
        input.add("#####.##.###..####..");
        input.add("..######..##.#######");
        input.add("####.##.####...##..#");
        input.add(".#####..#.######.###");
        input.add("##...#.##########...");
        input.add("#.##########.#######");
        input.add(".####.#.###.###.#.##");
        input.add("....##.##.###..#####");
        input.add(".#.#.###########.###");
        input.add("#.#.#.#####.####.###");
        input.add("###.##.####.##.#..##");

        return input;
    }

    /**
     * Test Day 10 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(210, d.solvePartOne(input));
    }


    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(260, d.solvePartOne(input));
    }

    /**
     * Test Day 10 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day10 d = new Day10();
        Assertions.assertEquals(802, d.solvePartTwo(input));
    }

    /**
     * Test Day 10 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(608, d.solvePartTwo(input));
    }

}
