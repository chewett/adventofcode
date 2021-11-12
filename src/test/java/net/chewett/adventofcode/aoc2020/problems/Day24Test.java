package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day24Test {

    /**
     * Get a set of example inputs to use for testing
     * @return Set of example inputs
     */
    public List<String> getExampleInputs() {
        List<String> inputs = new ArrayList<>();

        inputs.add("sesenwnenenewseeswwswswwnenewsewsw");
        inputs.add("neeenesenwnwwswnenewnwwsewnenwseswesw");
        inputs.add("seswneswswsenwwnwse");
        inputs.add("nwnwneseeswswnenewneswwnewseswneseene");
        inputs.add("swweswneswnenwsewnwneneseenw");
        inputs.add("eesenwseswswnenwswnwnwsewwnwsene");
        inputs.add("sewnenenenesenwsewnenwwwse");
        inputs.add("wenwwweseeeweswwwnwwe");
        inputs.add("wsweesenenewnwwnwsenewsenwwsesesenwne");
        inputs.add("neeswseenwwswnwswswnw");
        inputs.add("nenwswwsewswnenenewsenwsenwnesesenew");
        inputs.add("enewnwewneswsewnwswenweswnenwsenwsw");
        inputs.add("sweneswneswneneenwnewenewwneswswnese");
        inputs.add("swwesenesewenwneswnwwneseswwne");
        inputs.add("enesenwswwswneneswsenwnewswseenwsese");
        inputs.add("wnwnesenesenenwwnenwsewesewsesesew");
        inputs.add("nenewswnwewswnenesenwnesewesw");
        inputs.add("eneswnwswnwsenenwnwnwwseeswneewsenese");
        inputs.add("neswnwewnwnwseenwseesewsenwsweewe");
        inputs.add("wseweeenwnesenwwwswnew");

        return inputs;
    }

    /**
     * Test Day 24 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> example = this.getExampleInputs();
        Day24 d = new Day24();
        Assertions.assertEquals(10, d.solvePartOne(example));
    }

    /**
     * Test Day 24 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> inputs = ProblemLoader.loadProblemIntoStringArray(2020, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(244, d.solvePartOne(inputs));
    }

    /**
     * Test Day 24 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> example = this.getExampleInputs();
        Day24 d = new Day24();
        Assertions.assertEquals(2208, d.solvePartTwo(example));
    }

    /**
     * Test Day 24 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> inputs = ProblemLoader.loadProblemIntoStringArray(2020, 24);
        Day24 d = new Day24();
        Assertions.assertEquals(3665, d.solvePartTwo(inputs));
    }

}
