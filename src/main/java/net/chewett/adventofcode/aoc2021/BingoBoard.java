package net.chewett.adventofcode.aoc2021;

import java.util.List;

/**
 * Helper datastructure to store data about a bingo board
 */
public class BingoBoard {

    /** The numbers on the bingo board*/
    private int[][] numberBoard = new int[5][5];
    /** Stores whether each segment has been dotted */
    private boolean[][] dotted = new boolean[5][5];
    /** Stores the value of the board once finished */

    /**
     * Constructor to create a bingo board
     * @param lines Bingo data to parse
     */
    public BingoBoard(List<String> lines) {
        for(int rowId = 0; rowId < lines.size(); rowId++) {
            String[] parts = lines.get(rowId).trim().replaceAll("  ", " ").split(" ");
            for(int colId = 0; colId < parts.length; colId++) {
                int cellValue = Integer.parseInt(parts[colId]);
                this.numberBoard[rowId][colId] = cellValue;
                this.dotted[rowId][colId] = false;
            }
        }
    }

    /**
     * Look for any instances of the number and dot it if found. Once all numbers have been dotted
     * it checks to see if we have won
     * @param number Number to look for
     * @return Score of the board if won, or -1 if we have not won
     */
    public int mark(int number) {
        //Mark the number if we have any
        for(int rowId = 0; rowId < this.numberBoard.length; rowId++) {
            for(int colId = 0; colId < this.numberBoard[rowId].length; colId++) {
                if(this.numberBoard[rowId][colId] == number) {
                    this.dotted[rowId][colId] = true;
                }
            }
        }

        //If the entire row is dotted, return the score of the board
        for(int rowId = 0; rowId < this.numberBoard.length; rowId++) {
            if(this.dotted[rowId][0] && this.dotted[rowId][1] && this.dotted[rowId][2] && this.dotted[rowId][3] && this.dotted[rowId][4]) {
                return this.getSumOfUnmarkedAnswers() * number;
            }
        }

        //If the entire column is dotted, return the score of board
        for(int colId = 0; colId < this.numberBoard[0].length; colId++) {
            if(this.dotted[0][colId] && this.dotted[1][colId] && this.dotted[2][colId] && this.dotted[3][colId] && this.dotted[4][colId]) {
                return this.getSumOfUnmarkedAnswers() * number;
            }
        }

        //Return -1 if the board has not been completed
        return -1;
    }

    /**
     * Find all numbers that have not been dotted and return the sum of the unmarked numbers
     * @return The sum of all unmarked numbers
     */
    private int getSumOfUnmarkedAnswers() {
        int sum = 0;
        for(int rowId = 0; rowId < this.numberBoard.length; rowId++) {
            for (int colId = 0; colId < this.numberBoard[rowId].length; colId++) {
                if(!this.dotted[rowId][colId]) {
                    sum += this.numberBoard[rowId][colId];
                }
            }
        }
        return sum;
    }

}
