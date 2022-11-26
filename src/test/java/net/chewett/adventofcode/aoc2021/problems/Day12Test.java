package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day12Test {


    /**
     * Function to get the example 1 inputs
     * @return The example input 1
     */
    public List<String> getExampleInput1() {
        List<String> paths = new ArrayList<>();
        paths.add("start-A");
        paths.add("start-b");
        paths.add("A-c");
        paths.add("A-b");
        paths.add("b-d");
        paths.add("A-end");
        paths.add("b-end");
        return paths;
    }

    /**
     * Function to get the example 2 inputs
     * @return The example input 2
     */
    public List<String> getExampleInput2() {
        List<String> paths = new ArrayList<>();
        paths.add("dc-end");
        paths.add("HN-start");
        paths.add("start-kj");
        paths.add("dc-start");
        paths.add("dc-HN");
        paths.add("LN-dc");
        paths.add("HN-end");
        paths.add("kj-sa");
        paths.add("kj-HN");
        paths.add("kj-dc");
        return paths;
    }

    /**
     * Function to get the example 3 inputs
     * @return The example input 3
     */
    public List<String> getExampleInput3() {
        List<String> paths = new ArrayList<>();
        paths.add("fs-end");
        paths.add("he-DX");
        paths.add("fs-he");
        paths.add("start-DX");
        paths.add("pj-DX");
        paths.add("end-zg");
        paths.add("zg-sl");
        paths.add("zg-pj");
        paths.add("pj-he");
        paths.add("RW-he");
        paths.add("fs-DX");
        paths.add("pj-RW");
        paths.add("zg-RW");
        paths.add("start-pj");
        paths.add("he-WI");
        paths.add("zg-he");
        paths.add("pj-fs");
        paths.add("start-RW");
        return paths;
    }


    /**
     * Test Day 12 part one with the example input 1 (with the known result)
     */
    @Test
    public void testExampleInput1PartOne() {
        List<String> paths = this.getExampleInput1();
        Day12 d = new Day12();
        Assertions.assertEquals(10, d.solvePartOne(paths));
    }

    /**
     * Test Day 12 part one with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartOne() {
        List<String> paths = this.getExampleInput2();
        Day12 d = new Day12();
        Assertions.assertEquals(19, d.solvePartOne(paths));
    }

    /**
     * Test Day 12 part one with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartOne() {
        List<String> paths = this.getExampleInput3();
        Day12 d = new Day12();
        Assertions.assertEquals(226, d.solvePartOne(paths));
    }


    /**
     * Test Day 12 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(5958, d.solvePartOne(report));
    }

    /**
     * Test Day 12 part two with the example input 1 (with the known result)
     */
    @Test
    public void testExampleInput1PartTwo() {
        List<String> report = this.getExampleInput1();
        Day12 d = new Day12();
        Assertions.assertEquals(36, d.solvePartTwo(report));
    }

    /**
     * Test Day 12 part two with the example input 2 (with the known result)
     */
    @Test
    public void testExampleInput2PartTwo() {
        List<String> report = this.getExampleInput2();
        Day12 d = new Day12();
        Assertions.assertEquals(103, d.solvePartTwo(report));
    }

    /**
     * Test Day 12 part two with the example input 3 (with the known result)
     */
    @Test
    public void testExampleInput3PartTwo() {
        List<String> report = this.getExampleInput3();
        Day12 d = new Day12();
        Assertions.assertEquals(3509, d.solvePartTwo(report));
    }

    /**
     * Test Day 12 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> report = ProblemLoader.loadProblemIntoStringArray(2021, 12);
        Day12 d = new Day12();
        Assertions.assertEquals(150426, d.solvePartTwo(report));
    }


}
