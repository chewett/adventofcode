package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * --- Day 9: Encoding Error ---
 * With your neighbor happily enjoying their video game, you turn your attention to an open data port on the little
 * screen in the seat in front of you.
 *
 * Though the port is non-standard, you manage to connect it to your computer through the clever use of several
 * paperclips. Upon connection, the port outputs a series of numbers (your puzzle input).
 *
 * The data appears to be encrypted with the eXchange-Masking Addition System (XMAS) which, conveniently for you, is an
 * old cypher with an important weakness.
 *
 * XMAS starts by transmitting a preamble of 25 numbers. After that, each number you receive should be the sum of any
 * two of the 25 immediately previous numbers. The two numbers will have different values, and there might be more than
 * one such pair.
 *
 * For example, suppose your preamble consists of the numbers 1 through 25 in a random order. To be valid, the next
 * number must be the sum of two of those numbers:
 *
 * 26 would be a valid next number, as it could be 1 plus 25 (or many other pairs, like 2 and 24).
 * 49 would be a valid next number, as it is the sum of 24 and 25.
 * 100 would not be valid; no two of the previous 25 numbers sum to 100.
 * 50 would also not be valid; although 25 appears in the previous 25 numbers, the two numbers in the pair must be
 * different.
 * Suppose the 26th number is 45, and the first number (no longer an option, as it is more than 25 numbers ago) was 20.
 * Now, for the next number to be valid, there needs to be some pair of numbers among 1-19, 21-25, or 45 that add up to
 * it:
 *
 * 26 would still be a valid next number, as 1 and 25 are still within the previous 25 numbers.
 * 65 would not be valid, as no two of the available numbers sum to it.
 * 64 and 66 would both be valid, as they are the result of 19+45 and 21+45 respectively.
 * Here is a larger example which only considers the previous 5 numbers (and has a preamble of length 5):
 *
 * 35
 * 20
 * 15
 * 25
 * 47
 * 40
 * 62
 * 55
 * 65
 * 95
 * 102
 * 117
 * 150
 * 182
 * 127
 * 219
 * 299
 * 277
 * 309
 * 576
 * In this example, after the 5-number preamble, almost every number is the sum of two of the previous 5 numbers; the
 * only number that does not follow this rule is 127.
 *
 * The first step of attacking the weakness in the XMAS data is to find the first number in the list (after the
 * preamble) which is not the sum of two of the 25 numbers before it. What is the first number that does not have this
 * property?
 *
 * --- Part Two ---
 * The final step in breaking the XMAS encryption relies on the invalid number you just found: you must find a
 * contiguous set of at least two numbers in your list which sum to the invalid number from step 1.
 *
 * Again consider the above example:
 *
 * 35
 * 20
 * 15
 * 25
 * 47
 * 40
 * 62
 * 55
 * 65
 * 95
 * 102
 * 117
 * 150
 * 182
 * 127
 * 219
 * 299
 * 277
 * 309
 * 576
 * In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step 1, 127. (Of
 * course, the contiguous set of numbers in your actual list might be much longer.)
 *
 * To find the encryption weakness, add together the smallest and largest number in this contiguous range; in this
 * example, these are 15 and 47, producing 62.
 *
 * What is the encryption weakness in your XMAS-encrypted list of numbers?
 */
public class Day9 {

    public long solvePartOne(List<Long> cipher, int preambleLength) {
        //Loop over every value in the cipher starting at preamble +1
        for(int curIndex = preambleLength; curIndex < cipher.size(); curIndex++) {
            long numberToCheck = cipher.get(curIndex);
            Set<Long> preambleSet = new HashSet<>();
            //Create the sliding window of the last X preamble values and store it in a set for quick lookup
            for(int checkingNumberIndex = (curIndex - preambleLength); checkingNumberIndex < curIndex; checkingNumberIndex++) {
                preambleSet.add(cipher.get(checkingNumberIndex));
            }

            boolean foundSum = false;
            for(long checkingNumber : preambleSet) {
                //Find the "second" part of the pair which would make this number valid
                long differenceToCheck = numberToCheck - checkingNumber;
                //If our number and another add up to the right number its valid.
                //We dont need to worry about it being a set as the question defines that they must be different
                if(preambleSet.contains(differenceToCheck)) {
                    foundSum = true;
                    break;
                }
            }

            if(!foundSum) {
                return numberToCheck;
            }
        }

        //If nothing was found, return -1 as a failure value
        return -1;
    }

    public long solvePartTwo(List<Long> cipher, long nonMatchingValue) {
        //Loop over each item going backwards as the higher numbers will fail faster (and therefore run quicker potentially)
        for(int index = cipher.size() -1; index >= 0; index--) {
            //Keep track of the cumulative value and max/min numbers
            int cumulativeValue = 0;
            long currentMax = Long.MIN_VALUE;
            long currentMin = Long.MAX_VALUE;
            //Loop over the values and stop when we reach the end of the cipher or the cumulative is higher than the
            //non-matching value
            for(int cumulativeIndex = index; cumulativeIndex < cipher.size() && cumulativeValue < nonMatchingValue; cumulativeIndex++) {
                long newValue = cipher.get(cumulativeIndex);
                //Since we are going end to start, ignore if the new value is the non-matching value as that wont count
                //And will always be too large
                if(newValue == nonMatchingValue) {
                    break;
                }

                cumulativeValue += newValue;
                currentMax = Long.max(currentMax, newValue);
                currentMin = Long.min(currentMin, newValue);
            }

            //If our new cumulative is the value we are looking for, return the sum of max and min
            if(cumulativeValue == nonMatchingValue) {
                return currentMax + currentMin;
            }
        }

        //If nothing was found, return -1 as a known failure value
        return -1;
    }

    public static void main(String[] args) {
        Day9 d = new Day9();
        List<Long> cipher = ProblemLoader.loadProblemIntoLongList(2020, 9);

        long p1 = d.solvePartOne(cipher, 25);
        System.out.println("First value not matching the problem: " + p1);
        long p2 = d.solvePartTwo(cipher, p1);
        System.out.println("Sum of two values in the range that sum to the problem incorrect value: " + p2);
    }

}
