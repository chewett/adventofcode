package net.chewett.adventofcode.aoc2022.problems;

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
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("1=-0-2");
        input.add("12111");
        input.add("2=0=");
        input.add("21");
        input.add("2=01");
        input.add("111");
        input.add("20012");
        input.add("112");
        input.add("1=-1=");
        input.add("1-12");
        input.add("12");
        input.add("1=");
        input.add("122");

        return input;
    }

    /**
     * Test Day 25 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> blueprints = this.getExampleInput();
        Day25 d = new Day25();
        Assertions.assertEquals("2=-1=0", d.solvePartOne(blueprints));
    }


    /**
     * Test Day 25 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 25);
        Day25 d = new Day25();
        Assertions.assertEquals("122-2=200-0111--=200", d.solvePartOne(blueprints));
    }



}
