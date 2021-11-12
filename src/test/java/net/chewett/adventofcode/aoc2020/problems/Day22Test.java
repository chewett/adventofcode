package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day22Test {

    /**
     * Simple set of example data
     * @return An example set of cards for the match
     */
    public List<String> getExampleData() {
        List<String> cards = new ArrayList<>();
        cards.add("Player 1:");
        cards.add("9");
        cards.add("2");
        cards.add("6");
        cards.add("3");
        cards.add("1");
        cards.add("");
        cards.add("Player 2:");
        cards.add("5");
        cards.add("8");
        cards.add("4");
        cards.add("7");
        cards.add("10");

        return cards;
    }

    /**
     * Test Day 22 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> cards = this.getExampleData();
        Day22 d = new Day22();
        Assertions.assertEquals(306, d.solvePartOne(cards));
    }

    /**
     * Test Day 22 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> cards = ProblemLoader.loadProblemIntoStringArray(2020, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(32783, d.solvePartOne(cards));
    }

    /**
     * Test Day 22 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> cards = this.getExampleData();
        Day22 d = new Day22();
        Assertions.assertEquals(291, d.solvePartTwo(cards));
    }

    /**
     * Test Day 22 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> cards = ProblemLoader.loadProblemIntoStringArray(2020, 22);
        Day22 d = new Day22();
        Assertions.assertEquals(33455, d.solvePartTwo(cards));
    }

}
