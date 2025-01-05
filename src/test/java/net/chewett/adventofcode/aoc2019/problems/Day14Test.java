package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day14Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();

        input.add("10 ORE => 10 A");
        input.add("1 ORE => 1 B");
        input.add("7 A, 1 B => 1 C");
        input.add("7 A, 1 C => 1 D");
        input.add("7 A, 1 D => 1 E");
        input.add("7 A, 1 E => 1 FUEL");

        return input;
    }

    public List<String> getExampleInputTwo() {
        List<String> input = new ArrayList<>();

        input.add("171 ORE => 8 CNZTR");
        input.add("7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL");
        input.add("114 ORE => 4 BHXH");
        input.add("14 VRPVC => 6 BMBT");
        input.add("6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL");
        input.add("6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT");
        input.add("15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW");
        input.add("13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW");
        input.add("5 BMBT => 4 WPTQ");
        input.add("189 ORE => 9 KTJDG");
        input.add("1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP");
        input.add("12 VRPVC, 27 CNZTR => 2 XDBXC");
        input.add("15 KTJDG, 12 BHXH => 5 XCVML");
        input.add("3 BHXH, 2 VRPVC => 7 MZWV");
        input.add("121 ORE => 7 VRPVC");
        input.add("7 XCVML => 6 RJRHP");
        input.add("5 BHXH, 4 VRPVC => 5 LTCX");

        return input;
    }

    /**
     * Test Day 14 part one with the example input one (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(31 , d.solvePartOne(input));
    }

    /**
     * Test Day 14 part one with the example input one (with the known result)
     */
    @Test
    public void testExampleInputTwoPartOne() {
        List<String> input = this.getExampleInputTwo();
        Day14 d = new Day14();
        Assertions.assertEquals(2210736, d.solvePartOne(input));
    }


    /**
     * Test Day 14 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(337862, d.solvePartOne(input));
    }

    /**
     * Test Day 14 part two with the example input two (with the known result)
     *
     * FIXME: This takes forever due to hardcoded inputs, need a smarter way of doing this
     */
    //@Test
    public void testExampleInputTwoPartTwo() {
        List<String> input = this.getExampleInput();
        Day14 d = new Day14();
        Assertions.assertEquals(460664, d.solvePartTwo(input));
    }

    /**
     * Test Day 14 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2019, 14);
        Day14 d = new Day14();
        Assertions.assertEquals(3687786, d.solvePartTwo(input));
    }

}
