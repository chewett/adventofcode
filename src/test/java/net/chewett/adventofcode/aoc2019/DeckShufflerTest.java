package net.chewett.adventofcode.aoc2019;

import net.chewett.adventofcode.aoc2019.DeckShuffler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DeckShufflerTest {

    @Test
    public void testInitDeck() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10);
        Assertions.assertEquals(10, spaceDeck.size());
        for(int i = 0; i < 10; i++) {
            Assertions.assertEquals(i, (int)spaceDeck.get(i));
        }
    }


    @Test
    public void testShuffleOntoNewDeck() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10);

        DeckShuffler.dealIntoNewStack(spaceDeck);

        int indexNum = 0;
        for(int i = 9; i >= 0; i--) {
            Assertions.assertEquals(i, (int)spaceDeck.get(indexNum));
            indexNum++;
        }
    }

    @Test
    public void testCutNCardsPositive() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10);

        DeckShuffler.cutCards(spaceDeck, 3);

        int[] expectedNumbers = new int[] {3,4,5,6,7,8,9,0,1,2};
        for(int i = 0; i < 10; i++) {
            Assertions.assertEquals(expectedNumbers[i], (int)spaceDeck.get(i));
        }
    }

    @Test
    public void testCutNCardsNegative() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10);

        DeckShuffler.cutCards(spaceDeck, -4);

        int[] expectedNumbers = new int[] {6,7,8,9,0,1,2,3,4,5};
        for(int i = 0; i < 10; i++) {
            Assertions.assertEquals(expectedNumbers[i], (int)spaceDeck.get(i));
        }
    }

    @Test
    public void testIncrementNCards() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10);

        DeckShuffler.dealWithIncrement(spaceDeck, 3);

        int[] expectedNumbers = new int[] {0,7,4,1,8,5,2,9,6,3};
        for(int i = 0; i < 10; i++) {
            Assertions.assertEquals(expectedNumbers[i], (int)spaceDeck.get(i));
        }
    }

    @Test
    public void testMultipleStepsOne() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10);

        DeckShuffler.dealWithIncrement(spaceDeck, 7);
        DeckShuffler.dealIntoNewStack(spaceDeck);
        DeckShuffler.dealIntoNewStack(spaceDeck);

        int[] expectedNumbers = new int[] {0,3,6,9,2,5,8,1,4,7};
        for(int i = 0; i < 10; i++) {
            Assertions.assertEquals(expectedNumbers[i], (int)spaceDeck.get(i));
        }
    }



}
