package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day25Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("#####");
        input.add(".####");
        input.add(".####");
        input.add(".####");
        input.add(".#.#.");
        input.add(".#...");
        input.add(".....");
        input.add("");
        input.add("#####");
        input.add("##.##");
        input.add(".#.##");
        input.add("...##");
        input.add("...#.");
        input.add("...#.");
        input.add(".....");
        input.add("");
        input.add(".....");
        input.add("#....");
        input.add("#....");
        input.add("#...#");
        input.add("#.#.#");
        input.add("#.###");
        input.add("#####");
        input.add("");
        input.add(".....");
        input.add(".....");
        input.add("#.#..");
        input.add("###..");
        input.add("###.#");
        input.add("###.#");
        input.add("#####");
        input.add("");
        input.add(".....");
        input.add(".....");
        input.add(".....");
        input.add("#....");
        input.add("#.#..");
        input.add("#.#.#");
        input.add("#####");

        return input;
    }

    /**
     * Test Day 25 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day25 d = new Day25();
        Assertions.assertEquals(3, d.solvePartOne(input));
    }


    /**
     * Test Day 25 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 25);
        Day25 d = new Day25();
        Assertions.assertEquals(3136, d.solvePartOne(input));
    }

}
