package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day12Test {

    /**
     * Function to get the simple example inputs
     * @return The example input
     */
    public List<String> getSimpleExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("<x=-1, y=0, z=2>");
        input.add("<x=2, y=-10, z=-7>");
        input.add("<x=4, y=-8, z=8>");
        input.add("<x=3, y=5, z=-1>");

        return input;
    }

    /**
     * Function to get the complex example inputs
     * @return The example input
     */
    public List<String> getComplexExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("<x=-8, y=-10, z=0>");
        input.add("<x=5, y=5, z=10>");
        input.add("<x=2, y=-7, z=3>");
        input.add("<x=9, y=-8, z=-3>");

        return input;
    }

    /**
     * Test Day 12 part one with the simple example input (with the known result)
     */
    @Test
    public void testSimpleExampleInputPartOne() {
        List<String> input = this.getSimpleExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(183, d.solvePartOne(input));
    }

    /**
     * Test Day 12 part one with the complex example input (with the known result)
     */
    @Test
    public void testComplexExampleInputPartOne() {
        List<String> input = this.getComplexExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(14645, d.solvePartOne(input));
    }


    /**
     * Test Day 12 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(14809, d.solvePartOne(input));
    }

    /**
     * Test Day 12 part two with the simple example input (with the known result)
     *
     */
    @Test
    public void testSimpleExampleInputPartTwo() {
        List<String> input = this.getSimpleExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(2772 , d.solvePartTwo(input));
    }

    /**
     * Test Day 12 part two with the complex example input (with the known result)
     *
     */
    @Test
    public void testComplexExampleInputPartTwo() {
        List<String> input = this.getComplexExampleInput();
        Day12 d = new Day12();
        Assertions.assertEquals(4686774924L, d.solvePartTwo(input));
    }

    /**
     * Test Day 12 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(282270365571288L, d.solvePartTwo(input));
    }

}
