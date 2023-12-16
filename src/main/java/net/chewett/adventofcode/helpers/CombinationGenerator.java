package net.chewett.adventofcode.helpers;

import java.util.ArrayList;
import java.util.List;

public class CombinationGenerator {

    /**
     * Given a list of int elements this will return a list of lists holding all combinations of the items
     * This will create the combinations by "turning" the values on and off and iteratively creating the list of lists.
     * @param elements
     * @return
     */
    public static List<List<Integer>> createCombinationsWhereEachElementCanOrCannotExist(List<Integer> elements) {
        List<List<Integer>> currentListOfCombinations = new ArrayList<>();
        //Add an empty list to start off appending to
        currentListOfCombinations.add(new ArrayList<>());
        for(Integer i : elements) {
            List<List<Integer>> newListOfLists = new ArrayList<>();
            for(List<Integer> listToAddNewElementTo : currentListOfCombinations) {
                List<Integer> newListWithNoAdditionalElements = new ArrayList<>(listToAddNewElementTo);
                List<Integer> newListWithNewElement = new ArrayList<>(listToAddNewElementTo);
                newListWithNewElement.add(i);

                newListOfLists.add(newListWithNoAdditionalElements);
                newListOfLists.add(newListWithNewElement);
            }
            currentListOfCombinations = newListOfLists;
        }

        return currentListOfCombinations;
    }

    /**
     * Given a string, a replacement character, and possible characters this should be this will generate all possibilities
     * @param possibleString String to replace items
     * @param characterToReplace Character to replace inside the initial string
     * @param possibilites All the possibilities
     * @return A list of strings representing all the possibilities
     */
    public static List<String> createPossibilitiesOfStrings(String possibleString, char characterToReplace, char[] possibilites) {
        List<String> possibilities = new ArrayList<>();
        possibilities.add("");

        //Loop over the strings one by one
        for(int i = 0; i < possibleString.length(); i++) {
            List<String> newPossibilities = new ArrayList<>();
            //Keep adding the next string to all of the possibilities
            if(possibleString.charAt(i) == characterToReplace) {
                //If its a ? then we add both possibilities
                for(String pos : possibilities) {
                    for(char newC : possibilites) {
                        newPossibilities.add(pos + newC);
                    }
                }
            }else{
                //If not then just add the character to each possibility
                for(String pos : possibilities) {
                    newPossibilities.add(pos + possibleString.charAt(i));
                }
            }
            possibilities = newPossibilities;
        }

        return possibilities;
    }

}
