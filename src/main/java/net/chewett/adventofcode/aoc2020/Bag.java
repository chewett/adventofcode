package net.chewett.adventofcode.aoc2020;

import java.util.HashMap;
import java.util.Map;

/**
 * Bag holds both the name of the bag and how many of each bag it holds (if any)
 */
public class Bag {

    String bagName;
    Map<String, Integer> containingBags;

    public Bag(String bagString) {
        //Split on bags contain so the left side is the bag colour and right side is what it contains (if anything)
        String[] colourAndContentsString = bagString.split(" bags contain ");
        bagName = colourAndContentsString[0];
        containingBags = new HashMap<>();

        String containingBagsString = colourAndContentsString[1];
        //Remove the . at the end
        containingBagsString = containingBagsString.substring(0, containingBagsString.length() - 1);

        //If the string is "no other bags" then there are no containing items so only run processing if its not equal
        if(!containingBagsString.equals("no other bags")) {
            //Split on the comma, if there is only one bag inside this one then it will still return an array with one element
            String[] listOfIncludedBags = containingBagsString.split(", ");
            for(String bagToParse : listOfIncludedBags) {
                //Now split on the spaces with a limit of 2, so the first element is the number
                //and second is the colour plus "bags" or "bag"
                String[] bagStringElements = bagToParse.split(" ", 2);
                containingBags.put(
                    //Remove " bags" or " bag" from the colour part so it only includes the colour
                    bagStringElements[1].replace(" bags", "").replace(" bag", ""),
                    //Convert the number to an integer and store it
                    Integer.parseInt(bagStringElements[0])
                );
            }
        }
    }

    public String getBagName() {
        return bagName;
    }

    public Map<String, Integer> getContainingBags() {
        return containingBags;
    }
}
