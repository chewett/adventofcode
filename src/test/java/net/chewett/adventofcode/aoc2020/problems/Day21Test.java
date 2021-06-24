package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day21Test {

    /**
     * Simple set of example data
     * @return Example data of foodstuffs, their ingredients and the allergens
     */
    private List<String> getExampleData() {
        List<String> example = new ArrayList<>();

        example.add("mxmxvkd kfcds sqjhc nhms (contains dairy, fish)");
        example.add("trh fvjkl sbzzf mxmxvkd (contains dairy)");
        example.add("sqjhc fvjkl (contains soy)");
        example.add("sqjhc mxmxvkd sbzzf (contains fish)");

        return example;

    }

    /**
     * Test Day 21 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> foodstuffList = this.getExampleData();
        Day21 d = new Day21();
        Assert.assertEquals(5, d.solvePartOne(foodstuffList));
    }

    /**
     * Test Day 21 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> foodstuffList = ProblemLoader.loadProblemIntoStringArray(2020, 21);
        Day21 d = new Day21();
        Assert.assertEquals(2075, d.solvePartOne(foodstuffList));
    }

    /**
     * Test Day 21 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> foodstuffList = this.getExampleData();
        Day21 d = new Day21();
        Assert.assertEquals("mxmxvkd,sqjhc,fvjkl", d.solvePartTwo(foodstuffList));
    }

    /**
     * Test Day 21 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> foodstuffList = ProblemLoader.loadProblemIntoStringArray(2020, 21);
        Day21 d = new Day21();
        Assert.assertEquals("zfcqk,mdtvbb,ggdbl,frpvd,mgczn,zsfzq,kdqls,kktsjbh", d.solvePartTwo(foodstuffList));
    }

}
