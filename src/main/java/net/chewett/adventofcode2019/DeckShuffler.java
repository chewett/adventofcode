package net.chewett.adventofcode2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckShuffler {

    public static void initDeckWithNumberOfNumbers(List<Integer> spaceDeck, int numOfCards) {
        for(int i = 0; i < numOfCards; i++) {
            spaceDeck.add(i);
        }
    }

    public static void cutCards(List<Integer> spaceDeck, int cutNumber) {
        if(cutNumber < 0) {
            int lastNumbers = Math.abs(cutNumber);
            List<Integer> newList = new ArrayList<>();
            for(int i = (spaceDeck.size() - lastNumbers); i < spaceDeck.size(); i++) {
                newList.add(spaceDeck.get(i));
            }
            for(int i = 0; i < (spaceDeck.size() - lastNumbers); i++) {
                newList.add(spaceDeck.get(i));
            }
            while(spaceDeck.size() > 0) {
                spaceDeck.remove(0);
            }
            for(int i = 0; i < newList.size(); i++) {
                spaceDeck.add(newList.get(i));
            }
        }else{
            for(int i = 0; i < cutNumber; i++) {
                int fromTop = spaceDeck.remove(0);
                spaceDeck.add(fromTop);
            }
        }
    }

    public static void dealIntoNewStack(List<Integer> spaceDeck) {
        Collections.reverse(spaceDeck);
    }

    public static void dealWithIncrement(List<Integer> spaceDeck, int increment) {
        int[] newList = new int[spaceDeck.size()];
        boolean[] setList = new boolean[spaceDeck.size()];
        for(int i = 0; i < setList.length; i++) {
            setList[i] = false;
        }

        int curPos = 0;
        while(spaceDeck.size() > 0) {
            newList[curPos] = spaceDeck.get(0);
            setList[curPos] = true;
            curPos += increment;
            spaceDeck.remove(0);

            if(curPos > newList.length) {
                curPos -= newList.length;
            }
        }

        for(int i = 0; i < newList.length; i++) {
            spaceDeck.add(newList[i]);
        }
    }

}
