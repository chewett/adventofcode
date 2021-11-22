package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import net.chewett.adventofcode.helpers.NaiveTspSolver;
import java.util.List;

/**
 * --- Day 9: All in a Single Night ---
 * Every year, Santa manages to deliver all of his presents in a single night.
 *
 * This year, however, he has some new locations to visit; his elves have provided him the distances between every pair
 * of locations. He can start and end at any two (different) locations he wants, but he must visit each location exactly
 * once. What is the shortest distance he can travel to achieve this?
 *
 * For example, given the following distances:
 *
 * London to Dublin = 464
 * London to Belfast = 518
 * Dublin to Belfast = 141
 * The possible routes are therefore:
 *
 * Dublin -> London -> Belfast = 982
 * London -> Dublin -> Belfast = 605
 * London -> Belfast -> Dublin = 659
 * Dublin -> Belfast -> London = 659
 * Belfast -> Dublin -> London = 605
 * Belfast -> London -> Dublin = 982
 * The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this example.
 *
 * What is the distance of the shortest route?
 *
 * Your puzzle answer was 141.
 *
 * --- Part Two ---
 * The next year, just to show off, Santa decides to take the route with the longest distance instead.
 *
 * He can still start and end at any two (different) locations he wants, and he still must visit each location exactly
 * once.
 *
 * For example, given the distances above, the longest route would be 982 via (for example) Dublin -> London -> Belfast.
 *
 * What is the distance of the longest route?
 */
public class Day9 {

    /**
     * Given a list of distances between two locations this will attempt to find the shortest path between them all
     * @param distances List of distances between two locations
     * @return The shortest path between all the locations
     */
    public int solvePartOne(List<String> distances) {
        NaiveTspSolver tsp = new NaiveTspSolver(distances);
        return tsp.solveForShortest();
    }

    /**
     * Given a list of distances between two locations this will attempt to find the longest path between them all
     * @param directions List of distances between two locations
     * @return The longest path between all locations
     */
    public int solvePartTwo(List<String> directions) {
        NaiveTspSolver tsp = new NaiveTspSolver(directions);
        return tsp.solveForLongest();
    }

    public static void main(String[] args) {
        List<String> distances = ProblemLoader.loadProblemIntoStringArray(2015, 9);
        Day9 d = new Day9();
        int minDistance = d.solvePartOne(distances);
        System.out.println("The shortest distance Santa could travel is " + minDistance);
        int maxDistance = d.solvePartTwo(distances);
        System.out.println("The longest distance Santa could travel is " + maxDistance);
    }


}
