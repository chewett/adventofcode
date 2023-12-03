package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day24Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("#.######");
        input.add("#>>.<^<#");
        input.add("#.<..<<#");
        input.add("#>v.><>#");
        input.add("#<^v^^>#");
        input.add("######.#");

        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
            FormatConversion.convertStringArrayToCharListList(input)
        );
    }

    /**
     * Test Day 24 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day24 d = new Day24();
        Assertions.assertEquals(18, d.solvePartOne(input));
    }


    /**
     * Test Day 24 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2022, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(301, d.solvePartOne(input));
    }

    /**
     * Test Day 24 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> input = this.getExampleInput();
        Day24 d = new Day24();
        Assertions.assertEquals(54, d.solvePartTwo(input));
    }

    /**
     * Test Day 24 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2022, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(859, d.solvePartTwo(input));
    }


}
