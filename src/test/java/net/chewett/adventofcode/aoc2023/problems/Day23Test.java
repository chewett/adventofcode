package net.chewett.adventofcode.aoc2023.problems;

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

        input.add("#.#####################");
        input.add("#.......#########...###");
        input.add("#######.#########.#.###");
        input.add("###.....#.>.>.###.#.###");
        input.add("###v#####.#v#.###.#.###");
        input.add("###.>...#.#.#.....#...#");
        input.add("###v###.#.#.#########.#");
        input.add("###...#.#.#.......#...#");
        input.add("#####.#.#.#######.#.###");
        input.add("#.....#.#.#.......#...#");
        input.add("#.#####.#.#.#########v#");
        input.add("#.#...#...#...###...>.#");
        input.add("#.#.#v#######v###.###v#");
        input.add("#...#.>.#...>.>.#.###.#");
        input.add("#####v#.#.###v#.#.###.#");
        input.add("#.....#...#...#.#.#...#");
        input.add("#.#########.###.#.#.###");
        input.add("#...###...#...#...#.###");
        input.add("###.###.#.###v#####v###");
        input.add("#...#...#.#.>.>.#.>.###");
        input.add("#.###.###.#.###.#.#v###");
        input.add("#.....###...###...#...#");
        input.add("#####################.#");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 23 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day23 d = new Day23();
        Assertions.assertEquals(94, d.solvePartOne(input));
    }


    /**
     * Test Day 23 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(2306, d.solvePartOne(input));
    }

    /**
     * Test Day 23 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day23 d = new Day23();
        Assertions.assertEquals(154, d.solvePartTwo(input));
    }

    /**
     * Test Day 23 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(6718, d.solvePartTwo(input));
    }

}
