package net.chewett.adventofcode.aoc2020;

import java.util.LinkedList;
import java.util.List;

/**
 * Class used to run a game of CrabCups, This uses indexOf() on a list which means it is unsuitable for a large number
 * of cups and moves.
 */
public class CrabCupGame {

    List<Integer> initialCupOrder;
    int maxNumber;

    public CrabCupGame(List<Integer> initialCupOrder) {
        this.initialCupOrder = initialCupOrder;
        this.maxNumber = 0;
        for(int i : this.initialCupOrder) {
            if(this.maxNumber < i) {
                this.maxNumber = i;
            }
        }
    }

    /**
     * Runs the game for a specific number of moves and returns order after running
     * @param movesToRun The moves to run the game for
     * @return A long representing the position of the cups
     */
    public long runGameForMoves(int movesToRun) {
        int currentMoveNumber = 1;
        int currentCupIndex = 0;

        //Here we start the game looping over the number of moves.
        //We use a linked list here to make the removal much faster than an ArrayList or similar
        List<Integer> curCups = new LinkedList<>(this.initialCupOrder);
        while(currentMoveNumber <= movesToRun) {
            int currentCupValue = curCups.get(currentCupIndex);

            //Remove the three cups we are going to tweak
            int cupIndexToRemove = currentCupIndex+1;
            if(cupIndexToRemove >= curCups.size()) { cupIndexToRemove = 0; }
            int c1 = curCups.remove(cupIndexToRemove);
            if(cupIndexToRemove >= curCups.size()) { cupIndexToRemove = 0; }
            int c2 = curCups.remove(cupIndexToRemove);
            if(cupIndexToRemove >= curCups.size()) { cupIndexToRemove = 0; }
            int c3 = curCups.remove(cupIndexToRemove);

            //Look for the cup with the value of the current minus one
            int lookingForCupWithValue = currentCupValue-1;
            if(lookingForCupWithValue < 1) {
                lookingForCupWithValue = this.maxNumber;
            }
            //See if we can find the right value
            while(lookingForCupWithValue == c1 || lookingForCupWithValue == c2 || lookingForCupWithValue == c3) {
                lookingForCupWithValue--;
                if(lookingForCupWithValue < 1) {
                    lookingForCupWithValue = this.maxNumber;
                }
            }

            //This is the slow part which becomes too slow when we have many cups and many moves
            int cupInsertionIndex = curCups.indexOf(lookingForCupWithValue) + 1;
            if(cupInsertionIndex >= curCups.size()) {
                cupInsertionIndex = 0;
            }
            //Add these new cups in
            curCups.add(cupInsertionIndex, c3);
            curCups.add(cupInsertionIndex, c2);
            curCups.add(cupInsertionIndex, c1);

            //This is also incredibly slow when we expand to many cups and many moves
            currentCupIndex = curCups.indexOf(currentCupValue) + 1;
            if(currentCupIndex >= curCups.size()) {
                currentCupIndex = 0;
            }
            currentMoveNumber++;
        }

        return Long.parseLong(this.convertListToStringOutput(curCups));
    }

    /**
     * Small helper method to convert a list of numbers to a string output
     * @param cups The list of cups to convert to a string
     * @return A string representing the ordering of the cups
     */
    private String convertListToStringOutput(List<Integer> cups) {
        StringBuilder back = new StringBuilder();
        StringBuilder front = new StringBuilder();
        boolean addToBack = true;
        for(int i = 0; i < cups.size(); i++) {
            if(cups.get(i) == 1) {
                addToBack = false;
            }else if(addToBack){
                back.append(cups.get(i));
            }else{
                front.append(cups.get(i));
            }
        }
        return front.append(back).toString();

    }



}
