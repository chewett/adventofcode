package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day3Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> engineSchematic = new ArrayList<>();

        engineSchematic.add("467..34..");
        engineSchematic.add("...*......");
        engineSchematic.add("..35..633.");
        engineSchematic.add("......#...");
        engineSchematic.add("617*......");
        engineSchematic.add(".....+.58.");
        engineSchematic.add("..592.....");
        engineSchematic.add("......755.");
        engineSchematic.add("...$.*....");
        engineSchematic.add(".664.598..");

        List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(engineSchematic);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);
    }

    /**
     * Test Day 3 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Discrete2DPositionGrid<Character> engineSchematic = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(4361, d.solvePartOne(engineSchematic));
    }


    /**
     * Test Day 3 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> engineSchematic = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2023, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(539637, d.solvePartOne(engineSchematic));
    }

    /**
     * Test Day 3 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        Discrete2DPositionGrid<Character> engineSchematic = this.getExampleInput();
        Day3 d = new Day3();
        Assertions.assertEquals(467835, d.solvePartTwo(engineSchematic));
    }

    /**
     * Test Day 3 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Discrete2DPositionGrid<Character> engineSchematic = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2023, 3);
        Day3 d = new Day3();
        Assertions.assertEquals(82818007, d.solvePartTwo(engineSchematic));
    }

}
