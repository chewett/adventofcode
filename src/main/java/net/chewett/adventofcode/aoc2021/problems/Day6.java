package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;
import java.util.List;

/**
 * --- Day 6: Lanternfish ---
 * The sea floor is getting steeper. Maybe the sleigh keys got carried this way?
 *
 * A massive school of glowing lanternfish swims past. They must spawn quickly to reach such large numbers - maybe
 * exponentially quickly? You should model their growth rate to be sure.
 *
 * Although you know nothing about this specific species of lanternfish, you make some guesses about their attributes.
 * Surely, each lanternfish creates a new lanternfish once every 7 days.
 *
 * However, this process isn't necessarily synchronized between every lanternfish - one lanternfish might have 2 days
 * left until it creates another lanternfish, while another might have 4. So, you can model each fish as a single number
 * that represents the number of days until it creates a new lanternfish.
 *
 * Furthermore, you reason, a new lanternfish would surely need slightly longer before it's capable of producing more
 * lanternfish: two more days for its first cycle.
 *
 * So, suppose you have a lanternfish with an internal timer value of 3:
 *
 * After one day, its internal timer would become 2.
 * After another day, its internal timer would become 1.
 * After another day, its internal timer would become 0.
 * After another day, its internal timer would reset to 6, and it would create a new lanternfish with an internal timer
 * of 8.
 * After another day, the first lanternfish would have an internal timer of 5, and the second lanternfish would have an
 * internal timer of 7.
 * A lanternfish that creates a new fish resets its timer to 6, not 7 (because 0 is included as a valid timer value).
 * The new lanternfish starts with an internal timer of 8 and does not start counting down until the next day.
 *
 * Realizing what you're trying to do, the submarine automatically produces a list of the ages of several hundred nearby
 * lanternfish (your puzzle input). For example, suppose you were given the following list:
 *
 * 3,4,3,1,2
 * This list means that the first fish has an internal timer of 3, the second fish has an internal timer of 4, and so on
 * until the fifth fish, which has an internal timer of 2. Simulating these fish over several days would proceed as
 * follows:
 *
 * Initial state: 3,4,3,1,2
 * After  1 day:  2,3,2,0,1
 * After  2 days: 1,2,1,6,0,8
 * After  3 days: 0,1,0,5,6,7,8
 * After  4 days: 6,0,6,4,5,6,7,8,8
 * After  5 days: 5,6,5,3,4,5,6,7,7,8
 * After  6 days: 4,5,4,2,3,4,5,6,6,7
 * After  7 days: 3,4,3,1,2,3,4,5,5,6
 * After  8 days: 2,3,2,0,1,2,3,4,4,5
 * After  9 days: 1,2,1,6,0,1,2,3,3,4,8
 * After 10 days: 0,1,0,5,6,0,1,2,2,3,7,8
 * After 11 days: 6,0,6,4,5,6,0,1,1,2,6,7,8,8,8
 * After 12 days: 5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8
 * After 13 days: 4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8
 * After 14 days: 3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8
 * After 15 days: 2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7
 * After 16 days: 1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8
 * After 17 days: 0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8
 * After 18 days: 6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8
 * Each day, a 0 becomes a 6 and adds a new 8 to the end of the list, while each other number decreases by 1 if it was
 * present at the start of the day.
 *
 * In this example, after 18 days, there are a total of 26 fish. After 80 days, there would be a total of 5934.
 *
 * Find a way to simulate lanternfish. How many lanternfish would there be after 80 days?
 *
 * --- Part Two ---
 * Suppose the lanternfish live forever and have unlimited food and space. Would they take over the entire ocean?
 *
 * After 256 days in the example above, there would be a total of 26984457539 lanternfish!
 *
 * How many lanternfish would there be after 256 days?
 */
public class Day6 {

    /**
     * This is the "brute force" way to solve it which only works for a small number of numbers
     * This fails horrible for part 2 which uses a much smarter way
     * @param ages List of ints representing the age of the fish
     * @return The number of fish at the end of the 80 days
     */
    public long solvePartOne(List<Integer> ages) {
        //Loop until we finish 80 days
        int days = 0;
        while(days < 80) {
            days++;

            List<Integer> newArrayList = new ArrayList<>();
            for(int i : ages) {
                //Reduce the age by 1
                int newI = i - 1;
                if(newI < 0) {
                    //If fish "age" is <0 again, it creates a new fish at 8 and itself goes to 6
                    newArrayList.add(6);
                    newArrayList.add(8);
                }else{
                    newArrayList.add(newI);
                }
            }

            //Reset the array and try again!
            ages = newArrayList;
        }

        //Return the number of fish
        return ages.size();
    }

    /**
     * This does it the smarter way that isn't memory intensive like the one above.
     * Here instead of storing all the fish, we just store all the fish that are of the current age
     * @param ages List of ints representing the age of the fish
     * @return The number of fish at the end of the 256 days
     */
    public long solvePartTwo(List<Integer> ages) {
        //So we store a map of the ages so we prepare it first
        Map<Integer, Long> agesMap = new HashMap<>();
        for(int age : ages) {
            long currentNumberOfAges = agesMap.getOrDefault(age, 0L);
            agesMap.put(age, currentNumberOfAges+1);
        }

        int days = 0;
        while(days < 256) {
            days++;

            //For each loop, create a new map and then work out what each fish needs to do
            Map<Integer, Long> newAgeMap = new HashMap<>();
            for(Map.Entry<Integer, Long> agePair : agesMap.entrySet()) {
                //Reduce by one, and then if it spawns another, set a new set going
                int newAge = agePair.getKey() - 1;
                if(newAge < 0) {
                    //New ones start at 8
                    newAgeMap.put(8, agePair.getValue());
                    //These go to 6
                    newAge = 6;
                }

                newAgeMap.put(newAge, agePair.getValue() + newAgeMap.getOrDefault(newAge, 0L));
            }
            agesMap = newAgeMap;
        }

        //Now to work out the final total we have to sum up all the values in the set
        long totalFish = 0;
        for(Map.Entry<Integer, Long> agePair : agesMap.entrySet()) {
            totalFish += agePair.getValue();
        }

        return totalFish;
    }

    public static void main(String[] args) {
        List<Integer> ages = ProblemLoader.loadProblemFromCommaSeperatedStringIntoIntegerList(2021, 6);

        Day6 d = new Day6();
        long partOne = d.solvePartOne(ages);
        System.out.println("" + partOne);

        long partTwo = d.solvePartTwo(ages);
        System.out.println("" + partTwo);
    }

}
