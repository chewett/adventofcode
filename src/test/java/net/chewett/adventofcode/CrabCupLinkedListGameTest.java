package net.chewett.adventofcode;

import net.chewett.adventofcode.aoc2020.CrabCupLinkedListGame;
import org.junit.Assert;
import org.junit.Test;


public class CrabCupLinkedListGameTest {

    @Test
    public void testExampleInputOneMove() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(1);
        Assert.assertEquals(54673289L, output);
    }

    @Test
    public void testExampleInputTwoMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(2);
        Assert.assertEquals(32546789L, output);
    }

    @Test
    public void testExampleInputThreeMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(3);
        Assert.assertEquals(34672589L, output);
    }

    @Test
    public void testExampleInputTenMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(10);
        Assert.assertEquals(92658374L, output);
    }

    @Test
    public void testExampleInputOneHundredMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(100);
        Assert.assertEquals(67384529L, output);
    }

}
