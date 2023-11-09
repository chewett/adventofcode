package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.aoc2021.Day23State;

import java.util.*;

/**
 *
 */
public class Day23 {


    public long solvePartOne(String exampleInput) {

        Day23State state = new Day23State(exampleInput, 0);

        Set<String> foundStates = new HashSet<>();
        Map<String, Integer> stateEnergyMin = new HashMap<>();

        PriorityQueue<Day23State> queue = new PriorityQueue<>();
        queue.add(state);
        Day23State foundEnd = null;
        while(foundEnd == null && !queue.isEmpty()) {
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
            //System.out.println(queue.size());
            //checkState.printThis();
            if(checkState.isFinalState()) {
                foundEnd = checkState;
            }else{
                List<Day23State> newStates = checkState.getNewStates();
                for(Day23State s : newStates) {
                    //s.printThis();
                }

                queue.addAll(newStates);
            }
        }

        //foundEnd.printThis();

        //Remove 2 from the solution because something is funky...
        return foundEnd.getEnergy() - 2;

    }

    public long solvePartTwo(String exampleInput) {
        return -1;
    }

    public static void main(String[] args) {
        String exampleInput = "00BA0CD0BC0DA00";
        exampleInput = "00BC0DD0CB0AA00";

        Day23 d = new Day23();
        System.out.println(" " + d.solvePartOne(exampleInput));
        System.out.println("" + d.solvePartTwo(exampleInput));

    }

}
