package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.aoc2020.Bag;
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
        List<Bag> bags = new ArrayList<>();
        for(String bagString : bagDetails) {
            bags.add(new Bag(bagString));
        }

        //Create a map of which bag contains which bags
        Map<String, List<String>> bagMap = new HashMap<>();
        for(Bag b : bags) {
            List<String> allBags = new ArrayList<>();
            for(Map.Entry<String, Integer> bagInsideBag : b.getContainingBags().entrySet()) {
                allBags.add(bagInsideBag.getKey());
            }
            bagMap.put(b.getBagName(), allBags);
        }

        boolean foundShinyGold;
        int containsShinyGold = 0;
        //Loop over each bag type and check to see if there (eventually) is a shiny bag inside of it
        for(Map.Entry<String, List<String>> p : bagMap.entrySet()) {
            List<String> currentBags = new ArrayList<>();
            List<String> newBags = new ArrayList<>();
            //Add the current set of bags
            currentBags.add(p.getKey());

            foundShinyGold = false;
            //Keep iterating over the bag list until we have exhausted all bags inside this bag
            while(currentBags.size() > 0 && !foundShinyGold) {
                //Add in all the bags in the current bag set
                for(String bagColour : currentBags) {
                    newBags.addAll(bagMap.get(bagColour));
                }

                //Then set that equal to the current bags, and reset the new bags
                currentBags = newBags;
                newBags = new ArrayList<>();

                //If any of the current bags now contain shiny gold, stop searching for this instance.
                if(currentBags.contains("shiny gold")) {
                    foundShinyGold = true;
                    break;
                }
            }

            if(foundShinyGold) {
                containsShinyGold++;
            }
        }

        return containsShinyGold;
    }

    public int solvePartTwo(List<String> bagDetails) {
        List<Bag> bags = new ArrayList<>();
        //Hold a map of bagname -> bag object to make it faster to find bags we are interested in
        Map<String, Bag> bagMap = new HashMap<>();
        //We will create a list of bags we want to find the size for. Not all of them will be calculated as we will stop
        //when we have the value of the shiny gold bag.
        List<String> bagsToCalculateSizeFor = new ArrayList<>();
        for(String bagString : bagDetails) {
            //Create the bag object, store it in the map, and add it to the list of things to calculate
            Bag newBag = new Bag(bagString);
            bags.add(newBag);
            bagMap.put(newBag.getBagName(), newBag);
            bagsToCalculateSizeFor.add(newBag.getBagName());
        }

        //We will store a mapping of bag name -> number of bags it contains for faster lookups
        Map<String, Integer> numberOfBagsInBag = new HashMap<>();
        //Keep track of bags we have already calculated so we only iterate over the ones with no value
        List<String> bagsToRemoveForCalculation = new ArrayList<>();

        //Keep running until we have found the number of bags inside the shiny gold bag
        while(numberOfBagsInBag.get("shiny gold") == null) {

            //Loop over every bag which we still are trying to calculate
            for(String bagNameToCheck : bagsToCalculateSizeFor) {
                Bag bagToCheck = bagMap.get(bagNameToCheck);

                //If this bag contains no bags then store that value and note it to be removed once the loop is over
                if(bagToCheck.getContainingBags().size() == 0) {
                    numberOfBagsInBag.put(bagToCheck.getBagName(), 0);
                    bagsToRemoveForCalculation.add(bagToCheck.getBagName());
                }else{
                    //Store whether we have found the number of bags each bag contains inside this bag
                    boolean allComponentBagsHaveValues = true;
                    int totalBagsInsideThisBag = 0;
                    for(Map.Entry<String, Integer> component : bagToCheck.getContainingBags().entrySet()) {
                        if(numberOfBagsInBag.containsKey(component.getKey())) {
                            //Keep a running total of the number of nested bags this bag contains.
                            totalBagsInsideThisBag += (1 + numberOfBagsInBag.get(component.getKey())) * component.getValue();
                        }else{
                            //Oh well, cant find a value for this, break out of the loop
                            allComponentBagsHaveValues = false;
                            break;
                        }
                    }

                    //If we did find we have a value for every bag, then store it and mark it for removal
                    if(allComponentBagsHaveValues) {
                        numberOfBagsInBag.put(bagToCheck.getBagName(), totalBagsInsideThisBag);
                        bagsToRemoveForCalculation.add(bagToCheck.getBagName());
                    }
                }
            }

            //Remove all the bags that we have found values for and iterate again.
            //This loop will end once we have found a size for the shiny gold bag
            bagsToCalculateSizeFor.removeAll(bagsToRemoveForCalculation);
            bagsToRemoveForCalculation = new ArrayList<>();
        }

        return numberOfBagsInBag.get("shiny gold");
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
