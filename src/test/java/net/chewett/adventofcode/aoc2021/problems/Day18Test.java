package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day18Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> input = new ArrayList<>();
        
        input.add("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]");
        input.add("[[[5,[2,8]],4],[5,[[9,9],0]]]");
        input.add("[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]");
        input.add("[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]");
        input.add("[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]");
        input.add("[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]");
        input.add("[[[[5,4],[7,7]],8],[[8,3],8]]");
        input.add("[[9,3],[[9,9],[6,[4,9]]]]");
        input.add("[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]");
        input.add("[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]");

        return input;
    }

    /**
     * Test Day 18 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> sums = this.getExampleInput();
        Day18 d = new Day18();
        Assertions.assertEquals(4140, d.solvePartOne(sums));
    }


    /**
     * Test Day 18 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> sums = ProblemLoader.loadProblemIntoStringArray(2021, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(3806, d.solvePartOne(sums));
    }

    /**
     * Test Day 18 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> sums = this.getExampleInput();
        Day18 d = new Day18();
        Assertions.assertEquals(3993, d.solvePartTwo(sums));
    }

    /**
     * Test Day 18 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> sums = ProblemLoader.loadProblemIntoStringArray(2021, 18);
        Day18 d = new Day18();
        Assertions.assertEquals(4727, d.solvePartTwo(sums));
    }
    
}
