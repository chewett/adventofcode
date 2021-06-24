package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day20Test {

    /**
     * Simple set of example data
     * @return Example data of nine tiles which cam be used to check the solution is working as expected
     */
    private List<String> getExampleData() {
        List<String> example = new ArrayList<>();

        example.add("Tile 2311:");
        example.add("..##.#..#.");
        example.add("##..#.....");
        example.add("#...##..#.");
        example.add("####.#...#");
        example.add("##.##.###.");
        example.add("##...#.###");
        example.add(".#.#.#..##");
        example.add("..#....#..");
        example.add("###...#.#.");
        example.add("..###..###");
        example.add("");
        example.add("Tile 1951:");
        example.add("#.##...##.");
        example.add("#.####...#");
        example.add(".....#..##");
        example.add("#...######");
        example.add(".##.#....#");
        example.add(".###.#####");
        example.add("###.##.##.");
        example.add(".###....#.");
        example.add("..#.#..#.#");
        example.add("#...##.#..");
        example.add("");
        example.add("Tile 1171:");
        example.add("####...##.");
        example.add("#..##.#..#");
        example.add("##.#..#.#.");
        example.add(".###.####.");
        example.add("..###.####");
        example.add(".##....##.");
        example.add(".#...####.");
        example.add("#.##.####.");
        example.add("####..#...");
        example.add(".....##...");
        example.add("");
        example.add("Tile 1427:");
        example.add("###.##.#..");
        example.add(".#..#.##..");
        example.add(".#.##.#..#");
        example.add("#.#.#.##.#");
        example.add("....#...##");
        example.add("...##..##.");
        example.add("...#.#####");
        example.add(".#.####.#.");
        example.add("..#..###.#");
        example.add("..##.#..#.");
        example.add("");
        example.add("Tile 1489:");
        example.add("##.#.#....");
        example.add("..##...#..");
        example.add(".##..##...");
        example.add("..#...#...");
        example.add("#####...#.");
        example.add("#..#.#.#.#");
        example.add("...#.#.#..");
        example.add("##.#...##.");
        example.add("..##.##.##");
        example.add("###.##.#..");
        example.add("");
        example.add("Tile 2473:");
        example.add("#....####.");
        example.add("#..#.##...");
        example.add("#.##..#...");
        example.add("######.#.#");
        example.add(".#...#.#.#");
        example.add(".#########");
        example.add(".###.#..#.");
        example.add("########.#");
        example.add("##...##.#.");
        example.add("..###.#.#.");
        example.add("");
        example.add("Tile 2971:");
        example.add("..#.#....#");
        example.add("#...###...");
        example.add("#.#.###...");
        example.add("##.##..#..");
        example.add(".#####..##");
        example.add(".#..####.#");
        example.add("#..#.#..#.");
        example.add("..####.###");
        example.add("..#.#.###.");
        example.add("...#.#.#.#");
        example.add("");
        example.add("Tile 2729:");
        example.add("...#.#.#.#");
        example.add("####.#....");
        example.add("..#.#.....");
        example.add("....#..#.#");
        example.add(".##..##.#.");
        example.add(".#.####...");
        example.add("####.#.#..");
        example.add("##.####...");
        example.add("##..#.##..");
        example.add("#.##...##.");
        example.add("");
        example.add("Tile 3079:");
        example.add("#.#.#####.");
        example.add(".#..######");
        example.add("..#.......");
        example.add("######....");
        example.add("####.#..#.");
        example.add(".#...#.##.");
        example.add("#.#####.##");
        example.add("..#.###...");
        example.add("..#.......");
        example.add("..#.###...");

        return example;

    }

    /**
     * Test Day 20 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> tileData = this.getExampleData();
        Day20 d = new Day20();
        Assert.assertEquals(20899048083289L, d.solvePartOne(tileData));
    }

    /**
     * Test Day 20 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> tileData = ProblemLoader.loadProblemIntoStringArray(2020, 20);
        Day20 d = new Day20();
        Assert.assertEquals(27798062994017L, d.solvePartOne(tileData));
    }

    /**
     * Test Day 20 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> tileData = this.getExampleData();
        Day20 d = new Day20();
        Assert.assertEquals(273, d.solvePartTwo(tileData));
    }

    /**
     * Test Day 20 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> tileData = ProblemLoader.loadProblemIntoStringArray(2020, 20);
        Day20 d = new Day20();
        Assert.assertEquals(2366, d.solvePartTwo(tileData));
    }

}
