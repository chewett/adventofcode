package net.chewett.adventofcode.aoc2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that uses a Linked List implementation to create a CrabCups game, this is very similar to the CrabCupsGame class
 * but since it uses a linked list it is performant for much higher numbers of cups
 */
public class CrabCupLinkedListGame {

    List<Integer> initialCupOrder;
    int maxNumber;

    public CrabCupLinkedListGame(List<Integer> initialCupOrder) {
        this.initialCupOrder = initialCupOrder;
        this.maxNumber = 0;
        for(int i : this.initialCupOrder) {
            if(this.maxNumber < i) {
                this.maxNumber = i;
            }
        }
    }

    /**
     * Run the game for the number of moves and return the value of the two cups clockwise from one multiplied
     * @param movesToRun The number of moves to run
     * @return The value of the two numbers clockwise from one multiplied together
     */
    public long runGameForMoves(int movesToRun) {
        int currentMoveNumber = 1;

        //Here we store a map of number to CrabCup Object
        Map<Integer, CrabCup> ccList = new HashMap<>();
        CrabCup prev = null;
        CrabCup currPointer = null;
        //And create the Crab Cups setting the pointers up
        for(int i : this.initialCupOrder) {
            CrabCup newCc = new CrabCup(i);
            ccList.put(i, newCc);

            if(currPointer == null) {
                currPointer = newCc;
            }

            if(prev != null) {
                prev.setNext(newCc);
            }
            prev = newCc;
        }

        //Sets up the circular list so that the last one points to the first
        prev.setNext(currPointer);

        //Right lets start the loop
        while(currentMoveNumber <= movesToRun) {
            int currentCupValue = currPointer.getValue();

            //Get the three cups we want to remove
            CrabCup ccStartRemovalPoint = currPointer.getNext();
            CrabCup ccMiddleRemovalPoint = ccStartRemovalPoint.getNext();
            CrabCup ccEndRemovalPoint = ccMiddleRemovalPoint.getNext();

            //Join the start of the missing content to the end of the missing content, all "sealed up"
            currPointer.setNext(ccEndRemovalPoint.getNext());

            //Work out what cup we are looking for
            int lookingForCupWithValue = currentCupValue-1;
            if(lookingForCupWithValue < 1) {
                lookingForCupWithValue = this.maxNumber;
            }
            //Make sure we are looking for the thing "before" the removed items
            while(lookingForCupWithValue == ccStartRemovalPoint.getValue() || lookingForCupWithValue == ccMiddleRemovalPoint.getValue()
                    || lookingForCupWithValue == ccEndRemovalPoint.getValue()) {
                lookingForCupWithValue--;
                if(lookingForCupWithValue < 1) {
                    lookingForCupWithValue = this.maxNumber;
                }
            }

            //Now this is the fast bit that we were previously using indexOf for, we can use the ccList to immediately
            // get the item with the specific value
            CrabCup insertionPoint = ccList.get(lookingForCupWithValue);
            //Then we set the end of our three chain we need to splice in, to the next value of the insertion point
            ccEndRemovalPoint.setNext(insertionPoint.getNext());
            //And then the insertion point points to the first element in our three chain, and the cups are ordered again
            insertionPoint.setNext(ccStartRemovalPoint);

            currPointer = currPointer.getNext();
            currentMoveNumber++;
        }

        return (long) ccList.get(1).getNext().getValue() * ccList.get(1).getNext().getNext().getValue();
    }

}
