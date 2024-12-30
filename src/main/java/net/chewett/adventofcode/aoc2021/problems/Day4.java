package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.aoc2021.BingoBoard;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * --- Day 4: Giant Squid ---
 * You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any
 * sunlight. What you can see, however, is a giant squid that has attached itself to the outside of your submarine.
 *
 * Maybe it wants to play bingo?
 *
 * Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the
 * chosen number is marked on all boards on which it appears. (Numbers may not appear on all boards.) If all numbers in
 * any row or any column of a board are marked, that board wins. (Diagonals don't count.)
 *
 * The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time. It
 * automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input).
 * For example:
 *
 * 7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
 *
 * 22 13 17 11  0
 *  8  2 23  4 24
 * 21  9 14 16  7
 *  6 10  3 18  5
 *  1 12 20 15 19
 *
 *  3 15  0  2 22
 *  9 18 13 17  5
 * 19  8  7 25 23
 * 20 11 10 24  4
 * 14 21 16 12  6
 *
 * 14 21 17 24  4
 * 10 16 15  9 19
 * 18  8 23 26 20
 * 22 11 13  6  5
 *  2  0 12  3  7
 * After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as
 * follows (shown here adjacent to each other to save space):
 *
 * 22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 *  8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
 * 21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 *  6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 *  1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
 * After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:
 *
 * 22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 *  8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
 * 21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 *  6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 *  1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
 * Finally, 24 is drawn:
 *
 * 22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 *  8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
 * 21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 *  6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 *  1 12 20 15 19        14 21 16 12  6         2  0 12  3  7
 * At this point, the third board wins because it has at least one complete row or column of marked numbers (in this
 * case, the entire top row is marked: 14 21 17 24 4).
 *
 * The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers on that
 * board; in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board won,
 * 24, to get the final score, 188 * 24 = 4512.
 *
 * To guarantee victory against the giant squid, figure out which board will win first. What will your final score be
 * if you choose that board?
 *
 * --- Part Two ---
 * On the other hand, it might be wise to try a different strategy: let the giant squid win.
 *
 * You aren't sure how many bingo boards a giant squid could play at once, so rather than waste time counting its arms,
 * the safe thing to do is to figure out which board will win last and choose that one. That way, no matter which
 * boards it picks, it will win for sure.
 *
 * In the above example, the second board is the last to win, which happens after 13 is eventually called and its
 * middle column is completely marked. If you were to keep playing until this point, the second board would have a sum
 * of unmarked numbers equal to 148 for a final score of 148 * 13 = 1924.
 *
 * Figure out which board will win last. Once it wins, what would its final score be?
 */
public class Day4 {

    /**
     * Helper function to create an array of bingo boards from the list of bingo instructions
     * @param bingoInstructions List of strings with the bingo data
     * @return A list of bingo boards from the input
     */
    private List<BingoBoard> createBingoBoards(List<String> bingoInstructions) {
        List<BingoBoard> allBoards = new ArrayList<>();
        List<String> currentBingoBoardLines = new ArrayList<>();
        //Start at line 2 since the first line is the numbers
        for(int lineNo = 2; lineNo < bingoInstructions.size(); lineNo++) {
            String currentLine = bingoInstructions.get(lineNo);
            if(currentLine.equals("")) {
                allBoards.add(new BingoBoard(currentBingoBoardLines));
                currentBingoBoardLines = new ArrayList<>();
            }else{
                currentBingoBoardLines.add(currentLine);
            }
        }

        //Add the final board
        allBoards.add(new BingoBoard(currentBingoBoardLines));
        return allBoards;
    }

    /**
     * Find out what board will win first and then calculate it's score
     * @param bingo_instructions List of strings with the bingo data
     * @return Return the score of the winning bingo board
     */
    public long solvePartOne(List<String> bingo_instructions) {
        List<Integer> numbers = Arrays.stream(bingo_instructions.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<BingoBoard> allBoards = this.createBingoBoards(bingo_instructions);

        for(int number : numbers) {
            for(BingoBoard bb : allBoards) {
                int ret = bb.mark(number);
                if(ret != -1) {
                    return ret;
                }
            }
        }

        return -1;
    }

    /**
     * Find out which board will win last and work out its score
     * @param bingo_instructions List of strings with the bingo data
     * @return Return the score of the last  winning board
     */
    public long solvePartTwo(List<String> bingo_instructions) {
        List<Integer> numbers = Arrays.stream(bingo_instructions.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<BingoBoard> allBoards = this.createBingoBoards(bingo_instructions);
        List<BingoBoard> boardsToRemove = new ArrayList<>();

        int numberToCall = 0;
        while(allBoards.size() > 1) {
            int curNumberToCall = numbers.get(numberToCall);
            for(BingoBoard bb : allBoards) {
                int ret = bb.mark(curNumberToCall);
                if(ret != -1) {
                    boardsToRemove.add(bb);
                }
            }

            allBoards.removeAll(boardsToRemove);
            boardsToRemove = new ArrayList<>();

            numberToCall++;
        }

        while(numberToCall < numbers.size()) {
            int curNumberToCall = numbers.get(numberToCall);
            //Only one left now
            for(BingoBoard bb : allBoards) {
                int ret = bb.mark(curNumberToCall);
                if(ret != -1) {
                    return ret;
                }
            }

            numberToCall++;
        }

        return -1;
    }

    public static void main(String[] args) {
        List<String> bingo_instructions = ProblemLoader.loadProblemIntoStringArray(2021, 4);

        Day4 d = new Day4();
        long partOne = d.solvePartOne(bingo_instructions);
        System.out.println("" + partOne);

        long partTwo = d.solvePartTwo(bingo_instructions);
        System.out.println("" + partTwo);
    }


}
