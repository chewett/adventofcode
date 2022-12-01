package net.chewett.adventofcode.aoc2022.problems;

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
    public List<String> getExampleInput() {
        List<String> foodstuffs = new ArrayList<>();

        foodstuffs.add("1000");
        foodstuffs.add("2000");
        foodstuffs.add("3000");
        foodstuffs.add("");
        foodstuffs.add("4000");
        foodstuffs.add("");
        foodstuffs.add("5000");
        foodstuffs.add("6000");
        foodstuffs.add("");
        foodstuffs.add("7000");
        foodstuffs.add("8000");
        foodstuffs.add("9000");
        foodstuffs.add("");
        foodstuffs.add("10000");

        return foodstuffs;
    }

    /**
     * Test Day 1 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> foodstuffs = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(24000, d.solvePartOne(foodstuffs));
    }


    /**
     * Test Day 1 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> foodstuffs = ProblemLoader.loadProblemIntoStringArray(2022, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(69693, d.solvePartOne(foodstuffs));
    }

    /**
     * Test Day 1 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> foodstuffs = this.getExampleInput();
        Day1 d = new Day1();
        Assertions.assertEquals(45000, d.solvePartTwo(foodstuffs));
    }

    /**
     * Test Day 1 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> foodstuffs = ProblemLoader.loadProblemIntoStringArray(2022, 1);
        Day1 d = new Day1();
        Assertions.assertEquals(200945, d.solvePartTwo(foodstuffs));
    }

}
