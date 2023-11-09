package net.chewett.adventofcode.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day23StateTest {

    private static String initialState = "00BA0CD0BC0DA00";
    private static int startingEnergy = 0;

    /**
     * Simple setter test to make sure nothing corrupts
     */
    @Test
    public void testBasicConstructor() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        Assertions.assertEquals(Day23StateTest.initialState, d.getState());
        Assertions.assertEquals(Day23StateTest.startingEnergy, d.getEnergy());
    }

    /**
     * Check to see if there are no moves if I pass in something that shouldn't move
     */
    @Test
    public void testGetStatesToMoveToForStateNoMoves() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        List<Integer> newStates = d.getStatesToMoveToForState(3);
        Assertions.assertEquals(0, newStates.size());
    }

    /**
     * Test to make sure there are some simple moves to the hallway if passed in something that should move
     */
    @Test
    public void testGetStatesToMoveToForStateSimpleMoveToHallway() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        List<Integer> newStates = d.getStatesToMoveToForState(2);
        Assertions.assertEquals(7, newStates.size());
    }

    /**
     * Check to make sure that the expected move is generated when starting from a known start
     */
    @Test
    public void testNextStates() {
        String[][] statesToCheck = new String[][] {
                {"00BD0CC0BB0DA00DABDACCA", "00BD0CC0BB00A0DDABDACCA"}, //Move 1
                {"00BD0CC0BB00A0DDABDACCA", "A0BD0CC0BB0000DDABDACCA"}, //Move 2
                {"A0BD0CC0BB0000DDABDACCA", "A0BD0CC00B000BDDABDACCA"} // Move 3
        };

        for(String[] beforeAfterString : statesToCheck) {
            Day23State d = new Day23State(beforeAfterString[0], 0);

            List<Day23State> newStates = d.getNewStates();
            Set<String> stringStates = new HashSet<>();
            for(Day23State st : newStates) {
                stringStates.add(st.getState());
            }
            Assertions.assertTrue(stringStates.contains(beforeAfterString[1]));
        }

    }

}
