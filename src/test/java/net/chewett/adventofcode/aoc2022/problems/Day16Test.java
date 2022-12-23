package net.chewett.adventofcode.aoc2022.problems;

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
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB");
        input.add("Valve BB has flow rate=13; tunnels lead to valves CC, AA");
        input.add("Valve CC has flow rate=2; tunnels lead to valves DD, BB");
        input.add("Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE");
        input.add("Valve EE has flow rate=3; tunnels lead to valves FF, DD");
        input.add("Valve FF has flow rate=0; tunnels lead to valves EE, GG");
        input.add("Valve GG has flow rate=0; tunnels lead to valves FF, HH");
        input.add("Valve HH has flow rate=22; tunnel leads to valve GG");
        input.add("Valve II has flow rate=0; tunnels lead to valves AA, JJ");
        input.add("Valve JJ has flow rate=21; tunnel leads to valve II");

        return input;
    }

    /**
     * Test Day 16 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> report = this.getExampleInput();
        Day16 d = new Day16();
        Assertions.assertEquals(1651, d.solvePartOne(report));
    }


    /**
     * Test Day 16 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(1857, d.solvePartOne(report));
    }

    /**
     * Test Day 16 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> report = this.getExampleInput();
        Day16 d = new Day16();
        Assertions.assertEquals(1707, d.solvePartTwo(report));
    }

    /**
     * Test Day 16 part two with the real input (with the correct answer)
     *
     * This takes a very long time due to it being un-optimal, this needs fixing.
     *
     */
    //@Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2022, 16);
        Day16 d = new Day16();
        Assertions.assertEquals(2536, d.solvePartTwo(report));
    }


}
