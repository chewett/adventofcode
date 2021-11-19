package net.chewett.adventofcode.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PermutationGeneratorTest {

    @Test
    public void testNoPermutations() {
        List<Integer> ints = new ArrayList<>();
        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> permutations = pg.generatePermutations(ints);
        Assertions.assertEquals(0, permutations.size());
    }

    @Test
    public void testSinglePermutation() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> permutations = pg.generatePermutations(ints);
        Assertions.assertEquals(1, permutations.size());
    }

    @Test
    public void testTwoInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> permutations = pg.generatePermutations(ints);
        Assertions.assertEquals(2, permutations.size());
    }

    @Test
    public void testThreeInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> permutations = pg.generatePermutations(ints);
        Assertions.assertEquals(6, permutations.size());
    }

    @Test
    public void testFourInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> permutations = pg.generatePermutations(ints);
        Assertions.assertEquals(24, permutations.size());
    }

    @Test
    public void testFiveInts() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> permutations = pg.generatePermutations(ints);
        Assertions.assertEquals(120, permutations.size());
    }


}
