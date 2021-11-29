package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

/**
 * --- Day 10: Elves Look, Elves Say ---
 * Today, the Elves are playing a game called look-and-say. They take turns making sequences by reading aloud the
 * previous sequence and using that reading as the next sequence. For example, 211 is read as "one two, two ones",
 * which becomes 1221 (1 2, 2 1s).
 *
 * Look-and-say sequences are generated iteratively, using the previous value as input for the next step. For each step,
 * take the previous value, and replace each run of digits (like 111) with the number of digits (3) followed by the
 * digit itself (1).
 *
 * For example:
 *
 * 1 becomes 11 (1 copy of digit 1).
 * 11 becomes 21 (2 copies of digit 1).
 * 21 becomes 1211 (one 2 followed by one 1).
 * 1211 becomes 111221 (one 1, one 2, and two 1s).
 * 111221 becomes 312211 (three 1s, two 2s, and one 1).
 * Starting with the digits in your puzzle input, apply this process 40 times. What is the length of the result?
 *
 * --- Part Two ---
 * Neat, right? You might also enjoy hearing John Conway talking about this sequence (that's Conway of Conway's Game of
 * Life fame).
 *
 * Now, starting again with the digits in your puzzle input, apply this process 50 times. What is the length of the new
 * result?
 */
public class Day10 {

    /**
     * Given a string input this will attempt to solve part one using the 40 iterations
     * @param input String input to start on
     * @return Returns the final string output
     */
    public String solvePartOne(String input) {
        return this.solveWithIterations(input, 40);
    }

    /**
     * Given a string input this will attempt to solve part two using the 50 iterations
     * @param input String input to start on
     * @return Returns the final string output
     */
    public String solvePartTwo(String input) {
        return this.solveWithIterations(input, 50);
    }

    /**
     * Given an input string and a number of iterations this will run the algorithm over the string N times
     * @param input Input string to start the algorithm form
     * @param iterations Number of times to run the algorithm
     * @return Returns the final string output
     */
    public String solveWithIterations(String input, int iterations) {
        StringBuilder output = new StringBuilder();
        String currentString = input;
        for(int i = 0; i < iterations; i++) {
            output = new StringBuilder();

            //Keep track of the current char and how many times it has appeared
            char currentChar = currentString.charAt(0);
            int currentCharCount = 1;
            //Loop over each char, either adding to the current char count, or resetting it and adding it to the output string
            for(int charIndex = 1; charIndex < currentString.length(); charIndex++) {
                char newChar = currentString.charAt(charIndex);
                if(newChar != currentChar) {
                    output.append(currentCharCount);
                    output.append(currentChar);
                    currentChar = newChar;
                    currentCharCount = 1;
                }else{
                    currentCharCount++;
                }
            }
            //Now just add the final part to the
            output.append(currentCharCount);
            output.append(currentChar);

            currentString = output.toString();
        }
        return currentString;
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2015, 10);
        Day10 d = new Day10();
        String partOne = d.solvePartOne(input);
        System.out.println("Running the algorithm with 40 iterations results in a string of length " + partOne.length());
        String partTwo = d.solvePartTwo(input);
        System.out.println("Running the algorithm with 50 iterations results in a string of length " + partTwo.length());
    }


}
