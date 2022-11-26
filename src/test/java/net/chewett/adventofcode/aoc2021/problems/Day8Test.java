package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day8Test {

    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> exampleInput = new ArrayList<>();
        
        exampleInput.add("be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe");
        exampleInput.add("edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc");
        exampleInput.add("fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg");
        exampleInput.add("fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb");
        exampleInput.add("aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea");
        exampleInput.add("fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb");
        exampleInput.add("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe");
        exampleInput.add("bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef");
        exampleInput.add("egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb");
        exampleInput.add("gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce");
        
        return exampleInput;
    }

    /**
     * Test Day 8 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> segments = this.getExampleInput();
        Day8 d = new Day8();
        Assertions.assertEquals(26, d.solvePartOne(segments));
    }


    /**
     * Test Day 8 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> segments = ProblemLoader.loadProblemIntoStringArray(2021, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(294, d.solvePartOne(segments));
    }

    /**
     * Test Day 8 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> segments = this.getExampleInput();
        Day8 d = new Day8();
        Assertions.assertEquals(61229, d.solvePartTwo(segments));
    }

    /**
     * Test Day 8 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> segments = ProblemLoader.loadProblemIntoStringArray(2021, 8);
        Day8 d = new Day8();
        Assertions.assertEquals(973292, d.solvePartTwo(segments));
    }

}
