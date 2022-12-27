package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day21Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("root: pppw + sjmn");
        input.add("dbpl: 5");
        input.add("cczh: sllz + lgvd");
        input.add("zczc: 2");
        input.add("ptdq: humn - dvpt");
        input.add("dvpt: 3");
        input.add("lfqf: 4");
        input.add("humn: 5");
        input.add("ljgn: 2");
        input.add("sjmn: drzm * dbpl");
        input.add("sllz: 4");
        input.add("pppw: cczh / lfqf");
        input.add("lgvd: ljgn * ptdq");
        input.add("drzm: hmdt - zczc");
        input.add("hmdt: 32");

        return input;
    }

    /**
     * Test Day 21 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> blueprints = this.getExampleInput();
        Day21 d = new Day21();
        Assertions.assertEquals(152, d.solvePartOne(blueprints));
    }


    /**
     * Test Day 21 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 21);
        Day21 d = new Day21();
        Assertions.assertEquals(84244467642604L, d.solvePartOne(blueprints));
    }

    /**
     * Test Day 21 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> blueprints = this.getExampleInput();
        Day21 d = new Day21();
        Assertions.assertEquals(301, d.solvePartTwo(blueprints));
    }

    /**
     * Test Day 21 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> blueprints = ProblemLoader.loadProblemIntoStringArray(2022, 21);
        Day21 d = new Day21();
        Assertions.assertEquals(3759569926192L, d.solvePartTwo(blueprints));
    }


}
