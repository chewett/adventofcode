package net.chewett.adventofcode2019;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PermutationGeneratorTest {

    @Test
    public void testNoPermutations() {
        List<Integer> ints = new ArrayList<>();
        List<List<Integer>> permutations = PermutationGenerator.generatePermutations(ints);
        Assert.assertEquals(0, permutations.size());
    }

    @Test
    public void testSinglePermutation() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        List<List<Integer>> permutations = PermutationGenerator.generatePermutations(ints);
        Assert.assertEquals(1, permutations.size());
    }

    @Test
    public void testTwoInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        List<List<Integer>> permutations = PermutationGenerator.generatePermutations(ints);
        Assert.assertEquals(2, permutations.size());
    }

    @Test
    public void testThreeInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        List<List<Integer>> permutations = PermutationGenerator.generatePermutations(ints);
        Assert.assertEquals(6, permutations.size());
    }

    @Test
    public void testFourInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        List<List<Integer>> permutations = PermutationGenerator.generatePermutations(ints);
        Assert.assertEquals(24, permutations.size());
    }

    @Test
    public void testFiveInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        List<List<Integer>> permutations = PermutationGenerator.generatePermutations(ints);
        Assert.assertEquals(120, permutations.size());
    }


}
