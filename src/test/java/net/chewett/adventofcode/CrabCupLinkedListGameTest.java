package net.chewett.adventofcode;

import net.chewett.adventofcode.aoc2020.CrabCupLinkedListGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CrabCupLinkedListGameTest {

    @Test
    public void testExampleInputOneMove() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(1);
        Assertions.assertEquals(54673289L, output);
    }

    @Test
    public void testExampleInputTwoMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(2);
        Assertions.assertEquals(32546789L, output);
    }

    @Test
    public void testExampleInputThreeMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(3);
        Assertions.assertEquals(34672589L, output);
    }

    @Test
    public void testExampleInputTenMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(10);
        Assertions.assertEquals(92658374L, output);
    }

    @Test
    public void testExampleInputOneHundredMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(100);
        Assertions.assertEquals(67384529L, output);
    }

}
