package net.chewett.adventofcode.helpers;

import java.util.ArrayList;
import java.util.List;

public class ObjectCloning {

    /**
     * Takes a list of list of characters and creates an entirely new array from that data to allow editing
     * @param originalList Original list of List of Charcters that you wish to clone
     * @return A newly cloned list not linked to the original
     */
    public static List<List<Character>> createNewClonedList(List<List<Character>> originalList) {
        List<List<Character>> newList = new ArrayList<>();
        for(List<Character> oldRow : originalList) {
            List<Character> newRow = new ArrayList<>(oldRow);
            newList.add(newRow);
        }
        return newList;
    }


}
