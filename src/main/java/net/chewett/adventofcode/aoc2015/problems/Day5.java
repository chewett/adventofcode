package net.chewett.adventofcode.aoc2015.problems;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 *
 * A nice string is one with all of the following properties:
 *
 * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 * It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
 * It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
 * For example:
 *
 * ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
 * aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
 * jchzalrnumimnmhp is naughty because it has no double letter.
 * haegwjzuvuyypxyu is naughty because it contains the string xy.
 * dvszwmarrgswjxmb is naughty because it contains only one vowel.
 * How many strings are nice?
 *
 * --- Part Two ---
 * Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.
 *
 * Now, a nice string is one with all of the following properties:
 *
 * It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 * It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
 * For example:
 *
 * qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
 * xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
 * uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
 * ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
 * How many strings are nice under these new rules?
 */
public class Day5 {

    private Set<Character> vowels = new HashSet<>();
    private Set<String> bannedStrings = new HashSet<>();

    public Day5() {
        //Store the banned list of strings and vowels for easy use for part one
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        bannedStrings.add("ab");
        bannedStrings.add("cd");
        bannedStrings.add("pq");
        bannedStrings.add("xy");

    }

    /**
     * Function to determine whether a string is nice or naughty based on the requirements in part one
     * @param str The string to check for naughtiness
     * @return True if the string is nice, false if it is naughty
     */
    public boolean isNiceStringPartOne(String str) {
        int vowelCount = 0;
        boolean foundDuplicate = false;
        char previousLetter = 0;
        for(int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);

            if(this.vowels.contains(curChar)) {
                vowelCount++;
            }

            if(i > 0) {
                if(previousLetter == curChar) {
                    foundDuplicate = true;
                }

                //Quickly check to see if these two characters form one of the banned strings!
                String possibleBannedString = ""+ previousLetter + curChar;
                if(this.bannedStrings.contains(possibleBannedString)) {
                    return false;
                }
            }

            previousLetter = curChar;
        }

        //Nice string if vowels are greater than two and a duplicate has been found
        return vowelCount > 2 && foundDuplicate;
    }

    /**
     * Function to determine whether a string is nice or naughty based on the requirements in part two
     * @param str The string to check for naughtiness
     * @return True if the string is nice, false if it is naughty
     */
    public boolean isNiceStringPartTwo(String str) {
        boolean foundPairing = false;
        boolean foundPairWithBridge = false;
        for(int i = 0; i < str.length(); i++) {
            if(i > 0) {
                char curChar = str.charAt(i);

                //Only look for a pairing if we haven't already found one
                if(!foundPairing) {
                    char prevChar = str.charAt(i-1);
                    String currentPairing = ""+prevChar+curChar;

                    for(int x = i+2; x < str.length(); x++) {
                        char xPrevChar = str.charAt(x-1);
                        char xCurChar = str.charAt(x);

                        String xCurrentPairing = ""+xPrevChar+xCurChar;
                        if(xCurrentPairing.equals(currentPairing)) {
                            foundPairing = true;
                            break;
                        }
                    }
                }
                //Only do this if we have three characters to look at and haven't already found a pair with a bridge char
                if(i > 1 && !foundPairWithBridge) {
                    char prevPrevChar = str.charAt(i -2);
                    if(prevPrevChar == curChar) {
                        foundPairWithBridge = true;
                    }
                }
            }
        }

        //Nice if both of these have been found to be true
        return foundPairWithBridge && foundPairing;
    }


    public long solvePartOne(List<String> strings) {
        int niceStringsFound = 0;
        for(String str : strings) {
            if(this.isNiceStringPartOne(str)) {
                niceStringsFound++;
            }
        }

        return niceStringsFound;
    }



    public long solvePartTwo(List<String> strings) {
        int niceStringsFound = 0;
        for(String str : strings) {
            if(this.isNiceStringPartTwo(str)) {
                niceStringsFound++;
            }
        }

        return niceStringsFound;
    }

    public static void main(String[] args) {
        List<String> strings = ProblemLoader.loadProblemIntoStringArray(2015, 5);
        Day5 d = new Day5();
        long niceStrings = d.solvePartOne(strings);
        System.out.println("There are " + niceStrings + " nice strings");
        long niceStringsPartTwo = d.solvePartTwo(strings);
        System.out.println("There are " + niceStringsPartTwo + " nice strings with the second method");
    }

}
