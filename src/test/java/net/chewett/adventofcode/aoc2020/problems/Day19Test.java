package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day19Test {

    /**
     * Test Day 19 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> rules = new ArrayList<>();

        rules.add("0: 1 2");
        rules.add("1: \"a\"");
        rules.add("2: 1 3 | 3 1");
        rules.add("3: \"b\"");
        rules.add("");
        rules.add("aab");
        rules.add("aaa");
        rules.add("bbb");
        rules.add("aba");

        Day19 d = new Day19();
        Assertions.assertEquals(2, d.solvePartOne(rules));
    }

    /**
     * Test Day 19 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        List<String> rules = new ArrayList<>();

        rules.add("0: 4 1 5");
        rules.add("1: 2 3 | 3 2");
        rules.add("2: 4 4 | 5 5");
        rules.add("3: 4 5 | 5 4");
        rules.add("4: \"a\"");
        rules.add("5: \"b\"");
        rules.add("");
        rules.add("ababbb");
        rules.add("bababa");
        rules.add("abbbab");
        rules.add("aaabbb");
        rules.add("aaaabbb");

        Day19 d = new Day19();
        Assertions.assertEquals(2, d.solvePartOne(rules));
    }

    /**
     * Test Day 19 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> rules = ProblemLoader.loadProblemIntoStringArray(2020, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(272, d.solvePartOne(rules));
    }

    /**
     * Test Day 19 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> rules = new ArrayList<>();

        rules.add("42: 9 14 | 10 1");
        rules.add("9: 14 27 | 1 26");
        rules.add("10: 23 14 | 28 1");
        rules.add("1: \"a\"");
        rules.add("11: 42 31");
        rules.add("5: 1 14 | 15 1");
        rules.add("19: 14 1 | 14 14");
        rules.add("12: 24 14 | 19 1");
        rules.add("16: 15 1 | 14 14");
        rules.add("31: 14 17 | 1 13");
        rules.add("6: 14 14 | 1 14");
        rules.add("2: 1 24 | 14 4");
        rules.add("0: 8 11");
        rules.add("13: 14 3 | 1 12");
        rules.add("15: 1 | 14");
        rules.add("17: 14 2 | 1 7");
        rules.add("23: 25 1 | 22 14");
        rules.add("28: 16 1");
        rules.add("4: 1 1");
        rules.add("20: 14 14 | 1 15");
        rules.add("3: 5 14 | 16 1");
        rules.add("27: 1 6 | 14 18");
        rules.add("14: \"b\"");
        rules.add("21: 14 1 | 1 14");
        rules.add("25: 1 1 | 1 14");
        rules.add("22: 14 14");
        rules.add("8: 42");
        rules.add("26: 14 22 | 1 20");
        rules.add("18: 15 15");
        rules.add("7: 14 5 | 1 21");
        rules.add("24: 14 1");
        rules.add("");
        rules.add("abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa");
        rules.add("bbabbbbaabaabba");
        rules.add("babbbbaabbbbbabbbbbbaabaaabaaa");
        rules.add("aaabbbbbbaaaabaababaabababbabaaabbababababaaa");
        rules.add("bbbbbbbaaaabbbbaaabbabaaa");
        rules.add("bbbababbbbaaaaaaaabbababaaababaabab");
        rules.add("ababaaaaaabaaab");
        rules.add("ababaaaaabbbaba");
        rules.add("baabbaaaabbaaaababbaababb");
        rules.add("abbbbabbbbaaaababbbbbbaaaababb");
        rules.add("aaaaabbaabaaaaababaa");
        rules.add("aaaabbaaaabbaaa");
        rules.add("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa");
        rules.add("babaaabbbaaabaababbaabababaaab");
        rules.add("aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba");

        Day19 d = new Day19();
        Assertions.assertEquals(12, d.solvePartTwo(rules));

    }

    /**
     * Test Day 19 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> rules = ProblemLoader.loadProblemIntoStringArray(2020, 19);
        Day19 d = new Day19();
        Assertions.assertEquals(374, d.solvePartTwo(rules));
    }

}
