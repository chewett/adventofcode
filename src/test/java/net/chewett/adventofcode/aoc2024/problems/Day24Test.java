package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day24Test {

    /**
     * Function to get the example inputs for part one
     * @return The example input
     */
    public List<String> getExampleInputForPartOne() {
        List<String> input = new ArrayList<>();

        input.add("x00: 1");
        input.add("x01: 0");
        input.add("x02: 1");
        input.add("x03: 1");
        input.add("x04: 0");
        input.add("y00: 1");
        input.add("y01: 1");
        input.add("y02: 1");
        input.add("y03: 1");
        input.add("y04: 1");
        input.add("");
        input.add("ntg XOR fgs -> mjb");
        input.add("y02 OR x01 -> tnw");
        input.add("kwq OR kpj -> z05");
        input.add("x00 OR x03 -> fst");
        input.add("tgd XOR rvg -> z01");
        input.add("vdt OR tnw -> bfw");
        input.add("bfw AND frj -> z10");
        input.add("ffh OR nrd -> bqk");
        input.add("y00 AND y03 -> djm");
        input.add("y03 OR y00 -> psh");
        input.add("bqk OR frj -> z08");
        input.add("tnw OR fst -> frj");
        input.add("gnj AND tgd -> z11");
        input.add("bfw XOR mjb -> z00");
        input.add("x03 OR x00 -> vdt");
        input.add("gnj AND wpb -> z02");
        input.add("x04 AND y00 -> kjc");
        input.add("djm OR pbm -> qhw");
        input.add("nrd AND vdt -> hwm");
        input.add("kjc AND fst -> rvg");
        input.add("y04 OR y02 -> fgs");
        input.add("y01 AND x02 -> pbm");
        input.add("ntg OR kjc -> kwq");
        input.add("psh XOR fgs -> tgd");
        input.add("qhw XOR tgd -> z09");
        input.add("pbm OR djm -> kpj");
        input.add("x03 XOR y03 -> ffh");
        input.add("x00 XOR y04 -> ntg");
        input.add("bfw OR bqk -> z06");
        input.add("nrd XOR fgs -> wpb");
        input.add("frj XOR qhw -> z04");
        input.add("bqk OR frj -> z07");
        input.add("y03 OR x01 -> nrd");
        input.add("hwm AND bqk -> z03");
        input.add("tgd XOR rvg -> z12");
        input.add("tnw OR pbm -> gnj");

        return input;
    }

    /**
     * Test Day 24 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> input = this.getExampleInputForPartOne();
        Day24 d = new Day24();
        Assertions.assertEquals(2024, d.solvePartOne(input));
    }


    /**
     * Test Day 24 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(58367545758258L, d.solvePartOne(input));
    }

    /**
     * Test Day 24 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 24);
        Day24 d = new Day24();
        Assertions.assertEquals("bpf,fdw,hcc,hqc,qcw,z05,z11,z35", d.solvePartTwo(input));
    }

}
