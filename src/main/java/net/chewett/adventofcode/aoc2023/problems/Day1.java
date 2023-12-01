package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * --- Day 1: Trebuchet?! ---
 * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given
 * you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
 *
 * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by
 * December 25th.
 *
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second
 * puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 *
 * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you
 * ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the
 * sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a
 * trebuchet ("please hold still, we need to strap you in").
 *
 * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been
 * amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are
 * having trouble reading the values on the document.
 *
 * The newly-improved calibration document consists of lines of text; each line originally contained a specific
 * calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining
 * the first digit and the last digit (in that order) to form a single two-digit number.
 *
 * For example:
 *
 * 1abc2
 * pqr3stu8vwx
 * a1b2c3d4e5f
 * treb7uchet
 * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces
 * 142.
 *
 * Consider your entire calibration document. What is the sum of all of the calibration values?
 *
 * --- Part Two ---
 * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two,
 * three, four, five, six, seven, eight, and nine also count as valid "digits".
 *
 * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
 *
 * two1nine
 * eightwothree
 * abcone2threexyz
 * xtwone3four
 * 4nineeightseven2
 * zoneight234
 * 7pqrstsixteen
 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
 */
public class Day1 {

    /**
     * Find the calibration value from a list of strings
     * @param lines List of strings which have been artfully transformed from the original calibration values
     * @return Sum total of all calibration values
     */
    private long findCalibration(List<String> lines) {
        long total = 0;
        for(String line : lines) {
            String val = "";
            for(int i = 0; i < line.length(); i++) {
                if(Character.isDigit(line.charAt(i))) {
                    val += line.charAt(i);
                    break;
                }
            }
            for(int i = line.length() -1; i >= 0; i--) {
                if(Character.isDigit(line.charAt(i))) {
                    val += line.charAt(i);
                    break;
                }
            }

            total += Integer.parseInt(val);
        }

        return total;
    }

    /**
     * Solve part one where only digits 1-9 are considered
     * @param lines Lines of calibration numbers
     * @return The sum total of all calibration numbers
     */
    public long solvePartOne(List<String> lines) {
        return this.findCalibration(lines);
    }

    private String transformWordsToNumbers(String calibrationLine) {
         String trueString = "";

        for(int pos = 0; pos < calibrationLine.length(); pos++) {
            int strLeft = calibrationLine.length() - pos;

            if(strLeft >= 3 && calibrationLine.startsWith("one", pos)) {
                trueString += "1";
                pos += 2;
            }else if(strLeft >= 3 && calibrationLine.startsWith("two", pos)) {
                trueString += "2";
                pos += 2;
            }else if(strLeft >= 5 && calibrationLine.startsWith("three", pos)) {
                trueString += "3";
                pos += 4;
            }else if(strLeft >= 4 && calibrationLine.startsWith("four", pos)) {
                trueString += "4";
                pos += 3;
            }else if(strLeft >= 4 && calibrationLine.startsWith("five", pos)) {
                trueString += "5";
                pos += 3;
            }else if(strLeft >= 3 && calibrationLine.startsWith("six", pos)) {
                trueString += "6";
                pos += 2;
            }else if(strLeft >= 5 && calibrationLine.startsWith("seven", pos)) {
                trueString += "7";
                pos += 4;
            }else if(strLeft >= 5 && calibrationLine.startsWith("eight", pos)) {
                trueString += "8";
                pos += 4;
            }else if(strLeft >= 4 && calibrationLine.startsWith("nine", pos)) {
                trueString += "9";
                pos += 3;
            }else{
                trueString += calibrationLine.charAt(pos);
            }
        }
        return trueString;
    }

    public long solvePartTwo(List<String> lines) {
        long total = 0;
        for(String line : lines) {
            String forwardString = this.transformWordsToNumbers(line);

            String backwardString = "";

            for(int pos = line.length()-1; pos >= 0; pos--) {
                int strLeft = 100;

                if (strLeft >= 3 && line.startsWith("one", pos)) {
                    backwardString = "1" + backwardString;
                    pos -= 2;
                } else if (strLeft >= 3 && line.startsWith("two", pos)) {
                    backwardString = "2"+ backwardString;
                    pos -= 2;
                } else if (strLeft >= 5 && line.startsWith("three", pos)) {
                    backwardString = "3"+ backwardString;
                    pos -= 4;
                } else if (strLeft >= 4 && line.startsWith("four", pos)) {
                    backwardString = "4"+ backwardString;
                    pos -= 3;
                } else if (strLeft >= 4 && line.startsWith("five", pos)) {
                    backwardString = "5"+ backwardString;
                    pos -= 3;
                } else if (strLeft >= 3 && line.startsWith("six", pos)) {
                    backwardString = "6"+ backwardString;
                    pos -= 2;
                } else if (strLeft >= 5 && line.startsWith("seven", pos)) {
                    backwardString = "7"+ backwardString;
                    pos -= 4;
                } else if (strLeft >= 5 && line.startsWith("eight", pos)) {
                    backwardString = "8"+ backwardString;
                    pos -= 4;
                } else if (strLeft >= 4 && line.startsWith("nine", pos)) {
                    backwardString = "9"+ backwardString;
                    pos -= 3;
                } else {
                    backwardString = line.charAt(pos) + backwardString;
                }
            }

            String val = "";
            for(int i = 0; i < forwardString.length(); i++) {
                if(Character.isDigit(forwardString.charAt(i))) {
                    val += forwardString.charAt(i);
                    break;
                }
            }

            for(int i = backwardString.length() -1; i >= 0; i--) {
                if(Character.isDigit(backwardString.charAt(i))) {
                    val += backwardString.charAt(i);
                    break;
                }
            }

            total += Integer.parseInt(val);


        }

        return total;
    }

    public static void main(String[] args) {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2023, 1);

        Day1 d = new Day1();
        long partOne = d.solvePartOne(lines);
        System.out.println("" + partOne);

        long partTwo = d.solvePartTwo(lines);
        System.out.println("" + partTwo);
    }
}


