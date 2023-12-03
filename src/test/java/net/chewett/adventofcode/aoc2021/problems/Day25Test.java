package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
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
    public Discrete2DPositionGrid<Character> getExampleInput() {
        List<String> seaCucumbers = new ArrayList<>();

        seaCucumbers.add("v...>>.vv>");
        seaCucumbers.add(".vv>>.vv..");
        seaCucumbers.add(">>.>v>...v");
        seaCucumbers.add(">>v>>.>.v.");
        seaCucumbers.add("v>v.vv.v..");
        seaCucumbers.add(">.>>..v...");
        seaCucumbers.add(".vv..>.>v.");
        seaCucumbers.add("v.v..>>v.v");
        seaCucumbers.add("....v..v.>");

        List<List<Character>> octopiArray = FormatConversion.convertStringArrayToCharListList(seaCucumbers);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(octopiArray);
    }


    /**
     * Test Day 25 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        Day25 d = new Day25();
        Assertions.assertEquals(58, d.solvePartOne(this.getExampleInput()));
    }


    /**
     * Test Day 25 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2021, 25);
        Day25 d = new Day25();
        Assertions.assertEquals(549, d.solvePartOne(input));
    }

}
