package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.List;

/**
 * --- Day 8: Matchsticks ---
 * Space on the sleigh is limited this year, and so Santa will be bringing his list as a digital copy. He needs to know
 * how much space it will take up when stored.
 *
 * It is common in many programming languages to provide a way to escape special characters in strings. For example, C,
 * JavaScript, Perl, Python, and even PHP handle special characters in very similar ways.
 *
 * However, it is important to realize the difference between the number of characters in the code representation of the
 * string literal and the number of characters in the in-memory string itself.
 *
 * For example:
 *
 * "" is 2 characters of code (the two double quotes), but the string contains zero characters.
 * "abc" is 5 characters of code, but 3 characters in the string data.
 * "aaa\"aaa" is 10 characters of code, but the string itself contains six "a" characters and a single, escaped quote
 * character, for a total of 7 characters in the string data.
 * "\x27" is 6 characters of code, but the string itself contains just one - an apostrophe ('), escaped using
 * hexadecimal notation.
 * Santa's list is a file that contains many double-quoted string literals, one on each line. The only escape sequences
 * used are \\ (which represents a single backslash), \" (which represents a lone double-quote character), and \x plus
 * two hexadecimal characters (which represents a single character with that ASCII code).
 *
 * Disregarding the whitespace in the file, what is the number of characters of code for string literals minus the
 * number of characters in memory for the values of the strings in total for the entire file?
 *
 * For example, given the four strings above, the total number of characters of string code (2 + 5 + 10 + 6 = 23) minus
 * the total number of characters in memory for string values (0 + 3 + 7 + 1 = 11) is 23 - 11 = 12.
 *
 * --- Part Two ---
 * Now, let's go the other way. In addition to finding the number of characters of code, you should now encode each code
 * representation as a new string and find the number of characters of the new encoded representation, including the
 * surrounding double quotes.
 *
 * For example:
 *
 * "" encodes to "\"\"", an increase from 2 characters to 6.
 * "abc" encodes to "\"abc\"", an increase from 5 characters to 9.
 * "aaa\"aaa" encodes to "\"aaa\\\"aaa\"", an increase from 10 characters to 16.
 * "\x27" encodes to "\"\\x27\"", an increase from 6 characters to 11.
 * Your task is to find the total number of characters to represent the newly encoded strings minus the number of
 * characters of code in each original string literal. For example, for the strings above, the total encoded length
 * (6 + 9 + 16 + 11 = 42) minus the characters in the original code representation (23, just like in the first part of
 * this puzzle) is 42 - 23 = 19.
 */
public class Day8 {

    /**
     * Given a file string this returns an in-memory representation of it
     * @param str File representation
     * @return In memory representation of it
     */
    private String convertToMemoryString(String str) {
        String memoryString = str.substring(1, str.length() - 1)
                //This cheats as it encodes everything to 1, since we only need to know the lengths
                //This avoids the situation where the replacement is performed incrementally and it goes from:
                // \\x27 -> \x27 => '
                //Wheras it should only go to \x27
                .replace("\\\\", "1")
                .replace("\\\"", "1")
                .replaceAll("\\\\x..", "1")
        ;

        return memoryString;
    }

    /**
     * Given a file string this will encode it again and return that new file encoded string
     * @param str File string
     * @return Encoded file string
     */
    private String encodeToFileString(String str) {
        String encodedString = "\"" +
                //A similar cheat to above. To avoid the problem of converting it incrementally causing:
                // "foobar" -> \"foobar\" -> \\"foobar\\"
                // instead of:
                // "foobar" -> \"foobar\"
                // We use a "fake" replacement of 11 causing the string to be the correct length
                // Both of the solutions here demonstrate how coding to the problem and not making a generic solution
                // can be faster and sometimes wanted. Since the requirement is to determine the difference in length
                // it is fine to then code it returning garbage contents, since the length is the only thing we want
                // If you wanted the contents, iterating over the string replacing it bit by bit would be more useful
                str.replace("\"", "11")
                   .replace("\\", "11")
                + "\"";

        return encodedString;
    }

    /**
     * Given a list of input strings this will calculate the difference in size between the file string and the
     * memory representation
     * @param fileStrings List of strings representing data in a file format
     * @return The difference in size between the string representation and the memory representation
     */
    public int solvePartOne(List<String> fileStrings) {
        int fileSize = 0;
        int memorySize = 0;
        for(String str : fileStrings) {
            String newStr = this.convertToMemoryString(str);
            fileSize += str.length();
            memorySize += newStr.length();
        }

        return fileSize - memorySize;
    }

    /**
     * Given a list of strings this will encode them into the file format and return the difference between the encoded
     * size and the original file size
     * @param fileStrings A list of strings from an encoded file representation
     * @return The size difference between the encoded size and the original file size
     */
    public int solvePartTwo(List<String> fileStrings) {
        int fileSize = 0;
        int newlyEncodedFileSize = 0;
        for(String str : fileStrings) {
            String newStr = this.encodeToFileString(str);
            fileSize += str.length();
            newlyEncodedFileSize += newStr.length();
        }

        return newlyEncodedFileSize - fileSize;
    }

    public static void main(String[] args) {
        List<String> fileStrings = ProblemLoader.loadProblemIntoStringArray(2015, 8);
        Day8 d = new Day8();
        long difference = d.solvePartOne(fileStrings);
        System.out.println("The difference between file and memory strings is " + difference);
        long differencePartTwo = d.solvePartTwo(fileStrings);
        System.out.println("The difference between file and encoded file strings is " + differencePartTwo);
    }

}
