package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 8: Seven Segment Search ---
 * You barely reach the safety of the cave when the whale smashes into the cave mouth, collapsing it. Sensors indicate
 * another exit to this cave at a much greater depth, so you have no choice but to press on.
 *
 * As your submarine slowly makes its way through the cave system, you notice that the four-digit seven-segment displays
 * in your submarine are malfunctioning; they must have been damaged during the escape. You'll be in a lot of trouble
 * without them, so you'd better figure out what's wrong.
 *
 * Each digit of a seven-segment display is rendered by turning on or off any of seven segments named a through g:
 *
 *   0:      1:      2:      3:      4:
 *  aaaa    ....    aaaa    aaaa    ....
 * b    c  .    c  .    c  .    c  b    c
 * b    c  .    c  .    c  .    c  b    c
 *  ....    ....    dddd    dddd    dddd
 * e    f  .    f  e    .  .    f  .    f
 * e    f  .    f  e    .  .    f  .    f
 *  gggg    ....    gggg    gggg    ....
 *
 *   5:      6:      7:      8:      9:
 *  aaaa    aaaa    aaaa    aaaa    aaaa
 * b    .  b    .  .    c  b    c  b    c
 * b    .  b    .  .    c  b    c  b    c
 *  dddd    dddd    ....    dddd    dddd
 * .    f  e    f  .    f  e    f  .    f
 * .    f  e    f  .    f  e    f  .    f
 *  gggg    gggg    ....    gggg    gggg
 * So, to render a 1, only segments c and f would be turned on; the rest would be off. To render a 7, only segments a,
 * c, and f would be turned on.
 *
 * The problem is that the signals which control the segments have been mixed up on each display. The submarine is still
 * trying to display numbers by producing output on signal wires a through g, but those wires are connected to segments
 * randomly. Worse, the wire/segment connections are mixed up separately for each four-digit display! (All of the digits
 * within a display use the same connections, though.)
 *
 * So, you might know that only signal wires b and g are turned on, but that doesn't mean segments b and g are turned on:
 * the only digit that uses two segments is 1, so it must mean segments c and f are meant to be on. With just that
 * information, you still can't tell which wire (b/g) goes to which segment (c/f). For that, you'll need to collect more
 * information.
 *
 * For each display, you watch the changing signals for a while, make a note of all ten unique signal patterns you see,
 * and then write down a single four digit output value (your puzzle input). Using the signal patterns, you should be
 * able to work out which pattern corresponds to which digit.
 *
 * For example, here is what you might see in a single entry in your notes:
 *
 * acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
 * cdfeb fcadb cdfeb cdbaf
 * (The entry is wrapped here to two lines so it fits; in your notes, it will all be on a single line.)
 *
 * Each entry consists of ten unique signal patterns, a | delimiter, and finally the four digit output value. Within an
 * entry, the same wire/segment connections are used (but you don't know what the connections actually are). The unique
 * signal patterns correspond to the ten different ways the submarine tries to render a digit using the current
 * wire/segment connections. Because 7 is the only digit that uses three segments, dab in the above example means that
 * to render a 7, signal lines d, a, and b are on. Because 4 is the only digit that uses four segments, eafb means that
 * to render a 4, signal lines e, a, f, and b are on.
 *
 * Using this information, you should be able to work out which combination of signal wires corresponds to each of the
 * ten digits. Then, you can decode the four digit output value. Unfortunately, in the above example, all of the digits
 * in the output value (cdfeb fcadb cdfeb cdbaf) use five segments and are more difficult to deduce.
 *
 * For now, focus on the easy digits. Consider this larger example:
 *
 * be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |
 * fdgacbe cefdb cefbgd gcbe
 * edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec |
 * fcgedb cgb dgebacf gc
 * fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef |
 * cg cg fdcagb cbg
 * fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega |
 * efabcd cedba gadfec cb
 * aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga |
 * gecf egdcabf bgf bfgea
 * fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf |
 * gebdcfa ecba ca fadegcb
 * dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf |
 * cefg dcbef fcge gbcadfe
 * bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd |
 * ed bcgafe cdgba cbgef
 * egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg |
 * gbdfcae bgc cg cgb
 * gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc |
 * fgae cfgab fg bagce
 * Because the digits 1, 4, 7, and 8 each use a unique number of segments, you should be able to tell which combinations
 * of signals correspond to those digits. Counting only digits in the output values (the part after | on each line), in
 * the above example, there are 26 instances of digits that use a unique number of segments (highlighted above).
 *
 * In the output values, how many times do digits 1, 4, 7, or 8 appear?
 *
 * Your puzzle answer was 294.
 *
 * --- Part Two ---
 * Through a little deduction, you should now be able to determine the remaining digits. Consider again the first
 * example above:
 *
 * acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
 * cdfeb fcadb cdfeb cdbaf
 * After some careful analysis, the mapping between signal wires and segments only make sense in the following
 * configuration:
 *
 *  dddd
 * e    a
 * e    a
 *  ffff
 * g    b
 * g    b
 *  cccc
 * So, the unique signal patterns would correspond to the following digits:
 *
 * acedgfb: 8
 * cdfbe: 5
 * gcdfa: 2
 * fbcad: 3
 * dab: 7
 * cefabd: 9
 * cdfgeb: 6
 * eafb: 4
 * cagedb: 0
 * ab: 1
 * Then, the four digits of the output value can be decoded:
 *
 * cdfeb: 5
 * fcadb: 3
 * cdfeb: 5
 * cdbaf: 3
 * Therefore, the output value for this entry is 5353.
 *
 * Following this same process for each entry in the second, larger example above, the output value of each entry can be
 * determined:
 *
 * fdgacbe cefdb cefbgd gcbe: 8394
 * fcgedb cgb dgebacf gc: 9781
 * cg cg fdcagb cbg: 1197
 * efabcd cedba gadfec cb: 9361
 * gecf egdcabf bgf bfgea: 4873
 * gebdcfa ecba ca fadegcb: 8418
 * cefg dcbef fcge gbcadfe: 4548
 * ed bcgafe cdgba cbgef: 1625
 * gbdfcae bgc cg cgb: 8717
 * fgae cfgab fg bagce: 4315
 * Adding all of the output values in this larger example produces 61229.
 *
 * For each entry, determine all of the wire/segment connections and decode the four-digit output values. What do you
 * get if you add up all of the output values?
 */
public class Day8 {

    /**
     * Count the length of the string and represent the number it is displaying
     * If the length isnt one of the identifiable ones aka not showint 1, 4, 7, 8 then we just return -1
     * @param stringEntry String representing the segments that are lit
     * @return A number representing the number the segment is showing or -1 if we can't tell
     */
    private int convertToDigit(String stringEntry) {
        if(stringEntry.length() == 2) {
            return 1;
        }else if(stringEntry.length() == 4) {
            return 4;
        }else if(stringEntry.length() == 3) {
            return 7;
        }else if(stringEntry.length() == 7) {
            return 8;
        }

        return -1;
    }

    /**
     * Counts all the segments that we can recognise in the output 
     * @param entries Segment data
     * @return Number of digits we can identify
     */
    public long solvePartOne(List<String> entries) {
        int count = 0;
        for(String entry : entries) {
            String[] parts = entry.split(" \\| ");
            String[] digitsShown = parts[1].split(" ");
            for(String str : digitsShown) {
                int digit = this.convertToDigit(str);
                if(digit != -1) {
                    count++;
                }
            }
        }

        return count;
    }


    /**
     * Given two strings (which are assumed to differ by one character) return the character that exists
     * in one but not the other
     * @param str1 First string to compare
     * @param str2 Second string to compare
     * @return The differing character
     */
    private char diffChars(String str1, String str2) {
        //Force the ordering so the nice variable names make sense
        if(str1.length() < str2.length()) {
            String tmp = str1;
            str1 = str2;
            str2 = tmp;
        }

        Set<Character> moreChars = FormatConversion.convertStringToSet(str1);
        Set<Character> lessChars = FormatConversion.convertStringToSet(str2);

        moreChars.removeAll(lessChars);
        //A hack to get the single item out!
        for(char c : moreChars) {
            return c;
        }

        throw new RuntimeException("Error this function was passed in with no differing chars");
    }

    /**
     * Find the common characters in a string and then return that string of common characters
     * @param str1 First string to compare
     * @param str2 Second string to compare
     * @return Returns the string which includes all the common characters of the two strings
     */
    private String findCommonChars(String str1, String str2) {
        StringBuilder allCommonChars = new StringBuilder();
        Set<Character> set1 = FormatConversion.convertStringToSet(str1);
        set1.retainAll(FormatConversion.convertStringToSet(str2));

        for(Character c : set1) {
            allCommonChars.append(c);
        }

        return allCommonChars.toString();
    }

    /**
     * Sorts a string lexigraphically and then returns the newly sorted string
     * @param str String to sort
     * @return A newly sorted string
     */
    private String reSortStringLexigraphically(String str) {
        List<String> stringParts = new ArrayList<>();
        for(int i = 0; i < str.length(); i++) {
            stringParts.add(""+str.charAt(i));
        }
        Collections.sort(stringParts);
        StringBuilder sb = new StringBuilder();
        for(String newstr : stringParts) {
            sb.append(newstr);
        }
        return sb.toString();
    }


    /**
     * This is the complex one! Given a line entry this will slowly process the data to find the value of the output
     * digit
     * @param entry String line representing the segments
     * @return The value of the final four output segments
     */
    private int getValueOfOutputDigit(String entry) {
        String[] parts = entry.split(" \\| ");
        String[] allDigits = parts[0].split(" ");
        String[] outputDigits = parts[1].split(" ");

        //We want to store a mapping between the setting character and the mixed up character which represents that
        //segment in the entry input
        Map<Character, Character> knownCharMap = new HashMap<>();
        //Store a map between the integer value and the string used to represent it
        Map<Integer, String> knownInts = new HashMap<>();

        //The only tricky ones are the five and six segments so we create a list of them
        List<String> fiveSegments = new ArrayList<>();
        List<String> sixSegments = new ArrayList<>();
        for(String str : allDigits) {
            int value = this.convertToDigit(str);
            if(value != -1) {
                knownInts.put(value, this.reSortStringLexigraphically(str));
            }else if(str.length() == 5) {
                fiveSegments.add(str);
            }else if(str.length() == 6) {
                sixSegments.add(str);
            }
        }

        //A is found by diffing 7 and 1
        char charA = this.diffChars(knownInts.get(7), knownInts.get(1));
        knownCharMap.put('a', charA);


        //G is found by finding the union of all length 5+6
        Set<Character> gFinder = FormatConversion.convertStringToSet(fiveSegments.get(0));
        gFinder.retainAll(FormatConversion.convertStringToSet(fiveSegments.get(1)));
        gFinder.retainAll(FormatConversion.convertStringToSet(fiveSegments.get(2)));
        gFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(0)));
        gFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(1)));
        gFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(2)));
        gFinder.remove(charA);

        char charG = 0;
        for(char c : gFinder) {
            charG = c;
        }
        knownCharMap.put('g', charG);


        //D is found using A+G and the union of length 5
        Set<Character> dFinder = FormatConversion.convertStringToSet(fiveSegments.get(0));
        dFinder.retainAll(FormatConversion.convertStringToSet(fiveSegments.get(1)));
        dFinder.retainAll(FormatConversion.convertStringToSet(fiveSegments.get(2)));
        dFinder.remove(charA);
        dFinder.remove(charG);

        char charD = 0;
        for(char c : dFinder) {
            charD = c;
        }
        knownCharMap.put('d', charD);


        //F is found using A+D+G and the union of 7 and the union of length 6
        Set<Character> fFinder = FormatConversion.convertStringToSet(knownInts.get(7));
        fFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(0)));
        fFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(1)));
        fFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(2)));
        fFinder.remove(charA);

        char charF = 0;
        for(char c : fFinder) {
            charF = c;
        }
        knownCharMap.put('f', charF);


        //B is found using A+G+F and the union of length 6
        Set<Character> bFinder = FormatConversion.convertStringToSet(sixSegments.get(0));
        bFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(1)));
        bFinder.retainAll(FormatConversion.convertStringToSet(sixSegments.get(2)));
        bFinder.remove(charA);
        bFinder.remove(charG);
        bFinder.remove(charF);

        char charB = 0;
        for(char c : bFinder) {
            charB = c;
        }
        knownCharMap.put('b', charB);


        //C is found using A+F and the union of 7
        Set<Character> cFinder = FormatConversion.convertStringToSet(knownInts.get(7));
        cFinder.remove(charA);
        cFinder.remove(charF);


        char charC = 0;
        for(char c : cFinder) {
            charC = c;
        }
        knownCharMap.put('c', charC);

        //E is 8 minus A B C D F G
        Set<Character> eFinder = FormatConversion.convertStringToSet(knownInts.get(8));
        eFinder.remove(charA);
        eFinder.remove(charB);
        eFinder.remove(charC);
        eFinder.remove(charD);
        eFinder.remove(charF);
        eFinder.remove(charG);

        char charE = 0;
        for(char c : eFinder) {
            charE = c;
        }
        knownCharMap.put('e', charE);

        //Now we know each parts, we can work out what each int is represented by
        for(String str : fiveSegments) {
            String stringToSave = this.reSortStringLexigraphically(str);
            Set<Character> stringParts = FormatConversion.convertStringToSet(str);
            if(!stringParts.contains(charC)) {
                knownInts.put(5, stringToSave);
            }else if(stringParts.contains(charF)) {
                knownInts.put(3, stringToSave);
            }else{
                knownInts.put(2, stringToSave);
            }
        }
        for(String str : sixSegments) {
            String stringToSave = this.reSortStringLexigraphically(str);
            Set<Character> stringParts = FormatConversion.convertStringToSet(str);
            if(!stringParts.contains(charE)) {
                knownInts.put(9, stringToSave);
            }else if(stringParts.contains(charD)) {
                knownInts.put(6, stringToSave);
            }else{
                knownInts.put(0, stringToSave);
            }
        }

        //Now we reverse map above and get a mapping of string to integer
        Map<String, Integer> stringWorth = new HashMap<>();
        for(Map.Entry<Integer, String> e : knownInts.entrySet()) {
            stringWorth.put(e.getValue(), e.getKey());
        }

        //And then we can work out the final output string of four digits
        StringBuilder sbFinalValue = new StringBuilder();
        for(String digit : outputDigits) {
            sbFinalValue.append(stringWorth.get(this.reSortStringLexigraphically(digit)));
        }

        return Integer.parseInt(sbFinalValue.toString());
    }

    /**
     * Part two just calls the getValueOfOutputDigit function for each line
     * @param entries List of entries representing the segments
     * @return Final summed output value
     */
    public long solvePartTwo(List<String> entries) {
        int outputValuePoints = 0;
        for(String entry : entries) {
            outputValuePoints += this.getValueOfOutputDigit(entry);
        }

        return outputValuePoints;
    }

    public static void main(String[] args) {
        List<String> entries = ProblemLoader.loadProblemIntoStringArray(2021, 8);

        Day8 d = new Day8();
        long partOne = d.solvePartOne(entries);
        System.out.println("The number of segments I can identify is " + partOne);

        long partTwo = d.solvePartTwo(entries);
        System.out.println("If I add all the output values up I get " + partTwo);
    }

}
