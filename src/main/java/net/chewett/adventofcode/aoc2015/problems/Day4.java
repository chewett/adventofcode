package net.chewett.adventofcode.aoc2015.problems;
import net.chewett.adventofcode.aoc2015.MD5Hasher;
import net.chewett.adventofcode.helpers.ProblemLoader;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically
 * forward-thinking little girls and boys.
 *
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5
 * hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you
 * must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
 *
 * For example:
 *
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes
 * (000001dbbfa...), and it is the lowest such number to do so.
 * If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is
 * 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 *
 * --- Part Two ---
 * Now find one that starts with six zeroes.
 */
public class Day4 {

    /**
     * Simple function to solve part one and two by adjusting the second paramter.
     *
     * When given the secret key and the number of zero's you are searching for this will return the first number
     * that when combined with the secret key finds a md5 hash starting with the given number of zeros
     * @param secretKey Key to start your hash with
     * @param numberOfZeroes The number of zero's that you are looking for at the start of the string
     * @return The lowest number that when combined with the secret key produces a string with the given number of zeroes
     */
    private long findFirstNumberToStartWithXZeros(String secretKey, int numberOfZeroes) {
        String zeroString = "0".repeat(numberOfZeroes);

        int i = 0;
        while(i < 100000000) {
            i++;

            String hash = MD5Hasher.hash(secretKey + i);
            if(hash.startsWith(zeroString)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Simple function to be called with the findFirstNumberToStartWithXZeros function
     * @param secretKey
     * @return
     */
    public long solvePartOne(String secretKey) {
        return this.findFirstNumberToStartWithXZeros(secretKey, 5);
    }


    /**
     * Simple function to be called with the findFirstNumberToStartWithXZeros function
     * @param secretKey
     * @return
     */
    public long solvePartTwo(String secretKey) {
        return this.findFirstNumberToStartWithXZeros(secretKey, 6);
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2015, 4);
        Day4 d = new Day4();
        long secretNumberOne = d.solvePartOne(input);
        System.out.println("The secret number one is " + secretNumberOne);
        long secretNumberTwo = d.solvePartTwo(input);
        System.out.println("The secret number two is " + secretNumberTwo);
    }

}
