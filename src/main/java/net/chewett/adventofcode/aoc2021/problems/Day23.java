package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.aoc2021.Day23State;

import java.util.*;

/**
 *
 */
public class Day23 {

    private int solveWithNormal(String exampleInput, boolean extended) {
        Day23State state = new Day23State(exampleInput, 0, extended);

        Set<String> foundStates = new HashSet<>();
        Map<String, Integer> stateEnergyMin = new HashMap<>();

        PriorityQueue<Day23State> queue = new PriorityQueue<>();
        queue.add(state);

        state.printThis();

        int bestState = Integer.MAX_VALUE;
        Day23State foundEnd = null;
        while(!queue.isEmpty()) {
            Day23State checkState = queue.poll();
            String thisStateAsString = checkState.getState();

            //If we have found this before then check to see if we have visited this at a lower energy level
            if(foundStates.contains(thisStateAsString)) {
                if(checkState.getEnergy() >= stateEnergyMin.get(thisStateAsString)) {
                    continue;
                }
                stateEnergyMin.put(thisStateAsString, checkState.getEnergy());

            }

            foundStates.add(thisStateAsString);
            stateEnergyMin.put(thisStateAsString, checkState.getEnergy());
            if(checkState.isFinalState()) {
                if(checkState.getEnergy() < bestState) {
                    foundEnd = checkState;
                    bestState = checkState.getEnergy();
                }

            }else{
                List<Day23State> newStates = checkState.getNewStates();
                queue.addAll(newStates);
            }
        }

        foundEnd.printThis();
        return foundEnd.getEnergy();
    }


    public long solvePartOne(String exampleInput) {
        return this.solveWithNormal(exampleInput, false);
    }

    public long solvePartTwo(String exampleInput) {
        return this.solveWithNormal(exampleInput, true);
    }

    public static void main(String[] args) {
        String exampleInput = "00BA0CD0BC0DA00";
        //exampleInput = "00BC0DD0CB0AA00";

        Day23 d = new Day23();
        System.out.println(" " + d.solvePartOne(exampleInput));
        System.out.println("" + d.solvePartTwo(exampleInput));

    }

}
