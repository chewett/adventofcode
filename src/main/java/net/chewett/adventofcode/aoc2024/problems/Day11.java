package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 11: Plutonian Pebbles ---
 * The ancient civilization on Pluto was known for its ability to manipulate spacetime, and while The Historians
 * explore their infinite corridors, you've noticed a strange set of physics-defying stones.
 *
 * At first glance, they seem like normal stones: they're arranged in a perfectly straight line, and each stone has a
 * number engraved on it.
 *
 * The strange part is that every time you blink, the stones change.
 *
 * Sometimes, the number engraved on a stone changes. Other times, a stone might split in two, causing all the other
 * stones to shift over a bit to make room in their perfectly straight line.
 *
 * As you observe them for a while, you find that the stones have a consistent behavior. Every time you blink, the
 * stones each simultaneously change according to the first applicable rule in this list:
 *
 * If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
 * If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. The left
 * half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new
 * right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
 * If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024
 * is engraved on the new stone.
 * No matter how the stones change, their order is preserved, and they stay on their perfectly straight line.
 *
 * How will the stones evolve if you keep blinking at them? You take a note of the number engraved on each stone in
 * the line (your puzzle input).
 *
 * If you have an arrangement of five stones engraved with the numbers 0 1 10 99 999 and you blink once, the stones
 * transform as follows:
 *
 * The first stone, 0, becomes a stone marked 1.
 * The second stone, 1, is multiplied by 2024 to become 2024.
 * The third stone, 10, is split into a stone marked 1 followed by a stone marked 0.
 * The fourth stone, 99, is split into two stones marked 9.
 * The fifth stone, 999, is replaced by a stone marked 2021976.
 * So, after blinking once, your five stones would become an arrangement of seven stones engraved with the numbers
 * 1 2024 1 0 9 9 2021976.
 *
 * Here is a longer example:
 *
 * Initial arrangement:
 * 125 17
 *
 * After 1 blink:
 * 253000 1 7
 *
 * After 2 blinks:
 * 253 0 2024 14168
 *
 * After 3 blinks:
 * 512072 1 20 24 28676032
 *
 * After 4 blinks:
 * 512 72 2024 2 0 2 4 2867 6032
 *
 * After 5 blinks:
 * 1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32
 *
 * After 6 blinks:
 * 2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2
 * In this example, after blinking six times, you would have 22 stones. After blinking 25 times, you would have
 * 55312 stones!
 *
 * Consider the arrangement of stones in front of you. How many stones will you have after blinking 25 times?
 *
 * --- Part Two ---
 * The Historians sure are taking a long time. To be fair, the infinite corridors are very large.
 *
 * How many stones would you have after blinking a total of 75 times?
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


