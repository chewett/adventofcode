package net.chewett.adventofcode.aoc2023;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day7HandTest {

    @Test
    public void testOrderValueComparison() {
        long val = new Day7Hand("KK677 1").getOrderValue();
        long val2 = new Day7Hand("KTJJT 2").getOrderValue();
        Assertions.assertTrue(val > val2);
    }

    @Test
    public void testOrderValueComparisonWithJokers() {
        long val = new Day7Hand("KKKK1 1", true).getOrderValue();
        long val2 = new Day7Hand("KKKKJ 2", true).getOrderValue();
        Assertions.assertTrue(val > val2);
    }

    @Test
    public void testValueOrderComparison() {
        long val = new Day7Hand("KK678 1", true).getValueOfHandType();
        long val2 = new Day7Hand("KJQAT 2", true).getValueOfHandType();
        Assertions.assertEquals(val, val2);
    }

    @Test
    public void testValueOrderForJokers() {
        Assertions.assertEquals(8, new Day7Hand("JJJJJ 1", true).getValueOfHandType());
        Assertions.assertEquals(8, new Day7Hand("JJJJQ 1", true).getValueOfHandType());
        Assertions.assertEquals(8, new Day7Hand("JJJQQ 1", true).getValueOfHandType());
        Assertions.assertEquals(8, new Day7Hand("JJQQQ 1", true).getValueOfHandType());
        Assertions.assertEquals(8, new Day7Hand("JQQQQ 1", true).getValueOfHandType());

        Assertions.assertEquals(7, new Day7Hand("JJJAQ 1", true).getValueOfHandType());
        Assertions.assertEquals(7, new Day7Hand("JJAAQ 1", true).getValueOfHandType());
        Assertions.assertEquals(7, new Day7Hand("JJAAQ 1", true).getValueOfHandType());
        Assertions.assertEquals(7, new Day7Hand("JAAAQ 1", true).getValueOfHandType());

        Assertions.assertEquals(6, new Day7Hand("JAQQA 1", true).getValueOfHandType());

        Assertions.assertEquals(5, new Day7Hand("J1134 1", true).getValueOfHandType());

        Assertions.assertEquals(4, new Day7Hand("31134 1", true).getValueOfHandType());

        Assertions.assertEquals(3, new Day7Hand("1234J 1", true).getValueOfHandType());

        //1 joker
        Assertions.assertEquals(8, new Day7Hand("J1111 1", true).getValueOfHandType());
        Assertions.assertEquals(7, new Day7Hand("J2111 1", true).getValueOfHandType());
        Assertions.assertEquals(6, new Day7Hand("J2211 1", true).getValueOfHandType());
        Assertions.assertEquals(5, new Day7Hand("J2213 1", true).getValueOfHandType());
        Assertions.assertEquals(3, new Day7Hand("J4213 1", true).getValueOfHandType());
    }

}
