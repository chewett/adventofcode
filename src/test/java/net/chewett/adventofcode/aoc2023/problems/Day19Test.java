package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day19Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("px{a<2006:qkq,m>2090:A,rfg}");
        input.add("pv{a>1716:R,A}");
        input.add("lnx{m>1548:A,A}");
        input.add("rfg{s<537:gd,x>2440:R,A}");
        input.add("qs{s>3448:A,lnx}");
        input.add("qkq{x<1416:A,crn}");
        input.add("crn{x>2662:A,R}");
        input.add("in{s<1351:px,qqz}");
        input.add("qqz{s>2770:qs,m<1801:hdj,R}");
        input.add("gd{a>3333:R,R}");
        input.add("hdj{m>838:A,pv}");
        input.add("");
        input.add("{x=787,m=2655,a=1222,s=2876}");
        input.add("{x=1679,m=44,a=2067,s=496}");
        input.add("{x=2036,m=264,a=79,s=2244}");
        input.add("{x=2461,m=1339,a=466,s=291}");
        input.add("{x=2127,m=1623,a=2188,s=1013}");

        return input;
    }

    /**
     * Test Day 19 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day19 d = new Day19();
        Assertions.assertEquals(19114, d.solvePartOne(input));
    }


    /**
     * Test Day 19 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(263678, d.solvePartOne(input));
    }

    /**
     * Test Day 19 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> input = this.getExampleInput();
        Day19 d = new Day19();
        Assertions.assertEquals(167409079868000L, d.solvePartTwo(input));
    }

    /**
     * Test Day 19 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(125455345557345L, d.solvePartTwo(input));
    }

}
