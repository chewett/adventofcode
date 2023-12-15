package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day12Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("???.### 1,1,3");
        input.add(".??..??...?##. 1,1,3");
        input.add("?#?#?#?#?#?#?#? 1,3,1,6");
        input.add("????.#...#... 4,1,1");
        input.add("????.######..#####. 1,6,5");
        input.add("?###???????? 3,2,1");

        return input;
    }

    /**
     * Test Day 12 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(21, d.solvePartOne(input));
    }


    /**
     * Test Day 12 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(8075, d.solvePartOne(input));
    }

    /**
     * Test Day 12 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(525152, d.solvePartTwo(input));
    }

    /**
     * Test Day 12 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(4232520187524L, d.solvePartTwo(input));
    }

}
