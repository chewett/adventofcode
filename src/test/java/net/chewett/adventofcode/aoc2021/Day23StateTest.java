package net.chewett.adventofcode.aoc2021;

import net.chewett.adventofcode.aoc2021.problems.Day2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day23StateTest {

    private static String initialState = "00BA0CD0BC0DA00";
    private static int startingEnergy = 0;

    @Test
    public void testBasicConstructor() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        Assertions.assertEquals(Day23StateTest.initialState, d.getState());
        Assertions.assertEquals(Day23StateTest.startingEnergy, d.getEnergy());
    }

    @Test
    public void testGetStatesToMoveToForStateNoMoves() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        List<Integer> newStates = d.getStatesToMoveToForState(3);
        Assertions.assertEquals(0, newStates.size());
    }

    @Test
    public void testGetStatesToMoveToForStateSimpleMoveToHallway() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        List<Integer> newStates = d.getStatesToMoveToForState(2);
        Assertions.assertEquals(5, newStates.size());
    }

    @Test
    public void testNextStates() {
        Day23State d = new Day23State(Day23StateTest.initialState, Day23StateTest.startingEnergy);

        List<Day23State> newStates = d.getNewStates();
        Assertions.assertNotEquals(0, newStates.size());
    }

}
