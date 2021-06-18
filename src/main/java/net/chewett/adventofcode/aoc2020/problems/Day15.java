package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 15: Rambunctious Recitation ---
 * You catch the airport shuttle and try to book a new flight to your vacation island. Due to the storm, all direct
 * flights have been cancelled, but a route is available to get around the storm. You take it.
 *
 * While you wait for your flight, you decide to check in with the Elves back at the North Pole. They're playing a
 * memory game and are ever so excited to explain the rules!
 *
 * In this game, the players take turns saying numbers. They begin by taking turns reading from a list of starting
 * numbers (your puzzle input). Then, each turn consists of considering the most recently spoken number:
 *
 * If that was the first time the number has been spoken, the current player says 0.
 * Otherwise, the number had been spoken before; the current player announces how many turns apart the number is from
 * when it was previously spoken.
 * So, after the starting numbers, each turn results in that player speaking aloud either 0 (if the last number is new)
 * or an age (if the last number is a repeat).
 *
 * For example, suppose the starting numbers are 0,3,6:
 *
 * Turn 1: The 1st number spoken is a starting number, 0.
 * Turn 2: The 2nd number spoken is a starting number, 3.
 * Turn 3: The 3rd number spoken is a starting number, 6.
 * Turn 4: Now, consider the last number spoken, 6. Since that was the first time the number had been spoken, the 4th
 * number spoken is 0.
 * Turn 5: Next, again consider the last number spoken, 0. Since it had been spoken before, the next number to speak is
 * the difference between the turn number when it was last spoken (the previous turn, 4) and the turn number of the time
 * it was most recently spoken before then (turn 1). Thus, the 5th number spoken is 4 - 1, 3.
 * Turn 6: The last number spoken, 3 had also been spoken before, most recently on turns 5 and 2. So, the 6th number
 * spoken is 5 - 2, 3.
 * Turn 7: Since 3 was just spoken twice in a row, and the last two turns are 1 turn apart, the 7th number spoken is 1.
 * Turn 8: Since 1 is new, the 8th number spoken is 0.
 * Turn 9: 0 was last spoken on turns 8 and 4, so the 9th number spoken is the difference between them, 4.
 * Turn 10: 4 is new, so the 10th number spoken is 0.
 * (The game ends when the Elves get sick of playing or dinner is ready, whichever comes first.)
 *
 * Their question for you is: what will be the 2020th number spoken? In the example above, the 2020th number spoken will
 * be 436.
 *
 * Here are a few more examples:
 *
 * Given the starting numbers 1,3,2, the 2020th number spoken is 1.
 * Given the starting numbers 2,1,3, the 2020th number spoken is 10.
 * Given the starting numbers 1,2,3, the 2020th number spoken is 27.
 * Given the starting numbers 2,3,1, the 2020th number spoken is 78.
 * Given the starting numbers 3,2,1, the 2020th number spoken is 438.
 * Given the starting numbers 3,1,2, the 2020th number spoken is 1836.
 * Given your starting numbers, what will be the 2020th number spoken?
 *
 * --- Part Two ---
 * Impressed, the Elves issue you a challenge: determine the 30000000th number spoken. For example, given the same
 * starting numbers as above:
 *
 * Given 0,3,6, the 30000000th number spoken is 175594.
 * Given 1,3,2, the 30000000th number spoken is 2578.
 * Given 2,1,3, the 30000000th number spoken is 3544142.
 * Given 1,2,3, the 30000000th number spoken is 261214.
 * Given 2,3,1, the 30000000th number spoken is 6895259.
 * Given 3,2,1, the 30000000th number spoken is 18.
 * Given 3,1,2, the 30000000th number spoken is 362.
 * Given your starting numbers, what will be the 30000000th number spoken?
 */
public class Day15 {

    /**
     * The game is the same for part one and two, just with a differnt end condition, so this plays the elves number
     * game up to the specified max number. This will work with any max number
     * @param numbers The numbers to use as a starting pattern
     * @param maxNo The Number you want to stop on for the elves challenge
     * @return The N'th number called, based on the given maxNo
     */
    private long playElvesGameWithNumbers(String numbers, int maxNo) {
        String[] initialSet = numbers.split(",");

        //Use a map to keep track of the last time this turn was called for fast lookup
        Map<Long, Integer> lastTurnCalled = new HashMap<>();

        //Here we init the inial set into the memory
        int turn = 1;
        long previousTurn = 0;
        for(String s : initialSet) {
            previousTurn = Long.parseLong(s);
            lastTurnCalled.put(previousTurn, turn);

            turn++;
        }

        //We then remove the "last" turn and replay that one
        lastTurnCalled.remove(previousTurn);

        while(turn <= maxNo) {
            //Slowly loop over each turn, working out the time between this and the last turn and then going forward
            long previousPrevious = previousTurn;
            previousTurn = lastTurnCalled.getOrDefault(previousTurn, 0);

            lastTurnCalled.put(previousPrevious, turn-1);

            //If its not 0, work out the difference between the last time it was called
            if(previousTurn != 0) {
                previousTurn = turn - previousTurn - 1;
            }

            turn++;
        }

        return previousTurn;
    }

    /**
     * Play the elves game up to the 2020th number
     * @param numbers The initial set of numbers
     * @return The 2020th number called
     */
    public long solvePartOne(String numbers) {
        return this.playElvesGameWithNumbers(numbers, 2020);
    }

    /**
     * Play the elves game up to the 30000000th number
     * @param numbers The initial set of numbers
     * @return The 30000000th number called
     */
    public long solvePartTwo(String numbers) {
        return this.playElvesGameWithNumbers(numbers, 30000000);
    }

    public static void main(String[] args) {
        Day15 d = new Day15();
        String numbers = ProblemLoader.loadProblemIntoString(2020, 15);
        long p1 = d.solvePartOne(numbers);
        System.out.println("The 2020th number called is " + p1);
        long p2 = d.solvePartTwo(numbers);
        System.out.println("The 30000000th number called is " + p2);

    }


}
