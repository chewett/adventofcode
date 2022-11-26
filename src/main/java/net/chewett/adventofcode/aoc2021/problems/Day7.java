package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 7: The Treachery of Whales ---
 * A giant whale has decided your submarine is its next meal, and it's much faster than you are. There's nowhere to run!
 *
 * Suddenly, a swarm of crabs (each in its own tiny submarine - it's too deep for them otherwise) zooms in to rescue
 * you! They seem to be preparing to blast a hole in the ocean floor; sensors indicate a massive underground cave
 * system just beyond where they're aiming!
 *
 * The crab submarines all need to be aligned before they'll have enough power to blast a large enough hole for your
 * submarine to get through. However, it doesn't look like they'll be aligned before the whale catches you! Maybe you
 * can help?
 *
 * There's one major catch - crab submarines can only move horizontally.
 *
 * You quickly make a list of the horizontal position of each crab (your puzzle input). Crab submarines have limited
 * fuel, so you need to find a way to make all of their horizontal positions match while requiring them to spend as
 * little fuel as possible.
 *
 * For example, consider the following horizontal positions:
 *
 * 16,1,2,0,4,2,7,1,2,14
 * This means there's a crab with horizontal position 16, a crab with horizontal position 1, and so on.
 *
 * Each change of 1 step in horizontal position of a single crab costs 1 fuel. You could choose any horizontal position
 * to align them all on, but the one that costs the least fuel is horizontal position 2:
 *
 * Move from 16 to 2: 14 fuel
 * Move from 1 to 2: 1 fuel
 * Move from 2 to 2: 0 fuel
 * Move from 0 to 2: 2 fuel
 * Move from 4 to 2: 2 fuel
 * Move from 2 to 2: 0 fuel
 * Move from 7 to 2: 5 fuel
 * Move from 1 to 2: 1 fuel
 * Move from 2 to 2: 0 fuel
 * Move from 14 to 2: 12 fuel
 * This costs a total of 37 fuel. This is the cheapest possible outcome; more expensive outcomes include aligning at
 * position 1 (41 fuel), position 3 (39 fuel), or position 10 (71 fuel).
 *
 * Determine the horizontal position that the crabs can align to using the least fuel possible. How much fuel must they
 * spend to align to that position?
 *
 * --- Part Two ---
 * The crabs don't seem interested in your proposed solution. Perhaps you misunderstand crab engineering?
 *
 * As it turns out, crab submarine engines don't burn fuel at a constant rate. Instead, each change of 1 step in
 * horizontal position costs 1 more unit of fuel than the last: the first step costs 1, the second step costs 2, the
 * third step costs 3, and so on.
 *
 * As each crab moves, moving further becomes more expensive. This changes the best horizontal position to align them
 * all on; in the example above, this becomes 5:
 *
 * Move from 16 to 5: 66 fuel
 * Move from 1 to 5: 10 fuel
 * Move from 2 to 5: 6 fuel
 * Move from 0 to 5: 15 fuel
 * Move from 4 to 5: 1 fuel
 * Move from 2 to 5: 6 fuel
 * Move from 7 to 5: 3 fuel
 * Move from 1 to 5: 10 fuel
 * Move from 2 to 5: 6 fuel
 * Move from 14 to 5: 45 fuel
 * This costs a total of 168 fuel. This is the new cheapest possible outcome; the old alignment position (2) now costs
 * 206 fuel instead.
 *
 * Determine the horizontal position that the crabs can align to using the least fuel possible so they can make you an
 * escape route! How much fuel must they spend to align to that position?
 */
public class Day7 {

    /** Simple memoization for the fuel costs for the second movement cost function */
    private Map<Integer, Integer> newFuelCost = new HashMap<>();

    /**
     * Function to calculate how much it takes to move all the crabs to the given position
     * @param crabs List of crabs to move
     * @param position Position to move the crabs to
     * @return How much fuel it costs to move to that location
     */
    private int testPositionForCrabs(List<Integer> crabs, int position) {
        int fuelCost = 0;
        for(int c : crabs) {
            fuelCost += Math.abs(position - c);
        }

        return fuelCost;
    }

    /**
     * Calculate how much fuel it costs to move the crabs to the position with the new fuel calculations
     * @param crabs List of crabs to move
     * @param position Position to move the crabs to
     * @return How much fuel it costs to move to that location
     */
    private int testPositionForCrabsNewMovement(List<Integer> crabs, int position) {
        if(newFuelCost.size() == 0) {
            //Keep track of the fuel costs and pre-fill it, this speeds everything up massively
            int cumulativeFuelCost = 0;
            for(int i = 0; i < 2000; i++) { //2000 is big enough for this problem
                cumulativeFuelCost += i;
                this.newFuelCost.put(i, cumulativeFuelCost);
            }
        }

        int fuelCost = 0;
        for(int c : crabs) {
            fuelCost += this.newFuelCost.get(Math.abs(position - c));
        }

        return fuelCost;
    }

    /**
     * This will solve part 1 and 2 depending on whether you pass in 0 or 1 as the moveFunctionIndex
     * 0 - Use the testPositionForCrabs for part 1
     * 1 - Use the testPositionForCrabsNewMovement for part 2
     * @param crabs List of crabs to move
     * @param moveFunctionIndex Move function ID to use
     * @return The fuel needed to move to the best position
     */
    private long solveWithMoveFunction(List<Integer> crabs, int moveFunctionIndex) {
        int max = Collections.max(crabs);
        int min = Collections.min(crabs);

        //This is a bruteforce method, we could actually average the values and then search
        //for a global minimum around the average although its possible we might find a local minimum...
        int minFuel = Integer.MAX_VALUE;
        for(int startI = min; startI <= max; startI++) {
            int fuel;
            if(moveFunctionIndex == 0) {
                fuel = this.testPositionForCrabs(crabs, startI);
            }else{
                fuel = this.testPositionForCrabsNewMovement(crabs, startI);
            }
            if(fuel < minFuel) {
                minFuel = fuel;
            }
        }

        return minFuel;
    }

    /**
     * Solves Part 1 using the simple fuel calculation formulae
     * @param crabs A list of crab position
     * @return The minimum fuel needed
     */
    public long solvePartOne(List<Integer> crabs) {
       return this.solveWithMoveFunction(crabs, 0);
    }

    /**
     * Solves Part 2 using the more complex fuel calculation formulae
     * @param crabs A list of crab position
     * @return The minimum fuel needed
     */
    public long solvePartTwo(List<Integer> crabs) {
        return this.solveWithMoveFunction(crabs, 1);
    }

    public static void main(String[] args) {
        List<Integer> crabs = ProblemLoader.loadProblemFromCommaSeperatedStringIntoIntegerList(2021, 7);

        Day7 d = new Day7();
        long partOne = d.solvePartOne(crabs);
        System.out.println("The fuel needed to move the crabs into position is " + partOne);

        long partTwo = d.solvePartTwo(crabs);
        System.out.println("The real fuel needed to move the crabs into position is " + partTwo);
    }

}
