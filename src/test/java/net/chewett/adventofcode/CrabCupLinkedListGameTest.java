package net.chewett.adventofcode;

import net.chewett.adventofcode.aoc2020.CrabCupLinkedListGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;


public class CrabCupLinkedListGameTest {

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

        for(int i = 10; i <= 1000000; i++) {
            l.add(i);
        }

        return l;
    }

    @Test
    public void testExampleInputTenMillionMoves() {
        CrabCupLinkedListGame cc = new CrabCupLinkedListGame(CrabCupLinkedListGameTest.getExampleInput());
        long output = cc.runGameForMoves(10000000);
        Assertions.assertEquals(149245887792L, output);
    }

}
