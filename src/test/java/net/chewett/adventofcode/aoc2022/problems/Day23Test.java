package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day23Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("....#..");
        input.add("..###.#");
        input.add("#...#.#");
        input.add(".#...##");
        input.add("#.###..");
        input.add("##.#.##");
        input.add(".#..#..");

        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
            FormatConversion.convertStringArrayToCharListList(input)
        );
    }

    /**
     * Test Day 23 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> blueprints = this.getExampleInput();
        Day23 d = new Day23();
        Assertions.assertEquals(110, d.solvePartOne(blueprints));
    }


    /**
     * Test Day 23 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> blueprints = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2022, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(4000, d.solvePartOne(blueprints));
    }

    /**
     * Test Day 23 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> blueprints = this.getExampleInput();
        Day23 d = new Day23();
        Assertions.assertEquals(20, d.solvePartTwo(blueprints));
    }

    /**
     * Test Day 23 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> blueprints = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2022, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(1040, d.solvePartTwo(blueprints));
    }


}
