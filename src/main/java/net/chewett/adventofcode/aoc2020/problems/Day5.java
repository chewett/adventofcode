package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 5: Binary Boarding ---
 * You board your plane only to discover a new problem: you dropped your boarding pass! You aren't sure which seat is
 * yours, and all of the flight attendants are busy with the flood of people that suddenly made it through passport
 * control.
 *
 * You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input);
 * perhaps you can find your seat through process of elimination.
 *
 * Instead of zones or groups, this airline uses binary space partitioning to seat people. A seat might be specified
 * like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".
 *
 * The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane (numbered 0
 * through 127). Each letter tells you which half of a region the given seat is in. Start with the whole list of rows;
 * the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127). The next
 * letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.
 *
 * For example, consider just the first seven characters of FBFBBFFRLR:
 *
 * Start by considering the whole range, rows 0 through 127.
 * F means to take the lower half, keeping rows 0 through 63.
 * B means to take the upper half, keeping rows 32 through 63.
 * F means to take the lower half, keeping rows 32 through 47.
 * B means to take the upper half, keeping rows 40 through 47.
 * B keeps rows 44 through 47.
 * F keeps rows 44 through 45.
 * The final F keeps the lower of the two, row 44.
 * The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the plane
 * (numbered 0 through 7). The same process as above proceeds again, this time with only three steps. L means to keep
 * the lower half, while R means to keep the upper half.
 *
 * For example, consider just the last 3 characters of FBFBBFFRLR:
 *
 * Start by considering the whole range, columns 0 through 7.
 * R means to take the upper half, keeping columns 4 through 7.
 * L means to take the lower half, keeping columns 4 through 5.
 * The final R keeps the upper of the two, column 5.
 * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
 *
 * Every seat also has a unique seat ID: multiply the row by 8, then add the column. In this example, the seat has ID
 * 44 * 8 + 5 = 357.
 *
 * Here are some other boarding passes:
 *
 * BFFFBBFRRR: row 70, column 7, seat ID 567.
 * FFFBBBFRRR: row 14, column 7, seat ID 119.
 * BBFFBBFRLL: row 102, column 4, seat ID 820.
 * As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
 *
 * --- Part Two ---
 * Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
 *
 * It's a completely full flight, so your seat should be the only missing boarding pass in your list. However, there's
 * a catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be
 * missing from your list as well.
 *
 * Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.
 *
 * What is the ID of your seat?
 */
public class Day5 {

    /**
     * The Seat ID can actually be converted to binary and then to a standard base 10 number
     * @param boardingPass String representing boarding pass position
     * @return Integer representing the seat number
     */
    public int convertToSeatId(String boardingPass) {
        return Integer.parseInt(
            boardingPass
                .replace("F", "0")
                .replace("B", "1")
                .replace("R", "1")
                .replace("L", "0")
        , 2);
    }

    /**
     * Find the highest seat ID in the set of boarding passes
     * @param boardingPasses List of boarding passes to scan
     * @return The highest boarding pass ID found
     */
    public int solvePartOne(List<String> boardingPasses) {
        int maxSeatId = 0;
        for(String b : boardingPasses) {
            int seatId = this.convertToSeatId(b);
            maxSeatId = Math.max(maxSeatId, seatId);
        }

        return maxSeatId;
    }

    /**
     * Find the seat ID "missing" from the set which must be mine
     * @param boardingPasses A list of boarding passes that everyone else has
     * @return My seat ID since I have mislaid my boarding pass
     */
    public int solvePartTwo(List<String> boardingPasses) {
        int maxSeatId = 0;
        int minSeatId = Integer.MAX_VALUE;
        Set<Integer> allSeatIds = new HashSet<>();
        for(String b : boardingPasses) {
            //Store the min and max seat ID, and keep track of every seat ID I have found
            int seatId = this.convertToSeatId(b);
            maxSeatId = Math.max(maxSeatId, seatId);
            minSeatId = Math.min(minSeatId, seatId);
            allSeatIds.add(seatId);
        }

        //Start at the min seat ID, going to the max and checking if we have seen this boarding pass
        //If we haven't seen it, then we know that this was our boarding pass
        for(int i = minSeatId; i < maxSeatId; i++) {
            if(!allSeatIds.contains(i)) {
                return i;
            }
        }

        //Return -1 in the event we didnt find our boarding pass (should never happen)
        return -1;
    }

    public static void main(String[] args) {
        Day5 d = new Day5();
        List<String> boardingPasses = ProblemLoader.loadProblemIntoStringArray(2020, 5);

        int p1 = d.solvePartOne(boardingPasses);
        System.out.println("The highest seat ID is " + p1);
        int p2 = d.solvePartTwo(boardingPasses);
        System.out.println("My seat ID must be " + p2 + " as it is missing from the other boarding cards");
    }


}
