package net.chewett.adventofcode.aoc2023.problems;


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

        input.add("jqt: rhn xhk nvd");
        input.add("rsh: frs pzl lsr");
        input.add("xhk: hfx");
        input.add("cmg: qnr nvd lhk bvb");
        input.add("rhn: xhk bvb hfx");
        input.add("bvb: xhk hfx");
        input.add("pzl: lsr hfx nvd");
        input.add("qnr: nvd");
        input.add("ntq: jqt hfx bvb xhk");
        input.add("nvd: lhk");
        input.add("lsr: lhk");
        input.add("rzs: qnr cmg lsr rsh");
        input.add("frs: qnr lhk lsr");

        return input;
    }

    /**
     * Test Day 25 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day25 d = new Day25();
        Assertions.assertEquals(54, d.solvePartOne(input));
    }


    /**
     * Test Day 25 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 25);
        Day25 d = new Day25();
        Assertions.assertEquals(600225, d.solvePartOne(input));
    }

}
