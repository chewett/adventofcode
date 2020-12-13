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
}
