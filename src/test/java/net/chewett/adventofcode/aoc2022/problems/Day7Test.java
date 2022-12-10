package net.chewett.adventofcode.aoc2022.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day7Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> commands = new ArrayList<>();

        commands.add("$ cd /");
        commands.add("$ ls");
        commands.add("dir a");
        commands.add("14848514 b.txt");
        commands.add("8504156 c.dat");
        commands.add("dir d");
        commands.add("$ cd a");
        commands.add("$ ls");
        commands.add("dir e");
        commands.add("29116 f");
        commands.add("2557 g");
        commands.add("62596 h.lst");
        commands.add("$ cd e");
        commands.add("$ ls");
        commands.add("584 i");
        commands.add("$ cd ..");
        commands.add("$ cd ..");
        commands.add("$ cd d");
        commands.add("$ ls");
        commands.add("4060174 j");
        commands.add("8033020 d.log");
        commands.add("5626152 d.ext");
        commands.add("7214296 k");
        
        return commands;
    }

    /**
     * Test Day 7 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> commands = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(95437, d.solvePartOne(commands));
    }


    /**
     * Test Day 7 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> commands = ProblemLoader.loadProblemIntoStringArray(2022, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(1582412, d.solvePartOne(commands));
    }

    /**
     * Test Day 7 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> commands = this.getExampleInput();
        Day7 d = new Day7();
        Assertions.assertEquals(24933642, d.solvePartTwo(commands));
    }

    /**
     * Test Day 7 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> commands = ProblemLoader.loadProblemIntoStringArray(2022, 7);
        Day7 d = new Day7();
        Assertions.assertEquals(3696336, d.solvePartTwo(commands));
    }

}
