package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 19: Linen Layout ---
 * Today, The Historians take you up to the hot springs on Gear Island! Very suspiciously, absolutely nothing goes
 * wrong as they begin their careful search of the vast field of helixes.
 *
 * Could this finally be your chance to visit the onsen next door? Only one way to find out.
 *
 * After a brief conversation with the reception staff at the onsen front desk, you discover that you don't have the
 * right kind of money to pay the admission fee. However, before you can leave, the staff get your attention.
 * Apparently, they've heard about how you helped at the hot springs, and they're willing to make a deal: if you can
 * simply help them arrange their towels, they'll let you in for free!
 *
 * Every towel at this onsen is marked with a pattern of colored stripes. There are only a few patterns, but for any
 * particular pattern, the staff can get you as many towels with that pattern as you need. Each stripe can be white (w),
 * blue (u), black (b), red (r), or green (g). So, a towel with the pattern ggr would have a green stripe, a green
 * stripe, and then a red stripe, in that order. (You can't reverse a pattern by flipping a towel upside-down, as that
 * would cause the onsen logo to face the wrong way.)
 *
 * The Official Onsen Branding Expert has produced a list of designs - each a long sequence of stripe colors - that
 * they would like to be able to display. You can use any towels you want, but all of the towels' stripes must exactly
 * match the desired design. So, to display the design rgrgr, you could use two rg towels and then an r towel, an rgr
 * towel and then a gr towel, or even a single massive rgrgr towel (assuming such towel patterns were actually
 * available).
 *
 * To start, collect together all of the available towel patterns and the list of desired designs (your puzzle input).
 * For example:
 *
 * r, wr, b, g, bwu, rb, gb, br
 *
 * brwrr
 * bggr
 * gbbr
 * rrbgbr
 * ubwu
 * bwurrg
 * brgr
 * bbrgwb
 * The first line indicates the available towel patterns; in this example, the onsen has unlimited towels with a single
 * red stripe (r), unlimited towels with a white stripe and then a red stripe (wr), and so on.
 *
 * After the blank line, the remaining lines each describe a design the onsen would like to be able to display. In this
 * example, the first design (brwrr) indicates that the onsen would like to be able to display a black stripe, a red
 * stripe, a white stripe, and then two red stripes, in that order.
 *
 * Not all designs will be possible with the available towels. In the above example, the designs are possible or
 * impossible as follows:
 *
 * brwrr can be made with a br towel, then a wr towel, and then finally an r towel.
 * bggr can be made with a b towel, two g towels, and then an r towel.
 * gbbr can be made with a gb towel and then a br towel.
 * rrbgbr can be made with r, rb, g, and br.
 * ubwu is impossible.
 * bwurrg can be made with bwu, r, r, and g.
 * brgr can be made with br, g, and r.
 * bbrgwb is impossible.
 * In this example, 6 of the eight designs are possible with the available towel patterns.
 *
 * To get into the onsen as soon as possible, consult your list of towel patterns and desired designs carefully.
 * How many designs are possible?
 *
 * --- Part Two ---
 * The staff don't really like some of the towel arrangements you came up with. To avoid an endless cycle of towel
 * rearrangement, maybe you should just give them every possible option.
 *
 * Here are all of the different ways the above example's designs can be made:
 *
 * brwrr can be made in two different ways: b, r, wr, r or br, wr, r.
 *
 * bggr can only be made with b, g, g, and r.
 *
 * gbbr can be made 4 different ways:
 *
 * g, b, b, r
 * g, b, br
 * gb, b, r
 * gb, br
 * rrbgbr can be made 6 different ways:
 *.0
 * r, r, b, g, b, r
 * r, r, b, g, br
 * r, r, b, gb, r
 * r, rb, g, b, r
 * r, rb, g, br
 * r, rb, gb, r
 * bwurrg can only be made with bwu, r, r, and g.
 *
 * brgr can be made in two different ways: b, r, g, r or br, g, r.
 *
 * ubwu and bbrgwb are still impossible.
 *
 * Adding up all of the ways the towels in this example could be arranged into the desired designs
 * yields 16 (2 + 1 + 4 + 6 + 1 + 2).
 *
 * They'll let you into the onsen as soon as you have the list. What do you get if you add up the number of different
 * ways you could make each design?
 */
public class Day19 {

    private List<String> towels = new ArrayList<>();

    /**
     * Find out which of the patterns are solvable using a simple iterative step of slowly trying to add a towel to
     * match up the input until all the input has been matched
     * @param input List of towels and positions the person wants
     * @return Number of puzzles that are solvable
     */
    public long solvePartOne(List<String> input) {
        String[] parts = input.get(0).split(", ");
        this.towels = new ArrayList<>(Arrays.asList(parts));

        long possiblePatterns = 0;
        for(int i = 2; i < input.size(); i++) {
            Set<String> seen = new HashSet<>();

            Stack<String> arrayToMunch = new Stack<>();
            //Put the full input into the munching array
            arrayToMunch.push(input.get(i));
            while(arrayToMunch.size() > 0) {
                String munch = arrayToMunch.pop();
                //If it's gotten to zero length then we found a way to do it so we move to the next pattern
                if(munch.length() == 0) {
                    possiblePatterns++;
                    break;
                }

                //If we saw this before don't continue
                if(seen.contains(munch)) {
                    continue;
                }
                seen.add(munch);

                for(String towel : this.towels) {
                    if(munch.startsWith(towel)) {
                        arrayToMunch.push(munch.substring(towel.length()));
                    }
                }
            }
        }

        return possiblePatterns;
    }

    //Cache previous values we have seen before (memoization)
    private Map<String, Long> ways = new HashMap<>();

    /**
     * Pretty simple recursive function to find out the number of ways we can order The towels
     *
     * We use memoization to store the previous calls to this to speed it up and make it actually finish in a good time
     *
     * @param str String to determine how many ways we can order the towels
     * @return The number of ways we can organise the towels
     */
    public long howManyWays(String str) {
        if(this.ways.containsKey(str)) {
            return this.ways.get(str);
        }

        //base case, if we reached a zero length string then we found one way :)
        if(str.length() == 0) {
            return 1;
        }

        long foundWays = 0;
        for(String towel : this.towels) {
            //Then just loop over the various towels and sum up all the ways to order the lower tiers
            if(str.startsWith(towel)) {
                foundWays += this.howManyWays(str.substring(towel.length()));
            }
        }

        //Then save the value for use later
        this.ways.put(str, foundWays);

        return foundWays;
    }


    /**
     * Works out how many ways to order the towels
     * @param input List of towels and positions the person wants
     * @return Number of ways to organise all the different positions
     */
    public long solvePartTwo(List<String> input) {
        String[] parts = input.get(0).split(", ");
        this.towels = new ArrayList<>(Arrays.asList(parts));

        long possiblePatterns = 0;
        for(int i = 2; i < input.size(); i++) {
            long ways = this.howManyWays(input.get(i));
            possiblePatterns += ways;
        }

        return possiblePatterns;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 19);

        Day19 d = new Day19();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of valid positions is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of ways to order all the towels is " + partTwo);
    }
}


