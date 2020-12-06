package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.List;


/**
 * --- Day 2: Password Philosophy ---
 * Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via
 * toboggan.
 *
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong with our computers; we
 * can't log in!" You ask if you can take a look.
 *
 * Their password database seems to be a little corrupted: some of the passwords wouldn't have been allowed by the
 * Official Toboggan Corporate Policy that was in effect when they were chosen.
 *
 * To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted
 * database) and the corporate policy when that password was set.
 *
 * For example, suppose you have the following list:
 *
 * 1-3 a: abcde
 * 1-3 b: cdefg
 * 2-9 c: ccccccccc
 * Each line gives the password policy and then the password. The password policy indicates the lowest and highest
 * number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password
 * must contain a at least 1 time and at most 3 times.
 *
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but
 * needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of
 * their respective policies.
 *
 * How many passwords are valid according to their policies?
 *
 * --- Part Two ---
 * While it appears you validated the passwords correctly, they don't seem to be what the Official Toboggan Corporate
 * Authentication System is expecting.
 *
 * The shopkeeper suddenly realizes that he just accidentally explained the password policy rules from his old job at
 * the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little differently.
 *
 * Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second
 * character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of
 * these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of
 * policy enforcement.
 *
 * Given the same example list from above:
 *
 * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
 * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
 * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
 * How many passwords are valid according to the new interpretation of the policies?
 *
 */
public class Day2 {

    public int solvePartOne(List<String> passwordsString) {
        int validPasswordsForPartOne = 0;
        for(String st : passwordsString) {
            String[] p1 = st.split(": ");
            String password = p1[1];
            String[] p2 = p1[0].split(" ");
            char letter = p2[1].charAt(0);
            String[] p3 = p2[0].split("-");
            int minOccurrences = Integer.parseInt(p3[0]);
            int maxOccurrences = Integer.parseInt(p3[1]);

            //PartOneSolving
            int curOccurences = 0;
            for(int i = 0; i < password.length(); i++){
                if(password.charAt(i) == letter) {
                    curOccurences++;
                }
            }
            if(curOccurences >= minOccurrences && curOccurences <= maxOccurrences) {
                validPasswordsForPartOne++;
            }
        }

        return validPasswordsForPartOne;
    }

    public int solvePartTwo(List<String> passwordsString) {
        int validPasswordsForPartTwo = 0;
        for(String st : passwordsString) {

            String[] p1 = st.split(": ");
            String password = p1[1];
            String[] p2 = p1[0].split(" ");
            char letter = p2[1].charAt(0);
            String[] p3 = p2[0].split("-");
            int firstPos = Integer.parseInt(p3[0]);
            int secondLetter = Integer.parseInt(p3[1]);

            int foundInNewPassword = 0;
            if(password.charAt(firstPos - 1) == letter) {
                foundInNewPassword++;
            }
            if(password.charAt(secondLetter - 1) == letter) {
                foundInNewPassword++;
            }

            if(foundInNewPassword == 1) {
                validPasswordsForPartTwo++;
            }
        }

        return validPasswordsForPartTwo;
    }

    public static void main(String[] args) throws Exception {
        List<String> passwords = ProblemLoader.loadProblemIntoStringArray(2020, 2);

        Day2 d = new Day2();
        int validPasswordsForPartOne = d.solvePartOne(passwords);
        System.out.println("Valid passwords - Part One: " + validPasswordsForPartOne);

        int validPasswordsForPartTwo = d.solvePartTwo(passwords);
        System.out.println("Valid passwords - Part Two: " + validPasswordsForPartTwo);

    }


}
