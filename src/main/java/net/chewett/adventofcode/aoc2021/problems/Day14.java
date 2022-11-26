package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 14: Extended Polymerization ---
 * The incredible pressures at this depth are starting to put a strain on your submarine. The submarine has
 * polymerization equipment that would produce suitable materials to reinforce the submarine, and the nearby
 * volcanically-active caves should even have the necessary input elements in sufficient quantities.
 *
 * The submarine manual contains instructions for finding the optimal polymer formula; specifically, it offers a polymer
 * template and a list of pair insertion rules (your puzzle input). You just need to work out what polymer would result
 * after repeating the pair insertion process a few times.
 *
 * For example:
 *
 * NNCB
 *
 * CH -> B
 * HH -> N
 * CB -> H
 * NH -> C
 * HB -> C
 * HC -> B
 * HN -> C
 * NN -> C
 * BH -> H
 * NC -> B
 * NB -> B
 * BN -> B
 * BB -> N
 * BC -> B
 * CC -> N
 * CN -> C
 * The first line is the polymer template - this is the starting point of the process.
 *
 * The following section defines the pair insertion rules. A rule like AB -> C means that when elements A and B are
 * immediately adjacent, element C should be inserted between them. These insertions all happen simultaneously.
 *
 * So, starting with the polymer template NNCB, the first step simultaneously considers all three pairs:
 *
 * The first pair (NN) matches the rule NN -> C, so element C is inserted between the first N and the second N.
 * The second pair (NC) matches the rule NC -> B, so element B is inserted between the N and the C.
 * The third pair (CB) matches the rule CB -> H, so element H is inserted between the C and the B.
 * Note that these pairs overlap: the second element of one pair is the first element of the next pair. Also, because
 * all pairs are considered simultaneously, inserted elements are not considered to be part of a pair until the next
 * step.
 *
 * After the first step of this process, the polymer becomes NCNBCHB.
 *
 * Here are the results of a few steps using the above rules:
 *
 * Template:     NNCB
 * After step 1: NCNBCHB
 * After step 2: NBCCNBBBCBHCB
 * After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB
 * After step 4: NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
 * This polymer grows quickly. After step 5, it has length 97; After step 10, it has length 3073. After step 10, B
 * occurs 1749 times, C occurs 298 times, H occurs 161 times, and N occurs 865 times; taking the quantity of the most
 * common element (B, 1749) and subtracting the quantity of the least common element (H, 161) produces 1749 - 161 = 1588.
 *
 * Apply 10 steps of pair insertion to the polymer template and find the most and least common elements in the result.
 * What do you get if you take the quantity of the most common element and subtract the quantity of the least common
 * element?
 *
 * Your puzzle answer was 2587.
 *
 * --- Part Two ---
 * The resulting polymer isn't nearly strong enough to reinforce the submarine. You'll need to run more steps of the
 * pair insertion process; a total of 40 steps should do it.
 *
 * In the above example, the most common element is B (occurring 2192039569602 times) and the least common element is
 * H (occurring 3849876073 times); subtracting these produces 2188189693529.
 *
 * Apply 40 steps of pair insertion to the polymer template and find the most and least common elements in the result.
 * What do you get if you take the quantity of the most common element and subtract the quantity of the least common
 * element?
 */
public class Day14 {

    /**
     * Given the polymer details this parses out the transformations and returns it
     * @param data Polymer data
     * @return Transformations that can be made
     */
    private Map<String, String> getTransformations(List<String> data) {
        Map<String, String> transformations = new HashMap<>();

        int currentLine = 2;
        while(currentLine < data.size()) {
            String[] parts = data.get(currentLine).split(" -> ");
            transformations.put(parts[0], parts[1]);

            currentLine++;
        }
        return transformations;
    }

    /**
     * This is the bruteforce method that works fine with part 1 but part 2 becomes exponentially large so you have
     * to do it another day. It slowly expands the polymer each step until it has reached step 10.
     * @param polymerDetails Details of the polymer and the transformation
     * @return Difference between the smallist and biggest transformation after 10 steps
     */
    public long solvePartOne(List<String> polymerDetails) {
        String currentString = polymerDetails.get(0);
        Map<String, String> transformations = this.getTransformations(polymerDetails);

        int step = 0;
        while(step < 10) {
            //Lets use a string builder to construct the new string
            StringBuilder newString = new StringBuilder();
            newString.append(currentString.charAt(0));
            int currentStringLength = currentString.length();
            for(int i = 0; i < currentStringLength - 1; i++) {
                //Each time, take the string and expand the polymer
                String polymerToReview = currentString.substring(i, i+2);
                newString.append(transformations.get(polymerToReview));
                newString.append(polymerToReview.charAt(1));
            }

            currentString = newString.toString();
            step++;
        }

        //Once we have stepped through creating the string, we are ready to go! Lets count it
        Map<Character, Integer> occurrencesOfChars = new HashMap<>();
        for(int i = 0; i < currentString.length(); i++) {
            char charAtPos = currentString.charAt(i);
            occurrencesOfChars.put(charAtPos, occurrencesOfChars.getOrDefault(charAtPos, 0) + 1);
        }

        //Find the max and min
        int leastNumber = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for(Map.Entry<Character, Integer> e : occurrencesOfChars.entrySet()) {
            int val = e.getValue();
            if(val < leastNumber) {
                leastNumber = val;
            }
            if(val > maxValue) {
                maxValue = val;
            }
        }

        //Return the difference
        return maxValue - leastNumber;
    }

    /**
     * Expand out the polymer 40 steps and then work out the difference between the least and most occurring character
     *
     * This is the "smarter" step compared to part 1 as actually all you need to keep track is the pairings and the
     * occurences of them. Each pairing expanded will create two new pairings and as long as you keep track of that
     * you are sorted. Quite a neat little algorithm!
     *
     * @param polymerDetails Details of the polymer and the transformation
     * @return Difference between the smallist and biggest transformation after 10 steps
     */
    public long solvePartTwo(List<String> polymerDetails) {
        String currentString = polymerDetails.get(0);
        Map<String, String> transformations = this.getTransformations(polymerDetails);

        //Map the occurrences of the characters at the start
        Map<Character, Long> occurrencesOfChars = new HashMap<>();
        for(int i = 0; i < currentString.length(); i++) {
            occurrencesOfChars.put(currentString.charAt(i), occurrencesOfChars.getOrDefault(currentString.charAt(i), 0L) + 1);
        }

        //Map the occurences of each of the substring parts
        Map<String, Long> countMapping = new HashMap<>();
        for(int i = 0; i < currentString.length() - 1; i++) {
            String part = currentString.substring(i, i+2);
            countMapping.put(part, countMapping.getOrDefault(part, 0L) + 1);
        }



        int step = 0;
        while(step < 40) {
            Map<String, Long> newMapping = new HashMap<>();
            //Each time we step we split every pair into two new pairs and update all the values
            for(Map.Entry<String, Long> e : countMapping.entrySet()) {
                String newPairA = e.getKey().charAt(0) + transformations.get(e.getKey());
                String newPairB = transformations.get(e.getKey()) + e.getKey().charAt(1);
                newMapping.put(newPairA, newMapping.getOrDefault(newPairA, 0L) + e.getValue());
                newMapping.put(newPairB, newMapping.getOrDefault(newPairB, 0L) + e.getValue());
                occurrencesOfChars.put(transformations.get(e.getKey()).charAt(0), occurrencesOfChars.getOrDefault(transformations.get(e.getKey()).charAt(0), 0L) + e.getValue());
            }

            countMapping = newMapping;
            step++;
        }

        long leastNumber = Long.MAX_VALUE;
        long maxValue = Long.MIN_VALUE;
        for(Map.Entry<Character, Long> e : occurrencesOfChars.entrySet()) {
            long val = e.getValue();
            if(val < leastNumber) {
                leastNumber = val;
            }
            if(val > maxValue) {
                maxValue = val;
            }
        }

        return maxValue - leastNumber;
    }

    public static void main(String[] args) {
        List<String> polymerDetails = ProblemLoader.loadProblemIntoStringArray(2021, 14);

        Day14 d = new Day14();
        long partOne = d.solvePartOne(polymerDetails);
        System.out.println("After 10 steps the difference between the most and least common element is " + partOne);

        long partTwo = d.solvePartTwo(polymerDetails);
        System.out.println("After 40 steps the difference between the most and least common element is " + partTwo);
    }


}
