package net.chewett.adventofcode.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple test class to make sure the Naive Travelling Salesman problem works
 */
public class NaiveTspSolverTest {

    /**
     * Test a simple case where we are looking for the shortest distance
     */
    @Test
    public void testSimpleCaseOfTspSolveForShortest() {
        List<String> example = new ArrayList<>();
        example.add("London to Dublin = 464");
        example.add("London to Belfast = 518");
        example.add("Dublin to Belfast = 141");

        NaiveTspSolver tsp = new NaiveTspSolver(example);
        Assertions.assertEquals(605, tsp.solveForShortest());
    }

    /**
     * Test a simple case where we are looking for the longest distance
     */
    @Test
    public void testSimpleCaseOfTspSolveForLongest() {
        List<String> example = new ArrayList<>();
        example.add("London to Dublin = 464");
        example.add("London to Belfast = 518");
        example.add("Dublin to Belfast = 141");

        NaiveTspSolver tsp = new NaiveTspSolver(example);
        Assertions.assertEquals(982, tsp.solveForLongest());
    }


}
