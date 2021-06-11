package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;


/**
 * Taken from: https://adventofcode.com/2015/day/1
 * --- Day 1: Not Quite Lisp ---
 * Santa was hoping for a white Christmas, but his weather machine's "snow" function is powered by stars, and he's fresh out! To save Christmas, he needs you to collect fifty stars by December 25th.
 *
 * Collect stars by helping Santa solve puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 *
 * Here's an easy puzzle to warm you up.
 *
 * Santa is trying to deliver presents in a large apartment building, but he can't find the right floor - the directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the instructions one character at a time.
 *
 * An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down one floor.
 *
 * The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.
 *
 * For example:
 *
 * (()) and ()() both result in floor 0.
 * ((( and (()(()( both result in floor 3.
 * ))((((( also results in floor 3.
 * ()) and ))( both result in floor -1 (the first basement level).
 * ))) and )())()) both result in floor -3.
 * To what floor do the instructions take Santa?
 *
 * --- Part Two ---
 * Now, given the same instructions, find the position of the first character that causes him to enter the basement (floor -1). The first character in the instructions has position 1, the second character has position 2, and so on.
 *
 * For example:
 *
 * ) causes him to enter the basement at character position 1.
 * ()()) causes him to enter the basement at character position 5.
 * What is the position of the character that causes Santa to first enter the basement?
 *
 */
public class Day1 {

    /**
     * Given a string representing the instructions to move up or down this will return what floor number Santa ends on
     * @param instructions String representing the move instructions
     * @return An integer representing the final floor Santa ends at
     */
    public int solvePartOne(String instructions) {
        int floorNumber = 0;
        for(int i = 0; i < instructions.length(); i++) {
            //Assume we only get ( or )
            if(instructions.charAt(i) == '(') {
                floorNumber++;
            }else{
                floorNumber--;
            }
        }

        return floorNumber;
    }

    /**
     * Given a set of instructions slowly move up and down until Santa is at the basement and return that character position
     * @param instructions String representing the move instructions
     * @return The character position representing the instruction that moved Santa to the basement first
     */
    public int solvePartTwo(String instructions) {
        int floorNumber = 0;
        for(int i = 0; i < instructions.length(); i++) {
            if(instructions.charAt(i) == '(') {
                floorNumber++;
            }else{
                floorNumber--;
            }

            if(floorNumber == -1) {
                return i + 1; //Add one because the character position is one more than the index (which I am using)
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String st = ProblemLoader.loadProblemIntoString(2015, 1);
        Day1 d = new Day1();
        int endingFloor = d.solvePartOne(st);
        System.out.println("Santa will end up on floor " + endingFloor);
        int firstBasementInstruction = d.solvePartTwo(st);
        System.out.println("Santa enters the basement at instruction: " + firstBasementInstruction);
    }
}
