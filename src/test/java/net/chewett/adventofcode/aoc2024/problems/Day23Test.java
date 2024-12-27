package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day23Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("kh-tc");
        input.add("qp-kh");
        input.add("de-cg");
        input.add("ka-co");
        input.add("yn-aq");
        input.add("qp-ub");
        input.add("cg-tb");
        input.add("vc-aq");
        input.add("tb-ka");
        input.add("wh-tc");
        input.add("yn-cg");
        input.add("kh-ub");
        input.add("ta-co");
        input.add("de-co");
        input.add("tc-td");
        input.add("tb-wq");
        input.add("wh-td");
        input.add("ta-ka");
        input.add("td-qp");
        input.add("aq-cg");
        input.add("wq-ub");
        input.add("ub-vc");
        input.add("de-ta");
        input.add("wq-aq");
        input.add("wq-vc");
        input.add("wh-yn");
        input.add("ka-de");
        input.add("kh-ta");
        input.add("co-tc");
        input.add("wh-qp");
        input.add("tb-vc");
        input.add("td-yn");

        return input;
    }

    /**
     * Test Day 23 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day23 d = new Day23();
        Assertions.assertEquals(7, d.solvePartOne(input));
    }


    /**
     * Test Day 23 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 23);
        Day23 d = new Day23();
        Assertions.assertEquals(1230, d.solvePartOne(input));
    }

    /**
     * Test Day 23 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day23 d = new Day23();
        Assertions.assertEquals("co,de,ka,ta", d.solvePartTwo(input));
    }

    /**
     * Test Day 23 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 23);
        Day23 d = new Day23();
        Assertions.assertEquals("az,cj,kp,lm,lt,nj,rf,rx,sn,ty,ui,wp,zo", d.solvePartTwo(input));
    }

}
