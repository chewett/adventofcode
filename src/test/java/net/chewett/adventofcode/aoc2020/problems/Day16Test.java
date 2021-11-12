package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day16Test {

    /**
     * Test Day 16 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> ticketLines = new ArrayList<>();
        ticketLines.add("class: 1-3 or 5-7");
        ticketLines.add("row: 6-11 or 33-44");
        ticketLines.add("seat: 13-40 or 45-50");
        ticketLines.add("");
        ticketLines.add("your ticket:");
        ticketLines.add("7,1,14");
        ticketLines.add("");
        ticketLines.add("nearby tickets:");
        ticketLines.add("7,3,47");
        ticketLines.add("40,4,50");
        ticketLines.add("55,2,20");
        ticketLines.add("38,6,12");

        Day16 d = new Day16();

        Assertions.assertEquals(71, d.solvePartOne(ticketLines));
    }

    /**
     * Test Day 16 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> ticketLines = ProblemLoader.loadProblemIntoStringArray(2020, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(23044, d.solvePartOne(ticketLines));
    }

    /**
     * Test Day 16 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> ticketLines = new ArrayList<>();
        //Hack the example a little so two of these items are called departure and we can check a non-zero value
        ticketLines.add("departureclass: 0-1 or 4-19");
        ticketLines.add("row: 0-5 or 8-19");
        ticketLines.add("departureseat: 0-13 or 16-19");
        ticketLines.add("");
        ticketLines.add("your ticket:");
        ticketLines.add("11,12,13");
        ticketLines.add("");
        ticketLines.add("nearby tickets:");
        ticketLines.add("3,9,18");
        ticketLines.add("15,1,5");
        ticketLines.add("5,14,9");

        Day16 d = new Day16();
        Assertions.assertEquals(156, d.solvePartTwo(ticketLines));
    }

    /**
     * Test Day 16 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> ticketLines = ProblemLoader.loadProblemIntoStringArray(2020, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(3765150732757L, d.solvePartTwo(ticketLines));
    }

}
