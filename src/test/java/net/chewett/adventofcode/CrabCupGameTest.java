package net.chewett.adventofcode;

import net.chewett.adventofcode.aoc2020.CrabCupGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CrabCupGameTest {

    public static List<Integer> getExampleInput() {
        List<Integer> l = new LinkedList<>();
        l.add(3);
        l.add(8);
        l.add(9);
        l.add(1);
        l.add(2);
        l.add(5);
        l.add(4);
        l.add(6);
        l.add(7);

        return l;
    }

    @Test
    public void testExampleInputOneMove() {
        CrabCupGame cc = new CrabCupGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(1);
        Assert.assertEquals(54673289L, output);
    }

    @Test
    public void testExampleInputTwoMoves() {
        CrabCupGame cc = new CrabCupGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(2);
        Assert.assertEquals(32546789L, output);
    }

    @Test
    public void testExampleInputThreeMoves() {
        CrabCupGame cc = new CrabCupGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(3);
        Assert.assertEquals(34672589L, output);
    }

    @Test
    public void testExampleInputTenMoves() {
        CrabCupGame cc = new CrabCupGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(10);
        Assert.assertEquals(92658374L, output);
    }

    @Test
    public void testExampleInputOneHundredMoves() {
        CrabCupGame cc = new CrabCupGame(CrabCupGameTest.getExampleInput());
        long output = cc.runGameForMoves(100);
        Assert.assertEquals(67384529L, output);
    }

}
