package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
