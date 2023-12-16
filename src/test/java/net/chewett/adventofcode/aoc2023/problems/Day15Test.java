package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day15Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public String getExampleInput() {
        return "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";
    }

    /**
     * Test Day 15 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        String input = this.getExampleInput();
        Day15 d = new Day15();
        Assertions.assertEquals(1320, d.solvePartOne(input));
    }


    /**
     * Test Day 15 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        String input = ProblemLoader.loadProblemIntoString(2023, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(504036, d.solvePartOne(input));
    }

    /**
     * Test Day 15 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        String input = this.getExampleInput();
        Day15 d = new Day15();
        Assertions.assertEquals(145, d.solvePartTwo(input));
    }

    /**
     * Test Day 15 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        String input = ProblemLoader.loadProblemIntoString(2023, 15);
        Day15 d = new Day15();
        Assertions.assertEquals(295719, d.solvePartTwo(input));
    }

}
