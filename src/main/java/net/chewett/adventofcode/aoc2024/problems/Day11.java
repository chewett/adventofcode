package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 *
 */
public class Day11 {

    /**
     * This problem is easily solved using the Lanturnfish approach from 2021
     * (keep track of the number of stones, not the actual numbers)
     *
     * So simulate the changes, but track the number of stones of each state, rather than the individual stones
     *
     * @param input Starting stones
     * @param timeToSimulate How many blinks to simulate it for
     * @return The number of stones at the end
     */
    private long simulateStones(String input, int timeToSimulate) {
        //Grab the data
        String[] parts = input.split(" ");
        Map<Long, Long> stoneCount = new HashMap<>();

        //Load it into the map of stone values -> count of them
        for(String p : parts) {
            long stone = Long.parseLong(p);
            if(!stoneCount.containsKey(stone)) {
                stoneCount.put(stone, 1L);
            }else{
                stoneCount.put(stone, stoneCount.get(stone) + 1);
            }
        }

        //Do the magic N times
        for(int i = 0; i < timeToSimulate; i++) {
            Map<Long, Long> newStoneCount = new HashMap<>();

            for(Map.Entry<Long, Long> entry : stoneCount.entrySet()) {

                //If zero, move to 1
                if(entry.getKey() == 0L) {
                    newStoneCount.put(1L, entry.getValue() + newStoneCount.getOrDefault(entry.getKey(), 0L));

                    //horrific hack to see if the number is even...
                }else if((""+entry.getKey()).length() % 2 == 0) {
                    String numberToSplit = ""+entry.getKey();
                    long partOne = Long.parseLong(numberToSplit.substring(0, numberToSplit.length()/2));
                    long partTwo = Long.parseLong(numberToSplit.substring(numberToSplit.length()/2));

                    newStoneCount.put(partOne, entry.getValue() + newStoneCount.getOrDefault(partOne, 0L));
                    newStoneCount.put(partTwo, entry.getValue() + newStoneCount.getOrDefault(partTwo, 0L));

                }else{
                    long newVal = entry.getKey() * 2024;
                    newStoneCount.put(newVal, entry.getValue() + newStoneCount.getOrDefault(newVal, 0L));
                }
            }

            stoneCount = newStoneCount;
        }

        long total = 0;
        for(Map.Entry<Long, Long> entry : stoneCount.entrySet()) {
            total += entry.getValue();
        }

        return total;
    }

    /**
     * Simulate the stones for 25 blinks
     * @param input Initial mapping
     * @return Number of stones at the end
     */
    public long solvePartOne(String input) {
        return this.simulateStones(input, 25);
    }

    /**
     * Simulate the stones for 25 blinks
     * @param input Initial mapping
     * @return Number of stones at the end
     */
    public long solvePartTwo(String input) {
        return this.simulateStones(input, 75);
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2024, 11);

        Day11 d = new Day11();
        long partOne = d.solvePartOne(input);
        System.out.println("Simulating the stones for 25 blinks ends up with " + partOne + " stones");

        long partTwo = d.solvePartTwo(input);
        System.out.println("Simulating the stones for 75 blinks ends up with " + partTwo + " stones");
    }
}


