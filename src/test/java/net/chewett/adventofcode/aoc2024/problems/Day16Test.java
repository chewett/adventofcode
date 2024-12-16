package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day16Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("#################");
        input.add("#...#...#...#..E#");
        input.add("#.#.#.#.#.#.#.#.#");
        input.add("#.#.#.#...#...#.#");
        input.add("#.#.#.#.###.#.#.#");
        input.add("#...#.#.#.....#.#");
        input.add("#.#.#.#.#.#####.#");
        input.add("#.#...#.#.#.....#");
        input.add("#.#.#####.#.###.#");
        input.add("#.#.#.......#...#");
        input.add("#.#.###.#####.###");
        input.add("#.#.#...#.....#.#");
        input.add("#.#.#.#####.###.#");
        input.add("#.#.#.........#.#");
        input.add("#.#.#.#########.#");
        input.add("#S#.............#");
        input.add("#################");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 16 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day16 d = new Day16();
        Assertions.assertEquals(11048, d.solvePartOne(input));
    }


    /**
     * Test Day 16 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(95444, d.solvePartOne(input));
    }

    /**
     * Test Day 16 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day16 d = new Day16();
        Assertions.assertEquals(64, d.solvePartTwo(input));
    }

    /**
     * Test Day 16 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(513, d.solvePartTwo(input));
    }

}
