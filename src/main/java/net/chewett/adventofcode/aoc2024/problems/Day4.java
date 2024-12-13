package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ListHelpers;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 4: Ceres Search ---
 * "Looks like the Chief's not here. Next!" One of The Historians pulls out a device and pushes the only button on it.
 * After a brief flash, you recognize the interior of the Ceres monitoring station!
 *
 * As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt; she'd like to know
 * if you could help her with her word search (your puzzle input). She only has to find one word: XMAS.
 *
 * This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other
 * words. It's a little unusual, though, as you don't merely need to find one instance of XMAS - you need to find all
 * of them. Here are a few ways XMAS might appear, where irrelevant characters have been replaced with .:
 *
 *
 * ..X...
 * .SAMX.
 * .A..A.
 * XMAS.S
 * .X....
 * The actual word search will be full of letters instead. For example:
 *
 * MMMSXXMASM
 * MSAMXMSMSA
 * AMXSXMAAMM
 * MSAMASMSMX
 * XMASAMXAMM
 * XXAMMXXAMA
 * SMSMSASXSS
 * SAXAMASAAA
 * MAMMMXMMMM
 * MXMXAXMASX
 * In this word search, XMAS occurs a total of 18 times; here's the same word search again, but where letters not
 * involved in any XMAS have been replaced with .:
 *
 * ....XXMAS.
 * .SAMXMS...
 * ...S..A...
 * ..A.A.MS.X
 * XMASAMX.MM
 * X.....XA.A
 * S.S.S.S.SS
 * .A.A.A.A.A
 * ..M.M.M.MM
 * .X.X.XMASX
 * Take a look at the little Elf's word search. How many times does XMAS appear?
 *
 * --- Part Two ---
 * The Elf looks quizzically at you. Did you misunderstand the assignment?
 *
 * Looking for the instructions, you flip over the word search to find that this isn't actually an XMAS puzzle; it's
 * an X-MAS puzzle in which you're supposed to find two MAS in the shape of an X. One way to achieve that is like this:
 *
 * M.S
 * .A.
 * M.S
 * Irrelevant characters have again been replaced with . in the above diagram. Within the X, each MAS can be written
 * forwards or backwards.
 *
 * Here's the same example from before, but this time all of the X-MASes have been kept instead:
 *
 * .M.S......
 * ..A..MSMS.
 * .M.S.MAA..
 * ..A.ASMSM.
 * .M.S.M....
 * ..........
 * S.S.S.S.S.
 * .A.A.A.A..
 * M.M.M.M.M.
 * ..........
 * In this example, an X-MAS appears 9 times.
 *
 * Flip the word search from the instructions back over to the word search side and try again. How many times does an
 * X-MAS appear?
 *
 */
public class Day4 {

    /**
     * Get the string of letters from the central point outwards in the direction given
     * TODO: Abstract this into the grid class
     * @param input Grid input
     * @param x Initial starting position
     * @param y Initial starting position
     * @param xMove Unit to move in X every "jump"
     * @param yMove Unit to move in Y every "jump"
     * @param distance Distance to get the letters for
     * @return Returns a string of all the letters starting from the original position
     */
    public String getStringInDirection(Discrete2DPositionGrid<Character> input, int x, int y, int xMove, int yMove, int distance) {
        StringBuilder str = new StringBuilder();

        for(int movedVal = 0; movedVal < distance; movedVal++) {
            str.append(input.getValueAtPosition(x, y));
            x += xMove;
            y += yMove;
        }

        return str.toString();
    }

    /**
     * Get all the string of letters from every direction from the starting position
     * TODO: Abstract this into the grid class
     * @param input Grid input
     * @param x Initial starting position
     * @param y Initial starting position
     * @param distance Distance to get the letters for
     * @return List of strings representing all the strings from the central point
     */
    public List<String> getStringsInAllDirections(Discrete2DPositionGrid<Character> input, int x, int y, int distance) {
        List<String> strs = new ArrayList<>();

        strs.add(this.getStringInDirection(input, x, y, 1, -1, distance));
        strs.add(this.getStringInDirection(input, x, y, 1, 0, distance));
        strs.add(this.getStringInDirection(input, x, y, 1, 1, distance));
        strs.add(this.getStringInDirection(input, x, y, 0, -1, distance));
        strs.add(this.getStringInDirection(input, x, y, 0, 1, distance));
        strs.add(this.getStringInDirection(input, x, y, -1, -1, distance));
        strs.add(this.getStringInDirection(input, x, y, -1, 0, distance));
        strs.add(this.getStringInDirection(input, x, y, -1, 1, distance));

        return strs;
    }

    /**
     * Get all the string of letters from the diagonals from the starting position
     * TODO: Abstract this into the grid class
     * @param input Grid input
     * @param x Initial starting position
     * @param y Initial starting position
     * @param distance Distance to get the letters for
     * @return List of strings representing all the diagonal strings from the central point
     */
    public List<String> getStringsInDiagonalDirections(Discrete2DPositionGrid<Character> input, int x, int y, int distance) {
        List<String> strs = new ArrayList<>();

        strs.add(this.getStringInDirection(input, x, y, 1, 1, distance));
        strs.add(this.getStringInDirection(input, x, y, 1, -1, distance));
        strs.add(this.getStringInDirection(input, x, y, -1, -1, distance));
        strs.add(this.getStringInDirection(input, x, y, -1, 1, distance));

        return strs;
    }

    /**
     * Looks through the grid to find all the instances of XMAS and count them
     * @param input Grid
     * @return Number of instances of XMAS
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        long foundXmas = 0;
        for(int x = input.getMinX(); x <= input.getMaxX(); x++) {
            for(int y = input.getMinY(); y <= input.getMaxY(); y++) {
                List<String> words = this.getStringsInAllDirections(input, x, y, 4);
                for(String word : words) {
                    if(word.equals("XMAS")) {
                        foundXmas++;
                    }
                }
            }
        }

        return foundXmas;
    }

    /**
     * Looks through the grid to find all the instances of MAS in an X position and count them
     * @param input Grid
     * @return Number of instances of MAS in an X position
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        long foundXmas = 0;
        for(int x = input.getMinX(); x <= input.getMaxX(); x++) {
            for(int y = input.getMinY(); y <= input.getMaxY(); y++) {
                char thisChar = input.getValueAtPosition(x, y);
                //Ignore everything that isn't an A at the start
                if(thisChar != 'A') {
                    continue;
                }

                List<String> words = this.getStringsInDiagonalDirections(input, x, y, 2);
                Map<String, Integer> count = ListHelpers.countOccurencesInlist(words);
                //Check that there are exactly two AS and AM in the grid
                if(count.getOrDefault("AS", 0) == 2 &&
                    count.getOrDefault("AM", 0) == 2) {

                    //Then check to see if the words have two conseuctive AM and AS
                    //This is to ensure it's MAS and not SAS and MAM
                    if(words.get(0).equals(words.get(1)) || words.get(1).equals(words.get(2))) {
                        foundXmas++;
                    }
                }
            }
        }

        return foundXmas;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 4);

        Day4 d = new Day4();
        long partOne = d.solvePartOne(input);
        System.out.println("XMAS has been found in the word search " + partOne + " times");

        long partTwo = d.solvePartTwo(input);
        System.out.println("MAS in an X pattern has been found in the word search " + partTwo + " times");
    }
}


