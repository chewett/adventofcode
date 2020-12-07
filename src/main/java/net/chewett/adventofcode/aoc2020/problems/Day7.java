package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 7: Handy Haversacks ---
 * You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to grab
 * some food: all flights are currently delayed due to issues in luggage processing.
 *
 * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents;
 * bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently, nobody
 * responsible for these regulations considered how long they would take to enforce!
 *
 * For example, consider the following rules:
 *
 * light red bags contain 1 bright white bag, 2 muted yellow bags.
 * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
 * bright white bags contain 1 shiny gold bag.
 * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
 * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
 * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
 * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
 * faded blue bags contain no other bags.
 * dotted black bags contain no other bags.
 * These rules specify the required contents for 9 bag types. In this example, every faded blue bag is empty, every
 * vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black), and so on.
 *
 * You have a shiny gold bag. If you wanted to carry it in at least one other bag, how many different bag colors would
 * be valid for the outermost bag? (In other words: how many colors can, eventually, contain at least one shiny gold
 * bag?)
 *
 * In the above rules, the following options would be available to you:
 *
 * A bright white bag, which can hold your shiny gold bag directly.
 * A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.
 * A dark orange bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
 * A light red bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.
 * So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4.
 *
 * How many bag colors can eventually contain at least one shiny gold bag? (The list of rules is quite long; make sure
 * you get all of it.)
 *
 * --- Part Two ---
 * It's getting pretty expensive to fly these days - not because of ticket prices, but because of the ridiculous number
 * of bags you need to buy!
 *
 * Consider again your shiny gold bag and the rules from the above example:
 *
 * faded blue bags contain 0 other bags.
 * dotted black bags contain 0 other bags.
 * vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black bags.
 * dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black bags.
 * So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags within it) plus 2 vibrant plum bags (and
 * the 11 bags within each of those): 1 + 1*7 + 2 + 2*11 = 32 bags!
 *
 * Of course, the actual rules have a small chance of going several levels deeper than this example; be sure to count
 * all of the bags, even if the nesting becomes topologically impractical!
 *
 * Here's another example:
 *
 * shiny gold bags contain 2 dark red bags.
 * dark red bags contain 2 dark orange bags.
 * dark orange bags contain 2 dark yellow bags.
 * dark yellow bags contain 2 dark green bags.
 * dark green bags contain 2 dark blue bags.
 * dark blue bags contain 2 dark violet bags.
 * dark violet bags contain no other bags.
 * In this example, a single shiny gold bag must contain 126 other bags.
 *
 * How many individual bags are required inside your single shiny gold bag?
 */
public class Day7 {

    public int solvePartOne(List<String> bagDetails) {
        Map<String, List<String>> bagMap = new HashMap<>();

        for(String s : bagDetails) {
            String[] bagSplit = s.substring(0, s.length() - 2).split(" bags contain ");

            String biggerBag = bagSplit[0].replace(" ", "");
            String[] bagPieces = bagSplit[1].split(", ");
            List<String> bagsThatAreInside = new ArrayList<>();
            for(String bag : bagPieces) {
                String[] finalBagParts = bag.replace("bags", "").replace("bag", "").split(" ");
                if(finalBagParts[0].equals("no")) {

                }else {
                    bagsThatAreInside.add(finalBagParts[0] + " " + finalBagParts[1] + finalBagParts[2]);
                }
            }

            bagMap.put(biggerBag, bagsThatAreInside);
        }

        boolean foundShinyGold = false;
        int containsShinyGold = 0;
        for(Map.Entry<String, List<String>> p : bagMap.entrySet()) {
            List<String> currentBags = new ArrayList<>();
            List<String> newBags = new ArrayList<>();
            currentBags.add("x " + p.getKey());

            foundShinyGold = false;
            while(currentBags.size() > 0 && !foundShinyGold) {
                for(String bagColourNumber : currentBags) {
                    String bagColour = bagColourNumber.split(" ")[1];
                    for(String bagToAdd : bagMap.get(bagColour)) {
                        newBags.add(bagToAdd);
                    }
                }

                currentBags = newBags;
                newBags = new ArrayList<>();

                for(String s : currentBags) {
                    if(s.contains("shinygold")) {
                        foundShinyGold = true;
                    }
                }
            }

            if(foundShinyGold) {
                containsShinyGold++;
                foundShinyGold = false;
            }
        }

        return containsShinyGold;
    }

    public int solvePartTwo(List<String> bagDetails) {
        Map<String, List<String>> bagMap = new HashMap<>();

        for(String s : bagDetails) {
            String[] bagSplit = s.substring(0, s.length() - 2).split(" bags contain ");

            String biggerBag = bagSplit[0].replace(" ", "");
            String[] bagPieces = bagSplit[1].split(", ");
            List<String> bagsThatAreInside = new ArrayList<>();
            for(String bag : bagPieces) {
                String[] finalBagParts = bag.replace("bags", "").replace("bag", "").split(" ");
                if(finalBagParts[0].equals("no")) {

                }else {
                    bagsThatAreInside.add(finalBagParts[0] + " " + finalBagParts[1] + finalBagParts[2]);
                }
            }

            bagMap.put(biggerBag, bagsThatAreInside);
        }

        Map<String, Integer> bagNumbers = new HashMap<>();

        List<String> thingsToRemove = new ArrayList<>();
        for(Map.Entry<String, List<String>> e : bagMap.entrySet()) {
            if(e.getValue().size() == 0) {
                bagNumbers.put(e.getKey(), 0);
                thingsToRemove.add(e.getKey());
            }
        }
        for(String s : thingsToRemove) {
            bagMap.remove(s);
        }

        while(bagNumbers.get("shinygold") == null) {
            thingsToRemove = new ArrayList<>();

            for(Map.Entry<String, List<String>> e : bagMap.entrySet()) {
                int bagsFoundInThis = 0;
                boolean allBagsHaveCount = true;
                for(String bagString : e.getValue()) {
                    String[] bagStringParts = bagString.split(" ");
                    int bagNumberCount = Integer.parseInt(bagStringParts[0]);
                    String bagNewColour = bagStringParts[1];

                    if(bagNumbers.get(bagNewColour) != null) {
                        bagsFoundInThis += ((1 + bagNumbers.get(bagNewColour)) * bagNumberCount);
                    }else{
                        allBagsHaveCount = false;
                    }
                }

                if(allBagsHaveCount) {
                    bagNumbers.put(e.getKey(), bagsFoundInThis);
                    thingsToRemove.add(e.getKey());
                }
            }

            for(String s : thingsToRemove) {
                bagMap.remove(s);
            }
        }

        return bagNumbers.get("shinygold");
    }

    public static void main(String[] args) {
        List<String> bagDetails = ProblemLoader.loadProblemIntoStringArray(2020, 7);

        Day7 d = new Day7();
        int bagCount = d.solvePartOne(bagDetails);
        System.out.println("Bags fitting a gold bag " + bagCount);

        int bagCountTwo = d.solvePartTwo(bagDetails);
        System.out.println("Bags fitting inside a gold bag " + bagCountTwo);
    }

}
