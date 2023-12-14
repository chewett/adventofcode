package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day10Test {

    /**
     * Function to get the example input for part One
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInputPartOne() {
        List<String> input = new ArrayList<>();

        input.add("..F7.");
        input.add(".FJ|.");
        input.add("SJ.L7");
        input.add("|F--J");
        input.add("LJ...");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 10 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInputPartOne();
        Day10 d = new Day10();
        Assertions.assertEquals(8, d.solvePartOne(input));
    }


    /**
     * Test Day 10 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(7173, d.solvePartOne(input));
    }

    /**
     * Function to get the example input for part two
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInputPartTwo() {
        List<String> input = new ArrayList<>();

        input.add("FF7FSF7F7F7F7F7F---7");
        input.add("L|LJ||||||||||||F--J");
        input.add("FL-7LJLJ||||||LJL-77");
        input.add("F--JF--7||LJLJIF7FJ-");
        input.add("L---JF-JLJIIIIFJLJJ7");
        input.add("|F|F-JF---7IIIL7L|7|");
        input.add("|FFJF7L7F-JF7IIL---7");
        input.add("7-L-JL7||F7|L7F-7F7|");
        input.add("L.L7LFJ|||||FJL7||LJ");
        input.add("L7JLJL-JLJLJL--JLJ.L");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }


    /**
     * Test Day 10 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInputPartTwo();
        Day10 d = new Day10();
        Assertions.assertEquals(10, d.solvePartTwo(input));
    }

    /**
     * Test Day 10 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 10);
        Day10 d = new Day10();
        Assertions.assertEquals(291, d.solvePartTwo(input));
    }

}
